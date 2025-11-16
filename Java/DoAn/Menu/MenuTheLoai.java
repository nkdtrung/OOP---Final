package Java.DoAn.Menu;

import java.util.Scanner;
import Java.DoAn.DS_Class.DanhSachTheLoai;

public class MenuTheLoai {
	private DanhSachTheLoai dsTheLoai;

	public MenuTheLoai(DanhSachTheLoai dsTheLoai) {
		this.dsTheLoai = dsTheLoai;
	}

	public void MenuChinh() {
		if (dsTheLoai == null) dsTheLoai = new DanhSachTheLoai();
		dsTheLoai.docFile("Java/DoAn/input/inputTheLoai.txt");
		Scanner sc = new Scanner(System.in);
		int choice;
		do {
			System.out.println("===== MENU QUAN LY THE LOAI =====");
			System.out.println("1. Xuat danh sach the loai");
			System.out.println("2. Them the loai");
			System.out.println("3. Tim kiem the loai");
			System.out.println("4. Xoa the loai");
			System.out.println("5. Sua thong tin the loai");
			System.out.println("0. Thoat");
			System.out.print("Nhap lua chon cua ban: ");
			choice = sc.nextInt();
			sc.nextLine();

			switch (choice) {
				case 1:
					dsTheLoai.xuatDSTL();
					System.out.println();
					break;
				case 2:
					dsTheLoai.themTheLoai();
					break;
				case 3:
					dsTheLoai.timTheLoai();
					break;
				case 4:
					dsTheLoai.xoaTheLoai();
					break;
				case 5:
					dsTheLoai.suaTheLoai();
					break;
				case 0:
					System.out.println("Thoat khoi menu quan ly the loai.");
					break;
				default:
					System.out.println("Lua chon khong hop le. Vui long chon lai.");
			}
		} while (choice != 0);
	}
}
