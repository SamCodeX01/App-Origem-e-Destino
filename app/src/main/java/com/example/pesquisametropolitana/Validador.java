package com.example.pesquisametropolitana;

public class Validador {
    public static boolean validar(int actNumber, String string1, String string2) {
        switch (actNumber) {
            case 1:
                return validarAct1(string1, string2);
            case 2:
                return validarAct2(string1, string2);
            case 3:
                return validarAct3(string1, string2);
            default:
                return false;
        }
    }

    private static boolean validarAct1(String usuario, String senha) {
        // ESQUEMA
        return true;
    }

    private static boolean validarAct2(String origem, String destino) {
        // ESQUEMA
        return true;
    }

    private static boolean validarAct3(String nome, String telefone) {
        // ESQUEMA
        return true;
    }
}
