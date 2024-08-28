package WalletApp.Repository;

import WalletApp.Entity.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeneficiaryRepo extends JpaRepository<Beneficiary,String> {

    @Query("SELECT b FROM Beneficiary b WHERE b.wallet.id = :walletId")
    List<Beneficiary> findByWalletId(@Param("walletId") Long walletId);
}
