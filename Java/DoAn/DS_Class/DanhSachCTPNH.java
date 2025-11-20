package Java.DoAn.DS_Class;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

import Java.DoAn.Class_chinh.CTPhieuNhapHang;

public class DanhSachCTPNH {
    private CTPhieuNhapHang[] dsctPNH;
    private int n;

    public DanhSachCTPNH() {
        docFile("Java/DoAn/input/inputChiTietPNH.txt");
    }

   
    public DanhSachCTPNH(String filePath) {
        docFile(filePath);
    }

    public CTPhieuNhapHang getCTPNH(int i) {
        if (i >= 0 && i < n) return dsctPNH[i];
        return null;
    }

    public int size() {
        return n;
    }

   
    public void docFile(String filePath) {
        dsctPNH = new CTPhieuNhapHang[0];
        n = 0;
        try (Scanner sc = new Scanner(new File(filePath))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty() || line.startsWith("Tong:")) continue;

                String[] parts = line.split(",");
                if (parts.length < 5) continue;

                String maPNH = parts[0].trim();
                String maSach = parts[1].trim();
                int soluong = Integer.parseInt(parts[2].trim());
                double donGia = Double.parseDouble(parts[3].trim());
                double thanhTien = Double.parseDouble(parts[4].trim());

                CTPhieuNhapHang ctPNH = new CTPhieuNhapHang(maPNH, maSach, soluong, donGia, thanhTien);
                themChiTietPNH(ctPNH, false); // Không tự động lưu khi đọc file
            }
        } catch (FileNotFoundException e) {
            System.out.println("Khong tim thay file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Loi doc file: " + e.getMessage());
        }
    }

    public void themChiTietPNH(CTPhieuNhapHang ctPNH) {
        themChiTietPNH(ctPNH, true);
    }

    public void themChiTietPNH(CTPhieuNhapHang ctPNH, boolean autoSave) {
        dsctPNH = Arrays.copyOf(dsctPNH, n + 1);
        dsctPNH[n] = ctPNH;
        n++;
        if (autoSave) {
            tuDongCapNhatFile();
        }
    }
    
    // Dùng cho DS phiếu nhập hàng

    // Cập nhật Tổng tiền, thông báo nhập, gọi hàm them(ct, autoSave) để thêm chi tiết
    public double themDanhSachChoPNH(String maPNH) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap so luong chi tiet phieu nhap hang: ");
        int soLuong = sc.nextInt();
        sc.nextLine();
        double tong = 0.0;
        for (int i = 0; i < soLuong; i++) {
            System.out.println("Nhap chi tiet thu " + (i + 1) + ":");
            System.out.print("Nhap ma sach: ");
            String maSach = sc.nextLine();
            System.out.print("Nhap so luong: ");
            int sl = sc.nextInt();
            System.out.print("Nhap don gia: ");
            double dg = sc.nextDouble();
            sc.nextLine();
            double tt = sl * dg;
            tong += tt;
            CTPhieuNhapHang ct = new CTPhieuNhapHang(maPNH, maSach, sl, dg, tt);
            themChiTietPNH(ct, false);
        }
        tuDongCapNhatFile();
        return tong;
    }
    
    

    // Sửa chi tiết theo mã PNH và mã sách
    public void suaChiTiet(String maPNH, String maSach) {
        boolean timThay = false;
        for (int i = 0; i < n; i++) {
            if (dsctPNH[i].getmaPNH().equals(maPNH) && dsctPNH[i].getmaSach().equals(maSach)) {
                    
                Scanner sc = new Scanner(System.in);
                System.out.print("Nhap so luong moi: ");
                int soLuongMoi = sc.nextInt();
                dsctPNH[i].setsoLuong(soLuongMoi);
                
                System.out.print("Nhap don gia moi: ");
                double donGiaMoi = sc.nextDouble();
                dsctPNH[i].setDonGia(donGiaMoi);
                
                tuDongCapNhatFile();
                timThay = true;
                break;  //thoát khỏi vòng for
            }
        }
        if (!timThay) {
            System.out.println("Khong tim thay chi tiet voi ma PNH: " + maPNH + " va ma sach: " + maSach);
        }
    }
    
    // Xóa tất cả chi tiết theo mã PNH
    public void xoaChiTietTheoMaPNH(String maPNH) {
        int i = 0;
        while (i < n) {
            if (dsctPNH[i].getmaPNH().equals(maPNH)) {
                for (int j = i; j < n - 1; j++) {
                    dsctPNH[j] = dsctPNH[j + 1];
                }
                dsctPNH = Arrays.copyOf(dsctPNH, n - 1);
                n--;
            } else {
                i++;
            }
        }
        tuDongCapNhatFile();
    }

    public void timKiemTheoMaPNH(String maPNH) {
        System.out.println("\n=== KET QUA TIM KIEM ===");
        System.out.printf("%-10s %-15s %-10s %12s %12s%n", "Ma PNH", "Ma Sach", "So Luong", "Don Gia", "Thanh Tien");
        System.out.println("-------------------------------------------------------------------");
        
        boolean timThay = false;
        for (int i = 0; i < n; i++) {
            if (dsctPNH[i].getmaPNH().equalsIgnoreCase(maPNH)) {
                dsctPNH[i].xuat();
                timThay = true;
            }
        }
        
        if (!timThay) {
            System.out.println("Khong tim thay chi tiet nao voi ma PNH: " + maPNH);
        }
    }

    public void timKiemTheoMaSach(String maSach) {
        System.out.println("\n=== KET QUA TIM KIEM ===");
        System.out.printf("%-10s %-15s %-10s %12s %12s%n", 
            "Ma PNH", "Ma Sach", "So Luong", "Don Gia", "Thanh Tien");
        System.out.println("-------------------------------------------------------------------");
        
        boolean timThay = false;
        for (int i = 0; i < n; i++) {
            if (dsctPNH[i].getmaSach().equalsIgnoreCase(maSach)) {
                dsctPNH[i].xuat();
                timThay = true;
            }
        }
        
        if (!timThay) {
            System.out.println("Khong tim thay chi tiet nao voi ma sach: " + maSach);
        }
        
    }

    public void xuatDanhSach() {
        if (n == 0) {
            System.out.println("Danh sach rong!");
            return;
        }
        
        System.out.println("\n=== DANH SACH CHI TIET PHIEU NHAP HANG ===");
        System.out.printf("%-10s %-15s %-10s %12s %12s%n", 
            "Ma PNH", "Ma Sach", "So Luong", "Don Gia", "Thanh Tien");
        System.out.println("-------------------------------------------------------------------");
        
        for (int i = 0; i < n; i++) {
            dsctPNH[i].xuat();
        }
        System.out.println("Tong so chi tiet: " + n);
    }

    public int tongSoCT() {
        return n;
    }

    public int tongSoLuongNhap() {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += dsctPNH[i].getsoLuong();
        }
        return sum;
    }

    public double tongThanhTien() {
        double sum = 0.0;
        for (int i = 0; i < n; i++) {
            sum += dsctPNH[i].getThanhTien();
        }
        return sum;
    }

    // Tự động cập nhật file sau khi thay đổi dữ liệu
    public void tuDongCapNhatFile() {
        ghiFile();
    }

    // Ghi file
    public void ghiFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("Java/DoAn/input/inputChiTietPNH.txt"))) {
            for (int i = 0; i < n; i++) {
                writer.println(dsctPNH[i].getmaPNH() + "," + 
                              dsctPNH[i].getmaSach() + "," + 
                              dsctPNH[i].getsoLuong() + "," + 
                              dsctPNH[i].getDonGia() + "," + 
                              dsctPNH[i].getThanhTien());
            }
            writer.println("Tong: " + n);
        } catch (IOException e) {
            System.out.println("Loi ghi file: " + e.getMessage());
        }
    }
}