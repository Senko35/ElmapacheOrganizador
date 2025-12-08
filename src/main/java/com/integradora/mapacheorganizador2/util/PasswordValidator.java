package com.integradora.mapacheorganizador2.util;

public class PasswordValidator {
    
    public static boolean esValida(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }
        
        boolean tieneMayuscula = false;
        boolean tieneMinuscula = false;
        boolean tieneNumero = false;
        
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) tieneMayuscula = true;
            if (Character.isLowerCase(c)) tieneMinuscula = true;
            if (Character.isDigit(c)) tieneNumero = true;
        }
        
        return tieneMayuscula && tieneMinuscula && tieneNumero;
    }
}
