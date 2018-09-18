/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import View.MenuUtama;
import java.sql.*;
import java.text.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author REM
 */
public class TransaksiModel {

    Double sum=0.0;
    Connection koneksi;
    Statement stmt = null;
    public String sql = null;
    ResultSet rs = null;
    
    public TransaksiModel() {
        koneksi = new Koneksi().getMysqlConnection();
        
    }
    public void executeQuery(){
        try {
            stmt=koneksi.createStatement();
            rs=stmt.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(TransaksiModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public class getDataUser{
        //public ResultSet getByID(int id);
        //Mendapatkan data user berdasarkan id,dengan menerima parameter ID ber Tipe Int.
        //Melakukan /Mendapatkan data dari database dengan format
        //SQL SELECT * FROM `tb_pelanggan` WHERE `id` = parameter ID
        //Pilih Dari tb_Pelanggan dimana id yang berada di tabel pelanggan sama dengan parameter ID
        public ResultSet getByID(int id){
            sql="SELECT * FROM `tb_pelanggan` WHERE `id` = "+id;
            try {
                stmt=koneksi.createStatement();
                rs=stmt.executeQuery(sql);
            } catch (SQLException ex) {
                Logger.getLogger(TransaksiModel.class.getName()).log(Level.SEVERE, null, ex);
            }
            return rs;
        }
        //Mendapatkan data user berdasarkan nama,dengan menerima parameter nama ber Tipe String.
        //Melakukan /Mendapatkan data dari database dengan format
        //SQL SELECT * FROM `tb_pelanggan` WHERE `nama` = parameter nama
        //Pilih Dari tb_Pelanggan dimana nama yang berada di tabel pelanggan sama dengan parameter nama
        public ResultSet getByName(String nama){
            sql="SELECT * FROM `tb_pelanggan` WHERE `nama` = '"+nama+"'";
            try {
                stmt=koneksi.createStatement();
                rs=stmt.executeQuery(sql);
            } catch (SQLException ex) {
                Logger.getLogger(TransaksiModel.class.getName()).log(Level.SEVERE, null, ex);
            }
            return rs;
        }
        //Mendapatkan data user berdasarkan noHP,dengan menerima parameter noHP ber Tipe String.
        //Melakukan /Mendapatkan data dari database dengan format
        //SQL SELECT * FROM `tb_pelanggan` WHERE `kontak` = parameter kontak
        //Pilih Dari tb_Pelanggan dimana kontak yang berada di tabel pelanggan sama dengan parameter kontak
        public ResultSet getByPhone(String noHp){
            sql="SELECT * FROM `tb_pelanggan` WHERE `kontak` = '"+noHp+"'";
            
            try {
                stmt=koneksi.createStatement();
                rs=stmt.executeQuery(sql);
            } catch (SQLException ex) {
                Logger.getLogger(TransaksiModel.class.getName()).log(Level.SEVERE, null, ex);
            }
            return rs;
        }
    }
    
    public class getDataCombo{
        public ResultSet getDataLoundry(String jenisloundry){
            sql="SELECT * FROM `tb_jenislaundry` WHERE `jenis_laundry` = '"+jenisloundry+"'";
            executeQuery();
            return rs;
        }
        public ResultSet getDataItem(String jenisloundry,String tipelaundry){
            sql="SELECT `item` FROM `tb_jenislaundry` WHERE `tipe_laundry`='"
                    +tipelaundry+"'";
            executeQuery();
            return rs;
        }
        public void tambahParfum(MenuUtama view){
            view.getComboJenisParfum().addItem("Lavender");
            view.getComboJenisParfum().addItem("Molto");
        }
        public void tambahSatuan(MenuUtama view){
            view.getComboSatuanUkur().addItem("KG");
            view.getComboSatuanUkur().addItem("Satuan");
        }
    }
    
    public class Transaksi{
        public Double hitungKembalian(MenuUtama view){
            Double hasil=Double.parseDouble(view.getTxtUangYangDiBayar().getText())-Double.parseDouble(view.getTxtTotalBayar().getText());
            return hasil;
        }
        public void tambahKeJTable(MenuUtama view){
            DefaultTableModel model = (DefaultTableModel) view.getTableTransaksiLoundry().getModel();
            model.addRow(new Object[]{
                model.getRowCount()+1,
                view.getComboItem().getSelectedItem(),
                view.getComboJenisLaundry().getSelectedItem(),
                view.getComboPaketLoundry().getSelectedItem(),
                view.getComboJenisParfum().getSelectedItem(),
                view.getComboSatuanUkur().getSelectedItem(),
                view.getTxtUkuran().getValue(),
                view.getHarga().getText()
            });
            view.getTxtTotalBayar().setText(sum.toString());
        }
        public void getLastRecordTableTr_cuci(){
            sql="SELECT `No_Faktur` FROM `tr_cuci` ORDER BY `No_Faktur` DESC LIMIT 1";
            executeQuery();
        }
        public void getLastRecordTableTr_pencucian(){
            sql="SELECT `no_faktur` FROM `tr_pencucian` ORDER BY `no_faktur` DESC LIMIT 1";
            executeQuery();
        }
        public int getIdComboLoundry(String x,String y,String z){
            int id=0;
            try {
                stmt=koneksi.createStatement();
                sql="SELECT * FROM `tb_jenislaundry` WHERE"
                        + " `jenis_laundry` LIKE '"+x
                        + "' AND `tipe_laundry` LIKE '"+y
                        + "' AND `item` LIKE '"+z+"'";
                rs=stmt.executeQuery(sql);
                while(rs.next()){
                    id=rs.getInt("id");
                }
            } catch (SQLException ex) {
                Logger.getLogger(TransaksiModel.class.getName()).log(Level.SEVERE, null, ex);
            }
            return id;
        }

        public String getMetodePengambilan(MenuUtama view){
            if(view.getRadioDiantar().isSelected()){
                return view.getRadioDiantar().getText();
            }else if(view.getRadioExspress().isSelected()){
                return view.getRadioExspress().getText();
            }else if(view.getRadionAmbilSendiri().isSelected()){
                return view.getRadionAmbilSendiri().getText();
            }else{
                return "";
            }
        }
        public void tambahKeDataTransaksiCuci(MenuUtama view,int id_transaksi){
            DefaultTableModel model = (DefaultTableModel) view.getTableTransaksiLoundry().getModel();
            try {
                stmt=koneksi.createStatement();
                for(int i=0;i<model.getRowCount();i++){
                    sql="INSERT INTO `tr_cuci` (`No_Faktur`, `id_JenisLaundry`, `jenis_parfum`, `satuan_ukur`, `jml`, `harga`) VALUES ('"
                        +id_transaksi+"','"
                        +getIdComboLoundry(model.getValueAt(i, 2).toString(),model.getValueAt(i, 3).toString(),model.getValueAt(i, 1).toString())+"','"
                        +model.getValueAt(i, 4)+"','"
                        +model.getValueAt(i, 5)+"','"
                        +model.getValueAt(i, 6)+"','"
                        +model.getValueAt(i, 7)+"')";
                    stmt.executeUpdate(sql);
                }
            } catch (SQLException ex) {
                Logger.getLogger(TransaksiModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        public void tambahKeDataTransaksiPencucian(MenuUtama view,Double Diskon){
            try {
                sql="INSERT INTO `tr_pencucian` ("
                        + "`no_faktur`, "
                        + "`id_pelanggan`, "
                        + "`nama_pelanggan`, "
                        + "`tgl_order`, "
                        + "`tgl_update`,"
                        + "`estimasi`, "
                        + "`pengambilan`, "
                        + "`diskon`, "
                        + "`keterangan`, "
                        + "`status_pemesanan`, "
                        + "`total_bayar`, "
                        + "`jumlah_dibayar`, "
                        + "`kembalian`) "
                        
                        + "VALUES ("
                        + "NULL, "
                        + "'"+view.getTxtIDPemesan().getText()+"', "
                        + "'"+view.getTxtNamaPemesan().getText()+"', "
                        + "CURRENT_TIMESTAMP, "
                        + "NULL,"
                        + "'"+view.getTxtEstimasi().getValue()+"', "
                        + "'"+getMetodePengambilan(view)+"', "
                        + ""+Diskon+", "
                        + "'"+view.getTxtKeteranga().getText()+"', "
                        + "'Belum Di Cuci', "
                        + "'"+view.getTxtTotalBayar().getText()+"', "
                        + "'"+view.getTxtUangYangDiBayar().getText()+"', "
                        + "'"+view.getTxtKembalian().getText()+"')";
                System.out.println(sql);
                stmt=koneksi.createStatement();
                stmt.executeUpdate(sql);
                
                getLastRecordTableTr_pencucian();
                int No_Faktur = 0;
                while(rs.next()){
                    No_Faktur=rs.getInt("no_faktur");
                }
                tambahKeDataTransaksiCuci(view, No_Faktur);
            } catch (SQLException ex) {
                Logger.getLogger(TransaksiModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        public void cetakNota(Double Diskon,Double Tambahan){
            getLastRecordTableTr_pencucian();
            Config conf=new Config();
            String nama=conf.getNamaLaundry();
            String alamat=conf.getAlamatLaundry();
            String kontak=conf.getKontakLaundry();
            try {
                Integer NoFaktur=0;
                while(rs.next()){
                    NoFaktur=rs.getInt("no_faktur");
                }
                
                System.out.println(NoFaktur);
                new PDF().buatNota(NoFaktur,Diskon,Tambahan,nama,alamat,kontak);
            } catch (SQLException ex) {
                Logger.getLogger(TransaksiModel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
}
