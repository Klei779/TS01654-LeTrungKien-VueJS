/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpt.ptcd.dao;

import com.fpt.ptcd.entity.Category;
import com.fpt.ptcd.util.XJDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class CategoryDAO extends CrudDAO<Category, Integer> {

    final String INSERT_SQL = "INSERT INTO Category (ID, Name) VALUES (?, ?)";
    final String UPDATE_SQL = "UPDATE Category SET Name = ? WHERE ID = ?";
    final String DELETE_SQL = "DELETE FROM Category WHERE ID = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM Category";
    final String SELECT_BY_ID_SQL = "SELECT * FROM Category WHERE ID = ?";

    @Override
    public void insert(Category entity) {
        XJDBC.update(INSERT_SQL,
                entity.getId(),
                entity.getName()
        );
    }

    @Override
    public void update(Category entity) {
        XJDBC.update(UPDATE_SQL,
                entity.getName(),
                entity.getId()
        );
    }

    @Override
    public void delete(Integer id) {
        XJDBC.update(DELETE_SQL, id);
    }

    @Override
    public List<Category> selectAll() {
        return selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public Category selectByID(int id) {
        List<Category> list = selectBySQL(SELECT_BY_ID_SQL, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<Category> selectBySQL(String sql, Object... args) {
        List<Category> list = new ArrayList<>();
        try (ResultSet rs = XJDBC.query(sql, args)) {
            while (rs.next()) {
                Category entity = Category.builder()
                        .Id(rs.getInt("ID"))
                        .name(rs.getString("Name"))
                        .build();
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi truy vấn dữ liệu Category", e);
        }
    }
}

