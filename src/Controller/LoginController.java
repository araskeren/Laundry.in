/*
 * Sakurasou Digital Studio
 * Each line should be prefixed with  * 
 */
package Controller;

import Model.LoginModel;
import View.Login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class LoginController {
    LoginModel model;
    Login view;
    MainController main;
    ResultSet rs;
    String CurrentStatus=null;
    public LoginController(Login View) {
        this.view = View;
        model=new LoginModel();
        listener();
        main=new MainController();
        
    }
    
    void listener(){
       view.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
       view.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we)
            { 
                String ObjButtons[] = {"Iya","Tidak"};
                int PromptResult = JOptionPane.showOptionDialog(null,"Apakah Anda Yakin Ingin Keluar Program?","Laundry Management System",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
                if(PromptResult==JOptionPane.YES_OPTION)
                {
                    System.exit(0);
                }
            }
        });
        view.getBtnLogin().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean cekLogin=false;
                try {
                    rs=model.getDataUser();
                    while(rs.next()){
                        if(rs.getString("nama").equals(view.getTxtUsername().getText())&&rs.getString("password").equals(view.getTxtPassword().getText())){
                            cekLogin=true;
                            model.setLoginTime(Integer.parseInt(rs.getString("ID_User")));
                            
                        }
                        if(cekLogin==true){
                            view.setVisible(false);
                            if(rs.getString("level").equals("Admin")){
                                JOptionPane.showMessageDialog(view,"Selamat Datang Admin ,"+rs.getString("nama"));
                                main.addMenuAdmin();
                                break;
                            }else if(rs.getString("level").equals("Trial")){
                                JOptionPane.showMessageDialog(view,"Selamat Datang Trial ,"+rs.getString("nama"));
                                main.addMenuPegawai();
                                break;
                            }else if(rs.getString("level").equals("Pegawai")){
                                JOptionPane.showMessageDialog(view,"Selamat Datang Pegawai ,"+rs.getString("nama"));
                                main.addMenuPegawai();
                                break;
                            }
                            
                        }
                    }
                    if(cekLogin==false){
                        JOptionPane.showMessageDialog(view,"Username Atau Password Salah !");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
