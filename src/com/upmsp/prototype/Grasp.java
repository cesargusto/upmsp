package com.upmsp.prototype;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Grasp {
	
	public ArrayList<Integer> execute_grasp(ArrayList<Integer> sol_init, double alfa){

		ArrayList<Integer> LC = new ArrayList<>(sol_init);
		ArrayList<Integer> LCR = new ArrayList<>();
		ArrayList<Integer> solution = new ArrayList<>();
		Random rnd = new Random();

		int g_min = -1;
		int g_max = -1;
		
		for(int j = 0; j < LC.size();j++){
			Collections.sort(LC);

			g_min = LC.get(0);
			g_max = LC.get(LC.size()-1);
			
			double valor = Double.MAX_VALUE;
			
			valor = g_min + alfa*(g_max - g_min);
			
			for(int i = 0;i < LC.size();i++){
				int get = LC.get(i); 
				if( get < valor){
					LCR.add(LC.get(i));
					LC.remove(i);
				}
			}
			int index = rnd.nextInt(LCR.size());
			solution.add(LCR.get(index));
		}
		return solution;
	}
}
