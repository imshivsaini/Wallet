package WalletApp.Services;

import WalletApp.Entity.Wallet;
import WalletApp.Repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WalletService{
    @Autowired
    private WalletRepository walletRepository;

    public int savewallet(Wallet wallet)
    {
        Optional<Wallet> wallet1 = walletRepository.findBymobilenumber(wallet.getMobilenumber());
        Optional<Wallet> wallet2 = walletRepository.findByemail(wallet.getEmail());
        if(wallet1.isPresent() || wallet2.isPresent())
        {
            return 1;
        }
         walletRepository.save(wallet);
        return 0;
    }

    public List<Wallet> fetchWalletList(){
        return (List<Wallet>)walletRepository.findAll();
    }


    public Wallet getWallet(Long wallet_id) {
        return walletRepository.findById(wallet_id).orElseThrow(()-> new RuntimeException("No wallet found with id  :  " + wallet_id));
    }


    public int deletewalletbyid(Long wallet_id) {
        Optional<Wallet> wallet = walletRepository.findById(wallet_id);
        if (wallet.isPresent()) {
            walletRepository.deleteById(wallet_id);
            return 0;
        } else {
            return 1;
        }
    }

    public Wallet updatewallet(Wallet wallet, Long wallet_id) {
        return walletRepository.findById(wallet_id).map(existingWallet -> {
            existingWallet.setName(wallet.getName());
            existingWallet.setMobilenumber(wallet.getMobilenumber());
            existingWallet.setEmail(wallet.getEmail());
            existingWallet.setAddress(wallet.getAddress());
            existingWallet.setwallet_balance(wallet.getwallet_balance());
            return walletRepository.save(existingWallet);
        }).orElseThrow(() -> new RuntimeException("No wallet found with id: " + wallet_id));
    }



}
