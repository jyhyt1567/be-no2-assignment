package org.example.beno2assignment.schedule.repository;

import lombok.extern.slf4j.Slf4j;
import org.example.beno2assignment.schedule.entity.User;
import org.example.beno2assignment.schedule.exception.CustomException;
import org.example.beno2assignment.schedule.exception.ErrorCode;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//레벨 3 구현
@Slf4j
@Repository
public class UserRepositoryImpl implements UserRepository{

    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User signUp(User user) {

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("users").usingGeneratedKeyColumns("uid");

        Map<String, Object> parameters = new HashMap<>();

        LocalDateTime currentTime = LocalDateTime.now().withNano(0);

        parameters.put("name", user.getName());
        parameters.put("password",user.getPassword());
        parameters.put("email",user.getEmail());
        parameters.put("createAt", currentTime);
        parameters.put("modifiedAt", currentTime);

        Number uid = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new User(uid.longValue(), user.getName(), user.getPassword(), user.getEmail(), currentTime, currentTime);
    }

    @Override
    public User findUserByUidorElseThrow(Long uid) {
        List<User> res = jdbcTemplate.query("select * from users where uid = ?", userRowMapper(), uid);
        return res
                .stream()
                .findAny()
                .orElseThrow(()-> new CustomException(ErrorCode.USER_NOT_FOUND));

    }

    private RowMapper<User> userRowMapper(){
        return new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new User(
                        rs.getLong("uid"),
                        rs.getString("name"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getObject("createAt", LocalDateTime.class),
                        rs.getObject("modifiedAt", LocalDateTime.class)
                );
            }
        };
    }

}
