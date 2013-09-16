/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prosjekt.database.repository;

import org.prosjekt.database.AbstractProperties;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.prosjekt.helperclasses.Farmer;
import org.prosjekt.helperclasses.IFarmer;

/**
 *
 * @author Christoffer <christofferbuvik@gmail.com>
 */

public class FarmerRepository extends AbstractProperties {

    public FarmerRepository() {
    }

    public void printAllTables(Connection conn) throws SQLException {
        DatabaseMetaData dbmd = conn.getMetaData();
        String[] types = {"TABLE"};
        ResultSet rs = dbmd.getTables(null, null, "%", types);
        while (rs.next()) {
            System.out.println(rs.getString("TABLE_NAME"));
        }
    }

    public IFarmer getFarmer(int id) throws SQLException {
//        String selectTableSQL = "SELECT * FROM sheep";
        Connection conn = DriverManager.getConnection(getUrl(), getUser(), getPasswd());
        String selectTableSQL = "SELECT * FROM farmer where id=1";
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(selectTableSQL);
//        System.out.println(rs.);
        String firstname = null;
        while (rs.next()) {
            firstname = rs.getString("firstname");
        }
        Farmer farmer = new Farmer(id);
        farmer.setFirstName(firstname);
        System.out.println(farmer);
        return farmer;

    }

    public void m() {
//        Connection conn = DriverManager.getConnection(getUrl(), getUser(), getPasswd());
//        conn.setAutoCommit(false);//commit trasaction manually
//
//        String insertTableSQL = "INSERT INTO DBUSER"
//                + "(USER_ID, USERNAME, CREATED_BY, CREATED_DATE) VALUES"
//                + "(?,?,?,?)";
//        PreparedStatement = conn.prepareStatement(insertTableSQL);
//
//        preparedStatement.setInt(1, 101);
//        preparedStatement.setString(2, "mkyong101");
//        preparedStatement.setString(3, "system");
//        preparedStatement.setTimestamp(4, getCurrentTimeStamp());
//        preparedStatement.addBatch();
//
//        preparedStatement.setInt(1, 102);
//        preparedStatement.setString(2, "mkyong102");
//        preparedStatement.setString(3, "system");
//        preparedStatement.setTimestamp(4, getCurrentTimeStamp());
//        preparedStatement.addBatch();
//        preparedStatement.executeBatch();
//
//        dbConnection.commit();
    }

    public void createFarmer(Connection con){  
        Statement statement = null;
        try {
            String insertTableSQL = "INSERT INTO farmer"
                    + "(id, firstname, lastname) " + "VALUES"
                    + "(1,'Ola','Nordmann')";
            statement = con.createStatement();
            statement.executeQuery(insertTableSQL);
        } catch (SQLException ex) {
            Logger.getLogger(FarmerRepository.class.getName()).log(Level.SEVERE, "Failed to create farmer. Farmer might exists.", ex);
        }
    }
}
