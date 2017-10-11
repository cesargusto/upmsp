package com.upmsp.structure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

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
			int tam_mq = this.solucao.get(i).getTamMaq();
			for(int j = 0;j < tam_mq;j++){
				int elemento = this.solucao.get(i).getJob(j);
				solCp.getMaq(i).setJobMaq(elemento);
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
				this.solucao.get(j).setJobMaq(candidatos.get(k));
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
	
	public ArrayList<Machine> getSolucao() {
		return solucao;
	}
	
	public Machine getMaq(int i) {
		return solucao.get(i);
	}
	public void setMaqSolucao(Machine maq) {
		this.solucao.add(maq);
	}
	
	public void imprimeSolucao(){
		for(int x = 0;x < arquivo.getN_maqs();x++){
			System.out.printf("Maq-%d\tTempo: %d\n",x,this.solucao.get(x).tempoMaq(arquivo, x));
			for(int y = 0;y < this.getMaq(x).getTamMaq();y++){
				System.out.printf("%d ",this.solucao.get(x).getJob(y));
			}
			//System.out.printf("\nTempo:\t%d \n",this.solucao.get(x).tempoMaq(arquivo, x));
			System.out.println();
		}
		System.out.println("Makespan .........: "+this.makespan());
		System.out.println();
	}
	

}
