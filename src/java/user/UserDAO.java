/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import db.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author looby
 */
public class UserDAO {
 public static UserDTO checkLogin(String username,String password) throws SQLException, ClassNotFoundException{
        UserDTO loginUser = new UserDTO();
        Connection conn = DBUtils.getConnection();
        String sql = "Select * from account where username = ? and password = ? ";
        PreparedStatement ptm = conn.prepareStatement(sql);
        ptm.setString(1, username);
        ptm.setString(2, password);
        ResultSet rs =ptm.executeQuery();
        while (rs.next()) {
            loginUser.setUsername(username);
            loginUser.setPassword(password);
            loginUser.setName(rs.getString("name"));
            loginUser.setPhone(rs.getString("phone"));
            loginUser.setRoleId(rs.getInt("role"));
        }
        return loginUser;
    }  
}
