package ru.leonidivankin.photovkapp.model.entity;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class User {
	String avatarUrl;
	String login;
	String id;
	String reposUrl;

	List<Repository> repos = new ArrayList<>();

	public List<Repository> getRepos() {
		return repos;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}


	public String getLogin() {
		return login;
	}

	public String getId() {
		return id;
	}

	public void setRepos(List<Repository> repos) {
		this.repos = repos;
	}

	public String getReposUrl() {
		return reposUrl;
	}
}
