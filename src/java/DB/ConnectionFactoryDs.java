/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author CADASTRO02
 */
public class ConnectionFactoryDs implements IConnectionFactory{
    
    Context ctx;
    Connection con = null;

    @Override
    public Connection getConnection() {

        try {

            ctx = new InitialContext();

            DataSource ds
     = (DataSource) ctx.lookup("java:comp/env/jdbc/projetoBanca");
            
            con = ds.getConnection();
            
        } catch (NamingException ex) {
            Logger.getLogger(ConnectionFactoryDs.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactoryDs.class.getName()).log(Level.SEVERE, null, ex);
        }

        return con;
    }
}
