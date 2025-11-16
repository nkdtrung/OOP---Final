package Java.DoAn.DS_Class;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

import Java.DoAn.Class_chinh.NhaXuatBan;

public class DanhSachNXB {
    private NhaXuatBan[] dsNXB;
    private int n;

    // Hàm thiết lập
    public DanhSachNXB() {
        n = 0;
        dsNXB = new NhaXuatBan[0];
    }

    public DanhSachNXB(NhaXuatBan[] dsNXB, int n) {
        this.dsNXB = dsNXB;
        this.n = n;
    }

    public DanhSachNXB(DanhSachNXB ds) {
        this.n = ds.n;
        this.dsNXB = new NhaXuatBan[n];
        for (int i = 0; i < n; i++) {
            this.dsNXB[i] = new NhaXuatBan(ds.dsNXB[i].getMaNXB(), ds.dsNXB[i].getTenNXB(),
                    ds.dsNXB[i].getDiaChi(), ds.dsNXB[i].getSoDienThoai(), ds.dsNXB[i].getEmail());
        }
    }

    // Xuất
    public void xuatDSNXB() {
        System.out.printf("%-8s %-30s %-45s %-15s %-30s%n", "Ma NXB", "Ten NXB", "Dia Chi", "So Dien Thoai", "Email");
        for (int i = 0; i < n; i++) {
            dsNXB[i].xuat();
        }
        System.out.println("Tong: " + n + " nha xuat ban");
    }

    public void themNXB() {
        if (dsNXB == null || dsNXB.length <= n) {
            dsNXB = Arrays.copyOf(dsNXB == null ? new NhaXuatBan[0] : dsNXB, n + 1);
        }
        dsNXB[n] = new NhaXuatBan();
        dsNXB[n].nhap();
        n++;
        tuDongCapNhatFile();
    }

    public void themNXB(NhaXuatBan nxb) {
        dsNXB = Arrays.copyOf(dsNXB, n + 1);
        dsNXB[n] = nxb;
        n++;
        tuDongCapNhatFile();
    }

    public void timNXB() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma nha xuat ban can tim: ");
        String ma = sc.nextLine();
        timNXB(ma);
    }

    public NhaXuatBan timNXB(String ma) {
        for (int i = 0; i < n; i++) {
            if (dsNXB[i].getMaNXB().equals(ma)) {
                dsNXB[i].xuat();
                return dsNXB[i];
            }
        }
        System.out.println("Khong tim thay nha xuat ban co ma " + ma);
        return null;
    }
    
    // Tìm nhà xuất bản không xuất ra màn hình (dùng nội bộ)
    public NhaXuatBan timNXBKhongXuat(String ma) {
        for (int i = 0; i < n; i++) {
            if (dsNXB[i].getMaNXB().equals(ma)) {
                return dsNXB[i];
            }
        }
        return null;
    }

    // Xóa
    public void xoaNXB() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma nha xuat ban can xoa: ");
        String ma = sc.nextLine();
        xoaNXB(ma);
    }

    public void xoaNXB(String ma) {
        for (int i = 0; i < n; i++) {
            if (dsNXB[i].getMaNXB().equals(ma)) {
                for (int j = i; j < n - 1; j++) {
                    dsNXB[j] = dsNXB[j + 1];
                }
                dsNXB = Arrays.copyOf(dsNXB, n - 1);
                n--;
                System.out.println("Da xoa nha xuat ban co ma " + ma);
                tuDongCapNhatFile();
                return;
            }
        }
        System.out.println("Khong tim thay nha xuat ban co ma " + ma);
    }

    // Sửa
    public void suaNXB() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma nha xuat ban can sua: ");
        String ma = sc.nextLine();
        suaNXB(ma);
    }

    public void suaNXB(String ma) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < n; i++) {
            if (dsNXB[i].getMaNXB().equals(ma)) {
                int choice;
                do {
                    System.out.println("\n=== SUA THONG TIN NHA XUAT BAN ===");
                    System.out.println("1. Ten nha xuat ban");
                    System.out.println("2. Dia chi");
                    System.out.println("3. So dien thoai");
                    System.out.println("4. Email");
                    System.out.println("0. Thoat");
                    System.out.print("Nhap lua chon: ");
                    choice = sc.nextInt();
                    sc.nextLine();
                    
                    switch (choice) {
                        case 1:
                            System.out.print("Nhap ten nha xuat ban moi: ");
                            String ten = sc.nextLine();
                            dsNXB[i].setTenNXB(ten);
                            System.out.println("Da cap nhat ten nha xuat ban!");
                            break;
                        case 2:
                            System.out.print("Nhap dia chi moi: ");
                            String diachi = sc.nextLine();
                            dsNXB[i].setDiaChi(diachi);
                            System.out.println("Da cap nhat dia chi!");
                            break;
                        case 3:
                            System.out.print("Nhap so dien thoai moi: ");
                            String sdt = sc.nextLine();
                            dsNXB[i].setSoDienThoai(sdt);
                            System.out.println("Da cap nhat so dien thoai!");
                            break;
                        case 4:
                            System.out.print("Nhap email moi: ");
                            String email = sc.nextLine();
                            dsNXB[i].setEmail(email);
                            System.out.println("Da cap nhat email!");
                            break;
                        case 0:
                            System.out.println("Thoat khoi sua thong tin.");
                            break;
                        default:
                            System.out.println("Lua chon khong hop le!");
                            break;
                    }
                } while (choice != 0);
                tuDongCapNhatFile();
                System.out.println("\nDa luu thong tin nha xuat ban thanh cong!");
                return;
            }
        }
        System.out.println("Khong tim thay nha xuat ban co ma " + ma);
    }

    // Tự động cập nhật file sau khi thay đổi dữ liệu
    public void tuDongCapNhatFile() {
        ghiFile();
    }

    // Ghi file
    public void ghiFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("Java/DoAn/input/inputNhaXuatBan.txt"))) {
            for (int i = 0; i < n; i++) {
                writer.println(dsNXB[i].getMaNXB() + "," + 
                              dsNXB[i].getTenNXB() + "," + 
                              dsNXB[i].getDiaChi() + "," + 
                              dsNXB[i].getSoDienThoai() + "," + 
                              dsNXB[i].getEmail());
            }
            writer.println("Tong: " + n);
        } catch (IOException e) {
            System.out.println("Loi ghi file: " + e.getMessage());
        }
    }

    // Đọc file
    public void docFile(String filePath) {
        this.dsNXB = new NhaXuatBan[0];
        n = 0;
        try (Scanner sc = new Scanner(new File(filePath))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty())
                    continue;

                String[] parts = line.split(",");
                if (parts.length < 5)
                    continue;

                String maNXB = parts[0].trim();
                String tenNXB = parts[1].trim();
                String diaChi = parts[2].trim();
                String sdt = parts[3].trim();
                String email = parts[4].trim();

                NhaXuatBan nxb = new NhaXuatBan(maNXB, tenNXB, diaChi, sdt, email);
                themNXB(nxb);
            }
            // System.out.println("Da doc file " + filePath);
        } catch (FileNotFoundException e) {
            // System.out.println("Khong tim thay file: " + e.getMessage());
        } catch (Exception e) {
            // System.out.println("Loi doc file: " + e.getMessage());
        }
    }
}
