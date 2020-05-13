package hr.tvz.herceg.studapp;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CourseServiceImpl implements CourseService{

    private final CourseRepository courseRepository;
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<CourseDTO> findAll() {
        return courseRepository.findAll().stream().map(this::mapCourseToDTO).collect(Collectors.toList());
    }

    @Override
    public List<CourseDTO> findCourseByJMBAG(final String JMBAG) {
        return courseRepository.findByStudents_JMBAG(JMBAG).stream().map(this::mapCourseToDTO).collect(Collectors.toList());
    }

    @Override
    public List<CourseDTO> findByNumberOfECTS(Integer ects) {
        return courseRepository.findByNumberOfECTS(ects).stream().map(this::mapCourseToDTO).collect(Collectors.toList());
    }

    private CourseDTO mapCourseToDTO(final Course course){
        return new CourseDTO(course.getId(), course.getNaziv(), course.getNumberOfECTS());
    }
}
