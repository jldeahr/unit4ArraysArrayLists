

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class RadarTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class RadarTest
{
    /**
     * Default constructor for test class RadarTest
     */
    public RadarTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
    @Test
    public void testInitialLocation() throws InterruptedException
    {
        Radar radar = new Radar(100,100, 1, 1, 50, 50);
        radar.setNoiseFraction(0.0005); 
        
        radar.scan();
        if (radar.currentScan[50][50] != true)
        {
            assertNotNull("expected monster at [50][50]");
        }
        
    }


    @Test
    public void testFinalVelocity() throws InterruptedException
    {
        Radar radar = new Radar(100, 100, 1, 1, 50, 50);
        radar.setNoiseFraction(0.0005);
        
        for (int i = 0; i < 20; i++)
        {
            radar.scan();
        }
        if (radar.slope = )
        {
            assertNotNull("expected slope of 1");
        }   
    }
    
    @Test
    public void testFinalVelocity2() throws InterruptedException
    {
        Radar radar = new Radar(100, 100, 3, 3, 50, 50);
        radar.setNoiseFraction(0.0005);
        
        for (int i = 0; i < 20; i++)
        {
            radar.scan();
        }
        if (radar.slope != 1)
        {
            assertNotNull("expected slope of 3/3");
        }   
    }
}