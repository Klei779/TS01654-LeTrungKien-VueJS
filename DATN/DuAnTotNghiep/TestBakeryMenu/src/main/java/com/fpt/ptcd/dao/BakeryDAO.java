/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpt.ptcd.dao;

import com.fpt.ptcd.entity.Bakery;
import com.fpt.ptcd.util.XJDBC;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */

public class BakeryDAO extends CrudDAO<Bakery, Integer> {

    private final String INSERT_SQL = "INSERT INTO Bakery (ID, Name, UnitPrice, Discount, Photo, Available, CategoryID, Note) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private final String UPDATE_SQL = "UPDATE Bakery SET Name = ?, UnitPrice = ?, Discount = ?, Photo = ?, Available = ?, CategoryID = ?, Note = ? WHERE ID = ?";
    private final String DELETE_SQL = "DELETE FROM Bakery WHERE ID = ?";
    private final String SELECT_ALL_SQL = "SELECT * FROM Bakery";
    private final String SELECT_BY_ID_SQL = "SELECT * FROM Bakery WHERE ID = ?";

    @Override
    public void insert(Bakery entity) {
        XJDBC.update(INSERT_SQL,
            entity.getId(),
            entity.getName(),
            entity.getUnitPrice(),
            entity.getDiscount(),
            entity.getPhoto(),
            entity.isAvailable(),
            entity.getCategoryId(),
            entity.getNote()
        );
    }

    @Override
    public void update(Bakery entity) {
        XJDBC.update(UPDATE_SQL,
            entity.getName(),
            entity.getUnitPrice(),
            entity.getDiscount(),
            entity.getPhoto(),
            entity.isAvailable(),
            entity.getCategoryId(),
            entity.getNote(),
            entity.getId()
        );
    }

    @Override
    public void delete(Integer id) {
        XJDBC.update(DELETE_SQL, id);
    }

    @Override
    public List<Bakery> selectAll() {
        return selectBySQL(SELECT_ALL_SQL);
    }

    public Bakery selectByID(int id) {
        List<Bakery> list = selectBySQL(SELECT_BY_ID_SQL, id);
        return list.isEmpty() ? null : list.get(0);
    }

    public List<Bakery> selectByAvailable(boolean available) {
        String sql = "SELECT * FROM Bakery WHERE Available = ?";
        return selectBySQL(sql, available);
    }

    public List<Bakery> selectByCategory(int categoryId) {
        String sql = "SELECT * FROM Bakery WHERE CategoryID = ?";
        return selectBySQL(sql, categoryId);
    }

    public List<Bakery> selectByAvailables(List<Boolean> availables) {
        if (availables == null || availables.isEmpty()) {
            return new ArrayList<>();
        }

        String placeholders = String.join(",", availables.stream().map(a -> "?").toArray(String[]::new));
        String sql = "SELECT * FROM Bakery WHERE Available IN (" + placeholders + ")";
        return selectBySQL(sql, availables.toArray());
    }

    @Override
    public List<Bakery> selectBySQL(String sql, Object... args) {
        List<Bakery> list = new ArrayList<>();
        try {
            ResultSet rs = XJDBC.query(sql, args);
            while (rs.next()) {
                Bakery b = new Bakery();
                b.setId(rs.getInt("ID"));
                b.setName(rs.getString("Name"));
                b.setUnitPrice(rs.getFloat("UnitPrice"));
                b.setDiscount(rs.getFloat("Discount"));
                b.setPhoto(rs.getString("Photo"));
                b.setAvailable(rs.getBoolean("Available"));
                b.setCategoryId(rs.getInt("CategoryID"));
                b.setNote(rs.getString("Note"));
                list.add(b);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            throw new RuntimeException("Lỗi truy vấn Bakery: " + e.getMessage(), e);
        }
        return list;
    }
}

