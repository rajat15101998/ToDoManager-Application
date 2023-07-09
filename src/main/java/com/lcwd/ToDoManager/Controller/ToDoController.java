package com.lcwd.ToDoManager.Controller;

import com.lcwd.ToDoManager.Model.Entity.ToDo;
import com.lcwd.ToDoManager.Service.ToDoService;
import com.lcwd.ToDoManager.Service.ToDoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.util.Optional;

@RestController
@RequestMapping("/todo")
public class ToDoController {

       Logger log = LoggerFactory.getLogger(ToDoServiceImpl.class);

       //to use methods of Service Class
       @Autowired
       private ToDoService toDoService;

       //create ToDo
       @PostMapping("/create")
       public ResponseEntity<String> createToDo(@RequestBody ToDo todo) {
              SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
              Date currentDate = new Date();
              String formattedDate = dateFormatter.format(currentDate);
              Date parsedDate = null;
              try {
                     parsedDate = dateFormatter.parse(formattedDate);
                     System.out.println("Parsed date: " + parsedDate);
              } catch (ParseException e) {
                     e.printStackTrace();
              }

              todo.setStartDate(parsedDate);
              toDoService.createToDo(todo);
              return ResponseEntity.status(HttpStatus.CREATED).body("To Do created");
       }

       //get ToDo By Id
       @GetMapping("/{todoId}")
       public ResponseEntity<Optional<ToDo>> getToDoById(@PathVariable int todoId) {
              Optional<ToDo> toDoById = toDoService.getToDoById(todoId);
              if(toDoById.isEmpty())
                     return ResponseEntity.status(HttpStatus.NOT_FOUND).body(toDoById);
              else
                     return ResponseEntity.status(HttpStatus.OK).body(toDoById);
       }

       //get all todos
       @GetMapping("/all")
       public ResponseEntity<List<ToDo>> getAllToDo() {
              List<ToDo> allToDo = toDoService.getAllToDo();
              return ResponseEntity.status(HttpStatus.OK).body(allToDo);
       }

       //update ToDo
       @PutMapping("/{todoId}")
       public ResponseEntity<ToDo> updateToDo(@PathVariable int todoId, @RequestBody ToDo updatedDetails) {
              ToDo updatedToDo = toDoService.updateToDo(updatedDetails, todoId);
              return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedToDo);
       }

       @DeleteMapping("/{todoId}")
       public ResponseEntity<String> deleteToDo(@PathVariable int todoId) {
              boolean isDeleted = toDoService.deleteToDo(todoId);
              if(isDeleted)
                     return ResponseEntity.status(HttpStatus.OK).body("ToDo Deleted successfully");
              else
                     return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No ToDo found with Given Id");
       }

}
