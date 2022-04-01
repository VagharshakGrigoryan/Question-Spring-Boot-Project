package com.example.register.dto;

import com.example.register.model.Role;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.Arrays;

@Value
public class UserDTO {

	String username;
	String email;
	String firstName;
	String lastName;
	Role role;
	LocalDateTime lastActive;
	String picUrl;


	public UserDTO(String username, String email, String firstName, String lastName, Role role, LocalDateTime lastActive, String picUrl) {
		this.username = username;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.lastActive = lastActive;
		this.picUrl = picUrl;
	}

	private String redact(String emailFull) {
		
		StringBuilder sb = new StringBuilder();
		String[] parts = emailFull.split("@");
		char[] chars = new char[parts[0].length()];
		Arrays.fill(chars, '*');
		sb.append(chars);
		
		switch (chars.length) {
		case 0:
		case 1: break;
		case 2: {sb.setCharAt(0, parts[0].charAt(0)); break;}
		default: {
			int end = parts[0].length()-1;
			sb.setCharAt(0, parts[0].charAt(0)); 
			sb.setCharAt(end, parts[0].charAt(end));
			break;
			}
		}
		sb.append('@').append(parts[1]);
		return sb.toString();
	}

}