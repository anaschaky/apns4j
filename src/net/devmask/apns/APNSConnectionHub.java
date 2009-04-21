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

import java.util.HashMap;

/**
 * User: Jonathan Garay <netmask@webtelmex.net.mx>
 * Created: Apr 20, 2009 9:41:09 PM
 */
public class APNSConnectionHub {

    private volatile HashMap<String, APNSConnectionHandler> connectionMap;

    public APNSConnectionHub(APNSConfigFile configFile) {

        connectionMap = new HashMap();

        for (APNSConnectionConfig config : configFile.getConnectionConfig()) {
            try {
                connectionMap.put(config.getConnectionName(), new APNSConnectionHandler(config));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public APNSConnectionHub() {

    }


    public void sendMessage(APNSMessage APNSMessage) {
        getConnectionForName(APNSMessage.getDestination()).sendMessage(APNSMessage);
    }

    public APNSConnectionHandler getConnectionForName(String name) {
        return connectionMap.get(name);
    }

}
