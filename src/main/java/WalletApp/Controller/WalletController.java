package WalletApp.Controller;

import WalletApp.Entity.Wallet;
import WalletApp.Services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/Api/wallet")
public class WalletController {

    Logger logger = Logger.getLogger("Wallet log");
    @Autowired
    private WalletService walletService;

    @GetMapping("/")
    public String Welcome(){
        return " Hello User";
    }

    //save
    @PostMapping("/addwallet")
    public String savewallet(@RequestBody Wallet wallet)
    {
        wallet.setBalance(0L);
        if(walletService.savewallet(wallet) == 1) {

            logger.info("Duplicate values");
            return " Try with different Email/Mobile Number  ";
        }
            logger.info("Created Wallet");
            return " wallet Successfully added  ";
    }

    //read
    @GetMapping("/getwallets")
    public List<Wallet> fetchWalletList()
    {
        logger.info("Fetched All Wallets");
        return walletService.fetchWalletList();
    }

    @GetMapping("/getwallet/{wallet_id}")
    public Wallet getWallet(@PathVariable("wallet_id") Long wallet_id)
    {
        logger.info(" Fetched Wallet ");
        return walletService.getWallet(wallet_id);
    }

    //delete
    @DeleteMapping("/deletewallet/{wallet_id}")
    public String deletewalletbyid(@PathVariable("wallet_id") Long wallet_id){
        if(walletService.deletewalletbyid(wallet_id)==0){
            logger.info("Deleted Wallet " + wallet_id);
            return "Deleted Successfully";
        };
        logger.info("No Wallet with id   " + wallet_id);
        return "No wallet found with id  : " + wallet_id;

    }

    //update
    @PutMapping("/updatewallet/{wallet_id}")
    public Wallet updateuser(@RequestBody Wallet wallet, @PathVariable("wallet_id") Long wallet_id){
        logger.info("Updated a Wallet");
        return walletService.updatewallet(wallet,wallet_id);
    }

}
