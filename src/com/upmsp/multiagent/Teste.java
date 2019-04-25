package com.upmsp.multiagent;

import java.util.ArrayList;

public class Teste {

	public static void main(String[] args) {
		ArrayList<Integer>list = new ArrayList<Integer>();
		list.add(1);
		list.add(10);
		list.add(3);
		list.add(17);
		list.add(2);
		list.add(19);
		list.add(5);
		list.add(11);
		list.add(4);
		list.add(15);
		
		System.out.println(list);
		int soma = 0;
		for (Integer integer : list) {
			soma = soma + list.get(integer);
		}
		System.out.println(soma);

	}

}
