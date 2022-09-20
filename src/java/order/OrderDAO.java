/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package order;

import db.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author looby
 */
public class OrderDAO {
    public static List<OrderDTO> getOrderList() throws SQLException, ClassNotFoundException{
        List<OrderDTO> listOrder = new ArrayList<>();
        Connection con = DBUtils.getConnection();
        String sql = "select * from [Order]";
        PreparedStatement stm = con.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            OrderDTO order = new OrderDTO();
            order.setId(rs.getInt("Id"));
            order.setCarid(rs.getString("CarId"));
            order.setCustomerName(rs.getString("CustomerName"));
            order.setCustomerPhone(rs.getString("CustomerPhone"));
            order.setCustomerAddress(rs.getString("CustomerAddress"));
            listOrder.add(order);
        }
        con.close();
        return listOrder;
    }
    public static void submitOrder(String carid,String name,String phone,String address) throws SQLException, ClassNotFoundException {
        Connection con = DBUtils.getConnection();
        String sql = "INSERT INTO [Order]([CarId],[CustomerName],[CustomerPhone],[CustomerAddress]) VALUES (?,?,?,?);";
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, carid);
        stm.setString(2, name);
        stm.setString(3, phone);
        stm.setString(4, address);
        stm.execute();
        con.close();
    }
}
