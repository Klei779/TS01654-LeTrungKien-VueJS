/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.fpt.ptcd.dao;

import java.util.List;
/**
 *
 * @author LENOVO
 
 */
public abstract class CrudDAO<EntityType, KeyType> {

    public abstract void insert(EntityType entity);

    public abstract void update(EntityType entity);

    public abstract void delete(KeyType id);

    public abstract List<EntityType> selectAll();

    public abstract EntityType selectByID(int id);

    public abstract List<EntityType> selectBySQL(String sql, Object... args);
}

