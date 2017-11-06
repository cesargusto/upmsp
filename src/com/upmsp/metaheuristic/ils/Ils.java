package com.upmsp.metaheuristic.ils;

import com.upmsp.experiment.BestResults;
import com.upmsp.localsearch.Moviments;
import com.upmsp.structure.Solution;

public class Ils {

	private Solution solucao;
	private LocalSearchILS ls_ils;
	private BestResults best_results;
	private Pertubations perturbation;
	private Moviments mov;
	private int iter_max;

	public Ils(Solution solucao, int iter_max, BestResults br){
		this.solucao = solucao;
		this.ls_ils = new LocalSearchILS();
		this.best_results = br;
		this.perturbation = new Pertubations();
		this.mov = new Moviments();
		this.iter_max = iter_max;
	}

	public Solution execute_ils() throws CloneNotSupportedException{
		int iter = 0;
		int level = 1;
		int s_fo = Integer.MAX_VALUE;
		int melhor_s_fo = Integer.MAX_VALUE;
		Solution s = ls_ils.local_search_ils(solucao);
		Solution melhor_s = s.clone();
		melhor_s_fo = melhor_s.makespan();
		while(iter < iter_max){
			iter = iter + 1;
			s = this.perturbation.execute(s, level);
			//s = this.mov.perturbation_hard(s, level);
			s = ls_ils.local_search_ils(s);
			s_fo = s.makespan();
			if(s_fo < melhor_s_fo){
				melhor_s = s.clone();
				melhor_s_fo = s_fo;
				level = 1;
			}else{
				if(level < this.perturbation.getQuant_levels()){
					level = level + 1;
					//System.out.println("Nivel: "+level);
				}else{
					//System.out.println("Niveis esgotados sem melhora: "+level);
					level = 1;
				}
			}
		}
		this.best_results.setBest_list(melhor_s_fo);
		return melhor_s;
	}
}
