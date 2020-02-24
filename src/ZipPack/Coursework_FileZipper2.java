/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ZipPack;
import java.io.*;
import java.nio.file.*;
import java.util.zip.*;
import java.nio.file.attribute.*;
/**
 *
 * @author 17009936
 */
public class Coursework_FileZipper2 extends SimpleFileVisitor<Path>{

    private static ZipOutputStream zos;
 
    private Path sourceDir;
 
    public Coursework_FileZipper2(Path sourceDir) {
        this.sourceDir = sourceDir;
    }
 
    @Override
    public FileVisitResult visitFile(Path file,
            BasicFileAttributes attributes) {
 
        try {
            Path targetFile = sourceDir.relativize(file);
 
            zos.putNextEntry(new ZipEntry(targetFile.toString()));
 
            byte[] bytes = Files.readAllBytes(file);
            zos.write(bytes, 0, bytes.length);
            zos.closeEntry();
 
        } catch (IOException ex) {
            System.err.println(ex);
        }
 
        return FileVisitResult.CONTINUE;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String dirPath = "\\\\brookesf1\\s36\\17009936\\File_Movement_Test_Coursework\\ZipMe2";
        Path sourceDir = Paths.get(dirPath);
 
        try {
            String zipFileName = dirPath.concat(".zip");
            zos = new ZipOutputStream(new FileOutputStream(zipFileName));
 
            Files.walkFileTree(sourceDir, new Coursework_FileZipper2(sourceDir));
 
            zos.close();
        } catch (IOException ex) {
            System.err.println("I/O Error: " + ex);
        }
        // TODO code application logic here
    }
    
}
