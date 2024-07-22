package WalletApp.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "Transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Transaction_id")
    private Long Trans_id;

    @JoinColumn(name = "wallet_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Wallet Wallet;

    @Column(name = "transaction_type")
    private TransactionType type;

    @Column(name = "amount")
    private Long amount;

    @CreationTimestamp
    @Column(name = "Created", updatable = false)
    private Timestamp created;

    @UpdateTimestamp
    @Column(name = "Updated")
    private Timestamp updated;

    public Transaction() {
    }

    public Transaction(Long trans_id, Timestamp updated, Timestamp created, Long amount, TransactionType type, WalletApp.Entity.Wallet wallet) {
        Trans_id = trans_id;
        this.updated = updated;
        this.created = created;
        this.amount = amount;
        this.type = type;
        Wallet = wallet;
    }

    public WalletApp.Entity.Wallet getWallet() {
        return Wallet;
    }

    public void setWallet(WalletApp.Entity.Wallet wallet) {
        Wallet = wallet;
    }

    public Long getTrans_id() {
        return Trans_id;
    }

    public void setTrans_id(Long trans_id) {
        Trans_id = trans_id;
    }


    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }
}
