/*
 * 
 * Esta classe tem como função ler um arquivo de resultados da literatura e guardá-lo em uma
 * estrutura de dados para posterior comparação. Neste caso a função faz isso apenas para as instancias
 * de Vallada e Ruiz, mas será adaptada para ler outros arquivos caso tenham o mesmo formato.
 * Método concluído em 18 de Maio de 2018
 * 
 * @author Cesar
 * 
 */

package com.upmsp.results;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

public class R_ruiz {

	private Map<String, Integer> ruiz;
	private Properties prop;
	private int valor;
	
	public R_ruiz(Properties prop) {
		this.ruiz = new HashMap<>();
		this.prop = prop;
		this.valor = 0;
	}

	public void ler() throws IOException {

        InputStream is = new FileInputStream(prop.getProperty("BOUNDS_FILE_LIT"));
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        String linha = null;
        StringTokenizer st;
        
        String inst_name = "";
        int valor = -1;
        
        while(br.ready()) {
        	linha = br.readLine();
        	st = new StringTokenizer(linha, prop.getProperty("BOUNDS_FILE_DELIMIT"));
        
        	inst_name = (String)st.nextToken();
        	valor = Integer.parseInt(st.nextToken());
        	
        	ruiz.put(inst_name, valor);
		}
	}
	
	public int getValor(String name) {
		return ruiz.get(name);
	}
}
