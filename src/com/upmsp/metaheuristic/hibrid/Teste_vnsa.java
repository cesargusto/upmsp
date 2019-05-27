package com.upmsp.metaheuristic.hibrid;

import java.io.IOException;

import com.upmsp.experiment.BestResults;
import com.upmsp.instances.ReadInstances;
import com.upmsp.instances.readRuiz;
import com.upmsp.localsearch.LocalSearch;
import com.upmsp.localsearch.Moviments;
import com.upmsp.localsearch.VND;
import com.upmsp.metaheuristic.SA.SA;
import com.upmsp.metaheuristic.grasp.GraspConstruction;
import com.upmsp.metaheuristic.ils.Ils;
import com.upmsp.metaheuristic.ils.LocalSearchILS;
import com.upmsp.structure.Instance;
import com.upmsp.structure.Solution;

public class Teste_vnsa {

	public static void main(String[] args) throws IOException, CloneNotSupportedException {
		
		ReadInstances inst = new readRuiz("instancias/large/I_50_10_S_1-9_1.txt");
		//Instance inst = new Instance("instancias/small/I_12_2_S_1-124_1.txt");

		Solution s = new Solution(inst);
		System.out.println("Iniciou...");
		//s.construction_greedy();
		GraspConstruction gc = new GraspConstruction(inst);
		s = gc.execute_gc(0.8);
		System.out.println("Construído...");
		s.print_makespan();
		BestResults best_r = new BestResults();
		SA sa = new SA(s, 100000, best_r);
		Solution s2 = sa.execute_sa();
		s2.print_makespan();
		Ils ils = new Ils(s2, 100000, best_r);
		ils.execute_ils().print_makespan();
		




	}

}
