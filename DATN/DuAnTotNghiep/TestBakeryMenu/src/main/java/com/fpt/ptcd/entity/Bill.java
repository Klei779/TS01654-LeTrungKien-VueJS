/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpt.ptcd.entity;

import lombok.Builder;
import lombok.Data;
import java.util.Date;
/**
 *
 * @author admin
 */

@Data
@Builder
public class Bill {
    private Integer id;
    private Date checkIn;
    private Date checkOut;
    private Boolean status;
    private String username;
    private Integer cardId;
    private Integer paymentMethod;
    private Integer groundWorkId;
}