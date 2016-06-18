package com.lqc.designsupportlibrarydemo.app.customwidget.richeditor.CustomSpans;

/**
 * Created by albert on 16-6-17.
 */
public class LqcPart {
    private int start;
    private int end;

    public LqcPart(int start, int end){
        this.start = start;
        this.end = end;
    }

    public int getStart(){
        return start;
    }
    public int getEnd(){
        return end;
    }
    public boolean isValid(){
        return start<end;
    }
}
