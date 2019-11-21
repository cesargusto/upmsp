package com.upmsp.experiment.confexperiment;

public class MainExp {

	public static void main(String[] args) {
		ConfExperiment conf_exp = new ConfExperiment();
		conf_exp.execute("ILS").execute_experiment("Instancia_X");
		conf_exp.execute("VNS").execute_experiment("Instancia_Y");

	}

}
