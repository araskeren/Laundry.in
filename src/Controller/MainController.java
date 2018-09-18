/*
 * Sakurasou Digital Studio
 * Each line should be prefixed with  * 
 */
package Controller;

import View.MenuUtama;
import View.ManagementOrder;
import View.ManagementPelanggan;
import View.Laporan;
import View.Pengaturan;
import View.Monitoring;
import Model.PDF;
import Model.Config;
import Model.LoginModel;
import View.Login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MainController {
    MenuUtama transaksiView;
    ManagementOrder orderView;
    ManagementPelanggan pelangganView;
    Laporan laporanView;
    Pengaturan pengaturanView;
    Monitoring monitoringView;
    
    Config conf = new Config();
    
    TransaksiController transaksiControl;
    ManagementController managementControl;
    PelangganController pelangganControl;
    LaporanController laporanControl;
    PengaturanController pengaturanControl;
    MonitoringController monitoringControl;
    LoginModel Login;
    String ID_Log;
    public MainController() {
        Login = new LoginModel();
        ID_Log=Login.getIDLog();
        
    }
public void addMenuAdmin(){
          transaksiView = new MenuUtama();
          transaksiControl = new TransaksiController(transaksiView);

          orderView = new ManagementOrder();
          managementControl = new ManagementController(orderView);

          pelangganView = new ManagementPelanggan();
          pelangganControl = new PelangganController(pelangganView);

          laporanView = new Laporan();
          laporanControl = new LaporanController(laporanView);

          pengaturanView = new Pengaturan();
          pengaturanControl = new PengaturanController(pengaturanView);

          monitoringView = new Monitoring();
          monitoringControl = new MonitoringController(monitoringView);
          
          transaksiView.setVisible(true);
          allListener();
          adminListener();
          new sideBarAdmin().addSideBar();
}
public void addMenuPegawai(){
          transaksiView = new MenuUtama();
          transaksiControl = new TransaksiController(transaksiView);

          orderView = new ManagementOrder();
          managementControl = new ManagementController(orderView);

          pelangganView = new ManagementPelanggan();
          pelangganControl = new PelangganController(pelangganView);

          laporanView = new Laporan();
          laporanControl = new LaporanController(laporanView);
          
          transaksiView.setVisible(true);
          allListener();
          new sideBarPegawai().addSideBarPegawai();
}
void allListener(){
    transaksiView.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    transaksiView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we)
            { 
                String ObjButtons[] = {"Iya","Tidak"};
                int PromptResult = JOptionPane.showOptionDialog(null,"Apakah Anda Yakin Ingin Keluar Program?","Laundry Management System",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
                if(PromptResult==JOptionPane.YES_OPTION)
                {
                    Login.setLogoutTime(ID_Log);
                    System.exit(0);
                }
            }
        });
    orderView.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    orderView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we)
            { 
                String ObjButtons[] = {"Iya","Tidak"};
                int PromptResult = JOptionPane.showOptionDialog(null,"Apakah Anda Yakin Ingin Keluar Program?","Laundry Management System",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
                if(PromptResult==JOptionPane.YES_OPTION)
                {
                    Login.setLogoutTime(ID_Log);
                    System.exit(0);
                }
            }
        });
    pelangganView.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    pelangganView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we)
            { 
                String ObjButtons[] = {"Iya","Tidak"};
                int PromptResult = JOptionPane.showOptionDialog(null,"Apakah Anda Yakin Ingin Keluar Program?","Laundry Management System",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
                if(PromptResult==JOptionPane.YES_OPTION)
                {
                    Login.setLogoutTime(ID_Log);
                    System.exit(0);
                }
            }
        });
    laporanView.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    laporanView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we)
            { 
                String ObjButtons[] = {"Iya","Tidak"};
                int PromptResult = JOptionPane.showOptionDialog(null,"Apakah Anda Yakin Ingin Keluar Program?","Laundry Management System",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
                if(PromptResult==JOptionPane.YES_OPTION)
                {
                    Login.setLogoutTime(ID_Log);
                    System.exit(0);
                }
            }
        });
}
void adminListener(){
    monitoringView.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    monitoringView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we)
            { 
                String ObjButtons[] = {"Iya","Tidak"};
                int PromptResult = JOptionPane.showOptionDialog(null,"Apakah Anda Yakin Ingin Keluar Program?","Laundry Management System",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
                if(PromptResult==JOptionPane.YES_OPTION)
                {
                    Login.setLogoutTime(ID_Log);
                    System.exit(0);
                }
            }
        });
    pengaturanView.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    pengaturanView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we)
            { 
                String ObjButtons[] = {"Iya","Tidak"};
                int PromptResult = JOptionPane.showOptionDialog(null,"Apakah Anda Yakin Ingin Keluar Program?","Laundry Management System",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
                if(PromptResult==JOptionPane.YES_OPTION)
                {
                    Login.setLogoutTime(ID_Log);
                    System.exit(0);
                }
            }
        });
}
class sideBarAdmin{
    public void addSideBar(){
        String nama=conf.getNamaLaundry();
        String alamat="<html><p>"+conf.getAlamatLaundry()+"</p><br><p>Kontak : "+conf.getKontakLaundry()+"</p></html>";
        mainSide(nama, alamat);
        pelangganSide(nama, alamat);
        orderSide(nama, alamat);
        laporanSide(nama, alamat);
        monitoringSide(nama, alamat);
        pengaturanSide(nama, alamat);
    }
    ActionListener listMenu(JFrame view){
        ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.setVisible(false);
            transaksiView.setVisible(true);
        }
        };
        return listener;
    }
    ActionListener listOrder(JFrame view){
        ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.setVisible(false);
            orderView.setVisible(true);
        }
        };
        return listener;
    }
    ActionListener listPelanggan(JFrame view){
        ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.setVisible(false);
            pelangganView.setVisible(true);
        }
        };
        return listener;
    }
    ActionListener listLaporan(JFrame view){
        ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.setVisible(false);
            laporanView.setVisible(true);
        }
        };
        return listener;
    }
    ActionListener listMonitoring(JFrame view){
        ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.setVisible(false);
            monitoringView.setVisible(true);
        }
        };
        return listener;
    }
    ActionListener listPengaturan(JFrame view){
        ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.setVisible(false);
            pengaturanView.setVisible(true);
        }
        };
        return listener;
    }
    
    void mainSide(String nama,String alamat){
        transaksiView.getTxtJudulLaundry().setText(nama);
        transaksiView.getTxtAlamatLaundry().setText(alamat);
        transaksiView.getBtnManajemenOrder().addActionListener(listOrder(transaksiView));
        transaksiView.getBtnManajemenPelanggan().addActionListener(listPelanggan(transaksiView));
        transaksiView.getBtnLaporan().addActionListener(listLaporan(transaksiView));
        transaksiView.getBtnMonitoring().addActionListener(listMonitoring(transaksiView));
        transaksiView.getBtnPengaturan().addActionListener(listPengaturan(transaksiView));        
    }
    void pelangganSide(String nama,String alamat){
        pelangganView.getTxtJudulLaundry().setText(nama);
        pelangganView.getTxtAlamatLaundry().setText(alamat);
        pelangganView.getBtnManajemenOrder().addActionListener(listOrder(pelangganView));
        pelangganView.getBtnMenuUtama().addActionListener(listMenu(pelangganView));
        pelangganView.getBtnLaporan().addActionListener(listLaporan(pelangganView));
        pelangganView.getBtnMonitoring().addActionListener(listMonitoring(pelangganView));
        pelangganView.getBtnPengaturan().addActionListener(listPengaturan(pelangganView));        
    }
    void orderSide(String nama,String alamat){
        orderView.getTxtJudulLaundry().setText(nama);
        orderView.getTxtAlamatLaundry().setText(alamat);
        orderView.getBtnMenuUtama().addActionListener(listMenu(orderView));
        orderView.getBtnManajemenPelanggan().addActionListener(listPelanggan(orderView));
        orderView.getBtnLaporan().addActionListener(listLaporan(orderView));
        orderView.getBtnMonitoring().addActionListener(listMonitoring(orderView));
        orderView.getBtnPengaturan().addActionListener(listPengaturan(orderView));        
    }
    void laporanSide(String nama,String alamat){
        laporanView.getTxtJudulLaundry().setText(nama);
        laporanView.getTxtAlamatLaundry().setText(alamat);
        laporanView.getBtnManajemenOrder().addActionListener(listOrder(laporanView));
        laporanView.getBtnManajemenPelanggan().addActionListener(listPelanggan(laporanView));
        laporanView.getBtnMenuUtama().addActionListener(listMenu(laporanView));
        laporanView.getBtnMonitoring().addActionListener(listMonitoring(laporanView));
        laporanView.getBtnPengaturan().addActionListener(listPengaturan(laporanView));        
    }
    void monitoringSide(String nama,String alamat){
        monitoringView.getTxtJudulLaundry().setText(nama);
        monitoringView.getTxtAlamatLaundry().setText(alamat);
        monitoringView.getBtnManajemenOrder().addActionListener(listOrder(monitoringView));
        monitoringView.getBtnManajemenPelanggan().addActionListener(listPelanggan(monitoringView));
        monitoringView.getBtnLaporan().addActionListener(listLaporan(monitoringView));
        monitoringView.getBtnMenuUtama().addActionListener(listMenu(monitoringView));
        monitoringView.getBtnPengaturan().addActionListener(listPengaturan(monitoringView));        
    }
    void pengaturanSide(String nama,String alamat){
        pengaturanView.getTxtJudulLaundry().setText(nama);
        pengaturanView.getTxtAlamatLaundry().setText(alamat);
        pengaturanView.getBtnManajemenOrder().addActionListener(listOrder(pengaturanView));
        pengaturanView.getBtnManajemenPelanggan().addActionListener(listPelanggan(pengaturanView));
        pengaturanView.getBtnLaporan().addActionListener(listLaporan(pengaturanView));
        pengaturanView.getBtnMonitoring().addActionListener(listMonitoring(pengaturanView));
        pengaturanView.getBtnMenuUtama().addActionListener(listMenu(pengaturanView));  
    }
}  
class sideBarPegawai{
    public void addSideBar(){
        String nama=conf.getNamaLaundry();
        String alamat="<html><p>"+conf.getAlamatLaundry()+"</p><br><p>Kontak : "+conf.getKontakLaundry()+"</p></html>";
        mainSide(nama, alamat);
        pelangganSide(nama, alamat);
        orderSide(nama, alamat);
        laporanSide(nama, alamat);
        monitoringSide(nama, alamat);
        pengaturanSide(nama, alamat);
    }
    public void addSideBarPegawai(){
        String nama=conf.getNamaLaundry();
        String alamat="<html><p>"+conf.getAlamatLaundry()+"</p><br><p>Kontak : "+conf.getKontakLaundry()+"</p></html>";
        mainSide(nama, alamat);
        pelangganSide(nama, alamat);
        orderSide(nama, alamat);
        laporanSide(nama, alamat);
    }
    ActionListener listMenu(JFrame view){
        ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.setVisible(false);
            transaksiView.setVisible(true);
        }
        };
        return listener;
    }
    ActionListener listOrder(JFrame view){
        ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.setVisible(false);
            orderView.setVisible(true);
        }
        };
        return listener;
    }
    ActionListener listPelanggan(JFrame view){
        ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.setVisible(false);
            pelangganView.setVisible(true);
        }
        };
        return listener;
    }
    ActionListener listLaporan(JFrame view){
        ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.setVisible(false);
            laporanView.setVisible(true);
        }
        };
        return listener;
    }
    ActionListener listMonitoring(JFrame view){
        ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null,"Hanya Admin yang boleh mengakses menu ini !");
        }
        };
        return listener;
    }
    ActionListener listPengaturan(JFrame view){
        ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null,"Hanya Admin yang boleh mengakses menu ini !");
        }
        };
        return listener;
    }
    
    void mainSide(String nama,String alamat){
        transaksiView.getTxtJudulLaundry().setText(nama);
        transaksiView.getTxtAlamatLaundry().setText(alamat);
        transaksiView.getBtnManajemenOrder().addActionListener(listOrder(transaksiView));
        transaksiView.getBtnManajemenPelanggan().addActionListener(listPelanggan(transaksiView));
        transaksiView.getBtnLaporan().addActionListener(listLaporan(transaksiView));
        transaksiView.getBtnMonitoring().addActionListener(listMonitoring(transaksiView));
        transaksiView.getBtnPengaturan().addActionListener(listPengaturan(transaksiView));        
    }
    void pelangganSide(String nama,String alamat){
        pelangganView.getTxtJudulLaundry().setText(nama);
        pelangganView.getTxtAlamatLaundry().setText(alamat);
        pelangganView.getBtnManajemenOrder().addActionListener(listOrder(pelangganView));
        pelangganView.getBtnMenuUtama().addActionListener(listMenu(pelangganView));
        pelangganView.getBtnLaporan().addActionListener(listLaporan(pelangganView));
        pelangganView.getBtnMonitoring().addActionListener(listMonitoring(pelangganView));
        pelangganView.getBtnPengaturan().addActionListener(listPengaturan(pelangganView));        
    }
    void orderSide(String nama,String alamat){
        orderView.getTxtJudulLaundry().setText(nama);
        orderView.getTxtAlamatLaundry().setText(alamat);
        orderView.getBtnMenuUtama().addActionListener(listMenu(orderView));
        orderView.getBtnManajemenPelanggan().addActionListener(listPelanggan(orderView));
        orderView.getBtnLaporan().addActionListener(listLaporan(orderView));
        orderView.getBtnMonitoring().addActionListener(listMonitoring(orderView));
        orderView.getBtnPengaturan().addActionListener(listPengaturan(orderView));        
    }
    void laporanSide(String nama,String alamat){
        laporanView.getTxtJudulLaundry().setText(nama);
        laporanView.getTxtAlamatLaundry().setText(alamat);
        laporanView.getBtnManajemenOrder().addActionListener(listOrder(laporanView));
        laporanView.getBtnManajemenPelanggan().addActionListener(listPelanggan(laporanView));
        laporanView.getBtnMenuUtama().addActionListener(listMenu(laporanView));
        laporanView.getBtnMonitoring().addActionListener(listMonitoring(laporanView));
        laporanView.getBtnPengaturan().addActionListener(listPengaturan(laporanView));        
    }
    void monitoringSide(String nama,String alamat){
        monitoringView.getTxtJudulLaundry().setText(nama);
        monitoringView.getTxtAlamatLaundry().setText(alamat);
        monitoringView.getBtnManajemenOrder().addActionListener(listOrder(monitoringView));
        monitoringView.getBtnManajemenPelanggan().addActionListener(listPelanggan(monitoringView));
        monitoringView.getBtnLaporan().addActionListener(listLaporan(monitoringView));
        monitoringView.getBtnMenuUtama().addActionListener(listMenu(monitoringView));
        monitoringView.getBtnPengaturan().addActionListener(listPengaturan(monitoringView));        
    }
    void pengaturanSide(String nama,String alamat){
        pengaturanView.getTxtJudulLaundry().setText(nama);
        pengaturanView.getTxtAlamatLaundry().setText(alamat);
        pengaturanView.getBtnManajemenOrder().addActionListener(listOrder(pengaturanView));
        pengaturanView.getBtnManajemenPelanggan().addActionListener(listPelanggan(pengaturanView));
        pengaturanView.getBtnLaporan().addActionListener(listLaporan(pengaturanView));
        pengaturanView.getBtnMonitoring().addActionListener(listMonitoring(pengaturanView));
        pengaturanView.getBtnMenuUtama().addActionListener(listMenu(pengaturanView));  
    }
}
}
