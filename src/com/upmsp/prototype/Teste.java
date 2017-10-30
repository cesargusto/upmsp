package com.upmsp.prototype;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Teste {
    
    public static void main(String[] args) {
        
    	//Maquina maq1 = new Maquina();
        //maq1.addJobToMaq(1);
        //maq1.addJobToMaq(6);
        //maq1.addJobToMaq(8);
        //maq1.addJobToMaq(3);
        
        //Maquina maq2 = new Maquina();
        //maq2.addJobToMaq(7);
        //maq2.addJobToMaq(0);
        
        //Maquina maq3 = new Maquina();
        //maq3.addJobToMaq(4);
        //maq3.addJobToMaq(2);
        //maq3.addJobToMaq(5);
        
        //Solucao solucao = new Solucao();
        //solucao.setMaqToSol(maq1);
        //solucao.setMaqToSol(maq2);
        //solucao.setMaqToSol(maq3);
        
        //solucao.print_solucao();
        
        //System.out.println("-------------------------------");
        
        //BuscaLocal bl = new BuscaLocal(solucao);
        //bl.change_Maq();
    	
		Random rnd = new Random();
		ArrayList<Integer> lista = new ArrayList<>();
		
		for(int i = 0;i < 25; i++){
			lista.add(rnd.nextInt(50));
		}
		
		//Collections.sort(lista);
		System.out.println(lista);
		Grasp grasp = new Grasp();
		System.out.println(grasp.execute_grasp(lista, 0.6));
    }
}
