package com.arb_tech.vo;

import java.util.Map;

public class ProjectVO {
	private String name_token;
	private Map<String, String> product_mapping;

	public String getName_token() {
		return name_token;
	}

	public void setName_token(String name_token) {
		this.name_token = name_token;
	}

	public Map<String, String> getProduct_mapping() {
		return product_mapping;
	}

	public void setProduct_mapping(Map<String, String> product_mapping) {
		this.product_mapping = product_mapping;
	}

}
