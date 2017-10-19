package com.upmsp.experiment;

import java.io.File;
import java.io.IOException;

import com.upmsp.util.View;

public class StartExperiment {
	
	private static int executions_number = 3;

	public static void main(String[] args) throws IOException, CloneNotSupportedException {
		
		String path = "experiment_instances/";
		File[] name_list = ReadDirFilesNames.leDir(path);
		
		View.title_1("INICIO DO EXPERIMENTO");
		
		for(int i = 0;i < name_list.length;i++) {
			View.title_2(name_list[i].getName());
			for(int j = 0; j < executions_number;j++) {
				ConfExperiment.execute_experiment(path, name_list[i].getName());
			}
		}
		
		View.title_1("FIM DO EXPERIMENTO");

	}

}
