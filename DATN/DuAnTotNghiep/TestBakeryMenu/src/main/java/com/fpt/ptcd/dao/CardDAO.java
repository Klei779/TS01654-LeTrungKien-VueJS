package com.fpt.ptcd.dao;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.fpt.ptcd.entity.Card;
import com.fpt.ptcd.util.XJDBC;

/**
 *
 * @author LENOVO
 */

public class CardDAO extends CrudDAO<Card, Integer> {

    private final String INSERT_SQL = "INSERT INTO Card (ID, status) VALUES (?, ?)";
    private final String UPDATE_SQL = "UPDATE Card SET status = ? WHERE id = ?";
    private final String DELETE_SQL = "DELETE FROM Card WHERE id = ?";
    private final String SELECT_ALL_SQL = "SELECT * FROM Card";
    private final String SELECT_BY_ID_SQL = "SELECT * FROM Card WHERE id = ?";

    @Override
   
public void insert(Card entity) {
    XJDBC.update(INSERT_SQL, entity.getId(), entity.getStatus());
}


    @Override
    public void update(Card entity) {
        XJDBC.update(UPDATE_SQL, entity.getStatus(), entity.getId());
    }

    @Override
    public void delete(Integer id) {
        XJDBC.update(DELETE_SQL, id);
    }

    @Override
    public List<Card> selectAll() {
        return selectBySQL(SELECT_ALL_SQL);
    }
    public List<Card> selectByStatus(int status) {
    String sql = "SELECT * FROM Card WHERE status = ?";
    return selectBySQL(sql, status);
}


    public Card selectByID(int id) {
        List<Card> list = selectBySQL(SELECT_BY_ID_SQL, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<Card> selectBySQL(String sql, Object... args) {
        List<Card> list = new ArrayList<>();
        try {
            ResultSet rs = XJDBC.query(sql, args);
            while (rs.next()) {
                Card entity = new Card();
                entity.setId(rs.getInt("id"));
                entity.setStatus(rs.getInt("status"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            throw new RuntimeException("Lỗi truy vấn Card: " + e.getMessage(), e);
        }
        return list;
    }
    public List<Card> selectByStatuses(List<Integer> statuses) {
    if (statuses == null || statuses.isEmpty()) {
        return new ArrayList<>(); // Trả về rỗng nếu không chọn gì
    }

    // Tạo chuỗi dấu hỏi ?,?,?,... tuỳ số lượng status
    String placeholders = String.join(",", statuses.stream().map(s -> "?").toArray(String[]::new));
    String sql = "SELECT * FROM Card WHERE status IN (" + placeholders + ")";
    return selectBySQL(sql, statuses.toArray());
}


}
