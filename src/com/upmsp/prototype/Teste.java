package com.upmsp.prototype;

public class Teste {
    
    public static void main(String[] args) {
        Maquina maq1 = new Maquina();
        maq1.addJobToMaq(1);
        maq1.addJobToMaq(6);
        maq1.addJobToMaq(8);
        maq1.addJobToMaq(3);
        
        Maquina maq2 = new Maquina();
        maq2.addJobToMaq(7);
        maq2.addJobToMaq(0);
        
        Maquina maq3 = new Maquina();
        maq3.addJobToMaq(4);
        maq3.addJobToMaq(2);
        maq3.addJobToMaq(5);
        
        Solucao solucao = new Solucao();
        solucao.setMaqToSol(maq1);
        solucao.setMaqToSol(maq2);
        solucao.setMaqToSol(maq3);
        
        solucao.print_solucao();
        
        System.out.println("-------------------------------");
        
        BuscaLocal bl = new BuscaLocal(solucao);
        bl.troca_intra_Maq();
        
    }
}
