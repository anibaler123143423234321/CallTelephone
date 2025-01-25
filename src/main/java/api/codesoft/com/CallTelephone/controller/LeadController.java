package api.codesoft.com.CallTelephone.controller;

import api.codesoft.com.CallTelephone.model.LeadSilbo;
import api.codesoft.com.CallTelephone.service.LeadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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

  @GetMapping("/all")
  public ResponseEntity<?> getAllLeads(@RequestParam(value = "Bearer", required = true) String token) {
    Map<String, Object> response = new HashMap<>();

    try {
      // Verificar si el token coincide con el token estático
      if (!"vaxiDrezKeySecurePassword!".equals(token)) {
        response.put("status", "error");
        response.put("message", "Token de autenticación inválido");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
      }

      // Si el token es válido, obtener los datos
      List<LeadSilbo> leads = leadService.findAllLeads();
      response.put("status", "success");
      response.put("message", "Leads obtenidos exitosamente");
      response.put("data", leads);

      return ResponseEntity.ok(response);
    } catch (Exception e) {
      response.put("status", "error");
      response.put("message", "Error al obtener los leads");
      response.put("detail", e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

}