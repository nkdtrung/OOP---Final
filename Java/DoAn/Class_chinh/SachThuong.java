package Java.DoAn.Class_chinh;

public class SachThuong extends Sach {
    // Thuộc tính: Không có thuộc tính bổ sung

    // Phương thức:

    //Hàm thiết lập:
    public SachThuong() {
        super();
    }
    public SachThuong(String masach, String tensach, String tacgia, String maTL, String maNXB, int soLuong, double donGia) {
        super(masach, tensach, tacgia, maTL, maNXB, soLuong, donGia);
    }
    public SachThuong(SachThuong s) {
        super(s);
    }

    // Implement abstract methods:
    @Override
    public String getLoaiSach() {
        return "Sách thường";
    }

    @Override
    public double tinhGiaBan() {
        // Sách thường giá bán bằng đơn giá
        return getDonGia();
    }

    @Override
    public boolean isAvailable() {
        return getSoLuong() > 0;
    }
}
