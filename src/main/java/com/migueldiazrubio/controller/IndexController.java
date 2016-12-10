package com.migueldiazrubio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping("/")
	public String index() {
		
		// Create dummy data
		/*
		User newUser = new User("admin", "admin", "admin@admin.com");
		userDAO.save(newUser);
		
		Project newProject = new Project("Project 1");
		newProject.setUser(newUser);
		projectDAO.save(newProject);
		
		Task newTask = new Task("Task 1");
		newTask.setProject(newProject);
		taskDAO.save(newTask);
		*/
		
		return "redirect:tasks";
	}

}
