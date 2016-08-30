package com.adanac.demo.anno;

public class Main {
	public static void main(String[] args) {

		UserDto bean = new UserDto();
		String sql = ReflectUtil.createTable(bean.getClass());
		System.out.println(sql);
	}
}
