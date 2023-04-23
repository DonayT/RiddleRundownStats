import java.sql.*;

public class DatabaseHelper {

    Connection sql;

    public DatabaseHelper(String url, String user, String pswd) {
        try {
            this.sql = DriverManager.getConnection(url, user, pswd);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Insert team
    public void addTeam(String teamName, String teamLocation) {
        try {
            String insertString = "INSERT INTO Team VALUES (?, ?)";

            PreparedStatement insertStatement = sql.prepareStatement(insertString);
            insertStatement.setString(1, teamName);
            insertStatement.setString(2, teamLocation);

            int rows = insertStatement.executeUpdate();
            if (rows != 1) {
                System.out.println("ALERT: Insertion failed.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Insert position player
    public void addPositionPlayer(String teamName, String teamLocation, String name, int num, String pos) {
        try {
            String insertString = "INSERT INTO PositionPlayers VALUES (?, ?, ?, ?, ?)";

            PreparedStatement insertStatement = sql.prepareStatement(insertString);
            insertStatement.setString(1, teamName);
            insertStatement.setString(2, teamLocation);
            insertStatement.setString(3, name);
            insertStatement.setInt(4, num);
            insertStatement.setString(5, pos);

            int rows = insertStatement.executeUpdate();
            if (rows != 1) {
                System.out.println("ALERT: Insertion failed.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Insert pitcher
    public void addPitcher(String teamName, String teamLocation, String name, int num) {
        try {
            String insertString = "INSERT INTO Pitchers VALUES (?, ?, ?, ?)";

            PreparedStatement insertStatement = sql.prepareStatement(insertString);
            insertStatement.setString(1, teamName);
            insertStatement.setString(2, teamLocation);
            insertStatement.setString(3, name);
            insertStatement.setInt(4, num);

            int rows = insertStatement.executeUpdate();
            if (rows != 1) {
                System.out.println("ALERT: Insertion failed.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Delete team
    public void deleteTeam(String teamName, String teamLocation) {
        String deleteTeamString = "DELETE FROM  Team WHERE teamName = ? AND location = ?";
        String deletePositionPlayersString = "DELETE FROM PositionPlayers where teamName = ? AND location = ?";
        String deletePitchersString = "DELETE FROM Pitchers where teamName = ? AND location = ?";

        PreparedStatement deleteTeamStmt;
        PreparedStatement deletePositionPlayersStmt;
        PreparedStatement deletePitchersStmt;

        try {
            sql.setAutoCommit(false);

            // deletes team
            deleteTeamStmt = sql.prepareStatement(deleteTeamString);
            deleteTeamStmt.setString(1, teamName);
            deleteTeamStmt.setString(2, teamLocation);
            deleteTeamStmt.executeUpdate();

            // deletes player apart of deleted team
            deletePositionPlayersStmt = sql.prepareStatement(deletePositionPlayersString);
            deletePositionPlayersStmt.setString(1, teamName);
            deletePositionPlayersStmt.setString(2, teamLocation);
            deletePositionPlayersStmt.executeUpdate();

            // deletes pitcher apart of deleted team
            deletePitchersStmt = sql.prepareStatement(deletePitchersString);
            deletePitchersStmt.setString(1, teamName);
            deletePitchersStmt.setString(2, teamLocation);
            deletePitchersStmt.executeUpdate();

            sql.commit();

        } catch (SQLException e) {

            try {
                System.out.println("Rolling Back Transaction.");

                // Performs the roll back.
                sql.rollback();
            } catch (SQLException e2) {
                System.out.println(e2.getMessage());
            }

        } finally {
            try {
                sql.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void deletePositionPlayers(String teamName, String teamLocation, int num) {
        String deletePositionPlayersString = "DELETE FROM PositionPlayers where teamName = ? AND location = ? AND num - ?";

        PreparedStatement deletePositionPlayersStmt;

        try {
            sql.setAutoCommit(false);

            deletePositionPlayersStmt = sql.prepareStatement(deletePositionPlayersString);
            deletePositionPlayersStmt.setString(1, teamName);
            deletePositionPlayersStmt.setString(2, teamLocation);
            deletePositionPlayersStmt.setInt(3, num);
            deletePositionPlayersStmt.executeUpdate();

            sql.commit();
        } catch (SQLException e) {

            try {
                System.out.println("Rolling Back Transaction.");

                // Performs the roll back.
                sql.rollback();
            } catch (SQLException e2) {
                System.out.println(e2.getMessage());
            }

        } finally {
            try {
                sql.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void deletePitchers(String teamName, String teamLocation, int num) {
        String deletePitchersString = "DELETE FROM Pitchers where teamName = ? AND location = ? AND num - ?";

        PreparedStatement deletePitchersStmt;

        try {
            sql.setAutoCommit(false);

            deletePitchersStmt = sql.prepareStatement(deletePitchersString);
            deletePitchersStmt.setString(1, teamName);
            deletePitchersStmt.setString(2, teamLocation);
            deletePitchersStmt.setInt(3, num);
            deletePitchersStmt.executeUpdate();

            sql.commit();
        } catch (SQLException e) {

            try {
                System.out.println("Rolling Back Transaction.");

                // Performs the roll back.
                sql.rollback();
            } catch (SQLException e2) {
                System.out.println(e2.getMessage());
            }

        } finally {
            try {
                sql.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // Update team
    public void updateTeams(String teamName, String teamLocation, int wins, int losses) {
        try {
            String updateTeamsString = "UPDATE teams SET wins = ?, losses = ? WHERE teamname=? AND location=?";

            PreparedStatement updateTeamsPreparedStatement = sql.prepareStatement(updateTeamsString);
            updateTeamsPreparedStatement.setInt(1, wins);
            updateTeamsPreparedStatement.setInt(2, losses);
            updateTeamsPreparedStatement.setString(3, teamName);
            updateTeamsPreparedStatement.setString(4, teamLocation);

            updateTeamsPreparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Update position players
    public void updatePositionPlayers(String teamName, String teamLocation, int num, int games, int plateAppearances,
            int AtBats, int Runs, int Hits, int doubles, int triples, int HR, int rbis, int walks, int strikeouts,
            int errors) {
        try {
            String updatePositionPlayersString = "UPDATE positionPlayers SET games = ?, plateAppearances = ?, AtBats = ?, Runs = ?, Hits = ?, 2b = ?, 3b = ?, HR = ?, rbis = ?, walks = ?, strikeouts = ?, errors = ? WHERE teamname=? AND location=? AND num=?;";

            PreparedStatement updatePositionPlayersPreparedStatement = sql
                    .prepareStatement(updatePositionPlayersString);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
