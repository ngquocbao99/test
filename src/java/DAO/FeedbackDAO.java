/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import context.DBContext;
import entity.Feedback;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author ADMIN
 */
public class FeedbackDAO {

    Connection conn;//ket noi voi database
    PreparedStatement ps;//thuc thi cau lenh sql
    ResultSet rs;// luu gia tri lay tu database

    LocalDate date = java.time.LocalDate.now();
    LocalTime time = java.time.LocalTime.now();


    public void addFeedback(String fb_id, String content, Date date, Time time, String username) {
        String query = "INSERT INTO feedback \n"
                + "VALUES (?, ?, ?,?,?)";
        try {
            conn = new DBContext().connect();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, fb_id);
            ps.setString(2, content);
//            ps.setDate(3, java.sql.Date.valueOf(date));
//            ps.setTime(4, java.sql.Time.valueOf(time));
            ps.setDate(3, date);
            ps.setTime(4, time);
            ps.setString(5, username);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Feedback> getAllFeedbacks() {
        List<Feedback> listF = new ArrayList<>();
        String query = "select * from feedback";// test ben SQL
        try {
            conn = DBContext.connect();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                listF.add(new Feedback(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listF;
    }

    public static void main(String[] args) {
        FeedbackDAO dao = new FeedbackDAO();

//        DateTimeFormatter formattereDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//        String dateFormat = date.format(formattereDate);
//        System.out.println("-------------");
//
////        dd-MM-yyyy HH:mm:ss
        LocalDateTime myDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedDateTime = myDateTime.format(formatter);
        LocalDate localDate = java.time.LocalDate.now();
        LocalTime localTime = java.time.LocalTime.now();
//        
        Date date = java.sql.Date.valueOf(localDate);
        Time time = java.sql.Time.valueOf(localTime);

        List<Feedback> a = dao.getAllFeedbacks();
        for (Feedback t : a) {
            System.out.println(t.getFb_date());
        }
    }
}
