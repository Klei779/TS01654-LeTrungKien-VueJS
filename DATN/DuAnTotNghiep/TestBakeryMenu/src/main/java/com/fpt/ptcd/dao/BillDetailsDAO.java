/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpt.ptcd.dao;

import com.fpt.ptcd.entity.BillDetails;
import com.fpt.ptcd.util.XJDBC;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */

public class BillDetailsDAO extends CrudDAO<BillDetails, Integer> {

    final String UPDATE_SQL = """
        UPDATE BillDetails SET BillID=?, BakeryID=?, ProductName=?, Unit=?, Quantity=?, UnitPrice=?, Discount=?, FinalPrice=?
        WHERE ID=?
    """;

    final String DELETE_SQL = "DELETE FROM BillDetails WHERE ID=?";
    final String SELECT_ALL_SQL = "SELECT * FROM BillDetails";
    final String SELECT_BY_ID_SQL = "SELECT * FROM BillDetails WHERE ID=?";

@Override
public void insert(BillDetails bd) {
    String INSERT_SQL = """
        INSERT INTO BillDetails (BillID, BakeryID, ProductName, Unit, Quantity, UnitPrice, Discount, FinalPrice)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?)
    """;

    XJDBC.update(INSERT_SQL,
            bd.getBillId(),
            bd.getBakeryId(),
            bd.getProductName(),
            bd.getUnit(),
            bd.getQuantity(),
            bd.getUnitPrice(),
            bd.getDiscount(),
            bd.getFinalPrice());
}

    @Override
    public void update(BillDetails bd) {
        XJDBC.update(UPDATE_SQL,
                bd.getBillId(),
                bd.getBakeryId(),
                bd.getProductName(),
                bd.getUnit(),
                bd.getQuantity(),
                bd.getUnitPrice(),
                bd.getDiscount(),
                bd.getFinalPrice(),
                bd.getId());
    }

    @Override
    public void delete(Integer id) {
        XJDBC.update(DELETE_SQL, id);
    }

    @Override
    public List<BillDetails> selectAll() {
        return selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public BillDetails selectByID(int id) {
        List<BillDetails> list = selectBySQL(SELECT_BY_ID_SQL, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<BillDetails> selectBySQL(String sql, Object... args) {
        List<BillDetails> list = new ArrayList<>();
        try (ResultSet rs = XJDBC.query(sql, args)) {
            while (rs.next()) {
                BillDetails bd = BillDetails.builder()
                        .id(rs.getInt("ID"))
                        .billId(rs.getInt("BillID"))
                        .bakeryId(rs.getInt("BakeryID"))
                        .productName(rs.getString("ProductName"))
                        .unit(rs.getString("Unit"))
                        .quantity(rs.getDouble("Quantity"))
                        .unitPrice(rs.getDouble("UnitPrice"))
                        .discount(rs.getDouble("Discount"))
                        .finalPrice(rs.getDouble("FinalPrice"))
                        .build();
                list.add(bd);
            }
        } catch (Exception e) {
            throw new RuntimeException("Lỗi truy vấn BillDetails: " + e.getMessage(), e);
        }
        return list;
    }
    
    public List<BillDetails> selectByBillId(int billId) {
    String sql = "SELECT * FROM BillDetails WHERE BillID = ?";
    return selectBySQL(sql, billId);
}
}