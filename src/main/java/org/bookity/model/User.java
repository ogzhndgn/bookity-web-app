package org.bookity.model;


import javax.persistence.*;
import javax.validation.constraints.Email;

/**
 * @author Oguzhan Dogan <dogan_oguzhan@hotmail.com>
 */
@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "mailaddress")
    @Email
    private String mailAddress;
    @Column(name = "passwordhash")
    private String passwordHash;
    @Column(name = "passwordsalt")
    private String passwordSalt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }
}
