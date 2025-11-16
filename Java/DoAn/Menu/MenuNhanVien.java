package Java.DoAn.Menu;

import java.util.Scanner;
import Java.DoAn.DS_Class.DanhSachNV;

public class MenuNhanVien {
    public void MenuChinh() {
        DanhSachNV dsnv = new DanhSachNV();
        Scanner sc = new Scanner(System.in);
        dsnv.docFile("Java/DoAn/input/inputNhanVien.txt");
        int choice;
        do {
            System.out.println("===== MENU QUAN LY NHAN VIEN =====");
            System.out.println("1. Xuat danh sach nhan vien");
            System.out.println("2. Them nhan vien");
            System.out.println("3. Tim kiem nhan vien");
            System.out.println("4. Xoa nhan vien");
            System.out.println("5. Sua thong tin nhan vien");
            System.out.println("0. Thoat");
            System.out.print("Nhap lua chon cua ban: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    dsnv.xuatdsnv();
                    System.out.println();
                    break;
                case 2:
                    dsnv.themNhanVien();
                    break;
                case 3:
                    System.out.println("1. Tim theo ma.");
                    System.out.println("2. Tim theo ho.");
                    System.out.println("3. Tim theo ten.");
                    System.out.print("Nhap lua chon: ");
                    int subChoice = sc.nextInt();
                    sc.nextLine();
                    switch (subChoice) {
                        case 1:
                            System.out.print("Nhap ma nhan vien: ");
                            String ma = sc.nextLine();
                            var nv = dsnv.timKiemTheoMa(ma);
                            if (nv != null) {
                                System.out.println("Thong tin nhan vien:");
                                System.out.printf("%-10s %-15s %-10s %-15s %-15s %-10s %-10s\n", "Ma NV", "Ho", "Ten", "SDT", "Ngay Sinh", "Gioi Tinh", "Luong");
                                nv.xuat();
                            } else {
                                System.out.println("Khong tim thay nhan vien.");
                            }
                            break;
                        case 2:
                            System.out.print("Nhap ho nhan vien: ");
                            String ho = sc.nextLine();
                            DanhSachNV ketQuaHo = dsnv.timKiemTheoHo(ho);
                            if (ketQuaHo.getSoLuongNV() > 0) {
                                System.out.println("Cac nhan vien co ho '" + ho + "':");
                                ketQuaHo.xuatdsnv();
                            } else {
                                System.out.println("Khong tim thay nhan vien nao.");
                            }
                            break;
                        case 3:
                            System.out.print("Nhap ten nhan vien: ");
                            String ten = sc.nextLine();
                            DanhSachNV ketQuaTen = dsnv.timKiemTheoTen(ten);
                            if (ketQuaTen.getSoLuongNV() > 0) {
                                System.out.println("Cac nhan vien co ten '" + ten + "':");
                                ketQuaTen.xuatdsnv();
                            } else {
                                System.out.println("Khong tim thay nhan vien nao.");
                            }
                            break;
                        default:
                            System.out.println("Lua chon khong hop le.");
                    }
                    break;
                case 4:
                    dsnv.xoaNhanVien();
                    break;
                case 5:
                    dsnv.suaNhanVien();
                    break;
                case 0:
                    System.out.println("Thoat khoi menu quan ly nhan vien.");
                    break;
                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai.");
            }
        } while (choice != 0);
    }
}
