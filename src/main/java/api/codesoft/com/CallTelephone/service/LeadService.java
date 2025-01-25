package api.codesoft.com.CallTelephone.service;

import api.codesoft.com.CallTelephone.model.LeadSilbo;
import api.codesoft.com.CallTelephone.repository.LeadRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LeadService {

  private final LeadRepository leadRepository;

  public LeadService(LeadRepository leadRepository) {
    this.leadRepository = leadRepository;
  }

  public LeadSilbo saveLead(LeadSilbo lead) {
    lead.setFechaCreacion(LocalDateTime.now());
    return leadRepository.save(lead);
  }

  public List<LeadSilbo> findAllLeads() {
    return leadRepository.findAll();
  }

}
