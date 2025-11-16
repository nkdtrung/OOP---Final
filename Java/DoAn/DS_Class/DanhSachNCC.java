package Java.DoAn.DS_Class;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import Java.DoAn.Class_chinh.NhaCungCap;

public class DanhSachNCC {
    NhaCungCap[] dsNCC;
    int n;

    // Hàm thiết lập
    public DanhSachNCC() {
        dsNCC = new NhaCungCap[100];
        n = 0;
    }

    public DanhSachNCC(NhaCungCap[] dsNCC, int n) {
        this.dsNCC = dsNCC;
        this.n = n;
    }

    public DanhSachNCC(DanhSachNCC ds) {
        this.n = ds.n;
        this.dsNCC = new NhaCungCap[100];
        for (int i = 0; i < n; i++) {
            this.dsNCC[i] = new NhaCungCap(ds.dsNCC[i]);
        }
    }

    // Xuất
    public void xuatDSNCC() {
        System.out.printf("%-10s %-35s %-40s %-15s%n", "Ma NCC", "Ten NCC", "Dia Chi", "SDT");
        for (int i = 0; i < n; i++) {
            dsNCC[i].xuat();
        }
        System.out.println("Tong: " + n + " nha cung cap");
    }

    //thêm
    public void themNCC() {
    if (n < dsNCC.length) {
        dsNCC[n] = new NhaCungCap();
        dsNCC[n].nhap();
        n++;
        tuDongCapNhatFile();
    } else {
        System.out.println("Danh sach da day, khong the them.");
    }
}

    //tìm
    public NhaCungCap timNCC(String maNCC) {
        for (int i = 0; i < n; i++) {
            if (dsNCC[i].getMaNCC().equals(maNCC)) {
                return dsNCC[i];
            }
        }
        return null;
    }

    public void timNCC() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma nha cung cap can tim: ");
        String maNCC = sc.nextLine();
        NhaCungCap ncc = timNCC(maNCC);
        if (ncc != null) {
            System.out.printf("%-10s %-35s %-40s %-15s%n", "Ma NCC", "Ten NCC", "Dia Chi", "SDT");
            ncc.xuat();
        } else {
            System.out.println("Khong tim thay nha cung cap voi ma: " + maNCC);
        }
    }

    //xóa
    public void xoaNCC(String maNCC) {
        int index = -1;
        for (int i = 0; i < n; i++) {
            if (dsNCC[i].getMaNCC().equals(maNCC)) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            for (int i = index; i < n - 1; i++) {
                dsNCC[i] = dsNCC[i + 1];
            }
            dsNCC[n - 1] = null;
            n--;
            System.out.println("Da xoa nha cung cap voi ma: " + maNCC);
            tuDongCapNhatFile();
        } else {
            System.out.println("Khong tim thay nha cung cap voi ma: " + maNCC);
        }
    }

    //sửa
    public void suaNCC(String maNCC) {
        NhaCungCap ncc = timNCC(maNCC);
        if (ncc != null) {
            Scanner sc = new Scanner(System.in);
            int choice;
            do {
                System.out.println("Sua thong tin nha cung cap");
                System.out.println("1. Sua ten nha cung cap");
                System.out.println("2. Sua ma nha cung cap");
                System.out.println("3. Sua dia chi");
                System.out.println("4. Sua so dien thoai");
                System.out.println("5. Thoat");
                System.out.print("Nhap lua chon: ");
                choice = sc.nextInt();
                sc.nextLine();
            switch(choice) {
                case 1:
                    System.out.print("Nhap ten nha cung cap moi: ");
                    ncc.setTenNCC(sc.nextLine());
                    break;
                case 2:
                    System.out.print("Nhap ma nha cung cap moi: ");
                    ncc.setMaNCC(sc.nextLine());
                    break;
                case 3:
                    System.out.print("Nhap dia chi moi: ");
                    ncc.setDiaChi(sc.nextLine());
                    break;
                case 4:
                    System.out.print("Nhap so dien thoai moi: ");
                    ncc.setSdt(sc.nextLine());
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Lua chon khong hop le, vui long chon lai.");
                    break;
            }
        }while(choice != 5);
        tuDongCapNhatFile();
        }else{
            System.out.println("Khong tim thay nha cung cap voi ma " + maNCC);
        }
    }

    // Tự động cập nhật file sau khi thay đổi dữ liệu
    public void tuDongCapNhatFile() {
        ghiFile();
    }

    // Ghi file
    public void ghiFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("Java/DoAn/input/inputNhaCungCap.txt"))) {
            for (int i = 0; i < n; i++) {
                writer.println(dsNCC[i].getMaNCC() + "," + 
                              dsNCC[i].getTenNCC() + "," + 
                              dsNCC[i].getDiaChi() + "," + 
                              dsNCC[i].getSdt());
            }
            writer.println("Tong: " + n);
        } catch (IOException e) {
            System.out.println("Loi ghi file: " + e.getMessage());
        }
    }

    // Đọc file
    public void docFile(String filePath) {
        this.dsNCC = new NhaCungCap[100];
        n = 0;
        try (Scanner sc = new Scanner(new File(filePath))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty())
                    continue;

                String[] parts = line.split(",");
                if (parts.length < 4)
                    continue;

                String maNCC = parts[0].trim();
                String tenNCC = parts[1].trim();
                String diaChi = parts[2].trim();
                String sdt = parts[3].trim();

                NhaCungCap ncc = new NhaCungCap(tenNCC, maNCC, diaChi, sdt);
                if (n < dsNCC.length) {
                    dsNCC[n] = ncc;
                    n++;
                }
            }
            // System.out.println("Da doc file " + filePath);
        } catch (FileNotFoundException e) {
            System.out.println("Khong tim thay file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Loi doc file: " + e.getMessage());
        }
    }
}