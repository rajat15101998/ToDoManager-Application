package com.lcwd.ToDoManager.Service;

import com.lcwd.ToDoManager.Model.Entity.ToDo;

import java.util.List;
import java.util.Optional;

public interface ToDoService {

       //create new todo
       void createToDo(ToDo todo);

       //get todo by Id
       Optional<ToDo> getToDoById(int todoId);

       //get list of all todos
       List<ToDo> getAllToDo();

       //update ToDo
       ToDo updateToDo(ToDo updatedDetails, int id);

       //delete ToDo
       boolean deleteToDo(int todoId);

}
