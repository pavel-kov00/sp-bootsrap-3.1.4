package ru.kata.spring.boot_security.demo.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users_2")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String password;
    private String lastname;
    private int age;
    private String email;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="user_role"
            , joinColumns = @JoinColumn(name="user_id")
            , inverseJoinColumns = @JoinColumn(name="role_id"))
    private List<Role> role;

    public User() {}

    public User(String name, String password, String lastname, int age, String email) {
        this.name = name;
        this.password = password;
        this.lastname = lastname;
        this.age = age;
        this.email = email;
    }

    public User(String name, String password, String lastname, int age) {
        this.name = name;
        this.password = password;
        this.lastname = lastname;
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Role> getRole() {
        return this.role;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }

    public void addRole(Role role){
        if (this.role == null) {
            this.role = new ArrayList<>();
        }
        this.role.add(role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age=" + age +
                ", role='" + role + '\'' +
                '}';
    }

}
