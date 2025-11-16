package Java.DoAn.DS_Class;

import Java.DoAn.Class_chinh.Sach;
import Java.DoAn.Class_chinh.SachNuocNgoai;
import Java.DoAn.Class_chinh.SachThuong;
import Java.DoAn.Class_chinh.TapChi;
import java.util.Arrays;
import java.util.Scanner;
import java.io.*;

public class DanhSachTonKho {
	private Sach[] ds;
	private int n;

	public DanhSachTonKho() {
		ds = new Sach[0];
		n = 0;
	}

	public DanhSachTonKho(Sach[] ds, int n) {
		this.ds = ds;
		this.n = n;
	}

	// Đọc file sách 
	public void docFile(String filePath) {
		ds = new Sach[0];
		n = 0;
		try (Scanner sc = new Scanner(new java.io.File(filePath))) {
			while (sc.hasNextLine()) {
				String line = sc.nextLine().trim();
				if (line.isEmpty() || line.startsWith("Tong:")) continue;
				String[] parts = line.split(",");
				if (parts.length < 7) continue;
				String masach = parts[0].trim();
				String tensach = parts[1].trim();
				String tacgia = parts[2].trim();
				String matl = parts[3].trim();
				String manxb = parts[4].trim();
				int soLuong = Integer.parseInt(parts[5].trim());
				double donGia = Double.parseDouble(parts[6].trim());

				Sach s = null;
				if (parts.length == 7) {
					s = new SachThuong(masach, tensach, tacgia, matl, manxb, soLuong, donGia);
				} else if (parts[7].trim().equalsIgnoreCase("SachNN")) {
					String ngonNgu = parts.length > 8 ? parts[8].trim() : "";
					String quocGia = parts.length > 9 ? parts[9].trim() : "";
					s = new SachNuocNgoai(masach, tensach, tacgia, matl, manxb, soLuong, donGia, ngonNgu, quocGia);
				} else if (parts[7].trim().equalsIgnoreCase("TapChi")) {
					int soPhatHanh = parts.length > 9 ? Integer.parseInt(parts[9].trim()) : 0;
					String chuyenMuc = parts.length > 10 ? parts[10].trim() : "";
					s = new TapChi(masach, tensach, tacgia, matl, manxb, soLuong, donGia, soPhatHanh, chuyenMuc);
				} else {
					s = new SachThuong(masach, tensach, tacgia, matl, manxb, soLuong, donGia);
				}

				n = ds.length;
				ds = Arrays.copyOf(ds, n + 1);
				ds[n] = s;
				n++;
			}
		} catch (java.io.FileNotFoundException e) {
			System.out.println("Khong tim thay file: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Loi doc file: " + e.getMessage());
		}
	}

	// Tính và in danh sách tồn kho
	public void thongKeTonKho() {
		if (n == 0) {
			System.out.println("Danh sach sach rong. Hay goi docFile() truoc khi thong ke.");
			return;
		}

		int[] tongNhap = new int[n];
		int[] tongBan = new int[n];

		// Đọc chi tiết phiếu nhập
		try (Scanner sc = new Scanner(new java.io.File("Java/DoAn/input/inputChiTietPNH.txt"))) {
			while (sc.hasNextLine()) {
				String line = sc.nextLine().trim();
				if (line.isEmpty() || line.startsWith("Tong:")) continue;
				String[] parts = line.split(",");
				if (parts.length < 3) continue;
				String masach = parts[1].trim();
				int so = Integer.parseInt(parts[2].trim());
				for (int i = 0; i < n; i++) {
					if (ds[i].getMaSach().equals(masach)) {
						tongNhap[i] += so;
						break;
					}
				}
			}
		} catch (Exception e) {
		}

		// Đọc chi tiết hóa đơn
		try (Scanner sc = new Scanner(new java.io.File("Java/DoAn/input/inputChiTietHD.txt"))) {
			while (sc.hasNextLine()) {
				String line = sc.nextLine().trim();
				if (line.isEmpty() || line.startsWith("Tong:")) continue;
				String[] parts = line.split(",");
				if (parts.length < 3) continue;
				String masach = parts[1].trim();
				int so = Integer.parseInt(parts[2].trim());
				for (int i = 0; i < n; i++) {
					if (ds[i].getMaSach().equals(masach)) {
						tongBan[i] += so;
						break;
					}
				}
			}
		} catch (Exception e) {
		}

		// In bảng tồn kho
		System.out.printf("%-10s %-40s %10s %10s %10s%n", "MaSach", "TenSach", "Nhap", "Ban", "TonKho");
		for (int i = 0; i < n; i++) {
			int initial = ds[i].getSoLuong();
			int ton = initial + tongNhap[i] - tongBan[i];
			System.out.printf("%-10s %-40s %10d %10d %10d%n", ds[i].getMaSach(), ds[i].getTenSach(), tongNhap[i], tongBan[i], ton);
		}

		// Ghi kết quả vào file inputTonKho.txt
		try {
			File out = new File("Java/DoAn/input/inputTonKho.txt");
			File parent = out.getParentFile();
			if (parent != null && !parent.exists()) parent.mkdirs();
			File tmp = new File(parent, out.getName() + ".tmp");
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(tmp))) {
				for (int i = 0; i < n; i++) {
					int initial = ds[i].getSoLuong();
					int ton = initial + tongNhap[i] - tongBan[i];
					bw.write(ds[i].getMaSach() + "," + ton);
					bw.newLine();
				}
				bw.write("Tong: " + n);
				bw.newLine();
			}
			if (!tmp.renameTo(out)) {
				try (FileInputStream fis = new FileInputStream(tmp);
					 FileOutputStream fos = new FileOutputStream(out)) {
					byte[] buf = new byte[8192];
					int r;
					while ((r = fis.read(buf)) != -1) fos.write(buf, 0, r);
				} catch (Exception ignore) {
				}
				tmp.delete();
			}
		} catch (Exception e) {
		}
	}
}
