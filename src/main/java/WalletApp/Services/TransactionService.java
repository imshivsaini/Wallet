package WalletApp.Services;

import WalletApp.Entity.Transaction;
import WalletApp.Entity.TransactionType;
import WalletApp.Entity.Wallet;
import WalletApp.Repository.TransactionRepository;
import WalletApp.Repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {

    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private EmailService emailService;




    @Async("mainTaskExecutor")
    public  void LoadWallet(Transaction transaction, Long wallet_id){
        Wallet wallet = walletRepository.findById(wallet_id).orElseThrow(()-> new RuntimeException("Wallet not found with id"));
        transaction.setWallet(walletRepository.findById(wallet_id).orElseThrow(() -> new RuntimeException("Wallet not found with id" )));
        wallet.setwallet_balance(wallet.getwallet_balance()+ transaction.getAmount());
        walletRepository.save(wallet);
        transaction.setType(TransactionType.Load);
        transactionRepository.save(transaction);
        emailService.sendEmail(wallet.getEmail(),"Amount Loaded" , "Amount Successfully Loaded into your wallet : " + wallet_id);
    }

    @Async("mainTaskExecutor")
    public void SpendWallet(Transaction transaction, Long wallet_id) {
        Wallet wallet = walletRepository.findById(wallet_id).orElseThrow(() -> new RuntimeException("Wallet not found with id" ));
        transaction.setWallet(walletRepository.findById(wallet_id).orElseThrow(() -> new RuntimeException("Wallet not found with id" )));
        Long bal = wallet.getwallet_balance();
        Long spendbal = transaction.getAmount();
        if (bal < spendbal) {
            emailService.sendEmail(wallet.getEmail(),"Alert : Low Balance" , "Low Balance into your wallet : " + wallet.getwallet_balance());

        } else {
            wallet.setwallet_balance(bal - spendbal);
            walletRepository.save(wallet);
            transaction.setType(TransactionType.Spend);
            transactionRepository.save(transaction);
            emailService.sendEmail(wallet.getEmail(),"Amount Spent" , "Amount Successfully deducted from your wallet : " + wallet_id + " Amount :" + transaction.getAmount());

        }


}
    }
