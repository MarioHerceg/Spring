package hr.tvz.herceg.studapp;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
@CrossOrigin(origins = "http://localhost:4200")
public class CourseController {

    private final CourseService courseService;
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<CourseDTO> getAllCourses(){
        return courseService.findAll();
    }

    @GetMapping(params = "JMBAG")
    public List<CourseDTO> getCourseByJMBAG(@RequestParam final String JMBAG) {
        return courseService.findCourseByJMBAG(JMBAG);
    }

    @GetMapping(params = "ECTS")
    public List<CourseDTO> getCourseByECTS(@RequestParam final Integer ECTS) {

        return courseService.findByNumberOfECTS(ECTS);
    }
}
