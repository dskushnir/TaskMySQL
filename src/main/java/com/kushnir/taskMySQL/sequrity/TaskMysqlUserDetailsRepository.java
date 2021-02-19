package com.kushnir.taskMySQL.sequrity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskMysqlUserDetailsRepository extends JpaRepository<TaskMysqlUser, Integer> {
    TaskMysqlUser findByUsername(String username);
}
