package com.jrdm.model;

public class NinvasProperties {
	
	private String exploitSearchUrl;
	private String defaultShodanApiKey;
	private String googleApiKey;
	private String database;
	private String dbpassword;
	private String dbName;
	
	
	public String getExploitSearchUrl() {
		return exploitSearchUrl;
	}
	public void setExploitSearchUrl(String exploitSearchUrl) {
		this.exploitSearchUrl = exploitSearchUrl;
	}
	public String getDefaultShodanApiKey() {
		return defaultShodanApiKey;
	}
	public void setDefaultShodanApiKey(String defaultShodanApiKey) {
		this.defaultShodanApiKey = defaultShodanApiKey;
	}
	public String getGoogleApiKey() {
		return googleApiKey;
	}
	public void setGoogleApiKey(String googleApiKey) {
		this.googleApiKey = googleApiKey;
	}
	public String getDatabase() {
		return database;
	}
	public void setDatabase(String database) {
		this.database = database;
	}
	public String getDbpassword() {
		return dbpassword;
	}
	public void setDbpassword(String dbpassword) {
		this.dbpassword = dbpassword;
	}
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	@Override
	public String toString() {
		return "NinvasProperties{" +
				"exploitSearchUrl='" + exploitSearchUrl + '\'' +
				", defaultShodanApiKey='" + defaultShodanApiKey + '\'' +
				", googleApiKey='" + googleApiKey + '\'' +
				", database='" + database + '\'' +
				", dbpassword='" + dbpassword + '\'' +
				", dbName='" + dbName + '\'' +
				'}';
	}
}
