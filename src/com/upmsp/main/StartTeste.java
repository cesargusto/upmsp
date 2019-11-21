package com.upmsp.main;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import com.upmsp.metaheuristic.ils.Ils;
import com.upmsp.experiment.BestResults;
import com.upmsp.experiment.ReadDirFilesNames;
import com.upmsp.instances.ReadInstances;
import com.upmsp.instances.readRuiz;
import com.upmsp.metaheuristic.vns.Vns;
import com.upmsp.pr.EliteSet;
import com.upmsp.pr.pr;
import com.upmsp.results.R_ruiz;
import com.upmsp.structure.Instance;
import com.upmsp.structure.Moviments2;
import com.upmsp.structure.Solution;

import simulated_mov.ILS;
import simulated_mov.SA;
import simulated_mov.Vnd;

public class StartTeste {
	
	public static String dir = "instancias/large/";
	public static String name = "I_10_3_S_1-124_1";
	public static String path;
	
	public static void sa(Solution s) throws IOException, CloneNotSupportedException{
		s.ConstroiSolution();
		s.print_solution();

		EliteSet es = new EliteSet();
		pr pr = new pr();
		
		long Start = System.currentTimeMillis();
		
		SA sa = new SA(s, 100000, es);
		s = sa.execute_sa();
		s.print_solution();
		
		pr.execute_pr_pos(s, es).print_solution();
		
		
		long End = System.currentTimeMillis();
		long Time = End - Start;
		System.out.println("\nTEMPO SA -- "+Time/1000+" seg");

	}
	
	public static void ils(Solution s) throws IOException, CloneNotSupportedException{
		
		boolean pr = false;
		boolean fi = false;
		
		s.ConstroiSolution();
		s.print_solution();

		long Start = System.currentTimeMillis();
		
		ILS ils = new ILS(s, 150000, pr, fi);
		ils.execute_ils().print_solution();
		
		long End = System.currentTimeMillis();
		long Time = End - Start;
		System.out.println("\nTEMPO ILS -- "+Time/1000+" seg");
	
	}
	
public static void teste(Solution s) throws IOException, CloneNotSupportedException{
		
		boolean pr = false;
		boolean fi = false;
		
		s.ConstroiSolution();
		s.print_solution();

		Vnd vnd = new Vnd();
		s = vnd.execute_vnd(s, false, true);
		s.print_solution();
	
	}
	
	public static void main(String[] args) throws IOException, CloneNotSupportedException {

		ReadInstances inst = new readRuiz(dir+"I_100_20_S_1-124_1.txt");
		Solution s = new Solution(inst);
		
		//ils(s);
		sa(s);
		//teste(s);
		
	}

}
