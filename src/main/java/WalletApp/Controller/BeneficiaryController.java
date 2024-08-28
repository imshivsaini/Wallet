package WalletApp.Controller;

import WalletApp.Entity.Beneficiary;
import WalletApp.Entity.DTO.BankAccountDTO;
import WalletApp.Entity.DTO.BeneficiaryDTO;
import WalletApp.Services.BeneficiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Api/Beneficiary")
public class BeneficiaryController {
    @Autowired
    public BeneficiaryService beneficiaryService;

    @PostMapping("/AddBeneficiary/{wallet_id}")
    public HttpStatus saveBankAccount(@PathVariable("wallet_id") Long walletid, @RequestBody @Validated BeneficiaryDTO beneficiaryDTO) {
        System.out.println(beneficiaryDTO);
     //  System.out.println(walletid);
         if(beneficiaryService.AddBeneficiary(beneficiaryDTO,walletid).equals("0"))
         {
             return HttpStatus.OK;
         }


        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    @DeleteMapping("/DeleteBeneficiary")
    public HttpStatus deleteBankAccount(@RequestParam String name){
        if(beneficiaryService.RemoveBeneficiary(name).equals("0"))
        {
            return HttpStatus.OK;
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

   @GetMapping("/ViewBeneficiary")
    public List<Beneficiary> viewbeneficiary(@RequestParam Long walletid)
    {
        return beneficiaryService.viewbeneficiarybywalletid(walletid);
    }
}
