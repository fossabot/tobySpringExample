package com.example.tobyExample;

import java.sql.*;

public class UserDao {

    public Integer getCount() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/javadb?serverTimezone=GMT",
                "root",
                "1234"
        );

        PreparedStatement ps = c.prepareStatement("select count(*) from users");
        ResultSet rs = ps.executeQuery();

        rs.next();
        int result = rs.getInt(1);

        rs.close();
        ps.close();
        c.close();

        return result;

    }

    public void add(User user) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/javadb?serverTimezone=GMT",
                "root",
                "1234"
        );

        PreparedStatement ps = c.prepareStatement("insert into users (id, name, password) values (?, ?, ?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.execute();

        ps.close();
        c.close();

    }

    public void deleteAll() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/javadb?serverTimezone=GMT",
                "root",
                "1234"
        );

        PreparedStatement ps = c.prepareStatement("delete from users");

        ps.execute();

        ps.close();
        c.close();

    }

    public User get(String id) throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/javadb?serverTimezone=GMT",
                "root",
                "1234"
        );

        PreparedStatement ps = c.prepareStatement("select id, name, password from users where id=?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();

        User got = null;

        if (rs.next()) {
            got = User.builder()
                    .id(rs.getString("id"))
                    .name(rs.getString("name"))
                    .password(rs.getString("password"))
                    .build();
        }

        ps.close();
        c.close();

        return got;
    }
}
