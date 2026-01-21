package com.example.comparustestwork.db;

import com.example.comparustestwork.config.DataSourceConfig;
import com.example.comparustestwork.config.DataSourceProperties;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class DynamicDataSourceRegistry {

    private final Map<String, NamedParameterJdbcTemplate> jdbcTemplates;
    private final Map<String, DataSourceConfig> configs;

    public DynamicDataSourceRegistry(DataSourceProperties properties) {
        this.configs = properties.datasources().stream()
                .collect(Collectors.toMap(DataSourceConfig::name, c -> c));

        this.jdbcTemplates = properties.datasources().stream()
                .collect(Collectors.toMap(
                        DataSourceConfig::name,
                        this::createJdbcTemplate
                ));

        log.info("Registered {} datasource(s): {}", jdbcTemplates.size(), jdbcTemplates.keySet());
    }

    private NamedParameterJdbcTemplate createJdbcTemplate(DataSourceConfig config) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(config.url());
        hikariConfig.setUsername(config.username());
        hikariConfig.setPassword(config.password());
        hikariConfig.setPoolName("pool-" + config.name());

        hikariConfig.setMaximumPoolSize(config.config().maxPoolSize());
        hikariConfig.setMinimumIdle(config.config().minIdle());
        hikariConfig.setConnectionTimeout(config.config().connectionTimeout());
        hikariConfig.setIdleTimeout(config.config().idleTimeout());
        hikariConfig.setMaxLifetime(config.config().maxLifetime());

        return new NamedParameterJdbcTemplate(new HikariDataSource(hikariConfig));
    }

    public NamedParameterJdbcTemplate getJdbcTemplate(String name) {
        NamedParameterJdbcTemplate template = jdbcTemplates.get(name);
        if (template == null) {
            throw new IllegalArgumentException("Datasource not found: " + name);
        }
        return template;
    }

    public Map<String, NamedParameterJdbcTemplate> getAll() {
        return jdbcTemplates;
    }

    public Map<String, DataSourceConfig> getAllConfigs() {
        return configs;
    }

    @PreDestroy
    public void shutdown() {
        log.info("Shutting down datasources...");
        jdbcTemplates.values().forEach(template -> {
            if (template.getJdbcTemplate().getDataSource() instanceof HikariDataSource ds) {
                ds.close();
                log.debug("Closed datasource: {}", ds.getPoolName());
            }
        });
    }
}