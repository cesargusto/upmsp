/***************************************************************************
 * Esta classe retorna os 3 movimentos basicos de pertubação utilizados no ILS e SA
 * 
 * Classe criada em: 22 de out de 2017
 * @author cesar
 *
 ******************************************************************************/

package com.upmsp.metaheuristic.ils;

import com.upmsp.localsearch.LocalSearch;
import com.upmsp.structure.Solution;

public class Pertubations {
	
	private LocalSearch ls;
	private final int quant_levels = 3;
	
	public int getQuant_levels() {
		return quant_levels;
	}

	public Pertubations(){
		this.ls = new LocalSearch();
	}
	
	public Solution execute(Solution s, int level) throws CloneNotSupportedException{
		
		switch(level){
			case 1: return ls.two_realloc(s);
			case 2: return ls.two_swap(s);
			case 3: return ls.change_Maq(s);
		}
		return s;
	}

}
