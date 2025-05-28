package main.project;

import main.hotel.Guest;
import main.hotel.Room;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

public class DataBase {
    public static void CheckConnection() throws SQLException{
        String DBName = "hotel";
        System.out.println("test connection :" + DBName);
        String url = "jdbc:postgres://localhost:5432/" + DBName;
        Connection con = DriverManager.getConnection(url,"postgres" ,"");
        System.out.println("Connection success");
        con.close();
    }

    public void Save10Room(){
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Room.class).buildSessionFactory();
        Session session = factory.getCurrentSession();
        Date date = new Date(Calendar.getInstance().getTime().getTime());
        System.out.println("Creating new room Object..");
        session.beginTransaction();

        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("Saving the Room");
                Room r = new Room("VIP","Triple",date,date,true);
                session.save(r);
                System.out.println("Done!");
            }
        } catch (Exception e) {
            System.out.println("Save Room Error");
        }finally {
            session.getTransaction().commit();
            factory.close();
        }
    }
    public void SaveRoom(Room room){
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Room.class).buildSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            System.out.println("Creating new room object..");
            session.beginTransaction();
            System.out.println("Saving room..");
            session.save(room);
            session.getTransaction().commit();
            System.out.println("Done!");
        } catch (Exception e) {
            System.out.println("Save Room Error");
        }finally {
            factory.close();
        }
    }
    public void ReadRoom(Room room){
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Room.class).buildSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            System.out.println("Saved Student. Generated Id: " + room.getRoomID());
            session = factory.getCurrentSession();
            session.beginTransaction();
            System.out.println("Getting Student with Id: " + room.getRoomID());
            Room r = session.get(Room.class, room.getRoomID());
            System.out.println("Get Complete: " + r);
            session.getTransaction().commit();
            System.out.println("Done!");
        }finally {
            factory.close();
        }
    }

    public static List<Room> getRooms(){
        List<Room> rooms = null;
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Room.class).buildSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            rooms = session.createQuery("FROM ROOM").list();
            displayList(rooms);
            session.getTransaction().commit();
            System.out.println("Done!");
        }finally {
            factory.close();
        }
        return rooms;
    }

    public static List<Room> getAvailableRooms(){
        List<Room> rooms = null;
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Room.class).buildSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            rooms = session.createQuery("FROM ROOM R WHERE R.ISEMPTY = TRUE").list();
            displayList(rooms);
            session.getTransaction().commit();
            System.out.println("Done!");
        }finally {
            factory.close();
        }
        return rooms;
    }

    public static boolean SaveUser(User user){
        List<User> users = DataBase.getUsers();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserName().equals(user.getUserName()))
                return false;
        }
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        try{
            System.out.println("Creating new User Project..");
            session.beginTransaction();
            System.out.println("Saving User");
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Save User Error");
        }finally {
            factory.close();
        }
        return true;
    }

    public static boolean isUserNameValid(String userName){
        List<User> users = DataBase.getUsers();
        for (int i = 0; i < users.size(); i++) {
            if (userName.equals(users.get(i).getUserName())){
                System.out.println("Equal");
                return true;
            }
        }
        return false;
    }

    public static boolean DeleteUser(String userName){
        if(!isUserNameValid(userName)){
            System.out.println("Not valid username");
            return false;
        }
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        try{
            session = factory.getCurrentSession();
            session.beginTransaction();
            System.out.println("Deleting user name :' " + userName +"'");
            session.getTransaction().commit();
            System.out.println("Done!");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            factory.close();
        }
        return false;
    }

    public static List<User> getUsers(){
        List<User> users = null;
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        try{
            session.beginTransaction();
            users = session.createQuery("FROM USER").list();
            displayList(users);
            session.getTransaction().commit();
            System.out.println("Done!");
        }finally {
            factory.close();
        }
        return users;
    }

    public static List<Guest> getGuests(){
        List<Guest> guests = null;
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Guest.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        try{
            session.beginTransaction();
            guests = session.createQuery("FROM GUEST").list();
            displayList(guests);
            session.getTransaction().commit();
            System.out.println("Done!");
        }finally {
            factory.close();
        }
        return guests;
    }

    public static <T> void displayList(List<T> list){
        for (T tempUser: list) {
            System.out.println(tempUser);
        }
    }


}
