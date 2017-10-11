package com.upmsp.prototype;

public class BuscaLocal {
    
    private Solucao solucao;
    
    public BuscaLocal(Solucao solucao){
    
        this.solucao = solucao;
    }
    
    public void troca_intra_Maq(){
        int tamanho_sol = solucao.getSizeSol();
        
        for(int i = 0;i < tamanho_sol;i++){
            int tamanho_maq = this.solucao.getMaq(i).getSizeMaq();
            for(int j = 0;j < tamanho_maq -1;j++){
                for(int w = j+1;w < tamanho_maq;w++){
                    
                    int a = this.solucao.getMaq(i).getJob(j);
                    
                    this.solucao.getMaq(i).setJobToMaq(j, this.solucao.getMaq(i).getJob(w));
                    this.solucao.getMaq(i).setJobToMaq(w, a);
                    
                    solucao.print_solucao();
                    
                    a = this.solucao.getMaq(i).getJob(j);
                    
                    this.solucao.getMaq(i).setJobToMaq(j, this.solucao.getMaq(i).getJob(w));
                    this.solucao.getMaq(i).setJobToMaq(w, a);
                    
                }
            }
        }
    
    }
    
    public void insert_intra_Maq() {
        int tamanho_sol = solucao.getSizeSol();
        
        for(int i = 0;i < tamanho_sol;i++){
            int tamanho_maq = this.solucao.getMaq(i).getSizeMaq();
            for(int j = 0;j < tamanho_maq -1;j++){
                for(int w = j+1;w < tamanho_maq;w++){
                    
                    int a = this.solucao.getMaq(i).getJob(j);
                    
                    this.solucao.getMaq(i).removeJobToMaq(j);
                    this.solucao.getMaq(i).insertJobToMaq(w, a);
                    
                    solucao.print_solucao();
                    
                    a = this.solucao.getMaq(i).getJob(j);
                    
                    this.solucao.getMaq(i).setJobToMaq(j, this.solucao.getMaq(i).getJob(w));
                    this.solucao.getMaq(i).setJobToMaq(w, a);
                    
                }
            }
        }	
    }
}