package com.upmsp.experiment_metaheuristic;

import java.util.Properties;

import com.upmsp.experiment.Time;
import com.upmsp.util.Calcs;

public class MaeAlgoritmo {

	protected Properties prop;
	protected Time time;
	protected Calcs calc;
	//private int num_iter;//iterações do algoritmo
	protected int num_exec; //iterações do experimento - 30
	protected String dir;
	protected String file_result;
	protected String file_name;
	
	public MaeAlgoritmo(Properties prop, String file_name) {
		this.prop = prop;
		this.time = new Time();
		this.calc = new Calcs();
		this.num_exec = Integer.parseInt(this.prop.getProperty("N_EXEC")); //iterações do experimento - 30
		this.dir = this.prop.getProperty("INSTANCE_PATH"); //caminho das instâncias
		this.file_result = this.prop.getProperty("RESULT_PATH"); //caminho de gravação dos resultados
		this.file_name = file_name;
	}
	
}
