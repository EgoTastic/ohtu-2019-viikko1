package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void stringiksiMuunto() {
    	assertEquals("saldo = 0.0, vielä tilaa 10.0", varasto.toString());
    }
    
    @Test
    public void konstruktoriLiianPieniTilavuus() {
    	Varasto testivarasto = new Varasto(-1);
    	assertEquals(0, testivarasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaaVarastoonNegatiivinen() {
    	varasto.lisaaVarastoon(-1);
    	assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaaVarastoonLiianPaljon() {
    	varasto.lisaaVarastoon(20);
    	assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void otaLiianVahan() {
    	varasto.lisaaVarastoon(5);
    	varasto.otaVarastosta(-1);
    	assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void otaLiikaa() {
    	varasto.lisaaVarastoon(5);
    	varasto.otaVarastosta(12);
    	assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastoSaldolla() {
    	Varasto testivarasto = new Varasto(10, 5);
    	assertEquals(10, testivarasto.getTilavuus(), vertailuTarkkuus);
    	assertEquals(5, testivarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastoSaldollaTilavuusNegatiivinen() {
    	Varasto testivarasto = new Varasto(-1, 0);
    	assertEquals(0, testivarasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void varastoSaldollaSaldoNegatiivinen() {
    	Varasto testivarasto = new Varasto(10, -1);
    	assertEquals(0, testivarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastoSaldollaSaldoLiianIso() {
    	Varasto testivarasto = new Varasto(10, 20);
    	assertEquals(10, testivarasto.getSaldo(), vertailuTarkkuus);
    }

}
