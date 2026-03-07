/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpt.ptcd.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ASUS
 */
public class XJDBC {

    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String DB_URL = "jdbc:sqlserver://localhost;database=BakeryFinal;encrypt=true;trustServerCertificate=true";
    private static final String USER = "sa1";
    private static final String PASS = "Password.1";

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Không thể load driver SQL Server", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public static PreparedStatement getStmt(Connection conn, String sql, Object... args) throws SQLException {
        PreparedStatement stmt = sql.trim().startsWith("{") ?
                conn.prepareCall(sql) : conn.prepareStatement(sql);

        for (int i = 0; i < args.length; i++) {
            stmt.setObject(i + 1, args[i]);
        }
        return stmt;
    }

    // INSERT / UPDATE / DELETE
    public static int update(String sql, Object... args) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = getStmt(conn, sql, args)) {

            return stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi thực hiện update: " + sql, e);
        }
    }

    // SELECT – trả về ResultSet (DAO phải đóng connection sau khi dùng)
    public static ResultSet query(String sql, Object... args) {
        try {
            Connection conn = getConnection();
            PreparedStatement stmt = getStmt(conn, sql, args);
            return stmt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi truy vấn: " + sql, e);
        }
    }

    // SELECT – trả về giá trị đơn
    public static Object value(String sql, Object... args) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = getStmt(conn, sql, args);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getObject(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi truy vấn giá trị: " + sql, e);
        }

        return null;
    }
}