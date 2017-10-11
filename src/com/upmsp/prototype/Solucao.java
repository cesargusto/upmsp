package com.upmsp.prototype;

import java.util.ArrayList;

public class Solucao {
    
    //private Maquina maquina;
    private ArrayList<Maquina> solucao;
    
    public Solucao(){
    
        this.solucao = new ArrayList<>();
    }


    public ArrayList<Maquina> getSolucao() {
        return solucao;
    }

    public void setMaqToSol(Maquina maq) {
        this.solucao.add(maq);
    }
    
    public int getSizeSol(){
        return this.solucao.size();
    }
    
    public Maquina getMaq(int i){
        return this.solucao.get(i);
    }


    public void print_solucao(){
    
        for(int i = 0;i < solucao.size();i++){
            for(int j = 0;j < solucao.get(i).getMaquina().size();j++){
                System.out.printf("%d ",solucao.get(i).getMaquina().get(j));
            }
            System.out.printf("\n");
        }
        System.out.printf("\n");
    }
    
}  