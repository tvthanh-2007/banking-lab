package com.banking.banking_lab.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateCustomerRequest {

  @NotBlank
  private String name;

  public CreateCustomerRequest() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
