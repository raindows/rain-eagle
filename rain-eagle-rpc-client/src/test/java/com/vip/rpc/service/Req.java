package com.vip.rpc.service;

import java.io.Serializable;
import java.util.Map;

public class Req implements Serializable {

	private static final long serialVersionUID = -5070338292670675934L;

	private Map<String, String> headers;
	private Map<String, String> bodys;

	public Req() {
		super();
	}

	public Req(Map<String, String> headers, Map<String, String> bodys) {
		super();
		this.headers = headers;
		this.bodys = bodys;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public Map<String, String> getBodys() {
		return bodys;
	}

	public void setBodys(Map<String, String> bodys) {
		this.bodys = bodys;
	}

	@Override
	public String toString() {
		return "Req [headers=" + headers + ", bodys=" + bodys + "]";
	}

}