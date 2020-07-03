package com.example.FirstAngularProject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.FirstAngularProject.model.UserDetail;


@Repository
public interface UserRepository extends JpaRepository<UserDetail, Long>{

	UserDetail findByusername(String username);
	
}
