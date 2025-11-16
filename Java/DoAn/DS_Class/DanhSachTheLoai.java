package Java.DoAn.DS_Class;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

import Java.DoAn.Class_chinh.TheLoai;

public class DanhSachTheLoai {
	private TheLoai[] ds;
	private int n;

	// Hàm thiết lập
	public DanhSachTheLoai() {
		n = 0;
		ds = new TheLoai[0];
	}

	public DanhSachTheLoai(TheLoai[] ds, int n) {
		this.ds = ds;
		this.n = n;
	}

	public DanhSachTheLoai(DanhSachTheLoai d) {
		this.n = d.n;
		this.ds = new TheLoai[n];
		for (int i = 0; i < n; i++) {
			this.ds[i] = new TheLoai(d.ds[i]);
		}
	}

	// Xuất
	public void xuatDSTL() {
		System.out.printf("%-10s %-20s%n", "MaTL", "TenTheLoai");
		for (int i = 0; i < n; i++) {
			ds[i].xuat();
		}
		System.out.println("Tong: " + n + " the loai");
	}

	// Thêm
	public void themTheLoai() {
		if (ds == null || ds.length <= n) {
			ds = Arrays.copyOf(ds == null ? new TheLoai[0] : ds, n + 1);
		}
		ds[n] = new TheLoai();
		ds[n].nhap();
		n++;
		tuDongCapNhatFile();
	}

	public void themTheLoai(TheLoai tl) {
		ds = Arrays.copyOf(ds, n + 1);
		ds[n] = tl;
		n++;
		tuDongCapNhatFile();
	}

	// Tìm
	public void timTheLoai() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Nhap ma the loai can tim: ");
		String ma = sc.nextLine();
		timTheLoai(ma);
	}

	public TheLoai timTheLoai(String ma) {
		for (int i = 0; i < n; i++) {
			if (ds[i].getMaTheLoai().equals(ma)) {
				ds[i].xuat();
				return ds[i];
			}
		}
		System.out.println("Khong tim thay the loai co ma " + ma);
		return null;
	}

	// Xóa
	public void xoaTheLoai() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Nhap ma the loai can xoa: ");
		String ma = sc.nextLine();
		xoaTheLoai(ma);
	}

	public void xoaTheLoai(String ma) {
		for (int i = 0; i < n; i++) {
			if (ds[i].getMaTheLoai().equals(ma)) {
				for (int j = i; j < n - 1; j++) {
					ds[j] = ds[j + 1];
				}
				ds = Arrays.copyOf(ds, n - 1);
				n--;
				System.out.println("Da xoa the loai co ma " + ma);
				tuDongCapNhatFile();
				return;
			}
		}
		System.out.println("Khong tim thay the loai co ma " + ma);
	}

	// Sửa
	public void suaTheLoai() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Nhap ma the loai can sua: ");
		String ma = sc.nextLine();
		suaTheLoai(ma);
	}

	public void suaTheLoai(String ma) {
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < n; i++) {
			if (ds[i].getMaTheLoai().equals(ma)) {
				int choice;
				do {
					System.out.println("\n=== SUA THONG TIN THE LOAI ===");
					System.out.println("1. Ten the loai");
					System.out.println("2. Ma the loai");
					System.out.println("0. Thoat");
					System.out.print("Nhap lua chon: ");
					choice = sc.nextInt();
					sc.nextLine();
					switch (choice) {
						case 1:
							System.out.print("Nhap ten the loai moi: ");
							String ten = sc.nextLine();
							ds[i].setTenTheLoai(ten);
							System.out.println("Da cap nhat ten the loai!");
							break;
						case 2:
							System.out.print("Nhap ma the loai moi: ");
							String maMoi = sc.nextLine();
							ds[i].setMaTheLoai(maMoi);
							System.out.println("Da cap nhat ma the loai!");
							break;
						case 0:
							System.out.println("Thoat khoi sua thong tin.");
							break;
						default:
							System.out.println("Lua chon khong hop le!");
							break;
					}
				} while (choice != 0);
				tuDongCapNhatFile();
				System.out.println("\nDa luu thong tin the loai thanh cong!");
				return;
			}
		}
		System.out.println("Khong tim thay the loai co ma " + ma);
	}

	// Tự động cập nhật file sau khi thay đổi dữ liệu
	public void tuDongCapNhatFile() {
		ghiFile();
	}

	// Ghi file
	public void ghiFile() {
		try (PrintWriter writer = new PrintWriter(new FileWriter("Java/DoAn/input/inputTheLoai.txt"))) {
			for (int i = 0; i < n; i++) {
				writer.println(ds[i].getMaTheLoai() + "," + ds[i].getTenTheLoai());
			}
			writer.println("Tong: " + n);
		} catch (IOException e) {
			System.out.println("Loi ghi file: " + e.getMessage());
		}
	}

	// Đọc file
	public void docFile(String filePath) {
		this.ds = new TheLoai[0];
		n = 0;
		try (Scanner sc = new Scanner(new File(filePath))) {
			while (sc.hasNextLine()) {
				String line = sc.nextLine().trim();
				if (line.isEmpty())
					continue;

				String[] parts = line.split(",");
				if (parts.length < 2)
					continue;

				String ma = parts[0].trim();
				String ten = parts[1].trim();
				TheLoai tl = new TheLoai(ma, ten);
				themTheLoai(tl);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Khong tim thay file: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Loi doc file: " + e.getMessage());
		}
	}
}
