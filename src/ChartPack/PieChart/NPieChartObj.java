/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChartPack.PieChart;

import java.awt.Color;

/**
 *
 * @author 18074751
 */
public class NPieChartObj { // I miss C++
    public String extension;
    public Long extensionTotalLength;
    public Color segmentColour;

    public NPieChartObj(String extension, Long extensionTotalLength, Color color){
        this.extension = extension;
        this.extensionTotalLength = extensionTotalLength;
        this.segmentColour = color;
    }
}

