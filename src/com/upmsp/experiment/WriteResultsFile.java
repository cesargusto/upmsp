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
import java.io.IOException;
import java.util.Properties;

import com.upmsp.results.R_ruiz;

public class WriteResultsFile {
	
	private BestResults best_results;
	private String file_name;
	private String file_path;
	private Properties prop;
	private int factor_reduction = 0;
	private R_ruiz rr;
	
	public WriteResultsFile(BestResults best_results, String file_name, Properties prop) throws IOException {
		this.best_results = best_results;
		this.prop = prop;
		this.file_name = file_name;
		this.rr = new R_ruiz(this.prop);
		this.rr.ler();
	}
	
	public WriteResultsFile(Properties prop) throws IOException {
		this.prop = prop;
		this.file_path = this.prop.getProperty("RESUME_PATH");
	}
	
	public void write() {
		String file_name_r = "r_"+file_name;
		//File arquivo = new File("../results/"+file_name_r);
		File arquivo = new File("results/"+file_name_r);
		try{
			if(!arquivo.exists()){
				arquivo.createNewFile();
			}
			
			FileWriter fw = new FileWriter(arquivo, true);
			BufferedWriter bw = new BufferedWriter(fw);
			
			for(int i = 0;i < this.best_results.getSize_best_list();i++){
				int value = this.best_results.getElement_Best_list(i); 
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
	public void write_resume(long time, int quant_arqs) {
		
		File arquivo = new File(prop.getProperty("RESUME_PATH"));
		try{
			if(!arquivo.exists()){
				arquivo.createNewFile();
			}
			
			FileWriter fw = new FileWriter(arquivo, true);
			BufferedWriter bw = new BufferedWriter(fw);
			 
			bw.write(" - ARQUIVOS PROCESSADOS ..........: ");
			bw.write(Integer.toString(quant_arqs));
			bw.newLine();
			bw.write(" - TEMPO DE PROCESSAMENTO(SEC.)...: ");
			bw.write(Float.toString(time));
			
            bw.newLine();
			
			bw.close();
			fw.close();
			
			}catch(Exception e){
				System.out.println("Erro ao escrever no arquivo");
		}	
	}
	/*
	 * O metodo write_gap cria um arquivo com o nome da instancia, os valores absolutos de função objetivo
	 * do algoritmo executado e o da literatura, calcula e escreve o gap
	 * metodo finalizado em 18 de maio de 2018
	 */
	public void write_gap() {

		//File arquivo = new File("../results/gaps");
		File arquivo = new File(prop.getProperty("RESULT_PATH"));
		try{
			if(!arquivo.exists()){
				arquivo.createNewFile();
			}
			
			FileWriter fw = new FileWriter(arquivo, true);
			BufferedWriter bw = new BufferedWriter(fw);
			
			float value = 0; //valor obtido pelo algoritmo executado
			float value_lit = 0; //valor da literatura
			float gap = 0; //gap (value - value_lit)/value
			int quant_itens = this.best_results.getSize_best_list();//tamanho da lista
			
			for(int i = 0;i < quant_itens;i++){//somatorio do array
				value = value + this.best_results.getElement_Best_list(i);
			}
			//removendo o .txt do final do nome do arquivo
			String f_name_noE = file_name.substring(0, file_name.indexOf("."));
			//calculo de media
			value = value / quant_itens;
			
			bw.write(f_name_noE);//escreve o nome da instancia
			bw.write(prop.getProperty("WRITE_FILE_DELIMIT"));//escreve o delimitador
			bw.write(Float.toString(value));//escreve o resultado absoluto da execução
			bw.write(prop.getProperty("WRITE_FILE_DELIMIT"));//delimitador
			//pega o valor absoluto da literatura
			value_lit = this.rr.getValor(f_name_noE);
			
			bw.write(Float.toString(value_lit));//escreve o valor absoluto da literatura
			bw.write(prop.getProperty("WRITE_FILE_DELIMIT"));//delimitador

			gap = (value - value_lit)/value_lit; //calcula o gap
			
			bw.write(Float.toString(gap)); //grava o gap
			
            bw.newLine();
			
			bw.close();
			fw.close();
			
			}catch(Exception e){
				System.out.println("Erro ao escrever no arquivo");
		}	
	}
}
