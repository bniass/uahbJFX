package uahb.stic2.coursfx.utils;

import java.sql.*;

public class DatabaseHelper {
    private Connection cnx;
    private PreparedStatement pstmt;

    public PreparedStatement getPstmt() {
        return pstmt;
    }

    private void openConnection(){
        try {
            if(cnx == null || cnx.isClosed()){
                Class.forName("com.mysql.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/coursjavauahb";
                String user = "root", pwd = "";
                cnx = DriverManager.getConnection(url, user, pwd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void myPreparedStatement(String sql, Object[] parametres){
        try {
            openConnection();
            if(sql.toUpperCase().trim().startsWith("INSERT")){
                pstmt = cnx.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            }
            else{
                pstmt = cnx.prepareStatement(sql);
            }

            for (int i = 0; i < parametres.length; i++) {
                pstmt.setObject(i+1, parametres[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet mySelect(String tableName){
        try {
            openConnection();
            Statement stmt = cnx.createStatement();
            return stmt.executeQuery("SELECT * FROM "+tableName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet myExecuteQuery(){
        try {
            return pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public int myExecuteUpdate(){
        try {
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public void closeConnection(){
        try {
            if(pstmt != null && !pstmt.isClosed()){
                pstmt.close();
                pstmt = null;
            }
            if(cnx != null && !cnx.isClosed()){
                cnx.close();
                cnx = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
