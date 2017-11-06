/***********************
 * Esta classe dá o inicio do experimento criando uma array
 * com todos os arquivos existentes em um diretorio passado e então
 * passando seus nomes para a função execute_experiment. A função start executa
 * um número n de vezes (executions_number) o algoritmo para todos os arquivos contidos no
 * diretorio especificado.
 * 
 * Não é necessario passar um Array contendo os arquivos e sim utilizar
 * uma forma de extrair direto só os nomes dos arquivos passando um Array
 * de String ao invés de um Array de Files.
 * 
 * Classe criada em: 19 de out 2017
 * @author cesar
 * 
********************************************************************/
package com.upmsp.experiment;

import java.io.File;
import java.io.IOException;

import com.upmsp.util.View;

public class StartExperiment {
	
	private int executions_number;

	public StartExperiment(int n_exec){
		this.executions_number = n_exec;
	}
	
	public void start() throws IOException, CloneNotSupportedException {
		
		long Start = System.currentTimeMillis();
		
		//String path = "../experiment_instances/";
		String path = "experiment_instances/";
		File[] name_list = ReadDirFilesNames.leDir(path);
		
		View.title_1("INICIO DO EXPERIMENTO");
		
		for(int i = 0;i < name_list.length;i++) {
			View.title_2(name_list[i].getName());
			for(int j = 0; j < executions_number;j++) {
				ConfExperiment.execute_experiment(path, name_list[i].getName(), this.executions_number);
			}
		}
		
		View.title_1("FIM DO EXPERIMENTO");
		long End = System.currentTimeMillis();
		long Time = End - Start;
		Time = Time / 60000;
		View.title_1("Tempo decorrido: "+Time+" minutos");

	}

}
