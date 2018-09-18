/*
 * Sakurasou Digital Studio
 * Each line should be prefixed with  * 
 */
package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author REM
 */
public class MonitoringModel {
    Connection DB=null;
    Statement stmt = null;
    String sql=null;
    ResultSet rs=null;
    public MonitoringModel() {
        DB=new Koneksi().getMysqlConnection();
    }
    
    void executeQuery(){
        try {
          stmt=DB.createStatement();
          rs=stmt.executeQuery(sql);
        } catch (SQLException ex) {
          Logger.getLogger(ManagementModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//getData
    void executeUpdate(){
        try {
            stmt=DB.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(PelangganModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//Update/Delete
    
    public ResultSet getData(){
        sql="SELECT "
                + "tr_monitoring.`ID_Log` As ID_Log,"
                + "tr_monitoring.`ID_User` AS ID,"
                + "(SELECT nama FROM tb_user WHERE id_user=ID)AS Nama,"
                + "(SELECT level FROM tb_user WHERE id_user=ID) AS Level,"
                + "tr_monitoring.`Tgl_Login` AS Tgl_Login,"
                + "tr_monitoring.`Tgl_Logout` AS Tgl_Logout "
                + "FROM `tr_monitoring` tr_monitoring "
                + "WHERE 1";
        executeQuery();
        return rs;
    }
    public ResultSet getDataByRange(String tglMulai,String tglSelesai){
        sql="SELECT "
                + "tr_monitoring.`ID_Log` As ID_Log,"
                + "tr_monitoring.`ID_User` AS ID,"
                + "(SELECT nama FROM tb_user WHERE id_user=ID)AS Nama,"
                + "(SELECT level FROM tb_user WHERE id_user=ID) AS Level,"
                + "tr_monitoring.`Tgl_Login` AS Tgl_Login,"
                + "tr_monitoring.`Tgl_Logout` AS Tgl_Logout "
                + "FROM `tr_monitoring` tr_monitoring "
                + "WHERE Tgl_Login BETWEEN '"+tglMulai+"' AND '"+tglSelesai+"'";
        executeQuery();
        return rs;
    }
    public ResultSet getDataByMonth(String Bln,String Thn){
        sql="SELECT tr_monitoring.`ID_Log` As ID_Log,"
                + "tr_monitoring.`ID_User` AS ID,"
                + "(SELECT nama FROM tb_user WHERE id_user=ID)AS Nama,"
                + "(SELECT level FROM tb_user WHERE id_user=ID) AS Level,"
                + "tr_monitoring.`Tgl_Login` AS Tgl_Login,"
                + "tr_monitoring.`Tgl_Logout` AS Tgl_Logout"
                + " FROM `tr_monitoring` tr_monitoring "
                + "WHERE month(`Tgl_Login`)="
                + Bln+" and year(`Tgl_Login`)="+Thn;
        executeQuery();
        return rs;
    }
    public ResultSet getDataByDay(String tgl,String bln,String thn){
        sql="SELECT tr_monitoring.`ID_Log` As ID_Log,"
                + "tr_monitoring.`ID_User` AS ID,"
                + "(SELECT nama FROM tb_user WHERE id_user=ID)AS Nama,"
                + "(SELECT level FROM tb_user WHERE id_user=ID) AS Level,"
                + "tr_monitoring.`Tgl_Login` AS Tgl_Login,"
                + "tr_monitoring.`Tgl_Logout` AS Tgl_Logout "
                + "FROM `tr_monitoring` tr_monitoring "
                + "WHERE month(`tgl_login`)="
                + bln+" and year(`tgl_login`)="
                + thn+" and day(`tgl_login`)="
                + tgl;
        executeQuery();
        return rs;
    }
    
}
