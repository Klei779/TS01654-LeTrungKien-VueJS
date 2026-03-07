/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpt.ptcd.dao;
import java.util.List;
import com.fpt.ptcd.entity.GroundWork;
import com.fpt.ptcd.util.XJDBC;
import java.util.ArrayList;
import java.sql.ResultSet;

/**
 *
 * @author LENOVO
 */



public class GroundWorkDAO extends CrudDAO<GroundWork, Integer> {

    final String INSERT_SQL = "INSERT INTO GroundWork(Name, Address, Phone, OpenDate, Status) VALUES (?, ?, ?, ?, ?)";
    final String UPDATE_SQL = "UPDATE GroundWork SET Name=?, Address=?, Phone=?, OpenDate=?, Status=? WHERE GroundWorkID=?";
    final String DELETE_SQL = "DELETE FROM GroundWork WHERE GroundWorkID=?";
    final String SELECT_ALL_SQL = "SELECT * FROM GroundWork";
    final String SELECT_BY_ID_SQL = "SELECT * FROM GroundWork WHERE GroundWorkID=?";
        final String UPDATE_WITH_NEW_ID = 
    "UPDATE GroundWork SET GroundWorkID=?, Name=?, Address=?, Phone=?, OpenDate=?, Status=? WHERE GroundWorkID=?";

    @Override
    public void insert(GroundWork entity) {
        XJDBC.update(INSERT_SQL,
                entity.getName(),
                entity.getAddress(),
                entity.getPhone(),
                entity.getOpenDate(),
                entity.getStatus());
    }

    @Override
    public void update(GroundWork entity) {
        XJDBC.update(UPDATE_SQL,
                entity.getName(),
                entity.getAddress(),
                entity.getPhone(),
                entity.getOpenDate(),
                entity.getStatus(),
                entity.getGroundWorkID());
    }

    @Override
    public void delete(Integer id) {
        XJDBC.update(DELETE_SQL, id);
    }

    @Override
    public List<GroundWork> selectAll() {
        return selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public GroundWork selectByID(int id) {
        List<GroundWork> list = selectBySQL(SELECT_BY_ID_SQL, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<GroundWork> selectBySQL(String sql, Object... args) {
        List<GroundWork> list = new ArrayList<>();
        try (ResultSet rs = XJDBC.query(sql, args)) {
            while (rs.next()) {
                GroundWork entity = GroundWork.builder()
                        .groundWorkID(rs.getInt("GroundWorkID"))
                        .name(rs.getString("Name"))
                        .address(rs.getString("Address"))
                        .phone(rs.getString("Phone"))
                        .openDate(rs.getDate("OpenDate"))
                        .status(rs.getString("Status"))
                        .build();
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException("Lỗi truy vấn GroundWork", e);
        }
        return list;
    }
    
    public List<GroundWork> selectByName(String name) {
    String sql = "SELECT * FROM GroundWork WHERE Name LIKE ?";
    return selectBySQL(sql, "%" + name + "%");
}

    public void updateWithNewID(GroundWork gw, int oldId) {
    XJDBC.update(UPDATE_WITH_NEW_ID,
        gw.getGroundWorkID(),
        gw.getName(),
        gw.getAddress(),
        gw.getPhone(),
        gw.getOpenDate() != null ? new java.sql.Date(gw.getOpenDate().getTime()) : null,
        gw.getStatus(),
        oldId
    );
}

    public boolean existsID(int id) {
    String sql = "SELECT COUNT(*) FROM GroundWork WHERE GroundWorkID = ?";
    try (ResultSet rs = XJDBC.query(sql, id)) {
        if (rs.next()) {
            return rs.getInt(1) > 0;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}
}