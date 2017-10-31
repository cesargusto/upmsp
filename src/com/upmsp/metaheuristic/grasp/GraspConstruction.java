package com.upmsp.metaheuristic.grasp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GraspConstruction {
	
	public ArrayList<Integer> construction_grasp(
			ArrayList<Integer> sol_init, 
			double alfa)
		{

		ArrayList<Integer> LC = new ArrayList<>(sol_init);
		ArrayList<Integer> LCR = new ArrayList<>();
		ArrayList<Integer> solution = new ArrayList<>();
		Random rnd = new Random();

		int g_min = -1;
		int g_max = -1;
		
		while(!LC.isEmpty()){
			Collections.sort(LC);

			g_min = LC.get(0);
			g_max = LC.get(LC.size()-1);
			
			double valor = Double.MAX_VALUE;
			
			valor = g_min + alfa*(g_max - g_min);
			int contador = LC.size();
			for(int i = 0;i < contador;i++){
				int get = LC.get(i); 
				if( get <= valor){
					LCR.add(LC.get(i));
				}else {
					break;
				}
			}
			int index = -1;
			
			if(LCR.size() > 1)
				index = rnd.nextInt(LCR.size());
			else
				index = 0;
			
			solution.add(LCR.get(index));
			LC.remove(index);
			LCR.clear();
		}
		return solution;
	}

}
