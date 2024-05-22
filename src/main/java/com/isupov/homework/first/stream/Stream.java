package com.isupov.homework.first.stream;

import com.isupov.homework.first.stream.model.Employee;
import com.isupov.homework.first.stream.utils.EmployeeRandomizer;

import java.util.*;
import java.util.stream.Collectors;

public class Stream {

    public static void main(String[] args) {
        List<Integer> integerList = new ArrayList<>(100_000);
        for (int i = 1; i <= 100_000; i++) {
            integerList.add(i);
        }

        //четные числа
        List<Integer> evenList = integerList.stream()
                .filter(i -> i % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("Четные числа: " + evenList);

        System.out.println("\n=====================\n");

        //десятки
        List<Integer> collect = integerList.stream()
                .filter(i -> i > 9)
                .filter(i -> i < 100)
                .collect(Collectors.toList());
        System.out.println("Десятки: " + collect);
        System.out.println("\n=====================\n");

        //заполняем массив случайными числами
        List<Integer> randomIntegerList = new Random()
                .ints()
                .limit(100_000)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        //ASC
        List<Integer> ascOrderList = randomIntegerList.stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("asc order: " + ascOrderList);
        System.out.println("\n=====================\n");

        //DES
        List<Integer> desOrderList = randomIntegerList.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        System.out.println("des order: " + desOrderList);
        System.out.println("\n=====================\n");


        List<Employee> employeeList = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            employeeList.add(EmployeeRandomizer.getRandomEmployee());
        }

        //Работники чей возраст между 30 и 45 годами
        List<Employee> middleAgeEmployeeList = employeeList.stream()
                .filter(e -> e.getAge() > 30)
                .filter(e -> e.getAge() < 45)
                .collect(Collectors.toList());

        System.out.println(String.format("Работники чей возраст между 30 и 45 годами(кол-во: %d): %s", middleAgeEmployeeList.size(), middleAgeEmployeeList));
        System.out.println("\n=====================\n");

        //Работники мужчины
        List<Employee> maleEmployeeList = employeeList.stream()
                .filter(e -> Employee.Gender.MALE.equals(e.getGender()))
                .collect(Collectors.toList());
        System.out.println(String.format("Работники мужчины(кол-во: %d): %s", maleEmployeeList.size(), maleEmployeeList));
        System.out.println("\n=====================\n");

        //Работники женщины с зарплатой меньше 35к и возрастом от 20 до 45
        List<Employee> femaleEmployeeWithCertainAgeAndSalaryList = employeeList.stream()
                .filter(e -> Employee.Gender.FEMALE.equals(e.getGender()))
                .filter(e -> e.getSalary() < 35_000)
                .filter(e -> e.getAge() > 20)
                .filter(e -> e.getAge() < 45)
                .collect(Collectors.toList());
        System.out.println(String.format("Работники женщины с зарплатой меньше 35к и возрастом от 20 до 45(кол-во: %d): %s", femaleEmployeeWithCertainAgeAndSalaryList.size(), femaleEmployeeWithCertainAgeAndSalaryList));
        System.out.println("\n=====================\n");
    }
}
