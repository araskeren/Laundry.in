package Controller;

import View.ManagementOrder;
import Model.ManagementModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;


public class ManagementController {

    ManagementOrder view;
    ManagementModel model;
    DefaultTableModel modelTBBelumDiCuci;
    DefaultTableModel modelTBDiProses;
    DefaultTableModel modelTBBelumDiambil;
    DefaultTableModel modelTBSudahDiAmbil;
    ListSelectionModel TBBelumDiCuci;
    ListSelectionModel TBDiProses;
    ListSelectionModel TBBelumDiAmbil;
    ListSelectionModel TBSudahDiAmbil;
    public ManagementController(ManagementOrder view) {
        this.view = view;
        model=new ManagementModel();
        model.new setTab().setAllTable(view);
        modelTBBelumDiCuci=(DefaultTableModel) view.getTableBelumDiCuci().getModel();
        modelTBDiProses=(DefaultTableModel) view.getTableSedangDiProses().getModel();
        modelTBBelumDiambil=(DefaultTableModel) view.getTableBelumDiAmbil().getModel();
        modelTBSudahDiAmbil=(DefaultTableModel) view.getTableSudahDiAmbil().getModel();
        TBBelumDiCuci=view.getTableBelumDiCuci().getSelectionModel();
        TBDiProses=view.getTableSedangDiProses().getSelectionModel();
        TBBelumDiAmbil=view.getTableBelumDiAmbil().getSelectionModel();
        TBSudahDiAmbil=view.getTableSudahDiAmbil().getSelectionModel();
        ListenerTBBelumDicuci();
        ListenerTBBelumDiAmbil();
        ListenerTBSedangDiProses();
        ListenerTBSudahDiAmbil();
        
    }
    void filterTBBelumDiCuci(String value){
        TableRowSorter<DefaultTableModel> TRSorter = new TableRowSorter<DefaultTableModel>(modelTBBelumDiCuci);
        view.getTableBelumDiCuci().setRowSorter(TRSorter);
        TRSorter.setRowFilter(RowFilter.regexFilter(value));
    }
    void filterTBDiProses(String value){
        TableRowSorter<DefaultTableModel> TRSorter = new TableRowSorter<DefaultTableModel>(modelTBDiProses);
        view.getTableSedangDiProses().setRowSorter(TRSorter);
        TRSorter.setRowFilter(RowFilter.regexFilter(value));
    }
    void filterTBBelumDiAmbil(String value){
        TableRowSorter<DefaultTableModel> TRSorter = new TableRowSorter<DefaultTableModel>(modelTBBelumDiambil);
        view.getTableBelumDiAmbil().setRowSorter(TRSorter);
        TRSorter.setRowFilter(RowFilter.regexFilter(value));
    }
    void filterTBSudahDiAmbil(String value){
        TableRowSorter<DefaultTableModel> TRSorter = new TableRowSorter<DefaultTableModel>(modelTBSudahDiAmbil);
        view.getTableSudahDiAmbil().setRowSorter(TRSorter);
        TRSorter.setRowFilter(RowFilter.regexFilter(value));
    }
    KeyListener listFilterTBBelumDiCuci=new KeyAdapter() {
        @Override
        public void keyReleased(KeyEvent e) {
            String value = view.getTxtFilterTBUser().getText();
            System.out.println(value);
            //filterTB(value,modelTBBelumDiCuci,view.getTableBelumDiCuci());
            filterTBBelumDiCuci(value);
        }
    };
    KeyListener listFilterTBDiProses=new KeyAdapter() {
        @Override
        public void keyReleased(KeyEvent e) {
            String value = view.getTxtFilterTBUser1().getText();
            //filterTB(value,modelTBDiProses,view.getTableSedangDiProses());
            filterTBDiProses(value);
        }
    };
    KeyListener listFilterTBBelumDiAmbil=new KeyAdapter() {
        @Override
        public void keyReleased(KeyEvent e) {
            String value = view.getTxtFilterTBUser2().getText();
            filterTBBelumDiAmbil(value);
        }
    };
    KeyListener listFilterTBSudahDiAmbil=new KeyAdapter() {
        @Override
        public void keyReleased(KeyEvent e) {
            String value = view.getTxtFilterTBUser3().getText();
            filterTBSudahDiAmbil(value);
        }
    };
    void setSedangDiProses(Integer value){
        model.updateTRCucian("Di Proses", value);
    }
    void setBelumDiAmbil(Integer value){
        model.updateTRCucian("Belum Di Ambil", value);
    }
    void setSudahDiAmbil(Integer value){
        model.updateTRCucian("Sudah Di Ambil", value);
    }
    void clearTable(Integer param){
        model.dropTRCucian(param);
        clearAllTable();
    }
    void clearAllTable(){
        modelTBBelumDiCuci.getDataVector().removeAllElements();
        modelTBBelumDiambil.getDataVector().removeAllElements();
        modelTBDiProses.getDataVector().removeAllElements();
        modelTBSudahDiAmbil.getDataVector().removeAllElements();
        view.getTxtFilterTBUser().removeKeyListener(listFilterTBBelumDiCuci);
        view.getTxtFilterTBUser1().removeKeyListener(listFilterTBDiProses);
        view.getTxtFilterTBUser2().removeKeyListener(listFilterTBBelumDiAmbil);
        view.getTxtFilterTBUser3().removeKeyListener(listFilterTBSudahDiAmbil);
        model.new setTab().setAllTable(view);
    }
    void ListenerTBBelumDicuci(){
        view.getBtnSedangDiProses().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               int row=TBBelumDiCuci.getMinSelectionIndex();
               Integer nofaktur=Integer.parseInt(modelTBBelumDiCuci.getValueAt(row, 1).toString());
               int x=JOptionPane.showConfirmDialog(view, "Apakah Anda Yakin Ingin Mengubah Status Pemesanan KD Transaksi ="+nofaktur+" Ke Sedang Di Proses ?");
               if(x==JOptionPane.YES_NO_OPTION){
                   setSedangDiProses(nofaktur);
                   clearAllTable();
               }
            }
        });
        view.getBtnBelumDiAmbil().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row=TBBelumDiCuci.getMinSelectionIndex();
                Integer nofaktur=Integer.parseInt(modelTBBelumDiCuci.getValueAt(row, 1).toString());
                int x=JOptionPane.showConfirmDialog(view, "Apakah Anda Yakin Ingin Mengubah Status Pemesanan KD Transaksi ="+nofaktur+" Ke Belum Di Ambil?");
                    if(x==JOptionPane.YES_NO_OPTION){
                        setBelumDiAmbil(nofaktur);
                        clearAllTable();
                }
            }
        });
        view.getBtnSudahDiAmbil().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row=TBBelumDiCuci.getMinSelectionIndex();
                Integer nofaktur=Integer.parseInt(modelTBBelumDiCuci.getValueAt(row, 1).toString());
                int x=JOptionPane.showConfirmDialog(view, "Apakah Anda Yakin Ingin Mengubah Status Pemesanan KD Transaksi ="+nofaktur+" Ke Sudah Di Ambil ?");
                    if(x==JOptionPane.YES_NO_OPTION){
                        setSudahDiAmbil(nofaktur);
                        clearAllTable();
                }
            }
        });
        view.getBtnHapusOrder().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row=TBBelumDiCuci.getMinSelectionIndex();
                Integer nofaktur=Integer.parseInt(modelTBBelumDiCuci.getValueAt(row, 1).toString());
                int x=JOptionPane.showConfirmDialog(view, "Apakah Anda Yakin Ingin Menghapus Transaksi dengan kode ="+nofaktur+"?");
                    if(x==JOptionPane.YES_NO_OPTION){
                        clearTable(nofaktur);
                }
            }
        });
        view.getTxtFilterTBUser().addKeyListener(listFilterTBBelumDiCuci);
    }
    void ListenerTBSedangDiProses(){
        view.getBtnBelumDiAmbil1().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row=TBDiProses.getMinSelectionIndex();
                Integer nofaktur=Integer.parseInt(modelTBDiProses.getValueAt(row, 1).toString());
                int x=JOptionPane.showConfirmDialog(view, "Apakah Anda Yakin Ingin Mengubah Status Pemesanan KD Transaksi ="+nofaktur+" Ke Belum Di Ambil?");
                    if(x==JOptionPane.YES_NO_OPTION){
                        setBelumDiAmbil(nofaktur);
                        clearAllTable();
                }
            }
        });
        view.getBtnSudahDiAmbil1().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row=TBDiProses.getMinSelectionIndex();
                Integer nofaktur=Integer.parseInt(modelTBDiProses.getValueAt(row, 1).toString());
                int x=JOptionPane.showConfirmDialog(view, "Apakah Anda Yakin Ingin Mengubah Status Pemesanan KD Transaksi ="+nofaktur+" Ke Sudah Di Ambil ?");
                    if(x==JOptionPane.YES_NO_OPTION){
                        setSudahDiAmbil(nofaktur);
                        clearAllTable();
                }
            }
        });
        view.getBtnHapusOrder1().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row=TBDiProses.getMinSelectionIndex();
                Integer nofaktur=Integer.parseInt(modelTBDiProses.getValueAt(row, 1).toString());
                int x=JOptionPane.showConfirmDialog(view, "Apakah Anda Yakin Ingin Menghapus Transaksi dengan kode ="+nofaktur+"?");
                    if(x==JOptionPane.YES_NO_OPTION){
                        clearTable(nofaktur);
                }
            }
        });
        view.getTxtFilterTBUser1().addKeyListener(listFilterTBDiProses);
    }
    void ListenerTBBelumDiAmbil(){
        view.getBtnSudahDiAmbil2().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row=TBBelumDiAmbil.getMinSelectionIndex();
                Integer nofaktur=Integer.parseInt(modelTBBelumDiambil.getValueAt(row, 1).toString());
                int x=JOptionPane.showConfirmDialog(view, "Apakah Anda Yakin Ingin Mengubah Status Pemesanan KD Transaksi ="+nofaktur+" Ke Sudah Di Ambil ?");
                    if(x==JOptionPane.YES_NO_OPTION){
                        setSudahDiAmbil(nofaktur);
                        clearAllTable();
                }
            }
        });
        view.getBtnHapusOrder2().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row=TBBelumDiAmbil.getMinSelectionIndex();
                Integer nofaktur=Integer.parseInt(modelTBBelumDiambil.getValueAt(row, 1).toString());
                int x=JOptionPane.showConfirmDialog(view, "Apakah Anda Yakin Ingin Menghapus Transaksi dengan kode ="+nofaktur+"?");
                    if(x==JOptionPane.YES_NO_OPTION){
                        clearTable(nofaktur);
                }
            }
        });
        view.getTxtFilterTBUser2().addKeyListener(listFilterTBBelumDiAmbil);
        
    }
    void ListenerTBSudahDiAmbil(){
        view.getBtnHapusOrder3().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row=TBSudahDiAmbil.getMinSelectionIndex();
                Integer nofaktur=Integer.parseInt(modelTBSudahDiAmbil.getValueAt(row, 1).toString());
                int x=JOptionPane.showConfirmDialog(view, "Apakah Anda Yakin Ingin Menghapus Transaksi dengan kode ="+nofaktur+"?");
                    if(x==JOptionPane.YES_NO_OPTION){
                        clearTable(nofaktur);
                }
            }
        });
        view.getTxtFilterTBUser3().addKeyListener(listFilterTBSudahDiAmbil);
    }
}
