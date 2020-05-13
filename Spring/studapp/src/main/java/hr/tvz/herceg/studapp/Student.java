package hr.tvz.herceg.studapp;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name="student")
public class Student {
    @Id
    @Column(name ="ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="first_name")
    private String name;

    @Column(name="last_name")
    private String surname;

    @Column(name = "jmbag")
    private String JMBAG;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "ects_points")
    private Integer NumberOfECTS;

    @ManyToMany(targetEntity = Course.class)
    @JoinTable(
            name = "student_course",
            joinColumns = { @JoinColumn(name = "student_id") },
            inverseJoinColumns = { @JoinColumn(name = "course_id") }
    )
    private List<Course> courses;

    public Student(Long id, String name, String surname, String JMBAG, LocalDate dateOfBirth, Integer NumberOfECTS) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.JMBAG = JMBAG;
        this.dateOfBirth = dateOfBirth;
        this.NumberOfECTS = NumberOfECTS;
    }

    public Student(){};

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getJMBAG() {
        return JMBAG;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Integer getNumberOfECTS() {
        return NumberOfECTS;
    }

    public Long getId(){ return id; }

    public List<Course> getCourses(){ return courses; }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setJMBAG(String JMBAG) {
        this.JMBAG = JMBAG;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setNumberOfECTS(Integer numberOfECTS) {
        NumberOfECTS = numberOfECTS;
    }
}
