package org.rain.eagle.core.arith;

import java.util.Arrays;

public class ArraySortArith {

	public static void quickSort(int[] array) {
		if (array == null) {
			return;
		}
		quickSort(array, 0, array.length - 1);
	}

	private static void quickSort(int[] array, int start, int end) {
		if (start >= end) {
			return;
		}
		int left = start, right = end, key = array[end];
		while (right > left) {
			while (array[left] <= key && left < right)
				left++;
			while (array[right] >= key && left < right)
				right--;
			swap(array, left, right);
		}
		if (array[left] >= array[end])
			swap(array, left, end);
		else
			left++;
		quickSort(array, start, left - 1);
		quickSort(array, left + 1, end);
	}

	private static void swap(int[] array, int i, int j) {
		int temp = array[j];
		array[j] = array[i];
		array[i] = temp;
		System.out.println(Arrays.toString(array));
	}

	public static void insertionSort(int[] array) {
		int length = array.length;
		for (int i = 1; i < length; i++) {
			for (int j = i; j > 0; j--) {
				if (array[j - 1] <= array[j]) {
					break;
				}
				swap(array, j - 1, j);
			}
		}
	}

}
