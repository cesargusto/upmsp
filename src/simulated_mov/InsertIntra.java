package simulated_mov;

import java.util.ArrayList;
import java.util.Collections;

import com.upmsp.structure.Solution;

public class InsertIntra extends Moviment{

	public InsertIntra(Solution s) {
		super(s);
	}

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


		int makespan_menor = s.makespan();
		int makespan_corrente = 0;
		int M = s.indice_makespan();
		int nM = -1;
		int size_maq = -1;
		int makespan_atual = makespan_menor;

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
						p_antes_i = 0;
						p_t_antes_i = 0;
					} else {
						p_antes_i = s.get_T_prep(M, i - 1, i);
						p_t_antes_i = s.get_T_prep(M, i-1, i + 1);
					}

					// trata o envolvimento do último elemento
					if (j == size_maq -1) {
						p_depois_j = 0;
						p_t_depois_i =  0;
					} else {
						p_depois_j = s.get_T_prep(M, j, j + 1);
						p_t_depois_i = s.get_T_prep(M, i, j + 1);
					}

					// trata a consecutividade
					if (j - i == 1) {
						p_antes_i = p_antes_i;
						p_depois_i = s.get_T_prep(M, i, j);//nulo
						p_antes_j = 0;//remove
						p_depois_j = p_depois_j;
						
						p_t_antes_i = p_t_antes_i;
						p_t_depois_i = p_t_depois_i;
						p_t_antes_j = 0;
						p_t_depois_j = s.get_T_prep(M, j, i);
					}
					// trata a situação comum resultante
					else {
						p_antes_i = p_antes_i;
						p_depois_i = s.get_T_prep(M, i, i + 1);//remove							
						p_antes_j = 0;//s.get_T_prep(M, j-1, j);
						p_depois_j = p_depois_j;
						
						p_t_antes_i = p_t_antes_i;
						p_t_depois_i = p_t_depois_i;
						p_t_antes_j = 0;
						p_t_depois_j = s.get_T_prep(M, j, i);
					}

					/*
					 * Após atribuições das variáveis acontence o calculo do makespan que opera o
					 * makespan atual com o resultado de adição e subtração dos tempos de preparação
					 * envolvidos no movimento de troca
					 */

					makespan_corrente = makespan_atual + 
							(-1) * p_antes_i + p_t_antes_i + 
							(-1) * p_depois_i + p_t_depois_i + 
							(-1) * p_antes_j + p_t_antes_j + 
							(-1) * p_depois_j + p_t_depois_j;

					// Verifica se o movimento simulado trará ou não melhora

					if (makespan_corrente < makespan_menor) {
	
						makespan_menor = makespan_corrente;
			
						this.better_mov_final(M, i, j, -1, makespan_menor, 0);
						tempos.set(M, makespan_menor);
						makespan_corrente = Collections.max(tempos); // É necessário aqui? pra que?
						nM = tempos.indexOf(makespan_corrente);
						if(first)break first;
					}
				} 
			}				
		} 
		else
			System.out.println("Maquina menor que 1");

		/*
		if (!better_mov_vazio()) {
			grava_insert_intra();
			clear_better_mov();
			System.out.println("Melhor movimento gravado na solução \n");
		}*/

		return this.getBetter_mov();
	}
}
