package org.rain.eagle.core.arith;

import java.util.Arrays;

public class ArraysArith {

	/**
	 * Given a sorted array, remove the duplicates in place such that each
	 * element appear only once and return the new length. Do not allocate extra
	 * space for another array, you must do this in place with constant memory.
	 * For example, Given input array A = [1,1,2], Your function should return
	 * length = 2, and A is now [1,2].
	 * 
	 * 时间复杂度O(n)，空间复杂度O(1)
	 * 
	 * @param arys
	 * @return
	 */
	public static int removeDuplicates(int[] arys) {
		int length = arys.length;
		if (length == 0)
			return 0;
		int index = 1;
		for (int i = 1; i < length; i++) {
			if (arys[i] != arys[index - 1]) {
				arys[index++] = arys[i];
			}
		}
		int[] result = new int[index];
		System.arraycopy(arys, 0, result, 0, index);
		System.out.println(Arrays.toString(result));
		return index;
	}
}
