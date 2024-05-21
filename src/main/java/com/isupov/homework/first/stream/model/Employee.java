package com.isupov.homework.first.stream.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class Employee {

    private UUID id;
    private Gender gender;
    private Integer age;
    private Integer salary;

    public enum Gender {
        MALE, FEMALE
    }
}
