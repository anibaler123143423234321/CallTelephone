package api.codesoft.com.CallTelephone.repository;

import api.codesoft.com.CallTelephone.model.LeadSilbo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadRepository extends JpaRepository<LeadSilbo, Long> {
}
