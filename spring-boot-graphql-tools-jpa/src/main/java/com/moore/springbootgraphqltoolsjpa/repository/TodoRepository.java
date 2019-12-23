package com.moore.springbootgraphqltoolsjpa.repository;

import com.moore.springbootgraphqltoolsjpa.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
