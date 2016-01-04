package com.ctlovedove.joke.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.ctlovedove.joke.service.JokeTypeService;
@Controller("jokeTypeAction")
public class JokeTypeAction {
	@Resource
	private JokeTypeService jokeTypeService;

	public JokeTypeService getJokeTypeService() {
		return jokeTypeService;
	}

	public void setJokeTypeService(JokeTypeService jokeTypeService) {
		this.jokeTypeService = jokeTypeService;
	}
	
}
