package com.adanac.demo.anno;

@Table(name = "USER")
public class UserDto {

	@Column(name = "ID")
	private String id;
	@Column(name = "USERNAME")
	private String userName;
	@Column(name = "SEX")
	private String sex;
	@Column(name = "AGE")
	private Integer age;
	@Column(name = "ADDRESS")
	private String address;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
