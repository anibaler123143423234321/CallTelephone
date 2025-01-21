package api.codesoft.com.CallTelephone.service;

import api.codesoft.com.CallTelephone.model.LeadSilbo;
import api.codesoft.com.CallTelephone.repository.LeadRepository;
import org.springframework.stereotype.Service;

@Service
public class LeadService {

  private final LeadRepository leadRepository;

  public LeadService(LeadRepository leadRepository) {
    this.leadRepository = leadRepository;
  }

  public LeadSilbo saveLead(LeadSilbo lead) {
    return leadRepository.save(lead);
  }
}
