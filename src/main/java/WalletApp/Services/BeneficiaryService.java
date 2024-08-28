package WalletApp.Services;

import WalletApp.Entity.Beneficiary;
import WalletApp.Entity.DTO.BeneficiaryDTO;
import WalletApp.Entity.Wallet;
import WalletApp.Repository.BeneficiaryRepo;
import WalletApp.Repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BeneficiaryService {
    @Autowired
    public BeneficiaryRepo beneficiaryRepo;

    @Autowired
    public WalletRepository walletRepository;


    public String AddBeneficiary(BeneficiaryDTO beneficiaryDTO, Long walletid)
    {
        Optional<Wallet> walletOptional = walletRepository.findById(walletid);
        if(walletOptional.isEmpty())
        {
            return "1";
        }
        Wallet wallet = walletRepository.findById(walletid).orElseThrow();
        Beneficiary beneficiary = new Beneficiary(beneficiaryDTO.getBeneficiaryName(),beneficiaryDTO.getMobileNumber());
        beneficiary.setWallet(wallet);
        beneficiaryRepo.save(beneficiary);
        return "0";
    }

    public String RemoveBeneficiary(String name){
        Optional<Beneficiary> beneficiaryOptional = beneficiaryRepo.findById(name);
        if (beneficiaryOptional.isEmpty())
        {
            return "1";
        }
        beneficiaryRepo.deleteById(name);
        return "0";
    }

    public List<Beneficiary> viewbeneficiarybywalletid(Long walletid)
    {
        return beneficiaryRepo.findByWalletId(walletid);
    }



}

