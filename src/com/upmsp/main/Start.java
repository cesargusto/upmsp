package com.upmsp.main;

import com.upmsp.experiment.BestResults;
import com.upmsp.instances.ReadInstances;
import com.upmsp.instances.readRabadi;
import com.upmsp.metaheuristic.SA.SA;
import com.upmsp.structure.Solution;

public class Start {

	public static void main(String[] args) throws Exception {

		String file_name = "experiment_instances/Rabadi-2006/Large/Machines-ProcDomin/4Machines-ProcDomin(done)/80on4Rp50Rs50_1.dat";
		//String file_name = "experiment_instances/Rabadi-2006/Small/p-dominant/6on2Rp50Rs50_1Ameer.dat";
		//String file_name = "experiment_instances/ruiz/large/I_50_10_S_1-124_1.txt";
		
		//readRabadi rr = new readRabadi(file_name);
		//rr.imprime_tempo_exec();
		//rr.imprime_tempo_prep();
		System.out.println("Executando...\n");
		long Start = System.currentTimeMillis();
		ReadInstances ri = new readRabadi(file_name);
		//ReadInstances ri = new readRuiz(file_name);
		Solution s = new Solution(ri);
		s.ConstroiSolution();
		//s.print_solution();
		BestResults bst = new BestResults();
		//Ils ils = new Ils(s, 1000000, bst);
		//s = ils.execute_ils();
		SA sa = new SA(s, 900.0, (float) 0.99, 40000, bst);
		s = sa.execute_sa();
		System.out.println(s.makespan());
		
		long End = System.currentTimeMillis();
		long Time = End - Start;
		Time = Time / 1000;
		System.out.println("\nTempo: "+Time+" segundos");
		
		/*
		 * ## FAZER
		 * - Melhorar o construtor das classes filhas de ReadInstances, usar um método para leitura inicial
		 * - #### OK #### testar o cluster
		 * - #### OK #### proporcionar menos configurações à aplicação
		 * - subir para execução no cluster e comparar resultados - enviar para Sergio
		 * - Organizar execucao via strategy passando apenas o nome do algoritmo
		 * - #### OK #### Fazer RPDavg(medias entre todos os RPD de cada execucao) e RPDbest (a media em relacao ao ultimo)
		 * - #### OK #### Fazer o tempoExec em relacao aos parametros t = (10, 30, 50)
		 * - instancia | Media | Mediana | Desv.Padrao | RPDavg | RPDbest |
		 * - Medias finais para tudo
		 * - Gerar boxplots dos RPDavg entre os algoritmos comparados
		 * - Gerar grafico de tempo para alcancar o alvo - target
		 * - Calcular Anova e teste de Tukey
		 * - #### OK #### Ler outras instancias usadas na literatura
		 * - #### OK #### Mandar email com resultados
		 * - Usar integração entre python e latex para gerar relatórios e enviar em anexo
		 * - #### OK #### Corrigir execucao com instancias grandes
		 * - Estudar e aplicar recursos do GitHub
		 * - Estudar e implementar Shell Script para controle das execucoes
		 *   
		 * */
	}

}
