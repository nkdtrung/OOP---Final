package Java.DoAn.Class_chinh;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TapChi  extends Sach {
    // Thuộc tính:
    private int sophathanh;
    private String chuyenmuc;

    // Phương thức:

    //Hàm thiết lập:
    public TapChi() {
        super();
    }
    public TapChi(String masach, String tensach, String tacgia, String maTL, String maNXB, int soLuong, double donGia, int sophathanh, String chuyenmuc) {
        super(masach, tensach, tacgia, maTL, maNXB, soLuong, donGia);
        this.sophathanh = sophathanh;
        this.chuyenmuc = chuyenmuc;
    }
    public TapChi(TapChi t) {
        super(t);
        this.sophathanh = t.sophathanh;
        this.chuyenmuc = t.chuyenmuc;
    }

    // Nhập, xuất:
    @Override
    public void nhap() {
        super.nhap();
        java.util.Scanner sc = new java.util.Scanner(System.in);
        System.out.print("Nhap so phat hanh: ");
        sophathanh = sc.nextInt();
        sc.nextLine();
        System.out.print("Nhap chuyen muc: ");
        chuyenmuc = sc.nextLine();
    }
    @Override
    public void xuat() {
        super.xuat();
        System.out.printf("%-15d ", sophathanh);
        System.out.printf("%-20s\n", chuyenmuc);
    }

    @Override
    public String StringFile() {
        return super.StringFile() + ", " + getSoPhatHanh() + ", " + getChuyenMuc();
    }

    @Override
    public void ghiFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("Java/DoAn/input/inputSach.txt", true))) {
            writer.println(StringFile());
        }
        catch (IOException e) {}
    }

    // Get, set:
    public int getSoPhatHanh() {
        return sophathanh;
    } 
    public void setSoPhatHanh(int sophathanh) {
        this.sophathanh = sophathanh;
    }
    public String getChuyenMuc() {
        return chuyenmuc;
    }
    public void setChuyenMuc(String chuyenmuc) {
        this.chuyenmuc = chuyenmuc;
    }

    // Implement abstract methods:
    @Override
    public String getLoaiSach() {
        return "Tạp chí";
    }

    @Override
    public double tinhGiaBan() {
        // Tạp chí giá bán bằng đơn giá
        return getDonGia();
    }

    @Override
    public boolean isAvailable() {
        // Tạp chí cần tồn kho nhiều hơn do phát hành định kỳ
        return getSoLuong() > 5;
    }

}
