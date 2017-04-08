package com.jrdm.model;

public class Misconfiguration {
	
	private String misconfiguration;
	private int rank;
	private String rankDescription;

	private BannerDto bannerDto;
	private String misconfigurationForDisplay;


	public Misconfiguration(String mis, int rank, String desc){
		this.misconfiguration = mis;
		this.rank = rank;
		this.rankDescription = desc;
	}
	
	public String getMisconfiguration() {
		return misconfiguration;
	}
	public void setMisconfiguration(String misconfiguration) {
		this.misconfiguration = misconfiguration;
	}
	
	public int getRank() {
		return rank;
	}

    @Override
    public String toString() {
        return "Misconfiguration{" +
                "misconfiguration='" + misconfiguration + '\'' +
                ", rank=" + rank +
                ", rankDescription='" + rankDescription + '\'' +
                ", bannerDto=" + bannerDto +
                ", misconfigurationForDisplay='" + misconfigurationForDisplay + '\'' +
                '}';
    }

    public void setRank(int rank) {
		this.rank = rank;
	}

	public String getRankDescription() {
		return rankDescription;
	}

	public void setRankDescription(String rankDescription) {
		this.rankDescription = rankDescription;
	}

    public BannerDto getBannerDto() {
        return bannerDto;
    }

    public void setBannerDto(BannerDto bannerDto) {
        this.bannerDto = bannerDto;
    }

    public String getMisconfigurationForDisplay() {
        return misconfigurationForDisplay;
    }

    public void setMisconfigurationForDisplay(String misconfigurationForDisplay) {
        this.misconfigurationForDisplay = misconfigurationForDisplay;
    }


}
