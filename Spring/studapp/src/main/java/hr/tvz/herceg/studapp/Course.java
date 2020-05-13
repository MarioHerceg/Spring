package hr.tvz.herceg.studapp;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="course")
public class Course {

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="naziv")
    private String naziv;

    @Column(name = "ects_points")
    private Integer numberOfECTS;

    @ManyToMany(targetEntity = Student.class, mappedBy = "courses")
    private List<Student> students;

    public Course() {
    }

    public Course(Long id, String naziv, Integer ects_points) {
        this.id=id;
        this.naziv=naziv;
        this.numberOfECTS=ects_points;
    }

    public Long getId(){ return id; }
    public String getNaziv() {
        return naziv;
    }
    public Integer getNumberOfECTS() {
        return numberOfECTS;
    }

    public List<Student> getStudents(){
        return students;
    }

}
