/*
 * Sakurasou Digital Studio
 * Each line should be prefixed with  * 
 */
package Model;
import View.Laporan;
import Model.Koneksi;
import Model.PelangganModel;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author REM
 */
public class LaporanModel {
    Connection DB;
    Statement stmt = null;
    public String sql = null;
    ResultSet rs = null;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate localDate = LocalDate.now();
    String Tgl;
    public LaporanModel() {
        DB=new Koneksi().getMysqlConnection();
    }
    void getData(){
        try {
            stmt=DB.createStatement();
            rs=stmt.executeQuery(sql);    
        } catch (SQLException ex) {
            Logger.getLogger(ManagementModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void getDataMySql(){
        sql="SELECT * FROM `tr_pencucian`";
        getData();
    }
    void getDataPencucianByRange(String tglMulai,String tglSelesai){
        sql="SELECT * FROM `tr_pencucian` WHERE `tgl_order` BETWEEN '"
                + tglMulai+"' AND '"
                + tglSelesai+"'AND `status_pemesanan` = 'SUDAH DI AMBIL' ORDER BY `tr_pencucian`.`tgl_order` ASC ";
        getData();
    }
    void getDataPencucianByDay(String tgl,String bln,String thn){
        sql="SELECT * FROM `tr_pencucian` WHERE day(tgl_order)="
                + tgl+" and month(tgl_order)="
                + bln+" and year(tgl_order)="+thn;
        System.out.println(sql);
        getData();
    }
    void getDataPencucianByMonth(String bln,String thn){
        sql="SELECT * FROM `tr_pencucian` WHERE month(tgl_order)="+bln+" and year(tgl_order)="+thn+" AND `status_pemesanan` = 'SUDAH DI AMBIL'";
        System.out.println(sql);
        getData();
    }
    public class getRekap{
        public getRekap() {
            Tgl=dtf.format(localDate);
        }
        public void setDataKeTabel(String tglMulai,String tglSelesai,DefaultTableModel tbTransaksi){
            getDataPencucianByRange(tglMulai, tglSelesai);
            try {
                while(rs.next()){
                    tbTransaksi.addRow(new Object[]{
                        tbTransaksi.getRowCount()+1,
                        rs.getString("tgl_order"),
                        rs.getString("no_faktur"),
                        rs.getString("nama_pelanggan"),
                        rs.getString("estimasi"),
                        rs.getString("pengambilan"),
                        rs.getString("total_bayar")
                    });
                }
            } catch (SQLException ex) {
                Logger.getLogger(LaporanModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        public void setDataKeTabelBulan(String bln,String thn,DefaultTableModel tbTransaksi){
            getDataPencucianByMonth(bln,thn);
            try {
                while(rs.next()){
                    tbTransaksi.addRow(new Object[]{
                        tbTransaksi.getRowCount()+1,
                        rs.getString("tgl_order"),
                        rs.getString("no_faktur"),
                        rs.getString("nama_pelanggan"),
                        rs.getString("estimasi"),
                        rs.getString("pengambilan"),
                        rs.getString("total_bayar")
                    });
                }
            } catch (SQLException ex) {
                Logger.getLogger(LaporanModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        public void setDataKeTabelHarian(String tgl,String bln,String thn,DefaultTableModel tbTransaksi){
            getDataPencucianByDay(tgl,bln,thn);
            try {
                while(rs.next()){
                    tbTransaksi.addRow(new Object[]{
                        tbTransaksi.getRowCount()+1,
                        rs.getString("tgl_order"),
                        rs.getString("no_faktur"),
                        rs.getString("nama_pelanggan"),
                        rs.getString("estimasi"),
                        rs.getString("pengambilan"),
                        rs.getString("total_bayar")
                    });
                }
            } catch (SQLException ex) {
                Logger.getLogger(LaporanModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        public int getJumlahPelanggan(){
            sql="SELECT * FROM `tb_pelanggan`";
            
            int jml=0;
            try {
                stmt=DB.createStatement();
                rs=stmt.executeQuery(sql);
                while(rs.next()){
                    jml++;
                }
            } catch (SQLException ex) {
                Logger.getLogger(LaporanModel.class.getName()).log(Level.SEVERE, null, ex);
            }
            return jml;
        }
        public int getTotalPesanan(String tgl,String bln,String thn){
            getDataPencucianByDay(tgl, bln, thn);
            int x=0;
            try {
                while(rs.next()){
                    x++;
                }
            } catch (SQLException ex) {
                Logger.getLogger(LaporanModel.class.getName()).log(Level.SEVERE, null, ex);
            }
            return x;
        }
        public Double getTotalPemasukan(String tgl,String bln,String thn){
            getDataPencucianByDay(tgl, bln, thn);
            Double x=0.0;
            try {
                while(rs.next()){
                    x+=rs.getDouble("total_bayar");
                }
            } catch (SQLException ex) {
                Logger.getLogger(LaporanModel.class.getName()).log(Level.SEVERE, null, ex);
            }
            return x;
        }
    }
}
