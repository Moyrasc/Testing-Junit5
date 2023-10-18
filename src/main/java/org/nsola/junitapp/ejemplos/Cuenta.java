package org.nsola.junitapp.ejemplos;

import org.nsola.junitapp.ejemplos.exceptions.SaldoInsuficienteException;

import java.math.BigDecimal;

public class Cuenta {
    private String nombre;
    private BigDecimal saldo;
    public Cuenta(String nombre, BigDecimal saldo) {
        this.saldo = saldo;
        this.nombre = nombre;
    }

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
    public void debito(BigDecimal monto){
        //No podemos hacerlo de esta manera debido a que Bigdecimal es inmutable
        // this.saldo.subtract(monto);

        // this.saldo = this.saldo.subtract(monto);
        BigDecimal nuevoSaldo = this.saldo.subtract(monto);
        if(nuevoSaldo.compareTo(BigDecimal.ZERO) < 0){
            throw new SaldoInsuficienteException("Dinero Insuficiente");
        }
        this.saldo = nuevoSaldo;
    }
    public void credito(BigDecimal monto){
        //No podemos hacerlo de esta manera debido a que Bigdecimal es inmutable
        //this.saldo.add(monto);

        this.saldo = this.saldo.add(monto);
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