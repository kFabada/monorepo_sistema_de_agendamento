package com.fabada.agendamento.execption;

public class EmailExistException extends RuntimeException {
  public EmailExistException(String message) {
    super(message);
  }
}
