package com.example.comparustestwork.db;

import com.example.comparustestwork.api.dto.UserFilter;
import com.example.comparustestwork.config.DataSourceConfig;
import com.example.comparustestwork.config.FieldMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class SqlQueryBuilder {

    private static final Pattern SAFE_IDENTIFIER = Pattern.compile("^[a-zA-Z_][a-zA-Z0-9_]*$");

    private final FieldMapping m;
    private final String table;
    private final StringBuilder sql = new StringBuilder();
    private final Map<String, Object> params = new HashMap<>();

    public SqlQueryBuilder(DataSourceConfig config) {
        this.m = config.mapping();
        this.table = validateIdentifier(config.table());

        validateIdentifier(m.id());
        validateIdentifier(m.username());
        validateIdentifier(m.name());
        validateIdentifier(m.surname());
    }

    public SqlQueryBuilder select() {
        sql.append("SELECT ")
                .append(m.id()).append(" AS id, ")
                .append(m.username()).append(" AS username, ")
                .append(m.name()).append(" AS name, ")
                .append(m.surname()).append(" AS surname")
                .append(" FROM ").append(table);
        return this;
    }

    public SqlQueryBuilder where(UserFilter filter) {
        addCondition("username", filter.getUsername(), m.username());
        addCondition("name", filter.getName(), m.name());
        addCondition("surname", filter.getSurname(), m.surname());
        return this;
    }

    public SqlQuery build() {
        return new SqlQuery(sql.toString(), params);
    }

    private void addCondition(String paramName, String value, String column) {
        if (value == null || value.isBlank()) {
            return;
        }

        sql.append(params.isEmpty() ? " WHERE " : " AND ");
        sql.append(column).append(" LIKE :").append(paramName);
        params.put(paramName, "%" + value + "%");
    }

    private String validateIdentifier(String identifier) {
        if (identifier == null || !SAFE_IDENTIFIER.matcher(identifier).matches()) {
            throw new IllegalArgumentException(
                    "Invalid SQL identifier: " + identifier +
                            ". Only letters, digits and underscores allowed, must start with letter or underscore."
            );
        }
        return identifier;
    }
}
