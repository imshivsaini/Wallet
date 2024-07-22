package WalletApp.Controller;

import WalletApp.Entity.Transaction;
import WalletApp.Services.TransactionService;
import WalletApp.Services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/Api/transaction")
public class TransactionController {

    Logger logger = Logger.getLogger("Transaction log");

    @Autowired
    private WalletService walletService;
    @Autowired
    private TransactionService transactionService;


    @PostMapping("/Spend/{wallet_id}")
    public ResponseEntity<String> SpendTransaction(@RequestBody Transaction transaction, @PathVariable("wallet_id") Long wallet_id){

             logger.info("Inside Spend transaction");
        transactionService.SpendWallet(transaction, wallet_id);
            return ResponseEntity.ok("Success");
    }
    @PostMapping("/Load/{wallet_id}")

    public ResponseEntity<String> LoadTransaction(@RequestBody Transaction transaction, @PathVariable("wallet_id") Long wallet_id){
        logger.info("Inside Load transaction");
        transactionService.LoadWallet(transaction, wallet_id);
        return ResponseEntity.ok("Success");
    }


}
