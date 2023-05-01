import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyRRApp {
    final static int extraWindowWidth = 100;

    // Connection to database, set up for tyler's pc
    String url = "jdbc:mysql://localhost:3306/riddlerundown?useSSL=false";
    String user = "root";
    String pswd = "script47";
    DatabaseHelper sql = new DatabaseHelper(url, user, pswd);

    public void addComponentToPane(Container pane) {
        JTabbedPane tabbedPane = new JTabbedPane();

        // Card 1, view player stats
        JPanel viewPlayerStatPage = new JPanel();
        viewPlayerStatPage.setLayout(new BoxLayout(viewPlayerStatPage, BoxLayout.PAGE_AXIS));
        JLabel vPPdirections = new JLabel(
                "To view a player's stats, enter a team name, location, and the player's number.");
        JTextField vPPteamName = new JTextField("Enter team name", 20);
        JTextField vPPteamLocation = new JTextField("Enter team location", 20);
        JTextField vPPnum = new JTextField("Enter a number", 20);
        JButton vPPButton = new JButton("View stats", null);
        JLabel vPPStats = new JLabel("");
        vPPStats.setVisible(false);
        JLabel vPPLabel = new JLabel("G  PA  AB  R  H  2B  3B  HR  RBI  BB  K  E");
        vPPLabel.setVisible(false);
        vPPButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                vPPLabel.setVisible(true);
                String teamNameString = vPPteamName.getText();
                String teamLocationString = vPPteamLocation.getText();
                Integer numberInteger = Integer.parseInt(vPPnum.getText());
                System.out.printf("%s", sql.viewPlayerStat(teamNameString, teamLocationString, numberInteger));
                vPPStats.setText(sql.viewPlayerStat(teamNameString, teamLocationString, numberInteger));
                vPPStats.setVisible(true);
            }
        });

        viewPlayerStatPage.add(vPPdirections);
        viewPlayerStatPage.add(vPPteamName);
        viewPlayerStatPage.add(vPPteamLocation);
        viewPlayerStatPage.add(vPPnum);
        viewPlayerStatPage.add(vPPButton);
        viewPlayerStatPage.add(vPPLabel);
        viewPlayerStatPage.add(vPPStats);

        // Card 2, view pitcher statistics page
        JPanel viewPitcherStatPage = new JPanel();
        viewPitcherStatPage.setLayout(new BoxLayout(viewPitcherStatPage, BoxLayout.PAGE_AXIS));
        JLabel vPdirections = new JLabel(
                "To view a pitcher's stats, enter a team name, location, and the player's number.");
        JTextField vPteamName = new JTextField("Enter team name", 20);
        JTextField vPteamLocation = new JTextField("Enter team location", 20);
        JTextField vPnum = new JTextField("Enter a number", 20);
        JButton vPButton = new JButton("View stats", null);
        JLabel vPStats = new JLabel("");
        vPStats.setVisible(false);
        JLabel vPLabel = new JLabel("W  L  G  GS  IP  CG  SO  S  R  ER  H  HR  BB  HBP  K  BF");
        vPLabel.setVisible(false);
        vPButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                vPLabel.setVisible(true);
                String teamNameString = vPteamName.getText();
                String teamLocationString = vPteamLocation.getText();
                Integer numberInteger = Integer.parseInt(vPnum.getText());
                System.out.printf("%s", sql.viewPitcherStat(teamNameString, teamLocationString, numberInteger));
                vPStats.setText(sql.viewPitcherStat(teamNameString, teamLocationString, numberInteger));
                vPStats.setVisible(true);
            }
        });

        viewPitcherStatPage.add(vPdirections);
        viewPitcherStatPage.add(vPteamName);
        viewPitcherStatPage.add(vPteamLocation);
        viewPitcherStatPage.add(vPnum);
        viewPitcherStatPage.add(vPButton);
        viewPitcherStatPage.add(vPLabel);
        viewPitcherStatPage.add(vPStats);

        // Card 3, view team statistics page
        JPanel viewTeamStatPage = new JPanel();
        viewTeamStatPage.setLayout(new BoxLayout(viewTeamStatPage, BoxLayout.PAGE_AXIS));
        JLabel vTdirections = new JLabel(
                "To view a team's stats, enter a team name, location, and the player's number.");
        JTextField vTteamName = new JTextField("Enter team name", 20);
        JTextField vTteamLocation = new JTextField("Enter team location", 20);
        JButton vTButton = new JButton("View stats", null);
        JLabel vTStats = new JLabel("");
        vTStats.setVisible(false);
        JLabel vTLabel = new JLabel("W  L");
        vTLabel.setVisible(false);
        vTButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                vTLabel.setVisible(true);
                String teamNameString = vTteamName.getText();
                String teamLocationString = vTteamLocation.getText();
                System.out.printf("%s", sql.viewTeamStat(teamNameString, teamLocationString));
                vTStats.setText(sql.viewTeamStat(teamNameString, teamLocationString));
                vTStats.setVisible(true);
            }
        });

        viewTeamStatPage.add(vTdirections);
        viewTeamStatPage.add(vTteamName);
        viewTeamStatPage.add(vTteamLocation);
        viewTeamStatPage.add(vTButton);
        viewTeamStatPage.add(vTLabel);
        viewTeamStatPage.add(vTStats);

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
                TsuccessMessage.setVisible(true);
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
                TsuccessMessage.setVisible(true);
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
                "To create a new position player: select a team, enter a name, number, and position. To delete enter team name, location, and number.");
        JLabel PPsuccessMessage = new JLabel("Success!");
        PPsuccessMessage.setVisible(false);
        PPsuccessMessage.setForeground(Color.GREEN);
        JLabel PPfailureMessage = new JLabel("Failed to update database.");
        PPfailureMessage.setVisible(false);
        PPfailureMessage.setForeground(Color.red);
        JTextField PPTeamName = new JTextField("Enter team name", 20);
        JTextField PPTeamLocation = new JTextField("Enter team location", 20);
        JTextField ppName = new JTextField("Enter a name", 20);
        JTextField ppNum = new JTextField("Enter a number", 20);
        JTextField ppPosition = new JTextField("Enter a position", 20);
        JButton PPcreateButton = new JButton("Create Player", null);
        PPcreateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String teamNameString = PPTeamName.getText();
                String teamLocationString = PPTeamLocation.getText();
                String nameString = ppName.getText();
                Integer numberInteger = Integer.parseInt(ppNum.getText());
                String positionString = ppPosition.getText();
                System.out.print(nameString + numberInteger + positionString);
                sql.addPositionPlayer(teamNameString, teamLocationString, nameString, numberInteger, positionString);
                PPsuccessMessage.setVisible(true);
                ppName.setText("Enter a name");
                ppNum.setText("Enter a number");
                ppPosition.setText("Enter a position");
                PPTeamName.setText("Enter team name");
                PPTeamLocation.setText("Enter team location");
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
                String teamNameString = PPTeamName.getText();
                String teamLocationString = PPTeamLocation.getText();
                Integer numberInteger = Integer.parseInt(ppNum.getText());
                sql.deletePositionPlayers(teamNameString, teamLocationString, numberInteger);
                PPsuccessMessage.setVisible(true);
                ppName.setText("Enter a name");
                ppNum.setText("Enter a number");
                ppPosition.setText("Enter a position");
                PPTeamName.setText("Enter team name");
                PPTeamLocation.setText("Enter team location");
            }
        });

        createPlayerPage.add(PPdirections);
        createPlayerPage.add(PPTeamName);
        createPlayerPage.add(PPTeamLocation);
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
        JTextField PUpTeamName = new JTextField("Enter team name", 20);
        JTextField PUpTeamLocation = new JTextField("Enter team location", 20);
        JTextField pName = new JTextField("Enter a name", 20);
        JTextField pNum = new JTextField("Enter a number", 20);
        JButton PcreateButton = new JButton("Create Pitcher", null);
        PcreateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String teamNameString = PUpTeamName.getText();
                String teamLocationString = PUpTeamLocation.getText();
                String nameString = pName.getText();
                Integer numberInteger = Integer.parseInt(pNum.getText());
                System.out.print(nameString + numberInteger);
                sql.addPitcher(teamNameString, teamLocationString, nameString, numberInteger);
                PsuccessMessage.setVisible(true);
                pName.setText("Enter a name");
                pNum.setText("Enter a number");
                PUpTeamName.setText("Enter team name");
                PUpTeamLocation.setText("Enter team location");
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
                String teamNameString = PUpTeamName.getText();
                String teamLocationString = PUpTeamLocation.getText();
                String nameString = pName.getText();
                Integer numberInteger = Integer.parseInt(pNum.getText());
                System.out.print(nameString + numberInteger);
                sql.deletePitchers(teamNameString, teamLocationString, numberInteger);
                PsuccessMessage.setVisible(true);
                pName.setText("Enter a name");
                pNum.setText("Enter a number");
                PUpTeamName.setText("Enter team name");
                PUpTeamLocation.setText("Enter team location");
            }
        });

        createPitcherPage.add(Pdirections);
        createPitcherPage.add(PUpTeamName);
        createPitcherPage.add(PUpTeamLocation);
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

        JLabel TupDirections = new JLabel(
                "To update a team, enter a team name and location with the associated stats.");
        JLabel TupSuccessMessage = new JLabel("Success!");
        TupSuccessMessage.setVisible(false);
        TupSuccessMessage.setForeground(Color.GREEN);
        JLabel TupFailureMessage = new JLabel("Failed to update database.");
        TupFailureMessage.setVisible(false);
        TupFailureMessage.setForeground(Color.red);
        JTextField UpTeamName = new JTextField("Enter team name", 20);
        JTextField UpTeamLocation = new JTextField("Enter team location", 20);
        JTextField UpTeamWins = new JTextField("Insert number of wins");
        JTextField UpTeamLosses = new JTextField("Insert number of losses");
        JButton TupdateButton = new JButton("Update team", null);
        TupdateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String teamNameString = UpTeamName.getText();
                String teamLocationString = UpTeamLocation.getText();
                Integer teamWins = Integer.parseInt(UpTeamWins.getText());
                Integer teamLosses = Integer.parseInt(UpTeamLosses.getText());
                System.out.printf("%s %s", teamNameString, teamLocationString);
                sql.updateTeams(teamNameString, teamLocationString, teamWins, teamLosses);
                TupSuccessMessage.setVisible(true);
                UpTeamName.setText("Enter team name.");
                UpTeamLocation.setText("Enter team location.");
            }
        });

        updateTeam.add(TupDirections);
        updateTeam.add(UpTeamName);
        updateTeam.add(UpTeamLocation);
        updateTeam.add(UpTeamWins);
        updateTeam.add(UpTeamLosses);
        updateTeam.add(TupdateButton);
        updateTeam.add(TupSuccessMessage);
        updateTeam.add(TupFailureMessage);

        // Card 8, Update player
        JPanel updatePlayer = new JPanel();
        updatePlayer.setLayout(new BoxLayout(updatePlayer, BoxLayout.PAGE_AXIS));
        JLabel PPupDirections = new JLabel(
                "To update a player, enter a team name, location, and number with the associated stats. Player must exist to update.");
        JLabel PPupSuccessMessage = new JLabel("Success!");
        PPupSuccessMessage.setVisible(false);
        PPupSuccessMessage.setForeground(Color.GREEN);
        JTextField pUpTeamName = new JTextField("Enter team name", 20);
        JTextField pUpTeamLocation = new JTextField("Enter team location", 20);
        JTextField PUpNum = new JTextField("Insert player number");
        JTextField UpPGames = new JTextField("Insert number of games");
        JTextField UpPpas = new JTextField("Insert number of plate appearances");
        JTextField UpPabs = new JTextField("Insert number of at bats");
        JTextField UpPruns = new JTextField("Insert number of runs");
        JTextField UpPhits = new JTextField("Insert number of hits");
        JTextField UpPdoubles = new JTextField("Insert number of doubles");
        JTextField UpPtriples = new JTextField("Insert number of triples");
        JTextField UpPhr = new JTextField("Insert number of homeruns");
        JTextField UpPrbis = new JTextField("Insert number of rbis");
        JTextField UpPwalks = new JTextField("Insert number of walks");
        JTextField UpPks = new JTextField("Insert number of strikeouts");
        JTextField UpPerrors = new JTextField("Insert number of errors");
        JButton PupdateButton = new JButton("Update stats", null);
        PupdateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String teamNameString = pUpTeamName.getText();
                String teamLocationString = pUpTeamLocation.getText();
                Integer ppnum = Integer.parseInt(PUpNum.getText());
                Integer Ppgames = Integer.parseInt(UpPGames.getText());
                Integer PpPAs = Integer.parseInt(UpPpas.getText());
                Integer abs = Integer.parseInt(UpPabs.getText());
                Integer ppRuns = Integer.parseInt(UpPruns.getText());
                Integer ppHits = Integer.parseInt(UpPhits.getText());
                Integer doubles = Integer.parseInt(UpPdoubles.getText());
                Integer triples = Integer.parseInt(UpPtriples.getText());
                Integer ppHomeruns = Integer.parseInt(UpPhr.getText());
                Integer rbis = Integer.parseInt(UpPrbis.getText());
                Integer ppWalks = Integer.parseInt(UpPwalks.getText());
                Integer ppStrikeouts = Integer.parseInt(UpPks.getText());
                Integer ppErrors = Integer.parseInt(UpPerrors.getText());
                System.out.printf("%s %s", teamNameString, teamLocationString);
                sql.updatePositionPlayers(teamNameString, teamLocationString, ppnum, Ppgames, PpPAs, abs, ppRuns,
                        ppHits, doubles, triples, ppHomeruns, rbis, ppWalks, ppStrikeouts, ppErrors);
                TupSuccessMessage.setVisible(true);
                PUpTeamName.setText("Enter team name.");
                PUpTeamLocation.setText("Enter team location.");
            }
        });

        updatePlayer.add(PPupDirections);
        updatePlayer.add(pUpTeamName);
        updatePlayer.add(pUpTeamLocation);
        updatePlayer.add(PUpNum);
        updatePlayer.add(UpPGames);
        updatePlayer.add(UpPpas);
        updatePlayer.add(UpPabs);
        updatePlayer.add(UpPruns);
        updatePlayer.add(UpPhits);
        updatePlayer.add(UpPdoubles);
        updatePlayer.add(UpPtriples);
        updatePlayer.add(UpPhr);
        updatePlayer.add(UpPrbis);
        updatePlayer.add(UpPwalks);
        updatePlayer.add(UpPks);
        updatePlayer.add(UpPerrors);
        updatePlayer.add(PupdateButton);
        updatePlayer.add(PPupSuccessMessage);

        // Card 9, Update pitcher
        JPanel updatePitcher = new JPanel();
        updatePitcher.setLayout(new BoxLayout(updatePitcher, BoxLayout.PAGE_AXIS));
        JLabel PupDirections = new JLabel(
                "To update a pitcher, enter a team name, location, and number with the associated stats. Pitcher must exist to update.");
        JLabel PupSuccessMessage = new JLabel("Success!");
        PupSuccessMessage.setVisible(false);
        PupSuccessMessage.setForeground(Color.GREEN);
        JTextField pitcherUpTeamName = new JTextField("Enter team name", 20);
        JTextField pitcherUpTeamLocation = new JTextField("Enter team location", 20);
        JTextField UpNum = new JTextField("Insert player number");
        JTextField UpWins = new JTextField("Insert number of wins");
        JTextField UpLosses = new JTextField("Insert number of losses");
        JTextField pitcherUpGames = new JTextField("Insert number of games");
        JTextField UpGamesStarted = new JTextField("Insert number of games started");
        JTextField UpIPs = new JTextField("Insert number of innings pitched");
        JTextField UpCGs = new JTextField("Insert number of complete games");
        JTextField UpSOs = new JTextField("Insert number of shutouts");
        JTextField UpSaves = new JTextField("Insert number of saves");
        JTextField Upruns = new JTextField("Insert number of runs");
        JTextField UpERs = new JTextField("Insert number of earned runs");
        JTextField UpHits = new JTextField("Insert number of hits");
        JTextField Uphrs = new JTextField("Insert number of homeruns");
        JTextField UpBBs = new JTextField("Insert number of walks");
        JTextField UpHBPs = new JTextField("Insert number of hit by pitches");
        JTextField UpKs = new JTextField("Insert number of strikeouts");
        JTextField UpBFs = new JTextField("Insert number of battersFaced");
        JButton PitcherUpdateButton = new JButton("Update stats", null);
        PitcherUpdateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String teamNameString = pitcherUpTeamName.getText();
                String teamLocationString = pitcherUpTeamLocation.getText();
                Integer ppnum = Integer.parseInt(UpNum.getText());
                Integer pWins = Integer.parseInt(UpWins.getText());
                Integer pLosses = Integer.parseInt(UpLosses.getText());
                Integer pitcherGames = Integer.parseInt(pitcherUpGames.getText());
                Integer pGS = Integer.parseInt(UpGamesStarted.getText());
                Double pIPs = Double.parseDouble(UpIPs.getText());
                Integer pCGs = Integer.parseInt(UpCGs.getText());
                Integer pSOs = Integer.parseInt(UpSOs.getText());
                Integer pSaves = Integer.parseInt(UpSaves.getText());
                Integer pRuns = Integer.parseInt(Upruns.getText());
                Integer pERs = Integer.parseInt(UpERs.getText());
                Integer pHits = Integer.parseInt(UpHits.getText());
                Integer pHRs = Integer.parseInt(Uphrs.getText());
                Integer pBBs = Integer.parseInt(UpBBs.getText());
                Integer pHBPs = Integer.parseInt(UpHBPs.getText());
                Integer pKs = Integer.parseInt(UpKs.getText());
                Integer pBFs = Integer.parseInt(UpBFs.getText());
                System.out.printf("%s %s", teamNameString, teamLocationString);
                sql.updatePitchers(teamNameString, teamLocationString, ppnum, pWins, pLosses, pitcherGames, pGS, pIPs,
                        pCGs,
                        pSOs, pSaves, pRuns, pERs, pHits, pHRs, pBBs, pHBPs, pKs, pBFs);
                PupSuccessMessage.setVisible(true);
                PUpTeamName.setText("Enter team name.");
                PUpTeamLocation.setText("Enter team location.");
            }
        });

        updatePitcher.add(PupDirections);
        updatePitcher.add(pitcherUpTeamName);
        updatePitcher.add(pitcherUpTeamLocation);
        updatePitcher.add(UpNum);
        updatePitcher.add(UpWins);
        updatePitcher.add(UpLosses);
        updatePitcher.add(pitcherUpGames);
        updatePitcher.add(UpGamesStarted);
        updatePitcher.add(UpIPs);
        updatePitcher.add(UpCGs);
        updatePitcher.add(UpSOs);
        updatePitcher.add(UpSaves);
        updatePitcher.add(Upruns);
        updatePitcher.add(UpERs);
        updatePitcher.add(UpHits);
        updatePitcher.add(Uphrs);
        updatePitcher.add(UpBBs);
        updatePitcher.add(UpHBPs);
        updatePitcher.add(UpKs);
        updatePitcher.add(UpBFs);
        updatePitcher.add(PitcherUpdateButton);

        // Add tabs to tabbed pane
        tabbedPane.addTab("View Team Stats", viewTeamStatPage);
        tabbedPane.addTab("View Player Stats", viewPlayerStatPage);
        tabbedPane.addTab("View Pitcher Stats", viewPitcherStatPage);
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
        wHeight = wHeight / 2;
        wWidth = wWidth / 2;
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