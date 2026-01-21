package com.example.comparustestwork.service;

import com.example.comparustestwork.api.dto.SortParams;
import com.example.comparustestwork.api.dto.UserFilter;
import com.example.comparustestwork.domain.model.User;
import com.example.comparustestwork.repository.UserRepository;
import com.example.comparustestwork.util.UserSorter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final ParallelQueryExecutor queryExecutor;
    private final UserSorter sorter;

    public UserService(UserRepository userRepository, ParallelQueryExecutor queryExecutor, UserSorter sorter) {
        this.userRepository = userRepository;
        this.queryExecutor = queryExecutor;
        this.sorter = sorter;
    }

    public List<User> findAll(UserFilter filter, SortParams sortParams) {
        List<User> users = queryExecutor.execute(
                (jdbc, config) -> userRepository.getUsers(jdbc, config, filter)
        );

        return sorter.sort(users, sortParams);
    }

}

