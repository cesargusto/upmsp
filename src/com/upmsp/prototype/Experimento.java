package com.upmsp.prototype;

import java.io.File;

public class Experimento {
	
	public static File[] leDir(String caminho) {
		File arquivos[];
		File diretorio = new File(caminho);
		arquivos = diretorio.listFiles();
		return arquivos;
	}
	
	public static void main(String []args) {
		String caminho = "arquivos/";
		leDir(caminho);
	}

}
