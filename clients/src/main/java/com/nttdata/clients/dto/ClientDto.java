package com.nttdata.clients.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientDto {
  /**
   * annotation identifier.
   * client identifier.
   */
  @Id
  private String clientId;
  /**
   * client name.
   */
  private String name;
  /**
   * client last name.
   */
  private String lastName;
  /**
   * client phone.
   */
  private Integer phone;
  /**
   * client personal email.
   */
  private String email;
  /**
   * client type (personal, business).
   */
  private String type;
}
