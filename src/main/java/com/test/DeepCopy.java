package com.test;

import com.test.copier.DeepCopier;

import java.util.List;

public class DeepCopy {

    public static void main(String[] args) {
        Man man = new Man("John", 20, List.of("book1", "book2"));
        Man friend = new Man("Jack", 21, List.of("book3", "book4"));
        man.setFriend(friend);
        friend.setFriend(man);

        Man copy = DeepCopier.deepCopy(man);
    }
} 