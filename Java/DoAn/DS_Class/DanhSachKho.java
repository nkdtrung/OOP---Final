package Java.DoAn.DS_Class;

import Java.DoAn.Class_chinh.Kho;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class DanhSachKho {
    private Kho[] danhSach;
    private int soLuong;
    private DanhSachSach danhSachSach;

    public DanhSachKho() {
        danhSach = new Kho[0];
        soLuong = 0;
        danhSachSach = new DanhSachSach();
        danhSachSach.docFile("Java/DoAn/input/inputSach.txt");
    }

    public void docFile(String tenFile) {
        try (Scanner sc = new Scanner(new File(tenFile))) {
            soLuong = 0;
            danhSach = new Kho[0];
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.trim().isEmpty() || line.trim().startsWith("Tong:")) continue;
                String[] parts = line.split(",");
                if (parts.length < 5) continue;
                String maKho = parts[0].trim();
                String maSach = parts[1].trim();
                int soLuong = Integer.parseInt(parts[2].trim());
                int mucTonToiThieu = Integer.parseInt(parts[3].trim());
                String viTri = parts[4].trim();

                Kho kho = new Kho(maKho, maSach, soLuong, mucTonToiThieu, viTri);
                them(kho);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Khong tim thay file: " + tenFile);
        } catch (Exception e) {
            System.out.println("Loi khi doc file '" + tenFile + "': " + e.getMessage());
        }
    }

    // Tự động cập nhật file sau khi thay đổi dữ liệu
    public void tuDongCapNhatFile() {
        ghiFile("Java/DoAn/input/inputKho.txt");
    }

    public void ghiFile(String tenFile) {
        try (PrintWriter out = new PrintWriter(new FileWriter(tenFile))) {
            for (int i = 0; i < soLuong; i++) {
                Kho kho = danhSach[i];
                out.println(kho.getMaKho() + "," + kho.getMaSach() + "," + kho.getSoLuong() + "," + kho.getMucTonToiThieu() + "," + kho.getViTri());
            }
            out.println("Tong: " + soLuong);
        } catch (IOException e) {
            System.out.println("Loi khi ghi file: " + e.getMessage());
        }
    }

    public void them(Kho kho) {
        if (timKiemTheoMa(kho.getMaKho()) != null) {
            return;
        }
        danhSach = Arrays.copyOf(danhSach, soLuong + 1);
        danhSach[soLuong++] = kho;
        tuDongCapNhatFile();
    }

    public void xoa(String maKho) {
        int index = -1;
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i].getMaKho().equals(maKho)) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            for (int i = index; i < soLuong - 1; i++) {
                danhSach[i] = danhSach[i + 1];
            }
            danhSach = Arrays.copyOf(danhSach, soLuong - 1);
            soLuong--;
             System.out.println("Da xoa kho co ma: " + maKho);
             tuDongCapNhatFile();
        } else {
            System.out.println("Khong tim thay kho co ma: " + maKho);
        }
    }

    public Kho timKiemTheoMa(String maKho) {
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i].getMaKho().equals(maKho)) {
                return danhSach[i];
            }
        }
        return null;
    }
    
    public Kho timKiemTheoMaSach(String maSach) {
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i].getMaSach().equals(maSach)) {
                return danhSach[i];
            }
        }
        return null;
    }

    public void sua(String maKho, Kho khoMoi) {
        int index = -1;
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i].getMaKho().equals(maKho)) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            danhSach[index] = khoMoi;
            System.out.println("Da cap nhat thong tin kho co ma: " + maKho);
            tuDongCapNhatFile();
        } else {
            System.out.println("Khong tim thay kho de sua.");
        }
    }

    public void hienThiDanhSach() {
        System.out.println("--- Danh Sach Kho ---");
        System.out.printf("%-10s %-15s %-10s %-15s %-20s\n", "Ma Kho", "Ma Sach", "So Luong", "Ton Toi Thieu", "Vi Tri");
        for (int i = 0; i < soLuong; i++) {
            Kho k = danhSach[i];
            System.out.printf("%-10s %-15s %-10d %-15d %-20s\n",
                    k.getMaKho(),
                    k.getMaSach(),
                    k.getSoLuong(),
                    k.getMucTonToiThieu(),
                    k.getViTri());
        }
        System.out.println("Tong: " + soLuong + " kho");
    }
    
    public void capNhatSoLuongTuFileTonKho() {
        DanhSachTonKho dstk = new DanhSachTonKho();
        dstk.docFile("Java/DoAn/input/inputSach.txt");
        dstk.thongKeTonKho();

        try (Scanner sc = new Scanner(new File("Java/DoAn/input/inputTonKho.txt"))) {
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.trim().isEmpty() || line.trim().startsWith("Tong:")) continue;
                String[] parts = line.split(",");
                if (parts.length < 2) continue;
                String maSach = parts[0].trim();
                int soLuongTon = Integer.parseInt(parts[1].trim());
                
                Kho kho = timKiemTheoMaSach(maSach);
                if (kho != null) {
                    kho.setSoLuong(soLuongTon);
                }
            }
            ghiFile("Java/DoAn/input/inputKho.txt");
            System.out.println("Da cap nhat so luong ton kho tu file thong ke.");
        } catch (FileNotFoundException e) {
            System.out.println("Khong tim thay file inputTonKho.txt. Vui long chay thong ke ton kho truoc.");
        } catch (Exception e) {
            System.out.println("Loi khi cap nhat so luong tu file ton kho: " + e.getMessage());
        }
    }

    public void kiemTraTonKhoThap() {
        System.out.println("--- Cac San Pham Can Nhap Hang ---");
        boolean canNhap = false;
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i].laHangTonThap()) {
                if (!canNhap) {
                    System.out.printf("%-10s %-10s %-15s\n", "Ma Sach", "So Luong", "Ton Toi Thieu");
                    canNhap = true;
                }
                System.out.printf("%-10s %-10d %-15d\n",
                        danhSach[i].getMaSach(),
                        danhSach[i].getSoLuong(),
                        danhSach[i].getMucTonToiThieu());
            }
        }
        if (!canNhap) {
            System.out.println("Khong co san pham nao duoi muc ton kho toi thieu.");
        }
    }
}