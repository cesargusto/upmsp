package com.upmsp.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import com.upmsp.experiment.BestResults;
import com.upmsp.experiment.StartExperiment;
import com.upmsp.experiment.WriteResultsFile;
import com.upmsp.localsearch.Moviments;
import com.upmsp.metaheuristic.SA.SA;
import com.upmsp.metaheuristic.grasp.Grasp;
import com.upmsp.metaheuristic.grasp.GraspConstruction;
import com.upmsp.metaheuristic.hibrid.Vnlsa;
import com.upmsp.metaheuristic.ils.Ils;
import com.upmsp.metaheuristic.vns.Vns;
import com.upmsp.structure.Instance;
import com.upmsp.structure.Solution;
import com.upmsp.util.View;

public class Start {

	public static void main(String[] args) throws IOException, CloneNotSupportedException {

		//StartExperiment exp = new StartExperiment(10);
		//exp.start();
		
		//67
		Instance inst_1 = new Instance("instancias/large/I_50_10_S_1-9_1.txt");
		
		int num_exec = 30;
		int num_iter = 100;
		ArrayList<Integer>lista_vns = new ArrayList<>(30);
		ArrayList<Integer>lista_vnlsa = new ArrayList<>(30);

		View.title_2("EXPERIMENTO ALGORITMO VNLSA");
		
		long Start = System.currentTimeMillis();
		for(int i = 0;i < num_exec;i++){
			Solution s_1 = new Solution(inst_1);
			s_1.construction_greedy();
			BestResults best_r_1 = new BestResults();
			Vns vns = new Vns(s_1, num_iter, best_r_1);
			lista_vns.add(vns.execute_vns().makespan());
		}
		
		long End = System.currentTimeMillis();
		long Time_vns = End - Start;
		
		
		Instance inst_2 = new Instance("instancias/large/I_50_10_S_1-9_1.txt");

		Start = 0;
		End = 0;
		
		Start = System.currentTimeMillis();
		for(int i = 0;i < num_exec;i++){
			Solution s_2 = new Solution(inst_2);
			s_2.construction_greedy();
			BestResults best_r_2 = new BestResults();
			Vnlsa vnlsa = new Vnlsa(s_2, num_iter, best_r_2);
			lista_vnlsa.add(vnlsa.execute_vnlsa().makespan());
		}
		End = System.currentTimeMillis();
		long Time_vnlsa = End - Start;
	
		
		System.out.println("\nALGORITMO\tMÉDIA\tMIN\tMAX\tTEMPO");
		System.out.println("VNS ......: \t"+View.media(lista_vns)+"\t"
		+Collections.min(lista_vns)+"\t"+Collections.max(lista_vns)+"\t"+Time_vns+" milis.");
		System.out.println("VNLSA ....: \t"+View.media(lista_vnlsa)+"\t"
		+Collections.min(lista_vnlsa)+"\t"+Collections.max(lista_vnlsa)+"\t"+Time_vnlsa+" milis.");

	}

}
