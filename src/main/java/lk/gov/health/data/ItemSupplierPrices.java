/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.gov.health.data;

import lk.gov.health.entity.Institution;
import lk.gov.health.entity.Item;
import lk.gov.health.entity.pharmacy.Amp;
import lk.gov.health.entity.pharmacy.Vmp;

/**
 *
 * @author Niluka
 */
public class ItemSupplierPrices {
    Item item;
    Amp amp;
    Vmp vmp;
    Institution supplier;
    double pp;
    double sp;
    double wp;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Amp getAmp() {
        return amp;
    }

    public void setAmp(Amp amp) {
        this.amp = amp;
    }

    public Vmp getVmp() {
        return vmp;
    }

    public void setVmp(Vmp vmp) {
        this.vmp = vmp;
    }

    public Institution getSupplier() {
        return supplier;
    }

    public void setSupplier(Institution supplier) {
        this.supplier = supplier;
    }

    public double getPp() {
        return pp;
    }

    public void setPp(double pp) {
        this.pp = pp;
    }

    public double getSp() {
        return sp;
    }

    public void setSp(double sp) {
        this.sp = sp;
    }

    public double getWp() {
        return wp;
    }

    public void setWp(double wp) {
        this.wp = wp;
    }
    
    
    
    
}
