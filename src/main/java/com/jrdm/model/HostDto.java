package com.jrdm.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.fooock.shodan.model.banner.Banner;
import com.fooock.shodan.model.host.Host;

public class HostDto {

    private long ip;

    private double latitude;
    private double longitude;

    private String regionCode;
    private String areaCode;
    private String postalCode;
    private String dmaCode;
    private String countryCode;

    private String organization;
    private String asn;
    private String city;
    private String isp;
    private String countryCode3;
    private String countryName;
    
    private int[] ports;
    private String[] hostnames;
    private String[] tags;
    private String[] vulnerabilities;

    private String lastUpdate;

    private String ipStr;
    private String os;

    private List<BannerDto> bannerDto;
	private List<BannerDto> misconfigurationBannerDto;
    private List<ExploitDto> exploitDto;
    private List<Misconfiguration> misconfigurationList;

    private String insertedDate;
    private String id;

    //total count
    private int totalPorts;
    private int totalVulnerabilities;
    private int totalServices;
    private int totalMisconfigurations;


    public HostDto() {

    }
    
    public HostDto(Host t){
    	this.ip = t.getIp();
    	this.latitude = t.getLatitude();
    	this.longitude = t.getLongitude();
    	this.regionCode = t.getRegionCode();
    	this.areaCode = t.getAreaCode();
    	this.postalCode = t.getPostalCode();
    	this.dmaCode = t.getDmaCode();
    	this.countryCode = t.getCountryCode();
    	this.organization = t.getOrganization();
    	this.asn = t.getAsn();
    	this.city = t.getCity();
    	this.isp = t.getIsp();
    	this.countryCode3 = t.getCountryCode3();
    	this.countryName = t.getCountryName();
    	this.ports = t.getPorts();
    	this.hostnames = t.getHostnames();
    	this.tags = t.getTags();
    	this.vulnerabilities = t.getVulnerabilities();
    	this.lastUpdate = t.getLastUpdate();
    	this.ipStr = t.getIpStr();
    	this.os = t.getOs();
    	this.bannerDto = convertBanner( t.getBanners() ) ;
    }
    
    public void convertHost(Host t){
    	this.ip = t.getIp();
    	this.latitude = t.getLatitude();
    	this.longitude = t.getLongitude();
    	this.regionCode = t.getRegionCode();
    	this.areaCode = t.getAreaCode();
    	this.postalCode = t.getPostalCode();
    	this.dmaCode = t.getDmaCode();
    	this.countryCode = t.getCountryCode();
    	this.organization = t.getOrganization();
    	this.asn = t.getAsn();
    	this.city = t.getCity();
    	this.isp = t.getIsp();
    	this.countryCode3 = t.getCountryCode3();
    	this.countryName = t.getCountryName();
    	this.ports = t.getPorts();
    	this.hostnames = t.getHostnames();
    	this.tags = t.getTags();
    	this.vulnerabilities = t.getVulnerabilities();
    	this.lastUpdate = t.getLastUpdate();
    	this.ipStr = t.getIpStr();
    	this.os = t.getOs();
    	this.bannerDto = convertBanner( t.getBanners() ) ;
    }
    
    private List<BannerDto> convertBanner (List<Banner> t){
    	List<BannerDto> dtoList = new ArrayList<BannerDto>();
    	for(Banner b : t){
    		BannerDto dto = new BannerDto(b);
    		dtoList.add(dto);
    	}
    	
    	return dtoList;
    }

	public void setIpStr(String ipStr) {
		this.ipStr = ipStr;
	}

	public void setIp(long ip) {
		this.ip = ip;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public void setCountryCode3(String countryCode3) {
		this.countryCode3 = countryCode3;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public long getIp() {
		return ip;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public String getDmaCode() {
		return dmaCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public String getOrganization() {
		return organization;
	}

	public String getAsn() {
		return asn;
	}

	public String getCity() {
		return city;
	}

	public String getIsp() {
		return isp;
	}

	public String getCountryCode3() {
		return countryCode3;
	}

	public String getCountryName() {
		return countryName;
	}

	public int[] getPorts() {
		return ports;
	}

	public String[] getHostnames() {
		return hostnames;
	}

	public String[] getTags() {
		return tags;
	}

	public String[] getVulnerabilities() {
		return vulnerabilities;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public String getIpStr() {
		return ipStr;
	}

	public String getOs() {
		return os;
	}

	public List<BannerDto> getBannerDto() {
		return bannerDto;
	}


    public String getInsertedDate() {
        return insertedDate;
    }

    public void setInsertedDate(String insertedDate) {
        this.insertedDate = insertedDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "HostDto{" +
                "ip=" + ip +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", regionCode='" + regionCode + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", dmaCode='" + dmaCode + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", organization='" + organization + '\'' +
                ", asn='" + asn + '\'' +
                ", city='" + city + '\'' +
                ", isp='" + isp + '\'' +
                ", countryCode3='" + countryCode3 + '\'' +
                ", countryName='" + countryName + '\'' +
                ", ports=" + Arrays.toString(ports) +
                ", hostnames=" + Arrays.toString(hostnames) +
                ", tags=" + Arrays.toString(tags) +
                ", vulnerabilities=" + Arrays.toString(vulnerabilities) +
                ", lastUpdate='" + lastUpdate + '\'' +
                ", ipStr='" + ipStr + '\'' +
                ", os='" + os + '\'' +
                ", bannerDto=" + bannerDto +
                ", misconfigurationBannerDto=" + misconfigurationBannerDto +
                ", exploitDto=" + exploitDto +
                ", misconfigurationList=" + misconfigurationList +
                '}';
    }

    public List<ExploitDto> getExploitDto() {
		return exploitDto;
	}

	public void setExploitDto(List<ExploitDto> exploitDto) {
		this.exploitDto = exploitDto;
	}

    public List<Misconfiguration> getMisconfigurationList() {
        return misconfigurationList;
    }

    public void setMisconfigurationList(List<Misconfiguration> misconfigurationList) {
        this.misconfigurationList = misconfigurationList;
    }

    public void setBannerDto(List<BannerDto> bannerDto) {
        this.bannerDto = bannerDto;
    }

	public List<BannerDto> getMisconfigurationBannerDto() {
		return misconfigurationBannerDto;
	}

	public void setMisconfigurationBannerDto(List<BannerDto> misconfigurationBannerDto) {
		this.misconfigurationBannerDto = misconfigurationBannerDto;
	}

    public int getTotalPorts() {
        return totalPorts;
    }

    public void setTotalPorts(int totalPorts) {
        this.totalPorts = totalPorts;
    }

    public int getTotalVulnerabilities() {
        return totalVulnerabilities;
    }

    public void setTotalVulnerabilities(int totalVulnerabilities) {
        this.totalVulnerabilities = totalVulnerabilities;
    }

    public int getTotalServices() {
        return totalServices;
    }

    public void setTotalServices(int totalServices) {
        this.totalServices = totalServices;
    }

    public int getTotalMisconfigurations() {
        return totalMisconfigurations;
    }

    public void setTotalMisconfigurations(int totalMisconfigurations) {
        this.totalMisconfigurations = totalMisconfigurations;
    }
}