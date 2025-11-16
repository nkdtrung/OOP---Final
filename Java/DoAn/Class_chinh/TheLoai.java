package Java.DoAn.Class_chinh;

import java.util.Scanner;
public class TheLoai {
    //thuộc tính
    private String matheloai, tentheloai;

    //hàm thiết lập
    public TheLoai() {
    }
    public TheLoai(String matheloai, String tentheloai) {
        this.matheloai = matheloai;
        this.tentheloai = tentheloai;
    }
    public TheLoai(TheLoai t) {
        this.matheloai = t.matheloai;
        this.tentheloai = t.tentheloai;
    }
    
    //nhập, xuất
    public void nhap() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma the loai: ");
        matheloai = sc.nextLine();
        System.out.print("Nhap ten the loai: ");
        tentheloai = sc.nextLine();
    }
    public void xuat() {
        System.out.printf("%-10s %-20s%n", matheloai, tentheloai);
    }

    //get,set
    public String getMaTheLoai() {
        return matheloai;
    }
    public void setMaTheLoai(String matheloai) {
        this.matheloai = matheloai;
    }
    public String getTenTheLoai() {
        return tentheloai;
    }
    public void setTenTheLoai(String tentheloai) {
        this.tentheloai = tentheloai;
    }
}
