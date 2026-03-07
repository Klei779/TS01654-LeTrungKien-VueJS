/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpt.ptcd.entity;

/**
 *
 * @author admin
 */
import lombok.Builder;
import lombok.Data;
import java.util.Date;

@Data
@Builder
public class FullBillDTO {
    private Integer id;
    private String username;
    private Date checkIn;
    private Date checkOut;
    private Integer status;
    private Integer paymentMethod;
    private Integer cardId;
    private Integer groundWorkId;

    // Bakery chi tiết từ BillDetails
    private Float quantity;
    private String bakeryName;
    private Double unitPrice;
    private Double discount;
    private String photo;
    private Boolean available;
    private Integer categoryId;
    private String categoryName;

    // Nếu sau này muốn mở rộng: có thể thêm List<BakeryDTO> bakeryList;
}