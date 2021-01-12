package com.odysseycorp.homer.repositories;

import com.odysseycorp.homer.models.Tracing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TracingRepository extends JpaRepository<Tracing, String> {
}
