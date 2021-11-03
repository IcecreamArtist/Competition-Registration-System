import java.sql.*;

public class linkDBS {
    public static void main(String[] args) throws SQLException {
        // JDBC���ӵ�URL, ��ͬ���ݿ��в�ͬ�ĸ�ʽ:
        String JDBC_URL = "jdbc:mysql://localhost:3306/learnjdbc?serverTimezone=UTC";
        String JDBC_USER = "root";
        String JDBC_PASSWORD = "001208";

        // print all gender==1 out
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            try (Statement stmt = conn.createStatement()) {
                try (ResultSet rs = stmt.executeQuery("SELECT id, grade, name, gender FROM students WHERE gender=1")) {
                    while (rs.next()) {
                        long id = rs.getLong(1); // ע�⣺������1��ʼ
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
                ps.setObject(1, 999); // ע�⣺������1��ʼ
                ps.setObject(2, 1); // grade
                ps.setObject(3, "Bob"); // name
                ps.setObject(4, 1); // gender
                ps.setObject(5,3);
                int n = ps.executeUpdate(); // 1:����ɹ��ĸ���
            }
        }

        // update
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            try (PreparedStatement ps = conn.prepareStatement("UPDATE students SET name=? WHERE id=?")) {
                ps.setObject(1, "Bob"); // ע�⣺������1��ʼ
                ps.setObject(2, 999);
                int n = ps.executeUpdate(); // ���ظ��µ�����
            }
        }

        // delete
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            try (PreparedStatement ps = conn.prepareStatement("DELETE FROM students WHERE id=?")) {
                ps.setObject(1, 999); // ע�⣺������1��ʼ
                int n = ps.executeUpdate(); // ɾ��������
            }
        }
    }
}
