package com.upmsp.main;

import java.io.IOException;

import com.upmsp.metaheuristic.vns.Vns;
import com.upmsp.structure.Instance;
import com.upmsp.structure.Solution;

public class Start {

	public static void main(String[] args) throws IOException, CloneNotSupportedException {

		Instance inst = new Instance("small/I_10_2_S_1-9_1");
		//inst.imprime_tempo_exec();
		//inst.imprime_tempo_prep();
		Solution sol = new Solution(inst);
		sol.ConstroiSolution();
		sol.print_solution();
		
		Vns vns = new Vns(sol);
		vns.execute();
		//LocalSearch ls = new LocalSearch();
		//ls.two_realloc(sol).print_solution();

	}

}
//I_12_5_S_1-124_10