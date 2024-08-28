package WalletApp.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Beneficiary")
public class Beneficiary {

    @Id
    @Column(nullable = false)
    private String MobileNumber;


    @Column(nullable = false)
    private String BeneficiaryName;

    @ManyToOne
    @JoinColumn(name = "walletId" ,referencedColumnName = "wallet_id")
    private Wallet wallet;


    public Beneficiary() {
    }

    public Beneficiary(String mobileNumber, String beneficiaryName) {
        MobileNumber = mobileNumber;
        BeneficiaryName = beneficiaryName;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        MobileNumber = mobileNumber;
    }

    public String getBeneficiaryName() {
        return BeneficiaryName;
    }

    public void setBeneficiaryName(String beneficiaryName) {
        BeneficiaryName = beneficiaryName;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    @Override
    public String toString() {
        return "Beneficiary{" +
                "MobileNumber='" + MobileNumber + '\'' +
                ", BeneficiaryName='" + BeneficiaryName + '\'' +
                ", wallet=" + wallet +
                '}';
    }
}
