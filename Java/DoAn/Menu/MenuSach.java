package Java.DoAn.Menu;

import java.util.Scanner;
import Java.DoAn.DS_Class.DanhSachSach;

public class MenuSach {
    public void MenuChinh() {
        DanhSachSach dsSach = new DanhSachSach();
        dsSach.docFile("Java/DoAn/input/inputSach.txt");
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("===== MENU QUAN LY SACH =====");
            System.out.println("1. Xuat danh sach sach");
            System.out.println("2. Them sach");
            System.out.println("3. Tim kiem sach");
            System.out.println("4. Xoa sach");
            System.out.println("5. Sua thong tin sach");
            System.out.println("0. Thoat");
            System.out.print("Nhap lua chon cua ban: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    dsSach.xuat();
                    System.out.println();
                    break;
                case 2:
                    dsSach.themSach();
                    break;
                case 3:
                    System.out.print("Nhap ma sach can tim: ");
                    String maTim = sc.nextLine();
                    var sach = dsSach.timKiemTheoMa(maTim);
                    if (sach != null) {
                        System.out.println("Thong tin sach:");
                        if (sach instanceof Java.DoAn.Class_chinh.SachNuocNgoai) {
                            System.out.printf("%-10s %-40s %-20s %-10s %-10s %-10s %-10s %-15s %-15s%n", 
                                "MaSach", "TenSach", "TacGia", "MaTL", "MaNXB", "SoLuong", "DonGia", "NgonNgu", "QuocGia");
                        } else if (sach instanceof Java.DoAn.Class_chinh.TapChi) {
                            System.out.printf("%-10s %-40s %-20s %-10s %-10s %-10s %-10s %-15s %-20s%n", 
                                "MaSach", "TenSach", "TacGia", "MaTL", "MaNXB", "SoLuong", "DonGia", "SoPhatHanh", "ChuyenMuc");
                        } else {
                            System.out.printf("%-10s %-40s %-20s %-10s %-10s %-10s %-10s%n", 
                                "MaSach", "TenSach", "TacGia", "MaTL", "MaNXB", "SoLuong", "DonGia");
                        }
                        sach.xuat();
                        System.out.println();
                    } else {
                        System.out.println("Khong tim thay sach.");
                    }
                    break;
                case 4:
                    System.out.println("Nhap ma sach can xoa: ");
                    String maXoa = sc.nextLine();
                    dsSach.xoaSach(maXoa);
                    break;
                case 5:
                    System.out.println("Nhap ma sach can sua: ");
                    String maSua = sc.nextLine();
                    dsSach.suaSach(maSua);
                    break;
                case 0:
                    System.out.println("Thoat khoi menu quan ly sach.");
                    break;
            }
        } while (choice != 0);
    }
}
