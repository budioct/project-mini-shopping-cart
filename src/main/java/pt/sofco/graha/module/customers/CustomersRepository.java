package pt.sofco.graha.module.customers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface CustomersRepository extends JpaRepository<CustomersEntity, Long>, JpaSpecificationExecutor<CustomersEntity> {

    Optional<CustomersEntity> findFirstById(Long id);

}
