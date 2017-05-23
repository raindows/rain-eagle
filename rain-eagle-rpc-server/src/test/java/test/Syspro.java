package test;

import java.util.Map;

public class Syspro {

	public static void main(String[] args) {
		java.util.Properties properties=System.getProperties();
		
		for(java.util.Map.Entry<Object,Object> entry:properties.entrySet()){
			System.out.printf("%s\t%s\n",entry.getKey(),entry.getValue());
		}
		
		System.out.println("========");
		Map<String,String> map=System.getenv();
		for(Map.Entry<String, String> entry:map.entrySet()){
			System.out.printf("%s\t%s\n",entry.getKey(),entry.getValue());
		}

	}

}
