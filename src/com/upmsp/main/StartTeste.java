package com.upmsp.main;

import java.io.IOException;
import java.util.Properties;

import com.upmsp.experiment.BestResults;
import com.upmsp.metaheuristic.vns.Vns;
import com.upmsp.structure.Instance;
import com.upmsp.structure.Solution;
import com.upmsp.experiment.ReadFileConf;

public class StartTeste {

	public static void main(String[] args) throws IOException, CloneNotSupportedException {

		Properties prop = new ReadFileConf().getProp();
		
		Instance inst = new Instance("instancias/large/I_50_10_S_1-9_1.txt");
		
		int iter = Integer.parseInt(prop.getProperty("vns_num_iterations"));
		
		System.out.println(iter);
		
		Solution s = new Solution(inst);
		s.construction_greedy();
		BestResults best = new BestResults();
		Vns vns = new Vns(s, iter, best);
		vns.execute_vns().print_solution();

	}

}
