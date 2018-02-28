package org.rain.eagle.core.arith;

import java.util.Arrays;

public class ArraySortArithTest {

	public static void main(String[] args) {
		int[] array = { 6, 3, 1, 8, 7, 2, 9, 5, 4 };
		//testQuickSort(array);
		testInsertionSort(array);
	}

	private static void testInsertionSort(int[] array) {
		int[] testAry = array;
		ArraySortArith.insertionSort(testAry);
		System.out.println(Arrays.toString(testAry));
	}

	private static void testQuickSort(int[] array) {
		int[] testAry = array;
		ArraySortArith.quickSort(testAry);
		System.out.println(Arrays.toString(testAry));
	}

}
