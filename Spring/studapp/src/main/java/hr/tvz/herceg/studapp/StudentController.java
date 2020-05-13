package hr.tvz.herceg.studapp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "http://localhost:4200")
public class StudentController {
    private final StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<StudentDTO> getAllStudents(){
        return studentService.findAll();
    }

    @GetMapping(params = "JMBAG")
    public ResponseEntity<StudentDTO> getStudentByJMBAG(@RequestParam final String JMBAG){
        return studentService.findStudentByJMBAG(JMBAG).map(
                studentDTO -> ResponseEntity
                        .status(HttpStatus.ACCEPTED)
                        .body(studentDTO)
        )
                .orElseGet(
                        () -> ResponseEntity
                                .status(HttpStatus.NO_CONTENT)
                                .build()
                );
    }

    @GetMapping("/{donja}/{gornja}")
    public List<StudentDTO> getStudentByEcts(@PathVariable Integer donja, @PathVariable Integer gornja){
        List<StudentDTO> list = studentService.findStudentByEcts(donja, gornja);
        if(list.isEmpty()){
            return Collections.emptyList();
        }
        else{
            return studentService.findStudentByEcts(donja, gornja);
        }
    }

    @GetMapping(params = "ID")
    public List<StudentDTO> getCourseByJMBAG(@RequestParam final Long ID) {
        return studentService.findByCourses_id(ID);
    }

    @PostMapping
    public ResponseEntity<StudentDTO> save(@Valid @RequestBody final StudentCommand command){
        return studentService.save(command)
                .map(
                        studentDTO -> ResponseEntity
                                .status(HttpStatus.CREATED)
                                .body(studentDTO)
                )
                .orElseGet(
                        () -> ResponseEntity
                                .status(HttpStatus.CONFLICT)
                                .build()
                );
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{JMBAG}")
    public void delete(@PathVariable String JMBAG){
        studentService.deleteByJMBAG(JMBAG);
    }

    @PutMapping("/{JMBAG}")
    public ResponseEntity<StudentDTO> update(@PathVariable String JMBAG, @Valid @RequestBody
    final StudentCommand updateStudentCommand){
        return studentService.update(JMBAG, updateStudentCommand)
                .map(ResponseEntity::ok)
                .orElseGet(
                        () -> ResponseEntity.notFound().build()
                );
    }

}
