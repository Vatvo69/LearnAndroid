package com.example.th1_final.model;

public class MayBay {
    public static String VIP = "VIP", PHOTHONG = "PHOTHUONG", GIARE="GIARE";
    String maVe,loaiVe,ngayBay;
    boolean checkBox;

    public MayBay(String maVe, String loaiVe, String ngayBay, boolean checkBox) {
        this.maVe = maVe;
        this.loaiVe = loaiVe;
        this.ngayBay = ngayBay;
        this.checkBox = checkBox;
    }

    public String getMaVe() {
        return maVe;
    }

    public void setMaVe(String maVe) {
        this.maVe = maVe;
    }

    public String getLoaiVe() {
        return loaiVe;
    }

    public void setLoaiVe(String loaiVe) {
        this.loaiVe = loaiVe;
    }

    public String getNgayBay() {
        return ngayBay;
    }

    public void setNgayBay(String ngayBay) {
        this.ngayBay = ngayBay;
    }

    public boolean isCheckBox() {
        return checkBox;
    }

    public void setCheckBox(boolean checkBox) {
        this.checkBox = checkBox;
    }
}
