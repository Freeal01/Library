package library_1.DAO;

import library_1.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    List<Book>listbook = new ArrayList<>();
    {
        listbook.add(new Book());
        listbook.add(new Book());
    }

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        //return jdbcTemplate.query("SELECT * FROM Books", new BeanPropertyRowMapper<>(Book.class));
        return listbook;
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM Books WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Books(name, year, author) VALUES(?, ?, ?)", book.getName(), book.getYear(), book.getAuthor());
    }

    public void update(int id, Book updatedBook) {
        jdbcTemplate.update("UPDATE Books SET name=?, year=?, author=? WHERE id=?", updatedBook.getName(),
                updatedBook.getYear(),updatedBook.getAuthor(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Books WHERE id=?", id);
    }


}
