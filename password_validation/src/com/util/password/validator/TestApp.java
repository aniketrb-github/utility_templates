package com.util.password.validator;

import java.util.Scanner;

public class TestApp {

	/**
		^                 # start-of-string
		(?=.*[0-9])       # a digit must occur at least once
		(?=.*[a-z])       # a lower case letter must occur at least once
		(?=.*[A-Z])       # an upper case letter must occur at least once
		(?=.*[@#$%^&+=])  # a special character must occur at least once
		(?=\\S+$)         # no whitespace allowed in the entire string
		.{8,}             # anything, at least eight places though
		$                 # end-of-string
		
	 */
	
	public static void main(String[] args) {
		String pwdValidatorRegExp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
		Scanner in = new Scanner(System.in);
		
		System.out.println("Enter you Password : ");
		String userPwd = in.next();
		System.out.println(userPwd.matches(pwdValidatorRegExp));
		in.close();

	}

}
