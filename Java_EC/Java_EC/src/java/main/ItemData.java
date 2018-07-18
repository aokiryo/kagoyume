/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.Serializable;
import java.net.URL;

/**
 *
 * @author ryo
 */
public class ItemData implements Serializable {

    private String name;
    private String id;    
    private int price;
    private URL image;
    private String about;
    private double rate;

    public ItemData() {
        this.name = "";
        this.id = "";
        this.price = 0;
        this.image = null;
        this.about = "";
        this.rate = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
            this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
            this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public URL getImage() {
        return image;
    }

    public void setImage(URL image) {
        this.image = image;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
            this.about = about;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
            this.rate = rate;
    }

}
