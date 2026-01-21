package com.example.comparustestwork.util;

import com.example.comparustestwork.api.dto.SortField;
import com.example.comparustestwork.api.dto.SortOrder;
import com.example.comparustestwork.api.dto.SortParams;
import com.example.comparustestwork.domain.model.User;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component
public class UserSorter {
    private static final Map<SortField, Function<User, String>> EXTRACTORS = Map.of(
            SortField.ID, User::getId,
            SortField.USERNAME, User::getUsername,
            SortField.NAME, User::getName,
            SortField.SURNAME, User::getSurname
    );

    public List<User> sort(List<User> users, SortParams params) {
        Function<User, String> extractor = EXTRACTORS.get(params.getSortBy());
        Comparator<User> comparator = Comparator.comparing(
                extractor, Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER)
        );
        if (params.getSortOrder() == SortOrder.DESC) {
            comparator = comparator.reversed();
        }
        return users.stream().sorted(comparator).toList();
    }
}