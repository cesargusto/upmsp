package com.upmsp.main;

import java.io.IOException;

import com.upmsp.experiment.BestResults;
import com.upmsp.experiment.StartExperiment;
import com.upmsp.experiment.WriteResultsFile;
import com.upmsp.localsearch.Moviments;
import com.upmsp.metaheuristic.SA.SA;
import com.upmsp.metaheuristic.grasp.Grasp;
import com.upmsp.metaheuristic.grasp.GraspConstruction;
import com.upmsp.metaheuristic.ils.Ils;
import com.upmsp.metaheuristic.vns.Vns;
import com.upmsp.structure.Instance;
import com.upmsp.structure.Solution;
import com.upmsp.util.View;

public class Start {

	public static void main(String[] args) throws IOException, CloneNotSupportedException {

		StartExperiment exp = new StartExperiment(20);
		exp.start();
		
		
		//Instance inst = new Instance("instancias/small/I_10_3_S_1-9_7.txt");
		//inst.imprime_tempo_exec();
		/*
		Grasp grasp = new Grasp(inst, 0.5, 100);
		View.title_1("MELHOR SOLUÇÃO GRASP");
		grasp.execute_grasp().print_solution();;
		*/
		
		//inst.imprime_tempo_prep();
		//Solution sol = new Solution(inst);
		//sol.ConstroiSolution();
		//sol.print_solution();
		//sol.construction_greedy();
		//sol.print_solution();
		
		//BestResults br = new BestResults();
		
		/*
		Moviments mov = new Moviments();
		sol = mov.perturbation_hard(sol, 1);
		sol.print_solution();*/
		
		
		//Vns vns = new Vns(sol, 10000, br);
		//sol = vns.execute_vns();
		//sol.print_solution();
		
		/*
		System.out.println("SOLUÇÃO SA:");
		SA sa = new SA(sol, 10, br);
		sol = sa.execute_sa();
		sol.print_solution();*/
		
		/*
		System.out.println("SOLUÇÃO ILS:\n");
		Ils ils = new Ils(sol, 500, br);
		sol = ils.execute_ils();
		View.title_1("MELHOR SOLUÇÃO ILS");
		sol.print_solution();
		*/
	}

}
