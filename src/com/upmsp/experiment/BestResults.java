package com.upmsp.experiment;

import java.util.ArrayList;

public class BestResults {
	
	private ArrayList<Integer> makespan_list;
	
	public BestResults() {
		this.makespan_list = new ArrayList<>();
	}

	public ArrayList<Integer> getMakespan_list() {
		return makespan_list;
	}
	
	public Integer getElement_list(int i) {
		return makespan_list.get(i);
	}
	
	public int getSize_list() {
		return makespan_list.size();
	}

	public void setMakespan_list(int makespan) {
		this.makespan_list.add(makespan);
	}

}
