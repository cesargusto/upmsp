package com.upmsp.experiment.confexperiment;

import java.io.IOException;
import java.util.Properties;

import com.upmsp.experiment.BestResults;
import com.upmsp.experiment.Time;
import com.upmsp.experiment.WriteResultsFile;
import com.upmsp.experiment.confexperiment.Metaheuristica;
import com.upmsp.instances.ReadInstances;
import com.upmsp.instances.readRuiz;
import com.upmsp.metaheuristic.ils.Ils;
import com.upmsp.structure.Instance;
import com.upmsp.structure.Solution;
import com.upmsp.util.Calcs;
import com.upmsp.util.Parses;

public class MetaheuristicaExperimento {
	
	ConfExperiment conf_exp;
	String nome_algoritmo = "ILS";
	Properties prop;
	String file_name;
	BestResults best_results;
	WriteResultsFile write_file;
	Time time;
	Calcs calc;
	
	public MetaheuristicaExperimento(BestResults best_results, WriteResultsFile write_file,
			Time time, Properties prop) throws IOException {
		super();
		this.calc = new Calcs();
		this.prop = prop;
		this.best_results = best_results;
		this.best_results = new BestResults(prop);
		this.write_file = new WriteResultsFile(this.best_results, file_name, this.prop);
		this.time = new Time();
		this.conf_exp = new ConfExperiment();
	}

	public void execute(String file_name, MetaheuristicaExp M) throws IOException, CloneNotSupportedException {
		
		Solution sol;
		String dir = this.prop.getProperty("INSTANCE_RUIZ_SMALL");
		String complete_path = dir + this.file_name;
		ReadInstances inst = new readRuiz(complete_path);
		int num_exec = Integer.parseInt(this.prop.getProperty("N_EXEC"));
		
		if(prop.getProperty(nome_algoritmo).equals("ON")) {
			//ILS
			String algoritmo_name = "ILS";
			
			
			if(this.prop.getProperty("TIME_EXEC").equals("ON")) {
				String ts = this.prop.getProperty("VALUES_T");
				String[] t_s = ts.split(",");
				long limite = 0;
				
				for(int i = 0;i < num_exec;i++) {
					for(int j = 0;j < t_s.length;j++) {
						limite = this.calc.tempoExec(Parses.split_nmaq(file_name), Integer.parseInt(t_s[j]));
						sol = new Solution(inst);
						sol.ConstroiSolution();
						
						sol = conf_exp.execute("ILS").execute_experiment(file_name);
						
						best_results.setValueT(sol.makespan());
					}
					best_results.setTabelaT(best_results.getValueT());
					best_results.clean_valueT();
				}
				best_results.calc_media_valuesT(best_results.soma_valuesT());
				write_file.write_gap(algoritmo_name);
			}else {
				int ils_max = Integer.parseInt(this.prop.getProperty("ILS_MAX"));
				
				long start = 0;
				long end = 0;
				long t = 0;
				
				for(int i = 0;i < num_exec;i++) {//nºexec vs. Time
					start = System.currentTimeMillis();
					
					sol = new Solution(inst);
					sol.ConstroiSolution();
					Ils ils = new Ils(sol, ils_max, best_results);
					sol = ils.execute_ils();
					
					end = System.currentTimeMillis();
					t = end - start;
					time.setTempo(t);
				}
				write_file.write(algoritmo_name, time);
			}
		}
	}
			
	
	

}
