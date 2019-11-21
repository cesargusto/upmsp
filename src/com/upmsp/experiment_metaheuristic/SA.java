package com.upmsp.experiment_metaheuristic;

import java.util.Properties;

public class SA implements Metaheuristica{

	private Properties prop;
	
	public SA(Properties prop) {
		this.prop = prop;
	}
	@Override
	public void start() {
		System.out.println("Rodando SA ...");
		
	}

}
