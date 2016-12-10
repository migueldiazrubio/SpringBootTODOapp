package com.migueldiazrubio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.migueldiazrubio.model.Project;
import com.migueldiazrubio.model.User;
import com.migueldiazrubio.repository.ProjectDAO;
import com.migueldiazrubio.repository.UserDAO;

@Controller
public class ProjectsController {
	
	@Autowired
	private ProjectDAO projectDAO;

	@Autowired
	private UserDAO userDAO;

	@RequestMapping("/projects")
	public String allProjects(Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		
		model.addAttribute("projects", projectDAO.findByUser_UserName(userName));
		model.addAttribute("project", new Project(""));
		
		return "projects";
	}

	@PostMapping("/addProject")
	public String addTask(@ModelAttribute Project project) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    User currentUser = userDAO.findByUserName(auth.getName());
	    project.setUser(currentUser);
		projectDAO.save(project);
		
		return "redirect:projects";
	}
	
	@RequestMapping("/deleteProject")
	public String deleteProject(@RequestParam("project") int projectId) {
		
		Project project = projectDAO.findById(projectId);
		projectDAO.delete(project);
		
		return "redirect:projects";
	}
	
	
}
