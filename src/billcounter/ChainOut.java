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
public class ChainOut {
    
    private int index;
    private String chainname,touch,chainweight,ytouch,fweight;

    public ChainOut(int index, String chainname, String touch, String chainweight, String ytouch, String fweight) {
        this.index = index;
        this.chainname = chainname;
        this.touch = touch;
        this.chainweight = chainweight;
        this.ytouch = ytouch;
        this.fweight = fweight;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getChainname() {
        return chainname;
    }

    public void setChainname(String chainname) {
        this.chainname = chainname;
    }

    public String getTouch() {
        return touch;
    }

    public void setTouch(String touch) {
        this.touch = touch;
    }

    public String getChainweight() {
        return chainweight;
    }

    public void setChainweight(String chainweight) {
        this.chainweight = chainweight;
    }

    public String getYtouch() {
        return ytouch;
    }

    public void setYtouch(String ytouch) {
        this.ytouch = ytouch;
    }

    public String getFweight() {
        return fweight;
    }

    public void setFweight(String fweight) {
        this.fweight = fweight;
    }
    
    
    
    
    
    
    
    
}
