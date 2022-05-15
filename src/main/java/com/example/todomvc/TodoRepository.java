package com.example.todomvc;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface TodoRepository extends PagingAndSortingRepository<TodoItem, Long> {

}
