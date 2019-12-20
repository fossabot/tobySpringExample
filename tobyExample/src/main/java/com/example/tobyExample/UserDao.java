package com.example.tobyExample;

import java.sql.*;

public class UserDao {

    // TODO: 2019-12-20 객체의 관계 설정 책임을 분리 

    private ConnectionMaker connectionMaker;

    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public Integer getCount() throws ClassNotFoundException, SQLException {

        Connection c = connectionMaker.makeConnection();

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

        Connection c = connectionMaker.makeConnection();

        PreparedStatement ps = c.prepareStatement("insert into users (id, name, password) values (?, ?, ?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.execute();

        ps.close();
        c.close();

    }

    public void deleteAll() throws SQLException, ClassNotFoundException {

        Connection c = connectionMaker.makeConnection();

        PreparedStatement ps = c.prepareStatement("delete from users");

        ps.execute();

        ps.close();
        c.close();

    }

    public User get(String id) throws SQLException, ClassNotFoundException {

        Connection c = connectionMaker.makeConnection();

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
