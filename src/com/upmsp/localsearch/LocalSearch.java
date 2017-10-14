package com.upmsp.localsearch;

import java.util.ArrayList;
import java.util.Random;

import com.upmsp.structure.Solution;

public class LocalSearch {

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
		
		return best_sol_mov;
	}

	public Solution insert_intra_Maq(Solution solucao) throws CloneNotSupportedException {
		
		Solution best_sol_mov = solucao.clone();
		
		int tamanho_sol = solucao.getSizeSol();

		for(int i = 0;i < tamanho_sol;i++){
			int tamanho_maq = solucao.getMaq(i).getSizeMaq();
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
		return best_sol_mov;
	}
	
	public Solution remove_job_Maq_mspan(Solution solucao) throws CloneNotSupportedException{
		//O JOB PODE SER INSERIDO EM UMA POSIÇÃO ALEATÓRIA INVEZ DO FINAL

		Solution best_sol_mov = solucao.clone();
		
		int sol_size = solucao.getSizeSol();
		
		//ArrayList<Integer> maior_menor = new ArrayList<>(this.solucao.maior_menor());
		
		int maq_mspan = solucao.maior_menor().get(2);
		
		int machine_mkpan_size = solucao.getMaq(maq_mspan).getSizeMaq();
		
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
	
	public Solution two_realloc(Solution solucao){//remove e insere duas vezes
		Random rnd = new Random();
		
		int indice_maior = solucao.maior_menor().get(2);
		
		int pm1 = -1;
		int pm2 = -1;
		
		while(pm1 == pm2){
			pm1 = rnd.nextInt(solucao.getMaq(indice_maior).getSizeMaq());
			pm2 = rnd.nextInt(solucao.getMaq(indice_maior).getSizeMaq());
		}
		
		solucao.getMaq(indice_maior).trocaJob(pm1, pm2);
		
		pm1 = -1;
		pm2 = -1;
		
		while(pm1 == pm2){
			pm1 = rnd.nextInt(solucao.getMaq(indice_maior).getSizeMaq());
			pm2 = rnd.nextInt(solucao.getMaq(indice_maior).getSizeMaq());
		}
		
		solucao.getMaq(indice_maior).trocaJob(pm1, pm2);
		
		return solucao;
	}
	
	public Solution two_swap(Solution solucao) {//troca de um job de a com b e de b com a
		Random rnd = new Random();
		
		int indice_maq1 = -1;
		int indice_maq2 = -1;
		
		//test if not same machines
		while(indice_maq1 == indice_maq2) {
			indice_maq1 = rnd.nextInt(solucao.getSizeSol());
			indice_maq2 = rnd.nextInt(solucao.getSizeSol());
		}
		
		//get the size of machine chosen
		int tamanho_maq1 = solucao.getMaq(indice_maq1).getSizeMaq();
		int p1_m1 = rnd.nextInt(tamanho_maq1);//get a random position in this machine
		//get the job of random position
		int job_m1 = solucao.getMaq(indice_maq1).getJob(p1_m1); 
		//get the size of the second machine
		int tamanho_maq2 = solucao.getMaq(indice_maq2).getSizeMaq();
		int p1_m2 = rnd.nextInt(tamanho_maq2);
		//set the job of first machine in random position of the second machine
		solucao.getMaq(indice_maq2).setJobMaq(p1_m2, job_m1);
		solucao.getMaq(indice_maq1).removeJobToMaq(p1_m1);	//remove this job of first machine	
		
		
		tamanho_maq2 = solucao.getMaq(indice_maq2).getSizeMaq();
		p1_m2 = rnd.nextInt(tamanho_maq2);
		
		int job_m2 = solucao.getMaq(indice_maq2).getJob(p1_m2); 
		//test if it the same job
		while(job_m1 == job_m2) {
			p1_m2 = rnd.nextInt(tamanho_maq2);
			job_m2 = solucao.getMaq(indice_maq2).getJob(p1_m2);
		}

		tamanho_maq1 = solucao.getMaq(indice_maq1).getSizeMaq();
		int p2_m1 = rnd.nextInt(tamanho_maq1);
		
		solucao.getMaq(indice_maq1).setJobMaq(p2_m1, job_m2);
		solucao.getMaq(indice_maq2).removeJobToMaq(p1_m2);
		
		return solucao;
		
	}
	
}
