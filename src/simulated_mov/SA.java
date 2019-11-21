package simulated_mov;

import java.util.Random;

import com.upmsp.pr.EliteSet;
import com.upmsp.pr.pr;
import com.upmsp.structure.Solution;

public class SA {
	
	private Solution solucao;
	private SA_Moviments m_sa;
	private double T_INICIAL;
	private double ALF;
	private int SAMAX;
	private EliteSet es;
	private Vnd vnd;
	private pr pr;
	
	
	public SA(Solution solucao, double t_inicial, float alfa, int samax) {
		this.solucao = solucao;
		this.m_sa = new SA_Moviments();
		this.T_INICIAL = t_inicial;
		this.ALF = alfa;
		this.SAMAX = samax;
	}
	
	public SA(Solution solucao, int samax) {
		this.solucao = solucao;
		this.m_sa = new SA_Moviments();
		this.T_INICIAL = 900.0;
		this.ALF = 0.99;
		this.SAMAX = samax;
		this.es = new EliteSet();
		this.vnd = new Vnd();
		this.pr = new pr();
	}
	
	public SA(Solution solucao, int samax, EliteSet es) {
		this.solucao = solucao;
		this.m_sa = new SA_Moviments();
		this.T_INICIAL = 900.0;
		this.ALF = 0.99;
		this.SAMAX = samax;
		this.es = es;
		this.vnd = new Vnd();
		this.pr = new pr();
	}
	
	public Solution execute_sa() throws CloneNotSupportedException {
		Solution melhor_solucao;
		Solution solucao_linha;
		Solution s_aux;
		int fo_melhor = Integer.MAX_VALUE;
		int fo_solucao = Integer.MAX_VALUE;
		int fo_solucao_linha = Integer.MAX_VALUE;
		int IterT = 0;			// iterations number in temperature
		double T = T_INICIAL;	// initial temperature
		Random rnd = new Random();
		
		melhor_solucao = solucao.clone();	// best solution receive the solution was given
		
		while(T > 1){
			while(IterT < SAMAX){
				IterT += 1;
				
				fo_solucao = solucao.makespan();
				solucao_linha = solucao.clone();
				solucao_linha = this.gera_vizinho(solucao_linha);
				fo_solucao_linha = solucao_linha.makespan();
				
				//solucao_linha.print_solution();
				
				long Alfa = fo_solucao_linha - fo_solucao;
				
				if(Alfa < 0){
					solucao = solucao_linha.clone();					
					if(fo_solucao_linha < melhor_solucao.makespan()){
						melhor_solucao = solucao.clone();
						System.out.println("Melhora SA :"+melhor_solucao.makespan());
						this.es.setElite(melhor_solucao);
						
					}else {
						if(es.prob_pr() < 0 && es.getElite().size() >= 5) {
							s_aux = es.getRandomSolElite();
							
							if(solucao_linha.makespan() < s_aux.makespan()) {
								solucao_linha = pr.execute_pr(solucao_linha, s_aux, melhor_solucao.makespan());
							}
							/*
							else {
								if(solucao_linha.makespan() > s_aux.makespan()) {
									solucao_linha = pr.execute_pr(s_aux, solucao_linha, fo_melhor);
								}
							}*/

							if(solucao_linha.makespan() < melhor_solucao.makespan()) {
								System.out.println("\nMelhora no PR: "+fo_solucao_linha+"\n");
								melhor_solucao = solucao.clone();
							}
						}
					}
				}
				else{
					Double x = rnd.nextDouble();
					Double exp = Math.pow(Math.E, (-1*Alfa)/T); 
					if(x < exp){
						solucao = solucao_linha.clone();
					}
				}
			}
			//System.out.printf("\nTemperatura:\t%.4f\tMakespan:\t%d\n", T, fo_solucao_linha);
			
			T = ALF * T;
			IterT = 0;
			//this.best_results.setMakespan_list(melhor_solucao.makespan());
		}
		solucao = melhor_solucao.clone();
		//int fo_melhor = solucao.makespan();
		//this.best_results.setBest_list(fo_melhor);
		return solucao;
	}
	
	public Solution gera_vizinho(Solution s){
		
		int indice_maior = this.solucao.maior_menor().get(2);
		
		Random rnd = new Random();
		int num_movimentos = 5;
		int opcao = 1 + rnd.nextInt(num_movimentos);
		switch(opcao){
		case 1:
			return m_sa.task_move(s);
		case 2:
			return m_sa.shift(s);
		case 3:
			return m_sa.Switch(s);
		case 4:
			if(solucao.getMaq(this.solucao.maior_menor().get(0)).getSizeMaq() > 3)
				return m_sa.two_swap(s);
			else
				return m_sa.task_move(s);
		case 5:
			if(solucao.getMaq(indice_maior).getSizeMaq() > 2)
				return m_sa.two_realloc(s);
			else
				return m_sa.Switch(s);
		default:
			System.out.println("Problema com o valor aleatório.");
		}
		return null;
		//System.out.println("Movimento escolhido:\t"+opcao+"\n");
	}

}
