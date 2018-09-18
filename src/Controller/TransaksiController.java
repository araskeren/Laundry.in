/*
 * Sakurasou Digital Studio
 * Each line should be prefixed with  * 
 */
package Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Model.TransaksiModel;
import View.MenuUtama;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author REM
 */
public class TransaksiController {

    ResultSet rs=null;
    MenuUtama menuUtama;
    TransaksiModel model = new TransaksiModel();
    DefaultTableModel modelTBTransaksi;
    ListSelectionModel TBTransaksi;
    Double disc=0.0;
    Double tambahBiaya=0.0;
    Boolean statusDiskon = false,statusBiayaTambahan = false;
    int count;
    int row=0;
    Double TotalBayar =0.0;
    public TransaksiController(MenuUtama menuUtama) {
       this.menuUtama=menuUtama;
       modelTBTransaksi = (DefaultTableModel) menuUtama.getTableTransaksiLoundry().getModel();
       TBTransaksi=menuUtama.getTableTransaksiLoundry().getSelectionModel();
       Listener();
    }
    ListSelectionListener listTB_Item=new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            row = TBTransaksi.getMinSelectionIndex();
            menuUtama.getComboJenisLaundry().setSelectedItem(modelTBTransaksi.getValueAt(row,2));
            clearComboPaket();
            getDataPaket();
            menuUtama.getComboPaketLoundry().setSelectedItem(modelTBTransaksi.getValueAt(row,3));
            clearComboItem();
            getDataItem();
            menuUtama.getComboItem().setSelectedItem(modelTBTransaksi.getValueAt(row,1));
            menuUtama.getComboJenisParfum().setSelectedItem(modelTBTransaksi.getValueAt(row,4));
            menuUtama.getComboSatuanUkur().setSelectedItem(modelTBTransaksi.getValueAt(row,5));
            menuUtama.getTxtUkuran().setValue(modelTBTransaksi.getValueAt(row,6));
            }
        };
    void setValueKeTable(){
        modelTBTransaksi.setValueAt(menuUtama.getComboItem().getSelectedItem(), row, 1);
        modelTBTransaksi.setValueAt(menuUtama.getComboJenisLaundry().getSelectedItem(), row, 2);
        modelTBTransaksi.setValueAt(menuUtama.getComboPaketLoundry().getSelectedItem(), row, 3);
        modelTBTransaksi.setValueAt(menuUtama.getComboJenisParfum().getSelectedItem(), row, 4);
        modelTBTransaksi.setValueAt(menuUtama.getComboSatuanUkur().getSelectedItem(), row, 5);
        modelTBTransaksi.setValueAt(menuUtama.getTxtUkuran().getValue(), row, 6);
        modelTBTransaksi.setValueAt(menuUtama.getHarga().getText(), row, 7);
    }
    void clearCariPelanggan(){
        menuUtama.getTxtKode().setText("");
        menuUtama.getTxtDaftarNama().setText("");
        menuUtama.getTxtContact().setText("");
    }
    void clearDataPelanggan(){
        menuUtama.getTxtIDPemesan().setText("");
        menuUtama.getTxtNamaPemesan().setText("");
        menuUtama.getTxtEstimasi().setValue(0);
        menuUtama.getTxtBiayaTambahan().setText("");
        menuUtama.getTxtDiskon().setText("");
        menuUtama.getTxtKeteranga().setText("");
    }
    void clearComboPaket(){
        menuUtama.getComboPaketLoundry().removeAllItems();
        menuUtama.getComboPaketLoundry().addItem("-Tipe-");
    }
    void clearComboItem(){
        menuUtama.getComboItem().removeAllItems();
        menuUtama.getComboItem().addItem("-Item-");
    }
    void clearComboParfum(){
        menuUtama.getComboJenisParfum().removeAllItems();
        menuUtama.getComboJenisParfum().addItem("-Parfum-");
    }
    void clearComboSatuan(){
        menuUtama.getComboSatuanUkur().removeAllItems();
        menuUtama.getComboSatuanUkur().addItem("-Satuan Ukur-");
    }
    void clearDataInputCucian(){
        clearComboItem();
        clearComboPaket();
        clearComboParfum();
        clearComboSatuan();
        menuUtama.getComboJenisLaundry().setSelectedIndex(0);
        menuUtama.getComboPaketLoundry().setSelectedIndex(0);
        menuUtama.getComboItem().setSelectedIndex(0);
        menuUtama.getComboJenisParfum().setSelectedIndex(0);
        menuUtama.getComboSatuanUkur().setSelectedIndex(0);
        menuUtama.getTxtUkuran().setValue(0);
    }
    void clearDataCucian(){
        clearCariPelanggan();
        clearDataInputCucian();
        clearDataPelanggan();
        modelTBTransaksi.getDataVector().removeAllElements();
        TBTransaksi.removeListSelectionListener(listTB_Item);
        menuUtama.getTxtTotalBayar().setText("");
        menuUtama.getTxtDiskon().setText("");
        menuUtama.getTxtBiayaTambahan().setText("");
        menuUtama.getTxtKembalian().setText("");
        disc=0.0;
        tambahBiaya=0.0;
        TotalBayar=0.0;
        statusBiayaTambahan=false;
        statusDiskon=false;
        
    }
    void getDataPaket(){
        rs=model.new getDataCombo().getDataLoundry(menuUtama.getComboJenisLaundry().getSelectedItem().toString());
                int cek = 1;
                try {
                    while(rs.next()){
                        for(int i=menuUtama.getComboPaketLoundry().getItemCount()-1;i!=0;i--){
                            if(menuUtama.getComboPaketLoundry().getItemAt(i).equals(rs.getString("tipe_laundry"))){
                                cek=0;
                                break;
                            }
                        }
                        if(cek==1){
                        menuUtama.getComboPaketLoundry().addItem(rs.getString("tipe_laundry"));
                        }else{
                            cek=1;
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(TransaksiController.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    void getDataItem(){
        String JenisLaundry=menuUtama.getComboJenisLaundry().getSelectedItem().toString();
                rs=model.new getDataCombo().getDataLoundry(JenisLaundry);
                int cek=1;
                try {
                    while(rs.next()){
                        for(int i=menuUtama.getComboItem().getItemCount()-1;i!=0;i--){
                            if(menuUtama.getComboItem().getItemAt(i).equals(rs.getString("item"))){
                                cek=0;
                                break;
                            }
                        }
                        if(cek==1){
                        menuUtama.getComboItem().addItem(rs.getString("item"));
                        }else{
                            cek=1;
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(TransaksiController.class.getName()).log(Level.SEVERE, null, ex);
                }
                clearComboParfum();
                clearComboSatuan();
                model.new getDataCombo().tambahParfum(menuUtama);
                model.new getDataCombo().tambahSatuan(menuUtama);
    }
    Double hitungDiskon(){
        Double diskon=Double.parseDouble(menuUtama.getTxtDiskon().getText());
        TotalBayar= Double.parseDouble(menuUtama.getTxtTotalBayar().getText());
        Integer x=diskon.intValue();
        int count=0;
        if(x==0){
            count=1;
        }else{
            while(x>0){
                x=x/10;
                count++;
            }
        }
        if(count<=3&&count>=1){
            diskon=(TotalBayar*diskon)/100;
        }
        return diskon;
    }
    void getTotalBayar(){
        TotalBayar=0.0;
        System.out.println(modelTBTransaksi.getRowCount());
        for(int i=0;i<modelTBTransaksi.getRowCount();i++){
           TotalBayar+=Double.parseDouble(modelTBTransaksi.getValueAt(i, 7).toString());
        }
        menuUtama.getTxtTotalBayar().setText(TotalBayar.toString());
    }
    void removeDataTable(){
        modelTBTransaksi.getDataVector().remove(TBTransaksi.getMinSelectionIndex());
    }
    void hitungTotalBayar(){
       TotalBayar=Double.parseDouble(menuUtama.getTxtTotalBayar().getText());
                if(!menuUtama.getTxtDiskon().getText().equals("")){
                    disc = hitungDiskon();
                    TotalBayar-=disc;
                    statusDiskon = true;
                }else{
                    disc=0.0;
                    statusDiskon=false;
                }
                if(!menuUtama.getTxtBiayaTambahan().getText().equals("")){
                    tambahBiaya=Double.parseDouble(menuUtama.getTxtBiayaTambahan().getText());
                    TotalBayar+=tambahBiaya;
                    statusBiayaTambahan=true;
                }else{
                    statusBiayaTambahan=false;
                    tambahBiaya=0.0;
                }
                if(statusDiskon==true&&statusBiayaTambahan==false){
                     JOptionPane.showMessageDialog(menuUtama,"Pelanggan Mendapat Diskon Sebesar Rp."+disc);
                }else if(statusDiskon==false&&statusBiayaTambahan==true){
                     JOptionPane.showMessageDialog(menuUtama,"Tambahan Biaya Sebesar Rp."+tambahBiaya);
                }
                else if(statusDiskon==true&&statusBiayaTambahan==true){
                    JOptionPane.showMessageDialog(menuUtama,"Tambahan Biaya Sebesar Rp."+tambahBiaya+"\nPelanggan Mendapat Diskon Sebesar Rp."+disc);
                }
                
                
                menuUtama.getTxtTotalBayar().setText(TotalBayar.toString());
    }
    void Listener(){
        //Pencarian
        menuUtama.getTxtIDPemesan().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    rs=model.new getDataUser().getByID(Integer.parseInt(menuUtama.getTxtIDPemesan().getText()));
                    while(rs.next()){
                        menuUtama.getTxtNamaPemesan().setText(rs.getString("nama"));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(TransaksiController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        //Menambahkan fungsi ketika Text kode pelanggan di inputkan maka akan mencari data dari model getByID
        //Untuk mendapatkan data nama dan kontak
        //setelah itu di set ke dalam Text Nama dan Text Kontak
        //getTxtKode().addActionListener(new ActionListener();
        menuUtama.getTxtKode().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rs=model.new getDataUser().getByID(Integer.parseInt(menuUtama.getTxtKode().getText()));
                try {
                    while(rs.next()){
                        menuUtama.getTxtDaftarNama().setText(rs.getString("nama"));
                        menuUtama.getTxtContact().setText(rs.getString("kontak"));
                    }   
                    
                } catch (SQLException ex) {
                    Logger.getLogger(TransaksiController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
         //Menambahkan fungsi ketika Text kode pelanggan di inputkan maka akan mencari data dari model getTxtDaftarNama
        //Untuk mendapatkan data id dan kontak
        //setelah itu di set ke dalam Text id dan Text Kontak
        menuUtama.getTxtDaftarNama().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rs=model.new getDataUser().getByName(menuUtama.getTxtDaftarNama().getText());
                try {
                    while(rs.next()){
                    menuUtama.getTxtKode().setText(rs.getString("id"));
                    menuUtama.getTxtContact().setText(rs.getString("kontak"));
                    }
                    
                } catch (SQLException ex) {
                    Logger.getLogger(TransaksiController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        menuUtama.getTxtContact().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rs=model.new getDataUser().getByPhone(menuUtama.getTxtContact().getText());
                try {
                    while(rs.next()){
                    menuUtama.getTxtKode().setText(rs.getString("id"));
                    menuUtama.getTxtDaftarNama().setText(rs.getString("nama"));
                    }
                    
                } catch (SQLException ex) {
                    Logger.getLogger(TransaksiController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        menuUtama.getBtnTambahKeDataPemesanan().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuUtama.getTxtNamaPemesan().setText(menuUtama.getTxtDaftarNama().getText());
                menuUtama.getTxtIDPemesan().setText(menuUtama.getTxtKode().getText());
            }
        });
        menuUtama.getBtnClearCariPelanggan().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearCariPelanggan();
            }
        });
        menuUtama.getBtnClearDataPemesan().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearDataPelanggan();
            }
        });
        //Menu Transaksis
        menuUtama.getTxtIDPelanggan().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               rs=model.new getDataUser().getByName(menuUtama.getTxtIDPelanggan().getText());
               try {
                    while(rs.next()){
                        menuUtama.getTxtNamaPemesan().setText(rs.getString("nama"));
                    }   
                    
                } catch (SQLException ex) {
                    Logger.getLogger(TransaksiController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        menuUtama.getComboJenisLaundry().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearComboPaket();
                getDataPaket();
            }
        });
        menuUtama.getComboPaketLoundry().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearComboItem();
                getDataItem();
            }
        });
        menuUtama.getTxtUkuran().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                try {
                    Double sum=0.0;
                    rs=model.new getDataCombo().getDataLoundry(menuUtama.getComboJenisLaundry().getSelectedItem().toString());
                    while(rs.next()){
                        if(rs.getString("item").equals(menuUtama.getComboItem().getSelectedItem().toString())){
                            sum=Double.parseDouble(menuUtama.getTxtUkuran().getValue().toString())*rs.getDouble("harga");
                            break;
                        }
                    }
                    menuUtama.getHarga().setText(sum.toString());
                } catch (SQLException ex) {
                    Logger.getLogger(TransaksiController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        menuUtama.getBtnTambahKeDaftar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.new Transaksi().tambahKeJTable(menuUtama);
                clearDataInputCucian();
                TBTransaksi.addListSelectionListener(listTB_Item);
                getTotalBayar();
            }
        });
        menuUtama.getBtnClearInput().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearDataInputCucian();
            }
        });
        menuUtama.getBtnHapusDaftar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeDataTable();
                getTotalBayar();
                JOptionPane.showMessageDialog(menuUtama,"Data Sukses di Hapus");
            }
        });
        menuUtama.getBtnUbahDaftar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setValueKeTable();
                row=0;
                getTotalBayar();
                clearDataInputCucian();
                JOptionPane.showMessageDialog(menuUtama,"Data Sukses di Ubah");
            }
        });
        menuUtama.getTxtTotalBayar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hitungTotalBayar();
            }
        });
        menuUtama.getBtnClearDataCucian().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearDataCucian();
                JOptionPane.showMessageDialog(menuUtama,"Data Sukses di Hapus");
            }
        });
        menuUtama.getBtnSimpan().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.new Transaksi().tambahKeDataTransaksiPencucian(menuUtama,disc);
                JOptionPane.showMessageDialog(menuUtama, "Sukses Disimpan");
                model.new Transaksi().cetakNota(disc,tambahBiaya);
            }
        });
        menuUtama.getBtnHitung().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hitungTotalBayar();
                menuUtama.getTxtKembalian().setText(model.new Transaksi().hitungKembalian(menuUtama).toString());
            }
        });
        menuUtama.getTxtUangYangDiBayar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuUtama.getTxtKembalian().setText(model.new Transaksi().hitungKembalian(menuUtama).toString());
            }
        });
        
    }
}

