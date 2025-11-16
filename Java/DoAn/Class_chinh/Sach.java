package Java.DoAn.Class_chinh;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public abstract class Sach {
    // Thuộc tính:
    private String masach, tensach, matg, matl, manxb;
    private int soLuong;
    private double dongia;

    // Phương thức:

    //Hàm thiết lập:
    public Sach() {
    }
    public Sach(String masach, String tensach, String matg, String matl, String manxb, int soLuong, double dongia) {
        this.masach = masach;
        this.tensach = tensach;
        this.matg = matg;
        this.matl = matl;
        this.manxb = manxb;
        this.soLuong = soLuong;
        this.dongia = dongia;
    }
    public Sach(Sach s) {
        this.masach = s.masach;
        this.tensach = s.tensach;
        this.matg = s.matg;
        this.matl = s.matl;
        this.manxb = s.manxb;
        this.soLuong = s.soLuong;
        this.dongia = s.dongia;
    }

    // Nhập, xuất:
    public void nhap() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ten sach: ");
        tensach = sc.nextLine();
        System.out.print("Nhap ma tac gia: ");
        matg = sc.nextLine();
        System.out.print("Nhap ma the loai: ");
        matl = sc.nextLine();
        System.out.print("Nhap ma nha xuat ban: ");
        manxb = sc.nextLine();
        System.out.print("Nhap so luong: ");
        soLuong = sc.nextInt();
        System.out.print("Nhap don gia: ");
        dongia = sc.nextDouble();
    }
    public void xuat() {
        System.out.printf("%-10s %-40s %-20s %-10s %-10s %-10d %-10.2f", masach, tensach, matg, matl, manxb, soLuong, dongia);
    }

    //Ghi File:
    public String StringFile() {
        return getMaSach() + ", " + getTenSach() + ", " + getMaTG() + ", " + getMaTL() + ", " + getMaNXB() + ", " + getSoLuong() + ", " + getDonGia();
    }
    public void ghiFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("Java/DoAn/input/inputSach.txt", true))) {
            writer.println(StringFile());
        }
        catch (IOException e) {}
    }

    // Get, set:
    public String getMaSach() {
        return masach;
    }
    public void setMaSach(String masach) {
        this.masach = masach;
    }
    public String getTenSach() {
        return tensach;
    }
    public void setTenSach(String tensach) {
        this.tensach = tensach;
    }
    public String getMaTG() {
        return matg;
    }
    public void setMaTG(String matg) {
        this.matg = matg;
    }
    public String getMaTL() {
        return matl;
    }
    public void setMaTL(String matl) {
        this.matl = matl;
    }
    public String getMaNXB() {
        return manxb;
    }
    public void setMaNXB(String manxb) {
        this.manxb = manxb;
    }
    public int getSoLuong() {
        return soLuong;
    }
    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    public double getDonGia() {
        return dongia;
    }
    public void setDonGia(double dongia) {
        this.dongia = dongia;
    }

    // Abstract methods:
    public abstract String getLoaiSach();
    public abstract double tinhGiaBan();
    public abstract boolean isAvailable();
    
}
