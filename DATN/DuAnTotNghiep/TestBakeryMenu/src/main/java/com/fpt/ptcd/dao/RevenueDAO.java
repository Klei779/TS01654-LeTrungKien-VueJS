/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpt.ptcd.dao;

import com.fpt.ptcd.util.XJDBC;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class RevenueDAO {

    public List<Object[]> getRevenueByCategory() {
        String sql = """
SELECT 
            c.Name,
            SUM(bk.UnitPrice * bd.Quantity * (1 - bk.Discount)),
            SUM(bd.Quantity),
            MIN(bk.UnitPrice),
            MAX(bk.UnitPrice),
            AVG(bk.UnitPrice)
        FROM Bill b
        JOIN BillDetails bd ON b.ID = bd.BillID
        JOIN Bakery bk ON bd.BakeryID = bk.ID
        JOIN Category c ON bk.CategoryID = c.ID
        GROUP BY c.Name;
    """;

        List<Object[]> list = new ArrayList<>();
        try (ResultSet rs = XJDBC.query(sql)) {
            while (rs.next()) {
                Object[] row = {
                    rs.getString(1),
                    rs.getDouble(2),
                    rs.getInt(3),
                    rs.getDouble(4),
                    rs.getDouble(5),
                    rs.getDouble(6)
                };
                list.add(row);
            }
        } catch (Exception e) {
            throw new RuntimeException("Lỗi truy vấn doanh thu: " + e.getMessage());
        }

        return list;
    }
}
