package com.project.aggregator.api.useraccountservice.data.repository;

import com.project.aggregator.api.useraccountservice.data.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserDAO extends CrudRepository<UserEntity,Long> {
}
