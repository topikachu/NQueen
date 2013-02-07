package com.hp.hplab.example;

import java.util.Arrays;

import org.junit.Test;

public class NQueenTest {

	@Test
	public void test()  {
		NQueen nQueen =new NQueen();
		int[] queens = nQueen.getSolution(8);
		System.out.println(Arrays.toString(queens));
	}

}
