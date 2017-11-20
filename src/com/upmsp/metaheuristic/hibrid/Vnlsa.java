package com.upmsp.metaheuristic.hibrid;

import java.util.Random;

import com.upmsp.experiment.BestResults;
import com.upmsp.localsearch.LocalSearch;
import com.upmsp.metaheuristic.SA.MovimentosSA;
import com.upmsp.metaheuristic.SA.SA;
import com.upmsp.metaheuristic.ils.LocalSearchILS;
import com.upmsp.metaheuristic.ils.Pertubations;
import com.upmsp.metaheuristic.vns.Vns;
import com.upmsp.structure.Solution;

public class Vnlsa {
	
	private Solution best_solution;
	private Solution solution;
	private BestResults best_results;
	private LocalSearch ls;
	private LocalSearchILS lsi;
	private Pertubations pertubations;
	private SA sa;
	private MovimentosSA m_sa;
	private final int quant_moviments = 5;
	private int num_it;
	
	public Vnlsa(Solution s, int num_it, BestResults best_results) throws CloneNotSupportedException{
		this.num_it = num_it;
		this.solution = s;
		this.best_results = best_results;
		this.pertubations = new Pertubations();
		this.lsi = new LocalSearchILS();
		this.best_solution = s.clone();
		this.ls = new LocalSearch();
		this.m_sa = new MovimentosSA();
	}
	public Solution execute_vnlsa() throws CloneNotSupportedException{
		int v = 1;
		int level = 1;
		Solution sol_aux;
		while(num_it > 0){
			sol_aux = vizinhanca(v, this.solution);
			
			int mspan_vns = sol_aux.makespan();
			int mspan_bst = this.best_solution.makespan();
			
			if(mspan_vns < mspan_bst){
				this.best_solution = sol_aux.clone();
				this.solution = sol_aux.clone();
				System.out.println("Melhora VNS ... "+mspan_vns+"\tVizinhança: "+v);
				v = 1;
			}else{
				this.sa = new SA(this.solution, 300, best_results);
				sol_aux = this.sa.execute_sa();
				mspan_vns = sol_aux.makespan(); 
				if(mspan_vns < mspan_bst){
					this.best_solution = sol_aux.clone();
					this.solution = sol_aux.clone();
					System.out.println("Melhora SA ... "+mspan_vns);
					v = 1;
				}else{
					if(v < quant_moviments){
						v = v + 1;
					}	
					else{
						boolean chave = true;
						while(chave){
							sol_aux = this.pertubations.execute(solution, level);
							sol_aux = this.lsi.local_search_ils(sol_aux);
							mspan_vns = sol_aux.makespan();
							mspan_bst = this.best_solution.makespan();
							if(mspan_vns < mspan_bst){
								this.best_solution = sol_aux.clone();
								this.solution = sol_aux.clone();
								System.out.println("Melhora P ... "+mspan_vns+"\tLevel: "+level);
								level = 1;
							}
							else{
								if(level < pertubations.getQuant_levels())
									level = level + 1;
								else
									chave = false;
							}
						}
						v = 1;
					}
				}
				
			}
			num_it --;
		}
		this.best_results.setBest_list(best_solution.makespan());
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
		}
	}
	//build solution
	//vns
	//local-search or simulated annealing
	//pertubation

}
