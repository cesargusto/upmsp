package simulated_mov;

import java.util.ArrayList;
import java.util.Collections;

import com.upmsp.structure.Solution;

public class InsertExtra extends Moviment{

	public InsertExtra(Solution s) {
		super(s);
	}

	/*
	 * alocação e inicialização dos índices que serão manipulados na movimentação
	 **/

	int p_antes_i = 0; // tempo de preparação do job de índice i(i-1, i)
	int p_t_antes_i = 0; // tempo de preparação depois da troca (i-1, j)

	int p_depois_i = 0; // tempo de preparação do job a frente do i (i, i+1)
	int p_t_depois_i = 0; // tempo de preparação do job a frente do i depois da troca (j, i+1)

	int p_t_final_i = 0;
	int p_t_final_j = 0;
	
	int e_antes = 0;
	int e_depois = 0;
	
	@Override
	public ArrayList<Integer> execute_mov(Boolean first) {

		ArrayList<Integer> tempos_copia = this.duplicate_times(s.Tempos());

		int makespan_corrente = 0;	
		int makespan_star = Collections.max(tempos_copia);
		int M = tempos_copia.indexOf(makespan_star);
		int menor = Collections.min(tempos_copia); 
		int m = tempos_copia.indexOf(menor);
		int size_maq_M = s.getMaq(M).getSizeMaq();
		int size_maq_m = s.getMaq(m).getSizeMaq();
		
		if (size_maq_M > 1) 
		{
			first: for (int i = 0; i < size_maq_M - 1; i++) 
			{
				if (i == 0) {
					p_antes_i = 0;
					p_t_antes_i = 0;
				} else {
					p_antes_i = s.get_T_prep(M, i - 1, i);
					p_t_antes_i = s.get_T_prep(M, i-1, i + 1);
				}

				// trata o envolvimento do último elemento
				if (i == size_maq_M) {
					p_depois_i = 0;
					p_t_depois_i =  0;
				} else {
					p_depois_i = s.get_T_prep(M, i, i + 1);
					p_t_depois_i = 0;
				}

				int p = s.getMaq(M).getJob(i);
				e_antes = s.getArquivo().getT_exec(M, p);
				e_depois = s.getArquivo().getT_exec(m, p);
				
				int pivot = s.get_T_prep(m, size_maq_m - 1,M,i);
			
				makespan_corrente = tempos_copia.get(M) + (-1)* e_antes + (-1)* p_antes_i + (-1)* p_depois_i + p_t_antes_i; 
				int m_m = tempos_copia.get(m) + e_depois + p_t_depois_i + pivot;

				int t1 = tempos_copia.get(M);
				int t2 = tempos_copia.get(m);
				
				tempos_copia.set(M, makespan_corrente);
				tempos_copia.set(m, m_m);
				
				makespan_corrente = Collections.max(tempos_copia); // É necessário aqui? pra que?
				
				if (makespan_corrente < makespan_star) {
					makespan_star = makespan_corrente;
					this.better_mov_final(M, i, m, size_maq_m, makespan_star, 1);
					if(first)break first;
				}
				
				tempos_copia.set(M, t1);
				tempos_copia.set(m, t2);
			}				
		} 
		else
			System.out.println("Maquina menor que 1");
		
		return this.getBetter_mov();
	}
}
