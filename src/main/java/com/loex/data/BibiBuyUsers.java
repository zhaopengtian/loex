package com.loex.data;


public class BibiBuyUsers {

    private String type;
    private String side;
    private String price;
    private String volume;
    private String symbol;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BibiBuyUsers(String type, String side, String price, String volume, String symbol) {
        super();
        this.type = type;
        this.side = side;
        this.price = price;
        this.volume = volume;
        this.symbol = symbol;
    }

    public BibiBuyUsers() {
        super();
    }

//    public int getType() {
//        return type;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getJob() {
//        return job;
//    }
//
//    public void setJob(String job) {
//        this.job = job;
//    }

}
