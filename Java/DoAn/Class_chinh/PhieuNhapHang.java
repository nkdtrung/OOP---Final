package Java.DoAn.Class_chinh;

import java.util.Scanner;

public class PhieuNhapHang {
    String maPNH;
    String maNV;
    String maNCC;
    String ngayLapPhieu;
    double tongTien;
    CTPhieuNhapHang[] chiTiets;
    int soLuongCT;

    public PhieuNhapHang() {
        this.maPNH = "";
        this.maNV = "";
        this.maNCC = "";
        this.ngayLapPhieu = "";
        this.tongTien = 0.0;
        this.chiTiets = new CTPhieuNhapHang[100];
        this.soLuongCT = 0;
    }

    public PhieuNhapHang(String maPNH, String maNV, String maNCC, String ngayLapPhieu) {
        this();
        this.maPNH = maPNH;
        this.maNV = maNV;
        this.maNCC = maNCC;
        this.ngayLapPhieu = ngayLapPhieu;
    }

    public PhieuNhapHang(PhieuNhapHang PNH) {
        this.maPNH = PNH.maPNH;
        this.maNV = PNH.maNV;
        this.maNCC = PNH.maNCC;
        this.ngayLapPhieu = PNH.ngayLapPhieu;
        this.tongTien = PNH.tongTien;
        this.chiTiets = new CTPhieuNhapHang[100];
        this.soLuongCT = PNH.soLuongCT;
        for (int i = 0; i < soLuongCT; i++) {
            this.chiTiets[i] = new CTPhieuNhapHang(PNH.chiTiets[i]);
        }
    }

    public void nhap() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma Nhan Vien: ");
        maNV = sc.nextLine();
        System.out.print("Nhap ma Nha Cung Cap: ");
        maNCC = sc.nextLine();
        System.out.print("Nhap ngay lap phieu: ");
        ngayLapPhieu = sc.nextLine();
        tongTien = 0.0;
    }
    public void xuat() {
        System.out.printf("%-10s %-10s %-10s %-15s %15.2f\n", maPNH, maNV, maNCC, ngayLapPhieu, tongTien);
    

    }

    public String getMaPNH() {
        return maPNH;
    }

    public void setMaPNH(String maPNH) {
        this.maPNH = maPNH;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public String getNgayLapPhieu() {
        return ngayLapPhieu;
    }

    public void setNgayLapPhieu(String ngayLapPhieu) {
        this.ngayLapPhieu = ngayLapPhieu;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public int getSoLuongCT() {
        return soLuongCT;
    }

    public CTPhieuNhapHang[] getChiTiets() {
        return chiTiets;
    }

}
