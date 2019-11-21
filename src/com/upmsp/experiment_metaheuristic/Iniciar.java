package com.upmsp.experiment_metaheuristic;

import java.io.IOException;

public class Iniciar {

	public static void main(String[] args) throws IOException, CloneNotSupportedException {
		
		Metaheuristica m = 
				new MetaheuristicFactory().criaMetaheuristica(Algoritmo.VNS);
		m.start();

	}

}
