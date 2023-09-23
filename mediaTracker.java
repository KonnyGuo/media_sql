/**
* Practice program for connecting to a MySQL database and issuing queries.
*
* Compile this program with:
javac -cp .:./mariadb-java-client-2.7.3.jar mediaTracker.java mediaTrackerTest.java

*
* Run this program with:
java -cp .:./mariadb-java-client-2.7.3.jar mediaTrackerTest
*
*/

//go into .SQL
//run my sql

import java.sql.*;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class mediaTracker {
  /*
  NOTE: for better security do not put your database username and password
  in the code. Put them into a text file, then have the program read the text
  file. For best security, save the password in an encrypted file.
  With this more secure design, the file pathname could be passed as a parameter
  to the constructor and the constructor could read the database name, username,
  and password from the file.
  */

  /** Name of the database */
  private static final String dbName = ""

  /** Database username */
  private static final String dbUser = ""

  /** Database password */
  private static final String dbPass = ""

  /** A database connection */
  Connection dbConnect;

  /** A Statement object for executing arbitrary SQL statements */
  Statement dbStatement;

  /** Prepared statement for query that finds Users_ids of a given age */
  PreparedStatement psUserAge;

  //Prepared statment for query that finds distinct topics view by user of given age
  PreparedStatement psTopicsViewedAge;

  //Prepared statement for query that finds Users who view a certain topic_section
  PreparedStatement psUserViewedTopic;

  //Prepared statement for query that finds User with amount of interaction with app
  PreparedStatement psMediaViewedCount;

  //Prepared statement for query that finds topics that are viewed by Users a specific amount of time
  PreparedStatement psTimesTopicViewed;

  //Prepared statement for query that finds 2 different topic sections view by a User
  PreparedStatement psUserWhoViewBoth;

  /**
  * Constructor connects to the database and initializes prepared statements.
  */
  public mediaTracker() throws SQLException {

    // Connect to the database
		this.dbConnect = DriverManager.getConnection(
			);

    // Initialize the database statement object
    this.dbStatement = dbConnect.createStatement();

    // Initialize prepared statements
    String userAgeSQL = "SELECT U.user_id FROM User U WHERE U.user_age = ?";
    this.psUserAge = dbConnect.prepareStatement(userAgeSQL);

    String TopicsViewedAgeSQL = "Select DISTINCT(T.topic_id) From User U, viewTopics VT, topics T Where (U.user_id = VT.user_id) AND (T.topic_id = VT.topic_id) AND U.user_age = ?";
    this.psTopicsViewedAge = dbConnect.prepareStatement(TopicsViewedAgeSQL);

    String UserViewedTopicSQL = "Select DISTINCT(U.user_id) From User U, viewTopics VT, topics T  Where (U.user_id = VT.user_id) AND (T.topic_id = VT.topic_id) AND T.topic_section = ?";
    this.psUserViewedTopic = dbConnect.prepareStatement(UserViewedTopicSQL);

    String MediaViewedCountSQL = "Select COUNT((T.media)) as topic_count FROM User U, topics T, viewTopics VT WHERE (U.user_id = VT.user_id) AND (T.topic_id = VT.topic_id) AND T.media = ?";
    this.psMediaViewedCount = dbConnect.prepareStatement(MediaViewedCountSQL);

    String TimesTopicViewedSQL = "Select DISTINCT(T.topic_section) FROM User U, topics T, viewTopics VT WHERE (U.user_id = VT.user_id) AND (T.topic_id = VT.topic_id) GROUP BY T.topic_section HAVING COUNT(DISTINCT(T.topic_id)) = ?";
    this.psTimesTopicViewed = dbConnect.prepareStatement(TimesTopicViewedSQL);

    String UserWhoViewBothSQL = "Select U.user_id From User U, viewTopics VT, topics T Where (U.user_id = VT.user_id) AND (T.topic_id = VT.topic_id) AND (T.topic_section = ?) INTERSECT Select U.user_id From User U, viewTopics VT, topics T Where (U.user_id = VT.user_id) AND (T.topic_id = VT.topic_id) AND (T.topic_section = ?)";
    this.psUserWhoViewBoth = dbConnect.prepareStatement(UserWhoViewBothSQL);
  }

  public void close() throws SQLException {
    // Close the database objects
    this.dbStatement.close();
    this.psUserAge.close();
    this.psTopicsViewedAge.close();
    this.psUserViewedTopic.close();
    this.psMediaViewedCount.close();
    this.psTimesTopicViewed.close();
    this.psUserWhoViewBoth.close();
    this.dbConnect.close();
  }

  /**
  * Runs the query passed as a parameter.
  *
  * @param q The query to run.
  * @return The result set returned by the query.
  */
  // public ResultSet runQuery(String q) throws SQLException {
  //   // The query result is returned as a ResultSet object.
	// 	// The SQL query is specified as a parameter to the executeQuery method
	// 	ResultSet rs = this.dbStatement.executeQuery(q);
  //
  //   return rs;
  // }

  //String userAgeSQL = "SELECT U.user_id FROM User U WHERE U.user_age = ?";
  /**
  * Runs the User age prepared statement.
  * @param a The age of User to query.
  * @return The result set returned by the query.
  */
  public ArrayList<String> runUserAgeQuery(double a) throws SQLException {
    // The PreparedStatement class has set methods for setting the query parameters.
    // The first parameter to this method is the index of the query parameter,
    // with the first query parameter at index 1, second at index 2, etc.
    // The second paramter to this method is the value for the query parameter.
    // ArrayList<String> AL = new ArrayList<>();
    this.psUserAge.setDouble(1, a);

    // Execute the query, save the result set
    ResultSet rs = this.psUserAge.executeQuery();
    ArrayList<String> AL = new ArrayList<>();
    while(rs.next()){
      int id = rs.getInt("user_id");
      AL.add(id + " ");
    }

    return AL;
  }

  //String TopicsViewedAgeSQL = "Select DISTINCT(T.topic_id) From Topic T From User U, viewTopics VT, topics T Where (U.user_id = VT.user_id) AND (T.topic_id = VT.topic_id) AND U.user_age = ?";
  /**
  * Runs the query that finds distinct topics view by user of given age
  * @param a The age of User to query.
  * @return The result set returned by the query.
  */
  public ArrayList<String> runTopicsViewedAgeQuery (double a) throws SQLException {
    // The PreparedStatement class has set methods for setting the query parameters.
    // The first parameter to this method is the index of the query parameter,
    // with the first query parameter at index 1, second at index 2, etc.
    // The second paramter to this method is the value for the query parameter.
    this.psTopicsViewedAge.setDouble(1, a);

    // Execute the query, save the result set
    ResultSet rs = this.psTopicsViewedAge.executeQuery();
    ArrayList<String> AL = new ArrayList<>();
    while(rs.next()){
      int id = rs.getInt("topic_id");
      AL.add(id + " ");
    }

    return AL;
  }

  //String UserViewedTopicSQL = "Select DISTINCT(U.user_id) From User U, viewTopics VT, topics T  Where (U.user_id = VT.user_id) AND (T.topic_id = VT.topic_id) AND T.topic_section = ?";
  /**
  * Runs the query that finds Users who view a certain topic_section
  * @param a The topic_section for the querty
  * @return The result set returned by the query.
  */
  public ArrayList<String> runUserViewedTopicQuery (String a) throws SQLException {
    // The PreparedStatement class has set methods for setting the query parameters.
    // The first parameter to this method is the index of the query parameter,
    // with the first query parameter at index 1, second at index 2, etc.
    // The second paramter to this method is the value for the query parameter.

    // ArrayList<ResultSet> rs = new ArrayList<>();
    this.psUserViewedTopic.setString(1, a);
    //rs.add(this.psUserViewedTopic.executeQuery())

    // Execute the query, save the result set
    ResultSet rs = this.psUserViewedTopic.executeQuery();
    ArrayList<String> AL = new ArrayList<>();
    while(rs.next()){
      int id = rs.getInt("user_id");
      AL.add(id + " ");
    }

    return AL;
  }

  //String MediaViewedCountSQL = "Select COUNT((T.media)) FROM User U, topics T, viewTopics VT WHERE (U.user_id = VT.user_id) AND (T.topic_id = VT.topic_id) AND T.media = ?"";
  /**
  * Runs the query that count amount of time certain media is counted
  * @param a string for the media type
  * @return The result set returned by the query.
  */
  public ArrayList<String> runMediaViewedCount (String a) throws SQLException {
    // The PreparedStatement class has set methods for setting the query parameters.
    // The first parameter to this method is the index of the query parameter,
    // with the first query parameter at index 1, second at index 2, etc.
    // The second paramter to this method is the value for the query parameter.
    this.psMediaViewedCount.setString(1, a);

    // Execute the query, save the result set
    ResultSet rs = this.psMediaViewedCount.executeQuery();
    ArrayList<String> AL = new ArrayList<>();
    while(rs.next()){
      int getMedia  = rs.getInt("topic_count");
      AL.add(getMedia + " ");
    }

    return AL;
  }

  //String TimesTopicViewedSQL = "Select DISTINCT(T.topic_section) FROM User U, topics T, viewTopics VT WHERE (U.user_id = VT.user_id) AND (T.topic_id = VT.topic_id) GROUP BY T.topic_section HAVING COUNT(DISTINCT(T.topic_id)) < ?";

  /**
  * Runs the query that finds topics that are viewed by Users a specific amount of time
  * @param a int value for finding topic that Users have view less than x times
  * @return The result set returned by the query.
  */
  public ArrayList<String> runTimesTopicViewedQuery (double a) throws SQLException {
    // The PreparedStatement class has set methods for setting the query parameters.
    // The first parameter to this method is the index of the query parameter,
    // with the first query parameter at index 1, second at index 2, etc.
    // The second paramter to this method is the value for the query parameter.
    this.psTimesTopicViewed.setDouble(1, a);

    // Execute the query, save the result set
    ResultSet rs = this.psTimesTopicViewed.executeQuery();
    ArrayList<String> AL = new ArrayList<>();
    while(rs.next()){
      String getMedia  = rs.getString("topic_section");
      AL.add(getMedia + " ");
    }

    return AL;
  }
  //  String UserWhoViewBothSQL = "Select U.user_id From User U, viewTopics VT, topics T Where (U.user_id = VT.user_id) AND (T.topic_id = VT.topic_id) AND (T.topic_section = ?) INTERSECT Select U.user_id From User U, viewTopics VT, topics T Where (U.user_id = VT.user_id) AND (T.topic_id = VT.topic_id) AND (T.topic_section = ?)";

  /**
  * Runs the query that finds topics that are viewed by Users a specific amount of time
  * @param a inResultSett value for finding topic that Users have view less than x times
  * @return The result set returned by the query.
  */
  public ArrayList<String> runUserWhoViewBothQuery (String a, String b) throws SQLException {
    // The PreparedStatement class has set methods for setting the query parameters.
    // The first parameter to this method is the index of the query parameter,
    // with the first query parameter at index 1, second at index 2, etc.
    // The second paramter to this method is the value for the query parameter.
    this.psUserWhoViewBoth.setString(1, a);
    this.psUserWhoViewBoth.setString(2, b);

    // Execute the query, save the result set
    ResultSet rs = this.psUserWhoViewBoth.executeQuery();
    ArrayList<String> AL = new ArrayList<>();
    while(rs.next()){
      int id  = rs.getInt("user_id");
      AL.add(id + " ");
    }

    return AL;
  }

}
