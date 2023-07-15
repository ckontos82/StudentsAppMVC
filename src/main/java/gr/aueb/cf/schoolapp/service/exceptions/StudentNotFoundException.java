package gr.aueb.cf.schoolapp.service.exceptions;

import gr.aueb.cf.schoolapp.model.Student;

public class StudentNotFoundException extends Exception {
    private static final long serialVersionUID = 123456L;

    public StudentNotFoundException(Student student) {
        super("Student with id: " + student.getId() + " not found.");
    }

    public StudentNotFoundException(String message) {
        super(message);
    }
}
