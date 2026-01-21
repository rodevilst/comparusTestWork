package com.example.comparustestwork.service;

import com.example.comparustestwork.config.DataSourceConfig;
import com.example.comparustestwork.db.DynamicDataSourceRegistry;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;

@Slf4j
@Component
public class ParallelQueryExecutorImpl implements ParallelQueryExecutor {

    private final DynamicDataSourceRegistry registry;
    private final ExecutorService executor;

    public ParallelQueryExecutorImpl(DynamicDataSourceRegistry registry) {
        this.registry = registry;
        int poolSize = Math.min(registry.getAll().size(), 10);
        this.executor = Executors.newFixedThreadPool(poolSize);
        log.info("ParallelQueryExecutor initialized with {} threads", poolSize);
    }

    @Override
    public <T> List<T> execute(BiFunction<NamedParameterJdbcTemplate, DataSourceConfig, List<T>> query) {
        List<CompletableFuture<List<T>>> futures = registry.getAllConfigs()
                .entrySet()
                .stream()
                .map(entry -> CompletableFuture.supplyAsync(
                        () -> executeQuery(entry.getKey(), entry.getValue(), query),
                        executor
                ))
                .toList();

        return futures.stream()
                .map(this::getSafely)
                .flatMap(List::stream)
                .toList();
    }

    private <T> List<T> executeQuery(String dbName, DataSourceConfig config,
                                     BiFunction<NamedParameterJdbcTemplate, DataSourceConfig, List<T>> query) {
        log.debug("Executing query on '{}'", dbName);
        NamedParameterJdbcTemplate jdbcTemplate = registry.getJdbcTemplate(dbName);
        return query.apply(jdbcTemplate, config);
    }

    private <T> List<T> getSafely(CompletableFuture<List<T>> future) {
        try {
            return future.get(30, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("Query failed: {}", e.getMessage());
            return List.of();
        }
    }

    @PreDestroy
    public void shutdown() {
        log.info("Shutting down executor");
        executor.shutdown();
        try {
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}