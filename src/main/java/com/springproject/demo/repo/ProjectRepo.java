package com.springproject.demo.repo;

import org.springframework.data.repository.CrudRepository;

import com.springproject.demo.model.Project;

public interface ProjectRepo extends CrudRepository<Project, Integer> {

}
