package Java.DoAn.DS_Class;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import Java.DoAn.Class_chinh.KhachHang;

public class DanhSachKH {
    private KhachHang[] ds;
    private int n;
    private static int nextId = 1; //thuộc tính static

    public DanhSachKH() {
        ds = new KhachHang[0];
        n = 0;
    }
    public DanhSachKH(KhachHang[] ds, int n) {
        this.ds = new KhachHang[n];
        for (int i = 0; i < n; i++) {
            this.ds[i] = new KhachHang(ds[i]);
        }
        this.n = n;
    }
    public DanhSachKH(DanhSachKH d) {
        this.ds = new KhachHang[d.n];
        for (int i = 0; i < d.n; i++) {
            this.ds[i] = new KhachHang(d.ds[i]);
        }
        this.n = d.n;
    }	

    private static String taoMaKHTuDong() {  // Phương thức static
        return String.format("KH%03d", nextId++);
    }



    public void themKH() {
        KhachHang k = new KhachHang();
        String maKH = taoMaKHTuDong();
        k.setMaKH(maKH);
        System.out.println("Mã KH: " + maKH);
        k.nhap();
        
        ds = Arrays.copyOf(ds, n + 1);
        ds[n] = new KhachHang(k);
        n++;
        
        tuDongCapNhatFile();
    }

    public void themKH(KhachHang k) {
        String maKH = taoMaKHTuDong();
        k.setMaKH(maKH);
        
        ds = Arrays.copyOf(ds, n + 1);
        ds[n] = new KhachHang(k);
        n++;
        
        tuDongCapNhatFile();
    }
    

    public void inDanhSach() {
        System.out.println("=====================================================================================================");
        System.out.printf("%-15s %-25s %-25s %-20s %-15s %-10s\n", "Mã KH", "Họ", "Tên", "Địa Chỉ", "SĐT", "Giới Tính");
        System.out.println("=====================================================================================================");
        for (int i = 0; i < n; i++) {
            ds[i].xuat();
        }
        System.out.println("Tong: " + n + " khach hang");
    }


    public void xoaKhachHang(String maKH) {
		boolean found = false;
		for (int i = 0; i < n; i++) {
			if (ds[i].getMaKH().equals(maKH)) {
				for (int j = i; j < n - 1; j++) {
					ds[j] = ds[j + 1];
				}
				ds[n - 1] = null;
				n--;
				found = true;
				tuDongCapNhatFile();
				break;
			}
		}
		if (!found) {
			System.out.println("Không tìm thấy khách hàng có mã: " + maKH);
		}
	}


    public void suaThongTin(String maKH) {
		Scanner sc = new Scanner(System.in);
		boolean found = false;
		
		int viTri = -1;
		for (int i = 0; i < n; i++) {
			if (ds[i].getMaKH().equals(maKH)) {
				viTri = i;
				found = true;
				break;
			}
		}
		
		if (!found) {
			System.out.println("Không tìm thấy khách hàng có mã: " + maKH);
			return;
		}
		
		System.out.println("\nChọn thông tin muốn sửa:");
		System.out.println("1. Họ");
		System.out.println("2. Tên");
		System.out.println("3. Địa chỉ");
		System.out.println("4. Số điện thoại");
		System.out.println("5. Giới tính");
		System.out.print("Lựa chọn: ");
		
		int luaChon = sc.nextInt();
		sc.nextLine();
		
		switch (luaChon) {
			case 1:
				System.out.print("Nhập họ mới: ");
				String hoMoi = sc.nextLine();
				ds[viTri].setHoKH(hoMoi);
				break;
				
			case 2:
				System.out.print("Nhập tên mới: ");
				String tenMoi = sc.nextLine();
				ds[viTri].setTenKH(tenMoi);
				break;

			case 3:
				System.out.print("Nhập địa chỉ mới: ");
				String diaChiMoi = sc.nextLine();
				ds[viTri].setDiachi(diaChiMoi);
				break;
				
			case 4:
				System.out.print("Nhập số điện thoại mới: ");
				String sdtMoi = sc.nextLine();
				ds[viTri].setSdt(sdtMoi);
				break;

			case 5:
				System.out.print("Nhập giới tính mới: ");
				String gioiTinhMoi = sc.nextLine();
				ds[viTri].setGioiTinh(gioiTinhMoi);
				break;
				
			default:
				System.out.println("Lựa chọn không hợp lệ!");
				return;
		}
		
		System.out.println("\nThông tin sau khi cập nhật:");
		ds[viTri].xuat();
		
		tuDongCapNhatFile();
	}

	
	public KhachHang timKiemTheoMa(String maKH) {
		for (int i = 0; i < n; i++) {
			if (ds[i].getMaKH().equalsIgnoreCase(maKH)) {
				return ds[i];
			}
		}
		return null;
	}
	
	public DanhSachKH timKiemTheoTen(String ten) {
		DanhSachKH ketQua = new DanhSachKH();
		for (int i = 0; i < n; i++) {
			if (ds[i].getTenKH().toLowerCase().contains(ten.toLowerCase())) {
				ketQua.ds = Arrays.copyOf(ketQua.ds, ketQua.n + 1);
				ketQua.ds[ketQua.n] = new KhachHang(ds[i]);
				ketQua.n++;
			}
		}
		return ketQua;
	}
	
	private int[] tinhThongKeTheoGioiTinh() {
		int nam = 0, nu = 0, khac = 0;
		
		for (int i = 0; i < n; i++) {
			String gioiTinh = ds[i].getGioiTinh().toLowerCase();
			if (gioiTinh.equals("nam")) {
				nam++;
			} else if (gioiTinh.equals("nu")) {
				nu++;
			} else {
				khac++;
			}
		}
		
		return new int[] {nam, nu, khac};
	}
	
	public void thongKeTheoGioiTinh() {
		int[] result = tinhThongKeTheoGioiTinh();
		int nam = result[0];
		int nu = result[1];
		int khac = result[2];
		
		System.out.println("======= THỐNG KÊ KHÁCH HÀNG THEO GIỚI TÍNH =======");
		System.out.println("Tổng số khách hàng: " + n);
		System.out.println("Nam: " + nam + " khách hàng");
		System.out.println("Nữ: " + nu + " khách hàng");
		if (khac > 0) {
			System.out.println("Khác: " + khac + " khách hàng");
		}
		System.out.println("==================================================");
	}
	
	public void hienThiKetQuaTimKiem(String tieuDe) {
		System.out.println("======= " + tieuDe.toUpperCase() + " =======");
		if (n == 0) {
			System.out.println("Không tìm thấy khách hàng nào!");
		} else {
			System.out.println("Tìm thấy " + n + " khách hàng:");
			inDanhSach();
		}
		System.out.println("===============================================");
	}
	

	public int getSoLuongKH() {
		return n;
	}
	
	public KhachHang[] getDanhSach() {
		return ds;
	}
	

	public void tuDongCapNhatFile() {
		ghiThongTinKhachHang();
		ghiThongKeTheoGioiTinh();
	}
	

	public void ghiThongTinKhachHang() {
		try (PrintWriter writer = new PrintWriter(new FileWriter("Java/DoAn/input/inputKhachHang.txt"))) {
			for (int i = 0; i < n; i++) {
				writer.println(ds[i].getMaKH() + "," + 
					ds[i].getHoKH() + "," + 
					ds[i].getTenKH() + "," + 
					ds[i].getDiachi() + "," + 
					ds[i].getSdt() + "," + 
					ds[i].getGioiTinh());
			}
			writer.println("Tong: " + n);
		} catch (IOException e) {
			
		}
	}
	
	
	public void ghiThongKeTheoGioiTinh() {
		try (PrintWriter writer = new PrintWriter(new FileWriter("A_Class/thongke_gioitinh.txt"))) {
			int[] result = tinhThongKeTheoGioiTinh();
			int nam = result[0];
			int nu = result[1];
			int khac = result[2];
			
			writer.println("Nam." + nam);
			writer.println("Nu." + nu);
			writer.println("Tong:" + n);
			if (khac > 0) {
				writer.println("Khac." + khac);
			}
		} catch (IOException e) {
			
		}
	}
	
	
	public boolean docTuFile() {
		File file = new File("Java/DoAn/input/inputKhachHang.txt");
		if (!file.exists()) {
			return false;
		}
		
		// Reset dữ liệu trước khi đọc để tránh lặp
		ds = new KhachHang[0];
		n = 0;
		
		try (BufferedReader reader = new BufferedReader(new FileReader("Java/DoAn/input/inputKhachHang.txt"))) {
			String line;
			
			while ((line = reader.readLine()) != null) {
				line = line.trim();
				
				if (line.isEmpty() || line.startsWith("Tong:")) {
					continue;
				}
				
				String[] parts = line.split(",");
				
				if (parts.length == 6) {
					String maKH = parts[0].trim();
					String ho = parts[1].trim();
					String ten = parts[2].trim();
					String diaChi = parts[3].trim();
					String sdt = parts[4].trim();
					String gioiTinh = parts[5].trim();
					
					KhachHang kh = new KhachHang(maKH, ho, ten, diaChi, sdt, gioiTinh);
					
					ds = Arrays.copyOf(ds, n + 1);
					ds[n] = kh;
					n++;
					
					// Cập nhật nextId
					try {
						String[] idParts = maKH.split("KH");
						if (idParts.length == 2) {
							int id = Integer.parseInt(idParts[1]);
							if (id >= nextId) {
								nextId = id + 1;
							}
						}
					} catch (NumberFormatException e) {				
					}
				}
			}
			
			return true;
			
		} catch (IOException e) {
			return false;
		}
	}
}


