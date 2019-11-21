package com.upmsp.experiment.confexperiment;

import java.util.Properties;

import com.upmsp.structure.Solution;

public abstract class MetaheuristicaExp {

	private Properties prop;
	private String file_name = "XYZ";

	public abstract Solution execute_experiment(String file_name);

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	
	public Properties getProp() {
		return this.prop;
	}
	
}
