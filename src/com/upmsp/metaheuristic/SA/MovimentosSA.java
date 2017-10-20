/****************************************************************
 * Esta classe foi criada em setembro de 2017
 * Recebeu modificações de correção no dia 18 de out 2017
 * 
 * Esta classe é responsável por movimentos da metaheuristica SA.
 * Estes movimentos são aleatórios e executam apenas uma vez sobre
 * a solução e são escolhidos de maneira aleatória.
 * 
 * @author cesar
 * 
 *****************************************************************/

package com.upmsp.metaheuristic.SA;

import java.util.Random;

import com.upmsp.structure.Solution;

public class MovimentosSA implements Cloneable {

	public Solution task_move(Solution solucao){
		Random rnd = new Random();

		int indice_maior = solucao.maior_menor().get(2);
		
		int indice_menor = indice_maior;
		
		
		while(indice_menor == indice_maior)
			indice_menor = rnd.nextInt(solucao.getSizeSol()); 
		
		int pm1 = rnd.nextInt(solucao.getMaq(indice_maior).getSizeMaq());
		int pm2 = rnd.nextInt(solucao.getMaq(indice_menor).getSizeMaq());
		
		int job = solucao.getMaq(indice_maior).getJob(pm1);
		solucao.getMaq(indice_menor).setJobMaq(pm2, job);
		solucao.getMaq(indice_maior).removeJobToMaq(pm1);
		
		//System.out.println("task_move");
		//solucao.print_solution();
		
		return solucao;
	}
	
	public Solution shift(Solution solucao){//inserção
		Random rnd = new Random();

		int indice_maior = solucao.maior_menor().get(2);
		
		int pm1 = -1;
		int pm2 = -1;
		
		while(pm1 == pm2){
			pm1 = rnd.nextInt(solucao.getMaq(indice_maior).getSizeMaq());
			pm2 = rnd.nextInt(solucao.getMaq(indice_maior).getSizeMaq() - 1);
		}
		
		int job = solucao.getMaq(indice_maior).getJob(pm1);
		solucao.getMaq(indice_maior).removeJobToMaq(pm1);
		solucao.getMaq(indice_maior).setJobMaq(pm2, job);
		
		//System.out.println("shift");
		//solucao.print_solution();
		
		return solucao;
		
	}
	
	public Solution Switch(Solution solucao) {//troca
		Random rnd = new Random();

		int indice_maior = solucao.maior_menor().get(2);
		
		int pm1 = -1;
		int pm2 = -1;
		
		while(pm1 == pm2){
			pm1 = rnd.nextInt(solucao.getMaq(indice_maior).getSizeMaq());
			pm2 = rnd.nextInt(solucao.getMaq(indice_maior).getSizeMaq());
		}
		solucao.getMaq(indice_maior).trocaJob(pm1, pm2);
		
		//System.out.println("Switch");
		//solucao.print_solution();
		
		return solucao;
	}
	
	public Solution Swap(Solution solucao) {//troca de um job de a com b e de b com a
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
		
		//System.out.println("swap");
		//solucao.print_solution();
		
		return solucao;
	}
	
	public Solution two_realloc(Solution solucao){//remove e insere duas vezes
		Random rnd = new Random();

		int indice_maior = solucao.maior_menor().get(2);
		
		int pm1 = -1;
		int pm2 = -1;
		
		int maq_size = solucao.getMaq(indice_maior).getSizeMaq();
		
		while(pm1 == pm2){
			pm1 = rnd.nextInt(maq_size);
			pm2 = rnd.nextInt(maq_size);
		}
		
		solucao.getMaq(indice_maior).trocaJob(pm1, pm2);
		
		pm1 = -1;
		pm2 = -1;
		
		while(pm1 == pm2){
			pm1 = rnd.nextInt(maq_size);
			pm2 = rnd.nextInt(maq_size);
		}
		
		solucao.getMaq(indice_maior).trocaJob(pm1, pm2);
		
		//System.out.println("two_realloc");
		//solucao.print_solution();
		
		return solucao;
	}

}
