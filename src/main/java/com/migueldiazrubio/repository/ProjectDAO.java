package com.migueldiazrubio.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.migueldiazrubio.model.Project;

@Transactional
public interface ProjectDAO extends CrudRepository<Project, Long> {
	
	public List<Project> findByUser_UserName(String userName);
	public Project findById(int projectId);

}