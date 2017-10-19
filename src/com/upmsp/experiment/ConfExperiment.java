package com.upmsp.experiment;

import java.io.IOException;

import com.upmsp.metaheuristic.SA.SA;
import com.upmsp.metaheuristic.vns.Vns;
import com.upmsp.structure.Instance;
import com.upmsp.structure.Solution;

public class ConfExperiment {
	
	public static void execute_experiment(String dir, String file_name) throws IOException, CloneNotSupportedException {
		
		String complete_path = dir + file_name;
		Instance inst = new Instance(complete_path);
		
		BestResults best_results = new BestResults();
		
		System.out.println("CONSTRUÇÃO:\n");
		Solution sol = new Solution(inst);
		sol.ConstroiSolution();
		sol.print_solution();
		
		System.out.println("SOLUÇÃO VNS:\n");
		Vns vns = new Vns(sol, 100, best_results);
		sol = vns.execute_vns();
		sol.print_solution();
		
		System.out.println("SOLUÇÃO SA:\n");
		SA sa = new SA(sol, 600, best_results);
		sol = sa.execute_sa();
		sol.print_solution();
		
		WriteResultsFile write_file = new WriteResultsFile(best_results);
	}

}
