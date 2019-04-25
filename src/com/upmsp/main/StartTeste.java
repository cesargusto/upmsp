package com.upmsp.main;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import com.upmsp.experiment.BestResults;
import com.upmsp.experiment.ReadDirFilesNames;
import com.upmsp.metaheuristic.vns.Vns;
import com.upmsp.results.R_ruiz;
import com.upmsp.structure.Instance;
import com.upmsp.structure.Moviments2;
import com.upmsp.structure.Solution;
import com.upmsp.util.View;
import com.upmsp.experiment.ReadFileConf;
import com.upmsp.localsearch.Moviments;

public class StartTeste {
	
	public static String dir = "instancias/small/";
	public static String name = "I_12_5_S_1-99_";
	public static String path;
	
	public static void roda_todos() throws IOException, CloneNotSupportedException{
		
		for (int i = 1; i < 10; i++) {
			path = dir+name+i;
			System.out.println(name+i);
			Instance inst = new Instance(path+".txt");
			Solution s = new Solution(inst);
			s.ConstroiSolution();
			System.out.println("Makespan init.: "+s.makespan());
			System.out.println("New: "+Moviments2.troca_intra(s, true));
			Moviments mv = new Moviments();
			System.out.println("Old: "+mv.troca_intra_Maq(s).makespan());
			System.out.println();
		}
	}
	
	public static void roda_um() throws IOException, CloneNotSupportedException{
		Instance inst = new Instance(dir+"I_12_5_S_1-99_2.txt");
		Solution s = new Solution(inst);
		//inst.imprime_tempo_prep();
		//inst.imprime_tempo_exec();
		s.ConstroiSolution();
		s.print_solution();
		//System.out.println("Makespan init.: "+s.makespan());
		System.out.println("New: "+Moviments2.troca_intra(s, false));
		//System.out.println(s.Tempos());
		s.print_solution();
		System.out.println(s.Tempos());
		
		/*Solution p = new Solution(inst);
		p.ConstroiSolution();
		Moviments mv = new Moviments();
		System.out.println("Old: "+mv.troca_intra_Maq(p).makespan());
		p.print_solution();
		System.out.println();*/	
	}
	public static void main(String[] args) throws IOException, CloneNotSupportedException {

		//roda_todos();
		roda_um();
		
		//Properties prop = new ReadFileConf().getProp();
		//String dir = "instancias/small/";
		//String arq_name = "I_12_5_S_1-99_9";//instancia com problema de maquina nula - resolver
		//String dir = "instancias/large/";
		//String arq_name = "I_100_20_S(50-100;100-150)_R(1-5;5-10)_10";
		//Instance inst = new Instance(dir+arq_name+".txt");
		
		//int iter = Integer.parseInt(prop.getProperty("VNS_MAX"));
				
		//inst.imprime_tempo_prep();
		//inst.imprime_tempo_exec();
		
		//Solution s = new Solution(inst);
		//s.construction_greedy();
		//s.ConstroiSolution();
		//s.print_solution();
		
		//System.out.println(s.Tempos());
		//System.out.println("Makespan solução inicial: "+s.makespan());
		//System.out.println("Indice makespan: "+s.indice_makespan());
		
		
		
		//long Start = System.currentTimeMillis();
		
		//Moviments2 mv2 = new Moviments2();
		//System.out.println("Makespan init.: "+s.makespan());
		//System.out.println("New: "+Moviments2.troca_intra(s));
		
		//s.print_solution();
		
		/*
		long End = System.currentTimeMillis();
		
		long Time = End - Start;
		System.out.println("Tempo algoritmo NOVO: "+Time);
		
		
		System.out.println();
		
		Start = System.currentTimeMillis();*/
		//Moviments mv = new Moviments();
		//System.out.println("Old: "+mv.troca_intra_Maq(s).makespan());
		//End = System.currentTimeMillis();
		
		//Time = End - Start;
		//System.out.println("Tempo algoritmo VELHO: "+Time);
		
		/**/
		
		//Moviments mvs = new Moviments();
		//mvs.change_Maq(s).print_solution();
		
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
