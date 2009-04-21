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

import org.json.JSONException;
import org.json.JSONObject;

/**
 * User: Jonathan Garay <netmask@webtelmex.net.mx>
 * Created: Apr 9, 2009 4:01:53 PM
 */
public class PayloadBuilder {
    private JSONObject root;
    private JSONObject aps;
    private JSONObject composeAlert;

    public PayloadBuilder() throws JSONException {
        root = new JSONObject().put("aps", " ");
        aps = new JSONObject();
        composeAlert = new JSONObject();
    }

    public static PayloadBuilder getBuilder() {
        try {
            return new PayloadBuilder();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String simpleAPS(String simpleAps) throws JSONException {
        return root.put("aps", simpleAps).toString();
    }


    public PayloadBuilder simpleAlert(String alert) throws JSONException {
        aps.put("alert", alert);
        return this;
    }

    public PayloadBuilder badge(int badge) throws JSONException {
        aps.put("badge", badge);
        return this;
    }

    public PayloadBuilder sound(String sound) throws JSONException {
        aps.put("sound", sound);
        return this;
    }

    public PayloadBuilder composeAlert() throws JSONException {
        aps.put("alert", composeAlert);
        return this;
    }

    public PayloadBuilder alertBody(String body) throws JSONException {
        composeAlert.put("body", body);
        return this;
    }

    public PayloadBuilder alertActionLocKey(String actionLocKet) throws JSONException {
        composeAlert.put("action-loc-key", actionLocKet);
        return this;
    }

    public PayloadBuilder alertlocKey(String locKey) throws JSONException {
        composeAlert.put("loc-key", locKey);
        return this;
    }

    public PayloadBuilder alertlocArgs(String... args) throws JSONException {
        composeAlert.put("loc-args", args);
        return this;
    }

    public PayloadBuilder customAarg(String key, Object value) throws JSONException {
        root.put(key, value);
        return this;
    }


    @Override
    public String toString() {
        try {
            root.put("aps", aps);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return root.toString();
    }


}
