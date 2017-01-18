package test;

import junit.framework.*;

/**
 * Created by Thinh on 17.06.2016.
 */
public class TestFieldTest extends TestCase {

    private int n ;
    @Override
    protected void setUp() throws Exception {
        this.n = 2 ;
    }

    public void testMult(){
        assertEquals(6, n+4);
        this.n += 1 ;
    }
}