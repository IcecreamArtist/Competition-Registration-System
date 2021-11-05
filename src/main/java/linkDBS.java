import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;
import javax.swing.*;

public class linkDBS {
    String JDBC_URL = "jdbc:mysql://localhost:3306/learnjdbc?serverTimezone=UTC";
    String JDBC_USER = "root";
    String JDBC_PASSWORD = "001208";

    private Vector<String> rowName;
    private Vector<Vector<java.io.Serializable>> rowData;

    private JTable jt = null;

    private MyFrame frame;
    private MyFrame addteam;
    private MyFrame deleteam;
    private MyFrame modify;

    public void initialize() {
        addteam = new MyFrame("Add Team","background.png");
        addteam.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addteam.setBounds(350,180,600,500);
        addteam.setVisible(false);
//        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
//            try (PreparedStatement ps = conn.prepareStatement(
//                    "INSERT INTO students (id, grade, name, gender, score) VALUES (?,?,?,?,?)")) {
//                ps.setObject(1, 999); // 注意：索引从1开始
//                ps.setObject(2, 1); // grade
//                ps.setObject(3, "Bob"); // name
//                ps.setObject(4, 1); // gender
//                ps.setObject(5,3);
//                int n = ps.executeUpdate(); // 1:插入成功的个数
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }

        deleteam = new MyFrame("Delete Team","background.png");
        deleteam.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        deleteam.setBounds(350,180,600,500);
        deleteam.setVisible(false);
//        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
//            try (PreparedStatement ps = conn.prepareStatement("DELETE FROM students WHERE id=?")) {
//                ps.setObject(1, 999); // 注意：索引从1开始
//                int n = ps.executeUpdate(); // 删除的行数
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }

        modify = new MyFrame("Modify Information","background.png");
        modify.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        modify.setBounds(450,230,600,500);
        modify.setVisible(false);
//        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
//            try (PreparedStatement ps = conn.prepareStatement("UPDATE students SET name=? WHERE id=?")) {
//                ps.setObject(1, "Bob"); // 注意：索引从1开始
//                ps.setObject(2, 999);
//                int n = ps.executeUpdate(); // 返回更新的行数
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
    }

    public linkDBS() {

        initialize();
        rowData = new Vector();
        // query
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            try (Statement stmt = conn.createStatement()) {
                try (ResultSet rs = stmt.executeQuery("SELECT id, team_name, coach_name, coach_Email, leader_name, leader_Email, member2_name, member3_name FROM teams")) {
                    while (rs.next()) {
                        Vector tmp = new Vector();
                        tmp.add(rs.getLong(1));
                        for(int i=2;i<=8;++i) {
                            tmp.add(rs.getString(i));
                        }
                        rowData.add(tmp);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        rowName = new Vector();
        rowName.add("id");
        rowName.add("team name");
        rowName.add("coach name");
        rowName.add("coach e-mail");
        rowName.add("leader name");
        rowName.add("leader e-mail");
        rowName.add("member2 name");
        rowName.add("member3 name");

        jt = new JTable(rowData,rowName);

        frame = new MyFrame("Competition_Registration_System","background.png");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(350,180,800,600);
        frame.setVisible(true);
//        frame.setResizable(false);

        JScrollPane scrollPane = new JScrollPane(jt);
        scrollPane.setSize(new Dimension(785,120));
        frame.add(scrollPane);
        scrollPane.setLocation(0,230);

        // buttons
        JButton btn1 = new JButton("Create team");
        JButton btn2 = new JButton("Modify info");
        JButton btn3 = new JButton("Cancel team");
        btn1.setBounds(80,400,150,30);
        btn2.setBounds(305,400,150,30);
        btn3.setBounds(530,400,150,30);
        frame.add(btn1);
        frame.add(btn2);
        frame.add(btn3);

        btn1.addActionListener(e -> {
            addteam.setVisible(true);
        });
        btn2.addActionListener(e -> {
            deleteam.setVisible(true);
        });
        btn3.addActionListener(e -> {
            modify.setVisible(true);
        });

    }

    public static void main(String[] args) throws SQLException {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                linkDBS window = new linkDBS();
            }
        });

    }
}
