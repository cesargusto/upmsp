package com.upmsp.experiment_metaheuristic;

import java.io.IOException;
import java.util.Properties;

import com.upmsp.experiment.BestResults;
import com.upmsp.experiment.WriteResultsFile;
import com.upmsp.instances.ReadInstances;
import com.upmsp.instances.readRuiz;
import com.upmsp.metaheuristic.vns.Vns;
import com.upmsp.structure.Instance;
import com.upmsp.structure.Solution;
import com.upmsp.util.Parses;

public class VNS extends MaeAlgoritmo implements Metaheuristica {
	
	static Properties prop;
	static String dir;

	public VNS(Properties prop2) {
		super(prop, dir);
	}

	@Override
	public void start() throws CloneNotSupportedException {
		System.out.println("Rodando VNS ... "+this.prop.getProperty("VNS_MAX"));
		
		String complete_path;
		ReadInstances inst = null;
		
		complete_path = super.dir + super.file_name;
		try {
			inst = new readRuiz(complete_path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		Solution sol;
		BestResults best_results;
		String algoritmo_name;
		WriteResultsFile write_file;
		
		if(this.prop.getProperty("VNS").equals("ON")) {
			//VNS
			
			best_results = new BestResults();
			int vns_max = Integer.parseInt(this.prop.getProperty("VNS_MAX"));//iterações do algoritmo
			
			algoritmo_name = "VNS";
			long start = 0;
			long end = 0;
			long t = 0;

			if(this.prop.getProperty("TIME_EXEC").equals("ON")) {
				String ts = this.prop.getProperty("VALUES_T");
				String[] t_s = ts.split(",");
				this.num_exec = t_s.length;
				long limite = 0;
				for(int i = 0;i < t_s.length;i++) {
					limite = this.calc.tempoExec(Parses.split_nmaq(file_name), Integer.parseInt(t_s[i]));
					start = System.currentTimeMillis();
					sol = new Solution(inst);
					sol.ConstroiSolution();
					Vns vns = new Vns(sol, vns_max, best_results);
					sol = vns.execute_vns(limite);
					end = System.currentTimeMillis();
					t = end - start;
					time.setTempo(t);
					if(time.SomaTempos() >=  limite) {
						break;
					}
				}
			}else {
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
			}
			
			try {
				write_file = new WriteResultsFile(best_results, super.file_name, super.prop);
				write_file.write_gap(algoritmo_name);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}

}
