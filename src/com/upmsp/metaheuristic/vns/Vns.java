package com.upmsp.metaheuristic.vns;

import java.util.Random;

import com.upmsp.localsearch.LocalSearch;
import com.upmsp.structure.Solution;

public class Vns {
	
	private Solution best_solution;
	private Solution solution;
	private LocalSearch ls;
	private final int quant_moviments = 5;
	private int num_it = 1000000;
	
	public Vns(Solution s) throws CloneNotSupportedException{
		this.solution = s;
		this.best_solution = s.clone();
		this.ls = new LocalSearch();
	}
	public void execute() throws CloneNotSupportedException{
		int v = 1;
		Solution sol_aux;
		while(num_it > 0){
			sol_aux = vizinhanca(v, this.solution);
			
			int mspan_1 = sol_aux.makespan();
			int mspan_2 = this.best_solution.makespan();
			
			if(mspan_1 < mspan_2){
				this.best_solution = sol_aux.clone();
				this.solution = sol_aux.clone();
				v = 1;
			}else{
				if(v < quant_moviments){
					v = v + 1;
				}	
				else{
					Random rnd = new Random();
					if(rnd.nextBoolean()){
						this.solution = ls.two_realloc(solution);
						this.solution = ls.two_swap(solution);
					}else
						this.solution = ls.two_realloc(solution);
					v = 1;
				}
			}
			num_it --;
		}
		this.best_solution.print_solution();
	}
	
	public Solution vizinhanca(int v, Solution s) throws CloneNotSupportedException{

		switch(v){
		case 1:
			return ls.remove_job_Maq_mspan(s);
		case 2:
			return ls.swap_job_ExtraMaq(s);
		case 3:
			return ls.troca_intra_Maq(s);
		case 4:
			return ls.insert_intra_Maq(s);
		case 5:
			return ls.change_Maq(s);
		default:
			return null;
			//System.out.println("Problema com o valor aleatório.");
		}
	}
	

}
