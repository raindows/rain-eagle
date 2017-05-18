package org.rain.eagle.core;

import java.util.Arrays;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		Integer[] a = { 1,2,3,4 };
		List<?> list = Arrays.asList(a);
		System.out.println(list.size());  // [I

		System.out.println(Integer.MAX_VALUE + 1 < Integer.MAX_VALUE);
	}
}
