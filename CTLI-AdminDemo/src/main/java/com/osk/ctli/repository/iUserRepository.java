package com.osk.ctli.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.osk.ctli.model.iUser;

public interface iUserRepository extends JpaRepository<iUser, Integer> {

}
