package spring.rest.jpa.ormjparest.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Account_Details")
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ankitha")
    @SequenceGenerator(name = "ankitha", sequenceName = "ankitha_sequence", initialValue = 1000019650, allocationSize = 1)
    private Long id;
    private String userName;
    private String password;
    private String email;
    private Long phoneNumber;
    private Long accNo;

    public Account(String userName, String password, String email, Long phoneNumber, Long accNo) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.accNo = accNo;
    }

    public Account() {
    }

    public Long getAccNo() {
        return accNo;
    }

    public void setAccNo(Long accNo) {
        this.accNo = accNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Account{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", accNo=" + accNo +
                '}';
    }
}
