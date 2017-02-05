package ca.sfu.epsilon.servingcalculator;

import org.junit.Test;

import static org.junit.Assert.*;

public class PotTest {
    Pot pot = new Pot("Chicken", 66);

    @Test
    public void getWeightInG() throws Exception {
        assertEquals(66, pot.getWeightInG());
    }

    @Test
    public void setWeightInG() throws Exception {
        pot.setWeightInG(90);
        assertNotEquals(66, pot.getWeightInG());
        assertEquals(90, pot.getWeightInG());
    }

    @Test
    public void getName() throws Exception {
        assertEquals("Chicken", pot.getName());
    }

    @Test
    public void setName() throws Exception {
        pot.setName("Fernando, Master of the Dark Arts");
        assertNotEquals("Chicken", pot.getName());
        assertEquals("Fernando, Master of the Dark Arts", pot.getName());
    }

    @Test
    public void testGetName() throws Exception {
        assertNotEquals("", pot.getName());
    }

    @Test
    public void testGetWeight() throws Exception {
        assertNotEquals("33", pot.getWeightInG());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSetName() throws Exception {
        pot.setName("");
    }

    @Test (expected = NullPointerException.class)
    public void testSetNameNULL() throws Exception {
        pot.setName(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSetWeightZero() throws Exception{
        pot.setWeightInG(0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSetWeightNeg() throws Exception{
        pot.setWeightInG(-100);
    }
}