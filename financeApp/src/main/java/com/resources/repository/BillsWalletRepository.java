package com.resources.repository;

import com.resources.entity.BillsWallet;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Praca
 */
@Repository
public interface BillsWalletRepository extends JpaRepository<BillsWallet, Long> {

    @Transactional
    void deleteById(int id);

    List<BillsWallet> findByUserId(int userId);

    BillsWallet save(BillsWallet billsWallet);
}
