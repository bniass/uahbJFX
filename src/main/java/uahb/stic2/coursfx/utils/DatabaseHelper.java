package uahb.stic2.coursfx.utils;

import java.sql.*;

public class DatabaseHelper {
    private Connection cnx;
    private PreparedStatement pstmt;

    public PreparedStatement getPstmt() {
        return pstmt;
    }

    private static DatabaseHelper db;

    private DatabaseHelper()
    {

    }

    public static DatabaseHelper getInstance(){
        if(db == null){
            db = new DatabaseHelper();
        }
        return db;
    }

    private void openConnection() throws Exception{
        try {
            if(cnx == null || cnx.isClosed()){
                Class.forName("com.mysql.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/coursjavauahb";
                String user = "root", pwd = "";
                cnx = DriverManager.getConnection(url, user, pwd);
            }
        } catch (Exception e) {
            throw e;
        }
    }
    public void myPreparedStatement(String sql, Object[] parametres) throws Exception{

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
            throw e;
        }
    }

    public ResultSet mySelect(String tableName) throws Exception{
        try {
            openConnection();
            Statement stmt = cnx.createStatement();
            return stmt.executeQuery("SELECT * FROM "+tableName);
        } catch (Exception e) {
            throw e;
        }
    }

    public ResultSet myExecuteQuery() throws Exception{
        try {
            return pstmt.executeQuery();
        } catch (SQLException e) {
            throw e;
        }
    }
    public int myExecuteUpdate() throws Exception{
        try {
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }
    public void closeConnection() throws Exception{
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
            throw e;
        }
    }

    public void beginTransaction() throws Exception
    {
        try {
            openConnection();
            cnx.setAutoCommit(false);
        }catch (Exception e) {
            throw e;
        }
    }
    public void endTransaction() throws Exception
    {
        try {
            cnx.setAutoCommit(true);
        }catch (Exception e) {
            throw e;
        }
    }
    public void myCommit() throws Exception{
        try {
            cnx.commit();
        }catch (Exception e) {
            throw e;
        }
    }
    public void myRollback() throws Exception{
        try {
            cnx.commit();
        }catch (Exception e) {
            throw e;
        }
    }
}
