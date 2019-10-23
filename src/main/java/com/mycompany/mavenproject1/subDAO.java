/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import simplejdbc.*;

/**
 *
 * @author pedago
 */
public class subDAO extends simplejdbc.DAO{
    
    DataSource myDataSource;
    
    public subDAO(DataSource dataSource) {
        super(dataSource);
        myDataSource = dataSource;
    }
    
    public List<String> getAllStates(){
        List<String> states = new LinkedList<String>();
        String sql = "SELECT DISTINCT STATE FROM CUSTOMER";
        try(Connection co = myDataSource.getConnection();
            Statement stm = co.createStatement();
            ResultSet rs = stm.executeQuery(sql);){
            while(rs.next()){
                states.add(rs.getString(1));
            }
        return states;
        }catch (SQLException ex) {
            Logger.getLogger(subDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return states;
    }
}
