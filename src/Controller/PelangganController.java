/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import View.ManagementPelanggan;
import Model.PelangganModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
/**
 *
 * @author REM
 */
public class PelangganController {

    ManagementPelanggan view;
    PelangganModel model;
    DefaultTableModel modelTBPelanggan;
    ListSelectionModel TBPelanggan;
    public PelangganController(ManagementPelanggan view) {
        this.view=view;
        view.getTgl_Daftar().setDate(new Date());
        model = new PelangganModel();
        model.tambahKeTable(view);
        view.getTabbleSemuaPelanggan().setAutoCreateRowSorter(true);
        modelTBPelanggan=(DefaultTableModel) view.getTabbleSemuaPelanggan().getModel();
        TBPelanggan=view.getTabbleSemuaPelanggan().getSelectionModel();
        ActionListener();
    }
    void filterTBPelanggan(String value){
        TableRowSorter<DefaultTableModel> TRSorter = new TableRowSorter<DefaultTableModel>(modelTBPelanggan);
        view.getTabbleSemuaPelanggan().setRowSorter(TRSorter);
        TRSorter.setRowFilter(RowFilter.regexFilter(value));
    }
    KeyListener listFilterTBPelanggan=new KeyAdapter() {
        @Override
        public void keyReleased(KeyEvent e) {
            String value = view.getTxtFilterTBPelanggan().getText();
            System.out.println(value);
            filterTBPelanggan(value);
        }
    };
    ListSelectionListener listTBPelanggan=new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            int row = TBPelanggan.getMinSelectionIndex();
            view.getTxtID_Pelanggan().setText(modelTBPelanggan.getValueAt(row, 1).toString());
            view.getTxtNama().setText(modelTBPelanggan.getValueAt(row, 2).toString());
            view.getTxtContact().setText(modelTBPelanggan.getValueAt(row, 4).toString());
            view.getTxtAlamat().setText(modelTBPelanggan.getValueAt(row, 3).toString());
        }
    };
    void revoke(){
        modelTBPelanggan.getDataVector().removeAllElements();
        TBPelanggan.removeListSelectionListener(listTBPelanggan);
        view.getTxtFilterTBPelanggan().removeKeyListener(listFilterTBPelanggan);
        model.tambahKeTable(view);
        view.getTxtFilterTBPelanggan().addKeyListener(listFilterTBPelanggan);
        TBPelanggan.addListSelectionListener(listTBPelanggan);
    }
    
    void ActionListener(){
        view.getTxtFilterTBPelanggan().addKeyListener(listFilterTBPelanggan);
        TBPelanggan.addListSelectionListener(listTBPelanggan);
        view.getBtnTambahPelanggan().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.tambahDataPelanggan(view);
                revoke();
                JOptionPane.showMessageDialog(view,"Data Sukses Tersimpan");
            }
        });
        view.getBtnUbahPelanggan().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setDataPelanggan(view);
                revoke();
                JOptionPane.showMessageDialog(view,"Data Berhasil Di Simpan !");
            }
        });
        view.getBtnHapusPelanggan().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer value = Integer.parseInt(view.getTxtID_Pelanggan().getText());
                int x=JOptionPane.showConfirmDialog(view, "Anda yakin menghapus user dengan ID ="+value+" ?");
                if(x==JOptionPane.YES_NO_OPTION){
                    model.hapusDataPelanggan(value);
                    revoke();
                    JOptionPane.showMessageDialog(view,"Data Pelanggan dengan ID="+value+"Berhasil Di Hapus !");
                }
            }
        });
        view.getBtnClear().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.getTxtID_Pelanggan().setText("");
                view.getTxtNama().setText("");
                view.getTxtContact().setText("");
                view.getTxtAlamat().setText("");
                view.getTgl_Daftar().setDate(new Date());
            }
        });
        
    }
    
}
