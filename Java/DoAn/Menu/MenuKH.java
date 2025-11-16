package Java.DoAn.Menu;

import java.util.Scanner;
import Java.DoAn.DS_Class.DanhSachKH;

public class MenuKH {
    private DanhSachKH dsKH;

    public MenuKH(DanhSachKH dsKH) {
        this.dsKH = dsKH;
    }

    public void MenuChinh() {
        if (dsKH == null) dsKH = new DanhSachKH();
        dsKH.docTuFile();
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n===== MENU QUAN LY KHACH HANG =====");
            System.out.println("1. Xuat danh sach khach hang");
            System.out.println("2. Them khach hang");
            System.out.println("3. Tim kiem khach hang");
            System.out.println("4. Xoa khach hang");
            System.out.println("5. Sua thong tin khach hang");
            System.out.println("6. Thong ke theo gioi tinh");
            System.out.println("0. Thoat");
            System.out.print("Nhap lua chon cua ban: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    dsKH.inDanhSach();
                    System.out.println();
                    break;
                case 2:
                    dsKH.themKH();
                    break;
                case 3:
                    System.out.println("1. Tim theo ma");
                    System.out.println("2. Tim theo ten");
                    System.out.print("Nhap lua chon: ");
                    int subChoice = sc.nextInt();
                    sc.nextLine();
                    switch (subChoice) {
                        case 1:
                            System.out.print("Nhap ma khach hang: ");
                            String ma = sc.nextLine();
                            var kh = dsKH.timKiemTheoMa(ma);
                            if (kh != null) {
                                System.out.println("Thong tin khach hang:");
                                System.out.printf("%-15s %-25s %-25s %-20s %-15s %-10s\n", "Ma KH", "Ho", "Ten", "Dia Chi", "SDT", "Gioi Tinh");
                                kh.xuat();
                            } else {
                                System.out.println("Khong tim thay khach hang.");
                            }
                            break;
                        case 2:
                            System.out.print("Nhap ten khach hang: ");
                            String ten = sc.nextLine();
                            DanhSachKH ketQua = dsKH.timKiemTheoTen(ten);
                            if (ketQua.getSoLuongKH() > 0) {
                                ketQua.hienThiKetQuaTimKiem("Ket qua tim kiem");
                            } else {
                                System.out.println("Khong tim thay khach hang nao.");
                            }
                            break;
                        default:
                            System.out.println("Lua chon khong hop le.");
                    }
                    break;
                case 4:
                    System.out.print("Nhap ma khach hang can xoa: ");
                    String maXoa = sc.nextLine();
                    dsKH.xoaKhachHang(maXoa);
                    break;
                case 5:
                    System.out.print("Nhap ma khach hang can sua: ");
                    String maSua = sc.nextLine();
                    dsKH.suaThongTin(maSua);
                    break;
                case 6:
                    dsKH.thongKeTheoGioiTinh();
                    break;
                case 0:
                    System.out.println("Thoat khoi menu quan ly khach hang.");
                    break;
                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai.");
            }
        } while (choice != 0);
    }
}
