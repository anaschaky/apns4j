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

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.util.logging.Logger;

/**
 * User: Jonathan Garay <netmask@webtelmex.net.mx>
 * Created: Apr 9, 2009 2:19:51 PM
 */
public class APNSMessage implements Comparable {

    public enum DESTINATION {
        DEFAULT,
        TEST
    }

    public static final byte COMMAND = 0;

    private Short deviceTokenLenght;

    private byte[] deviceToken;
    private Short payloadLength;
    private byte[] payLoad;


    private String destination;


    /**
     * Message constructor takes the deviceToken as string and the payload
     *
     * @param deviceToken
     * @param payload
     * @throws UnsupportedEncodingException
     * @throws DecoderException
     */
    public APNSMessage(String deviceToken, String payload) throws UnsupportedEncodingException, DecoderException {
        if (payload.length() > 128) Logger.getAnonymousLogger().warning("payload length > 128 ");
        this.deviceToken = Hex.decodeHex(deviceToken.replace(" ", "").toCharArray());
        this.deviceTokenLenght = (short) this.deviceToken.length;
        this.payLoad = payload.getBytes("UTF-8");
        payloadLength = (short) this.payLoad.length;
    }


    public APNSMessage(String deviceToken, String payLoad, String connection) throws DecoderException, UnsupportedEncodingException {
        this(deviceToken, payLoad);
        this.destination = connection;
    }

    public Short getDeviceTokenLenght() {
        return deviceTokenLenght;
    }

    public void setDeviceTokenLenght(Short deviceTokenLenght) {
        this.deviceTokenLenght = deviceTokenLenght;
    }

    public byte[] getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(byte[] deviceToken) {
        this.deviceToken = deviceToken;
    }

    public short getPayloadLength() {
        return payloadLength;
    }

    public void setPayloadLength(short payloadLength) {
        this.payloadLength = payloadLength;
    }

    public byte[] getPayLoad() {
        return payLoad;
    }

    public void setPayLoad(byte[] payLoad) {

        this.payLoad = payLoad;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int compareTo(Object o) {
        APNSMessage APNSMessage = (APNSMessage) o;
        if (APNSMessage.deviceTokenLenght == this.deviceTokenLenght && APNSMessage.payloadLength == this.getPayloadLength()) {
            return 0;
        } else {
            return 1;
        }
    }
}
