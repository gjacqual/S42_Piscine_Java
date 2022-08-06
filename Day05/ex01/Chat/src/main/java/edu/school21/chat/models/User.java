package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class User {
	private Integer id;
	
	private String login;
	
	private String password;
	
	private List<Chatroom> createdRooms;
	
	private List<Chatroom> userRooms;
	
	public User(Integer id, String login, String password, List<Chatroom> createdRooms, List<Chatroom> userRooms) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.createdRooms = createdRooms;
		this.userRooms = userRooms;
	}

	public Integer getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public List<Chatroom> getCreatedRooms() {
		return createdRooms;
	}

	public List<Chatroom> getUserRooms() {
		return userRooms;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setCreatedRooms(List<Chatroom> createdRooms) {
		this.createdRooms = createdRooms;
	}

	public void setUserRooms(List<Chatroom> userRooms) {
		this.userRooms = userRooms;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return getId().equals(user.getId()) && getLogin().equals(user.getLogin()) && getPassword().equals(user.getPassword()) && getCreatedRooms().equals(user.getCreatedRooms()) && getUserRooms().equals(user.getUserRooms());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getLogin(), getPassword(), getCreatedRooms(), getUserRooms());
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", login='" + login + '\'' +
				", password='" + password + '\'' +
				", createdRooms=" + createdRooms +
				", userRooms=" + userRooms +
				'}';
	}
}