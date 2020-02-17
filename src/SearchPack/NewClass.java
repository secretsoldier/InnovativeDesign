/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SearchPack;

import java.io.File;

/**
 *
 * @author 18074751
 */
public class NewClass {
    public static void main(String[] args) {
        FileIterator iFile = new FileIterator(new File("/H:/"), true);
        while(iFile.hasNext()){
            System.out.printf("%s\n", iFile.next().getName());
        }
    }
}
