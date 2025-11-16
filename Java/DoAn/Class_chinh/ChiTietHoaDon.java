package Java.DoAn.Class_chinh;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ChiTietHoaDon {
    String mahd, masach;
    int soluong;
    double dongia;
    double thanhtien;

    //Hàm thiết lập:
    public ChiTietHoaDon() {
        mahd = "";
        masach = "";
        soluong = 0;
        dongia = 0;
        thanhtien = 0;
    }
    public ChiTietHoaDon(String mahd, String masach, int soluong, double dongia, double thanhtien) {
        this.mahd = mahd;
        this.masach = masach;
        this.soluong = soluong;
        this.dongia = dongia;
        this.thanhtien = thanhtien;
    }

    //Ghi File:
    public void ghiFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("Java/DoAn/input/inputChiTietHD.txt", true))) {
            writer.print(getMaHD());
            writer.print(", " + getMaSach());
            writer.print(", " + getSoLuong());
            writer.print(", " + getDonGia());
            writer.println(", " + getThanhTien());
        }
        catch (IOException e) {}
    }

    public void nhap() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma hoa don: ");
        mahd = sc.nextLine();
        System.out.print("Nhap ma sach: ");
        masach = sc.nextLine();
        System.out.print("Nhap so luong: ");
        soluong = sc.nextInt();
        System.out.print("Nhap thanh tien: ");
        thanhtien = sc.nextDouble();
    }
    public void xuat() {
        System.out.printf("%-10s %-15s %-10d %12.2f %12.2f\n", mahd, masach, soluong, dongia, thanhtien);
    }

    public String getMaHD() {
        return mahd;
    }
    public void setMaHD(String mahd) {
        this.mahd = mahd;
    }
    public String getMaSach() {
        return masach;
    }
    public void setMaSach(String masach) {
        this.masach = masach;
    }
    public int getSoLuong() {
        return soluong;
    }
    public void setSoLuong(int soluong) {
        this.soluong = soluong;
    }
    public double getDonGia() {
        return dongia;
    }
    public void setDonGia(double dongia) {
        this.dongia = dongia;
    }
    public double getThanhTien() {
        return thanhtien;
    }
    public void setThanhTien(double thanhtien) {
        this.thanhtien = thanhtien;
    }
}
