import java.sql.*;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
public class mediaTrackerTest {

  /**
  * Iterates through a result set containing rows from the Boat table. Assumes
  * each row has every attribute of the Boat table.
  */
  // public static void printUserResults(ArrayList<String> AL) throws SQLException {
  //   // Iterate through the result set
  //   // The ResultSet cursor is initially positioned before the first row.
  //   // The next method moves the cursor to the next row and will return false
  //   // when there are no more rows in the ResultSet object.
  //   int id = rsTopics.getInt("topic_id");
  //   System.out.println(id);
  // }
  /**
  * Iterates through a result set containing rows from the Sailor table. Assumes
  * each row has every attribute of the Sailor table.
  */
  // public static void printTopicResults (ResultSet rsTopics) throws SQLException {
  //   while(rsTopics.next()) {
  //     // Process the current row in the result set
  //     // Specify column using its name
  //     // Could also specify column using its position, but it is better to
  //     // use the name in case the columns are not in the expected order.
  //     // ArrayList <Integer> AL = new ArrayList<Integer>();
  //     int id = rsTopics.getInt("topic_id");
  //     System.out.println(id);
  //     // AL.add(id);
  //     //String topic_section1 = rsTopics.getString("topic_section");
  //     //String media1 = rsTopics.getString("media");
  //     // for(int i = 0; i< AL.size(); i++){
  //     //   System.out.print(AL.get(i) + " ,");
  //     // }
  //   }
  //
  // }
  //
  // public static void printUserViewTopicResults (ResultSet rsUserViewTopics) throws SQLException {
  //
  //   while(rsUserViewTopics.next()) {
  //     // Process the current row in the result set
  //     // Specify column using its name
  //     // Could also specify column using its position, but it is better to
  //     // use the name in case the columns are not in the expected order.
  //     int id = rsUserViewTopics.getInt("user_id");
  //     //String topic_section1 = rsTopics.getString("topic_section");
  //     //String media1 = rsTopics.getString("media");
  //     System.out.println(id);
  //   }
  //
  // }
  //
  // public static void printMediaViewCount (ResultSet rsMediaCount) throws SQLException {
  //   while(rsMediaCount.next()) {
  //     // Process the current row in the result set
  //     // Specify column using its name
  //     // Could also specify column using its position, but it is better to
  //     // use the name in case the columns are not in the expected order.
  //     int getMedia  = rsMediaCount.getInt("topic_count");
  //     // int count = 0;
  //     // count++;
  //     //String topic_section1 = rsTopics.getString("topic_section");
  //     //String media1 = rsTopics.getString("media");
  //     System.out.println(getMedia);
  //   }
  // }
  //
  // public static void printTimesTopicViewed (ResultSet rsTimesTopicViewed) throws SQLException {
  //   while(rsTimesTopicViewed.next()) {
  //     // Process the current row in the result set
  //     // Specify column using its name
  //     // Could also specify column using its position, but it is better to
  //     // use the name in case the columns are not in the expected order.
  //     String ts = rsTimesTopicViewed.getString("topic_section");
  //     //String topic_section1 = rsTopics.getString("topic_section");
  //     //String media1 = rsTopics.getString("media");
  //     System.out.println(ts);
  //   }
  // }
  //
  // public static void printUserWhoViewBoth (ResultSet rsViewedBoth) throws SQLException {
  //   while(rsViewedBoth.next()) {
  //     // Process the current row in the result set
  //     // Specify column using its name
  //     // Could also specify column using its position, but it is better to
  //     // use the name in case the columns are not in the expected order.
  //     int id = rsViewedBoth.getInt("user_id");
  //     //String topic_section1 = rsTopics.getString("topic_section");
  //     //String media1 = rsTopics.getString("media");
  //     System.out.println(id);
  //   }
  // }

  public static void printArrayList (ArrayList<String> AL) throws SQLException {
    for (int i = 0 ; i < AL.size(); i++){
      if(i < AL.size() - 1){
        System.out.print(AL.get(i) + ", ");

      } else {
        System.out.print(AL.get(i));
      }
    }
  }



  public static void main(String[] args) throws SQLException {

    // Create a mediaTracker object
    mediaTracker bre = new mediaTracker();

    // ArrayList to test
    ArrayList <String> AL = new ArrayList<>();

    AL = bre.runUserAgeQuery(30);
    System.out.println("\n user_id of Users whose age is 30:");
    System.out.println("\n ------------------------------");
    printArrayList(AL);
    System.out.println("\n ------------------------------");

    AL = bre.runUserAgeQuery(20);
    System.out.println("\n user_id of Users whose age is 20:");
    System.out.println("\n ------------------------------");
    printArrayList(AL);
    System.out.println("\n ------------------------------");

    AL = bre.runTopicsViewedAgeQuery(30);
    System.out.println("\n Distinct topic_id view by users 30 years old");
    System.out.println("\n ------------------------------");
    printArrayList(AL);
    System.out.println("\n ------------------------------");

    AL = bre.runTopicsViewedAgeQuery(20);
    System.out.println("\n Distinct topic_id view by users 20 years old");
    System.out.println("\n ------------------------------");
    printArrayList(AL);
    System.out.println("\n ------------------------------");


    AL = bre.runUserViewedTopicQuery("cras");
    System.out.println("\n Distinct user_id who viewed topic_section cras");
    System.out.println("\n ------------------------------");
    printArrayList(AL);
    System.out.println("\n ------------------------------");


    AL = bre.runUserViewedTopicQuery("dapibus");
    System.out.println("\n Distinct user_id who viewed topic_section cras");
    System.out.println("\n ------------------------------");
    printArrayList(AL);
    System.out.println("\n ------------------------------");

    AL = bre.runMediaViewedCount("video");
    System.out.println("\n Count times users view media type videos");
    System.out.println("\n ------------------------------");
    printArrayList(AL);
    System.out.println("\n ------------------------------");

    AL = bre.runMediaViewedCount("photo");
    System.out.println("\n Count times users view media type photo");
    System.out.println("\n ------------------------------");
    printArrayList(AL);
    System.out.println("\n ------------------------------");


    AL = bre.runMediaViewedCount("article");
    System.out.println("\n Count times users view media type article");
    System.out.println("\n ------------------------------");
    printArrayList(AL);
    System.out.println("\n ------------------------------");


    AL = bre.runTimesTopicViewedQuery(2);
    System.out.println("\n Distinct topic_sections that are viewed 2 times");
    System.out.println("\n ------------------------------");
    printArrayList(AL);
    System.out.println("\n ------------------------------");

    AL = bre.runTimesTopicViewedQuery(4);
    System.out.println("\n Distinct topic_sections that are viewed 4 times");
    System.out.println("\n ------------------------------");
    printArrayList(AL);
    System.out.println("\n ------------------------------");

    AL = bre.runUserWhoViewBothQuery("cras", "eget");
    System.out.println("\n user_id of User who viewed 2 topic_section cras and eget, should be none for this one");
    System.out.println("\n ------------------------------");
    printArrayList(AL);
    System.out.println("\n ------------------------------");

    AL = bre.runUserWhoViewBothQuery("dapibus", "sapien");
    System.out.println("\n user_id of User who viewed 2 topic_section cras and eget");
    System.out.println("\n ------------------------------");
    printArrayList(AL);
    System.out.println("\n ------------------------------");


    bre.close();

	}







}
