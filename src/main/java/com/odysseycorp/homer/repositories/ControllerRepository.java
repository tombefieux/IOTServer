package com.odysseycorp.homer.repositories;

import com.odysseycorp.homer.models.Controller;
import com.odysseycorp.homer.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ControllerRepository extends JpaRepository<Controller, Integer> {
}
