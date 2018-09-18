/*
 * Sakurasou Digital Studio
 * Each line should be prefixed with  * 
 */
package Model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author MashiroShina
 */
public class Config {
    Properties prop = new Properties();
    Properties propOut = new Properties();
    FileInputStream inputStream;
    FileOutputStream outputStream;
    String fileName = "config.properties";
    public Config() {
        try{
        
        inputStream=new FileInputStream(System.getProperty("user.dir")+"\\"+fileName);
        //outputStream=new FileOutputStream(fileName);
        if(inputStream!=null){
            prop.load(inputStream);
        }else{
            throw new FileNotFoundException("Property File "+fileName+" Tidak di temukan");
        }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error :"+e);
        }
    }
    public String getNamaLaundry(){
        return prop.getProperty("Nama_Laundry");
    }
    public void setNamaLaundry(String value){ 
        prop.setProperty("Nama_Laundry", value); 
    }
    public String getAlamatLaundry(){
        return prop.getProperty("Alamat_Laundry");
    }
    public void setAlamatLaundry(String value){
        prop.setProperty("Alamat_Laundry", value);
    }
    public String getKontakLaundry(){
        return prop.getProperty("Kontak_Laundry");
    }
    public void setKontakLaundry(String value){
        prop.setProperty("Kontak_Laundry", value);
    }
    public String getJenisDB(){
         return prop.getProperty("Jenis_DB");
    }
    public void setJenisDB(String value){
        prop.setProperty("Jenis_DB", value);
    }
    public String getAlamatIP(){
         return prop.getProperty("Alamat_IP_DB");
    }
    public void setAlamatIP(String value){
        prop.setProperty("Alamat_IP_DB", value);
    }
    public String getNamaDB(){
         return prop.getProperty("Nama_DB");
    }
    public void setNamaDB(String value){
        prop.setProperty("Nama_DB", value);
    }
    public String getUsername(){
         return prop.getProperty("Username");
    }
    public void setUsername(String value){
        prop.setProperty("Username", value);
    }
    public String getPassword(){
         return prop.getProperty("Password");
    }
    public void setPassword(String value){
        prop.setProperty("Password", value);
    }
    void save(){
        try {
            prop.store(new FileOutputStream(fileName), "Data Laundry");
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
