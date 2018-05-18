/*******************************************************************
 * 
 * Esta classe contem as instruções de chamadas das heurísticas 
 * implementadas. Inicialmente é apontado para o diretórios com as 
 * intancias e são executadas em séries as heurísticas. Os resultados
 * são armazendados em um objeto com os valores de makespan e no final
 * são gravados em arquivo de texto.
 * 
 * Classe criada em: 19 de out 2017
 * @author cesar
 * 
 *******************************************************************/
package com.upmsp.experiment;

import java.io.IOException;
import java.util.Properties;

import com.upmsp.metaheuristic.SA.SA;
import com.upmsp.metaheuristic.grasp.Grasp;
import com.upmsp.metaheuristic.ils.Ils;
import com.upmsp.metaheuristic.vns.Vns;
import com.upmsp.structure.Instance;
import com.upmsp.structure.Solution;

public class ConfExperiment {
	//acessa os parametros no arquivo properties
	private Properties prop;
	private int num_iter;//iterações do algoritmo
	private int num_exec; //iterações do experimento - 30
	private String dir;
	private String file_result;
	
	public ConfExperiment(Properties prop) {
		//acessa os parametros no arquivo properties
		this.prop = prop;
		this.num_iter = Integer.parseInt(this.prop.getProperty("VNS_MAX"));//iterações do algoritmo
		this.num_exec = Integer.parseInt(this.prop.getProperty("N_EXEC")); //iterações do experimento - 30
		this.dir = this.prop.getProperty("VNS_PATH"); //caminho das instâncias
		this.file_result = this.prop.getProperty("RESULT_PATH"); //caminho de gravação dos resultados
	}
	
	public void execute_experiment(String file_name) throws IOException, CloneNotSupportedException {
		
		String complete_path = this.dir + file_name;
		Instance inst = new Instance(complete_path);
		
		//VNS
		BestResults best_results = new BestResults();
		Solution sol;
		for(int i = 0;i < num_iter;i++) {
			sol = new Solution(inst);
			sol.ConstroiSolution();
			Vns vns = new Vns(sol, num_iter, best_results);
			sol = vns.execute_vns();
		}
		WriteResultsFile write_file = new WriteResultsFile(best_results, file_name, this.prop);
		write_file.write_gap();
		//write_file.write();
		
		/*
		
		//ILS
		best_results = new BestResults();
		for(int i = 0;i < num_it;i++) {
			sol = new Solution(inst);
			sol.ConstroiSolution();
			Ils ils = new Ils(sol, 1000, best_results);
			sol = ils.execute_ils();
		}
		write_file = new WriteResultsFile(best_results, file_name);
		write_file.write();
		
		//SA
		best_results = new BestResults();
		for(int i = 0;i < num_it;i++) {
			sol = new Solution(inst);
			sol.ConstroiSolution();
			SA sa = new SA(sol, 1000, best_results);
			sol = sa.execute_sa();
		}
		write_file = new WriteResultsFile(best_results, file_name);
		write_file.write();
		
		//GRASP
		best_results = new BestResults();
		for(int i = 0;i < num_it;i++) {
			Grasp grasp = new Grasp(inst, 0.5, 1000, best_results);
			grasp.execute_grasp();
		}
		write_file = new WriteResultsFile(best_results, file_name);
		write_file.write();
	
		*/
	}

}
