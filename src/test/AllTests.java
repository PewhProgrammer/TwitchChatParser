package test;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Created by Thinh on 17.06.2016.
 */
public class AllTests {

    public static Test suite(){
        TestSuite suite = new TestSuite() ;

        suite.addTestSuite(TestFieldTest.class);
        return suite ;
    }
}
