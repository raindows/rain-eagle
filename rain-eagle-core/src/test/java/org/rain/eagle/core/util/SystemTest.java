package org.rain.eagle.core.util;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

public class SystemTest {
	
	public static void main(String[] args) {

		Map<String, String> envs = System.getenv();
		System.out.println("********************");
		System.out.println(envs.size());
		for (Entry<String, String> entry : envs.entrySet()) {
			System.out.printf("%s : %s\n", entry.getKey(), entry.getValue());
		}

		Properties properties = System.getProperties();
		System.out.println("\n********************");
		System.out.println(properties.size());
		for (Entry<Object, Object> entry : properties.entrySet()) {
			System.out.printf("%s : %s\n", entry.getKey(), entry.getValue());
		}

	}
}
