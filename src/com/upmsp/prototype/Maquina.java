package com.upmsp.prototype;

import java.util.ArrayList;

public class Maquina {
    
    private ArrayList<Integer> maquina;
    
    public Maquina(){
    
        this.maquina = new ArrayList<>();
    }

    public ArrayList<Integer> getMaquina() {
        return maquina;
    }

    public int getSizeMaq(){
        return this.maquina.size();
    }
    
    public int getJob(int i){
        return this.maquina.get(i);
    }
        
    public void addJobToMaq(Integer job) {
        this.maquina.add(job);
    }
    
    public void setJobToMaq(int i, Integer job) {
        this.maquina.set(i, job);
    }
    
    public void removeJobToMaq(int i) {
        this.maquina.remove(i);
    }
    
    public void insertJobToMaq(int i, Integer job) {
        this.maquina.add(i, job);
    }
    
}