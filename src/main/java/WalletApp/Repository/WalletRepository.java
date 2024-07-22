package WalletApp.Repository;

import WalletApp.Entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet,Long> {

    Optional<Wallet> findBymobilenumber(Long mobilenumber);
    Optional<Wallet> findByemail(String email);

}
