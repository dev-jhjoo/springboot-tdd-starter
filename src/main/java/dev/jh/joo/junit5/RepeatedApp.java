package dev.jh.joo.junit5;

import lombok.ToString;

@ToString
public class RepeatedApp {

    private int limit;
    private String name;

    public RepeatedApp(int limit, String name) {
        this.limit = limit;
        this.name = name;
    }
}
