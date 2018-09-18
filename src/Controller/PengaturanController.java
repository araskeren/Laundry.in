/*
 * Sakurasou Digital Studio
 * Each line should be prefixed with  * 
 */
package Controller;
import View.Pengaturan;
import Model.PengaturanModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class PengaturanController {
    Pengaturan view;
    PengaturanModel model;
    DefaultTableModel modelTB_Item;
    DefaultTableModel modelTB_User;
    ListSelectionModel TB_User;
    ListSelectionModel TB_Item;
    ResultSet rs = null;
    public PengaturanController(Pengaturan VIEW) {
        this.view = VIEW;
        model=new PengaturanModel();
        modelTB_Item = (DefaultTableModel) view.getTb_Item().getModel();
        TB_User = view.getTb_User().getSelectionModel();
        modelTB_User = (DefaultTableModel) view.getTb_User().getModel();
        TB_Item = view.getTb_Item().getSelectionModel();
        AddListener();
        AddDataItemKeTabel();
        AddDataUserKeTabel();
        model.setAllConfToView(view);
    }
    ListSelectionListener listTB_Item=new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            int row = TB_Item.getMinSelectionIndex();
            view.getTxtIDItem().setText(modelTB_Item.getValueAt(row, 1)+"");
            view.getComboJenisLaundry().setSelectedItem(modelTB_Item.getValueAt(row, 2)+"");
            view.getComboTipeLaundry().setSelectedItem(modelTB_Item.getValueAt(row, 3)+"");
            view.getTxtNamaItem().setText(modelTB_Item.getValueAt(row, 4)+"");
            view.getTxtHarga().setText(modelTB_Item.getValueAt(row, 5)+"");
        }
    };
    ListSelectionListener listTB_User=new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            int row = TB_User.getMinSelectionIndex();
            view.getTxtIDUser().setText(modelTB_User.getValueAt(row, 1)+"");
            view.getTxtUsername().setText(modelTB_User.getValueAt(row, 2)+"");
            view.getComboLevel().setSelectedItem(modelTB_User.getValueAt(row, 3));
            view.getComboShift().setSelectedItem(modelTB_User.getValueAt(row, 4));
        }
    };
    KeyListener listFilterTBItem=new KeyAdapter() {
        @Override
        public void keyReleased(KeyEvent e) {
            String value = view.getTxtFilterTBItem().getText();
            filterTBItem(value);
        }
    };
    KeyListener listFilterTBUser=new KeyAdapter() {
        @Override
        public void keyReleased(KeyEvent e) {
            String value = view.getTxtFilterTBUser().getText();
            filterTBUser(value);
        }
    };
    void AddDataItemKeTabel(){
        rs=model.getDataJenisLaundry();
        try {
            while(rs.next()){
                modelTB_Item.addRow(new Object[]{
                    modelTB_Item.getRowCount()+1,
                    rs.getInt("id"),
                    rs.getString("jenis_laundry"),
                    rs.getString("tipe_laundry"),
                    rs.getString("item"),
                    rs.getInt("harga")
                    
                });
            }
            TB_Item.addListSelectionListener(listTB_Item);
            view.getTxtFilterTBItem().addKeyListener(listFilterTBItem);
        } catch (SQLException ex) {
            Logger.getLogger(PengaturanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void AddDataUserKeTabel(){
        rs=model.getDataUser();
        try {
            while(rs.next()){
                modelTB_User.addRow(new Object[]{
                    modelTB_User.getRowCount()+1,
                    rs.getInt("id_user"),
                    rs.getString("nama"),
                    rs.getString("level"),
                    rs.getString("shift"),
                    rs.getString("create_date")
                });
            }
            TB_User.addListSelectionListener(listTB_User);
            view.getTxtFilterTBUser().addKeyListener(listFilterTBUser);
        } catch (SQLException ex) {
            Logger.getLogger(PengaturanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void clearTBItem(){
        for (int i = modelTB_Item.getRowCount()-1;i>=0; i--) {
            modelTB_Item.removeRow(i);
        }
        modelTB_Item.setRowCount(0);
    }
    void clearTBUser(){
        for (int i = modelTB_User.getRowCount()-1; i>=0;i--) {
            modelTB_User.removeRow(i);
        }
        modelTB_User.setRowCount(0);
    }
    void clearInputItem(){
        view.getTxtIDItem().setText("");
        view.getTxtNamaItem().setText("");
        view.getTxtHarga().setText("");
        view.getComboJenisLaundry().setSelectedIndex(0);
        view.getComboTipeLaundry().setSelectedIndex(0);
    }
    void clearInputUser(){
        view.getTxtIDUser().setText("");
        view.getTxtNamaUser().setText("");
        view.getTxtPassword().setText("");
        view.getComboShift().setSelectedIndex(0);
        view.getComboLevel().setSelectedIndex(0);
    }
    void revokeTBItem(){
        clearInputItem();
        modelTB_Item.getDataVector().removeAllElements();
        TB_Item.removeListSelectionListener(listTB_Item);
        view.getTb_Item().setRowSorter(null);
        view.getTxtFilterTBItem().removeKeyListener(listFilterTBItem);
        AddDataItemKeTabel();
        JOptionPane.showMessageDialog(view,"Data Berhasil disimpan");
    }
    void revokeTBUser(){
        clearInputUser();
        modelTB_User.getDataVector().removeAllElements();
        TB_User.removeListSelectionListener(listTB_User);
        view.getTb_User().setRowSorter(null);
        view.getTxtFilterTBUser().removeKeyListener(listFilterTBUser);
        AddDataUserKeTabel();
        JOptionPane.showMessageDialog(view,"Data Berhasil disimpan");
    }
    void filterTBItem(String value){
        TableRowSorter<DefaultTableModel> TRSorter = new TableRowSorter<DefaultTableModel>(modelTB_Item);
        
        view.getTb_Item().setRowSorter(TRSorter);
        TRSorter.setRowFilter(RowFilter.regexFilter(value));
    }
    void filterTBUser(String value){
        TableRowSorter<DefaultTableModel> TRSorter = new TableRowSorter<DefaultTableModel>(modelTB_User);
        view.getTb_User().setRowSorter(TRSorter);
        TRSorter.setRowFilter(RowFilter.regexFilter(value));
    }
    
    void AddListener(){
        view.getBtnTambahKeDaftarItem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.addItem(view);
                revokeTBItem();
                
            }
        });
        view.getBtnTambahKeDaftarUser().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.addUser(view);
                revokeTBUser();
                
            }
        });
        view.getBtnUbahItem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setItem(view);
                revokeTBItem();
            }
        });
        view.getBtnUbahUser().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setUser(view);
                revokeTBUser();
            }
        });
        view.getBtnHapusItem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int hasil=JOptionPane.showConfirmDialog(view, "Apakah Anda Yakin ingin menghapus Item "+view.getTxtNamaItem().getText());
                if(hasil==JOptionPane.YES_OPTION){
                    model.dropItem(Integer.parseInt(view.getTxtIDItem().getText()));
                    revokeTBItem();
                }
            }
        });
        view.getBtnHapusUser().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int hasil=JOptionPane.showConfirmDialog(view, "Apakah Anda Yakin ingin menghapus User "+view.getTxtNamaUser().getText()+" Sebagai "+view.getComboLevel().getSelectedItem().toString());
                if(hasil==JOptionPane.YES_OPTION){
                    model.dropUser(Integer.parseInt(view.getTxtIDUser().getText()));
                    revokeTBUser();
                }
            }
        });
        view.getBtnClearItem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearInputItem();
            }
        });
        view.getBtnClearUser().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.getTxtIDUser().setText("");
                view.getTxtNamaUser().setText("");
                view.getTxtPasswordUser().setText("");
                view.getComboLevel().setSelectedIndex(0);
                view.getComboShift().setSelectedIndex(0);
            }
        });
        //Pengaturan Aplikasi
        view.getBtnSimpanKoneksi().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               model.setConfKoneksi(view);
            }
        });
        view.getBtnSimpanTentangLaundry().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setConfLaundry(view);
            }
        });
    }
        
}
