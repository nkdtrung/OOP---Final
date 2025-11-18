package Java.DoAn.Class_chinh;

import java.util.Scanner;


//Interface đơn giản để kiểm tra tính hợp lệ của dữ liệu
interface IValidatable {
    boolean isValid();
    String getValidationMessage();
}

public abstract class Sach implements IValidatable {
    private String masach, tensach, matg, matl, manxb;
    private int soLuong;
    private double dongia;

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

    public abstract String getLoaiSach();
    public abstract double tinhGiaBan();
    public abstract boolean isAvailable();
    
    // Triển khai interface IValidatable
    @Override
    public boolean isValid() {
        if (masach == null) return false;
        if (tensach == null) return false;
        if (soLuong < 0) return false;
        if (dongia <= 0) return false;
        return true;
    }
    
    @Override
    public String getValidationMessage() {
        if (masach == null) 
            return "Ma sach khong duoc de trong";
        if (tensach == null) 
            return "Ten sach khong duoc de trong";
        if (soLuong < 0) 
            return "So luong phai >= 0";
        if (dongia <= 0) 
            return "Don gia phai > 0";
        return "Du lieu hop le";
    }
    
}
