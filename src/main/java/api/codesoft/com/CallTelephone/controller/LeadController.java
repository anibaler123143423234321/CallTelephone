package api.codesoft.com.CallTelephone.controller;

import api.codesoft.com.CallTelephone.model.LeadSilbo;
import api.codesoft.com.CallTelephone.service.LeadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = {
        "https://tufibrarapida.es",
        "https://tufibrarapida.com"
})
@RequestMapping("/api/main/lead")
public class LeadController {

  private final LeadService leadService;

  public LeadController(LeadService leadService) {
    this.leadService = leadService;
  }

  @PostMapping("/create")
  public ResponseEntity<Map<String, String>> createLead(@RequestBody LeadSilbo lead) {
    Map<String, String> response = new HashMap<>();
    try {
      // Guardar el lead
      LeadSilbo savedLead = leadService.saveLead(lead);

      // Crear una respuesta detallada
      response.put("status", "success");
      response.put("message", "Lead creado exitosamente");
      response.put("detail", "El lead fue guardado con ID: " + savedLead.getId());

      return ResponseEntity.status(HttpStatus.CREATED).body(response);
    } catch (Exception e) {
      // Manejo de errores con un formato consistente
      response.put("status", "error");
      response.put("message", "Error al guardar el lead");
      response.put("detail", e.getMessage());

      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }
}