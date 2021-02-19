package com.kushnir.taskMySQL.sequrity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskMysqlAuthority implements GrantedAuthority {
    private String authority;
}

