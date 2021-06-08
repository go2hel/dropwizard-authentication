/**
 * 
 */
package com.auth.db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {


	public static Connection getConnection() {
		
		Connection connection = null;
		
        if (connection != null) {
			return connection;
		} 
        else 
        {
            try 
            {
            	
            	Properties prop = new Properties();
            	InputStream inputStream = new FileInputStream("D:\\Intellij Projects\\auth\\src\\main\\java\\config.properties");
                prop.load(inputStream);
                String driver = prop.getProperty("driver");
                String url = prop.getProperty("url");
                String user = prop.getProperty("user");
                String password = prop.getProperty("password");
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
                
            }
            catch (ClassNotFoundException e){
                System.err.println(e.getMessage());
            }
            catch (SQLException e) {
                System.err.println(e.getMessage());
            }
            catch (FileNotFoundException e) {
                System.err.println(e.getMessage());
            }
            catch (IOException e) {
                System.err.println(e.getMessage());
            }
            return connection;
        }

    }
}
