package com.upmsp.main;

import java.io.IOException;

import com.upmsp.experiment.BestResults;
import com.upmsp.experiment.WriteResultsFile;
import com.upmsp.metaheuristic.SA.SA;
import com.upmsp.metaheuristic.vns.Vns;
import com.upmsp.structure.Instance;
import com.upmsp.structure.Solution;

public class Start {

	public static void main(String[] args) throws IOException, CloneNotSupportedException {

		Instance inst = new Instance("small/I_6_2_S_1-9_1");

		System.out.println("CONSTRUÇÃO:\n");
		Solution sol = new Solution(inst);
		sol.ConstroiSolution();
		sol.print_solution();
		
		BestResults best_results = new BestResults();
		
		System.out.println("SOLUÇÃO VNS:\n");
		Vns vns = new Vns(sol, 1000, best_results);
		sol = vns.execute_vns();
		sol.print_solution();
		
		System.out.println("SOLUÇÃO SA:\n");
		SA sa = new SA(sol, 600, best_results);
		sol = sa.execute_sa();
		sol.print_solution();
		
		WriteResultsFile write_file = new WriteResultsFile(best_results);
		write_file.write();


	}

}
//I_12_5_S_1-124_10 - fix this problem
//I_10_2_S_1-99_1