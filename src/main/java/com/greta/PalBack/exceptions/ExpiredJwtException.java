package com.greta.PalBack.exceptions;

public class ExpiredJwtException extends RuntimeException{

 public ExpiredJwtException(String message) {
     super(message);
 }

    public ExpiredJwtException(Object message, Object o, String s) {
    }
}
