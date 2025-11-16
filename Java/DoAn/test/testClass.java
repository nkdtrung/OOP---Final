package Java.DoAn.test;

import java.util.Scanner;

import Java.DoAn.DS_Class.DanhSachCTHD;
import Java.DoAn.DS_Class.DanhSachHoaDon;
import Java.DoAn.DS_Class.DanhSachPNH;
import Java.DoAn.DS_Class.DanhSachCTPNH;
import Java.DoAn.DS_Class.DanhSachNXB;
import Java.DoAn.DS_Class.DanhSachTheLoai;
import Java.DoAn.DS_Class.DanhSachNCC;
import Java.DoAn.Menu.MenuHoaDon;
import Java.DoAn.Menu.MenuNhanVien;
import Java.DoAn.Menu.MenuSach;
import Java.DoAn.Menu.MenuPhieuNhapHang;
import Java.DoAn.Menu.MenuNXB;
import Java.DoAn.Menu.MenuNCC;
import Java.DoAn.Menu.MenuTheLoai;
import Java.DoAn.Menu.MenuKho;
import Java.DoAn.Menu.MenuKH;
import Java.DoAn.DS_Class.DanhSachKH;

public class testClass {
    private static MenuNhanVien menuNV;
    private static MenuSach menuSach;
    private static MenuNXB menuNXB;
    private static MenuHoaDon menuHD;
    private static MenuPhieuNhapHang menuPNH;
    private static MenuNCC menuNCC;
    private static MenuTheLoai menuTheLoai;
    private static MenuKho menuKho;
    private static MenuKH menuKH;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("         --- Xin Chao ---                 ");

        menuNV = new MenuNhanVien();
        menuSach = new MenuSach();
        menuKho = new MenuKho();

        DanhSachNXB dsnxb = new DanhSachNXB();
        dsnxb.docFile("Java/DoAn/input/inputNhaXuatBan.txt");
        menuNXB = new MenuNXB(dsnxb);

        DanhSachNCC dsncc = new DanhSachNCC();
        dsncc.docFile("Java/DoAn/input/inputNhaCungCap.txt");
        menuNCC = new MenuNCC(dsncc);

        DanhSachCTHD dscthd = new DanhSachCTHD();
        dscthd.docFile("Java/DoAn/input/inputChiTietHD.txt");

        DanhSachHoaDon dshd = new DanhSachHoaDon();
        dshd.docFile("Java/DoAn/input/inputHoaDon.txt");
        dshd.themHDFile();
        menuHD = new MenuHoaDon(dshd);

        DanhSachCTPNH dsctpnh = new DanhSachCTPNH();
        dsctpnh.docFile("Java/DoAn/input/inputChiTietPNH.txt");

        DanhSachPNH dspnh = new DanhSachPNH();
        dspnh.docFile("Java/DoAn/input/inputPhieuNhapHang.txt");
        dspnh.themPNHFile();
        menuPNH = new MenuPhieuNhapHang(dspnh);

        DanhSachTheLoai dsTheLoai = new DanhSachTheLoai();
        dsTheLoai.docFile("Java/DoAn/input/inputTheLoai.txt");
        menuTheLoai = new MenuTheLoai(dsTheLoai);

        DanhSachKH dskh = new DanhSachKH();
        dskh.docTuFile();
        menuKH = new MenuKH(dskh);

        int choice;
        do {
            System.out.println("\n========== MENU CHINH - QUAN LY SACH ==========");
            System.out.println("1. Quan ly Nhan Vien");
            System.out.println("2. Quan ly Khach Hang");
            System.out.println("3. Quan ly Sach");
            System.out.println("4. Quan ly Hoa Don");
            System.out.println("5. Quan ly Phieu Nhap Hang");
            System.out.println("6. Quan ly Nha Xuat Ban");
            System.out.println("7. Quan ly Nha Cung Cap");
            System.out.println("8. Quan ly The Loai");
            System.out.println("9. Quan ly Kho");
            System.out.println("0. Thoat");
            System.out.print("Nhap lua chon cua ban: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    menuNV();
                    break;
                case 2:
                    menuKH();
                    break;
                case 3:
                    menuSach();
                    break;
                case 4:
                    menuHD();
                    break;
                case 5:
                    menuCtPNH();
                    break;
                case 6:
                    menuNXB();
                    break;
                case 7:
                    menuNCC();
                    break;
                case 8:
                    menuTheLoai();
                    break;
                case 9:
                    MenuKho.main(new String[0]);
                    break;
                case 0:
                    System.out.println("Cam on ban da su dung chuong trinh. Hen gap lai!");
                    break;
                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai.");
            }
        } while (choice != 0);

        sc.close();
    }

    private static void menuNV() {
        if (menuNV == null) {
            System.out.println("MenuNhanVien chua duoc khoi tao.");
            return;
        }
        menuNV.MenuChinh();
    }

    private static void menuSach() {
        if (menuSach == null) {
            System.out.println("MenuSach chua duoc khoi tao.");
            return;
        }
        menuSach.MenuChinh();
    }

    private static void menuHD() {
        if (menuHD == null) {
            System.out.println("MenuHoaDon chua duoc khoi tao.");
            return;
        }
        menuHD.MenuChinh();
    }

    private static void menuCtPNH() {
        if (menuPNH == null) {
            System.out.println("MenuPhieuNhapHang chua duoc khoi tao.");
            return;
        }
        menuPNH.MenuChinh();
    }

    private static void menuNXB() {
        if (menuNXB == null) {
            System.out.println("MenuNXB chua duoc khoi tao.");
            return;
        }
        menuNXB.MenuChinh();
    }

    private static void menuNCC() {
        if (menuNCC == null) {
            System.out.println("MenuNCC chua duoc khoi tao.");
            return;
        }
        menuNCC.MenuChinh();
    }

    private static void menuTheLoai() {
        if (menuTheLoai == null) {
            System.out.println("MenuTheLoai chua duoc khoi tao.");
            return;
        }
        menuTheLoai.MenuChinh();
    }

    private static void menuKH() {
        if (menuKH == null) {
            System.out.println("MenuKH chua duoc khoi tao.");
            return;
        }
        menuKH.MenuChinh();
    }
}
