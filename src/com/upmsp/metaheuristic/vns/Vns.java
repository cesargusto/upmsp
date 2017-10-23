package com.upmsp.metaheuristic.vns;

import java.util.Random;

import com.upmsp.localsearch.LocalSearch;
import com.upmsp.metaheuristic.SA.MovimentosSA;
import com.upmsp.experiment.BestResults;
import com.upmsp.structure.Solution;

public class Vns {
	
	private Solution best_solution;
	private Solution solution;
	private BestResults best_results;
	private LocalSearch ls;
	private MovimentosSA m_sa;
	private final int quant_moviments = 5;
	private int num_it;
	
	public Vns(Solution s, int num_it, BestResults best_results) throws CloneNotSupportedException{
		this.num_it = num_it;
		this.solution = s;
		this.best_results = best_results;
		this.best_solution = s.clone();
		this.ls = new LocalSearch();
		this.m_sa = new MovimentosSA();
	}
	public Solution execute_vns() throws CloneNotSupportedException{
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
						this.solution = m_sa.two_realloc(solution);
						this.solution = m_sa.two_swap(solution);
						v = 1;
					}else
						{
							this.solution = m_sa.two_realloc(solution);
							v = 1;
						}
				}
			}
			this.best_results.setMakespan_list(best_solution.makespan());
			num_it --;
		}
		//this.best_solution.print_solution();
		return best_solution;
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
