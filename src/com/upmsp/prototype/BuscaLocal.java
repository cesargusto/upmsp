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

					solucao.print_solucao();//avaliação da solution

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
				int a = this.solucao.getMaq(i).getJob(j);
				this.solucao.getMaq(i).removeJobToMaq(j);
				for(int w = j+1;w < tamanho_maq;w++){
					this.solucao.getMaq(i).insertJobToMaq(w, a);

					solucao.print_solucao();//solution avaliation

					this.solucao.getMaq(i).removeJobToMaq(w);
				}
				this.solucao.getMaq(i).insertJobToMaq(j, a);
			}
		}	
	}
	
	public void remove_job_Maq_mspan(){
		//O JOB PODE SER INSERIDO EM UMA POSIÇÃO ALEATÓRIA INVEZ DO FINAL
		int sol_size = this.solucao.getSizeSol();
		int maq_mspan = 2; //get mkspan machine
		int machine_mkpan_size = this.solucao.getMaq(maq_mspan).getSizeMaq();
		
		for(int i = 0;i < machine_mkpan_size;i++){
			int job_pivot = this.solucao.getMaq(maq_mspan).getJob(i);
			this.solucao.getMaq(maq_mspan).removeJobToMaq(i);
			for(int j = 0;j < sol_size;j++){
				if(j != maq_mspan){
					this.solucao.getMaq(j).addJobToMaq(job_pivot);
					this.solucao.print_solucao();
					this.solucao.getMaq(j).removeLastJob();
				}
			}
			this.solucao.getMaq(maq_mspan).insertJobToMaq(i, job_pivot);
		}		
	}
	
	public void swap_job_ExtraMaq(){
		//SERÁ FEITO NA MAQUINA MAKESPAN COM AS DEMAIS
		int sol_size = this.solucao.getSizeSol();
		int maq_mspan = 0; //get mkspan machine
		int machine_mkpan_size = this.solucao.getMaq(maq_mspan).getSizeMaq();
		
		for(int i = 0;i < machine_mkpan_size;i++){
			int job_pivot = this.solucao.getMaq(maq_mspan).getJob(i);
			for(int j = 0;j < sol_size;j++){
				if(j != maq_mspan){
					for(int w = 0;w < this.solucao.getMaq(j).getSizeMaq();w++){
						int job_pivot_2 = this.solucao.getMaq(j).getJob(w);
						this.solucao.getMaq(maq_mspan).setJobToMaq(i, job_pivot_2);
						this.solucao.getMaq(j).setJobToMaq(w, job_pivot);
						
						this.solucao.print_solucao();
						
						this.solucao.getMaq(maq_mspan).setJobToMaq(i, job_pivot);
						this.solucao.getMaq(j).setJobToMaq(w, job_pivot_2);						
					}
				}
			}
		}
	}
	
	public void change_Maq(){
		
		int sol_size = this.solucao.getSizeSol();
		int maq_mspan = 1; //get mkspan machine
		int machine_mkpan_size = this.solucao.getMaq(maq_mspan).getSizeMaq();
		
		for(int i = 0;i < sol_size;i++){
			if(i != maq_mspan){
				this.solucao.swap_Machine(maq_mspan, i);
				this.solucao.print_solucao();
				this.solucao.swap_Machine(maq_mspan, i);
			}
		}	
	}
}