package org.rain.eagle.core.arith;

import java.util.Arrays;

public class ArraySortArithTest {

	public static void main(String[] args) {
		int[] array = { 6, 3, 1, 8, 7, 2, 9, 5, 4 };
		ArraySortArith.quicksort(array);
		System.out.println(Arrays.toString(array));
	}

}
