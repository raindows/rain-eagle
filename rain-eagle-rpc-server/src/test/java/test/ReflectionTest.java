package test;

import sun.reflect.Reflection;

public class ReflectionTest {

	public static void main(String[] args) {
		SunReflect sunReflect = new SunReflect();
		sunReflect.run();
	}
}

class SunReflect {

	public void run() {
		print();
	}

	private void print() {
		System.out.println(Reflection.getCallerClass());
	}

}
