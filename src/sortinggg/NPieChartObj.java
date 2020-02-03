package sortinggg;

import java.awt.*;

public class NPieChartObj {
    private String extention;
    private Long extentionTotalLength;
    private Color segmentColour;

    public NPieChartObj(String extention, Long extentionTotalLength){
        this.extention = extention;
        this.extentionTotalLength = extentionTotalLength;
        this.segmentColour = segmentColour;
    }

    public String getExtention(){
        return this.extention;
    }
    public void setExtention(String extention){
        this.extention = extention;
    }

    public Long getExtentionTotalLength(){
        return this.extentionTotalLength;
    }
    public NPieChartObj setExtentionTotalLength(Long extentionTotalLength){
        this.extentionTotalLength = extentionTotalLength;
        return this;
    }

    public Color getSegmentColour() {
        return segmentColour;
    }
    public NPieChartObj setSegmentColour(Color segmentColour){
        this.segmentColour = segmentColour;
        return this;
    }
}
