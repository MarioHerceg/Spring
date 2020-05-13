package hr.tvz.herceg.studapp;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface StudentService {
    List<StudentDTO> findAll();
    Optional<StudentDTO> findStudentByJMBAG(String JMBAG);
    Optional<StudentDTO> save(StudentCommand command);
    void deleteByJMBAG(String jmbag);

    Optional<StudentDTO> update(String jmbag, StudentCommand updateStudentCommand);

    List<StudentDTO> findStudentByEcts(Integer donja, Integer gornja);

    List<StudentDTO> findByCourses_id(Long id);
}
