package simulated_mov;

import com.upmsp.metaheuristic.ils.Pertubations;
import com.upmsp.pr.EliteSet;
import com.upmsp.pr.pr;
import com.upmsp.structure.Solution;
import com.upmsp.localsearch.Moviments;

public class ILS {
	
	Solution s;
	Solution s_aux;
	Vnd vnd;
	Pertubations perturb;
	Moviments p_hard;
	EliteSet es;
	pr pr;
	int iter_max;
	boolean p_r;
	boolean first_imp;
	
	public ILS(Solution solucao, int iter_max, boolean pr, boolean first_imp){
		this.s = solucao;
		this.vnd = new Vnd();
		this.perturb = new Pertubations();
		this.es = new EliteSet();
		this.pr = new pr();
		this.iter_max = iter_max;
		this.p_r = pr;
		this.p_hard = new Moviments(); 
		this.first_imp = first_imp;
	}

	public Solution execute_ils() throws CloneNotSupportedException{
		
		boolean rvnd = false;
		int iter = 0;
		int level = 1;
		int s_fo = s.makespan();
		int melhor_s_fo = s_fo;
		Solution melhor_s = null;
		Solution s_linha = this.vnd.execute_vnd(s, first_imp, rvnd);
		Solution s_aux = null;
		
		if(s_linha.makespan() < melhor_s_fo){
			melhor_s = s_linha.clone();
			melhor_s_fo = melhor_s.makespan();
			es.setElite(melhor_s);
		}
	
		while(iter < iter_max){
			iter = iter + 1;
			s_linha = this.perturb.execute(s_linha, level);
			//s = this.mov.perturbation_hard(s, level);
			s_linha = this.vnd.execute_vnd(s_linha, first_imp, rvnd);
			
			this.es.setElite(s_linha);
			if(p_r) {
				if(es.prob_pr() <= 0.1 && es.getElite().size() >= 5) {
					//System.out.println("  >>> Aplicando PR...");
					s_aux = es.getRandomSolElite();
					
					if(s_linha.makespan() < s_aux.makespan()) {
						s_linha = pr.execute_pr(s_linha, s_aux, melhor_s_fo);
					}else {
						if(s_linha.makespan() > s_aux.makespan()) {
							s_linha = pr.execute_pr(s_aux, s_linha, melhor_s_fo);
						}
					}
				}
			}
			
			s_fo = s_linha.makespan();
			
			if(s_fo < melhor_s_fo){
				melhor_s = s_linha.clone();
				melhor_s_fo = s_fo;
				this.es.setElite(melhor_s);
				System.out.println("Melhora ILS : "+melhor_s_fo);
				level = 1;
			}
			else{
				if(level < this.perturb.getQuant_levels())
					level = level + 1;
				else {
					//System.out.println("\nPerturbação hard ativada!\n");
					s_linha = this.p_hard.perturbation_hard(s_linha, level);
					s_linha = this.vnd.execute_vnd(s_linha, first_imp, rvnd);
					s_fo = s_linha.makespan();
					if(s_fo < melhor_s_fo) {
						melhor_s = s_linha.clone();
						melhor_s_fo = s_fo;
						this.es.setElite(melhor_s);
						System.out.println("Melhora PERTURB HARD : "+melhor_s_fo);
						level = 1;
					}
					level = 1;
				}
					
			}
		}
		return melhor_s;
	}
}
