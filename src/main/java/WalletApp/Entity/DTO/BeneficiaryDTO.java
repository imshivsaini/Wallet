package WalletApp.Entity.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BeneficiaryDTO {
    @JsonProperty("MobileNumber")
    private String MobileNumber;
    @JsonProperty("beneficiaryName")
    private String BeneficiaryName;

    public BeneficiaryDTO() {
    }

    public BeneficiaryDTO(String beneficiaryName, String mobileNumber) {
        BeneficiaryName = beneficiaryName;
        MobileNumber = mobileNumber;
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

    @Override
    public String toString() {
        return "BeneficiaryDTO{" +
                "MobileNumber='" + MobileNumber + '\'' +
                ", BeneficiaryName='" + BeneficiaryName + '\'' +
                '}';
    }
}
