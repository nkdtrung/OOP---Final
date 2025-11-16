package Java.DoAn.Class_chinh;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class HoaDon {
    // Thuộc tính:
    private String mahd, manv, makh, ngaylap;
    private double tongtien;

    // Phương thức:

    //Hàm thiết lập:
    public HoaDon() {
    }
    public HoaDon(String mahd, String manv, String makh, String ngaylap, double tongtien) {
        this.mahd = mahd;
        this.manv = manv;
        this.makh = makh;
        this.ngaylap = ngaylap;
        this.tongtien = tongtien;
    }
    public HoaDon(HoaDon h) {
        this.mahd = h.mahd;
        this.manv = h.manv;
        this.makh = h.makh;
        this.ngaylap = h.ngaylap;
        this.tongtien = h.tongtien;
    }

    // Nhập, xuất:
    public void nhap() {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        System.out.print("Nhap ma nhan vien: ");
        manv = sc.nextLine();
        System.out.print("Nhap ma khach hang: ");
        makh = sc.nextLine();
        System.out.print("Nhap ngay lap: ");
        ngaylap = sc.nextLine();
        tongtien = 0.0;
    }
    public void xuat() {
        System.out.printf("%-10s %-10s %-10s %-15s %15.2f\n", mahd, manv, makh, ngaylap, tongtien);
    }

    //Ghi File:
    public void ghiFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("Java/DoAn/input/inputHoaDon.txt", true))) {
            writer.print(getMaHD());
            writer.print(", " + getMaNV());
            writer.print(", " + getMaKH());
            writer.print(", " + getNgayLap());
            writer.println(", " + getTongTien());
        }
        catch (IOException e) {}
    }

    // Get, set:
    public String getMaHD() {
        return mahd;
    }
    public void setMaHD(String mahd) {
        this.mahd = mahd;
    }
    public String getMaNV() {
        return manv;
    }
    public void setMaNV(String manv) {
        this.manv = manv;
    }
    public String getMaKH() {
        return makh;
    }
    public void setMaKH(String makh) {
        this.makh = makh;
    }
    public String getNgayLap() {
        return ngaylap;
    }
    public void setNgayLap(String ngaylap) {
        this.ngaylap = ngaylap;
    }
    public double getTongTien() {
        return tongtien;
    }
    public void setTongTien(double tongtien) {
        this.tongtien = tongtien;
    }
}
