package Java.DoAn.Class_chinh;

import java.util.Scanner;
public class NhaCungCap {
    //thuộc tính
    private String tenNCC, maNCC, diaChi, sdt;

    //hàm thiết lập;
    public NhaCungCap() {
    }
    public NhaCungCap(String tenNCC, String maNCC, String diaChi, String sdt) {
        this.tenNCC = tenNCC;
        this.maNCC = maNCC;
        this.diaChi = diaChi;
        this.sdt = sdt;
    }
    public NhaCungCap(NhaCungCap n) {
        this.tenNCC = n.tenNCC;
        this.maNCC = n.maNCC;
        this.diaChi = n.diaChi;
        this.sdt = n.sdt;
    }

    //nhập, xuất
    public void nhap(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma nha cung cap: ");
        maNCC = sc.nextLine();
        System.out.print("Nhap ten nha cung cap: ");
        tenNCC = sc.nextLine();
        System.out.print("Nhap dia chi: ");
        diaChi = sc.nextLine();
        System.out.print("Nhap so dien thoai: ");
        sdt = sc.nextLine();
    }

    public void xuat() {
        System.out.printf("%-10s %-35s %-40s %-15s%n", maNCC, tenNCC, diaChi, sdt);
    }

    //get,set
    public String getTenNCC() {
        return tenNCC;
    }
    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
    }
    public String getMaNCC() {
        return maNCC;
    }
    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }
    public String getDiaChi() {
        return diaChi;
    }
    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
    public String getSdt() {
        return sdt;
    }
    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
}

