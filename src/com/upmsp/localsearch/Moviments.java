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
}
