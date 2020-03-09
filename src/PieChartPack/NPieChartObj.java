/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PieChartPack;

import java.awt.Color;

/**
 *
 * @author 18074751
 */
public class NPieChartObj { // I miss C++
    public String extension; // The file extension of the file
    public Long extensionTotalLength; // The sum of the extension sizes
    public Color segmentColour; // Colour of the segment of the chart

    public NPieChartObj(String extension, Long extensionTotalLength, Color color){
        this.extension = extension;
        this.extensionTotalLength = extensionTotalLength;
        this.segmentColour = color;
    }
}