/*
 * Sakurasou Digital Studio
 * Each line should be prefixed with  * 
 */
package Model;
import java.io.File;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;
//import net.sf.jasperreports.view.JRViewer;

/**
 *
 * @author REM
 */
public class PDF {
    String path;
    Connection koneksi;

    public PDF() {
        path = System.getProperty("user.dir") + "\\src\\PDF\\";
        koneksi = new Koneksi().getMysqlConnection();
    }
    
    public void buatNota(int NoFaktur,Double Diskon,Double Tambahan,String nama,String alamat,String kontak){
        String Nota = path+"Nota.jrxml";
        HashMap parameter = new HashMap();
        try {
            File fileNota = new File("src/PDF/Nota.jrxml");
            fileNota.getPath();
            System.out.println(path);
            JasperDesign DesignNota = JRXmlLoader.load(fileNota);
            //parameter.clear();
            parameter.put("Parameter_No_Faktur", NoFaktur);
            parameter.put("Parameter_Diskon",Diskon);
            parameter.put("Parameter_Biaya_Tambahan",Tambahan);
            parameter.put("Parameter_Nama_Laundry",nama);
            parameter.put("Parameter_Alamat_Laundry",alamat);
            parameter.put("Parameter_Kontak_Laundry",kontak);
             
            parameter.put("SUBREPORT_DIR",path);
            JasperReport RNota = JasperCompileManager.compileReport(DesignNota);
            
            JasperPrint jPrint = JasperFillManager.fillReport(RNota, parameter, koneksi);
            JRViewer viewer = new JRViewer(jPrint);
            JasperViewer.viewReport(jPrint,false);
        } catch (JRException ex) {
            Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void buatLaporan(String tgl_mulai,String tgl_selesai,String nama,String alamat,String kontak){
        String Nota = path+"Nota.jrxml";
        HashMap parameter = new HashMap();
        try {
            File fileNota = new File("src/PDF/Laporan.jrxml");
            fileNota.getPath();
            //System.out.println(path);
            JasperDesign DesignNota = JRXmlLoader.load(fileNota);
           
            parameter.clear();
            parameter.put("NAMA_LAUNDRY",nama);
            parameter.put("SUB",alamat+","+kontak);
            parameter.put("TGL_MULAI",tgl_mulai);
            parameter.put("TGL_SELESAI",tgl_selesai);
            JasperReport RNota = JasperCompileManager.compileReport(DesignNota);
            
            JasperPrint jPrint = JasperFillManager.fillReport(RNota, parameter, koneksi);
            JRViewer viewer = new JRViewer(jPrint);
            JasperViewer.viewReport(jPrint,false);
        } catch (JRException ex) {
            Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void buatLaporanHarian(String tgl_mulai,String nama,String alamat,String kontak){
        String Nota = path+"Nota.jrxml";
        HashMap parameter = new HashMap();
        try {
            File fileNota = new File("src/PDF/Laporan_Harian.jrxml");
            fileNota.getPath();
            //System.out.println(path);
            JasperDesign DesignNota = JRXmlLoader.load(fileNota);
           
            parameter.clear();
            parameter.put("NAMA_LAUNDRY",nama);
            parameter.put("SUB",alamat+","+kontak);
            parameter.put("TGL_MULAI",tgl_mulai);
            JasperReport RNota = JasperCompileManager.compileReport(DesignNota);
            
            JasperPrint jPrint = JasperFillManager.fillReport(RNota, parameter, koneksi);
            JRViewer viewer = new JRViewer(jPrint);
            JasperViewer.viewReport(jPrint,false);
        } catch (JRException ex) {
            Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void buatLaporanBulanan(String bln,String thn,String nama,String alamat,String kontak){
        String Nota = path+"Nota.jrxml";
        HashMap parameter = new HashMap();
        try {
            File fileNota = new File("src/PDF/Laporan_Bulanan.jrxml");
            fileNota.getPath();
            //System.out.println(path);
            JasperDesign DesignNota = JRXmlLoader.load(fileNota);
           
            parameter.clear();
            parameter.put("NAMA_LAUNDRY",nama);
            parameter.put("SUB",alamat+","+kontak);
            parameter.put("BULAN",bln);
            parameter.put("TAHUN",thn);
            JasperReport RNota = JasperCompileManager.compileReport(DesignNota);
            
            JasperPrint jPrint = JasperFillManager.fillReport(RNota, parameter, koneksi);
            JRViewer viewer = new JRViewer(jPrint);
            JasperViewer.viewReport(jPrint,false);
        } catch (JRException ex) {
            Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
