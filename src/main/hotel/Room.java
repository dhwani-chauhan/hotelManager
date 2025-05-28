package main.hotel;

import jakarta.persistence.*;
import main.project.DataBase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roomID")
    private int roomID;
    @Column(name = "room_Type")
    private String room_Type;
    @Column(name = "room_capacity")
    private String room_capacity;
    @Column(name = "Check_In_Date")
    @Temporal(TemporalType.DATE)
    private Date Check_In_Date;
    @Column(name = "Check_ot_Date")
    @Temporal(TemporalType.DATE)
    private Date Check_Out_Date;
    @Column(name = "isEmpty")
    private boolean isEmpty;

    public Room(){}

    public Room(String room_Type,String room_capacity,Date Check_In_Date,Date Check_Out_Date,boolean isEmpty){
        this.room_Type = room_Type;
        this.room_capacity = room_capacity;
        this.Check_In_Date = Check_In_Date;
        this.Check_Out_Date = Check_Out_Date;
        this.isEmpty = isEmpty;
    }

    public boolean isIsEmpty() {
        return isEmpty;
    }
    public void setIsEmpty(boolean isEmpty){
        this.isEmpty = isEmpty;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public String getRoom_Type() {
        return room_Type;
    }

    public void setRoom_Type(String room_Type) {
        this.room_Type = room_Type;
    }

    public String getRoom_capacity() {
        return room_capacity;
    }

    public void setRoom_capacity(String room_capacity) {
        this.room_capacity = room_capacity;
    }

    public Date getCheck_In_Date() {
        return Check_In_Date;
    }

    public void setCheck_In_Date(Date check_In_Date) {
        Check_In_Date = check_In_Date;
    }

    public Date getCheck_Out_Date() {
        return Check_Out_Date;
    }

    public void setCheck_Out_Date(Date check_Out_Date) {
        Check_Out_Date = check_Out_Date;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomID=" + roomID +
                ", room_Type='" + room_Type + '\'' +
                ", room_capacity='" + room_capacity + '\'' +
                ", Check_In_Date=" + Check_In_Date +
                ", Check_Out_Date=" + Check_Out_Date +
                ", isEmpty=" + isEmpty +
                '}';
    }

    public static void RoomBooking(Guest guest,Room room,int roomID){
        System.out.println("in roombooking() ...");
        CheckIn(guest, room, roomID);
        System.out.println("roombooking done ...");
    }

    public static void CheckIn(Guest guest,Room room,int roomID){
        System.out.println("In CheckIn()..");
        System.out.println("Save Guest..");
        Guest.SaveGuest(guest);
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Room.class).buildSessionFactory();
        Session session = factory.getCurrentSession();
        try{
            session = factory.getCurrentSession();
            session.beginTransaction();
            System.out.println("Getting room with id: " + roomID);
            Room r = session.get(Room.class,roomID);
            System.out.println("Updating Room..");
            r.setIsEmpty(room.isIsEmpty());
            r.setCheck_In_Date(room.getCheck_In_Date());
            r.setCheck_Out_Date(room.getCheck_Out_Date());
            session.getTransaction().commit();
            System.out.println("Check In Done!");
        }finally {
            factory.close();
        }
    }

    public void CancelBooking(int roomID){
        System.out.println("Cancel Booking..");
        CheckOut(roomID);
    }

    public static int CheckOut(int roomID){
        System.out.println("In CheckOut..");
        int flag =1;
        System.out.println("Execute Delete Guest");
        Guest.deleteGuest(roomID);
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Room.class).buildSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            System.out.println("Getting Room with Id: " + roomID);
            Room room = session.get(Room.class,roomID);
            if (room == null)
                return -1;
            else {
                if (room.isIsEmpty()){
                    flag = 0;
                }
                System.out.println("Updating room..");
                room.setIsEmpty(true);
                room.setCheck_Out_Date(new Date());
            }
            session.getTransaction().commit();
            System.out.println("Check Out Done!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            factory.close();
        }
        return flag;
    }

    public static Room Search_available_rooms(String roomType,String roomCapacity){
        List<Room> AvailableRooms = DataBase.getAvailableRooms();
        for (int i = 0; i < AvailableRooms.size(); i++) {
            if(AvailableRooms.get(i).getRoom_Type().equals(roomType) && AvailableRooms.get(i).getRoom_capacity().equals(roomCapacity))
                return AvailableRooms.get(i);
        }
        return null;
    }

    public double nightCost(Room room){
        double fees = 0;
        if ("Economy".equals(room.getRoom_Type()) && "Single".equals(room.getRoom_capacity()))
            fees += 200;
        else if ("Economy".equals(room.getRoom_Type()) && "Double".equals(room.getRoom_capacity()))
            fees += 150;
        else if ("Economy".equals(room.getRoom_Type()) && "Triple".equals(room.getRoom_capacity()))
            fees += 100;
        else if ("Normal".equals(room.getRoom_Type()) && "Single".equals(room.getRoom_capacity()))
            fees += 250;
        else if ("Normal".equals(room.getRoom_Type()) && "Double".equals(room.getRoom_capacity()))
            fees += 200;
        else if ("Normal".equals(room.getRoom_Type()) && "Triple".equals(room.getRoom_capacity()))
            fees += 150;
        else if ("VIP".equals(room.getRoom_Type()) && "Single".equals(room.getRoom_capacity()))
            fees += 300;
        else if ("VIP".equals(room.getRoom_Type()) && "Double".equals(room.getRoom_capacity()))
            fees += 250;
        else if ("VIP".equals(room.getRoom_Type()) && "Triple".equals(room.getRoom_capacity()))
            fees += 200;
        return fees;
    }
}
