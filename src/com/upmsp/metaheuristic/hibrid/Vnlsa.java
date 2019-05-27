package com.upmsp.metaheuristic.hibrid;

import java.util.Random;

import com.upmsp.experiment.BestResults;
import com.upmsp.localsearch.LocalSearch;
import com.upmsp.localsearch.Moviments;
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
	private Moviments mvs;
	private LocalSearchILS lsi;
	private Moviments pertubation;
	private Pertubations pertubations;
	private SA sa;
	private MovimentosSA m_sa;
	private final int quant_moviments = 5;
	private int num_it;
	
	public Vnlsa(Solution s, int num_it, BestResults best_results) throws CloneNotSupportedException{
		this.num_it = num_it;
		this.solution = s;
		this.best_results = best_results;
		this.pertubation = new Moviments();
		this.lsi = new LocalSearchILS();
		this.best_solution = s.clone();
		this.mvs = new Moviments();
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
			System.out.println("VNS ...."+mspan_vns);
			if(mspan_vns < mspan_bst){
				this.best_solution = sol_aux.clone();
				this.solution = sol_aux.clone();
				System.out.println("Melhora VNS ... "+mspan_vns+"\tVizinhança: "+v);
				v = 1;
			}else{
				this.sa = new SA(this.solution, 600, best_results);
				sol_aux = this.sa.execute_sa();
				mspan_vns = sol_aux.makespan(); 
				System.out.println("SA..."+mspan_vns);
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
							//REDEFINIR AS ESTRATEGIAS DE PERTUBAÇÃO
							sol_aux = this.pertubation.perturbation_hard(solution, level);
							System.out.println("Makespan da pertubação: "+sol_aux.makespan());
							sol_aux = this.lsi.local_search_ils(sol_aux);
							mspan_vns = sol_aux.makespan();
							mspan_bst = this.best_solution.makespan();
							System.out.println("Pertubation hard\t"+mspan_vns+"\tLevel: "+level);
							if(mspan_vns < mspan_bst){
								this.best_solution = sol_aux.clone();
								this.solution = sol_aux.clone();
								System.out.println("Melhora P ... "+mspan_vns+"\tLevel: "+level);
								level = 1;
							}
							else{
								if(level < 4)
									level = level + 1;
								else{
									//this.solution = sol_aux.clone();
									level = 1;
									chave = false;
								}
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
			return mvs.remove_job_Maq_mspan(s);
		case 2:
			return mvs.swap_job_ExtraMaq(s);
		case 3:
			return mvs.troca_intra_Maq(s);
		case 4:
			return mvs.insert_intra_Maq(s);
		case 5:
			return mvs.change_Maq(s);
		default:
			return null;
		}
	}
	//build solution
	//vns
	//local-search or simulated annealing
	//pertubation

}
