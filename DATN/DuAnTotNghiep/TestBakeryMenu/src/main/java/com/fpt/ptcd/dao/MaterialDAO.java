package com.fpt.ptcd.dao;

import com.fpt.ptcd.entity.Material;
import com.fpt.ptcd.util.XJDBC;
import java.sql.*;
import java.util.*;

public class MaterialDAO extends CrudDAO<Material, Integer> {

    private final String INSERT_SQL = "INSERT INTO Material (MaterialID, Name, Unit, Quantity, Supplier, ImportDate, Photo, TypeID, Note) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String UPDATE_SQL = "UPDATE Material SET Name = ?, Unit = ?, Quantity = ?, Supplier = ?, ImportDate = ?, Photo = ?, TypeID = ?, Note = ? WHERE MaterialID = ?";
    private final String DELETE_SQL = "DELETE FROM Material WHERE MaterialID = ?";
    private final String SELECT_ALL_SQL = "SELECT * FROM Material";
    private final String SELECT_BY_ID_SQL = "SELECT * FROM Material WHERE MaterialID = ?";

    @Override
    public void insert(Material entity) {
        XJDBC.update(INSERT_SQL,
            entity.getMaterialID(),
            entity.getName(),
            entity.getUnit(),
            entity.getQuantity(),
            entity.getSupplier(),
            entity.getImportDate(),
            entity.getPhoto(),
            entity.getTypeID(),
            entity.getNote()
        );
    }

    @Override
    public void update(Material entity) {
        XJDBC.update(UPDATE_SQL,
            entity.getName(),
            entity.getUnit(),
            entity.getQuantity(),
            entity.getSupplier(),
            entity.getImportDate(),
            entity.getPhoto(),
            entity.getTypeID(),
            entity.getNote(),
            entity.getMaterialID()
        );
    }

    @Override
    public void delete(Integer id) {
        XJDBC.update(DELETE_SQL, id);
    }

    @Override
    public List<Material> selectAll() {
        return selectBySQL(SELECT_ALL_SQL);
    }

    public Material selectByID(int id) {
        List<Material> list = selectBySQL(SELECT_BY_ID_SQL, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<Material> selectBySQL(String sql, Object... args) {
        List<Material> list = new ArrayList<>();
        try (ResultSet rs = XJDBC.query(sql, args)) {
            while (rs.next()) {
                Material m = Material.builder()
                    .materialID(rs.getInt("MaterialID"))
                    .name(rs.getString("Name"))
                    .unit(rs.getString("Unit"))
                    .quantity(rs.getDouble("Quantity"))
                    .supplier(rs.getString("Supplier"))
                    .importDate(rs.getDate("ImportDate"))
                    .photo(rs.getString("Photo"))
                    .typeID(rs.getInt("TypeID"))
                    .note(rs.getString("Note"))
                    .build();
                list.add(m);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            throw new RuntimeException("Lỗi truy vấn Material: " + e.getMessage(), e);
        }
        return list;
    }
}
