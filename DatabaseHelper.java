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
            String insertString = "INSERT INTO Team VALUES (?, ?, 0, 0)";

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

    // Update position player
    public void updatePositionPlayers(String teamName, String teamLocation, int num, int games, int plateAppearances,
            int AtBats, int Runs, int Hits, int doubles, int triples, int HR, int rbis, int walks, int strikeouts,
            int errors) {
        try {
            String updatePositionPlayersString = "UPDATE positionPlayers SET games = ?, plateAppearances = ?, AtBats = ?, Runs = ?, Hits = ?, 2b = ?, 3b = ?, HR = ?, rbis = ?, walks = ?, strikeouts = ?, errors = ? WHERE teamname=? AND location=? AND num=?";

            PreparedStatement updatePositionPlayersPreparedStatement = sql
                    .prepareStatement(updatePositionPlayersString);
            updatePositionPlayersPreparedStatement.setInt(1, games);
            updatePositionPlayersPreparedStatement.setInt(2, plateAppearances);
            updatePositionPlayersPreparedStatement.setInt(3, AtBats);
            updatePositionPlayersPreparedStatement.setInt(4, Runs);
            updatePositionPlayersPreparedStatement.setInt(5, Hits);
            updatePositionPlayersPreparedStatement.setInt(6, doubles);
            updatePositionPlayersPreparedStatement.setInt(7, triples);
            updatePositionPlayersPreparedStatement.setInt(8, HR);
            updatePositionPlayersPreparedStatement.setInt(9, rbis);
            updatePositionPlayersPreparedStatement.setInt(10, walks);
            updatePositionPlayersPreparedStatement.setInt(11, strikeouts);
            updatePositionPlayersPreparedStatement.setInt(12, errors);
            updatePositionPlayersPreparedStatement.setString(13, teamName);
            updatePositionPlayersPreparedStatement.setString(14, teamLocation);
            updatePositionPlayersPreparedStatement.setInt(15, num);

            updatePositionPlayersPreparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Update pitcher
    public void updatePitchers(String teamName, String teamLocation, int num, int wins, int losses, int games,
            int gamesStarted, int inningsPitched, int completeGames, int shutouts, int saves, int runs, int earnedRuns,
            int hits, int homeruns, int walks, int hitByPitches, int strikeouts, int battersFaced) {
        try {
            String updatePitchersString = "UPDATE pitchers SET wins = ?, losses = ?, games = ?, gamesStarted = ?, inningsPitched = ?, completeGames = ?, shutouts = ?, saves = ?, runs = ?, earnedRuns = ?, hits = ?, homeruns = ?, walks = ?, hitByPitches = ?, strikeouts = ?, battersFaced = ? WHERE teamname=?' AND location=? AND num=?";

            PreparedStatement updatePitchersPreparedStatement = sql.prepareStatement(updatePitchersString);

            updatePitchersPreparedStatement.setInt(1, wins);
            updatePitchersPreparedStatement.setInt(2, losses);
            updatePitchersPreparedStatement.setInt(3, games);
            updatePitchersPreparedStatement.setInt(4, gamesStarted);
            updatePitchersPreparedStatement.setInt(5, inningsPitched);
            updatePitchersPreparedStatement.setInt(6, completeGames);
            updatePitchersPreparedStatement.setInt(7, shutouts);
            updatePitchersPreparedStatement.setInt(8, saves);
            updatePitchersPreparedStatement.setInt(9, runs);
            updatePitchersPreparedStatement.setInt(10, earnedRuns);
            updatePitchersPreparedStatement.setInt(11, hits);
            updatePitchersPreparedStatement.setInt(12, homeruns);
            updatePitchersPreparedStatement.setInt(13, walks);
            updatePitchersPreparedStatement.setInt(14, hitByPitches);
            updatePitchersPreparedStatement.setInt(15, strikeouts);
            updatePitchersPreparedStatement.setInt(16, battersFaced);
            updatePitchersPreparedStatement.setString(17, teamName);
            updatePitchersPreparedStatement.setString(18, teamLocation);
            updatePitchersPreparedStatement.setInt(19, num);

            updatePitchersPreparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
