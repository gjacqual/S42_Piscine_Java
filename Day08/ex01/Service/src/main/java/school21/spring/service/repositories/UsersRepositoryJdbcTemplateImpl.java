package school21.spring.service.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {
    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;

    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public User findById(Long id) {
        return jdbcTemplate.query("SELECT * FROM users WHERE identifier =?",
                new Object[]{id},
                (rs, rowNum) -> new User(rs.getLong(1), rs.getString(2)))
                .stream().findAny().orElse(null);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM users",
                (rs, rowNum) -> new User(rs.getLong(1), rs.getString(2)));
    }

    @Override
    public void save(User entity) {
        jdbcTemplate.update("INSERT INTO users (email) VALUES (?)",
                entity.getEmail());
    }

    @Override
    public void update(User entity) {
        jdbcTemplate.update("UPDATE users SET email=? WHERE identifier=?",
                entity.getEmail(), entity.getIdentifier());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM users WHERE identifier = ?", id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        User user = jdbcTemplate.query("SELECT * FROM users WHERE email = ?",
                (rs, rowNum) -> new User(rs.getLong(1), rs.getString(2)),
                new Object[]{email})
                .stream().findAny().orElse(null);

        return Optional.of(user);
    }
}
