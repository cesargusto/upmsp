/***************************************************************
 * Esta classe monta a metaheurística grasp clássica
 * @date 5 de nov de 2017	
 * @author cesar
 */

package com.upmsp.metaheuristic.grasp;

import com.upmsp.metaheuristic.ils.LocalSearchILS;
import com.upmsp.structure.Instance;
import com.upmsp.structure.Solution;

public class Grasp {

	
	private double alfa;
	private int grasp_max;
	private Instance inst;
	private GraspConstruction gc;
	private LocalSearchILS bl_ils;
	
	public Grasp(Instance inst, double alfa, int grasp_max){
		this.inst = inst;
		this.gc = new GraspConstruction(this.inst);
		this.bl_ils = new LocalSearchILS();
		this.alfa = alfa;
		this.grasp_max = grasp_max;
	}
	
	public Solution execute_grasp() throws CloneNotSupportedException{
		Solution s = null;
		Solution s_star = null;
		int fo_melhor = Integer.MAX_VALUE;
		int fo_aux = -1;
		while(this.grasp_max > 0){
			s = this.gc.execute_gc(this.alfa);
			s = this.bl_ils.local_search_ils(s);
			fo_aux = s.makespan();
			if(fo_aux < fo_melhor){
				s_star = s.clone();
				fo_melhor = fo_aux;
				System.out.println("Melhora: "+fo_melhor);
			}
			this.grasp_max --;
		}
		
		return s_star;
	}
}
