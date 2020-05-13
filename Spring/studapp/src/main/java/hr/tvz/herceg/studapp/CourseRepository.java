package hr.tvz.herceg.studapp;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findAll();
    List<Course> findByStudents_JMBAG(String jmbag);
    List<Course> findByNumberOfECTS(Integer ects);
}
