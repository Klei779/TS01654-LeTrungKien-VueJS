/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpt.ptcd.dao;

import com.fpt.ptcd.entity.User;
import com.fpt.ptcd.util.XJDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */

public class UserDAO extends CrudDAO<User, String> {

    final String INSERT_SQL = "INSERT INTO Users (ID, Password, Fullname, Photo, Role, Enabled, GroundWorkID) VALUES (?, ?, ?, ?, ?, ?, ?)";
    final String UPDATE_SQL = "UPDATE Users SET Password = ?, Fullname = ?, Photo = ?, Role = ?, Enabled = ?, GroundWorkID = ? WHERE ID = ?";
    final String DELETE_SQL = "DELETE FROM Users WHERE ID = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM Users";
    final String SELECT_BY_ID_SQL = "SELECT * FROM Users WHERE ID = ?";

    @Override
    public void insert(User entity) {
        XJDBC.update(INSERT_SQL,
                entity.getId(),
                entity.getPassword(),
                entity.getFullname(),
                entity.getPhoto(),
                entity.isRole(),
                entity.isEnabled(),
                entity.getGroundWorkId()
        );
    }

    @Override
    public void update(User entity) {
        XJDBC.update(UPDATE_SQL,
                entity.getPassword(),
                entity.getFullname(),
                entity.getPhoto(),
                entity.isRole(),
                entity.isEnabled(),
                entity.getGroundWorkId(),
                entity.getId()
        );
    }

    @Override
    public void delete(String id) {
        XJDBC.update(DELETE_SQL, id);
    }

    @Override
    public List<User> selectAll() {
        return selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public User selectByID(int id) {
        // Vì ID của User là String nên ta ném exception nếu dùng nhầm
        throw new UnsupportedOperationException("Use selectByID(String id) instead of int");
    }

    // Overload đúng kiểu
    public User selectByID(String id) {
        List<User> list = selectBySQL(SELECT_BY_ID_SQL, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<User> selectBySQL(String sql, Object... args) {
        List<User> list = new ArrayList<>();
        try (ResultSet rs = XJDBC.query(sql, args)) {
            while (rs.next()) {
                User entity = User.builder()
                        .id(rs.getString("ID"))
                        .password(rs.getString("Password"))
                        .fullname(rs.getString("Fullname"))
                        .photo(rs.getString("Photo"))
                        .role(rs.getBoolean("Role"))
                        .enabled(rs.getBoolean("Enabled"))
                        .groundWorkId(rs.getInt("GroundWorkID"))
                        .build();
                list.add(entity);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi truy vấn dữ liệu Users", e);
        }
        return list;
    }
}