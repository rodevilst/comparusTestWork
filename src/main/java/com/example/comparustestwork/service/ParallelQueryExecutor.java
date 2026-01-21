package com.example.comparustestwork.service;

import com.example.comparustestwork.config.DataSourceConfig;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;
import java.util.function.BiFunction;

public interface ParallelQueryExecutor {

    <T> List<T> execute(BiFunction<NamedParameterJdbcTemplate, DataSourceConfig, List<T>> query);
}