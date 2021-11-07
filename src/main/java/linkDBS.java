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
    private JScrollPane sp;
    private MyFrame frame;

    public void addTeam() {
        JFrame addteam;
        addteam = new JFrame("Add Team");
        addteam.setDefaultCloseOperation(2);
        addteam.setBounds(550, 230, 400, 500);
        addteam.setVisible(true);

        JPanel panel = new JPanel();
        JLabel l0 = new JLabel("Team ID number");
        JLabel l1 = new JLabel("Team Name");
        JLabel l2 = new JLabel("Coach Name");
        JLabel l3 = new JLabel("Coach E-mail");
        JLabel l4 = new JLabel("Leader Name");
        JLabel l5 = new JLabel("Leader E-mail");
        JLabel l6 = new JLabel("member2 Name");
        JLabel l7 = new JLabel("member3 Name");
        JTextField teamID = new JTextField(20);
        JTextField teamName = new JTextField(20);
        JTextField coachName = new JTextField(20);
        JTextField coachEmail = new JTextField(20);
        JTextField leaderName = new JTextField(20);
        JTextField leaderEmail = new JTextField(20);
        JTextField member2Name = new JTextField(20);
        JTextField member3Name = new JTextField(20);
        panel.add(l0);
        panel.add(teamID);
        panel.add(l1);
        panel.add(teamName);
        panel.add(l2);
        panel.add(coachName);
        panel.add(l3);
        panel.add(coachEmail);
        panel.add(l4);
        panel.add(leaderName);
        panel.add(l5);
        panel.add(leaderEmail);
        panel.add(l6);
        panel.add(member2Name);
        panel.add(l7);
        panel.add(member3Name);

        JPanel panel2 = new JPanel();
        JButton btn1 = new JButton("Cancel");
        JButton btn2 = new JButton("Apply");
        panel2.add(btn1);
        panel2.add(btn2);

        panel.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 20));
        panel2.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 40));

        addteam.setLayout(new BorderLayout());
        addteam.add("Center", panel);
        addteam.add("South", panel2);

        btn1.addActionListener(e -> {
            addteam.setVisible(false);
        });
        btn2.addActionListener(e -> {
            try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
                try (PreparedStatement ps = conn.prepareStatement(
                        "INSERT INTO teams (id, team_name, coach_name, coach_Email, leader_name, leader_Email, member2_name, member3_name) VALUES (?,?,?,?,?,?,?,?)")) {
                    ps.setObject(1, Integer.parseInt(teamID.getText()));
                    ps.setObject(2, teamName.getText());
                    ps.setObject(3, coachName.getText());
                    ps.setObject(4, coachEmail.getText());
                    ps.setObject(5, leaderName.getText());
                    ps.setObject(6, leaderEmail.getText());
                    ps.setObject(7, member2Name.getText());
                    ps.setObject(8, member3Name.getText());
                    int n = ps.executeUpdate(); // 1:插入成功的个数
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            addteam.setVisible(false);
            repaint();
        });
    }

    public void deleTeam() {
        JFrame deleteam;
        deleteam = new JFrame("Delete Team");
        deleteam.setDefaultCloseOperation(2);
        deleteam.setBounds(550, 400, 400, 200);
        deleteam.setVisible(true);

        JPanel panel = new JPanel();
        JLabel l0 = new JLabel("Enter the Team id you want to delete:");
        JTextField teamID = new JTextField(20);
        panel.add(l0);
        panel.add(teamID);

        JPanel panel2 = new JPanel();
        JButton btn1 = new JButton("Cancel");
        JButton btn2 = new JButton("Delete");
        panel2.add(btn1);
        panel2.add(btn2);

        panel.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 15));
        panel2.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 20));

        deleteam.setLayout(new BorderLayout());
        deleteam.add("Center", panel);
        deleteam.add("South", panel2);

        btn1.addActionListener(e -> {
            deleteam.setVisible(false);
        });
        btn2.addActionListener(e -> {
            try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
                try (PreparedStatement ps = conn.prepareStatement("DELETE FROM teams WHERE id=?")) {
                    ps.setObject(1, Integer.parseInt(teamID.getText())); // 注意：索引从1开始
                    int n = ps.executeUpdate(); // 删除的行数
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            deleteam.setVisible(false);
            repaint();
        });
    }

    public void modifyinfo() {
        JFrame modify;
        modify = new JFrame("Modify Information");
        modify.setDefaultCloseOperation(2);
        modify.setBounds(550, 200, 450, 550);
        modify.setVisible(true);

        JPanel panel = new JPanel();
        JLabel l0 = new JLabel("Enter the Team id you want to modify:");
        JTextField teamID = new JTextField(20);
        JLabel l1 = new JLabel("Choose the fields that you want to modify,");
        JLabel l2 = new JLabel("Please fill in new information in the text field following");
        JCheckBox c1 = new JCheckBox("Team Name");
        JCheckBox c2 = new JCheckBox("Coach Name");
        JCheckBox c3 = new JCheckBox("Coach E-mail");
        JCheckBox c4 = new JCheckBox("Leader Name");
        JCheckBox c5 = new JCheckBox("Leader E-mail");
        JCheckBox c6 = new JCheckBox("member2 Name");
        JCheckBox c7 = new JCheckBox("member3 Name");
        JTextField teamName = new JTextField(20);
        JTextField coachName = new JTextField(20);
        JTextField coachEmail = new JTextField(20);
        JTextField leaderName = new JTextField(20);
        JTextField leaderEmail = new JTextField(20);
        JTextField member2Name = new JTextField(20);
        JTextField member3Name = new JTextField(20);
        panel.add(l0);
        panel.add(teamID);
        panel.add(l1);
        panel.add(l2);
        panel.add(c1);
        panel.add(teamName);
        panel.add(c2);
        panel.add(coachName);
        panel.add(c3);
        panel.add(coachEmail);
        panel.add(c4);
        panel.add(leaderName);
        panel.add(c5);
        panel.add(leaderEmail);
        panel.add(c6);
        panel.add(member2Name);
        panel.add(c7);
        panel.add(member3Name);

        JPanel panel2 = new JPanel();
        JButton btn1 = new JButton("Cancel");
        JButton btn2 = new JButton("Enter");
        panel2.add(btn1);
        panel2.add(btn2);

        panel.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 15));
        panel2.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 20));

        modify.setLayout(new BorderLayout());
        modify.add("Center", panel);
        modify.add("South", panel2);

        btn1.addActionListener(e -> {
            modify.setVisible(false);
        });
        btn2.addActionListener(e -> {
            try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
                if (c1.isSelected()) {
                    try (PreparedStatement ps = conn.prepareStatement("UPDATE teams SET team_name=? WHERE id=?")) {
                        ps.setObject(1, teamName.getText()); // 注意：索引从1开始
                        ps.setObject(2, Integer.parseInt(teamID.getText()));
                        int n = ps.executeUpdate(); // 返回更新的行数
                    }
                }
                if (c2.isSelected()) {
                    try (PreparedStatement ps = conn.prepareStatement("UPDATE teams SET coach_name=? WHERE id=?")) {
                        ps.setObject(1, coachName.getText()); // 注意：索引从1开始
                        ps.setObject(2, Integer.parseInt(teamID.getText()));
                        int n = ps.executeUpdate(); // 返回更新的行数
                    }
                }
                if (c3.isSelected()) {
                    try (PreparedStatement ps = conn.prepareStatement("UPDATE teams SET coach_Email=? WHERE id=?")) {
                        ps.setObject(1, coachEmail.getText()); // 注意：索引从1开始
                        ps.setObject(2, Integer.parseInt(teamID.getText()));
                        int n = ps.executeUpdate(); // 返回更新的行数
                    }
                }
                if (c4.isSelected()) {
                    try (PreparedStatement ps = conn.prepareStatement("UPDATE teams SET leader_name=? WHERE id=?")) {
                        ps.setObject(1, leaderName.getText()); // 注意：索引从1开始
                        ps.setObject(2, Integer.parseInt(teamID.getText()));
                        int n = ps.executeUpdate(); // 返回更新的行数
                    }
                }
                if (c5.isSelected()) {
                    try (PreparedStatement ps = conn.prepareStatement("UPDATE teams SET leader_Email=? WHERE id=?")) {
                        ps.setObject(1, leaderEmail.getText()); // 注意：索引从1开始
                        ps.setObject(2, Integer.parseInt(teamID.getText()));
                        int n = ps.executeUpdate(); // 返回更新的行数
                    }
                }
                if (c6.isSelected()) {
                    try (PreparedStatement ps = conn.prepareStatement("UPDATE teams SET member2_name=? WHERE id=?")) {
                        ps.setObject(1, member2Name.getText()); // 注意：索引从1开始
                        ps.setObject(2, Integer.parseInt(teamID.getText()));
                        int n = ps.executeUpdate(); // 返回更新的行数
                    }
                }
                if (c7.isSelected()) {
                    try (PreparedStatement ps = conn.prepareStatement("UPDATE teams SET member3_name=? WHERE id=?")) {
                        ps.setObject(1, member3Name.getText()); // 注意：索引从1开始
                        ps.setObject(2, Integer.parseInt(teamID.getText()));
                        int n = ps.executeUpdate(); // 返回更新的行数
                    }
                }
                modify.setVisible(false);
                repaint();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }

    public JTable queryDatabase() {
        Vector<Vector<java.io.Serializable>> rowData;
        Vector<String> rowName;

        rowData = new Vector();
        // query
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            try (Statement stmt = conn.createStatement()) {
                try (ResultSet rs = stmt.executeQuery("SELECT id, team_name, coach_name, coach_Email, leader_name, leader_Email, member2_name, member3_name FROM teams")) {
                    while (rs.next()) {
                        Vector tmp = new Vector();
                        tmp.add(rs.getLong(1));
                        for (int i = 2; i <= 8; ++i) {
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

        JTable jt;
        jt = new JTable(rowData, rowName);

        return jt;
    }

    public void repaint() {
        sp.setViewportView(queryDatabase());
    }

    public linkDBS() {
        frame = new MyFrame("Competition Registration System", "background.png");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(350, 180, 800, 600);
        frame.setVisible(true);

        sp = new JScrollPane(queryDatabase());
        sp.setSize(new Dimension(785, 120));
        sp.setLocation(0, 230);
        frame.add(sp);
        // buttons
        JButton btn1 = new JButton("Create team");
        JButton btn2 = new JButton("Modify info");
        JButton btn3 = new JButton("Cancel team");
        btn1.setBounds(80, 400, 150, 30);
        btn2.setBounds(305, 400, 150, 30);
        btn3.setBounds(530, 400, 150, 30);
        frame.add(btn1);
        frame.add(btn2);
        frame.add(btn3);
        btn1.addActionListener(e -> {
            addTeam();
        });
        btn2.addActionListener(e -> {
            modifyinfo();
        });
        btn3.addActionListener(e -> {
            deleTeam();
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
