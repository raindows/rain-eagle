package test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.UUID;

public class Clazz {

	private static ThreadLocal<ClazzInit> localClazz = new ThreadLocal<ClazzInit>();
	
	private static ThreadLocal<ClazzInit> localClazz1 = new ThreadLocal<ClazzInit>();

	public static void main(String[] args) throws Exception {
		// Class.forName("test.clazzInit");
		Class.forName("test.ClazzInit", false, Thread.currentThread().getContextClassLoader());
		ClazzInit clazzInit=new ClazzInit();
		ClazzInit clazzInit1=new ClazzInit();
		localClazz.set(clazzInit);
		System.out.printf("localClazz: %s\n",localClazz.get());
		System.out.println(clazzInit.getClass().getName());
		localClazz1.set(clazzInit1);
		
		System.out.printf("localClazz1: %s\n",localClazz1.get());
		System.out.printf("localClazz: %s\n",localClazz.get());
		
		

		HashMap<String, ClazzInit> map = new HashMap<>();
		
		LinkedList<String> linkedList=new LinkedList<>();
		
		LinkedHashMap<String, ClazzInit> linkedHashMap=new LinkedHashMap<>();
		
		//testInit();
	}

	private static void testInit() {
		System.out.println("=======================");
		ClazzInit clazzInit = new ClazzInit();
		System.out.println(clazzInit.uuidFinal);
		System.out.println(clazzInit.uuidFinal);
		System.out.println(clazzInit.uuidFinal.equals(clazzInit.uuidFinal));

		System.out.println(clazzInit.uuid);
		System.out.println(clazzInit.uuid);
		System.out.println(clazzInit.uuid.equals(clazzInit.uuid));
	}
}

class ClazzInit {

	public final String uuidFinal = nextUUID();

	public String uuid = nextUUID();

	static {
		System.out.println("static init");
	}

	public ClazzInit() {
		System.out.println("constructor init");
	}

	private String nextUUID() {
		System.out.println("nextUUID......");
		UUID id = UUID.randomUUID();
		return id.toString();
	}

	@Override
	public String toString() {
		return "ClazzInit [uuidFinal=" + uuidFinal + ", uuid=" + uuid + "]";
	}
	
	

}
