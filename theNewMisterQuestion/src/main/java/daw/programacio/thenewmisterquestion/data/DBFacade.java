package daw.programacio.thenewmisterquestion.data;
import daw.programacio.thenewmisterquestion.models.CategoryModel;
import daw.programacio.thenewmisterquestion.models.PlayerModel;
import daw.programacio.thenewmisterquestion.models.QuestionModel;

import java.sql.*;
import java.util.ArrayList;

public final class DBFacade {
    private static final String pathToDb = "jdbc:sqlite:theNewMisterQuestion/src/main/java/daw/programacio/thenewmisterquestion/data/";
    private static final String db = "sqlite.db";
    private static Connection conn;


    public static boolean logOn() {
        String url = pathToDb + db;

        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        if (conn != null) {
            return true;
        }
        return false;
    }

    public static void logOff() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

    public static ArrayList<QuestionModel> selectQuestions() {
        String sql = "SELECT q.*, c.name FROM question q, category c WHERE c.id = q.category;";
        ArrayList<QuestionModel> questions = new ArrayList<>();
        if (conn != null) {
            Statement stmt = null;
            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    questions.add(new QuestionModel(rs.getInt(1), rs.getString(2),
                            rs.getString(3), rs.getString(4), rs.getString(5),
                            rs.getString(6),rs.getString(9) , rs.getInt(7)));
                    System.out.println(rs.getInt(1));
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return questions;
    }

    public static ArrayList<CategoryModel> selectCategories() {
        String sql = "SELECT * FROM category;";
        ArrayList<CategoryModel> categories = new ArrayList<>();
        if (conn != null) {
            Statement stmt = null;
            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    categories.add(new CategoryModel(rs.getByte(1), rs.getString(2)));
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return categories;
    }

    public static ArrayList<PlayerModel> selectPlayers() {
        String sql = "SELECT name, score FROM player ORDER BY score DESC;";
        ArrayList<PlayerModel> players = new ArrayList<>();
        if (conn != null) {
            Statement stmt = null;
            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    players.add(new PlayerModel(rs.getString(1), rs.getInt(2)));
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return players;
    }

    public static void addCategory(String name) {
        String sql = "INSERT INTO category (name) VALUES ('" + name + "')";
        if (conn != null) {
            Statement stmt = null;
            try {
                stmt = conn.createStatement();
                stmt.execute(sql);
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static void deleteCategory(String name) {
        String sql = "DELETE FROM category WHERE name = '" + name + "'";
        if (conn != null) {
            Statement stmt = null;
            try {
                stmt = conn.createStatement();
                stmt.execute(sql);
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static void addQuestion(String question, String a, String b, String c, int correct, int category, int value){
        String sql = "INSERT INTO question (question, answer_A, answer_B, answer_C, correct_answer, category, value) values" +
                "('" + question + "', '" + a + "', '" + b + "', '" + c + "', " +
                correct + ", " + category + ", " + value + ")";
        if (conn != null) {
            Statement stmt = null;
            try {
                stmt = conn.createStatement();
                stmt.execute(sql);
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static void deleteQuestion(String question){
        String sql = "DELETE FROM question WHERE question = '" + question + "'";
        if (conn != null) {
            Statement stmt = null;
            try {
                stmt = conn.createStatement();
                stmt.execute(sql);
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static int getCategoryId(String category){
        String sql = "SELECT id FROM category WHERE name = '" + category + "'";
        ArrayList<PlayerModel> players = new ArrayList<>();
        if (conn != null) {
            Statement stmt = null;
            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                if(rs.next())
                    return rs.getInt(1);
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return -1;
    }

    public static PlayerModel getPlayer(String name){
        String sql = "SELECT * FROM player WHERE name = '" + name + "'";
        if(conn != null){
            Statement stmt = null;
            try{
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                if(rs.next())
                    return new PlayerModel(rs.getString(2), rs.getInt(3));
            }catch (SQLException e){
                System.err.println(e.getMessage());
            }
        }
        return null;
    }

    public static void addPlayer(String name, int score){
        String sql = "INSERT INTO player (name, score) VALUES ('" + name + "', " + score + ")";
        if(conn != null){
            Statement stmt = null;
            try{
                stmt = conn.createStatement();
                stmt.execute(sql);
            }catch (SQLException e){
                System.err.println(e.getMessage());
            }
        }
    }

    public static QuestionModel getRandomQuestionFromCategory(String category){
        ArrayList<QuestionModel> questions = new ArrayList<>();
        String sql = "SELECT q.* FROM question q, category c WHERE c.id = q.category AND c.name = '" + category + "'";
        if(conn != null){
            Statement stmt = null;
            try{
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()){
                    questions.add(new QuestionModel(rs.getInt(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getString(5), rs.getString(6), category, rs.getInt(7)));
                }
                return questions.get((int)(Math.random() * (questions.size())));
            }catch (SQLException e){
                System.err.println(e.getMessage());
            }
        }
        return null;
    }

    public static void updateScore(PlayerModel playerModel) {
        String sql = "UPDATE player SET score = " + playerModel.getScore() + " WHERE name = '" + playerModel.getName() + "'";
        if (conn != null) {
            Statement stmt = null;
            try {
                stmt = conn.createStatement();
                stmt.execute(sql);
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
