/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import View.ManagementOrder;
import java.awt.Component;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;


/**
 *
 * @author REM
 */
public class ManagementModel {
    Connection koneksi;
    Statement stmt = null;
    public String sql = null;
    ResultSet rs = null;

    public ManagementModel() {
        koneksi=new Koneksi().getMysqlConnection();
    }
    public void executeUpdate(){
        try {
            stmt=koneksi.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(PengaturanModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void updateTRCucian(String param,Integer no_faktur){
        sql="UPDATE `tr_pencucian` SET `status_pemesanan` = '"
                +param+"',`tgl_update`=CURRENT_TIMESTAMP WHERE `tr_pencucian`.`no_faktur` ="
                +no_faktur+"";
        executeUpdate();
    }
    public void dropTRCucian(Integer param){
        sql="DELETE FROM `tr_pencucian` WHERE `tr_pencucian`.`no_faktur` = "
                + param+"";
        executeUpdate();
    }
    public class setTab{

        public setTab() {
        }
        
        ResultSet getDataCucian(){
            sql="SELECT * FROM `tr_pencucian` WHERE 1";
            ResultSet x=null;
            try {
                stmt=koneksi.createStatement();
                x=stmt.executeQuery(sql);
            } catch (SQLException ex) {
                Logger.getLogger(ManagementModel.class.getName()).log(Level.SEVERE, null, ex);
            }
            return x;
        }
       
        public void setAllTable(ManagementOrder view){
            DefaultTableModel modelBelumDiAmbil = (DefaultTableModel) view.getTableBelumDiAmbil().getModel();
            DefaultTableModel modelBelumDiCuci = (DefaultTableModel) view.getTableBelumDiCuci().getModel();
            DefaultTableModel modelSedangDiProses = (DefaultTableModel) view.getTableSedangDiProses().getModel();
            DefaultTableModel modelSudahDiAmbil = (DefaultTableModel) view.getTableSudahDiAmbil().getModel();
            ResultSet rs=getDataCucian();
            
            try {
                while(rs.next()){
                    System.out.println(rs.getString("status_pemesanan"));
                    if(rs.getString("status_pemesanan").equals("Belum Di Cuci")){
                        modelBelumDiCuci.addRow(new Object[]{
                            modelBelumDiCuci.getRowCount()+1,
                            rs.getString("no_faktur"),
                            rs.getString("tgl_order"),
                            rs.getString("id_pelanggan"),
                            rs.getString("nama_pelanggan"),
                            rs.getString("estimasi"),
                            rs.getString("pengambilan"),
                            rs.getString("total_bayar"),
                            rs.getString("status_pemesanan"),
                            rs.getString("keterangan")
                        });
                    }else if(rs.getString("status_pemesanan").equals("Di Proses")){
                        modelSedangDiProses.addRow(new Object[]{
                            modelSedangDiProses.getRowCount()+1,
                            rs.getString("no_faktur"),
                            rs.getString("tgl_order"),
                            rs.getString("tgl_update"),
                            rs.getString("id_pelanggan"),
                            rs.getString("nama_pelanggan"),
                            rs.getString("estimasi"),
                            rs.getString("pengambilan"),
                            rs.getString("total_bayar"),
                            rs.getString("status_pemesanan"),
                            rs.getString("keterangan")
                            
                        });
                    }
                    else if(rs.getString("status_pemesanan").equals("Belum Di Ambil")){
                        modelBelumDiAmbil.addRow(new Object[]{
                            modelBelumDiAmbil.getRowCount()+1,
                            rs.getString("no_faktur"),
                            rs.getString("tgl_order"),
                            rs.getString("tgl_update"),
                            rs.getString("id_pelanggan"),
                            rs.getString("nama_pelanggan"),
                            rs.getString("estimasi"),
                            rs.getString("pengambilan"),
                            rs.getString("total_bayar"),
                            rs.getString("status_pemesanan"),
                            rs.getString("keterangan")
                            
                        });
                    }
                    else if(rs.getString("status_pemesanan").equals("Sudah Di Ambil")){
                        modelSudahDiAmbil.addRow(new Object[]{
                            modelSudahDiAmbil.getRowCount()+1,
                            rs.getString("no_faktur"),
                            rs.getString("tgl_order"),
                            rs.getString("tgl_update"),
                            rs.getString("id_pelanggan"),
                            rs.getString("nama_pelanggan"),
                            rs.getString("estimasi"),
                            rs.getString("pengambilan"),
                            rs.getString("total_bayar"),
                            rs.getString("status_pemesanan"),
                            rs.getString("keterangan")
                            
                        });
                    }
                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(ManagementModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
