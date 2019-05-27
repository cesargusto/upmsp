package com.upmsp.structure;

import java.util.ArrayList;
import java.util.Collections;

public class Moviments2 {

	public static void grava_troca_intra(ArrayList<Integer>mov, Solution s){
		s.getMaq(mov.get(0)).trocaJob(mov.get(1), mov.get(2));
	}

	public static int troca_intra(Solution s, boolean atalho){
		//o movimento de troca_intra será feito em todas as  maquinas que porventura
		//venham a se tornar makespan.
		int makespan_menor = s.makespan();
		int makespan_corrente = 0;
		int M = s.indice_makespan();
		int nM = -1;
		int size_maq = -1;
		int makespan_atual = makespan_menor;
		

		ArrayList<Integer>better_mov = new ArrayList<Integer>(3);

		ArrayList<Integer>tempos = new ArrayList<Integer>();
		for (int i = 0; i < s.Tempos().size(); i++) {
			tempos.add(s.Tempos().get(i));
		}
		zero:
		do {
			//System.out.println("Indice Makespan: "+M);
			makespan_menor = tempos.get(M);
			size_maq = s.getMaq(M).getSizeMaq();

			int p_antes_i = 0, p_t_antes_i = 0;
			int p_depois_i = 0, p_t_depois_i = 0;
			int p_antes_j = 0, p_t_antes_j = 0;
			int p_depois_j = 0, p_t_depois_j = 0;

			//trata maquinas com apenas um(1)job
			if(size_maq > 1){
				first:
				for(int i = 0;i < size_maq-1;i++){
					second:
					for(int j = i+1;j < size_maq;j++){
						//trata o envolvimento do primeiro elemento
						if(i==0){
							//p_antes_i = s.get_T_prep(M, i-1, i);
							p_antes_i = s.get_T_prep(M, i, i);
							//p_t_antes_i = s.get_T_prep(M, i-1, j);
							p_t_antes_i = s.get_T_prep(M, j, j);
						}else{
							p_antes_i = s.get_T_prep(M, i-1, i);
							p_t_antes_i = s.get_T_prep(M, i-1, j);
						}
						//trata o envolvimento do último elemento
						if(j+1==size_maq){
							p_depois_j = 0;
							p_t_depois_j = 0;
						}else{
							p_depois_j = s.get_T_prep(M, j, j+1);
							p_t_depois_j = s.get_T_prep(M, i, j+1);
						}
						if(j-i==1){
							p_depois_i = 0;
							p_t_antes_j = 0;
							p_t_depois_i = s.get_T_prep(M, j, i); 
							p_antes_j = s.get_T_prep(M, j-1, j);
						}else{
							p_depois_i = s.get_T_prep(M, i, i+1);
							p_t_depois_i = s.get_T_prep(M, j, i+1);
							p_antes_j = s.get_T_prep(M, j-1, j);
							p_t_antes_j = s.get_T_prep(M, j-1, i);
						}

						makespan_corrente = makespan_atual +
								(-1)* p_antes_i + p_t_antes_i +
								(-1)* p_depois_i + p_t_depois_i +
								(-1)* p_antes_j + p_t_antes_j +
								(-1)* p_depois_j + p_t_depois_j;

						if(makespan_corrente < makespan_menor){
							makespan_menor = makespan_corrente;
							makespan_atual = makespan_menor;
							better_mov.clear();
							better_mov.add(M);
							better_mov.add(i);
							better_mov.add(j);
							tempos.set(M, makespan_menor);
							makespan_corrente = Collections.max(tempos);
							if(makespan_menor < makespan_corrente){
								makespan_menor = makespan_corrente;
								makespan_atual = makespan_menor;
								tempos.set(M, makespan_menor);
								grava_troca_intra(better_mov, s);
								better_mov.clear();
							}
							nM = tempos.indexOf(makespan_corrente);
							if(atalho){
								if(nM!=M){
									if(!better_mov.isEmpty()){
										grava_troca_intra(better_mov, s);
										better_mov.clear();
									}
								break zero;
								//testar se é melhor reduzir a máquina ao máximo ou apenas até 
								//encontrar algo menor que o makespan atual.
								}
							}
							
							//System.out.println("Redução -> "+makespan_menor);
						}
					}
				}
			}
			nM = tempos.indexOf(Collections.max(tempos));
			int aux = M;
			M = nM;
			nM = aux;

			if(!better_mov.isEmpty()){
				grava_troca_intra(better_mov, s);
				better_mov.clear();
			}

		}while (nM!=M);
		
		//return makespan_melhor;
		//System.out.println(s.Tempos());
		//System.out.println(tempos);
		return Collections.max(tempos);
	}	
}
/*
makespan_corrente = makespan_atual + 
(-1* s.get_T_prep(M, i-1, i)) + s.get_T_prep(M, i-1, j)+
(-1* s.get_T_prep(M, i, i+1)) + s.get_T_prep(M, j, i+1)+
(-1* s.get_T_prep(M, j-1, j)) + s.get_T_prep(M, j-1, i)+
(-1* s.get_T_prep(M, j, j+1)) + s.get_T_prep(M, i, j+1);
 */