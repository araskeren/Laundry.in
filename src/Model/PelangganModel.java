/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import Model.Koneksi;
import View.ManagementPelanggan;
import java.sql.*;
import java.util.logging.*;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author REM
 */
public class PelangganModel {
    Connection DB;
    Statement stmt = null;
    public String sql = null;
    ResultSet rs = null;
    public PelangganModel() {
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
    void executeUpdate() {
        try {
            stmt=DB.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(PelangganModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void getDataMySql(){
        sql="SELECT * FROM `tb_pelanggan`";
        executeQuery();
    }
    public void hapusDataPelanggan(Integer ID){
        sql="DELETE FROM `tb_pelanggan` WHERE `tb_pelanggan`.`id` = "
                + ID;
        executeUpdate();
    }
    public void setDataPelanggan(ManagementPelanggan view){
        sql="UPDATE `tb_pelanggan` SET `nama`='"
                + view.getTxtNama().getText()+"',`alamat`='"
                + view.getTxtAlamat().getText()+"',`kontak`='"
                + view.getTxtContact().getText()+"' WHERE `tb_pelanggan`.`id` = "
                + view.getTxtID_Pelanggan().getText();
        executeUpdate();
    }
    public void tambahDataPelanggan(ManagementPelanggan view){
        sql="INSERT INTO `tb_pelanggan`(`id`, `nama`, `alamat`, `kontak`, `tgl_daftar`) VALUES (null,'"
                +view.getTxtNama().getText()+"','"
                +view.getTxtAlamat().getText()+"','"
                +view.getTxtContact().getText()+"',CURRENT_TIMESTAMP)";
        executeUpdate();
        tambahKeTable(view);
        
    }
    public void tambahKeTable(ManagementPelanggan view){
        getDataMySql();
        DefaultTableModel model = (DefaultTableModel) view.getTabbleSemuaPelanggan().getModel();
        try {
            while(rs.next()){
                model.addRow(new Object[]{
                    model.getRowCount()+1,
                    rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getString("alamat"),
                    rs.getString("kontak"),
                    rs.getString("tgl_daftar")
                });
            }
        } catch (SQLException ex) {
            Logger.getLogger(PelangganModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
