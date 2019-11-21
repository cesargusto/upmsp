package com.upmsp.experiment_metaheuristic;

import java.io.IOException;
import java.util.Properties;

import com.upmsp.experiment.ReadFileConf;

public class MetaheuristicFactory {
	
	private Properties prop;
	
	public MetaheuristicFactory() throws IOException {
		new ReadFileConf();
		this.prop = ReadFileConf.getProp();
	}
	
	public Metaheuristica criaMetaheuristica(Algoritmo algoritmo) {
		if(algoritmo.equals(Algoritmo.VNS))
			return new VNS(prop);
		else if(algoritmo.equals(Algoritmo.GRASP))
			return new GRASP(prop);
		else if (algoritmo.equals(Algoritmo.ILS))
			return new ILS(prop);
		else if (algoritmo.equals(Algoritmo.SA))
			return new SA(prop);
		return null;
		
	}

}
