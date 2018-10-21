/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package complements;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author webdev
 */
public class Chemin {
    String myPath;
    
    public Chemin(){ 
         myPath = "/" + MyFen.class.getName().replace('.', '/') + ".class";
        URL url = getClass().getResource(myPath);
        try {
            myPath = URLDecoder.decode(url.toString(), "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MyFen.class.getName()).log(Level.SEVERE, null, ex);
        }
        
// suppression de  la classe ou du jar du path de l'url
    int index = myPath.lastIndexOf("/");
    myPath = myPath.substring(0, index);

    if (myPath.startsWith("jar:file:"))
    {
      // suppression de jar:file: de l'url d'un jar
      // ainsi que du path de la classe dans le jar
      index = myPath.indexOf("!");
      myPath = myPath.substring(9, index);
    }
    else
    {
      // suppresion du file: de l'url si c'est une classe en dehors d'un jar
      // et suppression du path du package si il est présent.
      myPath = myPath.substring(5, myPath.length());
      Package pack = getClass().getPackage();
      if (null != pack)
      {
        String packPath = pack.toString().replace('.', '/');
        if (myPath.endsWith(packPath))
        {
          myPath = myPath.substring(0, (myPath.length() - packPath.length()));
        }
      }
    }
}

    public String getMyPath() {
        return myPath;
    }
}
