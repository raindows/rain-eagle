package test;

import java.io.InputStream;

/*
 * 3.类在JVM中的工作原理
 要想使用一个Java类为自己工作，必须经过以下几个过程
 1）：类加载load：从字节码二进制文件——.class文件将类加载到内存，从而达到类的从硬盘上到内存上的一个迁移，
 所有的程序必须加载到内存才能工作。将内存中的class放到运行时数据区的方法区内，
 之后在堆区建立一个java.lang.Class对象，用来封装方法区的数据结构。这个时候就体现出了万事万物皆对象了，
 干什么事情都得有个对象。就是到了最底层究竟是鸡生蛋，还是蛋生鸡呢？类加载的最终产物就是堆中的一个java.lang.Class对象。
 2）：连接：连接又分为以下小步骤
 验证：出于安全性的考虑，验证内存中的字节码是否符合JVM的规范，类的结构规范、语义检查、字节码操作是否合法、
 这个是为了防止用户自己建立一个非法的XX.class文件就进行工作了，或者是JVM版本冲突的问题，
 比如在JDK6下面编译通过的class（其中包含注解特性的类），是不能在JDK1.4的JVM下运行的。
 准备：将类的静态变量进行分配内存空间、初始化默认值。（对象还没生成呢，所以这个时候没有实例变量什么事情）
 解析：把类的符号引用转为直接引用（保留）
 3）：类的初始化： 将类的静态变量赋予正确的初始值，这个初始值是开发者自己定义时赋予的初始值，而不是默认值。
 4.	类的主动使用与被动使用
 以下是视为主动使用一个类，其他情况均视为被动使用！
 1）：初学者最为常用的new一个类的实例对象（声明不叫主动使用）
 2）：对类的静态变量进行读取、赋值操作的。
 3）：直接调用类的静态方法。
 4）：反射调用一个类的方法。
 5）：初始化一个类的子类的时候，父类也相当于被程序主动调用了（如果调用子类的静态变量是从父类继承过来并没有复写的，
 那么也就相当于只用到了父类的东东，和子类无关，所以这个时候子类不需要进行类初始化）。
 6）：直接运行一个main函数入口的类。
 所有的JVM实现（不同的厂商有不同的实现，有人就说IBM的实现比Sun的要好……）在首次主动调用类和接口的时候才会初始化他们。
 */
/*
请问，接口类和实现接口的类一定是同一个类加载器吗？//不一定
父类和继承父类的子类一定是同一个类加载器吗？//不一定
如果他们之间有关系，请问如果我子类用一个类加载器，父类用另一个类加载器，那他们还能父子类关系吗//能
*/
public class ClassLoaderTest {
	public static void main(String[] args)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		// TODO Auto-generated method stub2
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// test1();
		test2();
	}

	private static void test2() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		ClassLoader myLoader = new MyClassLoader();
		ClassLoader myLoader1 = new MyClassLoader();
		Object obj = myLoader.loadClass("test.RuntimeDemo").newInstance();
		Object obj1=Class.forName("test.RuntimeDemo").newInstance();
		Object obj2 = myLoader1.loadClass("test.RuntimeDemo").newInstance();
		System.out.println(obj.getClass());
		System.out.println(obj1.getClass());
		System.out.println();
		System.out.println(obj.getClass().getClassLoader());
		System.out.println(obj1.getClass().getClassLoader());
		System.out.println(obj2.getClass().getClassLoader());
		System.out.println(test.RuntimeDemo.class.getClassLoader());
		System.out.println();
		System.out.println(obj instanceof test.RuntimeDemo);
		System.out.println(obj1 instanceof test.RuntimeDemo);
		System.out.println(obj2 instanceof RuntimeDemo);
		System.out.println("------------------------");
		System.out.println(obj.equals(obj1));
		System.out.println(obj.equals(obj2));
		System.out.println(obj1.equals(new RuntimeDemo()));
		System.out.println("------------------------");
		System.out.println(myLoader.getClass().getClassLoader());
		System.out.println(java.lang.ClassLoader.class.getClassLoader());
		System.out.println(myLoader instanceof java.lang.ClassLoader);
		
		System.err.println("\n################");
		myLoader=null;myLoader1=null;obj=null;obj1=null;obj2=null;
		System.gc();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		System.err.println("\n################");
		Object obj3 = new MyClassLoader().loadClass("test.RuntimeDemo");
		
	}

	private static void test1() {
		try {
			/**
			 * ClassLoader的关系：根加载器——》扩展类加载器——》应用类加载器——》用户自定义类加载器
			 * 加载类的过程是首先从根加载器开始加载、根加载器加载不了的，由扩展类加载器加载，
			 * 再加载不了的有应用加载器加载，应用加载器如果还加载不了就由自定义的加载器 （一定继承自java.lang.
			 * ClassLoader）加载、如果自定义的加载器还加载不了。
			 * 而且下面已经没有再特殊的类加载器了，就会抛出ClassNotFoundException，
			 * 表面上异常是类找不到，实际上是class加载失败，更不能创建该类的Class对象。
			 */
			// 根类加载器：bootstrap，由C++编写，所有Java程序无法获得。
			System.out.println(ClassLoaderTest.class.getClassLoader().getParent().getParent());
			// 扩展类加载器：由Java编写。
			System.out.println(ClassLoaderTest.class.getClassLoader().getParent());
			// 系统类、应用类加载器：由Java编写。
			System.out.println(ClassLoaderTest.class.getClassLoader());

			System.out.println(Class.forName("test.ClassLoaderTest").getClassLoader());// 系统类、应用类加载器：由Java编写
			System.out.println(Class.forName("java.lang.String").getClassLoader());// 根类加载器：bootstrap，由C++编写，所有Java程序无法获得
		} catch (ClassNotFoundException e) {
			System.out.println("类没有找到！");
		}
		System.out.println(ClassLoader.getSystemClassLoader());
		// System.out.println(Singleton.GetInstence().getClass().getClassLoader());
	}
}

class MyClassLoader extends ClassLoader {

	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		try {
			String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
			InputStream is = getClass().getResourceAsStream(fileName);
			if (is == null) {
				return super.loadClass(name);
			}
			byte[] b = new byte[is.available()];
			is.read(b);
			return defineClass(name, b, 0, b.length);
		} catch (Exception e) {
			throw new ClassNotFoundException(name);
		}
	}
	
	@Override
	protected void finalize() throws Throwable {
		System.out.println(this+" start to gc");
		super.finalize();
		System.out.println(this+" gc success");
	}

}
