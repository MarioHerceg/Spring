package hr.tvz.herceg.studapp;

import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Primary
@Repository
public class JdbcStudentRepository implements StudentRepository{
    private JdbcTemplate jdbc;
    private SimpleJdbcInsert studentInserter;

    public JdbcStudentRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        this.studentInserter = new SimpleJdbcInsert(jdbc)
                .withTableName("student")
                .usingGeneratedKeyColumns("ID");
    }

    @Override
    public Iterable<Student> findAll(){
        return jdbc.query("select ID, first_name, last_name, jmbag, ects_points, date_of_birth from student",
                this::mapRowToStudent);
    }

    @Override
    public Iterable<Student> findEcts(Integer donja, Integer gornja){
        return jdbc.query("select ID, first_name, last_name, jmbag, ects_points, date_of_birth from student WHERE ects_points between ? and ?",
                this::mapRowToStudent, donja, gornja);
    }

    @Override
    public Student findOne(String id) {
        return jdbc.queryForObject("select ID, first_name, last_name, jmbag, ects_points, date_of_birth from student where id = ?",
                this::mapRowToStudent, id);
    }

    @Override
    public Student findStudentByJMBAG(String jmbag) {
        return jdbc.queryForObject("select ID, first_name, last_name, jmbag, ects_points, date_of_birth from student where jmbag = ?",
                this::mapRowToStudent, jmbag);
    }

    @Override
    public Student save(Student student) {
        student.setDateOfBirth(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        student.setId(saveStudentDetails(student));
        return student;
    }

    private Long saveStudentDetails(Student student) {
        Map<String, Object> values = new HashMap<>();

        values.put("first_name", student.getName());
        values.put("last_name", student.getSurname());
        values.put("jmbag",student.getJMBAG());
        values.put("date_of_birth",student.getDateOfBirth());
        values.put("ects_points", student.getNumberOfECTS());
        return studentInserter.executeAndReturnKey(values).longValue();
    }

    @Override
    public void deleteByJMBAG(String JMBAG){
        jdbc.update("DELETE FROM student WHERE jmbag = ?", JMBAG);
    }

    @Override
    public Optional<Student> update(String JMBAG, Student updatedStudent){
        int executed = jdbc.update("UPDATE student SET",
                "first_name=?",
                "last_name=?",
                "ects_points=?",
                "date_of_birth=?",
                "WHERE jmbag = ?",
                updatedStudent.getName(),
                updatedStudent.getSurname(),
                updatedStudent.getNumberOfECTS(),
                updatedStudent.getDateOfBirth(),
                updatedStudent.getJMBAG()
                );
        if(executed>0){
            return Optional.of(updatedStudent);
        }else{
            return Optional.empty();
        }
    }

    @Override
    public List<Student> findByCourses_id(Long id) {
        return jdbc.query("select * from student as s " +
                        "join student_course  as sc on s.ID=sc.student_id " +
                        "join course as c on c.ID = sc.course_id " +
                        "where c.ID = ?",
                this::mapRowToStudent, id);
    }

    private Student mapRowToStudent(ResultSet rs, int rowNub) throws SQLException {
        return new Student(
                rs.getLong("ID"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("jmbag"),
                rs.getDate("date_of_birth").toLocalDate(),
                rs.getInt("ects_points")
        );
    }
}
