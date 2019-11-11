package sample.database;

import sample.model.User;

import java.sql.*;

public class DatabaseHandler extends Configs{
    Connection dbConnection;

    public Connection getDbConnection() throws SQLException, ClassNotFoundException {
        String connectionString = "jdbc:mysql://" + dbHost + "/" + dbName;
        Class.forName("com.mysql.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString,dbUser,dbPass);
        return dbConnection;
    }
//    Write to DB
    public void signUpUser(User user){
        String insert = "INSERT INTO " + Constant.USERS_TABLE + "(" +
                Constant.USERS_FIRSTNAME + "," +
                Constant.USERS_LASTNAME + "," +
                Constant.USERS_USERNAME + "," +
                Constant.USERS_PASSWORD + "," +
                Constant.USERS_LOCATION + "," +
                Constant.USERS_GENDER + ")" +
                "VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1,user.getFirstname());
            preparedStatement.setString(2,user.getLastname());
            preparedStatement.setString(3,user.getUsername());
            preparedStatement.setString(4,user.getPassword());
            preparedStatement.setString(5,user.getLocation());
            preparedStatement.setString(6,user.getGender());
//            update does not return
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getUser(User user){
        ResultSet resultSet = null;
        if (!user.getUsername().equals("") || !user.getPassword().equals("")){
//            SELECT * FROM USER WHERE USERNAME =? AND PASSWORD =?;
            String query = "SELECT * FROM " + Constant.USERS_TABLE + " WHERE " + Constant.USERS_USERNAME + "=?" + " AND " + Constant.USERS_PASSWORD + "=?";
            try {
                PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
                preparedStatement.setString(1,user.getUsername());
                preparedStatement.setString(2,user.getPassword());
//                query return
                resultSet = preparedStatement.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Error");
        }
        return resultSet;
    }
//    Read DB

//    Update DB

//    Delete from DB

}
