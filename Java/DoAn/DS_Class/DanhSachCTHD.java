package Java.DoAn.DS_Class;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

import Java.DoAn.Class_chinh.ChiTietHoaDon;

public class DanhSachCTHD {
    private ChiTietHoaDon[] dscthd;
    private int n;

    //Đọc file:
    public void docFile(String filePath) {
        dscthd = new ChiTietHoaDon[0];
        n = 0;
        try (Scanner sc = new Scanner(new File(filePath))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty() || line.startsWith("Tong:")) continue;

                String[] parts = line.split(",");
                if (parts.length < 5) continue;  //đủ 5 thuộc tính

                String mahd = parts[0].trim();
                String masach = parts[1].trim();
                int soluong = Integer.parseInt(parts[2].trim());
                double dongia = Double.parseDouble(parts[3].trim());
                double thanhtien = Double.parseDouble(parts[4].trim());

                ChiTietHoaDon cthd = new ChiTietHoaDon(mahd, masach, soluong, dongia, thanhtien);
                themChiTietHoaDon(cthd);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Khong tim thay file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Loi doc file: " + e.getMessage());
        }
    }

    // Tự động cập nhật file sau khi thay đổi dữ liệu
    public void tuDongCapNhatFile() {
        ghiFile();
    }

    //Ghi File:
    public void ghiFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("Java/DoAn/input/inputChiTietHD.txt"))) {
            for (int i=0;i<n;i++) {
                writer.println(dscthd[i].getMaHD() + "," + 
                              dscthd[i].getMaSach() + "," + 
                              dscthd[i].getSoLuong() + "," + 
                              dscthd[i].getDonGia() + "," + 
                              dscthd[i].getThanhTien());
            }
            writer.println("Tong: " + n);
        }
        catch (IOException e) {
            System.out.println("Loi ghi file: " + e.getMessage());
        }
    }

    //Thêm:
    public void themChiTietHoaDon(ChiTietHoaDon cthd) {
        themChiTietHoaDon(cthd, true);
    }

    // Thêm chi tiết với tùy chọn tự động cập nhật file
   public void themChiTietHoaDon (ChiTietHoaDon cthd, boolean autoSave) {
        dscthd = Arrays.copyOf(dscthd, n + 1);
        dscthd[n] = cthd;
        n++;
        if (autoSave) {
            tuDongCapNhatFile();
        }
    }
    
    // Dùng cho DS hóa đơn
    public int size() {
        return n;
    }

    //get:
    public ChiTietHoaDon getCTHD(int i) {
        if (i >= 0 && i < n) return dscthd[i];
        return null;
    }

    // Xóa tất cả chi tiết hóa đơn theo mã hóa đơn
    public void xoaChiTietTheoMaHD(String maHD) {
        int i = 0;
        while (i < n) {
            if (dscthd[i].getMaHD().equals(maHD)) {
                for (int j = i; j < n - 1; j++) {
                    dscthd[j] = dscthd[j + 1];
                }
                dscthd = Arrays.copyOf(dscthd, n - 1);
                n--;
            } else {
                i++;
            }
        }
        tuDongCapNhatFile();
    }
    
}