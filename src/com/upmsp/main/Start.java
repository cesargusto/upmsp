package com.upmsp.main;

import java.io.IOException;
import com.upmsp.structure.Instance;
import com.upmsp.structure.Solution;

public class Start {

	public static void main(String[] args) throws IOException {

		Instance inst = new Instance("I_6_2_S_1-9_1");
		//inst.imprime_tempo_exec();
		//inst.imprime_tempo_prep();
		Solution sol = new Solution(inst);
		sol.ConstroiSolution();
		sol.imprimeSolucao();
		


	}

}
