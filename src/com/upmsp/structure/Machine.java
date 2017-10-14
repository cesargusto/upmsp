package com.upmsp.structure;

import java.util.ArrayList;
import java.util.Collections;

public class Machine {
	private ArrayList machine;
	
	public Machine(){
		this.machine = new ArrayList<Integer>();
	}

	public int tempoMaq(Instance arq, int nummaq){
		int tempo_total = 0;
		int tempo_prep = 0;
		int tempo_exec = 0;
		for(int t = 0;t < this.machine.size();t++){
			if(t!=0){
				tempo_prep = arq.getT_prep(nummaq, this.getJob(t - 1), this.getJob(t));
			}
			tempo_exec = arq.getT_exec(nummaq, this.getJob(t));
			tempo_total = tempo_total + tempo_prep + tempo_exec;
		}
		return tempo_total;
	}
	
	public int getSizeMaq() {
		return machine.size();
	}
	
	public ArrayList getMaquina() {
		return machine;
	}

	public int getJob(int i){
		return (Integer)this.machine.get(i);
	}

    public void addJobToMaq(Integer job) {
        this.machine.add(job);
    }
	
	public void setJobMaq(int pos, int job) {
		this.machine.add(pos, job);
	}
	
    public void setJobToMaq(int i, Integer job) {
        this.machine.set(i, job);
    }
    
    public void insertJobToMaq(int i, Integer job) {
        this.machine.add(i, job);
    }
	
    public void removeJobToMaq(int pos){
		this.machine.remove(pos);
	}
    public void removeLastJob() {
        this.machine.remove(this.machine.size() - 1);
    }

	public void trocaJob(int i, int j){
		Collections.swap(this.machine, i, j);
	}
	

}
