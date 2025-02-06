package com.greta.PalBack.exceptions;

public class MalformedJwtException extends RuntimeException{

 public MalformedJwtException(String message) {
     super(message);
 }
}
