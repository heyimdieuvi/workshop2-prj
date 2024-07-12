/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;


import java.util.List;

/**
 *
 * @author ADMIN
 * @param <T>
 */
public interface Accessible<T>{
    int insertRec(T obj);
    int updateRec(T obj);
    int deleteRec(String id);
    T getObjectById (String id);
    List<T> listAll();
    
}
