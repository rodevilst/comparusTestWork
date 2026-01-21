package com.example.comparustestwork.repository;

import com.example.comparustestwork.api.dto.UserFilter;
import com.example.comparustestwork.config.DataSourceConfig;
import com.example.comparustestwork.db.SqlQuery;
import com.example.comparustestwork.db.SqlQueryBuilder;
import com.example.comparustestwork.domain.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class UserRepository {

    public List<User> getUsers(NamedParameterJdbcTemplate jdbc,
                               DataSourceConfig config,
                               UserFilter filter) {
        SqlQuery query = new SqlQueryBuilder(config)
                .select()
                .where(filter)
                .build();

        log.debug("Executing SQL: {} with params: {}", query.sql(), query.params());

        return jdbc.query(query.sql(), query.params(), rowMapper());
    }

    private RowMapper<User> rowMapper() {
        return (rs, rowNum) -> new User(
                rs.getString("id"),
                rs.getString("username"),
                rs.getString("name"),
                rs.getString("surname")
        );
    }
}