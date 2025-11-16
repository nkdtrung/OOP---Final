package Java.DoAn.DS_Class;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

import Java.DoAn.Class_chinh.NhanVien;

public class DanhSachNV {
    // Thuộc tính:
    private NhanVien[] dsnv;
    private int n;

    // Phương thức:

    // Hàm thiết lập:
    public DanhSachNV() {
        n = 0;
    }
    public DanhSachNV(NhanVien[] dsnv, int n) {
        this.n = n;
        this.dsnv = dsnv;
    }

    // Nhập, xuất:
    public void xuatdsnv() {
        System.out.printf("%-10s %-15s %-10s %-15s %-15s %-10s %-10s\n", "Ma NV", "Ho", "Ten", "SDT", "Ngay Sinh", "Gioi Tinh", "Luong");
        for (int i = 0; i < n; i++) {
            dsnv[i].xuat();
        }
        System.out.println("Tong: " + n + " nhan vien");
    }

    // them
    public void themNhanVien() {
        Scanner sc = new Scanner(System.in);
        String manv;
        do {
            System.out.print("Nhap ma nhan vien: ");
            manv = sc.nextLine();
            if (timKiemTheoMa(manv) != null) {
                System.out.println("Ma nhan vien da ton tai. Vui long nhap lai!");
            }
        } while (timKiemTheoMa(manv) != null);
        
        n = dsnv.length;
        dsnv = Arrays.copyOf(dsnv, n+1);
        dsnv[n] = new NhanVien();
        dsnv[n].setMaNV(manv);
        System.out.println("Ma NV: " + manv);
        dsnv[n].nhap();
        n++;
        tuDongCapNhatFile();
    }
    public void themNhanVien(String manv) {
        n = dsnv.length;
        dsnv = Arrays.copyOf(dsnv, n+1);
        dsnv[n] = new NhanVien();
        n++;
    }
    public void themNhanVien(NhanVien nv) {
        dsnv = Arrays.copyOf(dsnv, n + 1);
        dsnv[n] = nv;
        n++;
    }

    // tim:
    public NhanVien timKiemTheoMa(String ma) {
        for (int i = 0; i < n; i++) {
            if (dsnv[i].getMaNV().equalsIgnoreCase(ma)) {
                return dsnv[i];
            }
        }
        return null;
    }

    public DanhSachNV timKiemTheoHo(String ho) {
        DanhSachNV ketQua = new DanhSachNV();
        ketQua.dsnv = new NhanVien[0];
        ketQua.n = 0;
        for (int i = 0; i < n; i++) {
            if (dsnv[i].getHo().toLowerCase().contains(ho.toLowerCase())) {
                ketQua.dsnv = Arrays.copyOf(ketQua.dsnv, ketQua.n + 1);
                ketQua.dsnv[ketQua.n] = new NhanVien(dsnv[i]);
                ketQua.n++;
            }
        }
        return ketQua;
    }
    public DanhSachNV timKiemTheoTen(String ten) {
        DanhSachNV ketQua = new DanhSachNV();
        ketQua.dsnv = new NhanVien[0];
        ketQua.n = 0;
        for (int i = 0; i < n; i++) {
            if (dsnv[i].getTen().toLowerCase().contains(ten.toLowerCase())) {
                ketQua.dsnv = Arrays.copyOf(ketQua.dsnv, ketQua.n + 1);
                ketQua.dsnv[ketQua.n] = new NhanVien(dsnv[i]);
                ketQua.n++;
            }
        }
        return ketQua;
    }

    // xoa:
    public void xoaNhanVien() {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        System.out.print("Nhap ma nhan vien can xoa: ");
        String ma = sc.nextLine();
        xoaNhanVien(ma);
    }
    public void xoaNhanVien(String ma) {
        for (int i = 0; i < n; i++) {
            if (dsnv[i].getMaNV().equals(ma)) {
                for (int j = i; j < n - 1; j++) {
                    dsnv[j] = dsnv[j + 1];
                }
                dsnv = Arrays.copyOf(dsnv, n-1);
                n--;
                System.out.println("Da xoa nhan vien co ma " + ma);
                tuDongCapNhatFile();
                return;
            }
        }
        System.out.println("Khong tim thay nhan vien co ma " + ma);
    }

    // sua:
    public void suaNhanVien() {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        System.out.print("Nhap ma nhan vien can sua: ");
        String ma = sc.nextLine();
        suaNhanVien(ma);
    }
    public void suaNhanVien(String manv) {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        for (int i = 0; i < n; i++) {
            if (dsnv[i].getMaNV().equals(manv)) {               
                System.out.println("Chon thong tin can sua:");
                System.out.println("1. Ho");
                System.out.println("2. Ten");
                System.out.println("3. SDT");
                System.out.println("4. Ngay sinh");
                System.out.println("5. Gioi tinh");
                System.out.println("6. Luong");
                System.out.print("Nhap lua chon: ");
                int choice = sc.nextInt();
                sc.nextLine(); // Xóa buffer
                
                switch (choice) {
                    case 1:
                        System.out.print("Nhap ho moi: ");
                        String ho = sc.nextLine();
                        dsnv[i].setHo(ho);
                        break;
                    case 2:
                        System.out.print("Nhap ten moi: ");
                        String ten = sc.nextLine();
                        dsnv[i].setTen(ten);
                        break;
                    case 3:
                        System.out.print("Nhap SDT moi: ");
                        String sdt = sc.nextLine();
                        dsnv[i].setSDT(sdt);
                        break;
                    case 4:
                        System.out.print("Nhap ngay sinh moi: ");
                        String ngaysinh = sc.nextLine();
                        dsnv[i].setNgaySinh(ngaysinh);
                        break;
                    case 5:
                        System.out.print("Nhap gioi tinh moi: ");
                        String gioitinh = sc.nextLine();
                        dsnv[i].setGioiTinh(gioitinh);
                        break;
                    case 6:
                        System.out.print("Nhap luong moi: ");
                        double luong = sc.nextDouble();
                        dsnv[i].setLuong(luong);
                        break;
                    default:
                        System.out.println("Lua chon khong hop le!");
                        return;
                }
                
                System.out.println("Da cap nhat thong tin nhan vien thanh cong!");
                tuDongCapNhatFile();
                return;
            }
        }
        System.out.println("Khong tim thay nhan vien co ma " + manv);
    }

    public int getSoLuongNV() {
        return n;
    }

    //Thống kê:
    public int[] thongKeGioiTinh() {
        int nam = 0, nu = 0;
        for (int i = 0; i < n; i++) {
            if (dsnv[i].getGioiTinh().equals("nam") || dsnv[i].getGioiTinh().equals( "Nam")) {
                nam++;
            }
            else nu++;
        }
        System.out.println("Nam: " + nam);
        System.out.println("Nu: " + nu);
        return new int[]{nam, nu};
    }

    //Đọc file:
    public void docFile(String filePath) {
        dsnv = new NhanVien[0];
        n = 0;
        try (Scanner sc = new Scanner(new File(filePath))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty() || line.startsWith("Tong:")) continue;

                String[] parts = line.split(",");
                if (parts.length < 5) continue;

                String manv = parts[0].trim();
                String ho = parts[1].trim();
                String ten = parts[2].trim();
                String sdt = parts[3].trim();
                String ngaysinh = parts[4].trim();
                String gioitinh = parts[5].trim();
                double luong = Double.parseDouble(parts[6].trim());

                NhanVien nv = new NhanVien(manv, ho, ten, sdt, ngaysinh, gioitinh, luong);
                themNhanVien(nv);

            }
            // System.out.println("Da doc file " + filePath);
        } catch (FileNotFoundException e) {
            System.out.println("Khong tim thay file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Loi doc file: " + e.getMessage());
        }
    }

    // Tự động cập nhật file sau khi thay đổi dữ liệu
    public void tuDongCapNhatFile() {
        ghiFile();
    }

    //Ghi File:
    public void ghiFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("Java/DoAn/input/inputNhanVien.txt"))) {
            for (int i=0;i<n;i++) {
                writer.println(dsnv[i].getMaNV() + "," + 
                              dsnv[i].getHo() + "," + 
                              dsnv[i].getTen() + "," + 
                              dsnv[i].getSDT() + "," + 
                              dsnv[i].getNgaySinh() + "," + 
                              dsnv[i].getGioiTinh() + "," + 
                              dsnv[i].getLuong());
            }
            writer.println("Tong: " + n);
        }
        catch (IOException e) {
            System.out.println("Loi ghi file: " + e.getMessage());
        }
    }
}
