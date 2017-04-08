package com.jrdm.model;

public class Cvss {
	
	private double score;
	private String vector;
	private int rank;
	private String rankDescription;
	private double scoreFrom;
	private double scoreTo;
	
	private double noVectorScore;
	
	public Cvss(){
		
	}

	@Override
	public String toString() {
		return "Cvss{" +
				"score=" + score +
				", vector='" + vector + '\'' +
				", rank=" + rank +
				", rankDescription='" + rankDescription + '\'' +
				", scoreFrom=" + scoreFrom +
				", scoreTo=" + scoreTo +
				", noVectorScore=" + noVectorScore +
				'}';
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public String getVector() {
		return vector;
	}

	public void setVector(String vector) {
		this.vector = vector;
	}

	public int getRank() {
		return rank;
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

	public double getScoreFrom() {
		return scoreFrom;
	}

	public void setScoreFrom(double scoreFrom) {
		this.scoreFrom = scoreFrom;
	}

	public double getScoreTo() {
		return scoreTo;
	}

	public void setScoreTo(double scoreTo) {
		this.scoreTo = scoreTo;
	}

	public double getNoVectorScore() {
		return noVectorScore;
	}

	public void setNoVectorScore(double noVectorScore) {
		this.noVectorScore = noVectorScore;
	}
	

}
