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

import java.io.File;

/**
 * User: Jonathan Garay <netmask@webtelmex.net.mx>
 * Created: Apr 20, 2009 9:42:50 PM
 */
public class APNSConnectionConfig {


    private String connectionName;
    private String name;
    private String p12FileToken;
    private File p12File;

    private boolean persistent;


    /**
     * @param name
     * @param p12FileToken
     * @param p12File
     * @param connectionName
     * @return APNSConnectionConfig
     */
    public static APNSConnectionConfig fromVervose(String name, String p12FileToken, File p12File, String connectionName) {
        APNSConnectionConfig config = new APNSConnectionConfig();
        config.setName(name);
        config.setP12FileToken(p12FileToken);
        config.setP12File(p12File);
        config.setConnectionName(connectionName);
        return config;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getP12FileToken() {
        return p12FileToken;
    }

    public void setP12FileToken(String p12FileToken) {
        this.p12FileToken = p12FileToken;
    }

    public File getP12File() {
        return p12File;
    }

    public void setP12File(File p12File) {
        this.p12File = p12File;
    }

    public String getConnectionName() {
        return connectionName;
    }

    public void setConnectionName(String connectionName) {
        this.connectionName = connectionName;
    }

    public boolean isPersistent() {
        return persistent;
    }

    public void setPersistent(boolean persistent) {
        this.persistent = persistent;
    }


}
