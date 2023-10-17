package org.nsola.junitapp.ejemplos;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CuentaTest {
    @Test
    void testNombreCuenta() {

        Cuenta cuenta = new Cuenta("Noe", new BigDecimal("1000.1234"));
        //cuenta.setNombre("Noe");
        String esperado = "Noe";
        String real = cuenta.getNombre();
        assertEquals(esperado,real);
        assertTrue(real.equals("Noe"));
    }
    @Test
    void testSaldoCuenta(){
        Cuenta cuenta = new Cuenta("Noe", new BigDecimal("1000.1234"));
        //convertimos el big decimal a tipo double value
        assertEquals(1000.1234, cuenta.getSaldo().doubleValue());
        assertFalse(cuenta.getSaldo().compareTo(BigDecimal.ZERO) < 0);
        assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO) > 0);

    }

    @Test
    void testReferenciaCuenta() {
        Cuenta cuenta = new Cuenta("John Doe", new BigDecimal("8900.9997"));
        Cuenta cuenta2 = new Cuenta("John Doe", new BigDecimal("8900.9997"));

        //assertNotEquals(cuenta, cuenta2);
        assertEquals(cuenta, cuenta2);
    }
}