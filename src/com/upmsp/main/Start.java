package com.upmsp.main;

import java.io.IOException;

import com.upmsp.experiment.BestResults;
import com.upmsp.experiment.StartExperiment;
import com.upmsp.experiment.WriteResultsFile;
import com.upmsp.metaheuristic.SA.SA;
import com.upmsp.metaheuristic.vns.Vns;
import com.upmsp.structure.Instance;
import com.upmsp.structure.Solution;

public class Start {

	public static void main(String[] args) throws IOException, CloneNotSupportedException {

		//StartExperiment exp = new StartExperiment(3);
		//exp.start();
		
		
		Instance inst = new Instance("instancias/small/I_6_5_S_1-9_10.txt");
		Solution sol = new Solution(inst);
		sol.ConstroiSolution();
		sol.print_solution();
		
		BestResults br = new BestResults();
		/*
		Vns vns = new Vns(sol, 600, br);
		sol = vns.execute_vns();
		sol.print_solution();
		*/
		System.out.println("SOLUÇÃO SA:\n");
		SA sa = new SA(sol, 600, br);
		sol = sa.execute_sa();
		sol.print_solution();
		
	}

}
