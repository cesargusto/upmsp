package com.upmsp.prototype;

import com.upmsp.metaheuristic.ils.LocalSearchILS;
import com.upmsp.structure.Solution;

public class Grasp {
	
	private LocalSearchILS ls_ils;
	
	public Grasp() {
		LocalSearchILS ls_ils = new LocalSearchILS();
	}
	
	public Solution execute_grasp(Solution s, int grasp_max) throws CloneNotSupportedException {
		
		Solution s_melhor;
		int fo_s_melhor = Integer.MAX_VALUE;
		
		while(grasp_max > 0) {
		
			s = this.construction_grasp(s);
			s = this.ls_ils.local_search_ils(s);
			int fo_s_aux = s.makespan();
			if(fo_s_aux < fo_s_melhor) {
				fo_s_melhor = fo_s_aux;
				s_melhor = s.clone();
			}
		}
		return s;
	}
	
	public Solution construction_grasp(Solution s) {
		return s;
	}

}
