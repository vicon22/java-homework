package com.isupov.homework.first.stream.utils;

import com.isupov.homework.first.stream.model.Employee;

import java.util.Random;
import java.util.UUID;

public class EmployeeRandomizer {

    public static Employee getRandomEmployee() {
        Random random = new Random();

        Integer age = random
                .ints(18, 65)
                .findFirst()
                .getAsInt();
        Integer salary = random.ints(15_000, 100_000)
                .filter(i -> i % 1000 == 0)
                .findFirst()
                .getAsInt();
        Employee.Gender gender = Employee.Gender.values()[random.nextInt(Employee.Gender.values().length)];

        return new Employee(UUID.randomUUID(), gender, age, salary);
    }
}
