package Java.DoAn.Class_chinh;

public class SachThuong extends Sach {

    public SachThuong() {
        super();
    }
    public SachThuong(String masach, String tensach, String tacgia, String maTL, String maNXB, int soLuong, double donGia) {
        super(masach, tensach, tacgia, maTL, maNXB, soLuong, donGia);
    }
    public SachThuong(SachThuong s) {
        super(s);
    }

    @Override
    public String getLoaiSach() {
        return "Sách thường";
    }

    @Override
    public double tinhGiaBan() {
        return getDonGia();
    }

    @Override
    public boolean isAvailable() {
        return getSoLuong() > 0;
    }
}
