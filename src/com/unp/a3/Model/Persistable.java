package com.unp.a3.Model;

public interface Persistable {
    boolean save();
    boolean update();
    boolean delete();
}