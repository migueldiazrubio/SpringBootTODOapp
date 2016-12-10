package com.migueldiazrubio.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.migueldiazrubio.model.Task;

@Transactional
public interface TaskDAO extends CrudRepository<Task, Long> {
	
	public Task findById(int projectId);
	public List<Task> findByProject_Id(int projectId);

}
