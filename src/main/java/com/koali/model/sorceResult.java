package com.koali.model;

/**
 * Created by Elric on 2017/7/8.
 */
public class sorceResult {
    public int value;
    public String name;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public sorceResult(int value, String name) {
        this.value = value;
        this.name = name;
    }

    @Override
    public String toString() {
        return "sorceResult{" +
                "value=" + value +
                ", name='" + name + '\'' +
                '}';
    }
}
