/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpt.ptcd.util;

import java.awt.Image;
import java.awt.MediaTracker;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;

public class XImage {

    // Hiện logo trên mỗi cửa sổ giao diện
    public static Image getAppIcon() {
        URL url = XImage.class.getResource("/poly/cafe/icons/trump-small.png");
        return new ImageIcon(url).getImage();
    }

    // Hàm này dùng để sao chép file/hình ảnh
    public static boolean save(File src) {
        // Tạo đường dẫn đến nơi lưu trữ file đích (đặt tên theo tên file gốc)
        File dst = new File("src\\main\\resources\\fpt\\ptcd\\icons", src.getName());

        if (!dst.getParentFile().exists()) { //Kiểm tra xem thư mục cha có tồn tại không
            dst.getParentFile().mkdirs(); // tạo thư mục cha nếu chưa có
        }

        try {
            // Lấy đường dẫn tuyệt đối của file nguồn và file đích
            Path from = Paths.get(src.getAbsolutePath());
            Path to = Paths.get(dst.getAbsolutePath());
            // Sao chép file từ nguồn sang đích, ghi đè nếu đã tồn tại
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //Hàm Hiện thị hình đã upload
    public static ImageIcon read(String fileName) {
        File path = new File("src\\main\\resources\\fpt\\ptcd\\icons", fileName);
        return new ImageIcon(path.getAbsolutePath());
    }

    // đọc ảnh load lên label theo kích thước của Label:
    public static ImageIcon readScaled(String fileName, JLabel label) {
        File path = new File("src\\main\\resources\\fpt\\ptcd\\icons", fileName);
        ImageIcon icon = new ImageIcon(path.getAbsolutePath());

        int width = label.getWidth();
        int height = label.getHeight();

        Image scaled = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }

public static ImageIcon readScaled(String fileName, JComponent comp) {
    File path = new File("src\\main\\resources\\fpt\\ptcd\\icons", fileName);
    ImageIcon icon = new ImageIcon(path.getAbsolutePath());

    if (icon.getIconWidth() == -1 || icon.getImageLoadStatus() != MediaTracker.COMPLETE) {
        System.err.println("❌ [XImage] Không thể load ảnh: " + path.getAbsolutePath());
    } else {
        System.out.println("✅ [XImage] Ảnh load thành công: " + fileName);
        System.out.println("   → Path: " + path.getAbsolutePath());
        System.out.println("   → Size gốc: " + icon.getIconWidth() + "x" + icon.getIconHeight());
    }

    int width = comp.getWidth() == 0 ? 100 : comp.getWidth();
    int height = comp.getHeight() == 0 ? 100 : comp.getHeight();
    Image scaled = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
    return new ImageIcon(scaled);
}

}
