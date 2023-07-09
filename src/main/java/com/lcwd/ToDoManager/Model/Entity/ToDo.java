package com.lcwd.ToDoManager.Model.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "ToDo")
public class ToDo {

       @Id
       @GeneratedValue(strategy = GenerationType.AUTO)
       @Column(name = "ToDoId")
       private int id;
       @Column(name = "Title", length = 50)
       private String title;
       @Column(name = "Content", length = 200)
       private String content;
       @Column(name = "Status", length = 20)
       private String status;
       @Column(name = "StartDate")
       @JsonFormat(pattern = "dd-MM-yyyy")
       private Date startDate;
       @Column(name = "TargetDate")
       @JsonFormat(pattern = "dd-MM-yyyy")
       private Date targetDate;

    public ToDo() {
    }


    public ToDo(int id, String title, String content, String status, Date startDate, Date targetDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.status = status;
        this.startDate = startDate;
        this.targetDate = targetDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }

    @Override
    public String toString() {
        return "ToDo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", status='" + status + '\'' +
                ", startDate=" + startDate +
                ", targetDate=" + targetDate +
                '}';
    }
}
