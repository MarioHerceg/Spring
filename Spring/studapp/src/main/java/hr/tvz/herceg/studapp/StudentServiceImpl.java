package hr.tvz.herceg.studapp;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class StudentServiceImpl implements StudentService {
    private static final int YEARS_AFTER_WHICH_TUITION_SHOULD_BE_PAYED = 26;
    private final StudentRepository studentRepository;
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    @Override
    public List<StudentDTO> findAll() {
        return StreamSupport.stream(studentRepository.findAll().spliterator(),false).map(this::mapStudentToDTO).collect(Collectors.toList());
    }
    @Override
    public Optional<StudentDTO> findStudentByJMBAG(final String JMBAG) {
        return Optional.of(studentRepository.findStudentByJMBAG(JMBAG)).map(this::mapStudentToDTO);
    }

    @Override
    public Optional<StudentDTO> save(StudentCommand command) {
        Student st = new Student(command.getID(),command.getFirstName(),command.getLastName(),command.getJmbag(),command.getDateOfBirth(),command.getNumberOfECTS());
        return Optional.of(studentRepository.save(st)).map(this::mapStudentToDTO);
    }

    @Override
    public void deleteByJMBAG(String jmbag){
        studentRepository.deleteByJMBAG(jmbag);
    }

    @Override
    public Optional<StudentDTO> update(String jmbag, StudentCommand command) {
        Student st = new Student(command.getID(),command.getFirstName(),command.getLastName(),command.getJmbag(),command.getDateOfBirth(),command.getNumberOfECTS());
        return studentRepository.update(jmbag,st).map(this::mapStudentToDTO);
    }

    @Override
    public List<StudentDTO> findStudentByEcts(Integer donja, Integer gornja) {
        return StreamSupport.stream(studentRepository.findEcts(donja, gornja).spliterator(),false).map(this::mapStudentToDTO).collect(Collectors.toList());
    }

    @Override
    public List<StudentDTO> findByCourses_id(Long id) {
        return studentRepository.findByCourses_id(id).stream().map(this::mapStudentToDTO).collect(Collectors.toList());
    }

    private StudentDTO mapStudentToDTO(final Student student){
        return new StudentDTO(student.getJMBAG(), student.getNumberOfECTS(), shouldTuitionBePayed(student.getDateOfBirth()));
    }
    private boolean shouldTuitionBePayed(LocalDate dateOfBirth){
        return dateOfBirth.plusYears(YEARS_AFTER_WHICH_TUITION_SHOULD_BE_PAYED).isBefore(LocalDate.now());
    }

}
