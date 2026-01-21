package com.example.comparustestwork.db;

import java.util.Map;

public record SqlQuery(
        String sql,
        Map<String, Object> params
) {}