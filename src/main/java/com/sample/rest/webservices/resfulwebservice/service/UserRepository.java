package com.sample.rest.webservices.resfulwebservice.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.rest.webservices.resfulwebservice.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
