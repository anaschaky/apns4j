/*
 *  Copyright (c) 2009, Jonathan Garay Mendoza
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *      * Redistributions of source code must retain the above copyright
 *        notice, this list of conditions and the following disclaimer.
 *      * Redistributions in binary form must reproduce the above copyright
 *        notice, this list of conditions and the following disclaimer in the
 *        documentation and/or other materials provided with the distribution.
 *      * Neither the name of the <organization> nor the
 *        names of its contributors may be used to endorse or promote products
 *        derived from this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY Jonathan Garay ''AS IS'' AND ANY
 *  EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED. IN NO EVENT SHALL <copyright holder> BE LIABLE FOR ANY
 *  DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 *  (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 *  LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 *  ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *  SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */

package net.devmask.apns;

import org.apache.commons.codec.binary.Hex;

import javax.net.ssl.SSLSocket;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.logging.Logger;

/**
 * User: Jonathan Garay <netmask@webtelmex.net.mx>
 * Created: Apr 9, 2009 3:14:39 PM
 */
public class APNSConnection extends Thread {
    private SSLSocket socket;
    private OutputStream outputStream;
    private volatile PriorityBlockingQueue<APNSMessage> queue;
    private static final byte[] pingbite = {0, 0};
    private APNSConnectionMonitor monitor;
    private boolean persistent;

    /**
     * Constructor takes the socket and opens the OutputStream
     *
     * @param socket
     */
    public APNSConnection(SSLSocket socket) {
        super("APSNConnection: " + Thread.currentThread().getId());
        this.socket = socket;
        try {
            outputStream = this.socket.getOutputStream();
            queue = new PriorityBlockingQueue<APNSMessage>(50);
            Logger.getAnonymousLogger().info("Connection ready " + Thread.currentThread().getId());
            messageQueueWorker();
        } catch (IOException e) {
            Logger.getAnonymousLogger().warning(e.getLocalizedMessage());
        }
    }

    public APNSConnection(SSLSocket socket, boolean persistent) {
        this(socket);
        this.persistent = persistent;
    }


    /**
     * Start a message worker checks the queue for new messages and sends the head message to the output stream
     */
    private void messageQueueWorker() {
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    checkSanity();
                    if (queue.size() >= 1) {
                        messageWriter(queue.remove());
                        //Todo: this inst the safe way
                        if (persistent) break;
                    }

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /**
     * Writes a message to the OutputStream
     *
     * @param APNSMessage
     */
    private void messageWriter(APNSMessage APNSMessage) {

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(baos);

            dos.writeByte(APNSMessage.COMMAND);
            dos.writeShort(APNSMessage.getDeviceTokenLenght());
            dos.write(APNSMessage.getDeviceToken());
            dos.writeShort(APNSMessage.getPayloadLength());
            dos.write(APNSMessage.getPayLoad());


            byte[] output = baos.toByteArray();

            outputStream.write(output);
            Logger.getAnonymousLogger().info("[pid]" + Thread.currentThread().getId() + "Sending APNSMessage: " + new String(Hex.encodeHex(baos.toByteArray())));
            outputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * Gets the queue size
     *
     * @return Integer queue  size
     */
    public synchronized Integer queueSize() {
        return queue.size();
    }

    /**
     * add a bunch of messages to the queue
     *
     * @param APNSMessages
     */
    public synchronized void addBulkMessages(APNSMessage[] APNSMessages) {
        Collections.addAll(queue, APNSMessages);
    }

    /**
     * Add a message to the queue
     *
     * @param APNSMessage
     */
    public synchronized void addMessage(APNSMessage APNSMessage) {
        queue.add(APNSMessage);
    }


    public void registerScoketInpector(APNSConnectionMonitor monitor) {
        this.monitor = monitor;
    }


    private void checkSanity() {
        if (socket.isClosed() && socket.isInputShutdown() && socket.isOutputShutdown()) {
            monitor.onAPNSStop(socket);
        }
    }


}
