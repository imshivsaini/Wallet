package WalletApp.Services;

import WalletApp.Entity.BankAccount;
import WalletApp.Entity.DTO.BankAccountDTO;
import WalletApp.Entity.Wallet;
import WalletApp.Repository.BankRepo;
import WalletApp.Repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankService {
    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private BankRepo bankRepo;

    public void saveBankAccount(BankAccountDTO bankAccountDTO,Long walletid)
    {
        Optional<Wallet> wallet1 = walletRepository.findById(walletid);
        if(wallet1.isEmpty()){ return ; }

        Optional<BankAccount> bankAccount1 = bankRepo.findById(bankAccountDTO.getAccountNumber());
        if(bankAccount1.isEmpty()) {
        BankAccount bnk = new BankAccount();
        bnk.setAccountNumber(bankAccountDTO.getAccountNumber());
        bnk.setBankname(bankAccountDTO.getBankName());
        bnk.setIFSCcode(bankAccountDTO.getIFSCcode());
        bnk.setBalance(bankAccountDTO.getBalance());
        bnk.setWallet(walletRepository.findById(walletid).orElseThrow(() -> new RuntimeException("No wallet found")));

            bankRepo.save(bnk);
            return;
        }
        throw new RuntimeException("Account already exists");
    }

    public void DeleteBankAccount(Long AccountNumber){
        Optional<BankAccount> bankAccount1 = bankRepo.findById(AccountNumber);
        if(bankAccount1.isPresent()) {
            bankRepo.deleteById(AccountNumber);
        }
        else {
            throw new RuntimeException("No account found");
        }

    }

    public BankAccount ViewBankAccountbyacc(Long accountNumber)
    {
            return bankRepo.findById(accountNumber).orElseThrow(()->new RuntimeException("No account found"));
    }

}
