package org.rain.eagle.core.arith;

import java.util.Arrays;

public class ArraySortArith {

	public static void quicksort(int[] array) {
		if (array == null) {
			return;
		}
		quciksort(array, 0, array.length - 1);
	}

	private static void quciksort(int[] array, int start, int end) {
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
		quciksort(array, start, left - 1);
		quciksort(array, left + 1, end);
	}

	private static void swap(int[] array, int i, int j) {
		int temp = array[j];
		array[j] = array[i];
		array[i] = temp;
		System.out.println(Arrays.toString(array));
	}

}
