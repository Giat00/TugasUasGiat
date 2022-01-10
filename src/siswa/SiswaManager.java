/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siswa;

import db.Koneksi;
import model.SiswaModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author GIAT
 */
public class SiswaManager {
    
    private final Connection koneksiDatabase;

    public SiswaManager() {
        this.koneksiDatabase = Koneksi.koneksiDB();
    }
    
    public List<SiswaModel> tampilSemua(){
        String namaTable = "siswa";
        String query = "SELECT * FROM "+namaTable;
        SiswaModel model;
        List<SiswaModel> list = new ArrayList<>();
        try {
            Statement preparedStatement = koneksiDatabase.createStatement();
            ResultSet hasilQuery = preparedStatement.executeQuery(query);
            
            while(hasilQuery.next()){
                model = new SiswaModel();
                model.setId(hasilQuery.getInt("id"));
                model.setNama(hasilQuery.getString("nama"));
                model.setNik(hasilQuery.getString("nik"));
                model.setTelepon(hasilQuery.getString("telepon"));
                model.setAlamat(hasilQuery.getString("alamat"));
                list.add(model);
            }            
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(SiswaManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public SiswaModel byId(int id){
        String namaTable = "siswa";
        String query = "SELECT * FROM "+namaTable+" WHERE id = "+id;
        SiswaModel model = null;
        try {
            Statement preparedStatement = koneksiDatabase.createStatement();
            ResultSet hasilQuery = preparedStatement.executeQuery(query);
            
            while(hasilQuery.next()){
                model = new SiswaModel();
                model.setId(hasilQuery.getInt("id"));
                model.setNama(hasilQuery.getString("nama"));
                model.setNik(hasilQuery.getString("nik"));
                model.setTelepon(hasilQuery.getString("telepon"));
                model.setAlamat(hasilQuery.getString("alamat"));
            }            
            return model;
        } catch (SQLException ex) {
            Logger.getLogger(SiswaManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }    
    
    public boolean insert(String nama, String nik, String telepon, String alamat) {
        String namaTable = "siswa";
        String query = "INSERT INTO "+namaTable+" (`nama`, `nik`, `telepon`, `alamat`) VALUES ('"+nama+"', '"+nik+"','"+telepon+"', '"+alamat+"');";
        
        try {
            PreparedStatement preparedStatement = koneksiDatabase.prepareStatement(query);
            preparedStatement.execute();   
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SiswaManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean update(String nama, String nik, String telepon, String alamat, int id) {
        String namaTable = "siswa";
        String query = "UPDATE "+namaTable+" SET `nama`='"+nama+"', `nik`='"+nik+"', `telepon`='"+telepon+"', `alamat`='"+alamat+"'  WHERE  `id`="+id+";";
        
        try {
            PreparedStatement preparedStatement = koneksiDatabase.prepareStatement(query);
            preparedStatement.execute();   
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SiswaManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean delete(String id) {
        String namaTable = "siswa";
        String query = "DELETE FROM "+namaTable+" WHERE id = "+id;
        
        try {
            PreparedStatement preparedStatement = koneksiDatabase.prepareStatement(query);
            preparedStatement.execute();   
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SiswaManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
}
