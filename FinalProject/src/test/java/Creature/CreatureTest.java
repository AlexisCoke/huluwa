package Creature;

import Ground.Battle;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CreatureTest {
    private Calabash calabash;

    @Before
    public void CreatureTest() throws Exception{
        calabash = new Calabash();
    }

    @Test
    public void isAlive() {
        assertEquals(true, calabash.isAlive());
    }
}