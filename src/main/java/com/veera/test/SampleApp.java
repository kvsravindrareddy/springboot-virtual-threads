package com.veera.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//Citizen DB -- 10 millions
//List<Citizen>
//How many citizens are below 25
// and how many citizen between 60 to 70
public class SampleApp {

    private List<Citizen> citizensUnderAgeCriteria(List<Citizen> citizenList, int age) {
        List<Citizen> citizens = new ArrayList<>();
        citizenList.parallelStream().filter(citizen -> citizen.getAge()<age).forEach(System.out::println);
        return citizens;
    }

    private List<Citizen> citizensBetweenAgeCriteria(List<Citizen> citizenList, int minAge, int maxAge) {
        List<Citizen> citizens = new ArrayList<>();
        citizenList.parallelStream().filter(citizen -> citizen.getAge()>minAge && citizen.getAge()<maxAge).forEach(System.out::println);
        return citizens;
    }

    public static void main(String[] args) {
        Citizen c1 = new Citizen(1,"Veera1",20);
        Citizen c2 = new Citizen(2,"Veera2",21);
        Citizen c3 = new Citizen(3,"Veera3",25);
        Citizen c4 = new Citizen(4,"Veera4",60);
        Citizen c5 = new Citizen(5,"Veera5",20);
        Citizen c6 = new Citizen(6,"Veera6",70);
        Citizen c7 = new Citizen(7,"Veera7",65);

        List<Citizen> citizenList = Arrays.asList(c1,c2,c3,c4,c5,c6,c7);

        System.out.println(".....Age under 25");

        System.out.println("....Age between 60 and 70");

    }
}

class Citizen {

    public Citizen(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    private int id;
    private String name;
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}