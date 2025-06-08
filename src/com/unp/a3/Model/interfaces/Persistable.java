package com.unp.a3.Model.interfaces;

public interface Persistable {
    boolean save();
    boolean update();
    boolean delete();
}