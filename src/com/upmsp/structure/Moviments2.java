package com.upmsp.structure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Moviments2 {

	/**
	 * Aplica a melhor movimentação encontrada:
	 * 
	 * @param mov     lista de movimento
	 * @param solução s
	 */
	
	private ArrayList<Integer> better_mov;
	private ArrayList<Integer> times;
	
	public Moviments2() {
		this.better_mov = new ArrayList<Integer>(3);
		this.times = new ArrayList<Integer>();
	}

	public void better_mov(int M, int i, int j) {
		clear_better_mov();
		better_mov.add(M);
		better_mov.add(i);
		better_mov.add(j);
	}
	public void clear_better_mov() {
		if(!better_mov.isEmpty())
			better_mov.clear();
	}
	public void update_times(int M, int value) {
		this.times.set(M, value);
	}
	
	// Execução da cópia dos tempos para a lista auxiliar [tempos]
	public ArrayList<Integer> duplicate_times(ArrayList<Integer> Tempos) {
		ArrayList<Integer> t = new ArrayList<Integer>();
	
		for (int i = 0; i < Tempos.size(); i++) {
			t.add(Tempos.get(i));
		}
		return t;
	}
	
	public void grava_troca_intra(ArrayList<Integer> mov, Solution s) {
		s.getMaq(mov.get(0)).trocaJob(mov.get(1), mov.get(2));
		this.clear_better_mov();
	}

	/**
	 * 
	 * @param s      solução do tipo Solution
	 * @param atalho tipo boolean, liga ou desliga a exploração completa da maquina
	 * @return um inteiro que será um valor de makespan
	 * 
	 */

	int p_antes_i = 0; // tempo de preparação do job de índice i(i-1, i)
	int p_t_antes_i = 0; // tempo de preparação depois da troca (i-1, j)

	int p_depois_i = 0; // tempo de preparação do job a frente do i (i, i+1)
	int p_t_depois_i = 0; // tempo de preparação do job a frente do i depois da troca (j, i+1)

	int p_antes_j = 0;
	int p_t_antes_j = 0;

	int p_depois_j = 0;
	int p_t_depois_j = 0;
	
	public int troca_intra(Solution s, boolean atalho) {

		int makespan_menor = s.makespan();
		int makespan_corrente = 0;
		int M = s.indice_makespan();
		int nM = -1;
		int size_maq = -1;

		int makespan_atual = makespan_menor;
		this.times = this.duplicate_times(s.Tempos());

		zero: do {

			makespan_menor = times.get(M);
			makespan_atual = makespan_menor;
			size_maq = s.getMaq(M).getSizeMaq();

			if (size_maq > 1) 
			{
				first: 
					for (int i = 0; i < size_maq - 1; i++) {
					second: 
						for (int j = i + 1; j < size_maq; j++){
						// trata o envolvimento do primeiro elemento
						if (i == 0) {
							p_antes_i = s.get_T_prep(M, i, i); // setup do primeiro é 0
							p_t_antes_i = s.get_T_prep(M, j, j); // setup depois da troca também
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

						makespan_corrente = makespan_atual + (-1) * p_antes_i + p_t_antes_i + (-1) * p_depois_i
								+ p_t_depois_i + (-1) * p_antes_j + p_t_antes_j + (-1) * p_depois_j + p_t_depois_j;

						
						if (makespan_corrente < makespan_menor) {
							makespan_menor = makespan_corrente;
							makespan_atual = makespan_menor;
							this.better_mov(M, i, j);
							this.update_times(M, makespan_menor);
							grava_troca_intra(better_mov, s);
							better_mov.clear();
							
							nM = times.indexOf(Collections.max(times));
							
							if(nM != M) {
								System.out.println("Mudou a maquina makespan para -> "+ nM +"!\n");
								nM = M;
								break first;
							}
							else {
								System.out.println("Não há mais melhoras \n");
								break zero;
							}
						} 
					} 
				}
			}
			if(nM == -1) {
				System.out.println("Não houve melhora com este movimento \n");
				break zero;
			}
			
		} while (nM != M);

		return Collections.max(times);
	}

	public int insert_intra(Solution s) {
		return -1;
	}
	
	public int troca_intra_2(Solution s, boolean atalho) {

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
		// variável auxiliar do índice do makespan
		int nM = -1;
		// tamanho da maquina
		int size_maq = -1;
		// uma terceira variável para operação inicializada com o valor de makespan
		// inicial
		int makespan_atual = makespan_menor;

		// lista para gravar o movimento de melhora
		

		// lista cópia dos tempos de cada máquina que compoe a solução
		this.times = this.duplicate_times(s.Tempos());

		/*
		 * Loop para executar o procedimento até o final da solução
		 */

		zero: do {
			// System.out.println("Indice Makespan: "+M);

			// atualiza o valor do makespan atual
			makespan_menor = times.get(M);
			// atualiza o tamanho da máquina atual
			size_maq = s.getMaq(M).getSizeMaq();

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

			// trata maquinas com apenas um(1)job
			if (size_maq > 1) 
			{
				first: for (int i = 0; i < size_maq - 1; i++) 
				{
					second: for (int j = i + 1; j < size_maq; j++) 
					{
						// trata o envolvimento do primeiro elemento
						if (i == 0) {
							p_antes_i = s.get_T_prep(M, i, i); // setup do primeiro é 0
							p_t_antes_i = s.get_T_prep(M, j, j); // setup depois da troca também
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

						/*
						 * Após atribuições das variáveis acontence o calculo do makespan que opera o
						 * makespan atual com o resultado de adição e subtração dos tempos de preparação
						 * envolvidos no movimento de troca
						 */

						makespan_corrente = makespan_atual + (-1) * p_antes_i + p_t_antes_i + (-1) * p_depois_i
								+ p_t_depois_i + (-1) * p_antes_j + p_t_antes_j + (-1) * p_depois_j + p_t_depois_j;

						// Verifica se o movimento simulado trará ou não melhora

						if (makespan_corrente < makespan_menor) {
							/*
							 * Atualiza as variáveis makespan_menor e makespan_atual para o novo valor
							 * encontrado
							 */
							makespan_menor = makespan_corrente;
							makespan_atual = makespan_menor;

							// Adiciona o novo movimento de melhora
							this.better_mov(M, i, j);
							// Atualiza a lista de tempos secundária, máquia e tempo
							this.update_times(M, makespan_menor);
							grava_troca_intra(better_mov, s);
							
							better_mov.clear();
							// Recalcula a lista de tempos para eleger o novo maior tempo
							makespan_corrente = Collections.max(times); // É necessário aqui? pra que?
							nM = times.indexOf(makespan_corrente);

							/**
							 * Após uma máquina ter seu tempo reduzido e com isso deixar de ser a máquina
							 * makespan, dois caminhos diferentes podem ser tomados: -> abandonar esta máqui
							 * e partir para a próxima máquina makespan - first improvement; -> continuar
							 * reduzindo essa máquina ao máximo - best improvement.
							 **/

							/*
							 * Se atalho for true a execução sai do loop após a primeira melhora testar se é
							 * melhor reduzir a máquina ao máximo ou apenas até encontrar algo menor que o
							 * makespan atual.
							 */

							if (atalho) 
							{
								nM = times.indexOf(makespan_corrente);
								if (nM != M) // Testa se a maq makespan mudou, se sim aplica o mov e sai do loop
								{
									if (!better_mov.isEmpty()) 
									{
										grava_troca_intra(better_mov, s);
										better_mov.clear();
									}
									break zero;
								}
							} 
							
						} 
					} 

					/* concluiu a máquina aplica o movimento de forma real
					*  ou apenas adiciona uma nova linha e aplica todos os movimentos no
					*  final(implementar) o objeto faz um set ao invez de um clear
					*/

					if (!better_mov.isEmpty()) 
					{
						grava_troca_intra(better_mov, s);
						better_mov.clear();
					}

					
					if (nM == M)
						break zero;
					
				}

				nM = times.indexOf(Collections.max(times));
				int aux = M;
				M = nM;
				nM = aux;

				if (!better_mov.isEmpty()) {
					grava_troca_intra(better_mov, s);
					better_mov.clear();
				}
			} else
				System.out.println("Maquina menor que 1");

		} while (nM != M);

		// return makespan_melhor;
		// System.out.println(s.Tempos());
		// System.out.println(tempos);
		return Collections.max(times);
	}
}
/*
 * makespan_corrente = makespan_atual + (-1* s.get_T_prep(M, i-1, i)) +
 * s.get_T_prep(M, i-1, j)+ (-1* s.get_T_prep(M, i, i+1)) + s.get_T_prep(M, j,
 * i+1)+ (-1* s.get_T_prep(M, j-1, j)) + s.get_T_prep(M, j-1, i)+ (-1*
 * s.get_T_prep(M, j, j+1)) + s.get_T_prep(M, i, j+1);
 */