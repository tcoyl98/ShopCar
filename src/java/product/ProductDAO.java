/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package product;

import db.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import order.OrderDTO;

/**
 *
 * @author looby
 */
public class ProductDAO {

    public static List<ProductDTO> getProduct(int index, List<ProductDTO> listProduct) throws SQLException, ClassNotFoundException {
        List<ProductDTO> list = new ArrayList<>();
        int fromIndex = (index - 1) * 5;
        list = listProduct.subList(fromIndex, Math.min(fromIndex + 5, listProduct.size()));
        return list;
    }

    public static List<ProductDTO> getAllProduct() throws SQLException, ClassNotFoundException {
        Connection con = DBUtils.getConnection();
        String sql = "select * from Product ORDER BY cast (Price as bigint) ASC";
        PreparedStatement stm = con.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        List<ProductDTO> list = new ArrayList<>();
        while (rs.next()) {
            ProductDTO car = new ProductDTO();
            car.setId(rs.getString("Id"));
            car.setName(rs.getString("Name"));
            car.setPrice(rs.getString("Price"));
            car.setBrand(rs.getString("Brand"));
            car.setColor(rs.getString("Color"));
            car.setType(rs.getString("Type"));
            car.setDescription(rs.getString("Description"));
            car.setImagePath(rs.getString("ImagePath"));
            list.add(car);
        }
        con.close();
        return list;
    }

    public static ProductDTO getProductById(String id) throws SQLException, ClassNotFoundException {
        Connection con = DBUtils.getConnection();
        String sql = "select * from Product where id like ? ORDER BY cast (Price as bigint) ASC";
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, id);
        ResultSet rs = stm.executeQuery();
        ProductDTO car = new ProductDTO();
        while (rs.next()) {

            car.setId(rs.getString("Id"));
            car.setName(rs.getString("Name"));
            car.setPrice(rs.getString("Price"));
            car.setBrand(rs.getString("Brand"));
            car.setColor(rs.getString("Color"));
            car.setType(rs.getString("Type"));
            car.setDescription(rs.getString("Description"));
            car.setImagePath(rs.getString("ImagePath"));
        }
        con.close();
        return car;
    }

    public static List<ProductDTO> getAllProductByBrand(String name) throws SQLException, ClassNotFoundException {
        Connection con = DBUtils.getConnection();
        String sql = "select * from Product where brand like ? ORDER BY cast (Price as bigint) ASC";
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, name);
        ResultSet rs = stm.executeQuery();
        List<ProductDTO> list = new ArrayList<>();
        while (rs.next()) {
            ProductDTO car = new ProductDTO();
            car.setId(rs.getString("Id"));
            car.setName(rs.getString("Name"));
            car.setPrice(rs.getString("Price"));
            car.setBrand(rs.getString("Brand"));
            car.setColor(rs.getString("Color"));
            car.setType(rs.getString("Type"));
            car.setDescription(rs.getString("Description"));
            car.setImagePath(rs.getString("ImagePath"));
            list.add(car);
        }
        con.close();
        return list;
    }

    public static List<ProductDTO> getAllProductByName(String name) throws SQLException, ClassNotFoundException {
        Connection con = DBUtils.getConnection();
        String sql = "select * from Product where name like ? ORDER BY cast (Price as bigint) ASC";
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, "%" + name + "%");
        ResultSet rs = stm.executeQuery();
        List<ProductDTO> list = new ArrayList<>();
        while (rs.next()) {
            ProductDTO car = new ProductDTO();
            car.setId(rs.getString("Id"));
            car.setName(rs.getString("Name"));
            car.setPrice(rs.getString("Price"));
            car.setBrand(rs.getString("Brand"));
            car.setColor(rs.getString("Color"));
            car.setType(rs.getString("Type"));
            car.setDescription(rs.getString("Description"));
            car.setImagePath(rs.getString("ImagePath"));
            list.add(car);
        }
        con.close();
        return list;
    }

    public static List<ProductDTO> getAllProductByPrice(String from, String to) throws SQLException, ClassNotFoundException {
        Connection con = DBUtils.getConnection();
        String sql = "select  * from Product where cast (Price as bigint) > ? and cast (Price as bigint) < ? ORDER BY  cast (Price as bigint)  ASC";
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, from);
        stm.setString(2, to);
        ResultSet rs = stm.executeQuery();
        List<ProductDTO> list = new ArrayList<>();
        while (rs.next()) {
            ProductDTO car = new ProductDTO();
            car.setId(rs.getString("Id"));
            car.setName(rs.getString("Name"));
            car.setPrice(rs.getString("Price"));
            car.setBrand(rs.getString("Brand"));
            car.setColor(rs.getString("Color"));
            car.setType(rs.getString("Type"));
            car.setDescription(rs.getString("Description"));
            car.setImagePath(rs.getString("ImagePath"));
            list.add(car);
        }
        con.close();
        return list;
    }

    public static List<ProductDTO> getAllProductByType(String type) throws SQLException, ClassNotFoundException {
        Connection con = DBUtils.getConnection();
        String sql = "";
        List<ProductDTO> list = new ArrayList<>();
        if (type.equals("Sedan") || type.equals("SUV")) {
            sql = "select  * from Product where Type=? ORDER BY Price ASC";
        } else {
            sql = "select  * from Product where Type!='Sedan' and Type!='SUV' ORDER BY  cast (Price as bigint)  ASC";
        }
        PreparedStatement stm = con.prepareStatement(sql);
        if (type.equals("Sedan") || type.equals("SUV")) {
            stm.setString(1, type);
        }
        ResultSet rs = stm.executeQuery();

        while (rs.next()) {
            ProductDTO car = new ProductDTO();
            car.setId(rs.getString("Id"));
            car.setName(rs.getString("Name"));
            car.setPrice(rs.getString("Price"));
            car.setBrand(rs.getString("Brand"));
            car.setColor(rs.getString("Color"));
            car.setType(rs.getString("Type"));
            car.setDescription(rs.getString("Description"));
            car.setImagePath(rs.getString("ImagePath"));
            list.add(car);
        }

        con.close();
        return list;
    }

    public static void create(String id, String name, String price, String brand, String color, String description, String type, String imagePath) throws SQLException, ClassNotFoundException {
        Connection con = DBUtils.getConnection();
        String sql = "insert into Product values(?,?,?,?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, id);
        stm.setString(2, name);
        stm.setString(3, price);
        stm.setString(4, brand);
        stm.setString(5, color);
        stm.setString(6, description);
        stm.setString(7, type);
        stm.setString(8, imagePath);
        stm.execute();

        con.close();
    }

    public static void deleteCar(String id) throws SQLException, ClassNotFoundException {
        Connection con = DBUtils.getConnection();
        String sql = "delete from Product where id=?";
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, id);
        stm.execute();
        con.close();
    }

    public static void update(String id, String name, String price, String brand, String color, String description, String type, String imagePath) throws SQLException, ClassNotFoundException {
        Connection con = DBUtils.getConnection();
        String sql = "Update Product set name = ?, price =?,brand=?,color=?,description=?,type=?,imagePath=?  where id=?";
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, name);
        stm.setString(2, price);
        stm.setString(3, brand);
        stm.setString(4, color);
        stm.setString(5, description);
        stm.setString(6, type);
        stm.setString(7, imagePath);
        stm.setString(8, id);

        stm.execute();

        con.close();
    }
}
