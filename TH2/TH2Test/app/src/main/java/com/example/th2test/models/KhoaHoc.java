package com.example.th2test.models;

public class KhoaHoc {
    private int id;
    private String name, publishDate, publisher, price;
    private boolean cb_coop;

    public KhoaHoc() {
    }

    public KhoaHoc(int id, String name, String publishDate, String publisher, String price, boolean cb_coop) {
        this.id = id;
        this.name = name;
        this.publishDate = publishDate;
        this.publisher = publisher;
        this.price = price;
        this.cb_coop = cb_coop;
    }

    public KhoaHoc(String name, String publishDate, String publisher, String price, boolean cb_coop) {
        this.name = name;
        this.publishDate = publishDate;
        this.publisher = publisher;
        this.price = price;
        this.cb_coop = cb_coop;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isCb_coop() {
        return cb_coop;
    }

    public void setCb_coop(boolean cb_coop) {
        this.cb_coop = cb_coop;
    }
}
