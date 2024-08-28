package WalletApp.Controller;

import WalletApp.Entity.BankAccount;
import WalletApp.Entity.DTO.BankAccountDTO;
import WalletApp.Services.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/Api/Bank")
public class BankController {

    Logger logger = Logger.getLogger("Bank log");

    @Autowired
    private BankService bankService;

    // save
    @PostMapping("/AddBank/{wallet_id}")
    public HttpStatus saveBankAccount(@PathVariable("wallet_id") Long walletid,@RequestBody @Validated BankAccountDTO bankAccountDTO) {
        logger.info("inside save Bank account");
        System.out.println(bankAccountDTO);
        System.out.println(walletid);
        bankService.saveBankAccount(bankAccountDTO, walletid);


        return HttpStatus.OK;
    }


    @DeleteMapping("/DeleteBank")
    public HttpStatus deleteBankAccount(@RequestParam Long accountnumber){
        logger.info("Inside Delete Bank Account");
        bankService.DeleteBankAccount(accountnumber);
        return HttpStatus.OK;
    }

    @GetMapping("/ViewBankAccount")
    public BankAccount ViewBankAccount(@RequestParam Long accountnumber){
        logger.info("Inside View Bank Account");

        return bankService.ViewBankAccountbyacc(accountnumber);
    }
}
