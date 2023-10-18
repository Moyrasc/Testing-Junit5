package org.nsola.junitapp.ejemplos;

import org.junit.jupiter.api.Test;
import org.nsola.junitapp.ejemplos.exceptions.SaldoInsuficienteException;
import org.nsola.junitapp.ejemplos.models.Banco;
import org.nsola.junitapp.ejemplos.models.Cuenta;

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
        assertNotNull(cuenta.getSaldo());
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
    @Test
    void testDebitoCuenta() {
        Cuenta cuenta = new Cuenta("Noe", new BigDecimal("1000.1234"));
        cuenta.debito(new BigDecimal(100));
        assertNotNull(cuenta.getSaldo());
        assertEquals(900, cuenta.getSaldo().intValue());
        assertEquals("900.1234", cuenta.getSaldo().toPlainString());

    }
    @Test
    void testCreditoCuenta() {
        Cuenta cuenta = new Cuenta("Noe", new BigDecimal("1000.1234"));
        cuenta.credito(new BigDecimal(100));
        assertNotNull(cuenta.getSaldo());
        assertEquals(1100, cuenta.getSaldo().intValue());
        assertEquals("1100.1234", cuenta.getSaldo().toPlainString());

    }

    @Test
    void testSaldoInsuficienteException() {
        Cuenta cuenta = new Cuenta("Noe", new BigDecimal("1000.1234"));
        Exception exception = assertThrows(SaldoInsuficienteException.class,()-> {
            cuenta.debito(new BigDecimal(1500));
        });
        String actual = exception.getMessage();
        String esperado = "Dinero Insuficiente";
        assertEquals(esperado,actual);
    }

    @Test
    void testTransferirDineroCuentas() {
        Cuenta cuenta = new Cuenta("Noe", new BigDecimal("2500"));
        Cuenta cuenta2 = new Cuenta("John Doe", new BigDecimal("1500"));

        Banco banco = new Banco();
        banco.setNombre("BBVA");
        banco.transferir(cuenta2, cuenta, new BigDecimal(500));
        assertEquals("1000", cuenta2.getSaldo().toPlainString());
        assertEquals("3000", cuenta.getSaldo().toPlainString());
    }

    @Test
    void testRelacionBancoCuentas() {
        Cuenta cuenta = new Cuenta("Noe", new BigDecimal("2500"));
        Cuenta cuenta2 = new Cuenta("John Doe", new BigDecimal("1500"));

        Banco banco = new Banco();
        banco.addCuenta(cuenta);
        banco.addCuenta(cuenta2);
        banco.setNombre("BBVA");
        banco.transferir(cuenta2, cuenta, new BigDecimal(500));
        assertEquals("1000", cuenta2.getSaldo().toPlainString());
        assertEquals("3000", cuenta.getSaldo().toPlainString());

        assertEquals(2, banco.getCuentas().size());
        //Comprobamos que el nombre del banco sea el esperado
        assertEquals("BBVA", cuenta.getBanco().getNombre());
        //Comprobamos que el banco tiene alguna cuenta a nombre de la persona que le indicamos
        //assertEquals("Noe", banco.getCuentas().stream().filter(c -> c.getNombre().equals("Noe")).findFirst().get().getNombre());
        //assertTrue(banco.getCuentas().stream().filter(c -> c.getNombre().equals("Noe")).findFirst().isPresent());
        assertTrue(banco.getCuentas().stream().anyMatch(c -> c.getNombre().equals("Noe")));
    }
}