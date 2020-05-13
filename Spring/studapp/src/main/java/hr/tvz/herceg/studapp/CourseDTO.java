package hr.tvz.herceg.studapp;


import java.util.List;

public class CourseDTO {
    private Long id;
    private String naziv;
    private Integer numberOfECTS;

    public CourseDTO(Long id, String naziv, Integer numberOfECTS) {
        this.id = id;
        this.naziv = naziv;
        this.numberOfECTS = numberOfECTS;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Integer getNumberOfECTS() {
        return numberOfECTS;
    }

    public void setNumberOfECTS(Integer numberOfECTS) {
        this.numberOfECTS = numberOfECTS;
    }

    @Override
    public String toString() {
        return "CourseDTO{" +
                "ID='" + id + '\'' +
                ", Naziv=" + naziv +
                ", numberOfECTS=" + numberOfECTS +
                '}';
    }
}
