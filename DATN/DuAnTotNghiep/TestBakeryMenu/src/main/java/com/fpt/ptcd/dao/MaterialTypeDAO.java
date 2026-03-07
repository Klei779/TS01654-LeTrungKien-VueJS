/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpt.ptcd.dao;

import com.fpt.ptcd.entity.MaterialType;
import com.fpt.ptcd.util.XJDBC;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class MaterialTypeDAO extends CrudDAO<MaterialType, Integer> {

    final String INSERT_SQL = "INSERT INTO MaterialType (TypeID, TypeName) VALUES (?, ?)";
    final String UPDATE_SQL = "UPDATE MaterialType SET TypeName = ? WHERE TypeID = ?";
    final String DELETE_SQL = "DELETE FROM MaterialType WHERE TypeID = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM MaterialType";
    final String SELECT_BY_ID_SQL = "SELECT * FROM MaterialType WHERE TypeID = ?";

    @Override
    public void insert(MaterialType entity) {
        XJDBC.update(INSERT_SQL, entity.getId(), entity.getName());
    }

    @Override
    public void update(MaterialType entity) {
        XJDBC.update(UPDATE_SQL, entity.getId(), entity.getName());
    }

    @Override
    public void delete(Integer id) {
        XJDBC.update(DELETE_SQL, id);
    }

    @Override
    public List<MaterialType> selectAll() {
        return selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public MaterialType selectByID(int id) {
        List<MaterialType> list = selectBySQL(SELECT_BY_ID_SQL, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<MaterialType> selectBySQL(String sql, Object... args) {
        List<MaterialType> list = new ArrayList<>();
        try (ResultSet rs = XJDBC.query(sql, args)) {
            while (rs.next()) {
                MaterialType mt = MaterialType.builder()
                        .Id(rs.getInt("TypeID"))
                        .name(rs.getString("TypeName"))
                        .build();
                list.add(mt);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}

