package Java.DoAn.Menu;

import java.util.Map;
import java.util.Scanner;

import Java.DoAn.Class_chinh.ChiTietHoaDon;
import Java.DoAn.DS_Class.DanhSachHoaDon;
import Java.DoAn.DS_Class.DanhSachNV;
import Java.DoAn.DS_Class.DanhSachSach;
import Java.DoAn.DS_Class.DanhSachCTHD;
import Java.DoAn.Class_chinh.HoaDon;

public class MenuHoaDon {
    private DanhSachHoaDon dshd;

    public MenuHoaDon(DanhSachHoaDon dshd) {
        this.dshd = dshd;
    }

    public void MenuChinh() {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("===== MENU QUAN LY HOA DON =====");
            System.out.println("1. Xuat danh sach hoa don");
            System.out.println("2. Them hoa don");
            System.out.println("3. Tim kiem hoa don");
            System.out.println("4. Xoa hoa don");
            System.out.println("5. Sua thong tin hoa don");
            System.out.println("6. Thong ke tong thu theo nam");
            System.out.println("7. Xem chi tiet hoa don theo ma");
            System.out.println("8. Tinh tong tien theo khoang thoi gian");
            System.out.println("9. Thong ke chi tieu theo khach hang va nam");
            System.out.println("10. Thong ke hieu suat nhan vien");
            System.out.println("0. Thoat");
            System.out.print("Nhap lua chon cua ban: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    dshd.xuat();
                    System.out.println();
                    break;
                case 2:
                    dshd.themHoaDon();
                    break;
                case 3:
                    System.out.print("Nhap ma hoa don can tim: ");
                    String maTim = sc.nextLine();
                    var hd = dshd.timKiemTheoMa(maTim);
                    if (hd != null) {
                        System.out.println("Thong tin hoa don:");
                        System.out.printf("%-10s %-10s %-10s %-15s %15s\n", "Ma HD", "Ma NV", "Ma KH", "Ngay Lap", "Tong Tien");
                        hd.xuat();
                    } else {
                        System.out.println("Khong tim thay hoa don.");
                    }
                    break;
                case 4:
                    System.out.println("Nhap ma hoa don can xoa: ");
                    String maXoa = sc.nextLine();
                    dshd.xoaHoaDon(maXoa);
                    break;
                case 5:
                    System.out.println("Nhap ma hoa don can sua: ");
                    String maSua = sc.nextLine();
                    dshd.suaHoaDon(maSua);
                    break;
                case 6:
                    dshd.thongKeTongThu();
                    break;
                case 7:
                    // Chức năng mới: Xem chi tiết hóa đơn theo mã
                    System.out.print("Nhap ma hoa don can xem chi tiet: ");
                    String maChiTiet = sc.nextLine();
                    dshd.xuatChiTietTheoMa(maChiTiet); // Gọi hàm vừa tạo ở DanhSachHoaDon
                    break;
                case 8:
                    System.out.print("Nhap ngay bat dau (yyyy-MM-dd): ");
                    String tuNgay = sc.nextLine();
                    System.out.print("Nhap ngay ket thuc (yyyy-MM-dd): ");
                    String denNgay = sc.nextLine();
                    dshd.tinhTongTienTheoKhoangThoiGian(tuNgay, denNgay);
                    break;
                case 9:
                    dshd.thongKeChiTieuTheoKhachHangVaNam();
                    break;
                case 10:
                    dshd.thongKeHieuSuatNhanVien();
                    break;
                case 0:
                    System.out.println("Thoat khoi menu quan ly hoa don.");
                    break;  
                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai.");
            }
        } while (choice != 0);
    }

    public void themHoaDon() {
        Scanner sc = new Scanner(System.in);
        DanhSachSach dssach = new DanhSachSach();
        dssach.docFile("Java/DoAn/input/inputSach.txt");
        DanhSachCTHD dscthd = new DanhSachCTHD();
        dscthd.docFile("Java/DoAn/input/inputChiTietHD.txt");
        DanhSachNV dsnv = new DanhSachNV();
        dsnv.docFile("Java/DoAn/input/inputNhanVien.txt");
        
        String mahd;
        do {
            System.out.print("Nhap ma hoa don: ");
            mahd = sc.nextLine();
            if (dshd.timKiemTheoMa(mahd) != null) {
                System.out.println("Ma hoa don da ton tai. Vui long nhap lai!");
            }
        } while (dshd.timKiemTheoMa(mahd) != null);
        System.out.print("Nhap ma nhan vien: ");
        String manv = sc.nextLine();
        if (dsnv.timKiemTheoMa(manv) == null) {
            System.out.println("Ma nhan vien khong ton tai. Vui long kiem tra lai.");
            return;
        }
        System.out.print("Nhap ma khach hang: ");
        String makh = sc.nextLine();
        System.out.print("Nhap ngay lap (yyyy-MM-dd): ");
        String ngaylap = sc.nextLine();
        
        double tongtien = 0.0;
        System.out.print("Nhap so luong chi tiet hoa don: ");
        int soluongCTHD = sc.nextInt();
        sc.nextLine(); // Clear buffer
        
        for (int i = 0; i < soluongCTHD; i++) {
            System.out.println("\n--- Chi tiet thu " + (i+1) + " ---");
            System.out.print("Nhap ma sach: ");
            String masach = sc.nextLine();
            if (dssach.timKiemTheoMa(masach) == null) {
                System.out.println("Ma sach khong ton tai. Vui long kiem tra lai.");
                i--;
                continue;
            }
            System.out.print("Nhap so luong: ");
            int soluong = sc.nextInt();
            System.out.print("Nhap don gia: ");
            double dongia = sc.nextDouble();
            sc.nextLine(); // Clear buffer
            
            double thanhtien = soluong * dongia;
            tongtien += thanhtien;
            
            ChiTietHoaDon ct = new ChiTietHoaDon(mahd, masach, soluong, dongia, thanhtien);
            dscthd.themChiTietHoaDon(ct);
            dssach.capNhatSL(masach, soluong);
        }
        
        // Tạo hóa đơn với tổng tiền đã tính
        HoaDon hd = new HoaDon(mahd, manv, makh, ngaylap, tongtien);
        this.dshd.themHoaDon(hd); // Thêm vào danh sách hóa đơn
        hd.ghiFile(); // Ghi vào file
        
        System.out.println("\n=== THONG TIN HOA DON VUA TAO ===");
        System.out.printf("Ma HD: %s | Ma NV: %s | Ma KH: %s\n", mahd, manv, makh);
        System.out.printf("Ngay lap: %s | Tong tien: %.2f\n", ngaylap, tongtien);
        System.out.println("Da them hoa don thanh cong!");
    }
}
