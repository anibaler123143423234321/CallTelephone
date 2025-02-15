package api.codesoft.com.CallTelephone.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "leadSilbo")
public class LeadSilbo implements Serializable {

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getCampaignId() {
    return campaignId;
  }

  public void setCampaignId(String campaignId) {
    this.campaignId = campaignId;
  }

  public String getProductOrder() {
    return productOrder;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSegment() {
    return segment;
  }

  public void setSegment(String segment) {
    this.segment = segment;
  }

  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "phone", nullable = true)
  @JsonProperty("phone")
  private String phone;

  @Column(name = "operator", nullable = true)
  @JsonProperty("operator")
  private String operator;

  @Column(name = "segment", nullable = true)
  @JsonProperty("segment")
  private String segment;

  @Column(name = "email", nullable = true)
  @JsonProperty("email")
  private String email;

  @Column(name = "product_order", nullable = true)
  @JsonProperty("order")
  private String productOrder;

  @Column(name="fecha_creacion", nullable = false)
  private LocalDateTime fechaCreacion;

  @Column(name = "campaign_id", nullable = true)
  @JsonProperty("campaign_id")
  private String campaignId;

  public Long getId() {
    return id;
  }

  // Normalización y limpieza del campo `productOrder`
  public void setProductOrder(String productOrder) {
    this.productOrder = cleanInput(productOrder);
  }

  // Método genérico para limpiar cadenas
  private String cleanInput(String input) {
    if (input == null) {
      return null;
    }
    // Limpia etiquetas HTML y normaliza caracteres
    return Jsoup.clean(input, Safelist.none())
            .replace("\n", " ") // Reemplaza saltos de línea con espacios
            .replace(",", ".")  // Cambia comas por puntos
            .trim();            // Elimina espacios innecesarios
  }

  @Override
  public String toString() {
    return "Lead{" +
            "id=" + id +
            ", phone='" + phone + '\'' +
            ", operator='" + operator + '\'' +
            ", segment='" + segment + '\'' +
            ", email='" + email + '\'' +
            ", productOrder='" + productOrder + '\'' +
            ", campaignId='" + campaignId + '\'' +
            '}';
  }

  public LocalDateTime getFechaCreacion() {
    return fechaCreacion;
  }

  public void setFechaCreacion(LocalDateTime fechaCreacion) {
    this.fechaCreacion = fechaCreacion;
  }

}
