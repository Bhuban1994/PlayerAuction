package com.mindtree.PlayerAuction.entity;

public class Player {
	private int player_no;
	private String name;
	private static String category;
	private int highestscore;
	private String bestfigure;

	public int getPlayer_no() {
		return player_no;
	}

	public void setPlayer_no(int player_no) {
		this.player_no = player_no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getHighestscore() {
		return highestscore;
	}

	public void setHighestscore(int highestscore) {
		this.highestscore = highestscore;
	}

	public String getBestfigure() {
		return bestfigure;
	}

	public void setBestfigure(String bestFigure) {
		// TODO Auto-generated method stub
		this.bestfigure=bestFigure;
	}
}
