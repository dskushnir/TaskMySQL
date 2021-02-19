package com.kushnir.taskMySQL.sequrity;

import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AuthorityToStringConverter implements AttributeConverter<Set<TaskMysqlAuthority>, String> {
    @Override
    public String convertToDatabaseColumn(Set<TaskMysqlAuthority> attribute) {
        return attribute.stream()
                .map(TaskMysqlAuthority::getAuthority)
                .collect(Collectors.joining(","));
    }

    @Override
    public Set<TaskMysqlAuthority> convertToEntityAttribute(String dbData) {
        return Arrays.stream(dbData.split(","))
                .map(TaskMysqlAuthority::new)
                .collect(Collectors.toSet());
    }
}
