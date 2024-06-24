/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import context.DBContext;
import entity.Voucher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

/**
 *
 * @author ADMIN
 */
public class VoucherDAO {

    Connection conn;//ket noi voi database
    PreparedStatement ps;//thuc thi cau lenh sql
    ResultSet rs;// luu gia tri lay tu database

    public List<Voucher> getAllVouchers() {
        List<Voucher> list = new ArrayList<>();
        String query = "select * from voucher";
        try {
            conn = DBContext.connect();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Voucher(rs.getString(1),
                        rs.getString(2),
                        rs.getFloat(3)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean checkVoucherExist(String id) {
        String query = "SELECT * FROM voucher WHERE voucher_id = ?";
        boolean exists = false;

        try {
            conn = new DBContext().connect();
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();

            // Kiểm tra nếu ResultSet không rỗng và cột đầu tiên không là null
            if (rs.next() && rs.getObject(1) != null) {
                exists = true;
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi", e);
        } finally {
            // Đóng ResultSet, PreparedStatement và Connection để tránh rò rỉ tài nguyên
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Lỗi", e);
            }
        }
        return exists;
    }

    public Voucher searchVouchersByCode(String text) {
        String query = "select * from voucher\n"
                + "where voucher_code=?";
        try {
            conn = new DBContext().connect();
            ps = conn.prepareStatement(query);
            ps.setString(1, text);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Voucher(rs.getString(1),
                        rs.getString(2),
                        rs.getFloat(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    public boolean checkVoucherExist(String code) {
//        String query = "SELECT * FROM voucher WHERE voucher_code = ?";
//        boolean exists = false;
//
//        try {
//            conn = new DBContext().connect();
//            ps = conn.prepareStatement(query);
//            ps.setString(1, code);
//            rs = ps.executeQuery();
//
//            if (rs.next()) {
//                exists = true;
//            }
//        } catch (Exception e) {
//            LOGGER.log(Level.SEVERE, "Lỗi", e);
//        } finally {
//            // Đóng ResultSet, PreparedStatement và Connection để tránh rò rỉ tài nguyên
//            try {
//                if (rs != null) {
//                    rs.close();
//                }
//                if (ps != null) {
//                    ps.close();
//                }
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (Exception e) {
//                LOGGER.log(Level.SEVERE, "Lỗi", e);
//            }
//        }
//        return exists;
//    }
    public List<Voucher> searchVouchersByRate(String text) {
        List<Voucher> list = new ArrayList<>();
        String query = "select * from voucher\n"
                + "where discount_percentage=?";
        try {
            conn = new DBContext().connect();
            ps = conn.prepareStatement(query);
            ps.setString(1, text);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Voucher(rs.getString(1),
                        rs.getString(2),
                        rs.getFloat(3)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void createVoucher(String id, String code, Double discount) {
        String query = "INSERT INTO voucher\n"
                + "VALUES (?, ?, ?)";
        try {
            conn = new DBContext().connect();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            ps.setString(2, code);
            ps.setDouble(3, discount);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateVoucher(String id, String code, Double discount) {
        String query = "UPDATE voucher\n"
                + "SET voucher_code=?, discount_percentage=? \n"
                + "WHERE voucher_id = ?";
        try {
            conn = new DBContext().connect();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, code);
            ps.setDouble(2, discount);
            ps.setString(3, id);
          
            ps.executeUpdate();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi", e);
        }
    }

    public void deleteVoucher(String id) {
        String query = "DELETE FROM voucher WHERE voucher_id = ? ";
        try {
            conn = new DBContext().connect();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ khi gặp lỗi thực thi câu lệnh SQL
        } finally {
            // Đóng kết nối và tài nguyên
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        VoucherDAO dao = new VoucherDAO();

       dao.updateVoucher("vou0", "Null", 0.0);

    }
}
