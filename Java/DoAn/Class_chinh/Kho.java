package Java.DoAn.Class_chinh;

import java.util.Objects;

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
    public void setSoLuong(int soLuong) {
        if (soLuong < 0) {
            throw new IllegalArgumentException("So luong khong the am");
        }
        this.soLuong = soLuong;
    }

    public int getMucTonToiThieu() { return mucTonToiThieu; }
    public void setMucTonToiThieu(int mucTonToiThieu) { this.mucTonToiThieu = mucTonToiThieu; }

    public String getViTri() { return viTri; }
    public void setViTri(String viTri) { this.viTri = viTri; }

    public void tangTonKho(int soLuongThem) {
        if (soLuongThem <= 0) {
            throw new IllegalArgumentException("So luong them vao phai la so duong");
        }
        this.soLuong += soLuongThem;
    }

    public void giamTonKho(int soLuongGiam) {
        if (soLuongGiam <= 0) {
            throw new IllegalArgumentException("So luong giam phai la so duong");
        }
        if (soLuongGiam > this.soLuong) {
            throw new IllegalStateException("Khong du so luong ton kho de giam. Ton kho hien tai: " + this.soLuong);
        }
        this.soLuong -= soLuongGiam;
    }

    public boolean laHangTonThap() {
        return this.soLuong <= this.mucTonToiThieu;
    }

    @Override
    public String toString() {
        return "Kho{" +
                "maKho='" + maKho + '\'' +
                ", maSach='" + maSach + '\'' +
                ", soLuong=" + soLuong +
                ", mucTonToiThieu=" + mucTonToiThieu +
                ", viTri='" + viTri + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Kho)) return false;
        Kho kho = (Kho) o;
        return Objects.equals(maKho, kho.maKho);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maKho);
    }
}