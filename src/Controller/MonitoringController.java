/*
 * Sakurasou Digital Studio
 * Each line should be prefixed with  * 
 */
package Controller;
import View.Monitoring;
import Model.MonitoringModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
public class MonitoringController {
    ResultSet rs;
    Monitoring view;
    MonitoringModel model;
    DefaultTableModel modelTBMonitoring;
    ListSelectionModel TBMonitoring;
    Integer idLog;
    public MonitoringController(Monitoring VIEW) {
        this.view=VIEW;
        model=new MonitoringModel();
        modelTBMonitoring=(DefaultTableModel) view.getTB_Monitoring().getModel();
        TBMonitoring=view.getTB_Monitoring().getSelectionModel();
        setData();
        Listener();
    }
    KeyListener listFilterTBMonitoringByUserNama = new KeyAdapter() {
        @Override
        public void keyReleased(KeyEvent e) {
            String value = view.getTxtFilterLog().getText();
            filterTBMonitoring(value);
        }
    };
    void filterTBMonitoring(String value){
        TableRowSorter<DefaultTableModel> TRSorter = new TableRowSorter<DefaultTableModel>(modelTBMonitoring);
        view.getTB_Monitoring().setRowSorter(TRSorter);
        TRSorter.setRowFilter(RowFilter.regexFilter(value));
    }
    ListSelectionListener listTBMonitoring = new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            int row = TBMonitoring.getMinSelectionIndex();
            idLog=Integer.parseInt(modelTBMonitoring.getValueAt(row, 1).toString());
        }
    };
    void setData(){
        rs=model.getData();
        try {
            while(rs.next()){
                modelTBMonitoring.addRow(new Object[]{
                    modelTBMonitoring.getRowCount()+1,
                    rs.getInt("ID_Log"),
                    rs.getInt("ID"),
                    rs.getString("Nama"),
                    rs.getString("Level"),
                    rs.getString("Tgl_Login"),
                    rs.getString("Tgl_Logout")
                });
            }
            TBMonitoring.addListSelectionListener(listTBMonitoring);
            view.getTxtFilterLog().addKeyListener(listFilterTBMonitoringByUserNama);
        } catch (SQLException ex) {
            Logger.getLogger(MonitoringController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void setDataByRange(Date tgl_Mulai,Date tgl_Selesai){
        SimpleDateFormat dt = new SimpleDateFormat("yyyyy-MM-dd");
        String tglMulai = dt.format(tgl_Mulai);
        String tglSelesai = dt.format(tgl_Selesai);
        StringBuilder tgl1 = new StringBuilder(tglMulai);
        tgl1.deleteCharAt(0);
        StringBuilder tgl2 = new StringBuilder(tglSelesai);
        tgl2.deleteCharAt(0);
        tglMulai=tgl1.toString();
        tglSelesai=tgl2.toString();
        rs=model.getDataByRange(tglMulai, tglSelesai);
        try {
            while(rs.next()){
                modelTBMonitoring.addRow(new Object[]{
                    modelTBMonitoring.getRowCount()+1,
                    rs.getInt("ID_Log"),
                    rs.getInt("ID"),
                    rs.getString("Nama"),
                    rs.getString("Level"),
                    rs.getString("Tgl_Login"),
                    rs.getString("Tgl_Logout")
                });
            }
            TBMonitoring.addListSelectionListener(listTBMonitoring);
            view.getTxtFilterLog().addKeyListener(listFilterTBMonitoringByUserNama);
        } catch (SQLException ex) {
            Logger.getLogger(MonitoringController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void setDataByMounth(Date tgl_Mulai){
        
        SimpleDateFormat bln=new SimpleDateFormat("MM");
        SimpleDateFormat thn=new SimpleDateFormat("yyyy");
        String Bln = bln.format(tgl_Mulai);
        String Thn = thn.format(tgl_Mulai);
        rs=model.getDataByMonth(Bln, Thn);
        try {
            while(rs.next()){
                modelTBMonitoring.addRow(new Object[]{
                    modelTBMonitoring.getRowCount()+1,
                    rs.getInt("ID_Log"),
                    rs.getInt("ID"),
                    rs.getString("Nama"),
                    rs.getString("Level"),
                    rs.getString("Tgl_Login"),
                    rs.getString("Tgl_Logout")
                });
            }
            TBMonitoring.addListSelectionListener(listTBMonitoring);
            view.getTxtFilterLog().addKeyListener(listFilterTBMonitoringByUserNama);
        } catch (SQLException ex) {
            Logger.getLogger(MonitoringController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void setDataByDay(Date tgl_Mulai){
        SimpleDateFormat tgl=new SimpleDateFormat("dd");
        SimpleDateFormat bln=new SimpleDateFormat("MM");
        SimpleDateFormat thn=new SimpleDateFormat("yyyy");
        String Tgl = tgl.format(tgl_Mulai);
        String Bln = bln.format(tgl_Mulai);
        String Thn = thn.format(tgl_Mulai);
        rs=model.getDataByDay(Tgl,Bln,Thn);
        try {
            while(rs.next()){
                modelTBMonitoring.addRow(new Object[]{
                    modelTBMonitoring.getRowCount()+1,
                    rs.getInt("ID_Log"),
                    rs.getInt("ID"),
                    rs.getString("Nama"),
                    rs.getString("Level"),
                    rs.getString("Tgl_Login"),
                    rs.getString("Tgl_Logout")
                });
            }
            TBMonitoring.addListSelectionListener(listTBMonitoring);
            view.getTxtFilterLog().addKeyListener(listFilterTBMonitoringByUserNama);
        } catch (SQLException ex) {
            Logger.getLogger(MonitoringController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void clearInput(){
        idLog=0;
        view.getTxtFilterLog().setText("");
        view.getTgl_Dari().setCalendar(null);
        view.getTgl_Sampai().setCalendar(null);
    }
    void revokeTBMonitoring(){
        modelTBMonitoring.getDataVector().removeAllElements();
        TBMonitoring.removeListSelectionListener(listTBMonitoring);
        view.getTxtFilterLog().removeKeyListener(listFilterTBMonitoringByUserNama);
    }
    void Listener(){
        view.getBtnClear().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               clearInput();
               revokeTBMonitoring();
               setData();
            }
        });
        view.getBtnCari().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                revokeTBMonitoring();
                if(view.getComboJenisLog().getSelectedIndex()==0){
                    JOptionPane.showMessageDialog(view,"Silahkan Pilih Jenis Log !");
                }else if(view.getComboJenisLog().getSelectedIndex()==1){
                    setDataByMounth(view.getTgl_Dari().getDate());
                }else if(view.getComboJenisLog().getSelectedIndex()==2){
                    setDataByDay(view.getTgl_Dari().getDate());
                }else if(view.getComboJenisLog().getSelectedIndex()==3){
                    setDataByRange(view.getTgl_Dari().getDate(),view.getTgl_Sampai().getDate());
                } 
               
            }
        });
        view.getComboJenisLog().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(view.getComboJenisLog().getSelectedIndex()==0){
                    view.getTgl_Dari().setEnabled(false);
                    view.getTgl_Sampai().setEnabled(false);
                }else if(view.getComboJenisLog().getSelectedIndex()==1){
                    view.getTgl_Dari().setEnabled(true);
                    view.getTgl_Sampai().setEnabled(false);
                }else if(view.getComboJenisLog().getSelectedIndex()==2){
                    view.getTgl_Dari().setEnabled(true);
                    view.getTgl_Sampai().setEnabled(false);
                }else if(view.getComboJenisLog().getSelectedIndex()==3){
                    view.getTgl_Dari().setEnabled(true);
                    view.getTgl_Sampai().setEnabled(true);
                } 
            }
        });
    }
}
