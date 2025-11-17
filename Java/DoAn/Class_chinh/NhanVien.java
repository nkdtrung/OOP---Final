package Java.DoAn.Class_chinh;

import java.util.Scanner;

public class NhanVien {
    private String manv, ho, ten, sdt, ngaysinh, gioitinh;
    private double luong;

    public NhanVien() {
    }
    public NhanVien(String manv, String ho, String ten, String sdt, String ngaysinh, String gioitinh, double luong) {
        this.manv = manv;
        this.ho = ho;
        this.ten = ten;
        this.sdt = sdt;
        this.ngaysinh = ngaysinh;
        this.gioitinh = gioitinh;
        this.luong = luong;
    }
    public NhanVien(NhanVien n) {
        this.manv = n.manv;
        this.ho = n.ho;
        this.ten = n.ten;
        this.sdt = n.sdt;
        this.ngaysinh = n.ngaysinh;
        this.gioitinh = n.gioitinh;
        this.luong = n.luong;
    }

    // Nhập, xuất:
    public void nhap() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ho: ");
        ho = sc.nextLine();
        System.out.print("Nhap ten: ");
        ten = sc.nextLine();
        System.out.print("Nhap so dien thoai: ");
        sdt = sc.nextLine();
        System.out.print("Nhap ngay sinh: ");
        ngaysinh = sc.nextLine();
        System.out.print("Nhap gioi tinh: ");
        gioitinh = sc.nextLine();
        System.out.print("Nhap luong: ");
        luong = sc.nextDouble();
    }
    public void xuat() {
        System.out.printf("%-10s %-15s %-10s %-15s %-15s %-10s %-10.2f\n", manv, ho, ten, sdt, ngaysinh, gioitinh, luong);
    }

    // Get, set:
    public String getMaNV() {
        return manv;
    }   
    public void setMaNV(String manv) {
        this.manv = manv;
    }
    public String getHo() {
        return ho;
    }
    public void setHo(String ho) {
        this.ho = ho;
    }
    public String getTen() {
        return ten;
    }
    public void setTen(String ten) {
        this.ten = ten;
    }
    public String getSDT() {
        return sdt;
    }
    public void setSDT(String sdt) {
        this.sdt = sdt;
    }
    public String getNgaySinh() {
        return ngaysinh;
    }
    public void setNgaySinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }
    public String getGioiTinh() {
        return gioitinh;
    }
    public void setGioiTinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }
    public double getLuong() {
        return luong;
    }
    public void setLuong(double luong) {
        this.luong = luong;
    }
    
}
