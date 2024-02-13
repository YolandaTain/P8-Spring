package com.yolandatain.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.yolandatain.spring.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
