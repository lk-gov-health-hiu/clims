/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.gov.health.ws.lims;

/**
 *
 * @author ariyaratne_mhb2017
 */
public class Barcode {

    private String insid;
    private String tests;
    private String name;
    private Long barcode;

    public String getInsid() {
        return insid;
    }

    public void setInsid(String insid) {
        this.insid = insid;
    }

    public String getTests() {
        return tests;
    }

    public void setTests(String tests) {
        this.tests = tests;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBarcode() {
        return barcode;
    }

    public void setBarcode(Long barcode) {
        this.barcode = barcode;
    }

}
