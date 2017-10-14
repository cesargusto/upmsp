package com.upmsp.structure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import com.upmsp.localsearch.LocalSearch;

public class Solution implements Cloneable{
	
	private Instance arquivo;
	private ArrayList<Machine> solucao;
	
	public Solution(Instance arq){
		this.arquivo = arq;
		this.solucao = new ArrayList<>();
	}
	public Solution(Machine maq){
		this.solucao = new ArrayList<>();
		this.solucao.add(maq);
	}
	
	public Solution(){
		this.solucao = new ArrayList<>();
	}
	
	@Override
	public Solution clone() throws CloneNotSupportedException {
		Solution solCp = new Solution(arquivo);
		int tam = solucao.size();
		for(int i = 0;i < tam;i++){
			solCp.solucao.add(new Machine());
			int tam_mq = this.solucao.get(i).getSizeMaq();
			for(int j = 0;j < tam_mq;j++){
				int elemento = this.solucao.get(i).getJob(j);
				solCp.getMaq(i).addJobToMaq(elemento);
			}
		}
		return solCp;
	}
	
	public void ConstroiSolution(){
		ArrayList<Integer> candidatos = new ArrayList<>(arquivo.getN_jobs());
		for(int i = 0;i < arquivo.getN_jobs();i++){
			candidatos.add(i);
		}
		for(int x = 0;x < arquivo.getN_maqs();x++){
			this.setMaqSolucao(new Machine());
		}
		
		int k = 0;
		while(k < arquivo.getN_jobs()){
			for(int j = 0;j < arquivo.getN_maqs();j++){
				this.solucao.get(j).addJobToMaq(candidatos.get(k));
				k++;
				if(k >= arquivo.getN_jobs())
					break;
			} 
		}
	}
	
	public int makespan(){
		int mspan = 0;
		ArrayList<Integer>mspans = new ArrayList<>(arquivo.getN_maqs());
		for(int t = 0;t < arquivo.getN_maqs();t++){
			mspans.add(this.getMaq(t).tempoMaq(arquivo, t));
		}
		mspan = Collections.max(mspans);
		return mspan;
	}
	
	public ArrayList<Integer> maior_menor(){
		ArrayList<Integer> maior_menor = new ArrayList<>();
		int indice_menor = -1;
		int indice_maior = -1;
		int valor_menor = Integer.MAX_VALUE;
		int valor_maior = Integer.MIN_VALUE;
		int aux = 0;
		
		for(int i = 0;i < arquivo.getN_maqs();i++){
			aux = this.getMaq(i).tempoMaq(arquivo, i);
			if(aux < valor_menor){
				valor_menor = aux;
				indice_menor = i;
				if(valor_menor > valor_maior){
					valor_maior = valor_menor;
					indice_maior = i;
					}
			}
			if(aux > valor_maior){
				valor_maior = aux;
				indice_maior = i;
				if(valor_maior < valor_menor){
					valor_menor = valor_maior;
					indice_maior = i;
				}
			}
		}
		maior_menor.add(indice_menor);
		maior_menor.add(valor_menor);
		maior_menor.add(indice_maior);
		maior_menor.add(valor_maior);
		
		return maior_menor;
	}
	
	
	public ArrayList<Machine> getSolucao() {
		return solucao;
	}
	
	public int getSizeSol(){
		return this.solucao.size();
	}
	
	public Machine getMaq(int i) {
		return solucao.get(i);
	}
	public void setMaqSolucao(Machine maq) {
		this.solucao.add(maq);
	}
	
    public void swap_Machine(int i, int j){
    	Collections.swap(this.solucao, i, j);
    }
	
	public void print_solution(){
		for(int x = 0;x < arquivo.getN_maqs();x++){
			System.out.printf("Maq-%d\tTempo: %d\n",x,this.solucao.get(x).tempoMaq(arquivo, x));
			for(int y = 0;y < this.getMaq(x).getSizeMaq();y++){
				System.out.printf("%d ",this.solucao.get(x).getJob(y));
			}
			//System.out.printf("\nTempo:\t%d \n",this.solucao.get(x).tempoMaq(arquivo, x));
			System.out.println();
		}
		System.out.println("Makespan .........: "+this.makespan());
		System.out.println();
	}
	

}
