package com.example.tobyExample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlUserDao extends UserDao {
    @Override
    protected Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/javadb?serverTimezone=GMT",
                "root",
                "1234"
        );
    }
}
