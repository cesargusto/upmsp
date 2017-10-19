package com.upmsp.main;

import java.io.IOException;

import com.upmsp.metaheuristic.SA.SA;
import com.upmsp.metaheuristic.vns.Vns;
import com.upmsp.structure.Instance;
import com.upmsp.structure.Solution;

public class Start {

	public static void main(String[] args) throws IOException, CloneNotSupportedException {

		Instance inst = new Instance("small/I_12_5_S_1-124_10");
		//inst.imprime_tempo_exec();
		//inst.imprime_tempo_prep();
		System.out.println("CONSTRUÇÃO:\n");
		Solution sol = new Solution(inst);
		sol.ConstroiSolution();
		sol.print_solution();
		
		System.out.println("SOLUÇÃO VNS:\n");
		Vns vns = new Vns(sol, 10000);
		sol = vns.execute_vns();
		sol.print_solution();
		
		System.out.println("SOLUÇÃO SA:\n");
		SA sa = new SA(sol);
		sol = sa.execute_sa();
		sol.print_solution();


	}

}
//I_12_5_S_1-124_10 - fix this problem
//I_10_2_S_1-99_1