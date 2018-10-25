import MyException.FullUserException;
import Users.RegistratedUsers;

import java.sql.*;
import java.util.ArrayList;

public class Event {
    static Event festival = new Event();
    FestivalInfo rockFestivalInfo = new FestivalInfo();
    ArrayList<RegistratedUsers> users = new ArrayList<RegistratedUsers>();

    public static void main(String[] args) throws SQLException {
        // Connecting
         final String URL = "jdbc:mysql://localhost:3306/festivaldata?autoReconnect=true&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
         final String USER = "root";
         final String PASSWORD = "5681751_koliana";
        Connection conn = DriverManager.getConnection(URL,USER,PASSWORD);

            //Select anf Insert
        PreparedStatement stat = conn.prepareStatement(
                "SELECT Nikname, SecName, PASSWORD FROM Users " + "Where ID BETWEEN ? AND ?");
        stat.setInt(1, 1);
        stat.setInt(2, 6);
        stat.executeUpdate("INSERT INTO Users (`ID`, `Nikname`, `NAME`, `SecName`, `PASSWORD`, `AGE`, `EMAIL`) VALUES (6, 'MaRO', 'MaROM', 'Monarx', 'Mary123', 44, 'mary23@gmail.com')");

        ResultSet set = stat.executeQuery();
        while (set.next()) {
            System.out.println(set.getString("Nikname") + " " + set.getString("SecName") + " "
                    + set.getString("PASSWORD"));
        }

        // UPDATE
        int count = stat.executeUpdate("UPDATE festivaldata.Users SET Nikname = 'Lelik Faggot' WHERE ID>1");
        System.out.println("Rows affected: " + count);

        // Select after update
        stat.setInt(1, 1);
        stat.setInt(2, 6);

        ResultSet setUpd = stat.executeQuery();
        while (setUpd.next()) {
            System.out.println(setUpd.getString("Nikname") + " " + setUpd.getString("SecName") + " "
                    + setUpd.getString("PASSWORD"));
        }
//        try {
//            festival.registration();
//        }
//        catch (FullUserException e){
//            System.out.println("Регистрация невозможна. Все билеты проданы.");
//        }
    }
    public void registration() throws FullUserException{
        if (users.size()>rockFestivalInfo.maxUsers)  throw new FullUserException();
        users.add(new RegistratedUsers());
    }
}
