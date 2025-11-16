package Java.DoAn.DS_Class;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

import Java.DoAn.Class_chinh.Sach;
import Java.DoAn.Class_chinh.SachNuocNgoai;
import Java.DoAn.Class_chinh.SachThuong;
import Java.DoAn.Class_chinh.TapChi;

public class DanhSachSach {
    private Sach[] ds;
    private int n;

    //Hàm thiết lập:
    public DanhSachSach() {
        n = 0;
    }
    public DanhSachSach(Sach[] ds, int n) {
        this.ds = ds;
        this.n = n;
    }
    public DanhSachSach(DanhSachSach s) {
        this.n = s.n;
        this.ds = new Sach[n];
        for (int i = 0; i < n; i++) {
            this.ds[i] = s.ds[i];
        }
    }

    //Nhập, xuất:

    public void xuat() {
        // Đếm số lượng từng loại
        int soSachThuong = 0, soSachNN = 0, soTapChi = 0;
        for (int i = 0; i < n; i++) {
            if (ds[i] instanceof SachNuocNgoai) {
                soSachNN++;
            } else if (ds[i] instanceof TapChi) {
                soTapChi++;
            } else {
                soSachThuong++;
            }
        }
        
        // Xuất sách bình thường
        if (soSachThuong > 0) {
            System.out.println("\n========================================== SÁCH BÌNH THƯỜNG ==========================================");
            System.out.printf("%-10s %-40s %-20s %-10s %-10s %-10s %-10s\n", 
                "Mã sách", "Tên sách", "Tác giả", "Mã TL", "Mã NXB", "Số lượng", "Đơn giá");
            System.out.println("======================================================================================================");
            for (int i = 0; i < n; i++) {
                if (!(ds[i] instanceof SachNuocNgoai) && !(ds[i] instanceof TapChi)) {
                    ds[i].xuat();
                    System.out.println();
                }
            }
        }
        
        // Xuất sách nước ngoài
        if (soSachNN > 0) {
            System.out.println("\n===================================================== SÁCH NƯỚC NGOÀI =====================================================");
            System.out.printf("%-10s %-40s %-20s %-10s %-10s %-10s %-10s %-15s %-15s\n", 
                "Mã sách", "Tên sách", "Tác giả", "Mã TL", "Mã NXB", "Số lượng", "Đơn giá", "Ngôn ngữ", "Quốc gia");
            System.out.println("===========================================================================================================================");
            for (int i = 0; i < n; i++) {
                if (ds[i] instanceof SachNuocNgoai) {
                    ds[i].xuat();
                }
            }
        }
        
        // Xuất tạp chí
        if (soTapChi > 0) {
            System.out.println("\n===================================================== TẠP CHÍ ============================================================");
            System.out.printf("%-10s %-40s %-20s %-10s %-10s %-10s %-10s %-15s %-20s\n", 
                "Mã sách", "Tên sách", "Tác giả", "Mã TL", "Mã NXB", "Số lượng", "Đơn giá", "Số phát hành", "Chuyên mục");
            System.out.println("===========================================================================================================================");
            for (int i = 0; i < n; i++) {
                if (ds[i] instanceof TapChi) {
                    ds[i].xuat();
                }
            }
        }
        
        // Thông báo tổng kết
        System.out.println("\n===========================================================================================================================");
        System.out.println("Sách bình thường: " + soSachThuong + " | Sách nước ngoài: " + soSachNN + " | Tạp chí: " + soTapChi);
        System.out.println("===========================================================================================================================");
    }

    // Thêm:
    public void themSach() {
        Scanner sc = new Scanner(System.in);
        String masach;
        do {
            System.out.print("Nhap ma sach: ");
            masach = sc.nextLine();
            if (timKiemTheoMa(masach) != null) {
                System.out.println("Ma sach da ton tai. Vui long nhap lai!");
            }
        } while (timKiemTheoMa(masach) != null);
        
        n = ds.length;
        ds = Arrays.copyOf(ds, n + 1);
        System.out.println("Nhap sach binh thuong (1) hay sach nuoc ngoai (2) hay tap chi (3): ");
        int choice = sc.nextInt();
        sc.nextLine();
        if (choice == 1) {
            ds[n] = new SachThuong();
            ds[n].setMaSach(masach);
            System.out.println("Ma sach: " + masach);
            ds[n].nhap();
        } else if (choice == 2) {
            ds[n] = new SachNuocNgoai();
            ds[n].setMaSach(masach);
            System.out.println("Ma sach: " + masach);
            ds[n].nhap();
        } else if (choice == 3) {
            ds[n] = new TapChi();
            ds[n].setMaSach(masach);
            System.out.println("Ma sach: " + masach);
            ds[n].nhap();
        } else {
            System.out.println("Lua chon khong hop le. Mac dinh nhap sach binh thuong.");
            ds[n] = new SachThuong();
            ds[n].setMaSach(masach);
            System.out.println("Ma sach: " + masach);
            ds[n].nhap();
        }
        n++;
        tuDongCapNhatFile();
    }
    public void themSach(Sach sach) {
        if (timKiemTheoMa(sach.getMaSach()) != null) {
            System.out.println("Ma sach da ton tai. Khong the them!");
            return;
        }
        n = ds.length;
        ds = Arrays.copyOf(ds, n + 1);
        // Giữ nguyên kiểu của đối tượng (SachNuocNgoai hoặc TapChi)
        if (sach instanceof SachNuocNgoai) {
            ds[n] = new SachNuocNgoai((SachNuocNgoai)sach);
        } else if (sach instanceof TapChi) {
            ds[n] = new TapChi((TapChi)sach);
        } else {
            ds[n] = new SachThuong((SachThuong)sach);
        }
        n++;
    }

    //Tìm:
    public Sach timKiemTheoMa(String ma) {
        for (int i = 0; i < n; i++) {
            if (ds[i].getMaSach().equalsIgnoreCase(ma)) {
                return ds[i];
            }
        }
        return null;
    }

    //Xóa:
    public void xoaSach() {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        System.out.print("Nhap ma sach can xoa: ");
        String ma = sc.nextLine();
        xoaSach(ma);
    }
    public void xoaSach(String ma) {
        for (int i = 0; i < n; i++) {
            if (ds[i].getMaSach().equals(ma)) {
                for (int j = i; j < n - 1; j++) {
                    ds[j] = ds[j + 1];
                }
                ds = Arrays.copyOf(ds, n-1);
                n--;
                System.out.println("Da xoa sach co ma " + ma);
                tuDongCapNhatFile();
                return;
            }
        }
        System.out.println("Khong tim thay sach co ma " + ma);
    }

    //Sửa:
    public void suaSach() {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        System.out.print("Nhap ma sach can sua: ");
        String ma = sc.nextLine();
        suaSach(ma);
    }
    public void suaSach(String masach) {
        for (int i = 0; i < n; i++) {
            if (ds[i].getMaSach().equals(masach)) {               
                System.out.println("Chon thong tin can sua:");
                System.out.println("1. Ten sach");
                System.out.println("2. Tac gia");
                System.out.println("3. Ma the loai");
                System.out.println("4. Ma nha xuat ban");
                System.out.println("5. So luong");
                System.out.println("6. Don gia");
                if (ds[i] instanceof SachNuocNgoai) {
                    System.out.println("7. Quoc gia");
                    System.out.println("8. Ngon ngu");
                } else if (ds[i] instanceof TapChi) {
                    System.out.println("7. So phat hanh");
                    System.out.println("8. Chuyen muc");
                }
                System.out.print("Nhap lua chon: ");
                java.util.Scanner sc = new java.util.Scanner(System.in);
                int choice = sc.nextInt();
                sc.nextLine(); // Xóa buffer
                
                switch (choice) {
                    case 1:
                        System.out.print("Nhap ten sach moi: ");
                        String tensach = sc.nextLine();
                        ds[i].setTenSach(tensach);
                        break;
                    case 2:
                        System.out.print("Nhap ma tac gia moi: ");
                        String matg = sc.nextLine();
                        ds[i].setMaTG(matg);
                        break;
                    case 3:
                        System.out.print("Nhap ma the loai moi: ");
                        String maTL = sc.nextLine();
                        ds[i].setMaTL(maTL);
                        break;
                    case 4:
                        System.out.print("Nhap ma nha xuat ban moi: ");
                        String maNXB = sc.nextLine();
                        ds[i].setMaNXB(maNXB);
                        break;
                    case 5:
                        System.out.print("Nhap so luong moi: ");
                        int soLuong = sc.nextInt();
                        ds[i].setSoLuong(soLuong);
                        break;
                    case 6:
                        System.out.print("Nhap don gia moi: ");
                        double donGia = sc.nextDouble();
                        ds[i].setDonGia(donGia);
                        break;
                    case 7:
                        if (ds[i] instanceof SachNuocNgoai) {
                            System.out.print("Nhap quoc gia moi: ");
                            String quocgia = sc.nextLine();
                            ((SachNuocNgoai) ds[i]).setQuocGia(quocgia);
                        } else if (ds[i] instanceof TapChi) {
                            System.out.print("Nhap so phat hanh moi: ");
                            int sophathanh = sc.nextInt();
                            ((TapChi) ds[i]).setSoPhatHanh(sophathanh);
                        }
                        break;
                    case 8:
                        if (ds[i] instanceof SachNuocNgoai) {
                            System.out.print("Nhap ngon ngu moi: ");
                            String ngonngu = sc.nextLine();
                            ((SachNuocNgoai) ds[i]).setNgonNgu(ngonngu);
                        } else if (ds[i] instanceof TapChi) {
                            System.out.print("Nhap chuyen muc moi: ");
                            String chuyenmuc = sc.nextLine();
                            ((TapChi) ds[i]).setChuyenMuc(chuyenmuc);
                        }
                        break;
                    default:
                        System.out.println("Lua chon khong hop le!");
                        return;
                }
                
                System.out.println("Da cap nhat thong tin sach thanh cong!");
                tuDongCapNhatFile();
                return;
            }
        }
        System.out.println("Khong tim thay sach co ma " + masach);  
    }

    //Cập nhật số lượng:
    public void capNhatSL(String masach, int soluong) {
        for (int i=0;i<n;i++) {
            if (ds[i].getMaSach().equals(masach)) {
                int slmoi = ds[i].getSoLuong() + soluong;
                ds[i].setSoLuong(slmoi);
                return;
            }
        }
    }

    //Thống kê:
    public void thongKeTheLoai() {
        // Tạo mảng để lưu các thể loại khác nhau
        String[] maTheLoai = new String[n];
        int[] soLuong = new int[n];
        int soTheLoai = 0;
        
        // Thống kê số lượng theo thể loại
        for (int i = 0; i < n; i++) {
            String tl = ds[i].getMaTL();
            boolean timThay = false;
            
            // Kiểm tra thể loại đã tồn tại chưa
            for (int j = 0; j < soTheLoai; j++) {
                if (maTheLoai[j].equals(tl)) {
                    soLuong[j]++;
                    timThay = true;
                    break;
                }
            }
            
            // Nếu chưa có thì thêm mới
            if (!timThay) {
                maTheLoai[soTheLoai] = tl;
                soLuong[soTheLoai] = 1;
                soTheLoai++;
            }
        }
        
        // Hiển thị kết quả
        System.out.println("\n=== THONG KE SACH THEO THE LOAI ===");
        System.out.printf("%-15s %15s\n", "Ma The Loai", "So Luong");
        System.out.println("-----------------------------------");
        for (int i = 0; i < soTheLoai; i++) {
            System.out.printf("%-15s %15d quyen\n", maTheLoai[i], soLuong[i]);
        }
        System.out.println("\nTong cong: " + n + " quyen sach");
    }

    //Đoc file:
    public void docFile(String filePath) {
        ds = new Sach[0];
        n = 0;
        
        // Đọc cả 3 file: inputSach.txt, inputSachNuocNgoai.txt và inputTapChi.txt
        docFileHelper("Java/DoAn/input/inputSach.txt");
        docFileHelper("Java/DoAn/input/inputSachNuocNgoai.txt");
        docFileHelper("Java/DoAn/input/inputTapChi.txt");
    }
    
    private void docFileHelper(String filePath) {
        DanhSachNXB dsnxb = new DanhSachNXB();
        dsnxb.docFile("Java/DoAn/input/inputNhaXuatBan.txt");
        try (Scanner sc = new Scanner(new java.io.File(filePath))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty() || line.startsWith("Tong:")) continue;

                String[] parts = line.split(",");
                if (parts.length < 7) continue;

                String masach = parts[0].trim();
                String tensach = parts[1].trim();
                String matg = parts[2].trim();
                String matl = parts[3].trim();
                String manxb = parts[4].trim();
                
                // Validate mã NXB
                if (dsnxb.timNXBKhongXuat(manxb) == null) {
                    // System.out.println("Ma nha xuat ban " + manxb + " khong ton tai. Bo qua sach " + masach);
                    continue;
                }
                
                int soLuong = Integer.parseInt(parts[5].trim());
                double donGia = Double.parseDouble(parts[6].trim());
                Sach s;

                // Kiểm tra xem có loại sách hay không
                if (parts.length == 7) { 
                    s = new SachThuong(masach, tensach, matg, matl, manxb, soLuong, donGia);
                } 
                else if (parts[7].trim().equalsIgnoreCase("SachNN")) {
                    String ngonNgu = parts[8].trim();
                    String quocGia = parts[9].trim();
                    s = new SachNuocNgoai(masach, tensach, matg, matl, manxb, soLuong, donGia, ngonNgu, quocGia);
                } 
                else if (parts[7].trim().equalsIgnoreCase("TapChi")) {
                    int soPhatHanh = Integer.parseInt(parts[8].trim());
                    String chuyenMuc = parts[9].trim();
                    s = new TapChi(masach, tensach, matg, matl, manxb, soLuong, donGia, soPhatHanh, chuyenMuc);
                } 
                else {
                    continue; // bỏ dòng lỗi
                }
                themSach(s);
            }
            // System.out.println("Da doc file " + filePath);
        } catch (java.io.FileNotFoundException e) {
            // System.out.println("Khong tim thay file: " + e.getMessage());
        } catch (Exception e) {
            // System.out.println("Loi doc file: " + e.getMessage());
        }
    }

    //Ghi File:
    // Tự động cập nhật file sau khi thay đổi dữ liệu
    public void tuDongCapNhatFile() {
        ghiFile();
    }

    public void ghiFile() {
        // Ghi sách bình thường vào inputSach.txt
        try (PrintWriter writer = new PrintWriter(new FileWriter("Java/DoAn/input/inputSach.txt", false))) {
            int count = 0;
            for (int i=0;i<n;i++) {
                if (ds[i] instanceof SachNuocNgoai || ds[i] instanceof TapChi) continue; // Bỏ qua sách NN và tạp chí
                
                writer.print(ds[i].getMaSach());
                writer.print("," + ds[i].getTenSach());
                writer.print("," + ds[i].getMaTG());
                writer.print("," + ds[i].getMaTL());
                writer.print("," + ds[i].getMaNXB());
                writer.print("," + ds[i].getSoLuong());
                writer.print("," + ds[i].getDonGia());
                writer.println();
                count++;
            }
            writer.println("Tong: " + count);
        }
        catch (IOException e) {}
        
        // Ghi sách nước ngoài vào inputSachNuocNgoai.txt
        try (PrintWriter writer = new PrintWriter(new FileWriter("Java/DoAn/input/inputSachNuocNgoai.txt", false))) {
            int count = 0;
            for (int i=0;i<n;i++) {
                if (!(ds[i] instanceof SachNuocNgoai)) continue; // Chỉ lấy sách nước ngoài
                
                writer.print(ds[i].getMaSach());
                writer.print("," + ds[i].getTenSach());
                writer.print("," + ds[i].getMaTG());
                writer.print("," + ds[i].getMaTL());
                writer.print("," + ds[i].getMaNXB());
                writer.print("," + ds[i].getSoLuong());
                writer.print("," + ds[i].getDonGia());
                writer.print(",SachNN");
                writer.print("," + ((SachNuocNgoai)ds[i]).getNgonNgu());
                writer.print("," + ((SachNuocNgoai)ds[i]).getQuocGia());
                writer.println();
                count++;
            }
            writer.println("Tong: " + count);
        }
        catch (IOException e) {}
        
        // Ghi tạp chí vào inputTapChi.txt
        try (PrintWriter writer = new PrintWriter(new FileWriter("Java/DoAn/input/inputTapChi.txt", false))) {
            int count = 0;
            for (int i=0;i<n;i++) {
                if (!(ds[i] instanceof TapChi)) continue; // Chỉ lấy tạp chí
                
                writer.print(ds[i].getMaSach());
                writer.print("," + ds[i].getTenSach());
                writer.print("," + ds[i].getMaTG());
                writer.print("," + ds[i].getMaTL());
                writer.print("," + ds[i].getMaNXB());
                writer.print("," + ds[i].getSoLuong());
                writer.print("," + ds[i].getDonGia());
                writer.print(",TapChi");
                writer.print("," + ((TapChi)ds[i]).getSoPhatHanh());
                writer.print("," + ((TapChi)ds[i]).getChuyenMuc());
                writer.println();
                count++;
            }
            writer.println("Tong: " + count);
        }
        catch (IOException e) {}
    }
}
                

