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

import javax.xml.bind.JAXB;
import java.io.File;
import java.io.Serializable;
import java.util.List;

/**
 * User: Jonathan Garay <netmask@webtelmex.net.mx>
 * Created: Apr 20, 2009 10:41:13 PM
 */
public class APNSConfigFile implements Serializable {

    private int maxConnections;
    private int minConnections;


    private List<APNSConnectionConfig> connectionConfig;

    public static APNSConfigFile fromFile(File file) {
        return JAXB.unmarshal(file, APNSConfigFile.class);
    }

    public void saveToFile(File file) {
        JAXB.marshal(this, file);
    }


    public int getMaxConnections() {
        return maxConnections;
    }

    public void setMaxConnections(int maxConnections) {
        this.maxConnections = maxConnections;
    }

    public int getMinConnections() {
        return minConnections;
    }

    public void setMinConnections(int minConnections) {
        this.minConnections = minConnections;
    }

    public List<APNSConnectionConfig> getConnectionConfig() {
        return connectionConfig;
    }

    public void setConnectionConfig(List<APNSConnectionConfig> connectionConfig) {
        this.connectionConfig = connectionConfig;
    }


}
