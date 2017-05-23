package test;

import java.util.ArrayList;
import java.util.List;

/**
 * 运行时常量池溢出 java.lang.OutOfMemoryError: PermGen space
 * 
 * VM Args:-XX:PermSize=10M -XX:MaxPermSize=10M
 * 
 * @author xiaoyu.wang
 */
public class RuntimeConstantPoolOOM {
	
	private static final String a="计算机软件";

	public static void main(String[] args) {
		internTest();
		//test();
	}

	/*private static void test() {
		// 使用List保持着常量池的引用，避免FULL GC回收常量池行为
		List<String> list = new ArrayList<String>();
		// 10MB的PermSize在integer范围内足够产生OOM了
		int i = 0;
		while (true) {
			list.add(String.valueOf(i++).intern());
		}
	}*/

	private static void internTest() {
		String str1 = new StringBuilder("计算机").append("软件").toString();
		System.out.println(str1.intern() == str1);
		System.out.println(str1.intern().equals(str1) );

		String str2 = new StringBuilder("ja").append("va").toString();
		System.out.println(str2.intern() == str2);
	}
}
