/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
/**
 *
 * @author LENOVO
 */
public class Bill {
     private Long id; 
    private String username; 
    private Integer cardId; 
    @Builder.Default 
    private Date checkin = new Date(); 
    private Date checkout; 
    private int status;

public Bill(Long id, String cardId, Date checkin, Date checkout, String status, String user) {
    this.id = id;
    this.cardId = Integer.valueOf(cardId);
    this.checkin = checkin;
    this.checkout = checkout;
    this.status = parseStatus(status); // Sử dụng map thay vì parseInt
    this.username = user;
}
private static int parseStatus(String status) {
    return switch (status.toLowerCase()) {
        case "servicing" -> 0;
        case "completed" -> 1;
        case "canceled" -> 2;
        default -> -1; // hoặc throw new IllegalArgumentException()
    };
}
}



