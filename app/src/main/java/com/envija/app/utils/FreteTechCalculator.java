package com.envija.app.utils;

public class FreteTechCalculator {

    public static final double LIMITE_MAXIMO_KG = 30.0; // Limite 30kg [RN03]
    private static final double FATOR_CUBAGEM_RODOVIARIO = 300.0;
    private static final double TAXA_SEGURO_NFE = 0.015; // 1.5% [RN01]
    private static final double TARIFA_BASE_FIXA = 25.00;
    private static final double VALOR_POR_KG = 4.50;

    public static double calcularPesoCubado(double compCm, double largCm, double altCm) {
        double volumeMetrosCubicos = (compCm / 100.0) * (largCm / 100.0) * (altCm / 100.0);
        return volumeMetrosCubicos * FATOR_CUBAGEM_RODOVIARIO;
    }

    public static double calcularPesoConsiderado(double pesoRealKg, double compCm, double largCm, double altCm) {
        double pesoCubado = calcularPesoCubado(compCm, largCm, altCm);
        return Math.max(pesoRealKg, pesoCubado);
    }

    public static double calcularSeguroNFe(double valorNotaFiscal) {
        return valorNotaFiscal * TAXA_SEGURO_NFE;
    }

    public static double calcularFreteTotal(double pesoConsiderado, double distanciaKm, double valorNotaFiscal, boolean isFragil) {
        double valorPeso = pesoConsiderado * VALOR_POR_KG;
        double valorDistancia = distanciaKm * 0.15;
        double valorSeguro = calcularSeguroNFe(valorNotaFiscal);
        double adicionalFragil = isFragil ? 15.00 : 0.0;
        return TARIFA_BASE_FIXA + valorPeso + valorDistancia + valorSeguro + adicionalFragil;
    }
}