package com.test;

import java.util.List;
import java.util.Objects;

public class Man {

    private String name;
    private Integer age;
    private List<String> favoriteBooks;
    private Man friend;

    // Default constructor required for reflection
    public Man() {
    }

    public Man(String name, int age, List<String> favoriteBooks) {
        this.name = name;
        this.age = age;
        this.favoriteBooks = favoriteBooks;
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

    public List<String> getFavoriteBooks() {
        return favoriteBooks;
    }

    public void setFavoriteBooks(List<String> favoriteBooks) {
        this.favoriteBooks = favoriteBooks;
    }

    public Man getFriend() {
        return friend;
    }

    public void setFriend(Man friend) {
        this.friend = friend;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Man man = (Man) o;
        return age == man.age &&
                Objects.equals(name, man.name) &&
                Objects.equals(favoriteBooks, man.favoriteBooks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, favoriteBooks);
    }
}