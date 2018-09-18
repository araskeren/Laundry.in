    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.mysql.jdbc.Connection;
import java.sql.*;
import java.util.logging.*;
import javax.swing.JOptionPane;

/**
 *
 * @author REM
 */
public class Koneksi {
    public Connection konek;
	public Koneksi() {
		// TODO Auto-generated constructor stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Config conf=new Config();
                        String namaDB=conf.getNamaDB();
                        String username=conf.getUsername();
                        String password=conf.getPassword();
			//getConnection("jdbc:mysql://ipServer/nama_database,"username db","password db"
			konek=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/"+namaDB+"?zeroDateTimeBehavior=convertToNull",username,password);
			//menampilakan notifikasi apabila berhasil terkoneksi dengan database
			//JOptionPane.showMessageDialog(null, "Berhasil Terknoneksi dengan database");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//menampilkan notifikasi apabila gagal terkoneksi dengan database beserta pesan error
			JOptionPane.showMessageDialog(null, "Gagal Terknoneksi dengan database,"+e.getMessage());
		}
	}
	
	public void PutuskanKoneksiMYSQL() {
		// TODO Auto-generated method stub
		try {
			konek.close();
		} catch (SQLException e) {
			// TODO: handle exception
			Logger.getLogger(Koneksi.class.getName()).log(Level.SEVERE,null,e);
			//menampilkan notifikasi apabila database sudah berhasil di putus
			JOptionPane.showMessageDialog(null, "Berhasil memutuskan koneksi dengan database,"+e.getMessage());
		}
	}
	public Connection getMysqlConnection(){
		//mendapatkan koneksi database yang sedang aktif
		return konek;
		
	}
	
	PreparedStatement preparedStatement(String query){
		return null;
	}
}
