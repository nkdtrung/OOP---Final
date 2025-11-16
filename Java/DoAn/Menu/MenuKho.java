package Java.DoAn.Menu;

import Java.DoAn.Class_chinh.Kho;
import Java.DoAn.DS_Class.DanhSachKho;
import Java.DoAn.DS_Class.DanhSachTonKho;

import java.util.Scanner;

public class MenuKho {
    static DanhSachKho dsKho = new DanhSachKho();
    static DanhSachTonKho dsTonKho = new DanhSachTonKho();
    static Scanner sc = new Scanner(System.in);
    static final String FILE_PATH = "Java/DoAn/input/inputKho.txt";

    public static void main(String[] args) {
        dsKho.docFile(FILE_PATH);
        // chuẩn bị dữ liệu sách cho thống kê tồn kho
        dsTonKho.docFile("Java/DoAn/input/inputSach.txt");
        int choice;
        do {
            System.out.println("\n--- MENU QUAN LY KHO ---");
            System.out.println("1. Xem danh sach kho");
            System.out.println("2. Them ban ghi kho");
            System.out.println("3. Xoa ban ghi kho");
            System.out.println("4. Sua thong tin kho");
            System.out.println("5. Tim kiem kho");
            System.out.println("6. Cap nhat so luong ton tu file thong ke");
            System.out.println("7. Kiem tra hang ton kho thap");
            System.out.println("8. Ghi thay doi vao file");
            System.out.println("9. Thong ke ton kho");
            System.out.println("0. Thoat");
            System.out.print("Nhap lua chon cua ban: ");
            choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    dsKho.hienThiDanhSach();
                    break;
                case 2:
                    themKho();
                    break;
                case 3:
                    xoaKho();
                    break;
                case 4:
                    suaKho();
                    break;
                case 5:
                    timKiemKho();
                    break;
                case 6:
                    dsKho.capNhatSoLuongTuFileTonKho();
                    break;
                case 7:
                    dsKho.kiemTraTonKhoThap();
                    break;
                case 8:
                    dsKho.ghiFile(FILE_PATH);
                    System.out.println("Da ghi cac thay doi vao file " + FILE_PATH);
                    break;
                case 9:
                    dsTonKho.thongKeTonKho();
                    break;
                case 0:
                    System.out.println("Dang thoat...");
                    break;
                default:
                    System.out.println("Lua chon khong hop le. Vui long thu lai.");
            }
        } while (choice != 0);
    }

    public static void themKho() {
        System.out.print("Nhap ma kho moi: ");
        String maKho = sc.nextLine();
        if (dsKho.timKiemTheoMa(maKho) != null) {
            System.out.println("Loi: Ma kho da ton tai.");
            return;
        }

        System.out.print("Nhap ma sach: ");
        String maSach = sc.nextLine();

        System.out.print("Nhap so luong ban dau: ");
        int soLuong = sc.nextInt();
        System.out.print("Nhap muc ton toi thieu: ");
        int mucTonToiThieu = sc.nextInt();
        sc.nextLine(); 
        System.out.print("Nhap vi tri (ke, ngan,...): ");
        String viTri = sc.nextLine();

        Kho khoMoi = new Kho(maKho, maSach, soLuong, mucTonToiThieu, viTri);
        dsKho.them(khoMoi);
        System.out.println("Da them kho moi thanh cong.");
    }

    public static void xoaKho() {
        System.out.print("Nhap ma kho can xoa: ");
        String maKho = sc.nextLine();
        if (dsKho.timKiemTheoMa(maKho) == null) {
            System.out.println("Loi: Khong tim thay kho co ma nay.");
        } else {
            dsKho.xoa(maKho);
        }
    }

    public static void suaKho() {
        System.out.print("Nhap ma kho can sua: ");
        String maKho = sc.nextLine();
        Kho khoCanSua = dsKho.timKiemTheoMa(maKho);
        if (khoCanSua == null) {
            System.out.println("Loi: Khong tim thay kho co ma nay.");
            return;
        }

        System.out.println("Nhap thong tin moi (de trong neu khong muon thay doi):");

        System.out.print("Ma sach moi (" + khoCanSua.getMaSach() + "): ");
        String maSachMoi = sc.nextLine();
        if (maSachMoi.isEmpty()) {
            maSachMoi = khoCanSua.getMaSach();
        }

        System.out.print("So luong moi (" + khoCanSua.getSoLuong() + "): ");
        String soLuongStr = sc.nextLine();
        int soLuongMoi = khoCanSua.getSoLuong();
        if (!soLuongStr.isEmpty()) {
            soLuongMoi = Integer.parseInt(soLuongStr);
        }

        System.out.print("Muc ton toi thieu moi (" + khoCanSua.getMucTonToiThieu() + "): ");
        String mucTonToiThieuStr = sc.nextLine();
        int mucTonToiThieuMoi = khoCanSua.getMucTonToiThieu();
        if (!mucTonToiThieuStr.isEmpty()) {
            mucTonToiThieuMoi = Integer.parseInt(mucTonToiThieuStr);
        }

        System.out.print("Vi tri moi (" + khoCanSua.getViTri() + "): ");
        String viTriMoi = sc.nextLine();
        if (viTriMoi.isEmpty()) {
            viTriMoi = khoCanSua.getViTri();
        }

        Kho khoMoi = new Kho(maKho, maSachMoi, soLuongMoi, mucTonToiThieuMoi, viTriMoi);
        dsKho.sua(maKho, khoMoi);
    }

    public static void timKiemKho() {
        System.out.println("Tim kiem theo:");
        System.out.println("1. Ma kho");
        System.out.println("2. Ma sach");
        System.out.print("Lua chon: ");
        int choice = sc.nextInt();
        sc.nextLine(); 

        switch (choice) {
            case 1:
                System.out.print("Nhap ma kho can tim: ");
                String maKho = sc.nextLine();
                Kho kho = dsKho.timKiemTheoMa(maKho);
                if (kho != null) {
                    System.out.println("Thong tin kho tim thay:");
                    System.out.printf("%-10s %-15s %-10s %-15s %-20s\n", "Ma Kho", "Ma Sach", "So Luong", "Ton Toi Thieu", "Vi Tri");
                     System.out.printf("%-10s %-15s %-10d %-15d %-20s\n",
                        kho.getMaKho(),
                        kho.getMaSach(),
                        kho.getSoLuong(),
                        kho.getMucTonToiThieu(),
                        kho.getViTri());
                } else {
                    System.out.println("Khong tim thay kho.");
                }
                break;
            case 2:
                System.out.print("Nhap ma sach can tim: ");
                String maSach = sc.nextLine();
                Kho khoTheoSach = dsKho.timKiemTheoMaSach(maSach);
                 if (khoTheoSach != null) {
                    System.out.println("Thong tin kho tim thay:");
                    System.out.printf("%-10s %-15s %-10s %-15s %-20s\n", "Ma Kho", "Ma Sach", "So Luong", "Ton Toi Thieu", "Vi Tri");
                     System.out.printf("%-10s %-15s %-10d %-15d %-20s\n",
                        khoTheoSach.getMaKho(),
                        khoTheoSach.getMaSach(),
                        khoTheoSach.getSoLuong(),
                        khoTheoSach.getMucTonToiThieu(),
                        khoTheoSach.getViTri());
                } else {
                    System.out.println("Khong co kho nao chua sach nay.");
                }
                break;
            default:
                System.out.println("Lua chon khong hop le.");
        }
    }
}