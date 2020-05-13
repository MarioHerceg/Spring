/*package hr.tvz.herceg.studapp;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public abstract class CourseRepositoryImpl implements CourseRepository{
    private JdbcTemplate jdbc;
    private SimpleJdbcInsert courseInserter;

    public CourseRepositoryImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        this.courseInserter = new SimpleJdbcInsert(jdbc)
                .withTableName("course")
                .usingGeneratedKeyColumns("ID");
    }
    @Override
    public List<Course> findAll() {
        return jdbc.query("select ID, naziv, ects_points from course",
                this::mapRowToCourse);
    }

    @Override
    public List<Course> findByStudents_JMBAG(String jmbag) {
        return jdbc.query("select ID, naziv, ects_points from course as c " +
                                        "join student_course  as sc on c.ID=sc.course_id " +
                                        "join student as s on s.ID = sc.student_id " +
                                        "where s.jmbag = ?",
                this::mapRowToCourse, jmbag);
    }

    private Course mapRowToCourse(ResultSet rs, int rowNub) throws SQLException {
        return new Course(
                rs.getLong("ID"),
                rs.getString("naziv"),
                rs.getInt("ects_points")
        );
    }
}
*/
