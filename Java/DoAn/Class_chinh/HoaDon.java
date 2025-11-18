package Java.DoAn.Class_chinh;
import java.util.Scanner;

public class HoaDon {
    private String mahd, manv, makh, ngaylap;
    private double tongtien;

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
        Scanner sc = new Scanner(System.in);
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
