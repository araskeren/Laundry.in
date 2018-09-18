/*
 * Sakurasou Digital Studio
 * Each line should be prefixed with  * 
 */
package Model;
import Model.Koneksi;
import Model.Config;
import View.Pengaturan;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author REM
 */
public class PengaturanModel {
    Connection DB;
    Config conf;
    Statement stmt = null;
    public String sql = null;
    ResultSet rs = null;
    public PengaturanModel() {
        DB = new Koneksi().getMysqlConnection();
        conf=new Config();
    }
    void executeUpdate(){
        try {
            stmt=DB.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(PengaturanModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void executeQuery(){
        try {
            stmt = DB.createStatement();
            rs=stmt.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(PengaturanModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ResultSet getDataJenisLaundry(){
        sql="SELECT * FROM `tb_jenislaundry`";
        executeQuery();
        return rs;
    }
    public ResultSet getDataUser(){
        sql="SELECT * FROM `tb_user`";
        executeQuery();
        return rs;
    }
    public void addItem(Pengaturan view){
        sql="INSERT INTO `tb_jenislaundry` (`id`, `jenis_laundry`, `tipe_laundry`, `item`, `harga`) VALUES (NULL,'"
                +view.getComboJenisLaundry().getSelectedItem().toString()+"','"
                +view.getComboTipeLaundry().getSelectedItem().toString()+"','"
                +view.getTxtNamaItem().getText()+"','"
                +Integer.parseInt(view.getTxtHarga().getText())+"')"
                ;
        executeUpdate();
    }
    public void addUser(Pengaturan view){
        sql="INSERT INTO `tb_user`(`id_user`,`nama`,`password`, `level`, `shift`,`create_date`) VALUES (NULL,'"
                +view.getTxtNamaUser().getText()+"','"
                +view.getTxtPasswordUser().getText()+"','"
                +view.getComboLevel().getSelectedItem().toString()+"','"
                +view.getComboShift().getSelectedItem().toString()+"',CURRENT_TIMESTAMP)";
        executeUpdate();
    }
    public void setItem(Pengaturan view){
        sql="UPDATE `tb_jenislaundry` SET `jenis_laundry` = '"
                + view.getComboJenisLaundry().getSelectedItem().toString()+"',`tipe_laundry` = '"
                + view.getComboTipeLaundry().getSelectedItem().toString()+"',`item`='"
                + view.getTxtNamaItem().getText()+"',`harga`='"
                + view.getTxtHarga().getText()+"' WHERE `tb_jenislaundry`.`id` = "
                + view.getTxtIDItem().getText();
        executeUpdate();
        
    }
    public void setUser(Pengaturan view){
        sql="UPDATE `tb_user` SET `id_user` = '"
                + view.getTxtIDUser().getText()+"',`nama` = '"
                + view.getTxtNamaUser().getText()+"',`password` = '"
                + view.getTxtPassword().getText()+"',`level` = '"
                + view.getComboLevel().getSelectedItem().toString()+"',`shift` = '"
                + view.getComboShift().getSelectedItem().toString()+"' WHERE `tb_user`.`id_user` = "
                + view.getTxtIDUser().getText();
        executeUpdate();
    }
    public void dropItem(int id){
        sql="DELETE FROM `tb_jenislaundry` WHERE `tb_jenislaundry`.`id` ="+id;
        executeUpdate();
    }
    public void dropUser(int id){
        sql="DELETE FROM `tb_user` WHERE `tb_user`.`id_user` ="+id;
        executeUpdate();
    }
    public void setAllConfToView(Pengaturan view){
        view.getComboTipeDB().setSelectedItem(conf.getJenisDB());
        view.getTxtAlamatIP().setText(conf.getAlamatIP());
        view.getTxtNamaDatabase().setText(conf.getNamaDB());
        view.getTxtUsernameDB().setText(conf.getUsername());
        view.getTxtPassword().setText(conf.getPassword());
        
        view.getTxtNamaLaundry().setText(conf.getNamaLaundry());
        view.getTxtAlamat().setText(conf.getAlamatLaundry());
        view.getTxtKontakLaundry().setText(conf.getKontakLaundry());
    }
    public void setConfKoneksi(Pengaturan view){
        conf.setJenisDB(view.getComboTipeDB().getSelectedItem().toString());
        conf.setNamaDB(view.getTxtNamaDatabase().getText());
        conf.setAlamatIP(view.getTxtAlamatIP().getText());
        conf.setUsername(view.getTxtUsernameDB().getText());
        conf.setPassword(view.getTxtPassword().getText());
        conf.save();
    }
    public void setConfLaundry(Pengaturan view){
        conf.setNamaLaundry(view.getTxtNamaLaundry().getText());
        conf.setKontakLaundry(view.getTxtKontakLaundry().getText());
        conf.setAlamatLaundry(view.getTxtAlamat().getText());
        conf.save();
    }
}
