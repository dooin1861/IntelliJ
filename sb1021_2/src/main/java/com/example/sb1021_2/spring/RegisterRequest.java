package com.example.sb1021_2.spring;

public class RegisterRequest {

	private String email;
	private String password;
	private String confirmPassword;
	private String name;
	private Boolean agree;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isPasswordEqualToConfirmPassword() {
		return password.equals(confirmPassword);
	}

	public Boolean getAgree() { // agree 필드에 대한 getter 추가
		return agree;
	}

	public void setAgree(Boolean agree) { // agree 필드에 대한 setter 추가
		this.agree = agree;
	}
}
