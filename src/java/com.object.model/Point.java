package com.object.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Point implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;
    private int x;
    private int y;

    public Point(){

    }

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public long getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", x, y);
    }
}