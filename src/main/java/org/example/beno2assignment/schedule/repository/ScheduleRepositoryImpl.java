package org.example.beno2assignment.schedule.repository;

import lombok.extern.slf4j.Slf4j;
import org.example.beno2assignment.schedule.dto.ReadScheduleRequestDto;
import org.example.beno2assignment.schedule.dto.UpdateScheduleRequestDto;
import org.example.beno2assignment.schedule.entity.Schedule;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository{

    private final JdbcTemplate jdbcTemplate;
    public ScheduleRepositoryImpl(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Schedule createSchedule(Schedule schedule) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>();

        LocalDateTime currentTime = LocalDateTime.now().withNano(0);

        parameters.put("name", schedule.getName());
        parameters.put("todo", schedule.getTodo());
        parameters.put("password",schedule.getPassword());
        parameters.put("createAt", currentTime);
        parameters.put("modifiedAt", currentTime);
        parameters.put("uid", schedule.getUid());

        Number id = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new Schedule(id.longValue(), schedule.getTodo(), schedule.getName(),schedule.getPassword(), currentTime, currentTime, schedule.getUid());
    }

    @Override
    public List<Schedule> findSchedulesByConditions(ReadScheduleRequestDto requestDto) {

        StringBuilder sql = new StringBuilder("select * from schedule where 1 = 1");
        List<Object> conds = new ArrayList<>();

        if(requestDto.getName() != null){
            sql.append(" and name = ?");
            conds.add(requestDto.getName());
        }
        if(requestDto.getModifiedAt() != null){
            sql.append(" and modifiedAt between ? and ?");
            conds.add(requestDto.getModifiedAt().atStartOfDay());
            conds.add(requestDto.getModifiedAt().atTime(LocalTime.MAX));
        }
        if(requestDto.getUid() != null){
            if(requestDto.getUid() == 0){
                sql.append(" and uid is null");
            }
            else{
                sql.append(" and uid = ?");
                conds.add(requestDto.getUid());
            }
        }

        sql.append(" order by modifiedAt desc");

        return jdbcTemplate.query(sql.toString(), scheduleRowMapper(), conds.toArray());

    }

    @Override
    public Schedule findScheduleByIdorElseThrow(Long id) {
        List<Schedule> res = jdbcTemplate.query("select * from schedule where id = ?", scheduleRowMapper(), id);
        return res
                .stream()
                .findAny()
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "아이디= "+id+"가 없습니다."));

    }

    @Override
    public int updateSchedule(Long id, UpdateScheduleRequestDto requestDto) {

        StringBuilder sql = new StringBuilder("update schedule set");
        List<Object> conds = new ArrayList<>();

        Boolean flag = false;

        if(requestDto.getName() != null){
            sql.append(" name = ?");
            conds.add(requestDto.getName());
            flag = true;
        }

        if(requestDto.getTodo() != null){
            if(flag){
                sql.append(",");
            }
            sql.append(" todo = ?");
            conds.add(requestDto.getTodo());
        }

        sql.append(",");
        sql.append(" modifiedAt = ?");
        conds.add(LocalDateTime.now());

        sql.append(" where id = ?");
        conds.add(id);

        return jdbcTemplate.update(sql.toString(), conds.toArray());
    }

    @Override
    public int deleteSchedule(Long id) {
        return jdbcTemplate.update("delete from schedule where id = ?", id);
    }

    private RowMapper<Schedule> scheduleRowMapper(){
        return new RowMapper<Schedule>() {
            @Override
            public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Schedule(
                        rs.getLong("id"),
                        rs.getString("todo"),
                        rs.getString("name"),
                        rs.getString("password"),
                        rs.getObject("createAt", LocalDateTime.class),
                        rs.getObject("modifiedAt", LocalDateTime.class),
                        rs.getObject("uid", Long.class)
                );
            }
        };
    }
}
