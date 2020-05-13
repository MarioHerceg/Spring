package hr.tvz.herceg.studapp;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<CourseDTO> findAll();
    List<CourseDTO> findCourseByJMBAG(String jmbag);
    List<CourseDTO> findByNumberOfECTS(Integer ects);
}
