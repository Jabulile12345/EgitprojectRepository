package Module16;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Scanner;
import com.mysql.jdbc.*;
public class Egitproject {
// code for connection
Git2 git = new Git2();
static Connection connect = null;
private static void connectionQuery() {
try{
Class.forName("com.mysql.jdbc.Driver");
connect
=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject","root","jubilant");
if(connect!=null){
System.out.println("Connected");
}
}
catch(Exception e){
System.out.println(" Not connected ");
}
}
public static void displayselection(){
// using a scanner to enter information
Scanner input = new Scanner(System.in);
System.out.println(" Enter\n"+ "1 Display Data \n" + " " + "2 Add new Lecture \n"+ " " + "3 Delete Lecture from the Database\n " + " " +" 4 Update Database ");
// declaring switch statement to select what you want to perform
int choose=input.nextInt();
switch(choose){
case 1:
connectionQuery();
display();
break;
case 2:
add();
break;
case 3:
delete();
break;
case 4:
Update();
break;
default:
System.out.println("Invalid Selection");
}
input.close();
}
// creating a method to display data
public static void display()
{
int LectureId,Lecturephone;
String Firstname,Lastname,LectureSalary,LectureModule;
// using a try and catch to connect and catch the exception
try{
connect =
(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject","root","jubilant");
PreparedStatement state = connect.prepareStatement("SELECT * from lecture");
ResultSet results = state.executeQuery();
while(results.next())
{
LectureId = results.getInt("LectureId");
Firstname = results.getString("Firstname");
Lastname = results.getString("Lastname");
Lecturephone=results.getInt("Lecturephone");
LectureSalary = results.getString("LectureSalary");
LectureModule = results.getString("LectureModule");
System.out.println("user's ID: " + LectureId + "\n" + "NAME: " +
Firstname +"\n"+ "SURNAME: " + Lastname + "MOBILE NUMBER"+ Lecturephone+"\n");
}
connect.close();
}
catch (SQLException e)
{
System.out.println("failed to connect ");
}
}
public static void add()
{
connectionQuery();
Scanner input = new Scanner(System.in);
try
{
connect =
(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject","root","jubilant");
if (connect != null)
{
int LectureId;
String Firstname = null;
String Lastname = null;
int Lecturephone;
int LectureSalary;
String LectureModule = null;
System.out.println("Add data to the database");
System.out.println("Enter your identity number");
LectureId = input.nextInt();
System.out.println("Enter your Firstname");
Firstname = input.next();
System.out.println("Enter your Lastname");
Lastname = input.next();
System.out.println("Enter your Digits");
Lecturephone = input.nextInt();
System.out.println("Enter your Salary");
LectureSalary = input.nextInt();
System.out.println("Enter your LectureModule");
LectureModule = input.next();
input.close();
// inserting data into the database
String lec = "INSERT INTO lecture (LectureId,Firstname,Lastname,Lecturephone,LectureSalary,LectureModule) VALUES (?,?,?,?,?,?)";
PreparedStatement line = connect.prepareStatement(lec);
line.setInt(1,LectureId );
line.setString( 2,Firstname);
line.setString(3,Lastname);
line.setInt(4,Lecturephone);
line.setInt(5,LectureSalary);
line.setString(6,LectureModule);
line.executeUpdate();
System.out.println("Added");
}
}
catch(Exception e)
{
System.out.println("Not connected ");
}
}
// creating a method to delete data form the database
public static void delete(){
int id=0;
connectionQuery();
try
{
Class.forName("com.mysql.jdbc.Driver");
connect =
(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject","root","jubilant");
Scanner input = new Scanner(System.in);
System.out.println("Please enter your id");
id =input.nextInt();
String delete = "DELETE FROM lecture WHERE LectureId = '" + id +"' ";
PreparedStatement statement = connect.prepareStatement(delete);
statement.executeUpdate();
System.out.println("Data deleted");
display();
connect.close();
input.close();
}
catch (Exception e)
{
System.out.println("Data not deleted");
}
}
// method to update data from the database
public static void Update()
{
int LectureId,Lecturephone;
try
{
Class.forName("com.mysql.jdbc.Driver");
connect =
(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject","root","jubilant");
Scanner input = new Scanner(System.in);
System.out.println(" Enter your Id");
LectureId = input.nextInt();
System.out.println("Enter your Digits");
Lecturephone =input.nextInt();
System.out.println("Enter your Salary");
Lecturephone =input.nextInt();
String update = "UPDATE lecture SET Lecturephone='"+ Lecturephone+"' WHERE LectureId ='" +LectureId + "' ";
PreparedStatement statement = connect.prepareStatement(update);
statement.executeUpdate();
System.out.println("Data updated");
input.close();
}
catch (Exception e )
{
System.out.println(e.getMessage());
}
}
public static void main(String[] args) throws Exception{
displayselection();
}
}
