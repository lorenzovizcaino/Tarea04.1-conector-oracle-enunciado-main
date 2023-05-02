package es.teis.oracle.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import oracle.jdbc.datasource.impl.OracleDataSource;
import oracle.sql.BfileDBAccess;

public class ConnectionFactory {

    private static OracleDataSource ods = null;
    private static final String RUTA_FICHERO_CONFIG = "src/main/resources/db.properties";
    private static final String URL_KEY = "url";
    private static final String USER_KEY ="user";
    private static final String PWD_KEY ="pwd";

    public ConnectionFactory() {

    }

    public static OracleDataSource getConnection() throws SQLException {

        if (ods == null) {
            ods = new OracleDataSource();
            Properties properties = new Properties();
            try (FileInputStream fis = new FileInputStream(RUTA_FICHERO_CONFIG)) {
                properties.load(fis);
                String fichero = properties.getProperty(URL_KEY);
                String usuario = properties.getProperty(USER_KEY);
                String password = properties.getProperty(PWD_KEY);
                ods.setURL(fichero);
                ods.setUser(usuario);
                ods.setPassword(password);



            } catch (FileNotFoundException e) {
                System.err.println("Ha ocurrido una excepción FileNotFound: " + e.getMessage());
            } catch (IOException e) {
                System.err.println("Ha ocurrido una excepción IOE: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Ha ocurrido una excepción: " + e.getMessage());

            }
        }
        return ods;
    }



	
	
}
