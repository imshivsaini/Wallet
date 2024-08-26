package WalletApp.Entity.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;

public class BankAccountDTO {


    @JsonProperty("bankName")
    private String BankName;
    @JsonProperty("ifscCode")
    private String IFSCcode;
    @JsonProperty("balance")
    private Long Balance;
    @JsonProperty("accountNumber")
    private Long AccountNumber;

    public String getBankName() {
        return BankName;
    }

    public void setBankName(String bankName) {
        BankName = bankName;
    }

    public Long getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        AccountNumber = accountNumber;
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

    public BankAccountDTO() {
    }

    public BankAccountDTO(String bankName, Long accountNumber, String IFSCcode, Long balance) {
        BankName = bankName;
        AccountNumber = accountNumber;
        this.IFSCcode = IFSCcode;
        Balance = balance;
    }

    @Override
    public String toString() {
        return "BankAccountDTO{" +
                "BankName='" + BankName + '\'' +
                ", IFSCcode='" + IFSCcode + '\'' +
                ", Balance=" + Balance +
                ", AccountNumber=" + AccountNumber +
                '}';
    }
}
