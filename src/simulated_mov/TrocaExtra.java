/**
 * Classe criada em 2 de Jun, objetivo é realizar o movimento de troca intra usando
 * o método de simulação de movimento. esta classe é filha da classe movimento
 * 
 * @author cesar
 * 
 * **/

package simulated_mov;

import java.util.ArrayList;
import java.util.Collections;

import com.upmsp.structure.Solution;

public class TrocaExtra extends Moviment{

	public TrocaExtra(Solution s) {
		super(s);
	}

	/**
	 * 
	 * @param s      solução do tipo Solution
	 * @param atalho tipo boolean, liga ou desliga a exploração completa da maquina
	 * @return um inteiro que será um valor de makespan
	 * 
	 */
	
	/*
	 * alocação e inicialização dos índices que serão manipulados na movimentação
	 **/

	int p_antes_i = 0; // tempo de preparação do job de índice i(i-1, i)
	int p_t_antes_i = 0; // tempo de preparação depois da troca (i-1, j)

	int p_depois_i = 0; // tempo de preparação do job a frente do i (i, i+1)
	int p_t_depois_i = 0; // tempo de preparação do job a frente do i depois da troca (j, i+1)

	int p_antes_j = 0;
	int p_t_antes_j = 0;

	int p_depois_j = 0;
	int p_t_depois_j = 0;
	
	@Override
	public ArrayList<Integer> execute_mov(Boolean first) {

		int makespan_corrente = 0;

		ArrayList<Integer> tempos = this.duplicate_times(s.Tempos());

		int makespan_star = s.makespan();
		int sol_size = s.getSizeSol();
		int M = tempos.indexOf(makespan_star);
		
		int machine_mkpan_size = s.getMaq(M).getSizeMaq();
		
		first:
		for(int i = 0;i < machine_mkpan_size;i++){
			for(int w = 0;w < sol_size;w++){
				if(w != M){
					for(int j = 0;j < s.getMaq(w).getSizeMaq();j++){
						// trata o envolvimento do primeiro elemento
						if (i == 0) {
							p_antes_i = 0; 
							p_t_antes_i = 0; 
						} else {
							p_antes_i = s.get_T_prep(M, i - 1, i);
							p_t_antes_i = s.get_T_prep(M, i - 1, w, j);
						}
						
						if (j == 0) {
							p_antes_j = 0; 
							p_t_antes_j = 0; 
						} else {
							p_antes_j = s.get_T_prep(w, j - 1, j);
							p_t_antes_j = s.get_T_prep(w, j-1, M, i, w);
						}

						// trata o envolvimento do último elemento
						if (j + 1 == s.getMaq(w).getSizeMaq()) {
							p_depois_j = 0;
							p_t_depois_j = 0;
							p_t_depois_i = 0;
						} else {
							p_depois_j = s.get_T_prep(w, j, j + 1);
							p_t_depois_j = s.get_T_prep(M, i, w, j + 1, w);
						}
						
						if(i + 1 == machine_mkpan_size) {
							p_t_depois_j = 0;
							p_depois_i = 0;
						}else {
							p_t_depois_i = s.get_T_prep(w, j, M, i + 1, M);
							p_depois_i = s.get_T_prep(M, i, i + 1);
						}

						int p = s.getMaq(M).getJob(i);
						int e_antes_i = s.getArquivo().getT_exec(M, p);
						
						int q = s.getMaq(w).getJob(j);
						int e_depois_i = s.getArquivo().getT_exec(M, q);
						
						p = s.getMaq(w).getJob(j);
						int e_antes_j = s.getArquivo().getT_exec(w, p);
						
						q = s.getMaq(M).getJob(i);
						int e_depois_j = s.getArquivo().getT_exec(w, q);
						
						makespan_corrente = tempos.get(M) + 
								(-1)* p_antes_i + (-1)* p_depois_i + (-1)* e_antes_i +
								p_t_antes_i + p_t_depois_i + e_depois_i;
						int maq_corrente = tempos.get(w) + 
								(-1)* p_antes_j + (-1)* p_depois_j + (-1)* e_antes_j +
								p_t_antes_j + p_t_depois_j + e_depois_j;
								
						int t1 = tempos.get(M);
						int t2 = tempos.get(w);
						
						tempos.set(M, makespan_corrente);
						tempos.set(w, maq_corrente);
						
						makespan_corrente = Collections.max(tempos); 
						
						if (makespan_corrente < makespan_star) {
							makespan_star = makespan_corrente;
							this.better_mov_final(M, i, w, j, makespan_star, 3);
							if(first)break first;
						}
						
						tempos.set(M, t1);
						tempos.set(w, t2);
					}
				}
			}
		}
		/*
		if (!better_mov_vazio()) {
			grava_troca_extra();
			clear_better_mov();
			System.out.println("Melhor movimento gravado na solução \n");
		}
		*/
		return this.getBetter_mov();
	}

}

