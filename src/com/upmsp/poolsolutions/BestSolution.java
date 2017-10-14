package com.upmsp.poolsolutions;

import com.upmsp.structure.Solution;

public class BestSolution extends Solution{
	
	private Solution best_solution;
	private int best_fo;
	
	public BestSolution(Solution solution){
		this.best_solution = solution;
		this.best_fo = this.best_solution.makespan();
	}

	public Solution getBest_solution() {
		return best_solution;
	}

	public void setBest_solution(Solution best_solution) {
		this.best_solution = best_solution;
	}

	public int getBest_fo() {
		return best_solution.makespan();
	}

	public void setBest_fo(int best_fo) {
		this.best_fo = best_fo;
	}
	
	

}
