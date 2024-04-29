package be.pxl.repository;

import be.pxl.domain.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organisation, Long> {
    Optional<Organisation> findByName(String name);
}
