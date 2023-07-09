package com.lcwd.ToDoManager.Service;

import com.lcwd.ToDoManager.Model.Entity.ToDo;
import com.lcwd.ToDoManager.Model.Repository.ToDoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ToDoServiceImpl implements ToDoService {

    //create instance of Logger
    Logger log = LoggerFactory.getLogger(ToDoServiceImpl.class);

    //to utilize JPARepository Interface methods
    @Autowired
    private ToDoRepository toDoRepository;

    @Override
    public void createToDo(ToDo todo) {
        ToDo createdToDo = toDoRepository.save(todo);
        log.info("New to do created:" + createdToDo);
    }

    @Override
    public Optional<ToDo> getToDoById(int todoId) {
        Optional<ToDo> toDo = toDoRepository.findById(todoId);
        if(toDo.isEmpty()) {
            log.info("No to found with given Id");
        }
        log.info("getToDoById todo = " + toDo);
        return toDo;
    }

    @Override
    public List<ToDo> getAllToDo() {
        List<ToDo> allToDos = toDoRepository.findAll();
        return allToDos;
    }

    @Override
    public ToDo updateToDo(ToDo updatedToDo, int todoId) {
        ToDo existingToDo = toDoRepository.findById(todoId).orElseThrow(() -> new RuntimeException("No ToDo found with given Id"));
        existingToDo.setTitle(updatedToDo.getTitle());
        existingToDo.setContent(updatedToDo.getContent());
        existingToDo.setStatus(updatedToDo.getStatus());
        existingToDo.setTargetDate(updatedToDo.getTargetDate());

        toDoRepository.save(existingToDo);
        log.info("ToDo with Id = " + todoId + " is updated");
        return existingToDo;
    }

    @Override
    public boolean deleteToDo(int todoId) {
        Optional<ToDo> toDo = toDoRepository.findById(todoId);
        if(toDo.isPresent()) {
            toDoRepository.deleteById(todoId);
            log.info("ToDo with Id = " + todoId + " deleted");
            return true;
        }
        else {
            log.info("Cannot delete, because No ToDo Found with Given Id");
            return false;
        }
    }
}
