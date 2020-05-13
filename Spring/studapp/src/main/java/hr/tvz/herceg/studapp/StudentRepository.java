package hr.tvz.herceg.studapp;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    Iterable<Student> findAll();
    Iterable<Student> findEcts(Integer donja, Integer gornja);
    Student findOne(String id);
    Student findStudentByJMBAG(String id);
    Student save(Student student);
    void deleteByJMBAG(String JMBAG);
    Optional<Student> update(String JMBAG, Student updatedStudent);
    List<Student> findByCourses_id(Long id);
}
