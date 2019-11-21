package simulated_mov;

import java.util.ArrayList;

import com.upmsp.structure.Solution;

public abstract class Moviment {

	private ArrayList<Integer> times;
	private ArrayList<Integer> better_mov;
	protected Solution s;
	
	public Moviment(Solution s) {
		this.s = s;
		this.times = new ArrayList<Integer>();
		this.better_mov = new ArrayList<Integer>();
	}

	
	public abstract ArrayList<Integer> execute_mov(Boolean first_imp);
	
	/**
	 * Aplica a melhor movimentação encontrada:
	 * 
	 * @param mov     lista de movimento
	 * @param solução s
	 */
	
	
	public void update_times(int M, int value) {
		this.times.set(M, value);
	}
	
	// Execução da cópia dos tempos para a lista auxiliar [tempos]
	public ArrayList<Integer> duplicate_times(ArrayList<Integer> Tempos) {
		ArrayList<Integer> t = new ArrayList<Integer>();
	
		for (int i = 0; i < Tempos.size(); i++) {
			t.add(Tempos.get(i));
		}
		return t;
	}


	public ArrayList<Integer> getBetter_mov() {
		return better_mov;
	}

	public void setBetter_mov(ArrayList<Integer> better_mov) {
		this.better_mov = better_mov;
	}
	
	public void better_mov_final(int M, int pM, int m, int pm, int makespan, int movimento) {		
		better_mov.clear();
		better_mov.add(M);
		better_mov.add(pM);
		better_mov.add(m);
		better_mov.add(pm);
		better_mov.add(makespan);
		better_mov.add(movimento);
	}
	
}
