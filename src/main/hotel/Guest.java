package main.hotel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Entity
@Table(name = "guest")
public class Guest  implements RoomFees{
    @Column(name = "room_ID")
    private int roomId;
    @Column(name = "number_Of_Days")
    private int numberOFDaysStayed;
    @Column(name = "Name")
    private String Name;
    @Column(name = "Email")
    private String Email;
    @Column(name = "Address")
    private String Address;
    @Column(name = "city")
    private String city;
    @Column(name = "Nationality")
    private String Nationality;
    @Id
    @Column(name = "passport_number")
    private String passportNumber;
    @Column(name = "phoneNo")
    private String phoneNo;
    @Column(name = "Card_Number")
    private String CardNumber;
    @Column(name = "card_Pass")
    private String cardPass;
    @Column(name = "fees")
    private double Fees;

    public Guest(){}

    public Guest(int roomId,int numberOFDaysStayed,String Name,String Email,String Address,String city,String Nationality,String passportNumber,String phoneNo,String cardNumber,String cardPass,double fees){
        this.roomId = roomId;
        this.numberOFDaysStayed = numberOFDaysStayed;
        this.Name = Name;
        this.Email = Email;
        this.Address = Address;
        this.city = city;
        this.Nationality = Nationality;
        this.passportNumber = passportNumber;
        this.phoneNo = phoneNo;
        this.CardNumber = cardNumber;
        this.cardPass = cardPass;
        this.Fees = fees;
    }

    public double getFees() {
        return Fees;
    }

    public void setFees(double fees) {
        this.Fees = fees;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getNumberOFDaysStayed() {
        return numberOFDaysStayed;
    }

    public void setNumberOFDaysStayed(int numberOFDaysStayed) {
        this.numberOFDaysStayed = numberOFDaysStayed;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNationality() {
        return Nationality;
    }

    public void setNationality(String nationality) {
        Nationality = nationality;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(String cardNumber) {
        CardNumber = cardNumber;
    }

    public String getCardPass() {
        return cardPass;
    }

    public void setCardPass(String cardPass) {
        this.cardPass = cardPass;
    }

    @Override
    public String toString() {
        return "Guest{" +
                "roomId=" + roomId +
                ", numberOFDaysStayed=" + numberOFDaysStayed +
                ", Name='" + Name + '\'' +
                ", Email='" + Email + '\'' +
                ", Address='" + Address + '\'' +
                ", city='" + city + '\'' +
                ", Nationality='" + Nationality + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", CardNumber='" + CardNumber + '\'' +
                ", cardPass='" + cardPass + '\'' +
                ", fees=" + Fees +
                '}';
    }

    @Override
    public double CustomerRoomFees(Room room) {
        System.out.println("Inside CustomerRoomFees()..");
        double fees = 0;
        if (numberOFDaysStayed == 0)
            fees += room.nightCost(room);
        for (int i = 0; i < numberOFDaysStayed; i++) {
            fees += room.nightCost(room);
        }
        return fees;
    }

    public static void SaveGuest(Guest guest){
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Guest.class).buildSessionFactory();
        System.out.println("Create Session Factory");
        Session session = factory.getCurrentSession();
        System.out.println("Create Session");
        try {
            session.beginTransaction();
            System.out.println("Start Transaction");
            System.out.println("Saving Guest..");
            session.save(guest);
            session.getTransaction().commit();
            System.out.println("Done!");
        } catch (Exception e) {
            System.out.println("Save Guest Error");
        }finally {
            factory.close();
        }
    }

    public static void deleteGuest(int roomId){
        System.out.println("Delete Guest..");
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Guest.class).buildSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            System.out.println("Delete Guest RoomID: " + roomId);
            session.createQuery("DELETE FROM GUEST WHERE ROOM_ID= " + roomId).executeUpdate();
            session.getTransaction().commit();
            System.out.println("Done!");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            factory.close();
        }
    }
}
