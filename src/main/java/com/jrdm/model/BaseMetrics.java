package com.jrdm.model;

import org.apache.log4j.Logger;

public class BaseMetrics {
	
	final static Logger logger = Logger.getLogger(BaseMetrics.class);
	
	private final static String ACCESS_VECTOR = "AV";
	private final static String ACCESS_COMPLEX = "AC";
	private final static String AUTH = "Au";
	private final static String CONF = "C";
	private final static String INTEG_IMP = "I";
	private final static String AVAIL_IMP = "A";
	
	private String accessVector; 
	private String accessComplexity;
	private String authentication;
	private String confidentialityImpact;
	private String integrityImpact;
	private String availabilityImpact;
	
	private double avscore;
	private double acscore;
	private double auscore;
	private double cscore;
	private double iscore;
	private double ascore;
	
	private final static String NONE = "N";
	private final static String PARTIAL = "P";
	private final static String COMPLETE = "C";
	
	private final static String AV_LOCAL = "L";
	private final static String AV_ADJ = "A";
	private final static String AV_NET = "N";
	
	private final static String HIGH = "H";
	private final static String MEDIUM = "M";
	private final static String LOW = "L";
	
	private final static String AU_MULTIPLE = "M";
	private final static String AU_SINGLE = "S";
	private final static String AU_NONE = "N";
	
	
	public BaseMetrics(String v){
		String[] vector = v.split("/");
		
		this.avscore = 0.0;
		this.acscore = 0.0;
		this.auscore = 0.0;
		this.cscore = 0.0;
		this.iscore = 0.0;
		this.ascore = 0.0;
		
		for(String keyValue : vector){
			//keyValue=> AV:N
			String[] s = keyValue.split(":");
			String key = s[0];
			String value = s[1];
			
			if(key.trim().equalsIgnoreCase(ACCESS_VECTOR)){
				this.accessVector = value.trim();
			} else if(key.trim().equalsIgnoreCase(ACCESS_COMPLEX)){
				this.accessComplexity = value.trim();
			} else if(key.trim().equalsIgnoreCase(AUTH)){
				this.authentication = value.trim();
			} else if(key.trim().equalsIgnoreCase(CONF)){
				this.confidentialityImpact = value.trim();
			} else if(key.trim().equalsIgnoreCase(INTEG_IMP)){
				this.integrityImpact = value.trim();
			} else if(key.trim().equalsIgnoreCase(AVAIL_IMP)){
				this.availabilityImpact = value.trim();
			}
		}
		
	}
	
	/**
	 * Calculates the base metrics score based on CVSS version 2 computation
	 */
	public void calculate(){
		
		calcAccessVector();
		calcAccessComplexity();
		calcAuthentication();
		calcConfidentialityImpact();
		calcIntegrityImpact();
		calcAvailabilityImpact();
		
	}
	
	/**
	 * AccessVector: Local Access (L) 0.395 / Adjacent Network (A) 0.646 / Network (N) 1.0
	 */
	
	private void calcAccessVector(){
		//Access Vector
		if(this.accessVector.equalsIgnoreCase(AV_LOCAL))
			this.avscore = 0.395;
		else if(this.accessVector.equalsIgnoreCase(AV_ADJ))
			this.avscore = 0.646;
		else if(this.accessVector.equalsIgnoreCase(AV_NET))
			this.avscore = 1.0;
	}
	
	/**
	 * AccessComplexity: High (H) 0.35 / Medium (M) 0.61 / Low (L) 0.71
	 */
	private void calcAccessComplexity(){
		if(this.accessComplexity.equalsIgnoreCase(HIGH))
			this.acscore = 0.35;
		else if(this.accessComplexity.equalsIgnoreCase(MEDIUM))
			this.acscore = 0.61;
		else if(this.accessComplexity.equalsIgnoreCase(LOW))
			this.acscore = 0.71;
	}
	
	/**
	 * Authentication: Multiple (M) 0.45 / Single (S) 0.56 / None (N) 0.706
	 */
	private void calcAuthentication(){
		//Access Complexity
		if(this.authentication.equalsIgnoreCase(AU_MULTIPLE))
			this.auscore = 0.45;
		else if(this.authentication.equalsIgnoreCase(AU_SINGLE))
			this.auscore = 0.56;
		else if(this.authentication.equalsIgnoreCase(AU_NONE))
			this.auscore = 0.706;
	}
	
	/**
	 * Confidentiality Impact: None (N) 0.0 / Partial (P) 0.275 / Complete (C) 0.660
	 */
	private void calcConfidentialityImpact(){
		if(this.confidentialityImpact.equalsIgnoreCase(NONE))
			this.cscore = 0.0;
		else if(this.confidentialityImpact.equalsIgnoreCase(PARTIAL))
			this.cscore = 0.275;
		else if(this.confidentialityImpact.equalsIgnoreCase(COMPLETE))
			this.cscore = 0.660;
	}
	
	/**
	 * Integrity Impact: None (N) 0.0 / Partial (P) 0.275 / Complete (C) 0.660
	 */
	private void calcIntegrityImpact(){
		if(this.integrityImpact.equalsIgnoreCase(NONE))
			this.iscore = 0.0;
		else if(this.integrityImpact.equalsIgnoreCase(PARTIAL))
			this.iscore = 0.275;
		else if(this.integrityImpact.equalsIgnoreCase(COMPLETE))
			this.iscore = 0.660;
	}
	
	/**
	 * Availability Impact: None (N) 0.0 / Partial (P) 0.275 / Complete (C) 0.660
	 */
	private void calcAvailabilityImpact(){
		if(this.availabilityImpact.equalsIgnoreCase(NONE))
			this.ascore = 0.0;
		else if(this.availabilityImpact.equalsIgnoreCase(PARTIAL))
			this.ascore = 0.275;
		else if(this.availabilityImpact.equalsIgnoreCase(COMPLETE))
			this.ascore = 0.660;
	}
	
	

	@Override
	public String toString() {
		return "BaseMetrics{" +
				"accessVector='" + accessVector + '\'' +
				", accessComplexity='" + accessComplexity + '\'' +
				", authentication='" + authentication + '\'' +
				", confidentialityImpact='" + confidentialityImpact + '\'' +
				", integrityImpact='" + integrityImpact + '\'' +
				", availabilityImpact='" + availabilityImpact + '\'' +
				"\n---------------scores---------------------\n" +
				", avscore=" + avscore +
				", acscore=" + acscore +
				", auscore=" + auscore +
				", cscore=" + cscore +
				", iscore=" + iscore +
				", ascore=" + ascore +
				'}';
	}

	public String getAccessVector() {
		return accessVector;
	}
	public String getAccessComplexity() {
		return accessComplexity;
	}
	public String getAuthentication() {
		return authentication;
	}
	public String getConfidentialityImpact() {
		return confidentialityImpact;
	}
	public String getIntegrityImpact() {
		return integrityImpact;
	}
	public String getAvailabilityImpact() {
		return availabilityImpact;
	}

	public double getAccessVectorScore() {
		return avscore;
	}

	public double getAccessComplexityScore() {
		return acscore;
	}

	public double getAuthenticationScore() {
		return auscore;
	}

	public double getConfidentialityScore() {
		return cscore;
	}

	public double getIntegrityScore() {
		return iscore;
	}

	public double getAvailabilityScore() {
		return ascore;
	}
	
	
	
	
	
	
	
	

}
