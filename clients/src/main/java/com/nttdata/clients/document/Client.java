package com.nttdata.clients.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "clients")
public class Client {
  /**
   * annotation identifier.
   * Client identifier.
  */
  @Id
  private String clientId;
  /**
   * Client name.
   */
  private String name;
  /**
   * Client last name.
   */
  private String lastName;
  /**
   * Client phone.
   */
  private Integer phone;
  /**
   * Client personal email.
   */
  private String email;
  /**
   * Client type (personal, business).
   */
  private String type;
}
