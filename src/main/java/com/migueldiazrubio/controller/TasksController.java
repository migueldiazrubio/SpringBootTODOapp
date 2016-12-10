package com.migueldiazrubio.controller;

import java.util.Date;

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
import com.migueldiazrubio.model.Task;
import com.migueldiazrubio.repository.ProjectDAO;
import com.migueldiazrubio.repository.TaskDAO;

@Controller
public class TasksController {
	
	@Autowired
	private TaskDAO taskDAO;
	
	@Autowired
	private ProjectDAO projectDAO;
	
	@RequestMapping("/tasks")
	public String allTasks(@RequestParam(value="project", required=false) Integer projectId, Model model) {

		if (projectId != null) {
			model.addAttribute("tasks", taskDAO.findByProject_Id(projectId));
			Project project = projectDAO.findById(projectId);
			model.addAttribute("project", project);
		} else {
			model.addAttribute("tasks", taskDAO.findAll());
		}
		
		model.addAttribute("task", new Task(""));
		
		return "tasks";
	}
	
	@PostMapping("/addTask")
	public String addTask(@RequestParam(value="projectId") Integer projectId, @ModelAttribute Task task) {
		Project project = projectDAO.findById(projectId);
		task.setProject(project);
		task.setDateCreated(new Date());
		task.setDateUpdated(new Date());
		task.setCompleted(false);
		taskDAO.save(task);
		return "redirect:tasks?project=" + projectId;
	}

	@RequestMapping("/completeTask")
	public String completeTask(@RequestParam("task") int taskId, @RequestParam(value="projectId", required=false) Integer projectId) {
		
		Task task = taskDAO.findById(taskId);
		taskDAO.delete(task);
		
		if (projectId != null) {
			return "redirect:tasks?project=" + projectId;
		} else {
			return "redirect:tasks";
		}
		
	}
	
	
}
