package Java.DoAn.Menu;

import java.util.Scanner;
import Java.DoAn.DS_Class.DanhSachNXB;

public class MenuNXB {
    private DanhSachNXB dsnxb;

    public MenuNXB(DanhSachNXB dsnxb) {
        this.dsnxb = dsnxb;
    }

    public void MenuChinh() {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n===== MENU QUAN LY NHA XUAT BAN =====");
            System.out.println("1. Xuat danh sach nha xuat ban");
            System.out.println("2. Them nha xuat ban");
            System.out.println("3. Tim kiem nha xuat ban");
            System.out.println("4. Xoa nha xuat ban");
            System.out.println("5. Sua thong tin nha xuat ban");
            System.out.println("0. Thoat");
            System.out.print("Nhap lua chon cua ban: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    dsnxb.xuatDSNXB();
                    System.out.println();
                    break;
                case 2:
                    dsnxb.themNXB();
                    break;
                case 3:
                    dsnxb.timNXB();
                    break;
                case 4:
                    dsnxb.xoaNXB();
                    break;
                case 5:
                    dsnxb.suaNXB();
                    break;
                case 0:
                    System.out.println("Thoat khoi menu quan ly nha xuat ban.");
                    break;
                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai.");
            }
        } while (choice != 0);
    }
}
