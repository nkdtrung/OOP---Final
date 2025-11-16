package Java.DoAn.Menu;

import java.util.Scanner;
import Java.DoAn.DS_Class.DanhSachNCC;

public class MenuNCC {
    private DanhSachNCC dsNCC;

    public MenuNCC(DanhSachNCC dsNCC) {
        this.dsNCC = dsNCC;
    }

    public void MenuChinh() {
        if (dsNCC == null) dsNCC = new DanhSachNCC();
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("===== MENU QUAN LY NHA CUNG CAP =====");
            System.out.println("1. Xuat danh sach nha cung cap");
            System.out.println("2. Them nha cung cap");
            System.out.println("3. Tim kiem nha cung cap");
            System.out.println("4. Xoa nha cung cap");
            System.out.println("5. Sua thong tin nha cung cap");
            System.out.println("0. Thoat");
            System.out.print("Nhap lua chon cua ban: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    dsNCC.xuatDSNCC();
                    System.out.println();
                    break;
                case 2:
                    dsNCC.themNCC();
                    break;
                case 3:
                    dsNCC.timNCC();
                    break;
                case 4:
                    System.out.println("Nhap ma nha cung cap can xoa: ");
                    String maXoa = sc.nextLine();
                    dsNCC.xoaNCC(maXoa);
                    break;
                case 5:
                    System.out.println("Nhap ma nha cung cap can sua: ");
                    String maSua = sc.nextLine();
                    dsNCC.suaNCC(maSua);
                    break;
                case 0:
                    System.out.println("Thoat khoi menu quan ly nha cung cap.");
                    break;
                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai.");
            }
        } while (choice != 0);
    }

}
