package com.upmsp.metaheuristic.ils;

import com.upmsp.localsearch.LocalSearch;
import com.upmsp.structure.Solution;

public class LocalSearchILS extends LocalSearch{
	
	public Solution local_search_ils(Solution s) throws CloneNotSupportedException{
		Solution sol_aux = this.remove_job_Maq_mspan(s);
		sol_aux = this.swap_job_ExtraMaq(sol_aux);
		sol_aux = this.troca_intra_Maq(sol_aux);
		sol_aux = this.insert_intra_Maq(sol_aux);
		
		return sol_aux;
	}

}
