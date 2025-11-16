package Java.DoAn.Class_chinh;

import java.util.Scanner;

public class NhaXuatBan {
     //thuộc tính    
    private String maNXB, tenNXB, diaChi, SoDienThoai, Email; 
    //hàm thiết lập
    public NhaXuatBan(){
        maNXB = "";
        tenNXB="";
        diaChi="";
        SoDienThoai="";
        Email="";
    }

    public NhaXuatBan (String maNXB, String tenNXB,String diaChi,String SoDienThoai, String Email){
        this.maNXB=maNXB;
        this.tenNXB =tenNXB;
        this.diaChi=diaChi;
        this.SoDienThoai=SoDienThoai;
        this.Email=Email;



    }
    // input / output for NhaXuatBan
    public void nhap(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap ma NXB: ");
        maNXB = sc.nextLine();
        System.out.println("Nhap ten NXB: ");
        tenNXB = sc.nextLine();
        System.out.println("Nhap dia chi: ");
        diaChi = sc.nextLine();
        System.out.println("Nhap so dien thoai: ");
        SoDienThoai = sc.nextLine();
        System.out.println("Nhap email: ");
        Email = sc.nextLine();
    }

    public void xuat(){
        System.out.printf("%-8s %-30s %-45s %-15s %-30s%n", maNXB, tenNXB, diaChi, SoDienThoai, Email);
    }

    // get,set
    public String getMaNXB() {
        return maNXB;
    }

    public void setMaNXB(String maNXB) {
        this.maNXB = maNXB;
    }

    public String getTenNXB() {
        return tenNXB;
    }

    public void setTenNXB(String tenNXB) {
        this.tenNXB = tenNXB;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String SoDienThoai) {
        this.SoDienThoai = SoDienThoai;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
}
