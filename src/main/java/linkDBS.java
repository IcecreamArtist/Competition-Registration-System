import java.sql.*;

public class linkDBS {
    public static void main(String[] args) throws SQLException {
        // JDBC连接的URL, 不同数据库有不同的格式:
        String JDBC_URL = "jdbc:mysql://localhost:3306/learnjdbc?serverTimezone=UTC";
        String JDBC_USER = "root";
        String JDBC_PASSWORD = "001208";

        // print all gender==1 out
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            try (Statement stmt = conn.createStatement()) {
                try (ResultSet rs = stmt.executeQuery("SELECT id, grade, name, gender FROM students WHERE gender=1")) {
                    while (rs.next()) {
                        long id = rs.getLong(1); // 注意：索引从1开始
                        long grade = rs.getLong(2);
                        String name = rs.getString(3);
                        int gender = rs.getInt(4);
                        System.out.println(id+grade+name+gender);
                    }
                }
            }
        }

        // insert new student
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            try (PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO students (id, grade, name, gender, score) VALUES (?,?,?,?,?)")) {
                ps.setObject(1, 999); // 注意：索引从1开始
                ps.setObject(2, 1); // grade
                ps.setObject(3, "Bob"); // name
                ps.setObject(4, 1); // gender
                ps.setObject(5,3);
                int n = ps.executeUpdate(); // 1:插入成功的个数
            }
        }

        // update
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            try (PreparedStatement ps = conn.prepareStatement("UPDATE students SET name=? WHERE id=?")) {
                ps.setObject(1, "Bob"); // 注意：索引从1开始
                ps.setObject(2, 999);
                int n = ps.executeUpdate(); // 返回更新的行数
            }
        }

        // delete
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            try (PreparedStatement ps = conn.prepareStatement("DELETE FROM students WHERE id=?")) {
                ps.setObject(1, 999); // 注意：索引从1开始
                int n = ps.executeUpdate(); // 删除的行数
            }
        }
    }
}
