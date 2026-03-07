/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpt.ptcd.dao;

import com.fpt.ptcd.entity.Bill;
import com.fpt.ptcd.util.XJDBC;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BillDAO extends CrudDAO<Bill, Integer> {

    final String INSERT_SQL = """
        INSERT INTO Bill (ID, CheckIn, CheckOut, Status, Username, CardID, PaymentMethod, GroundWorkID)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?)
    """;

    final String UPDATE_SQL = """
        UPDATE Bill SET CheckIn=?, CheckOut=?, Status=?, Username=?, CardID=?, PaymentMethod=?, GroundWorkID=?
        WHERE ID=?
    """;

    final String DELETE_SQL = "DELETE FROM Bill WHERE ID=?";
    final String SELECT_ALL_SQL = "SELECT * FROM Bill";
    final String SELECT_BY_ID_SQL = "SELECT * FROM Bill WHERE ID=?";

    @Override
    public void insert(Bill bill) {
        XJDBC.update(INSERT_SQL,
                bill.getId(),
                bill.getCheckIn(),
                bill.getCheckOut(),
                bill.getStatus(),
                bill.getUsername(),
                bill.getCardId(),
                bill.getPaymentMethod(),
                bill.getGroundWorkId());
    }

    @Override
    public void update(Bill bill) {
        XJDBC.update(UPDATE_SQL,
                bill.getCheckIn(),
                bill.getCheckOut(),
                bill.getStatus(),
                bill.getUsername(),
                bill.getCardId(),
                bill.getPaymentMethod(),
                bill.getGroundWorkId(),
                bill.getId());
    }

    @Override
    public void delete(Integer id) {
        XJDBC.update(DELETE_SQL, id);
    }

    @Override
    public List<Bill> selectAll() {
        return selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public Bill selectByID(int id) {
        List<Bill> list = selectBySQL(SELECT_BY_ID_SQL, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<Bill> selectBySQL(String sql, Object... args) {
        List<Bill> list = new ArrayList<>();
        try (ResultSet rs = XJDBC.query(sql, args)) {
            while (rs.next()) {
                Bill bill = Bill.builder()
                        .id(rs.getInt("ID"))
                        .checkIn(rs.getDate("CheckIn"))
                        .checkOut(rs.getDate("CheckOut"))
                        .status(rs.getBoolean("Status"))
                        .username(rs.getString("Username"))
                        .cardId(rs.getInt("CardID"))
                        .paymentMethod(rs.getInt("PaymentMethod"))
                        .groundWorkId(rs.getInt("GroundWorkID"))
                        .build();
                list.add(bill);
            }
        } catch (Exception e) {
            throw new RuntimeException("Lỗi truy vấn Bill: " + e.getMessage(), e);
        }
        return list;
    }
}
