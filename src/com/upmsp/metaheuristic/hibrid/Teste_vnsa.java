package com.upmsp.metaheuristic.hibrid;

import java.io.IOException;

import com.upmsp.experiment.BestResults;
import com.upmsp.structure.Instance;
import com.upmsp.structure.Solution;

public class Teste_vnsa {

	public static void main(String[] args) throws IOException, CloneNotSupportedException {
		
		Instance inst = new Instance("instancias/large/I_50_10_S_1-9_1.txt");

		Solution s = new Solution(inst);
		System.out.println("Iniciou...");
		s.construction_greedy();
		System.out.println("Construído...");
		BestResults best_r = new BestResults();
		Vnlsa vnlsa = new Vnlsa(s, 600, best_r);
		vnlsa.execute_vnlsa().print_solution();


	}

}
