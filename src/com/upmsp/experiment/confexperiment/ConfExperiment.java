package com.upmsp.experiment.confexperiment;

import java.io.IOException;

import com.upmsp.experiment.BestResults;
import com.upmsp.experiment.Time;
import com.upmsp.experiment.WriteResultsFile;
import com.upmsp.metaheuristic.vns.Vns;
import com.upmsp.structure.Instance;
import com.upmsp.structure.Solution;
import com.upmsp.util.Parses;

public class ConfExperiment {
	//MetaheuristicaExp metExp; 
	
	public MetaheuristicaExp execute(String alg_name) {
		if(alg_name.equals("ILS"))
			return new ILS();
		else 
			if(alg_name.equals("VNS"))
			return new VNS();
		return null;
	}
	
/*
	String complete_path = this.dir + file_name;
	Instance inst = new Instance(complete_path);
	Time time;
	
	Solution sol;
	BestResults best_results;
	String algoritmo_name;
	WriteResultsFile write_file;
	
public void execute_experiment(String file_name) throws IOException, CloneNotSupportedException {
		
		if(this.prop.getProperty("VNS").equals("ON")) {
			
			//VNS
			algoritmo_name = "VNS";
			best_results = new BestResults(this.prop);
			write_file = new WriteResultsFile(best_results, file_name, this.prop);
			time = new Time();

			if(this.prop.getProperty("TIME_EXEC").equals("ON")) {
				
				String ts = this.prop.getProperty("VALUES_T");
				String[] t_s = ts.split(",");
				long limite = 0;
				
				for(int i = 0;i < this.num_exec;i++) {
					for(int j = 0;j < t_s.length;j++) {
						limite = this.calc.tempoExec(Parses.split_nmaq(file_name), Integer.parseInt(t_s[j]));
						sol = new Solution(inst);
						sol.ConstroiSolution();
						Vns vns = new Vns(sol, best_results);
						sol = vns.execute_vns(limite);
						best_results.setValueT(sol.makespan());
					}
					best_results.setTabelaT(best_results.getValueT());
					best_results.clean_valueT();
				}
				best_results.calc_media_valuesT(best_results.soma_valuesT());
				write_file.write_gap(algoritmo_name);
				
			}else {

				int vns_max = Integer.parseInt(this.prop.getProperty("VNS_MAX"));//iterações do algoritmo
				
				long start = 0;
				long end = 0;
				long t = 0;
				
				for(int i = 0;i < num_exec;i++) {
					start = System.currentTimeMillis();
					sol = new Solution(inst);
					sol.ConstroiSolution();
					Vns vns = new Vns(sol, vns_max, best_results);
					sol = vns.execute_vns();
					end = System.currentTimeMillis();
					t = end - start;
					time.setTempo(t);
				}
				write_file.write(algoritmo_name, time);
			}
		}
		*/
}
