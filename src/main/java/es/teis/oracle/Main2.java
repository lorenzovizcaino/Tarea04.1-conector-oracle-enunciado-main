package es.teis.oracle;

import es.teis.oracle.modelo.JPersona;
import es.teis.oracle.util.ConnectionFactory;
import oracle.jdbc.OracleResultSet;
import oracle.jdbc.datasource.impl.OracleDataSource;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main2 {
    public static void main(String[] args) {
        OracleDataSource ods;
        ConnectionFactory connectionFactory=new ConnectionFactory();
        try {
            ods = connectionFactory.getConnection();




            Connection conn = ods.getConnection();

            // Create Oracle DatabaseMetaData object
            DatabaseMetaData meta = conn.getMetaData();

            // gets driver info:
            System.out.println("JDBC driver version is " + meta.getDriverVersion());


            PreparedStatement stmt = conn.prepareStatement("SELECT value(p) FROM PERSON_OBJ_TABLE p");
            OracleResultSet rs = (OracleResultSet) stmt.executeQuery();
            while (rs.next()) {





                Object s = rs.getObject(1, JPersona.getOracleDataFactory());

                if (s != null) {
                    if (s instanceof JPersona) {
                        JPersona john = (JPersona)s;


                        System.out.println("This is a JPerson");
                        System.out.println(s);
                    }

                    else {
                        System.out.println("Unknown type");
                        System.out.println(s);
                    }
                }
            }






        } catch (SQLException e) {
            System.err.println("Ha ocurrido una exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
