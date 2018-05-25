package com.upmsp.main;

import java.io.IOException;
import java.util.Properties;

import com.upmsp.experiment.BestResults;
import com.upmsp.metaheuristic.vns.Vns;
import com.upmsp.results.R_ruiz;
import com.upmsp.structure.Instance;
import com.upmsp.structure.Solution;
import com.upmsp.experiment.ReadFileConf;
import com.upmsp.localsearch.Moviments;

public class StartTeste {

	public static void main(String[] args) throws IOException, CloneNotSupportedException {

		Properties prop = new ReadFileConf().getProp();
		String dir = "instancias/small/";
		String arq_name = "I_10_3_S_1-9_1";
		Instance inst = new Instance(dir+arq_name+".txt");
		
		int iter = Integer.parseInt(prop.getProperty("VNS_MAX"));
		
		System.out.println("Algoritmo VNS:");
		
		Solution s = new Solution(inst);
		s.construction_greedy();
		s.print_solution();
		
		Moviments mvs = new Moviments();
		mvs.change_Maq(s).print_solution();
		
		//BestResults best = new BestResults();
		//Vns vns = new Vns(s, iter, best);
		//vns.execute_vns().print_makespan();
		
		/*
		System.out.println("Bound conhecido:");
		
		R_ruiz rr = new R_ruiz(prop);
		//rr.ler();
		
		System.out.println("Makespan .........: "+rr.getValor(arq_name));
		*/
	}

}
