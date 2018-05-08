/***************************************************************************************
 * 
 * Está classe tem a função de gerar movimentos mais bruscos na solução.
 * Basicamente são retirados de uma só vez da maquina makespan 2, 3 e 4 tarefas
 * de posições aleatórias e distribuídas nas demais máquinas escolhidas aleatóriamente
 * e em posições também aleatórias.
 * 
 * Data de criação: 23 de out de 2017
 * @author cesar
 * 
 ***************************************************************************************/
package com.upmsp.localsearch;

import java.util.Random;

import com.upmsp.structure.Solution;

public class Moviments {
	
	private final int n_vizinhanca = 5;
	
	public boolean avalia_solucao(Solution s, Solution s_melhor) throws CloneNotSupportedException{
		
		int fo_1 = s.makespan();
		int fo_2 = s_melhor.makespan();
		
		if(fo_1 < fo_2){
			return true;
		}else
			return false;
	}
	
	public Solution troca_intra_Maq(Solution solucao) throws CloneNotSupportedException{

		Solution best_sol_mov = solucao.clone();
		
		int tamanho_sol = solucao.getSizeSol();

		for(int i = 0;i < tamanho_sol;i++){
			int tamanho_maq = solucao.getMaq(i).getSizeMaq();
			if(tamanho_maq > 1) {
				for(int j = 0;j < tamanho_maq -1;j++){
					for(int w = j+1;w < tamanho_maq;w++){
	
						int a = solucao.getMaq(i).getJob(j);
	
						solucao.getMaq(i).setJobToMaq(j, solucao.getMaq(i).getJob(w));
						solucao.getMaq(i).setJobToMaq(w, a);
	
						if(avalia_solucao(solucao, best_sol_mov)){
							best_sol_mov = solucao.clone();
						}
						//solucao.print_solution();//avaliação da solution
	
						a = solucao.getMaq(i).getJob(j);
	
						solucao.getMaq(i).setJobToMaq(j, solucao.getMaq(i).getJob(w));
						solucao.getMaq(i).setJobToMaq(w, a);
					}
				}
			}
		}
		
		return best_sol_mov;
	}

	public Solution insert_intra_Maq(Solution solucao) throws CloneNotSupportedException {
		
		Solution best_sol_mov = solucao.clone();
		
		int tamanho_sol = solucao.getSizeSol();

		for(int i = 0;i < tamanho_sol;i++){
			int tamanho_maq = solucao.getMaq(i).getSizeMaq();
			if(tamanho_maq > 1) {
				for(int j = 0;j < tamanho_maq -1;j++){
					int a = solucao.getMaq(i).getJob(j);
					solucao.getMaq(i).removeJobToMaq(j);
					for(int w = j+1;w < tamanho_maq;w++){
						solucao.getMaq(i).insertJobToMaq(w, a);
	
						if(avalia_solucao(solucao, best_sol_mov)){
							best_sol_mov = solucao.clone();
						}
						//solucao.print_solution();//avaliação da solution
						solucao.getMaq(i).removeJobToMaq(w);
					}
					solucao.getMaq(i).insertJobToMaq(j, a);
				}
			}
		}
		return best_sol_mov;
	}
	
	public Solution remove_job_Maq_mspan(Solution solucao) throws CloneNotSupportedException{
		//O JOB PODE SER INSERIDO EM UMA POSIÇÃO ALEATÓRIA INVEZ DO FINAL

		Solution best_sol_mov = solucao.clone();
		
		int sol_size = solucao.getSizeSol();
		int maq_mspan = solucao.maior_menor().get(2);
		int machine_mkpan_size = solucao.getMaq(maq_mspan).getSizeMaq();
		
		if(machine_mkpan_size > 1) {
			for(int i = 0;i < machine_mkpan_size;i++){
				int job_pivot = solucao.getMaq(maq_mspan).getJob(i);
				solucao.getMaq(maq_mspan).removeJobToMaq(i);
				for(int j = 0;j < sol_size;j++){
					if(j != maq_mspan){
						solucao.getMaq(j).addJobToMaq(job_pivot);
						
						if(avalia_solucao(solucao, best_sol_mov)){
							best_sol_mov = solucao.clone();
						}
						//solucao.print_solution();//avaliação da solution
						solucao.getMaq(j).removeLastJob();
					}
				}
				solucao.getMaq(maq_mspan).insertJobToMaq(i, job_pivot);
			}
		}
		return best_sol_mov;
	}
	
	public Solution swap_job_ExtraMaq(Solution solucao) throws CloneNotSupportedException{
		//SERÁ FEITO NA MAQUINA MAKESPAN COM AS DEMAIS

		Solution best_sol_mov = solucao.clone();
		
		int sol_size = solucao.getSizeSol();
		int maq_mspan = solucao.maior_menor().get(2);
		int machine_mkpan_size = solucao.getMaq(maq_mspan).getSizeMaq();
		
		for(int i = 0;i < machine_mkpan_size;i++){
			int job_pivot = solucao.getMaq(maq_mspan).getJob(i);
			for(int j = 0;j < sol_size;j++){
				if(j != maq_mspan){
					for(int w = 0;w < solucao.getMaq(j).getSizeMaq();w++){
						int job_pivot_2 = solucao.getMaq(j).getJob(w);
						solucao.getMaq(maq_mspan).setJobToMaq(i, job_pivot_2);
						solucao.getMaq(j).setJobToMaq(w, job_pivot);
						
						if(avalia_solucao(solucao, best_sol_mov)){
							best_sol_mov = solucao.clone();
						}
						//solucao.print_solution();//avaliação da solution
						solucao.getMaq(maq_mspan).setJobToMaq(i, job_pivot);
						solucao.getMaq(j).setJobToMaq(w, job_pivot_2);						
					}
				}
			}
		}
		return best_sol_mov;
	}
	
	public Solution change_Maq(Solution solucao) throws CloneNotSupportedException{
		//Troca todo o sequenciamento de máquinas
		Solution best_sol_mov = solucao.clone();
		
		int sol_size = solucao.getSizeSol();
		int maq_mspan = solucao.maior_menor().get(2);
		
		for(int i = 0;i < sol_size;i++){
			if(i != maq_mspan){
				solucao.swap_Machine(maq_mspan, i);
				if(avalia_solucao(solucao, best_sol_mov)){
					best_sol_mov = solucao.clone();
				}
				//solucao.print_solution();//avaliação da solution
				solucao.swap_Machine(maq_mspan, i);
			}
		}	
		return best_sol_mov;
	}
	
	public Solution perturbation_hard(Solution s, int level){
		
		int tamanho_solucao = s.getSizeSol();
		int indice_maior = s.maior_menor().get(2);
		int tamanho_maq_maior = s.getMaq(indice_maior).getSizeMaq();
		Random rnd = new Random();
		
		if(tamanho_maq_maior > level + 1){
			
			int posicao_1 = -1;
			int posicao_2 = -1;
			int posicao_3 = -1;
			
			int job_aux = -1;
			
			for(int i = 0;i < level + 1;i++){
				posicao_1 = rnd.nextInt(tamanho_maq_maior);
				
				posicao_2 = indice_maior;
				
				while(posicao_2 == indice_maior){
					posicao_2 = rnd.nextInt(tamanho_solucao);
				}
				posicao_3 = rnd.nextInt(s.getMaq(posicao_2).getSizeMaq());
				
				job_aux = s.getMaq(indice_maior).getJob(posicao_1);
				s.getMaq(indice_maior).removeJobToMaq(posicao_1);
				s.getMaq(posicao_2).insertJobToMaq(posicao_3, job_aux);
				
				tamanho_maq_maior = tamanho_maq_maior - 1;
				
			}
		}
		
		return s;
	}

	public int getN_vizinhanca() {
		return n_vizinhanca;
	}
	
	
}
