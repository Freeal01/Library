package library_1.DAO;

import library_1.models.Book;
import library_1.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));

    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person(name, yearBirth) VALUES(?,?)", person.getName(), person.getYearBirth());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET name=?, yearBirth=? WHERE id=?", updatedPerson.getName(),
                updatedPerson.getYearBirth(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }
}
