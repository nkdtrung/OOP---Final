package Java.DoAn.DS_Class;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import Java.DoAn.Class_chinh.ChiTietHoaDon;
import Java.DoAn.Class_chinh.HoaDon;

public class DanhSachHoaDon {
    private HoaDon[] dshd;
    private int n;

    Scanner sc = new Scanner(System.in);

    //Hàm thiết lập:
    public DanhSachHoaDon() {
        n = 0;
    }
    public DanhSachHoaDon(HoaDon[] dshd, int n) {
        this.dshd = dshd;
        this.n = n;
    }
    public DanhSachHoaDon(DanhSachHoaDon dshd) {
        this.n = dshd.n;
        this.dshd = new HoaDon[n];
        for (int i = 0; i < n; i++) {
            this.dshd[i] = new HoaDon(dshd.dshd[i]);
        }
    }

    //Nhập, xuất:

    public void xuat() {
        System.out.printf("%-10s %-10s %-10s %-15s %15s\n", "Ma HD", "Ma NV", "Ma KH", "Ngay Lap", "Tong Tien");
        for (int i = 0; i < n; i++) {
            dshd[i].xuat();
        }
        System.out.println("Tong: " + n + " hoa don");
    }

    public void DSHD() {
        n = 0;
        dshd = new HoaDon[0];
    }

    //Thêm:
    public void themHDFile() {  
        DanhSachCTHD dscthd = new DanhSachCTHD();
        dscthd.docFile("Java/DoAn/input/inputChiTietHD.txt");

        for (int i = 0; i < dscthd.size(); i++) {
            ChiTietHoaDon cthd = dscthd.getCTHD(i);
            String mahd = cthd.getMaHD();

            HoaDon hd = this.timHoaDonKhongXuat(mahd);
            if (hd != null) {
                double thanhtien = cthd.getThanhTien();
                hd.setTongTien(hd.getTongTien() + thanhtien);
            }
        }
        tuDongCapNhatFile();
    }
    public void themHoaDon() {
        Scanner sc = new Scanner(System.in);
        DanhSachCTHD dscthd = new DanhSachCTHD();
        dscthd.docFile("Java/DoAn/input/inputChiTietHD.txt");
        DanhSachSach dssach = new DanhSachSach();
        dssach.docFile("Java/DoAn/input/inputSach.txt");
         
        String mahd;
        do {
            System.out.print("Nhap ma hoa don: ");
            mahd = sc.nextLine();
            if (timKiemTheoMa(mahd) != null) {
                System.out.println("Ma hoa don da ton tai. Vui long nhap lai!");
            }
        } while (timKiemTheoMa(mahd) != null);
        
        n = dshd.length;
        dshd = Arrays.copyOf(dshd, n+1);
        dshd[n] = new HoaDon();
        dshd[n].setMaHD(mahd);
        System.out.println("Ma HD: " + mahd);
        dshd[n].nhap();
        
        // Nhập chi tiết hóa đơn
        System.out.print("Nhap so luong chi tiet hoa don: ");
        int soLuongCT = sc.nextInt();
        sc.nextLine();
        
        double tongTien = 0.0;
        for (int i = 0; i < soLuongCT; i++) {
            System.out.println("\n--- Chi tiet hoa don thu " + (i+1) + " ---");
            ChiTietHoaDon ct = new ChiTietHoaDon();
            ct.setMaHD(mahd);
            ct.nhap();
            
            double thanhtien = ct.getSoLuong() * ct.getDonGia();
            ct.setThanhTien(thanhtien);
            tongTien += thanhtien;
            
            dscthd.themChiTietHoaDon(ct, false);
        }
        dscthd.tuDongCapNhatFile();
        
        dshd[n].setTongTien(tongTien);
        n++;
        tuDongCapNhatFile();
        
        System.out.println("\nDa them hoa don thanh cong!");
        System.out.println("Tong tien hoa don: " + String.format("%.2f", tongTien) + " VND");
    }

    //Thêm
    public void themHoaDon(HoaDon hd) {
        if (timKiemTheoMa(hd.getMaHD()) != null) {
            System.out.println("Ma hoa don da ton tai. Khong the them!");
            return;
        }
        n = dshd.length;
        dshd = Arrays.copyOf(dshd, n+1);
        dshd[n] = new HoaDon(hd);
        n++;
    }

    //Tìm
    public HoaDon timKiemTheoMa (String ma) {
        for (int i = 0; i < n; i++ ){
            if (dshd[i].getMaHD().equals(ma)) {
                return dshd[i];
            }
        }
        return null;
    }
    
    
    // Tìm hóa đơn không xuất ra màn hình
    private HoaDon timHoaDonKhongXuat(String ma) {
        for (int i=0; i<n;i++) {
            if (dshd[i].getMaHD().equals(ma)) {
                return dshd[i];
            }
        }
        return null;
    }

    //Sửa
    public void suaHoaDon() {
        System.out.print("Nhap ma hoa don can sua");
        String ma = sc.nextLine();
        suaHoaDon(ma);
    }
    public void suaHoaDon(String ma) {
        for (int i=0;i<n;i++) {
            if (dshd[i].getMaHD().equals(ma)) {
                System.out.println("Chon thong tin can sua:");
                System.out.println("1. Ma hoa don");
                System.out.println("2. Ma nhan vien");
                System.out.println("3. Ma khach hang");
                System.out.println("4. Ngay lap");
                System.out.println("5. Tong tien");
                System.out.println("6. Sua chi tiet hoa don");
                System.out.print("Nhap lua chon: ");
                int choice = sc.nextInt();
                sc.nextLine();
                
                switch (choice) {
                    case 1:
                        System.out.print("Nhap ma hoa don moi: ");
                        String mahd = sc.nextLine();
                        dshd[i].setMaHD(mahd);
                        break;
                    case 2:
                        System.out.print("Nhap ma nhan vien moi: ");
                        String manv = sc.nextLine();
                        dshd[i].setMaNV(manv);
                        break;
                    case 3:
                        System.out.print("Nhap ma khach hang moi: ");
                        String makh = sc.nextLine();
                        dshd[i].setMaKH(makh);
                        break;
                    case 4:
                        System.out.print("Nhap ngay lap moi: ");
                        String ngaylap = sc.nextLine();
                        dshd[i].setNgayLap(ngaylap);
                        break;
                    case 5:
                        System.out.print("Nhap tong tien moi: ");
                        Double tongtien = sc.nextDouble();
                        dshd[i].setTongTien(tongtien);
                        break;
                    case 6:
                        // Sửa chi tiết hóa đơn
                        suaChiTietHoaDon(ma);
                        break;
                    default:
                        System.out.println("Lua chon khong hop le!");
                        return;
                }
                
                System.out.println("Da cap nhat thong tin hoa don thanh cong!");
                tuDongCapNhatFile();
                return;
            }
        }
        System.out.println("Khong tim thay hoa don co ma " + ma);
    }
    
    // Phương thức sửa chi tiết hóa đơn - thêm chi tiết mới vào hóa đơn
    private void suaChiTietHoaDon(String mahd) {
        // Đọc dữ liệu hiện có từ file trước
        DanhSachCTHD dscthd = new DanhSachCTHD();
        dscthd.docFile("Java/DoAn/input/inputChiTietHD.txt");
        DanhSachSach dssach = new DanhSachSach();
        dssach.docFile("Java/DoAn/input/inputSach.txt");
        
        System.out.println("\n=== THEM CHI TIET MOI VAO HOA DON ===");
        System.out.print("Nhap so luong chi tiet can them: ");
        int soLuongCT = sc.nextInt();
        sc.nextLine();
        
        double tongTienThem = 0.0;
        for (int i = 0; i < soLuongCT; i++) {
            System.out.println("\n--- Chi tiet thu " + (i+1) + " ---");
            System.out.print("Nhap ma sach: ");
            String masach = sc.nextLine();
            System.out.print("Nhap so luong: ");
            int soluong = sc.nextInt();
            System.out.print("Nhap don gia: ");
            double dongia = sc.nextDouble();
            sc.nextLine();
            
            double thanhtien = soluong * dongia;
            tongTienThem += thanhtien;
            
            // Tạo và thêm chi tiết hóa đơn
            ChiTietHoaDon ctMoi = new ChiTietHoaDon(mahd, masach, soluong, dongia, thanhtien);
            dscthd.themChiTietHoaDon(ctMoi, false);
        }
        dscthd.tuDongCapNhatFile();
        
        // Cập nhật tổng tiền hóa đơn
        HoaDon hd = timKiemTheoMa(mahd);
        hd.setTongTien(hd.getTongTien() + tongTienThem);
        tuDongCapNhatFile();
        
        System.out.println("\nDa them " + soLuongCT + " chi tiet thanh cong!");
        System.out.println("Tong tien them: " + String.format("%.2f", tongTienThem) + " VND");
        System.out.println("Tong tien hoa don sau khi cap nhat: " + String.format("%.2f", hd.getTongTien()) + " VND");
    }

    //Xóa
    public void xoaHoaDon() {
        System.out.print("Nhap ma hoa don can xoa");
        String ma = sc.nextLine();
        xoaHoaDon(ma);
    }
    public void xoaHoaDon(String ma) {
        for (int i=0;i<n;i++) {
            if (dshd[i].getMaHD().equals(ma)) {
                // Xóa tất cả chi tiết hóa đơn liên quan
                DanhSachCTHD dscthd = new DanhSachCTHD();
                dscthd.docFile("Java/DoAn/input/inputChiTietHD.txt");
                dscthd.xoaChiTietTheoMaHD(ma);
                
                // Xóa hóa đơn
                for (int j=i;j<n-1;j++) {
                    dshd[j] =dshd[j+1];
                }
                dshd = Arrays.copyOf(dshd, n-1);
                n--;
                System.out.println("Da xoa hoa don co ma " +  ma + " va cac chi tiet lien quan");
                tuDongCapNhatFile();
                return;
            }
        }
        System.out.println("Khong tim thay hoa don co ma " + ma);
    }

    //Thống kê:
    public void thongKeTongThu() {
        int namMin = 9999, namMax = 0;

        for (int i = 0; i < n; i++) {
            try{
                LocalDate ngaylap = LocalDate.parse(dshd[i].getNgayLap());
                int nam = ngaylap.getYear();
                if (nam < namMin) namMin = nam;
                if (nam > namMax) namMax = nam;
            } catch (Exception e) {}
        }

        
        // Tạo mảng thống kê
        int soNam = namMax - namMin +1;
        int[] nam = new int[soNam];
        double[] tongThu = new double[soNam];
        for (int i = 0; i < soNam; i++) {
            nam[i] = namMin + i;
            tongThu[i] = 0.0;
        }

        
        // Tính tổng thu theo năm
        for (int i = 0; i < n; i++) {
            try {
                LocalDate ngayLap = LocalDate.parse(dshd[i].getNgayLap());
                int namHD = ngayLap.getYear();
                int vitri = namHD - namMin;
                tongThu[vitri] = dshd[i].getTongTien();
            } catch (Exception e) {}
        }

        

            
        System.out.println("\n=== THONG KE TONG THU THEO NAM ===");
        System.out.printf("%-10s %20s\n", "Nam", "Tong Thu (VND)");
        System.out.println("-----------------------------------");
        for (int i = 0; i < soNam; i++) {
            if (tongThu[i] > 0) {
                System.out.printf("%-10d %,20.2f\n", nam[i], tongThu[i]);
            }
        }
    }
    

    // Thống kê chi tiêu theo khách hàng và năm
    public void thongKeChiTieuTheoKhachHangVaNam() {

        // Tìm năm min và max
        int namMax= 0, namMin = 9999;

        for(int i= 0; i < n; i++) {
            try {
                LocalDate ngay = LocalDate.parse(dshd[i].getNgayLap());
                int nam = ngay.getYear();
                if(nam < namMin) namMin = nam;
                if(nam > namMax) namMax = nam;
            }catch (Exception e) {}
        }

        int soNam = namMax - namMin +1;
        String[] maKH = new String [100];
        int soKH = 0;
        

        // Lấy danh sách mã khách hàng duy nhất
        for (int i = 0; i < n; i++) {
            boolean tontai = false;
            String ma = dshd[i].getMaKH();
            for (int j = 0; j < soKH; j++) {
                if (maKH[j].equals(ma)) {
                    tontai = true;
                    break;
                }
            }
            if (!tontai) {
                maKH[soKH++] = ma;
            }
        }

        

        // Tạo mảng 2 chiều lưu tổng tiền [khách hàng][năm]
        double tongTien[][] = new double[soKH][soNam];
        for (int i = 0; i < soKH; i++) {
            for (int j = 0; j < soNam; j++) {
                tongTien[i][j] = 0.0;
            }
        }
        

        // Tính tổng tiền cho từng khách hàng theo năm
        for (int i = 0; i < n; i++) {
            String ma = dshd[i].getMaKH();
            int viTriKH = -1;
            for (int j = 0; j < soKH; j++) {
                if (maKH[j].equals(ma)) {
                    viTriKH = j;
                    break;
                }
            }

        
            try {
                LocalDate ngaylap = LocalDate.parse(dshd[i].getNgayLap());
                int nam = ngaylap.getYear();
                int viTriNam = nam - namMin;
                tongTien[viTriKH][viTriNam] += dshd[i].getTongTien();
            } catch (Exception e) {
            }
        }

        
        
        

        

        // In tiêu đề
        System.out.println("\n========================================================================================================");
        System.out.println("                        THONG KE CHI TIEU THEO KHACH HANG VA NAM");
        System.out.println("========================================================================================================");

        System.out.printf("%-10s", "Ma KH");
        for (int i = 0; i < soNam; i++) {
            System.out.printf("%15d", namMin + i);
        }
        System.out.printf("%20s\n", "TONG");
        System.out.println("--------------------------------------------------------------------------------------------------------");

        for (int i = 0; i < soKH; i++) {
            System.out.printf("%-10s", maKH[i]);
            double tongKH = 0.0;

            for (int j = 0; j < soNam; j++) {
                System.out.printf("%,15.0f", tongTien[i][j]);
                tongKH += tongTien[i][j];
            }

            System.out.printf("%,20.0f\n", tongKH);
        }

        System.out.println("========================================================================================================\n");
    }

    // Thống kê hiệu suất nhân viên
    public void thongKeHieuSuatNhanVien() {

        // Lấy danh sách mã nhân viên duy nhất
        String[] maNV = new String[100]; // Giả sử tối đa 100 nhân viên
        int soNV = 0;

        for (int i = 0; i < n; i++) {
            String ma = dshd[i].getMaNV();
            boolean daTonTai = false;
            for (int j = 0; j < soNV; j++) {
                if (maNV[j].equals(ma)) {
                    daTonTai = true;
                    break;
                }
            }
            if (!daTonTai) {
                maNV[soNV++] = ma;
            }
        }

        // Sắp xếp mã nhân viên
        for (int i = 0; i < soNV - 1; i++) {
            for (int j = i + 1; j < soNV; j++) {
                if (maNV[i].compareTo(maNV[j]) > 0) {
                    String temp = maNV[i];
                    maNV[i] = maNV[j];
                    maNV[j] = temp;
                }
            }
        }

        // Tạo mảng lưu thông tin thống kê cho từng nhân viên
        int[] soLuongHD = new int[soNV];
        double[] tongTien = new double[soNV];
        double[] maxTien = new double[soNV];
        double[] minTien = new double[soNV];

        // Khởi tạo giá trị max và min
        for (int i = 0; i < soNV; i++) {
            soLuongHD[i] = 0;
            tongTien[i] = 0.0;
            maxTien[i] = 0.0;
            minTien[i] = Double.MAX_VALUE;
        }

        // Tính toán thống kê
        for (int i = 0; i < n; i++) {
            String ma = dshd[i].getMaNV();
            int viTriNV = -1;
            for (int j = 0; j < soNV; j++) {
                if (maNV[j].equals(ma)) {
                    viTriNV = j;
                    break;
                }
            }

            if (viTriNV != -1) {
                soLuongHD[viTriNV]++;
                tongTien[viTriNV] += dshd[i].getTongTien();
                
                if (dshd[i].getTongTien() > maxTien[viTriNV]) {
                    maxTien[viTriNV] = dshd[i].getTongTien();
                }
                if (dshd[i].getTongTien() < minTien[viTriNV]) {
                    minTien[viTriNV] = dshd[i].getTongTien();
                }
            }
        }

        // In bảng thống kê
        System.out.println("\n========================================================================================================");
        System.out.println("                            THONG KE HIEU SUAT NHAN VIEN");
        System.out.println("========================================================================================================");
        System.out.printf("%-15s", "");
        for (int i = 0; i < soNV; i++) {
            System.out.printf("%15s", maNV[i]);
        }
        System.out.println();
        System.out.println("--------------------------------------------------------------------------------------------------------");

        // In dòng Số lượng hóa đơn
        System.out.printf("%-15s", "So Luong HD");
        for (int i = 0; i < soNV; i++) {
            System.out.printf("%15d", soLuongHD[i]);
        }
        System.out.println();

        // In dòng Tổng tiền
        System.out.printf("%-15s", "Tong Tien");
        for (int i = 0; i < soNV; i++) {
            System.out.printf("%,15.0f", tongTien[i]);
        }
        System.out.println();

        // In dòng Max tiền
        System.out.printf("%-15s", "Max Tien");
        for (int i = 0; i < soNV; i++) {
            System.out.printf("%,15.0f", maxTien[i]);
        }
        System.out.println();

        // In dòng Min tiền
        System.out.printf("%-15s", "Min Tien");
        for (int i = 0; i < soNV; i++) {
            if (soLuongHD[i] > 0) {
                System.out.printf("%,15.0f", minTien[i]);
            } else {
                System.out.printf("%15s", "0");
            }
        }
        System.out.println();

        // In dòng Average (Trung bình)
        System.out.printf("%-15s", "Average");
        for (int i = 0; i < soNV; i++) {
            if (soLuongHD[i] > 0) {
                double average = tongTien[i] / soLuongHD[i];
                System.out.printf("%,15.0f", average);
            } else {
                System.out.printf("%15s", "0");
            }
        }
        System.out.println();

        System.out.println("========================================================================================================\n");
    }

    // Tính tổng tiền từ ngày a đến ngày b
    public double tinhTongTienTheoKhoangThoiGian(String tuNgay, String denNgay) {
        try {
            LocalDate ngayBatDau = LocalDate.parse(tuNgay);
            LocalDate ngayKetThuc = LocalDate.parse(denNgay);
            double tongTien = 0.0;
            int dem = 0;
            
            for (int i = 0; i < n; i++) {
                LocalDate ngayLap = LocalDate.parse(dshd[i].getNgayLap());
                if ((ngayLap.isEqual(ngayBatDau) || ngayLap.isAfter(ngayBatDau)) && 
                    (ngayLap.isEqual(ngayKetThuc) || ngayLap.isBefore(ngayKetThuc))) {
                    tongTien += dshd[i].getTongTien();
                    dem++;
                }
            }
            
            System.out.println("\n=== THONG KE DOANH THU ===");
            System.out.println("Tu ngay: " + tuNgay + " den ngay: " + denNgay);
            System.out.println("So hoa don: " + dem);
            System.out.println("Tong doanh thu: " + String.format("%.2f", tongTien) + " VND");
            return tongTien;
        } catch (Exception e) {
            System.out.println("Loi: Dinh dang ngay khong hop le. Vui long nhap theo dinh dang yyyy-MM-dd");
            return 0.0;
        }
    }

    //Đọc File
    public void docFile(String filePath) {
        this.dshd = new HoaDon[0];
        n = 0;
        DanhSachNV dsnv = new DanhSachNV();
        dsnv.docFile("Java/DoAn/input/inputNhanVien.txt");
        DanhSachKH dskh = new DanhSachKH();
        dskh.docTuFile();
        try (Scanner sc = new Scanner(new File(filePath))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty() || line.startsWith("Tong:")) continue;

                String[] parts = line.split(",");
                if (parts.length < 5) continue;

                String mahd = parts[0].trim();
                String manv = parts[1].trim();
                if (dsnv.timKiemTheoMa(manv) == null) {
                    continue;
                }
                String makh = parts[2].trim();
                if (dskh.timKiemTheoMa(makh) == null) {
                    continue;
                }
                String ngaylap = parts[3].trim();
                double tongtien = Double.parseDouble(parts[4].trim());

                HoaDon hd = new HoaDon(mahd, manv, makh, ngaylap, tongtien);
                themHoaDon(hd);

            }
        } catch (FileNotFoundException e) {
        } catch (Exception e) {
        }
    }

    public void tuDongCapNhatFile() {
        ghiFile();
    }

    public void ghiFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("Java/DoAn/input/inputHoaDon.txt"))) {
            for (int i=0;i<n;i++) {
                writer.println(dshd[i].getMaHD() + "," + 
                              dshd[i].getMaNV() + "," + 
                              dshd[i].getMaKH() + "," + 
                              dshd[i].getNgayLap() + "," + 
                              dshd[i].getTongTien());
            }
            writer.println("Tong: " + n);
        }
        catch (IOException e) {
            System.out.println("Loi ghi file: " + e.getMessage());
        }
    }

    public void xuatChiTietTheoMa(String maHD) {
        HoaDon hd = timKiemTheoMa(maHD);
        if (hd == null) {
            System.out.println("Khong tim thay hoa don voi ma: " + maHD);
            return;
        }

        DanhSachCTHD dscthd = new DanhSachCTHD();
        dscthd.docFile("Java/DoAn/input/inputChiTietHD.txt");

        System.out.println("\n========================================");
        System.out.println("THONG TIN HOA DON: " + maHD);
        System.out.println("========================================");
        hd.xuat();

        // Bước 4: In tiêu đề bảng chi tiết
        System.out.println("\n--- CHI TIET HOA DON ---");
        System.out.printf("%-10s %-15s %-10s %12s %12s\n", 
                         "Ma HD", "Ma Sach", "So Luong", "Don Gia", "Thanh Tien");
        System.out.println("--------------------------------------------------------------");

        // Bước 5: Duyệt qua tất cả chi tiết và chỉ in những dòng có mã trùng
        boolean coChiTiet = false; // Biến đánh dấu có chi tiết hay không
        for (int i = 0; i < dscthd.size(); i++) {
            ChiTietHoaDon ct = dscthd.getCTHD(i);
            // So sánh mã hóa đơn: nếu trùng thì in ra
            if (ct.getMaHD().equals(maHD)) {
                ct.xuat(); // Gọi phương thức xuat() của ChiTietHoaDon
                coChiTiet = true; // Đánh dấu đã có chi tiết
            }
        }

        // Bước 6: Thông báo nếu không có chi tiết nào
        if (!coChiTiet) {
            System.out.println("Khong co chi tiet nao cho hoa don nay.");
        }
        System.out.println("========================================\n");
    }
}
