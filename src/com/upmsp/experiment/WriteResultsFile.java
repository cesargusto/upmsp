/*****************************************************
 * 
 * Esta classe é responsável por gravar um array de valores de makespan
 * em um arquivo de texto para posterior análise. o atributo factor_reduction
 * é responsável por amostrar esses valores de modo que não seja necessário
 * a gravação de todos os valores quando uma execuão tiver um valor alto
 * de iterações.
 * 
 * @author cesar
 * Esta classe foi criada em: 19 de out 2017.
 * 
 *****************************************************/

package com.upmsp.experiment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class WriteResultsFile {
	
	private BestResults best_results;
	private String file_name;
	private int factor_reduction = 10;
	
	public WriteResultsFile(BestResults best_results, String file_name) {
		this.best_results = best_results;
		this.file_name = file_name;
	}
	
	public void write() {
		String file_name_r = "r_"+file_name;
		File arquivo = new File("results/"+file_name_r);
		try{
			if(!arquivo.exists()){
				arquivo.createNewFile();
			}
			
			FileWriter fw = new FileWriter(arquivo, true);
			BufferedWriter bw = new BufferedWriter(fw);
			
			for(int i = 0;i < this.best_results.getSize_list();i+=factor_reduction){
				int value = this.best_results.getElement_list(i); 
				bw.write(Integer.toString(value));
				bw.write(" ");

			}
			
            bw.newLine();
			
			bw.close();
			fw.close();
			
			}catch(Exception e){
				System.out.println("Erro ao escrever no arquivo");
			}	
	}

}
