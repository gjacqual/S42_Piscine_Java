package edu.school21.sockets.repositories;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import edu.school21.sockets.models.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Component("jdbcTemplateImpl")
public class UsersRepositoryImpl implements UsersRepository {
    private final JdbcTemplate jdbcTemplate;
    private final DataSource dataSource;

    @Autowired
    public UsersRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.dataSource = dataSource;
    }

    @Override
    public User findById(Long id) {
        return jdbcTemplate.query("SELECT * FROM users WHERE identifier =?",
                new Object[]{id}, new BeanPropertyRowMapper<>(User.class))
                .stream().findAny().orElse(null);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM users", new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public void save(User entity) {
        jdbcTemplate.update("INSERT INTO users (username, password) VALUES (?, ?)",
                entity.getUsername(), entity.getPassword());
    }

    @Override
    public void update(User entity) {
        jdbcTemplate.update("UPDATE users SET username=? WHERE identifier=?",
                entity.getUsername(), entity.getIdentifier());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM users WHERE identifier = ?", id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        User user = jdbcTemplate.query("SELECT * FROM users WHERE username = ?",
                        new BeanPropertyRowMapper<>(User.class),
                        new Object[]{username})
                .stream().findAny().orElse(null);
        return Optional.ofNullable(user);
    }
}
