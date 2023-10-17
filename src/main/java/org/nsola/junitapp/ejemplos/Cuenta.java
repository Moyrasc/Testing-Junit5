package org.nsola.junitapp.ejemplos;

import java.math.BigDecimal;

public class Cuenta {
    private String nombre;

    public Cuenta(String nombre, BigDecimal saldo) {
        this.saldo = saldo;
        this.nombre = nombre;
    }

    private BigDecimal saldo;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Cuenta)) {
            return false;
        }
        Cuenta c = (Cuenta) obj;
        if (this.nombre == null || this.saldo == null) {
            return false;
        }
        return this.nombre.equals(c.getNombre()) && this.saldo.equals(c.getSaldo());
    }
}