package com.upmsp.experiment_metaheuristic;

import java.util.Properties;

public class ILS implements Metaheuristica{

	private Properties prop;
	
	public ILS(Properties prop) {
		this.prop = prop;
	}
	@Override
	public void start() {
		System.out.println("Rodando ILS ...");
		
	}

}
