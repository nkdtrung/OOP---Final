package Java.DoAn.Class_chinh;
import java.util.Scanner;

public class KhachHang {
    private String makh;
    private String ho;
    private String ten;
    private String diachi;
    private String sdt;
    private String gioitinh;

    public KhachHang() {
        makh = "";
        ho = "";
        ten = "";
        diachi = "";
        sdt = "";
        gioitinh = "";
    }

    public KhachHang(String makh, String ho, String ten, String diachi, String sdt, String gioitinh) {
        this.makh = makh;
        this.ho = ho;
        this.ten = ten;
        this.diachi = diachi;
        this.sdt = sdt;
        this.gioitinh = gioitinh;
    }

    public KhachHang(KhachHang k) {
        this.makh = k.makh;
        this.ho = k.ho;
        this.ten = k.ten;
        this.diachi = k.diachi;
        this.sdt = k.sdt;
        this.gioitinh = k.gioitinh;
    }

    public String getMaKH() { return makh; }
    public void setMaKH(String makh) { this.makh = makh; }
    public String getHoKH() { return ho; }
    public void setHoKH(String ho) { this.ho = ho; }
    public String getTenKH() { return ten; }
    public void setTenKH(String ten) { this.ten = ten; }
    public String getHoTenKH() { return ho + " " + ten; }
    public String getDiachi() { return diachi; }
    public void setDiachi(String diachi) { this.diachi = diachi; }
    public String getSdt() { return sdt; }
    public void setSdt(String sdt) { this.sdt = sdt; }
    public String getGioiTinh() { return gioitinh; }
    public void setGioiTinh(String gioitinh) { this.gioitinh = gioitinh; }

    public void nhap() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập họ khách hàng: ");
        ho = sc.nextLine();
        System.out.print("Nhập tên khách hàng: ");
        ten = sc.nextLine();
        System.out.print("Nhập địa chỉ: ");
        diachi = sc.nextLine();
        System.out.print("Nhập số điện thoại: ");
        sdt = sc.nextLine();
        System.out.print("Nhập giới tính: ");
        gioitinh = sc.nextLine();
    }

    public void xuat() {
        System.out.printf("%-15s %-25s %-25s %-20s %-15s %-10s\n", makh, ho, ten, diachi, sdt, gioitinh);
    }
}

