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

import com.fooock.shodan.model.banner.Banner;
import com.fooock.shodan.model.banner.Location;
import com.fooock.shodan.model.banner.Metadata;
import com.fooock.shodan.model.banner.Options;
import com.fooock.shodan.model.banner.SslInfo;
import com.google.gson.annotations.SerializedName;
import com.jrdm.common.Utility;

import java.util.Arrays;
import java.util.List;

/**
 * The banner is the main type of information that Shodan provides through the REST and Streaming
 * API.
 */
public class BannerDto {

    private int port;

    private long ip;

    private String asn;
    private String data;

    private String ipStr;
    private String ipv6;
    private String timestamp;

    private String[] hostnames;
    private String[] domains;

    private Location location;

    private Options options;

    private Metadata metadata;

    // ssl info
    private boolean isSslEnabled;

    private SslInfoDto sslInfoDto;

    // Optional properties
    private int uptime;

    private String link;
    private String title;
    private String html;
    private String product;
    private String version;
    private String isp;
    private String os;
    private String transport;

    private String deviceType;
    private String info;

    private String[] cpe;
    
    //misconfiguration score per banner
    private double totalScore;
    private List<Misconfiguration> misconfigList;
    
    
    public BannerDto() {

    }
    
    public BannerDto(Banner b) {
    	
    	this.asn = b.getAsn();
    	this.port = b.getPort();
    	this.ip = b.getIp();
    	this.ipStr = b.getIpStr();
    	this.ipv6 = b.getIpv6();
    	this.timestamp = b.getTimestamp();
    	this.hostnames = b.getHostnames();
    	this.domains = b.getDomains();
    	this.location = b.getLocation();
    	this.options = b.getOptions();
    	this.metadata = b.getMetadata();
    	convertSslDto(b.getSslInfo());
    	this.isSslEnabled = b.isSslEnabled();
    	this.uptime = b.getUptime();
    	this.link = b.getLink();
    	this.title = b.getTitle();
    	this.product = b.getProduct();
    	this.version = b.getVersion();
    	this.isp = b.getIsp();
    	this.os = b.getOs();
    	this.transport = b.getTransport();
    	this.deviceType = b.getDeviceType();
    	this.info = b.getInfo();
    	this.cpe = b.getCpe();
    	
    	this.data = b.getData();

    }

    /**
     * @return If the service uses SSL, such as HTTPS, this method returns true, otherwise return false
     */
    public boolean isSslEnabled() {
        return isSslEnabled;
    }

    /**
     * @return The IP address of the host as an integer.
     */
    public long getIp() {
        return ip;
    }

    /**
     * @return The port number that the service is operating on.
     */
    public int getPort() {
        return port;
    }

    /**
     * @return The autonomous system number (ex. "AS4837")
     */
    public String getAsn() {
        return asn;
    }

    /**
     * @return Contains the banner information for the service.
     */
    public String getData() {
        return data;
    }

    /**
     * @return The IP address of the host as a string.
     */
    public String getIpStr() {
        return ipStr;
    }

    /**
     * @return The IPv6 address of the host as a string. If this is present then the "ip" and "ip_str"
     * fields wont be
     */
    public String getIpv6() {
        return ipv6;
    }

    /**
     * @return The timestamp for when the banner was fetched from the device in the UTC timezone.
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * @return An array of strings containing all of the hostnames that have been assigned to the IP
     * address for this device.
     */
    public String[] getHostnames() {
        return hostnames;
    }

    /**
     * @return An array of strings containing the top-level domains for the hostnames of the device.
     * This is a utility property in case you want to filter by TLD instead of subdomain. It is smart
     * enough to handle global TLDs with several dots in the domain (ex. "co.uk")
     */
    public String[] getDomains() {
        return domains;
    }

    /**
     * @return An object {@link Location} containing all of the location information for the device.
     */
    public Location getLocation() {
        return location;
    }

    /**
     * @return Contains experimental and supplemental data for the service. This can include the SSL
     * certificate, robots.txt and other raw information that hasn't yet been formalized into the
     * BannerDto Specification.
     */
    public Options getOptions() {
        return options;
    }

    /**
     * @return Shodan {@link Metadata}
     */
    public Metadata getMetadata() {
        return metadata;
    }

    /**
     * @return The number of minutes that the device has been online.
     */
    public int getUptime() {
        return uptime;
    }

    /**
     * @return The network link type. Possible values are: "Ethernet or modem", "generic tunnel or
     * VPN", "DSL", "IPIP or SIT", "SLIP", "IPSec or GRE", "VLAN", "jumbo Ethernet", "Google", "GIF",
     * "PPTP", "loopback", "AX.25 radio modem".
     */
    public String getLink() {
        return link;
    }

    /**
     * @return The title of the website as extracted from the HTML source.
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return The raw HTML source for the website.
     */
    public String getHtml() {
        return html;
    }

    /**
     * @return The name of the product that generated the banner.
     */
    public String getProduct() {
        return product;
    }

    /**
     * @return The version of the product that generated the banner.
     */
    public String getVersion() {
        return version;
    }

    /**
     * @return The type of device (webcam, router, etc.).
     */
    public String getDeviceType() {
        return deviceType;
    }

    /**
     * @return Miscellaneous information that was extracted about the product.
     */
    public String getInfo() {
        return info;
    }

    /**
     * @return The relevant Common Platform Enumeration for the product or known vulnerabilities if
     * available.
     */
    public String[] getCpe() {
        return cpe;
    }

    /**
     * @return The ISP that is providing the organization with the IP space for this device. Consider
     * this the "parent" of the organization in terms of IP ownership.
     */
    public String getIsp() {
        return isp;
    }

    /**
     * @return The operating system that powers the device.
     */
    public String getOs() {
        return os;
    }

    /**
     * @return Either "udp" or "tcp" to indicate which IP transport protocol was used to fetch the
     * information
     */
    public String getTransport() {
        return transport;
    }

    /**
     * Check if the ssl info is available using the {@link #isSslEnabled()} method
     *
     * @return Ssl info for this service, if available
     */
    public SslInfoDto getSslInfoDto() {
        return sslInfoDto;
    }

    public void setSslEnabled(boolean sslEnabled) {
        isSslEnabled = sslEnabled;
    }

    public void setSslInfoDto(SslInfoDto sslInfoDto) {
        this.sslInfoDto = sslInfoDto;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setIp(long ip) {
        this.ip = ip;
    }

    public void setAsn(String asn) {
        this.asn = asn;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setIpStr(String ipStr) {
        this.ipStr = ipStr;
    }

    public void setIpv6(String ipv6) {
        this.ipv6 = ipv6;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setHostnames(String[] hostnames) {
        this.hostnames = hostnames;
    }

    public void setDomains(String[] domains) {
        this.domains = domains;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setOptions(Options options) {
        this.options = options;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public void setUptime(int uptime) {
        this.uptime = uptime;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setCpe(String[] cpe) {
        this.cpe = cpe;
    }

    @Override
    public String toString() {
        return "BannerDto{" +
                "port=" + port +
                ", ip=" + ip +
                ", asn='" + asn + '\'' +
                ", data='" + data + '\'' +
                ", ipStr='" + ipStr + '\'' +
                ", ipv6='" + ipv6 + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", hostnames=" + Arrays.toString(hostnames) +
                ", domains=" + Arrays.toString(domains) +
                ", location=" + location +
                ", options=" + options +
                ", metadata=" + metadata +
                ", isSslEnabled=" + isSslEnabled +
                ", sslInfoDto=" + sslInfoDto +
                ", uptime=" + uptime +
                ", link='" + link + '\'' +
                ", title='" + title + '\'' +
                ", html='" + html + '\'' +
                ", product='" + product + '\'' +
                ", version='" + version + '\'' +
                ", isp='" + isp + '\'' +
                ", os='" + os + '\'' +
                ", transport='" + transport + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", info='" + info + '\'' +
                ", cpe=" + Arrays.toString(cpe) +
                ", totalScore=" + totalScore +
                ", misconfigList=" + misconfigList +
                '}';
    }

    public double getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(double totalScore) {
		this.totalScore = totalScore;
	}

	public List<Misconfiguration> getMisconfigList() {
		return misconfigList;
	}

	public void setMisconfigList(List<Misconfiguration> misconfigList) {
		this.misconfigList = misconfigList;
	}


	private void convertSslDto(SslInfo sslInfo){

        sslInfoDto = new SslInfoDto();
        if(sslInfo!=null){
           if(sslInfo.getVersions()!=null) sslInfoDto.setVersions( Utility.cleanArray(sslInfo.getVersions()) );
           if(sslInfo.getChain() !=null) sslInfoDto.setChain( Utility.cleanArray( sslInfo.getChain() ) );
           if(sslInfo.getCipher()!=null) sslInfoDto.setCipher( sslInfo.getCipher() );
        }

        //System.out.println("sslinfodto=>" + sslInfoDto.toString());



    }


}
