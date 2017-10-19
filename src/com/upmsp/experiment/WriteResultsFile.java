package com.upmsp.experiment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class WriteResultsFile {
	
	private BestResults best_results;
	
	public WriteResultsFile(BestResults best_results) {
		this.best_results = best_results;
	}
	
	public void write() {
		File arquivo = new File("teste.txt");
		try{
			if(!arquivo.exists()){
				arquivo.createNewFile();
			}
			
			FileWriter fw = new FileWriter(arquivo, true);
			BufferedWriter bw = new BufferedWriter(fw);
			
			for(int i = 0;i < this.best_results.getSize_list();i++){
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
