package main.project;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_pass")
    private String password;
    @Column(name = "is_admin")
    private boolean is_admin;

    public User(){}

    public User(String userName,String password,boolean is_admin){
        this.userName = userName;
        this.password = password;
        this.is_admin = is_admin;
    }

    public boolean isIs_admin(){
        return is_admin;
    }

    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" + "username= " + userName + ", password= " + password + ", is_admin= " + is_admin + "}";
    }

    public static boolean isUserValid(User user){
        List<User> users = DataBase.getUsers();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserName().equals(user.getUserName())){
                if (users.get(i).getPassword().equals(user.getPassword())){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isUserAdmin(User user){
        List<User> users = DataBase.getUsers();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserName().equals(user.getUserName())){
                if (users.get(i).getPassword().equals(user.getPassword())){
                    if (users.get(i).isIs_admin())
                        return true;
                }
            }
        }
        return false;
    }
}
