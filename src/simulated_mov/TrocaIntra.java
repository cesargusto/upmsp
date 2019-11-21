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

public class TrocaIntra extends Moviment{
	
	public TrocaIntra(Solution s) {
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

			/*
			 * O movimento de troca_intra será feito em todas as maquinas que porventura
			 * venham a se tornar makespan.
			 */

			// recebe o valor do makespan da solução
			int makespan_menor = s.makespan();
			// cria uma segunda variável para fazer operações
			int makespan_corrente = 0;
			// armazena sempre o indice do makaspan na lista de tempos
			int M = s.indice_makespan();
			// tamanho da maquina
			int size_maq = -1;
			// uma terceira variável para operação inicializada com o valor de makespan
			// inicial
			int makespan_atual = makespan_menor;
			// lista cópia dos tempos de cada máquina que compoe a solução
			ArrayList<Integer> tempos = this.duplicate_times(s.Tempos());

			makespan_menor = tempos.get(M);
			makespan_atual = makespan_menor;
			size_maq = s.getMaq(M).getSizeMaq();

			// trata maquinas com apenas um(1)job
			if (size_maq > 1) 
			{
				first: for (int i = 0; i < size_maq - 1; i++) 
				{
					second: for (int j = i + 1; j < size_maq; j++) 
					{
						// trata o envolvimento do primeiro elemento
						if (i == 0) {
							p_antes_i = 0; // setup do primeiro é 0
							p_t_antes_i = 0; // setup depois da troca também
						} else {
							p_antes_i = s.get_T_prep(M, i - 1, i);
							p_t_antes_i = s.get_T_prep(M, i - 1, j);
						}

						// trata o envolvimento do último elemento
						if (j + 1 == size_maq) {
							p_depois_j = 0;
							p_t_depois_j = 0;
						} else {
							p_depois_j = s.get_T_prep(M, j, j + 1);
							p_t_depois_j = s.get_T_prep(M, i, j + 1);
						}

						// trata a consecutividade
						if (j - i == 1) {
							p_depois_i = 0;
							p_t_antes_j = 0;
							p_t_depois_i = s.get_T_prep(M, j, i);
							p_antes_j = s.get_T_prep(M, j - 1, j);
						}
						// trata a situação comum resultade
						else {
							p_depois_i = s.get_T_prep(M, i, i + 1);
							p_t_depois_i = s.get_T_prep(M, j, i + 1);
							p_antes_j = s.get_T_prep(M, j - 1, j);
							p_t_antes_j = s.get_T_prep(M, j - 1, i);
						}

						makespan_corrente = makespan_atual + 
								(-1) * p_antes_i + p_t_antes_i + 
								(-1) * p_depois_i+ p_t_depois_i + 
								(-1) * p_antes_j + p_t_antes_j + 
								(-1) * p_depois_j + p_t_depois_j;

						// Verifica se o movimento simulado trará ou não melhora

						if (makespan_corrente < makespan_menor) {
							makespan_menor = makespan_corrente;
							tempos.set(M, makespan_menor);
							makespan_corrente = Collections.max(tempos);
							this.better_mov_final(M, i, j, -1, makespan_corrente, 2);
							if(first)break first;
						} 
					} 
				}
			} 
			else
				System.out.println("Maquina menor que 1");

			return this.getBetter_mov();
		}

}
