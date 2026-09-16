package com.unesp.logistica.app;

import java.util.ArrayList;
import java.util.List;

import com.unesp.logistica.model.Entregador;
import com.unesp.logistica.model.EntregadorMoto;
import com.unesp.logistica.model.EntregadorCarro;
import com.unesp.logistica.model.EntregadorBike;

public class App {
    public static void main(String[] args) {

        List<Entregador> entregadores = new ArrayList<>();

        entregadores.add(new EntregadorMoto("João Pedro", "111.222.333-44", "ABC-1234", 1000.0, 1.0));
        entregadores.add(new EntregadorCarro("Marcos Silva", "222.333.444-55", "XYZ-5678", 2000.0, 0.8));
        entregadores.add(new EntregadorBike("Beatriz Souza", "333.444.555-66", "N/A", 800.0, 15));

        System.out.println("=== RELATÓRIO DE BÔNUS DOS ENTREGADORES ===");

        for (Entregador e : entregadores) {
            double bonus = e.calcularBonus();

            System.out.println("--------------------------------------------------");
            System.out.println("Nome    : " + e.getNome());
            System.out.println("Tipo    : " + e.getClass().getSimpleName());
            System.out.println("Bônus   : R$ " + String.format("%.2f", bonus));
        }

        System.out.println("--------------------------------------------------");
    }
}