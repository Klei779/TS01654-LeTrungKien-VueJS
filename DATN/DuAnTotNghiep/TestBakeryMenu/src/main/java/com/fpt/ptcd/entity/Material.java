/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpt.ptcd.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

/**
 *
 * @author admin
 */
@Data
@Builder
public class Material {
    private int materialID;
    private String name;
    private String unit;
    private double quantity;
    private String supplier;
    private Date importDate;
    private String photo;
    private int typeID;
    private String note;
}

