/*
 * Sakurasou Digital Studio
 * Each line should be prefixed with  * 
 */
package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginModel {
    Connection DB=null;
    Statement stmt = null;
    String sql=null;
    ResultSet rs=null;

    public LoginModel() {
        DB=new Koneksi().getMysqlConnection();
    }
    
    void executeQuery(){
        try {
          stmt=DB.createStatement();
          rs=stmt.executeQuery(sql);
        } catch (SQLException ex) {
          Logger.getLogger(ManagementModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void executeUpdate(){
        try {
            stmt=DB.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(PengaturanModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ResultSet getDataUser(){
        sql="SELECT * FROM `tb_user`";
        executeQuery();
        return rs;
    }
    public void setLoginTime(int id){
        sql="INSERT INTO `tr_monitoring` (`ID_Log`, `ID_User`, `Tgl_Login`, `Tgl_Logout`) VALUES (NULL,'"
                +id+"', CURRENT_TIMESTAMP, NULL)";
        executeUpdate();
    }
    public void setLogoutTime(String ID_Log){
        sql="UPDATE `tr_monitoring` SET `Tgl_Logout` = CURRENT_TIMESTAMP WHERE `tr_monitoring`.`ID_Log` = "+ID_Log;
        executeUpdate();
    }
    public String getIDLog(){
        String idLog=null;
        sql="SELECT `ID_Log` FROM tr_monitoring ORDER BY `ID_Log` DESC LIMIT 1";
        executeQuery();
        try {
            while(rs.next()){
                idLog=rs.getString("ID_Log");
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idLog;
    }
    
}
