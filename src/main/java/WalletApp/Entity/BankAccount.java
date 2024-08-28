package WalletApp.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "BankAccount")
public class BankAccount {

    @Id
    private Long AccountNumber;
    @Column(name = "BankName")
    private String Bankname;
    @Column(name = "IFSCcode")
    private String IFSCcode;
    @Column(name = "Balance")
    private Long Balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "walletId" ,referencedColumnName = "wallet_id")
    private Wallet wallet;

    public BankAccount() {
    }

    public BankAccount(Long accountNumber, String bankname, String IFSCcode, Long balance) {
        super();
        this.AccountNumber = accountNumber;
        this.Bankname = bankname;
        this.IFSCcode = IFSCcode;
        this.Balance = balance;
    }

    public Long getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        AccountNumber = accountNumber;
    }

    public String getBankname() {
        return Bankname;
    }

    public void setBankname(String bankname) {
        Bankname = bankname;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public Long getBalance() {
        return Balance;
    }

    public void setBalance(Long balance) {
        Balance = balance;
    }

    public String getIFSCcode() {
        return IFSCcode;
    }

    public void setIFSCcode(String IFSCcode) {
        this.IFSCcode = IFSCcode;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "AccountNumber=" + AccountNumber +
                ", Bankname='" + Bankname + '\'' +
                ", IFSCcode='" + IFSCcode + '\'' +
                ", Balance=" + Balance +
                ", wallet=" + wallet +
                '}';
    }
}
