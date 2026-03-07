/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpt.ptcd.entity;

import lombok.Builder;
import lombok.Data;

/**
 *
 * @author admin
 */


@Data
@Builder
public class BillDetails {
    private Integer id;
    private Integer billId;
    private Integer bakeryId;
    private String productName;
    private String unit;
    private Double quantity;
    private Double unitPrice;
    private Double discount;
    private Double finalPrice;
}