import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyRRApp {
    final static int extraWindowWidth = 100;

    String url = "jdbc:mysql://localhost:3306/riddlerundown?useSSL=false";
    String user = "root";
    String pswd = "script47";
    DatabaseHelper sql = new DatabaseHelper(url, user, pswd);

    public void addComponentToPane(Container pane) {
        JTabbedPane tabbedPane = new JTabbedPane();

        // Card 1, view player stats
        JPanel viewPlayerStatPage = new JPanel();

        // Card 2, view pitcher statistics page
        JPanel viewPitcherStatPage = new JPanel();
        viewPitcherStatPage.add(new JTextField("TextField", 20));

        // Card 3, view team statistics page
        JPanel viewTeamStatPage = new JPanel();

        // Card 4, create team
        JPanel createTeamPage = new JPanel();
        createTeamPage.setLayout(new BoxLayout(createTeamPage, BoxLayout.PAGE_AXIS));

        JLabel Tdirections = new JLabel("To create a new team, enter a team name and location.");
        JLabel TsuccessMessage = new JLabel("Success!");
        TsuccessMessage.setVisible(false);
        TsuccessMessage.setForeground(Color.GREEN);
        JLabel TfailureMessage = new JLabel("Failed to update database.");
        TfailureMessage.setVisible(false);
        TfailureMessage.setForeground(Color.red);
        JTextField teamName = new JTextField("Enter team name", 20);
        JTextField teamLocation = new JTextField("Enter team location", 20);
        JButton TcreateButton = new JButton("Create team", null);
        TcreateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String teamNameString = teamName.getText();
                String teamLocationString = teamLocation.getText();
                System.out.printf("%s %s", teamNameString, teamLocationString);
                sql.addTeam(teamNameString, teamLocationString);
                TfailureMessage.setVisible(true);
                teamName.setText("Enter team name.");
                teamLocation.setText("Enter team location.");
            }
        });
        JButton TdeleteButton = new JButton("Delete Team", null);
        JButton TconfirmDeleteButton = new JButton("Confirm delete?", null);
        TconfirmDeleteButton.setVisible(false);
        TdeleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TconfirmDeleteButton.setVisible(true);
            }
        });
        TconfirmDeleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nameString = teamName.getText();
                String numberInteger = teamLocation.getText();
                sql.deleteTeam(nameString, numberInteger);
                System.out.print(nameString + numberInteger);
                TfailureMessage.setVisible(true);
            }
        });

        createTeamPage.add(Tdirections);
        createTeamPage.add(teamName);
        createTeamPage.add(teamLocation);
        createTeamPage.add(TcreateButton);
        createTeamPage.add(TdeleteButton);
        createTeamPage.add(TconfirmDeleteButton);
        createTeamPage.add(TsuccessMessage);
        createTeamPage.add(TfailureMessage);

        // Card 5, create position player
        JPanel createPlayerPage = new JPanel();
        createPlayerPage.setLayout(new BoxLayout(createPlayerPage, BoxLayout.PAGE_AXIS));

        JLabel PPdirections = new JLabel(
                "To create a new position player: select a team, enter a name, number, and position");
        JLabel PPsuccessMessage = new JLabel("Success!");
        PPsuccessMessage.setVisible(false);
        PPsuccessMessage.setForeground(Color.GREEN);
        JLabel PPfailureMessage = new JLabel("Failed to update database.");
        PPfailureMessage.setVisible(false);
        PPfailureMessage.setForeground(Color.red);
        JTextField ppName = new JTextField("Enter a name", 20);
        JTextField ppNum = new JTextField("Enter a number", 20);
        JTextField ppPosition = new JTextField("Enter a position", 20);
        JButton PPcreateButton = new JButton("Create Player", null);
        PPcreateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nameString = ppName.getText();
                String numberInteger = ppNum.getText();
                String positionString = ppPosition.getText();
                System.out.print(nameString + numberInteger + positionString);
                PPfailureMessage.setVisible(true);
            }
        });
        JButton PPdeleteButton = new JButton("Delete Player", null);
        JButton PPconfirmDeleteButton = new JButton("Confirm delete?", null);
        PPconfirmDeleteButton.setVisible(false);
        PPdeleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PPconfirmDeleteButton.setVisible(true);
            }
        });
        PPconfirmDeleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nameString = ppName.getText();
                String numberInteger = ppNum.getText();
                System.out.print(nameString + numberInteger);
                PPfailureMessage.setVisible(true);
            }
        });

        createPlayerPage.add(PPdirections);
        createPlayerPage.add(ppName);
        createPlayerPage.add(ppNum);
        createPlayerPage.add(ppPosition);
        createPlayerPage.add(PPcreateButton);
        createPlayerPage.add(PPdeleteButton);
        createPlayerPage.add(PPconfirmDeleteButton);
        createPlayerPage.add(PPsuccessMessage);
        createPlayerPage.add(PPfailureMessage);

        // Card 6, create pitcher
        JPanel createPitcherPage = new JPanel();
        createPitcherPage.setLayout(new BoxLayout(createPitcherPage, BoxLayout.PAGE_AXIS));

        JLabel Pdirections = new JLabel(
                "To create a new pitcher: select a team, enter a name and number.");
        JLabel PsuccessMessage = new JLabel("Success!");
        PsuccessMessage.setVisible(false);
        PsuccessMessage.setForeground(Color.GREEN);
        JLabel PfailureMessage = new JLabel("Failed to update database.");
        PfailureMessage.setVisible(false);
        PfailureMessage.setForeground(Color.red);
        JTextField pName = new JTextField("Enter a name", 20);
        JTextField pNum = new JTextField("Enter a number", 20);
        JButton PcreateButton = new JButton("Create Pitcher", null);
        PcreateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nameString = pName.getText();
                String numberInteger = pNum.getText();
                System.out.print(nameString + numberInteger);
                PfailureMessage.setVisible(true);
            }
        });
        JButton PdeleteButton = new JButton("Delete Pitcher", null);
        JButton PconfirmDeleteButton = new JButton("Confirm delete?", null);
        PconfirmDeleteButton.setVisible(false);
        PdeleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PconfirmDeleteButton.setVisible(true);
            }
        });
        PconfirmDeleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nameString = pName.getText();
                String numberInteger = pNum.getText();
                System.out.print(nameString + numberInteger);
                PfailureMessage.setVisible(true);
            }
        });

        createPitcherPage.add(Pdirections);
        createPitcherPage.add(pName);
        createPitcherPage.add(pNum);
        createPitcherPage.add(PcreateButton);
        createPitcherPage.add(PdeleteButton);
        createPitcherPage.add(PconfirmDeleteButton);
        createPitcherPage.add(PsuccessMessage);
        createPitcherPage.add(PfailureMessage);

        // Card 7, Update team
        JPanel updateTeam = new JPanel();
        updateTeam.setLayout(new BoxLayout(updateTeam, BoxLayout.PAGE_AXIS));

        // Card 8, Update team
        JPanel updatePlayer = new JPanel();
        updatePlayer.setLayout(new BoxLayout(updatePlayer, BoxLayout.PAGE_AXIS));

        // Card 9, Update team
        JPanel updatePitcher = new JPanel();
        updatePitcher.setLayout(new BoxLayout(updatePitcher, BoxLayout.PAGE_AXIS));

        // Add tabs to tabbed pane
        tabbedPane.addTab("View Player Stats", viewPlayerStatPage);
        tabbedPane.addTab("View Pitcher Stats", viewPitcherStatPage);
        tabbedPane.addTab("View Team Stats", viewTeamStatPage);
        tabbedPane.addTab("New Team", createTeamPage);
        tabbedPane.addTab("New Position Player", createPlayerPage);
        tabbedPane.addTab("New Pitcher", createPitcherPage);
        tabbedPane.addTab("Update Team Stats", updateTeam);
        tabbedPane.addTab("Update Player Stats", updatePlayer);
        tabbedPane.addTab("Update Pitcher Stats", updatePitcher);

        pane.add(tabbedPane, BorderLayout.CENTER);
    }

    private static void createAndShowGUI() {
        // Window name and setup
        JFrame frame = new JFrame("Riddle Rundown Statistics");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Label on window
        JLabel label = new JLabel("Riddle Rundown Statistics");
        frame.getContentPane().add(label);

        // Create and set up content pane
        MyRRApp demo = new MyRRApp();
        demo.addComponentToPane(frame.getContentPane());

        // For window size
        Dimension sz = Toolkit.getDefaultToolkit().getScreenSize();
        int wHeight = sz.height * (1);
        int wWidth = sz.width * (1);
        wHeight = wHeight / 4;
        wWidth = wWidth / 4;
        System.out.println("Screen size: " + sz.width + " x " + sz.height);
        System.out.println("Screen size: " + wHeight + " x " + wWidth);

        // Display the window.
        frame.pack();
        frame.setVisible(true);
        frame.setSize(wWidth, wHeight);
        frame.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}