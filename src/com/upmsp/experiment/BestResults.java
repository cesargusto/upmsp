/*******************************************************
 * Esta classe armazena melhores soluções e caminhos de convergência
 * para posterior gravação e análise.
 * 
 * Aterações de ampliação da classe feita em 23 de out 2017 as 23:40
 * @author cesar
 * 
 */
package com.upmsp.experiment;

import java.util.ArrayList;

import com.upmsp.structure.Solution;

public class BestResults {
	
	private ArrayList<Integer> makespan_list; //curva de convergencia
	private ArrayList<Integer> best_list;	//Melhor de cada execução
	private Solution best_solution;			//Melhor solução
	private ArrayList<Solution> elite_set;	//conjunto de melhores soluções
	
	public BestResults() {
		this.makespan_list = new ArrayList<>();
		this.best_list = new ArrayList<>();
		this.best_solution = new Solution();
		this.elite_set = new ArrayList<Solution>();
	}

	public ArrayList<Integer> getMakespan_list() {
		return makespan_list;
	}
	
	public Integer getElement_list(int i) {
		return makespan_list.get(i);
	}
	
	public int getSize_list() {
		return makespan_list.size();
	}

	public void setMakespan_list(int makespan) {
		this.makespan_list.add(makespan);
	}

	public int getSize_best_list() {
		return best_list.size();
	}
	
	public Integer getElement_Best_list(int i) {
		return best_list.get(i);
	}
	
	public ArrayList<Integer> getBest_list() {
		return best_list;
	}
	
	public Integer getBest_mkpan(int i) {
		return best_list.get(i);
	}

	public void setBest_list(long fo_solucao_linha) {
		this.best_list.add((int) fo_solucao_linha);
	}

	public Solution getBest_solution() {
		return best_solution;
	}

	public void setBest_solution(Solution best_solution) {
		this.best_solution = best_solution;
	}

	public ArrayList<Solution> getElite_set() {
		return elite_set;
	}
	
	public Solution getSolutionElite_set(int i) {
		return elite_set.get(i);
	}

	public void setElite_set(Solution solution) {
		this.elite_set.add(solution);
	}

	
}
 