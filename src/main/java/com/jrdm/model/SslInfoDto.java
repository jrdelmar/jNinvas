/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Newhouse (nhitbh at gmail dot com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.jrdm.model;

import com.fooock.shodan.model.banner.Cipher;
import com.fooock.shodan.model.banner.DiffieHellmanParams;

import java.util.ArrayList;
import java.util.List;

/**
 * If the service uses SSL, such as HTTPS, then the banner will also contain this object info
 */
public class SslInfoDto {

    private List<String> chain;
    private List<String> versions;

    private Cipher cipher;
   // private DiffieHellmanParams diffieHellmanParams;

    SslInfoDto() {
        chain = new ArrayList<String>();
        versions = new ArrayList<String>();

    }

    public List<String> getChain() {
        return chain;
    }

    public void setChain(List<String> chain) {
        this.chain = chain;
    }

    public List<String> getVersions() {
        return versions;
    }

    public void setVersions(List<String> versions) {
        this.versions = versions;
    }

    public Cipher getCipher() {
        return cipher;
    }

    public void setCipher(Cipher cipher) {
        this.cipher = cipher;
    }


    @Override
    public String toString() {
        return "SslInfoDto{" +
                "chain=" + chain +
                ", versions=" + versions +
                ", cipher=" + cipher +
                '}';
    }
}
