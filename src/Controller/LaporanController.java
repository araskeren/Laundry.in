/*
 * Sakurasou Digital Studio
 * Each line should be prefixed with  * 
 */
package Controller;
import Model.Config;
import Model.LaporanModel;
import Model.PDF;
import View.Laporan;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author REM
 */
public class LaporanController {
    LaporanModel model;
    Laporan view;
    DefaultTableModel modelTBTransaksi;
    SimpleDateFormat tgl=new SimpleDateFormat("dd");
    SimpleDateFormat bln=new SimpleDateFormat("MM");
    SimpleDateFormat thn=new SimpleDateFormat("yyyy");
    String Bulan;
    String Tahun;
    String Tanggal;
    public LaporanController(Laporan view) {
        this.view = view;
        model=new LaporanModel();
        modelTBTransaksi=(DefaultTableModel) view.getTblTransaksi().getModel();
        SetDataRekap();
        Listener();
    }
    void SetDataRekap(){
        Date tglSekarang = new Date();
        Tanggal=tgl.format(tglSekarang);
        Bulan=bln.format(tglSekarang);
        Tahun=thn.format(tglSekarang);
        view.getTxtTotalPelanggan().setText(model.new getRekap().getJumlahPelanggan()+"");
        view.getTxtPemasukan().setText(model.new getRekap().getTotalPemasukan(Tanggal,Bulan,Tahun)+"");
        view.getTxtTotalPesanan().setText(model.new getRekap().getTotalPesanan(Tanggal,Bulan,Tahun)+"");
        model.new getRekap().setDataKeTabelHarian(Tanggal, Bulan, Tahun, modelTBTransaksi);
    }
    
    void Listener(){
        view.getBtnLihatLaporan().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modelTBTransaksi.getDataVector().removeAllElements();
                SimpleDateFormat dt = new SimpleDateFormat("yyyyy-MM-dd");
                PDF pdf = new PDF();
                Bulan = bln.format(view.getTglDari().getDate());
                Tahun = thn.format(view.getTglDari().getDate());
                String tglMulai = dt.format(view.getTglDari().getDate());
                StringBuilder tgl1 = new StringBuilder(tglMulai);
                tgl1.deleteCharAt(0);
                
                String tglSelesai;
                
                tglMulai=tgl1.toString();
                
                
                Config conf = new Config();
                String namaLaundry = conf.getNamaLaundry();
                String alamat=conf.getAlamatLaundry();
                String kontak=conf.getKontakLaundry();
                if(view.getComboJenisLaporan().getSelectedIndex()==1){
                    model.new getRekap().setDataKeTabelBulan(Bulan,Tahun,modelTBTransaksi);
                    
                    if(view.getComboImport().getSelectedIndex()==1){
                        pdf.buatLaporanBulanan(Bulan,Tahun,namaLaundry, alamat, kontak);
                    }
                }else if(view.getComboJenisLaporan().getSelectedIndex()==2){
                    Tanggal =tgl.format(view.getTglDari().getDate());
                    model.new getRekap().setDataKeTabelHarian(Tanggal,Bulan,Tahun,modelTBTransaksi);
                    
                    if(view.getComboImport().getSelectedIndex()==1){
                        pdf.buatLaporanHarian(tglMulai,namaLaundry, alamat, kontak);
                    }
                }else if(view.getComboJenisLaporan().getSelectedIndex()==3){
                    tglSelesai = dt.format(view.getTglSampai().getDate());
                    StringBuilder tgl2 = new StringBuilder(tglSelesai);
                    tgl2.deleteCharAt(0);
                    tglSelesai=tgl2.toString();
                    model.new getRekap().setDataKeTabel(tglMulai, tglSelesai,modelTBTransaksi);
                    
                    if(view.getComboImport().getSelectedIndex()==1){
                        pdf.buatLaporan(tglMulai,tglSelesai,namaLaundry, alamat, kontak);
                    }
                }
            }
        });
        view.getComboJenisLaporan().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(view.getComboJenisLaporan().getSelectedIndex()==1){
                    view.getTglDari().setEnabled(true);
                    view.getTglSampai().setEnabled(false);
                }else if(view.getComboJenisLaporan().getSelectedIndex()==2){
                    view.getTglDari().setEnabled(true);
                    view.getTglSampai().setEnabled(false);
                }else if(view.getComboJenisLaporan().getSelectedIndex()==3){
                    view.getTglDari().setEnabled(true);
                    view.getTglSampai().setEnabled(true);
                }
            }
        });
        
    }
    
}
