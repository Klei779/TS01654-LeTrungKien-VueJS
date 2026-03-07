/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.fpt.ptcd.ui.frame;

import com.fpt.ptcd.ui.dialog.ChangePasswordJDialog;
import com.fpt.ptcd.ui.dialog.WellcomeJDialog;
import com.fpt.ptcd.ui.dialog.LoginJDialog;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author admin
 */
public interface BakeryController {

    void init();

    default void exit() {
        int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn kết thúc không?", "Xác nhận thoát",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (choice == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else {
            JOptionPane.showMessageDialog(null, "Chương trình sẽ tiếp tục...");
        }
    }

    default void showJDialog(JDialog dialog) {
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    default void ChangePasswordJDialog(JFrame frame) {
        this.showJDialog(new ChangePasswordJDialog(frame, true));
    }

    default void LoginJDialog(JFrame frame) {
        this.showJDialog(new LoginJDialog(frame, true));
    }

    default void WellcomeJDialog(JFrame frame) {
        this.showJDialog(new WellcomeJDialog(frame, true));
    }

}
