package test;

/*
 totalMemory() ： 返回 Java 虚拟机中的内存总量。
 maxMemory() ：      返回 Java 虚拟机试图使用的最大内存量。
 freeMemory()  ： 返回 Java 虚拟机中的空闲内存量。
   这是API的解释。
 */
public class RuntimeDemo {

	private String a;
	
	private static byte[] b=new byte[2*1024*1024];
	static {
		System.out.println("RuntimeDemo static ...");
	}

	public static void main(String[] args) throws Exception {
		// 构造方法被私有化了。
		Runtime myRun = Runtime.getRuntime();
		System.out.println("Java 虚拟机试图使用的最大内存量: " + myRun.maxMemory() / 1024 / 1024);
		System.out.println("Java 虚拟机中的内存总量: " + myRun.totalMemory() / 1024 / 1024);
		System.out.println("Java 虚拟机中的空闲内存量: " + myRun.freeMemory() / 1024 / 1024);
		byte[] bigSize = null;
		long start = System.currentTimeMillis();
		System.out.println("*****************浪费内存中******************");
		try {
			for (int j = 1; j <= 5; j++) {
				bigSize = new byte[j * 1024 * 1024];
				System.out.println("Java 虚拟机试图使用的最大内存量: " + myRun.maxMemory() / 1024 / 1024);
				System.out.println("Java 虚拟机中的内存总量: " + myRun.totalMemory() / 1024 / 1024);
				System.out.println("Java 虚拟机中的空闲内存量: " + myRun.freeMemory() / 1024 / 1024);
				System.out.println("=====================================\n");
				// Thread.sleep(j*10000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// bigSize=null;
		long end = System.currentTimeMillis();
		System.out.println("执行此程序总共花费了" + (end - start) + "毫秒");
		System.out.println("Java 虚拟机试图使用的最大内存量: " + myRun.maxMemory() / 1024 / 1024);
		System.out.println("Java 虚拟机中的内存总量: " + myRun.totalMemory() / 1024 / 1024);
		System.out.println("Java 虚拟机中的空闲内存量: " + myRun.freeMemory() / 1024 / 1024);
		myRun.gc();
		System.out.println("****************清理垃圾后*********************");
		System.out.println("Java 虚拟机试图使用的最大内存量: " + myRun.maxMemory() / 1024 / 1024);
		System.out.println("Java 虚拟机中的内存总量: " + myRun.totalMemory() / 1024 / 1024);
		System.out.println("Java 虚拟机中的空闲内存量: " + myRun.freeMemory() / 1024 / 1024);

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a == null) ? 0 : a.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RuntimeDemo other = (RuntimeDemo) obj;
		if (a == null) {
			if (other.a != null)
				return false;
		} else if (!a.equals(other.a))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RuntimeDemo [a=" + a + "]";
	}

}
