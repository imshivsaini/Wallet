package WalletApp.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "Wallet")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wallet_id")
    private Long wallet_id;
    @Column(name = "name")
    private String name;
    @Column(name = "mobilenumber",unique = true)
    private Long mobilenumber;
    @Column(name = "email",unique = true)
    private String email;
    @Column(name = "address")
    private String address;
    @Column(name = "Balance")
    private Long balance;

    @Column(updatable = false,name = "created")
    @CreationTimestamp
    private Timestamp created;
    @Column(name = "updated")
    @UpdateTimestamp
    private Timestamp updated;

//  Getters and Setters

    public Wallet() {
    }

    public Wallet(Timestamp updated, Timestamp created, Long balance, String address, String email, Long mobilenumber, Long wallet_id, String name) {
        this.updated = updated;
        this.created = created;
        this.balance = balance;
        this.address = address;
        this.email = email;
        this.mobilenumber = mobilenumber;
        this.wallet_id = wallet_id;
        this.name = name;
    }

    public Long getWallet_id() {
        return wallet_id;
    }

    public void setWallet_id(Long wallet_id) {
        this.wallet_id = wallet_id;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(Long mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
