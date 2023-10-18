package com.springproject.demo.repo;

import org.springframework.data.repository.CrudRepository;

import com.springproject.demo.model.AdminLogin;

public interface AdminRepo extends CrudRepository<AdminLogin, Integer>{
	AdminLogin findByUserid(String userid);
}
