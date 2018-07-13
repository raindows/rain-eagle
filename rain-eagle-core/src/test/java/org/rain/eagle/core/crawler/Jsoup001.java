package org.rain.eagle.core.crawler;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * http://www.open-open.com/jsoup/load-document-from-url.htm
 * 
 * @author xiaoyu.wang
 *
 */
public class Jsoup001 {

	public static void main(String[] args) throws IOException {
		Document doc = Jsoup.connect("http://www.vip.com").get();
		String title = doc.title();
		System.out.println(title);
		System.out.println(doc.toString());
	}

}
