package pt.sofco.graha.module.pesanans;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PesanansRepository extends JpaRepository<PesanansEntity, Long> {

    Optional<PesanansEntity> findFirstByNoRegister(String noregister);

}
