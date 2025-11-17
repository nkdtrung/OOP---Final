package Java.DoAn.Class_chinh;

public class Kho {
    private String maKho;
    private String maSach;
    private int soLuong;
    private int mucTonToiThieu;
    private String viTri;

    public Kho() {}

    public Kho(String maKho, String maSach, int soLuong, int mucTonToiThieu, String viTri) {
        this.maKho = maKho;
        this.maSach = maSach;
        this.soLuong = soLuong;
        this.mucTonToiThieu = mucTonToiThieu;
        this.viTri = viTri;
    }

    public String getMaKho() { return maKho; }
    public void setMaKho(String maKho) { this.maKho = maKho; }

    public String getMaSach() { return maSach; }
    public void setMaSach(String maSach) { this.maSach = maSach; }

    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }

    public int getMucTonToiThieu() { return mucTonToiThieu; }
    public void setMucTonToiThieu(int mucTonToiThieu) { this.mucTonToiThieu = mucTonToiThieu; }

    public String getViTri() { return viTri; }
    public void setViTri(String viTri) { this.viTri = viTri; }

    public void tangTonKho(int soLuongThem) {
        this.soLuong += soLuongThem;
    }

    public void giamTonKho(int soLuongGiam) {
        this.soLuong -= soLuongGiam;
    }

    public boolean laHangTonThap() {
        return this.soLuong <= this.mucTonToiThieu;
    }
}