/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpt.ptcd.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author admin
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class User {
    private String id;
    private String password;
    private String fullname;
    private String photo;
    private boolean role;         // true = admin, false = user
    private boolean enabled;
    private int groundWorkId;

}
