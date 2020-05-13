/*package hr.tvz.herceg.studapp;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;

@Repository
public abstract class MockStudentRepository implements StudentRepository {
    private final List<Student> MOCKED_STUDENTS = new ArrayList<Student>(Arrays.asList(
            new Student((long) 1, "Ivo", "Ivić", "0256423323", LocalDate.now().minusYears(27), 120),
            new Student((long) 2, "Ivo", "Lucić", "0256423324", LocalDate.now().minusYears(25), 98),
            new Student((long) 3, "Lucija", "Ivić", "0256423325", LocalDate.now().minusYears(26), 114)
    ));
    @Override
    public List<Student> findAll() {
        return MOCKED_STUDENTS;
    }
    @Override
    public Optional<Student> findStudentByJMBAG(final String JMBAG) {
        return MOCKED_STUDENTS.stream().filter(it -> Objects.equals(it.getJMBAG(), JMBAG)).findAny();
    }

    @Override
    public Optional<Student> save(StudentCommand st){
            System.out.println(st.getDateOfBirth());
            String nameSt=st.getFirstName();
            String surnameSt=st.getLastName();
            String jmbagSt=st.getJmbag();
            LocalDate localDate = st.getDateOfBirth();
            Integer ectsSt=st.getNumberOfECTS();
            Student novi = new Student(id, nameSt, surnameSt, jmbagSt, localDate, ectsSt);
            System.out.println(novi.getDateOfBirth());
            if(MOCKED_STUDENTS.stream().filter(it -> Objects.equals(it.getJMBAG(), jmbagSt)).findAny().isEmpty()) {
                MOCKED_STUDENTS.add(novi);
                return Optional.of(novi);
            }
            else{
                return Optional.empty();
            }

    }

    @Override
    public void deleteByJmbag(String jmbag){
        int i=0,j=0;
        for(Student stud : MOCKED_STUDENTS){
            if(stud.getJMBAG().equals(jmbag)) i=j;
            j++;
        }
        MOCKED_STUDENTS.remove(i);
    }

    @Override
    public Optional<Student> update(String jmbag, StudentCommand st) {
        String nameSt=st.getFirstName();
        String surnameSt=st.getLastName();
        LocalDate localDate = st.getDateOfBirth();
        Integer ectsSt=st.getNumberOfECTS();

        int i=-1,j=0;
        for(Student stud : MOCKED_STUDENTS){
            if(stud.getJMBAG().equals(jmbag)) i=j;
            j++;
        }
        Student novi = new Student(id, nameSt, surnameSt, jmbag, localDate, ectsSt);
        if(i!=-1 && jmbag.equals(st.getJmbag())){
            MOCKED_STUDENTS.set(i,novi);
            return Optional.of(novi);
        }
        else{
            return Optional.empty();
        }
    }
}
*/