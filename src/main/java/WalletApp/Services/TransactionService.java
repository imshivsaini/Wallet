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
    private SmsService smsService;


    @Async("mainTaskExecutor")
    public  void LoadWallet(Transaction transaction, Long wallet_id){
        Wallet wallet = walletRepository.findById(wallet_id).orElseThrow(()-> new RuntimeException("Wallet not found with id"));
        transaction.setWallet(walletRepository.findById(wallet_id).orElseThrow(() -> new RuntimeException("Wallet not found with id" )));
        wallet.setBalance(wallet.getBalance()+ transaction.getAmount());
        walletRepository.save(wallet);
        transaction.setType(TransactionType.Load);
        transactionRepository.save(transaction);
        sendSmsWithDelay(wallet.getMobilenumber(), "Amount Loaded successfully");
    }

    @Async("mainTaskExecutor")
    public void SpendWallet(Transaction transaction, Long wallet_id) {
        Wallet wallet = walletRepository.findById(wallet_id).orElseThrow(() -> new RuntimeException("Wallet not found with id" ));
        transaction.setWallet(walletRepository.findById(wallet_id).orElseThrow(() -> new RuntimeException("Wallet not found with id" )));
        Long bal = wallet.getBalance();
        Long spendbal = transaction.getAmount();
        if (bal < spendbal) {
            sendSmsWithDelay(wallet.getMobilenumber(), "Low Balance");
        } else {
            wallet.setBalance(bal - spendbal);
            walletRepository.save(wallet);
            transaction.setType(TransactionType.Spend);
            transactionRepository.save(transaction);
            sendSmsWithDelay(wallet.getMobilenumber(), "Amount spent successfully");
        }


}

    private void sendSmsWithDelay(Long mobilenumber, String message) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                smsService.sendSms(mobilenumber, message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
    }
