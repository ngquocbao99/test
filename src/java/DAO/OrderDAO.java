/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import context.DBContext;
import entity.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Quynhh Nhuu
 */
public class OrderDAO {

    Connection conn;//ket noi voi database
    PreparedStatement ps;//thuc thi cau lenh sql
    ResultSet rs;// luu gia tri lay tu database

    LocalDate date = java.time.LocalDate.now();
    LocalTime time = java.time.LocalTime.now();

    public List<Order> getAllOrders() {
        List<Order> list = new ArrayList<>();
        String query = "select * from [order] ";// test ben SQL
        try {
            conn = DBContext.connect();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Order(rs.getString(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        OrderDAO dao = new OrderDAO();
        List<Order> list = dao.getAllOrders();
        for (Order order : list) {
            System.out.println(order.getTotal_price());
        }
    }
}
