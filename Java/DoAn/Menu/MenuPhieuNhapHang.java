package Java.DoAn.Menu;

import java.util.Scanner;

import Java.DoAn.Class_chinh.PhieuNhapHang;
import Java.DoAn.DS_Class.DanhSachPNH;
import Java.DoAn.DS_Class.DanhSachCTPNH;

public class MenuPhieuNhapHang {
	private DanhSachPNH dspnh;

	public MenuPhieuNhapHang(DanhSachPNH dspnh) {
		this.dspnh = dspnh;
	}

	public void MenuChinh() {
		Scanner sc = new Scanner(System.in);
		int choice;
		do {
			System.out.println("===== MENU QUAN LY PHIEU NHAP HANG =====");
			System.out.println("1. Xuat danh sach phieu nhap hang");
			System.out.println("2. Them phieu nhap hang");
			System.out.println("3. Tim kiem phieu nhap hang");
			System.out.println("4. Xoa phieu nhap hang");
			System.out.println("5. Sua thong tin phieu nhap hang");
			System.out.println("6. Thong ke tong nhap theo nam");
			System.out.println("7. Xem chi tiet phieu nhap hang theo ma");
			System.out.println("8. Thong ke doanh thu va loi nhuan theo quy");
			System.out.println("0. Thoat");
			System.out.print("Nhap lua chon cua ban: ");
			choice = sc.nextInt();
			sc.nextLine();

			switch (choice) {
				case 1:
					dspnh.xuat();
					System.out.println();
					break;
				case 2: {
					PhieuNhapHang pMoi = dspnh.themPNH();
					DanhSachCTPNH dsct = new DanhSachCTPNH();
					double tong = dsct.themDanhSachChoPNH(pMoi.getMaPNH());
					pMoi.setTongTien(tong);
					System.out.println("Them phieu nhap thanh cong");
					break;
				}
				case 3:
					System.out.print("Nhap ma phieu nhap hang can tim: ");
					String maTim = sc.nextLine();
					var pnh = dspnh.timKiemTheoMa(maTim);
					if (pnh != null) {
						System.out.println("Thong tin phieu nhap hang:");
						System.out.printf("%-10s %-10s %-10s %-15s %15s\n", "Ma PNH", "Ma NV", "Ma NCC", "Ngay Lap", "Tong Tien");
						pnh.xuat();
					} else {
						System.out.println("Khong tim thay phieu nhap hang.");
					}
					break;
				case 4:
					System.out.print("Nhap ma Phieu Nhap Hang can xoa: ");
					String maXoa = sc.nextLine();
					dspnh.xoaPNH(maXoa);
					break;
				case 5:
					System.out.print("Nhap ma Phieu Nhap Hang can sua: ");
					String maSua = sc.nextLine();
					dspnh.suaPNH(maSua);
					break;
				case 6: {
					dspnh.thongKeTheoNam();
					System.out.println("\n=== THONG KE CHI TIET PHIEU NHAP HANG ===");
					System.out.println("Tong so phieu: " + dspnh.tongSoPhieu());
					System.out.printf("Tong tien tat ca: %,.2f VND\n", dspnh.tongTienTatCa());
					DanhSachCTPNH dsct = new DanhSachCTPNH("Java/DoAn/input/inputChiTietPNH.txt");
					System.out.println("Tong so chi tiet: " + dsct.tongSoCT());
					System.out.println("Tong so luong sach nhap: " + dsct.tongSoLuongNhap());
					System.out.printf("Tong thanh tien chi tiet: %,.2f VND\n", dsct.tongThanhTien());
					break;
				}
				case 7:
					System.out.print("Nhap ma phieu nhap hang can xem chi tiet: ");
					String maChiTiet = sc.nextLine();
					dspnh.xuatChiTietTheoMa(maChiTiet); 
					break;
				case 8:
					System.out.print("Nhap nam can thong ke: ");
					int nam = sc.nextInt();
					sc.nextLine();
					dspnh.thongKeTheoQuy(nam);
					break;
				case 0:
					System.out.println("Thoat khoi menu quan ly phieu nhap hang.");
					break;
				default:
					System.out.println("Lua chon khong hop le. Vui long chon lai.");
			}
		} while (choice != 0);
	}
}