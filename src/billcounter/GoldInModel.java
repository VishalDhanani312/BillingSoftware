/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billcounter;

/**
 *
 * @author Vishu
 */
public class GoldInModel {
    
    private String index,goldweight,touch,fineweight,totalfineweight;

    public GoldInModel(String index, String goldweight, String touch, String fineweight, String totalfineweight) {
        this.index = index;
        this.goldweight = goldweight;
        this.touch = touch;
        this.fineweight = fineweight;
        this.totalfineweight = totalfineweight;
    }

    public GoldInModel() {
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getGoldweight() {
        return goldweight;
    }

    public void setGoldweight(String goldweight) {
        this.goldweight = goldweight;
    }

    public String getTouch() {
        return touch;
    }

    public void setTouch(String touch) {
        this.touch = touch;
    }

    public String getFineweight() {
        return fineweight;
    }

    public void setFineweight(String fineweight) {
        this.fineweight = fineweight;
    }

    public String getTotalfineweight() {
        return totalfineweight;
    }

    public void setTotalfineweight(String totalfineweight) {
        this.totalfineweight = totalfineweight;
    }
    
    
    
}
