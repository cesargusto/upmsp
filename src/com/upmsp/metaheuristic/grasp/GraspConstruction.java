package com.upmsp.metaheuristic.grasp;

import java.util.ArrayList;
import java.util.Random;

import com.upmsp.structure.Solution;

public class GraspConstruction {
	
	public Solution construction_grasp(Solution s, double alfa){

		ArrayList<Integer> LC = new ArrayList<>();
		ArrayList<Integer> LCR = new ArrayList<>();
		ArrayList<Integer> solution = new ArrayList<>();
		Random rnd = new Random();
		//criar uma matriz que recebe os valores de exec = LC
		//criar outra matriz pra guardar os selecionados = LCR
		//criar uma solucao para receber os elementos escolhidos

		
		int g_min = -1;
		int g_max = -1;
		
		while(!LC.isEmpty()){
			//Collections.sort(LC);

			g_min = this.extremos(s)[0];
			g_max = this.extremos(s)[1];
			
			double valor = Double.MAX_VALUE;
			
			valor = g_min + alfa * (g_max - g_min);
			/****************************
			 * cria um repetidor pra percorrer a matriz
			 * a matriz LCR é composta pelo nome das tarefas e nao
			 * seus tempos de exec.
			 * 
			 * desses valores da LCR será retirado o elemento da nova
			 * solucao.  
			 */
			int contador = LC.size();
			for(int i = 0;i < contador;i++){
				int get = LC.get(i); 
				if( get <= valor){
					LCR.add(LC.get(i));
				}else {
					break;
				}
			}
			int index = -1;
			
			if(LCR.size() > 1)
				index = rnd.nextInt(LCR.size());
			else
				index = 0;
			
			solution.add(LCR.get(index));
			LC.remove(index);
			LCR.clear();
		}
		return s;
	}
	
	public int[] extremos(Solution s){
		int[]menor_maior = new int[2];
		int maior = -1;
		int menor = Integer.MAX_VALUE;
		int value_aux = Integer.MAX_VALUE;
		
		for(int i = 0;i < s.getArquivo().getN_maqs();i++){
			for(int j = 0;j < s.getArquivo().getN_jobs();j++){
				value_aux = s.getArquivo().getT_exec(i, j);
				if(value_aux > maior){
					maior = value_aux;
				}else if(value_aux < menor){
					menor = value_aux;
				}
			}
		}
		menor_maior[0] = menor;
		menor_maior[1] = maior;
		
		return menor_maior;
	}

}
