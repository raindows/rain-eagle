package com.vip.rpc.service;

import java.io.Serializable;

public class Resp implements Serializable {

	private static final long serialVersionUID = 8156302719046611516L;

	private String code;
	private String desc;

	public Resp() {
		super();
	}

	public Resp(String code, String desc) {
		super();
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "Resp [code=" + code + ", desc=" + desc + "]";
	}

}
