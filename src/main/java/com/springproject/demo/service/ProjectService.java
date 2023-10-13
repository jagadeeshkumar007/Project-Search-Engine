package com.springproject.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springproject.demo.model.Project;
import com.springproject.demo.repo.ProjectRepo;

@Service
public class ProjectService {
	@Autowired
	private ProjectRepo prepo;
	
	public Project createRecord(Project project)
	{
		return prepo.save(project);
	}
	
	public List<Project> getAllRecords()
	{
		return (List<Project>) prepo.findAll();
	}
	public Optional<Project> findRecordById(long id)
	{
		return prepo.findById((int) id);
	}

}
