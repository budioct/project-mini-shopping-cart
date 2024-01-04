package pt.sofco.graha.module.keranjangs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface KeranjangsRepository extends JpaRepository<KeranjangsEntity, Long>, JpaSpecificationExecutor<KeranjangsEntity> {

    Optional<KeranjangsEntity> findFirstById(Long id);

    @Modifying
    @Query(value = "delete from keranjangs where id in (select keranjang_id from keranjangs_like_items where keranjangs.id = :id)", nativeQuery = true) // table pivot (id, keranjang_id, item_id)
    void removeKeranjangs(@Param("id") Long id);

}
