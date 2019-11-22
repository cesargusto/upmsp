package simulated_mov;

import java.util.ArrayList;

import com.upmsp.structure.Solution;

public class Better_mov {
	
	private ArrayList<Integer> better_mov;
	
	public Better_mov() {
		this.better_mov = new ArrayList<Integer>(5);
	}
	
	public void better_mov(int M, int i, int j) {
		clear_better_mov();
		better_mov.add(M);
		better_mov.add(i);
		better_mov.add(j);
	}
	public void better_mov(int M, int pM, int m, int pm) {
		clear_better_mov();
		better_mov.add(M);
		better_mov.add(pM);
		better_mov.add(m);
		better_mov.add(pm);
	}
	
	public void better_mov_final(int M, int pM, int m, int pm, int makespan, int movimento) {
		if(!better_mov.isEmpty()) {
			if(makespan < better_mov.get(4)) {
				clear_better_mov();
				better_mov.add(M);
				better_mov.add(pM);
				better_mov.add(m);
				better_mov.add(pm);
				better_mov.add(makespan);
				better_mov.add(movimento);
			}
		}else {
			better_mov.add(M);
			better_mov.add(pM);
			better_mov.add(m);
			better_mov.add(pm);
			better_mov.add(makespan);
			better_mov.add(movimento);
		}
	}
	
	public void carrega_better_mov(ArrayList<Integer> resultado) {
		if(!better_mov.isEmpty()) {
			if(resultado.get(4) < better_mov.get(4)) {
				clear_better_mov();
				for(int i = 0;i < resultado.size();i++) {
					better_mov.add(resultado.get(i));
				}
			}
		}else {
			for(int i = 0;i < resultado.size();i++) {
				better_mov.add(resultado.get(i));
			}
		}
		
	}
	
	public void clear_better_mov() {
		if(!better_mov.isEmpty())
			better_mov.clear();
	}
	
	public boolean better_mov_vazio() {
		return better_mov.isEmpty();
	}
	
	public ArrayList<Integer> getBetter_mov() {
		return better_mov;
	}
	
	public int getMakespan() {
		return better_mov.get(4);
	}
	
	public int getMovMakespan() {
		if(!better_mov.isEmpty())
			return better_mov.get(5);
		else
			return -1;
	}
	
	public Solution grava_movimento(Solution s, int movimento) {
		switch(movimento) {
			case 0:
				int job = s.getMaq(this.better_mov.get(0)).getJob(this.better_mov.get(1)); 
				s.getMaq(this.better_mov.get(0)).setJobMaq(this.better_mov.get(2)+1, job);
				s.getMaq(this.better_mov.get(0)).removeJobToMaq(this.better_mov.get(1));
				clear_better_mov();
				break;
				
			case 1://grava insert extra
				job = s.getMaq(this.better_mov.get(0)).getJob(this.better_mov.get(1)); 
				s.getMaq(this.better_mov.get(0)).removeJobToMaq(this.better_mov.get(1));
				s.getMaq(this.better_mov.get(2)).setJobMaq(this.better_mov.get(3), job);
				clear_better_mov();
	
				break;
			case 2://grava insert intra
				s.getMaq(this.better_mov.get(0)).trocaJob(this.better_mov.get(1), this.better_mov.get(2));
				clear_better_mov();
	
				break;
				
			case 3://grava troca extra
				int job_1 = s.getMaq(this.better_mov.get(0)).getJob(this.better_mov.get(1));
				int job_2 = s.getMaq(this.better_mov.get(2)).getJob(this.better_mov.get(3));
				s.getMaq(this.better_mov.get(0)).setJobToMaq(this.better_mov.get(1), job_2);
				s.getMaq(this.better_mov.get(2)).setJobToMaq(this.better_mov.get(3), job_1);
				clear_better_mov();
	
				break;
		}
		return s;
	}
}
