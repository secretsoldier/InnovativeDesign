package innovativedesign;

public class Resources {
    static private ClassLoader classLoader = Resources.class.getClassLoader();
    public static java.net.URL getResourceURL(String filename){
        java.net.URL resource = classLoader.getResource(filename);
        if (resource == null){
            throw new IllegalArgumentException(String.format("Resource \"%s\" is not found.", filename));
        } else {
            return resource;
        }
    }
    public static javax.swing.ImageIcon getIcon(String filename){
        return new javax.swing.ImageIcon(getResourceURL(filename));
    }
    public static java.io.File getFile(String filename){
        return new java.io.File(getResourceURL(filename).getFile());
    }
}
