package org.maleb0lge.restdemo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> students;

    @PostConstruct
    public void loadData() {
        students = new ArrayList<>();

        students.add(new Student("Celso", "Chang"));
        students.add(new Student("Ilaria", "Maruccia"));
        students.add(new Student("Juan", "Pérez"));
    }

    @GetMapping("/students")
    public List<Student> getStudents() {

        return students;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {

        if (studentId < 0 || studentId >= students.size())
            throw new StudentNotFoundException(String.format("Student with id {%s} not found", studentId));

        return students.get(studentId);
    }

}
