package com.example.tobyExample;

import java.sql.*;

public abstract class UserDao {

    // TODO: 2019-12-17
    // 데이터 베이스로 부터 연결객체를 가져오는 관심사의 분리
    //  - 관심사를 method로 분리
    //  - 관심사의 method를 확장 -> 확장의 방법 이용 -> template method pattern

    public Integer getCount() throws ClassNotFoundException, SQLException {

        Connection c = getConnection();

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

        Connection c = getConnection();

        PreparedStatement ps = c.prepareStatement("insert into users (id, name, password) values (?, ?, ?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.execute();

        ps.close();
        c.close();

    }

    public void deleteAll() throws SQLException, ClassNotFoundException {

        Connection c = getConnection();

        PreparedStatement ps = c.prepareStatement("delete from users");

        ps.execute();

        ps.close();
        c.close();

    }

    public User get(String id) throws SQLException, ClassNotFoundException {

        Connection c = getConnection();

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

    protected abstract Connection getConnection() throws SQLException, ClassNotFoundException;
}
