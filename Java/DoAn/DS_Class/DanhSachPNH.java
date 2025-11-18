package Java.DoAn.DS_Class;

import Java.DoAn.Class_chinh.CTPhieuNhapHang;
import Java.DoAn.Class_chinh.PhieuNhapHang;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class DanhSachPNH {
    private PhieuNhapHang dsPNH[];
    private int n;

    Scanner sc = new Scanner(System.in);

    public DanhSachPNH() {
        n = 0;
        dsPNH = new PhieuNhapHang[0];
    }
    public DanhSachPNH( PhieuNhapHang[] dsPNH,int n){
        this.dsPNH=dsPNH;
        this.n=n;
    }
    public DanhSachPNH(DanhSachPNH dsPNH){
        this.n = dsPNH.n;
        this.dsPNH = new PhieuNhapHang[n];
        for (int i=0;i<n;i++){
            this.dsPNH[i] = new PhieuNhapHang(dsPNH.dsPNH[i]);
        }
    }


    public void xuat() {
        System.out.printf("%-10s %-10s %-10s %-15s %15s\n", "Ma PNH", "Ma NV", "Ma NCC", "Ngay Lap", "Tong Tien");
        for (int i = 0; i < n; i++) {
            dsPNH[i].xuat();
        }
        System.out.println("Tong: " + n + " phieu nhap hang");
    }

    public void DSPNH() {
        n = 0;
        dsPNH = new PhieuNhapHang[0];

}
// Thêm
    public void themPNHFile() {
        DanhSachCTPNH dsctPNH = new DanhSachCTPNH();
        dsctPNH.docFile("Java/DoAn/input/inputChiTietPNH.txt");
        for (int i = 0; i < dsctPNH.size(); i++) {
            CTPhieuNhapHang ctPNH = dsctPNH.getCTPNH(i);
            String maPNH = ctPNH.getmaPNH();
            PhieuNhapHang pnh = this.timPNHKhongXuat(maPNH);
            if (pnh != null) {
                double thanhtien = ctPNH.getThanhTien();
                pnh.setTongTien(pnh.getTongTien() + thanhtien);
            }
        }
    }

    public PhieuNhapHang themPNH() {
        Scanner sc = new Scanner(System.in);
        String mapnh;
        do {
            System.out.print("Nhap ma phieu nhap hang: ");
            mapnh = sc.nextLine();
            if (timKiemTheoMa(mapnh) != null) {
                System.out.println("Ma phieu nhap hang da ton tai. Vui long nhap lai!");
            }
        } while (timKiemTheoMa(mapnh) != null);
        
        n = dsPNH.length;
        dsPNH = Arrays.copyOf(dsPNH, n + 1);
        dsPNH[n] = new PhieuNhapHang();
        dsPNH[n].setMaPNH(mapnh);
        System.out.println("Ma PNH: " + mapnh);
        dsPNH[n].nhap();
        n++;
        tuDongCapNhatFile();
        return dsPNH[n - 1];
    }

    public void themPNH(PhieuNhapHang pnh) {
        if (timKiemTheoMa(pnh.getMaPNH()) != null) {
            System.out.println("Ma phieu nhap hang da ton tai. Khong the them!");
            return;
        }
        dsPNH = Arrays.copyOf(dsPNH, n + 1);
        dsPNH[n] = new PhieuNhapHang(pnh);
        n++;
    }

    // Tìm
    public PhieuNhapHang timKiemTheoMa(String ma) {
        for (int i = 0; i < n; i++) {
            if (dsPNH[i].getMaPNH().equalsIgnoreCase(ma)) {
                return dsPNH[i];
            }
        }
        return null;
    }
    
    // Tìm phiếu nhập hàng không xuất ra màn hình (dùng nội bộ)
    private PhieuNhapHang timPNHKhongXuat(String ma) {
        for (int i = 0; i < n; i++) {
            if (dsPNH[i].getMaPNH().equals(ma)) {
                return dsPNH[i];
            }
        }
        System.out.println("Khong tim thay Phieu Nhap Hang co ma " + ma);
        return null;
    }

    // Sửa
    public void suaPNH() {
        System.out.print("Nhap ma phieu nhap can sua: ");
        String ma = sc.nextLine();
        suaPNH(ma);
    }

    public void suaPNH(String ma) {
        for (int i = 0; i < n; i++) {
            if (dsPNH[i].getMaPNH().equals(ma)) {
                System.out.println("Chon thong tin can sua:");
                System.out.println("1.Ma phieu nhap");
                System.out.println("2.Ma nhan vien");
                System.out.println("3.Ma nha cung cap");
                System.out.println("4.Ngay lap");
                System.out.println("5.Tong tien");
                System.out.print("Nhap lua chon: ");
                int choice = sc.nextInt();
                sc.nextLine(); // Xóa buffer
                
                switch (choice) {
                    case 1:
                        System.out.print("Nhap ma phieu nhap moi: ");
                        String mapnh = sc.nextLine();
                        dsPNH[i].setMaPNH(mapnh);
                        break;
                    case 2:
                        System.out.print("Nhap ma nhan vien moi: ");
                        String manv = sc.nextLine();
                        dsPNH[i].setMaNV(manv);
                        break;
                    case 3:
                        System.out.print("Nhap ma nha cung cap moi: ");
                        String mancc = sc.nextLine();
                        dsPNH[i].setMaNCC(mancc);
                        break;
                    case 4:
                        System.out.print("Nhap ngay lap moi: ");
                        String ngay = sc.nextLine();
                        dsPNH[i].setNgayLapPhieu(ngay);
                        break;
                    case 5:
                        System.out.print("Nhap tong tien moi: ");
                        double tong = sc.nextDouble();
                        dsPNH[i].setTongTien(tong);
                        break;
                    default:
                        System.out.println("Lua chon khong hop le!");
                        return;
                }
                
                System.out.println("Da cap nhat thong tin phieu nhap hang thanh cong!");
                tuDongCapNhatFile();
                return;
            }
        }
        System.out.println("Khong tim thay phieu nhap co ma " + ma);
    }

    // Xóa
    public void xoaPNH() {
        System.out.print("Nhap ma phieu nhap can xoa: ");
        String ma = sc.nextLine();
        xoaPNH(ma);
    }

    public void xoaPNH(String ma) {
        for (int i = 0; i < n; i++) {
            if (dsPNH[i].getMaPNH().equals(ma)) {
                for (int j = i; j < n - 1; j++) {
                    dsPNH[j] = dsPNH[j + 1];
                }
                dsPNH = Arrays.copyOf(dsPNH, n - 1);
                n--;
                System.out.println("Da xoa phieu nhap co ma " + ma);
                tuDongCapNhatFile();
                return;
            }
        }
        System.out.println("Khong tim thay phieu nhap co ma " + ma);
    }

    // Thống kê
    public void thongKeTheoNam() {
        int namMin = 9999, namMax = 0;
        for (int i = 0; i < n; i++) {
            try {
                LocalDate ngaylap = LocalDate.parse(dsPNH[i].getNgayLapPhieu());
                int nam = ngaylap.getYear();
                if (nam < namMin) namMin = nam;
                if (nam > namMax) namMax = nam;
            } catch (Exception e) {
            }
        }
        
        // Tạo mảng thống kê
        int soNam = namMax - namMin + 1;
        int[] nam = new int[soNam];
        double[] tongTien = new double[soNam];
        
        for (int i = 0; i < soNam; i++) {
            nam[i] = namMin + i;
            tongTien[i] = 0.0;
        }
        
        // Tính tổng tiền theo năm
        for (int i = 0; i < n; i++) {
            try {
                LocalDate ngaylap = LocalDate.parse(dsPNH[i].getNgayLapPhieu());
                int namPNH = ngaylap.getYear();
                int viTri = namPNH - namMin;
                tongTien[viTri] += dsPNH[i].getTongTien();
            } catch (Exception e) {
            }
        }
        
        // Hiển thị kết quả
        System.out.println("\n=== THONG KE NHAP HANG THEO NAM ===");
        System.out.printf("%-10s %20s\n", "Nam", "Tong Tien (VND)");
        System.out.println("-----------------------------------");
        for (int i = 0; i < soNam; i++) {
            if (tongTien[i] > 0) {
                System.out.printf("%-10d %,20.2f\n", nam[i], tongTien[i]);
            }
        }
    }

    public double tongTienTatCa() {
        double sum = 0.0;
        for (int i = 0; i < n; i++) {
            sum += dsPNH[i].getTongTien();
        }
        return sum;
    }

    public int tongSoPhieu() {
        return n;
    }

    // Tự động cập nhật file sau khi thay đổi dữ liệu
    public void tuDongCapNhatFile() {
        ghiFile();
    }

    // Ghi file
    public void ghiFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("Java/DoAn/input/inputPhieuNhapHang.txt"))) {
            for (int i = 0; i < n; i++) {
                writer.println(dsPNH[i].getMaPNH() + "," + 
                                dsPNH[i].getMaNV() + "," + 
                                dsPNH[i].getMaNCC() + "," + 
                                dsPNH[i].getNgayLapPhieu());
            }
            writer.println("Tong: " + n);
        } catch (IOException e) {
            System.out.println("Loi ghi file: " + e.getMessage());
        }
    }

    // Doc File
    public void docFile(String filePath) {
        this.dsPNH = new PhieuNhapHang[0];
        n = 0;
        DanhSachNV dsnv = new DanhSachNV();
        dsnv.docFile("Java/DoAn/input/inputNhanVien.txt");
        DanhSachNCC dsncc = new DanhSachNCC();
        dsncc.docFile("Java/DoAn/input/inputNhaCungCap.txt");
        try (Scanner fileScanner = new Scanner(new File(filePath))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (line.isEmpty() || line.startsWith("Tong:"))
                    continue;

                String[] parts = line.split(",");
                if (parts.length < 4)
                    continue;

                String mapnh = parts[0].trim();
                String manv = parts[1].trim();
                if (dsnv.timKiemTheoMa(manv) == null) {
                    System.out.println("Ma nhan vien " + manv + " khong ton tai. Bo qua phieu " + mapnh);
                    continue;
                }
                String mancc = parts[2].trim();
                if (dsncc.timNCC(mancc) == null) {
                    System.out.println("Ma nha cung cap " + mancc + " khong ton tai. Bo qua phieu " + mapnh);
                    continue;
                }
                String ngaylap = parts[3].trim();

                PhieuNhapHang pnh = new PhieuNhapHang(mapnh, manv, mancc, ngaylap);
                themPNH(pnh);
            }
            // System.out.println("Da doc file " + filePath);
        } catch (FileNotFoundException e) {
            System.out.println("Khong tim thay file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Loi doc file: " + e.getMessage());
        }
    }

    public void xuatChiTietTheoMa(String maPNH) {

        PhieuNhapHang pnh = timKiemTheoMa(maPNH);
        if (pnh == null) {
            System.out.println("Khong tim thay phieu nhap hang voi ma: " + maPNH);
            return; 
        }

 
    DanhSachCTPNH dsctPNH = new DanhSachCTPNH();
        dsctPNH.docFile("Java/DoAn/input/inputChiTietPNH.txt");

        System.out.println("THONG TIN PHIEU NHAP HANG: " + maPNH);

        pnh.xuat(); 

    System.out.println("\n--- CHI TIET PHIEU NHAP HANG ---");
        System.out.printf("%-10s %-15s %-10s %12s %12s\n", 
                         "Ma PNH", "Ma Sach", "So Luong", "Don Gia", "Thanh Tien");
        System.out.println("--------------------------------------------------------------");

        boolean coChiTiet = false; 
        for (int i = 0; i < dsctPNH.size(); i++) {
            CTPhieuNhapHang ct = dsctPNH.getCTPNH(i);
 
            if (ct.getmaPNH().equals(maPNH)) {
                ct.xuat(); 
                coChiTiet = true; 
            }
        }

        if (!coChiTiet) {
            System.out.println("Khong co chi tiet nao cho phieu nhap hang nay.");
        }
      
    }

    public PhieuNhapHang layPNHCuoi() {
        if (n <= 0 || dsPNH == null || dsPNH.length == 0) return null;
        return dsPNH[n - 1];
    }

    // Thống kê theo quý: Tổng thu, Tổng chi, Lợi nhuận
    public void thongKeTheoQuy(int nam) {
        if (n == 0) {
            System.out.println("Danh sach phieu nhap hang rong!");
            return;
        }

        // Đọc danh sách hóa đơn để tính tổng thu
        DanhSachHoaDon dshd = new DanhSachHoaDon();
        dshd.docFile("Java/DoAn/input/inputHoaDon.txt");
        dshd.themHDFile();

        // Mảng lưu tổng chi và tổng thu theo quý (4 quý)
        double[] tongChi = new double[4];
        double[] tongThu = new double[4];

        // Tính tổng chi từ phiếu nhập hàng
        for (int i = 0; i < n; i++) {
            try {
                LocalDate ngaylap = LocalDate.parse(dsPNH[i].getNgayLapPhieu());
                if (ngaylap.getYear() == nam) {
                    int thang = ngaylap.getMonthValue();
                    int quy = (thang - 1) / 3; // Quý 0-3 (sẽ hiển thị là 1-4)
                    tongChi[quy] += dsPNH[i].getTongTien();
                }
            } catch (Exception e) {
            }
        }

        // Tính tổng thu từ hóa đơn (cần truy cập DanhSachHoaDon)
        // Giả sử có phương thức tính tổng thu theo quý trong DanhSachHoaDon
        for (int i = 0; i < 30; i++) { // Giả sử có tối đa 30 hóa đơn
            try {
                // Đọc từng hóa đơn và tính theo quý
                java.io.File file = new java.io.File("Java/DoAn/input/inputHoaDon.txt");
                java.util.Scanner sc = new java.util.Scanner(file);
                while (sc.hasNextLine()) {
                    String line = sc.nextLine().trim();
                    if (line.isEmpty() || line.startsWith("Tong:")) continue;
                    
                    String[] parts = line.split(",");
                    if (parts.length < 5) continue;
                    
                    String ngayLapStr = parts[3].trim();
                    double tongTien = Double.parseDouble(parts[4].trim());
                    
                    LocalDate ngayLap = LocalDate.parse(ngayLapStr);
                    if (ngayLap.getYear() == nam) {
                        int thang = ngayLap.getMonthValue();
                        int quy = (thang - 1) / 3;
                        tongThu[quy] += tongTien;
                    }
                }
                sc.close();
                break;
            } catch (Exception e) {
            }
        }

        // In bảng thống kê
        System.out.println("\n====================================================================================================");
        System.out.println("                    THONG KE DOANH THU VA LOI NHUAN THEO QUY NAM " + nam);
        System.out.println("====================================================================================================");
        System.out.printf("%-15s %18s %18s %18s %18s %18s\n", "", "Quy 1", "Quy 2", "Quy 3", "Quy 4", "TONG");
        System.out.println("----------------------------------------------------------------------------------------------------");

        // Tính tổng
        double tongThuTatCa = 0, tongChiTatCa = 0;
        for (int i = 0; i < 4; i++) {
            tongThuTatCa += tongThu[i];
            tongChiTatCa += tongChi[i];
        }

        // In dòng Tổng Thu
        System.out.printf("%-15s", "Tong Thu");
        for (int i = 0; i < 4; i++) {
            System.out.printf("%,18.0f", tongThu[i]);
        }
        System.out.printf("%,18.0f\n", tongThuTatCa);

        // In dòng Tổng Chi
        System.out.printf("%-15s", "Tong Chi");
        for (int i = 0; i < 4; i++) {
            System.out.printf("%,18.0f", tongChi[i]);
        }
        System.out.printf("%,18.0f\n", tongChiTatCa);

        // In dòng Lợi Nhuận
        System.out.printf("%-15s", "Loi Nhuan");
        for (int i = 0; i < 4; i++) {
            double loiNhuan = tongThu[i] - tongChi[i];
            System.out.printf("%,18.0f", loiNhuan);
        }
        double loiNhuanTong = tongThuTatCa - tongChiTatCa;
        System.out.printf("%,18.0f\n", loiNhuanTong);

        System.out.println("====================================================================================================\n");
    }
}