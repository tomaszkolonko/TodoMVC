package com.example.todomvc.repository;

import com.example.todomvc.model.TodoItem;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface TodoRepository extends PagingAndSortingRepository<TodoItem, Long> {

    @Override
    List<TodoItem> findAll();

}
