package simulated_mov;

import java.util.ArrayList;
import java.util.Random;

import com.upmsp.structure.Solution;

public class Vnd {
	
	private Moviment moviment;
	private Better_mov bm;
	private int melhor_fo;
	
	public Vnd() {
		this.bm = new Better_mov();
	}
	
	public Solution execute_vnd(Solution s, Boolean first_imp, Boolean rvnd) throws CloneNotSupportedException {
		
		Random rnd = new Random();
		melhor_fo = s.makespan();
		int fo_aux = Integer.MAX_VALUE;
		
		ArrayList<Integer> result_aux = new ArrayList<>();
		
		int n_v = 3;
		int k = 0;
		int counter = 0;
		
		first:
		while(k < n_v){
			if(rvnd) {
				if(counter > 100)
					break first;
				else {
					k = rnd.nextInt(4);
					//System.out.println("Aleatorio: "+k);
					counter++;
				}
			}
			switch(k){
				case 0:
					moviment = new TrocaExtra(s);
					result_aux.clear();
					result_aux = moviment.execute_mov(first_imp);
					if(!result_aux.isEmpty())
						fo_aux = result_aux.get(4);
					if(fo_aux < melhor_fo){
						this.bm.carrega_better_mov(result_aux);
						melhor_fo = fo_aux;
						//System.out.println("Troca Extra: "+k);
					}
					if(rvnd) {
						break;
					}
					else
						k = k + 1;
					break;
				case 1:
					moviment = new InsertExtra(s);
					result_aux.clear();
					result_aux = moviment.execute_mov(first_imp);
					if(!result_aux.isEmpty())
						fo_aux = result_aux.get(4);
					if(fo_aux < melhor_fo){
						this.bm.carrega_better_mov(result_aux);
						melhor_fo = fo_aux;
						//System.out.println("Insert Intra: "+k);
					}
					if(rvnd) {
						break;
					}
					else
						k = k + 1;
					break;
				case 2:
					moviment = new TrocaIntra(s);
					result_aux.clear();
					result_aux = moviment.execute_mov(first_imp);
					if(!result_aux.isEmpty())
						fo_aux = result_aux.get(4);
					if(fo_aux < melhor_fo){
						this.bm.carrega_better_mov(result_aux);
						melhor_fo = fo_aux;
						//System.out.println("Troca Intra: "+k);
					}
					if(rvnd) {
						break;
					}
					else
						k = k + 1;
					break;
				case 3:
					moviment = new InsertIntra(s);
					result_aux.clear();
					result_aux = moviment.execute_mov(first_imp);
					if(!result_aux.isEmpty())
						fo_aux = result_aux.get(4);
					if(fo_aux < melhor_fo){
						this.bm.carrega_better_mov(result_aux);
						melhor_fo = fo_aux;
						//System.out.println("Insert Intra: "+k);
					}
					if(rvnd) {
						break;
					}
					else
						k = k + 1;
					break;
			}
		}
		
		int t = this.bm.getMovMakespan();
		if(t >=0)
			return this.bm.grava_movimento(s, t);
		else
			return s;
	}
	
	public int getMelhor_fo() {
		return this.melhor_fo;
	}

}
