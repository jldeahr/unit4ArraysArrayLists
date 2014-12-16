/**
 * The model for radar scan and accumulator
 * 
 * @author @gcschmit
 * @version 19 July 2014
 */
public class Radar
{
    
    // stores whether each cell triggered detection for the current scan of the radar
    public boolean[][] currentScan;
    private boolean[][] prevScan;
    
    // value of each cell is incremented for each scan in which that cell triggers detection 
    private int[][] accumulator;
    
    // location of the monster
    private int monsterLocationRow;
    private int monsterLocationCol;

    // probability that a cell will trigger a false detection (must be >= 0 and < 1)
    private double noiseFraction;
    
    // number of scans of the radar since construction
    private int numScans;
    
    // distance in x and y directions
    public int dx;
    public int dy;
    public int slope;
    
    //current rows anc cols
    public int row;
    public int col;
    public int curRow;
    public int curCol;

    /**
     * Constructor for objects of class Radar
     * 
     * @param   rows    the number of rows in the radar grid
     * @param   cols    the number of columns in the radar grid
     */
    public Radar(int rows, int cols, int dx, int dy, int row, int col)
    {
        // initialize instance variables
        currentScan = new boolean[rows][cols]; // elements will be set to false
        accumulator = new int[row][col]; // elements will be set to 0 with accumulator length
                                         // of 10 for both dimensions
        dx = dx;
        dy = dy;
        
        
        // randomly set the location of the monster (can be explicity set through the
        //  setMonsterLocation method
        setMonsterLocation(row,col);
        //monsterLocationRow = row;
        //monsterLocationCol = col;
        
        noiseFraction = 0.05;
        numScans = 0;
    }
    
    /**
     * Performs a scan of the radar. Noise is injected into the grid and the accumulator is updated.
     * 
     */
    public void scan() //throws InterruptedException
    {
        // zero the current scan grid
        for(int rows = 0; rows < currentScan.length; rows++)
        {
            for(int cols = 0; cols < currentScan[0].length; cols++)
            {
                currentScan[rows][cols] = false;
            }
        }
        
        //scans the grid
        //for(int rows = 0; rows < currentScan.length; rows++)
        //{
        //    for(int cols = 0; cols < currentScan[0].length; cols++)
        //    {
        //        if (this.isDetected(rows,cols) == true)
        //        {
        //            currentScan[rows][cols] = true;
                    //System.out.println(currentScan[rows][cols]);
        //        }
        //    }
        //}
        
        //prepares for comparisons
        prevScan = currentScan;
        
        //move monster
        currentScan[row][col] = true;
        this.row += dx;
        this.col += dy;
        
        // inject noise into the grid
        injectNoise();
        
        // udpate the accumulator
        for (int rows = 0; rows < currentScan.length; rows++)
        {
            for (int cols = 0; cols < currentScan[0].length; cols++)
            {
                for (int rows2 = 0; rows2 < prevScan.length; rows2++)
                {
                    for (int cols2 = 0; cols2 < prevScan[0].length; cols2++)
                    {
                        if (currentScan[rows][cols] == true &&
                            rows-rows2 >= 0 &&
                            rows-rows2 <= 10 &&
                            cols-cols2 >= 0 &&                            
                            cols-cols2 <= 10)
                        {
                            //Thread.sleep(5);
                            System.out.println("rows-rows2");
                            System.out.println("rows2: " + rows2 + " rows: " + rows + " cols2 " + cols2 + " cols: " + cols);
                            System.out.println("added to accumulator: " + accumulator[rows-rows2][cols-cols2]);
                            accumulator[rows-rows2][cols-cols2] += 1;
                        }
                         else if (currentScan[rows][cols] == true &&
                                 rows2-rows >=0 &&
                                 rows2-rows <=10 &&
                                 cols2-cols >=0 &&                                 
                                 cols2-cols <=10)
                        {
                            //Thread.sleep(5);
                            System.out.println("rows2-rows");
                            System.out.println("rows2: " + rows2 + " rows: " + rows + " cols2 " + cols2 + " cols: " + cols);
                            System.out.println("added to accumulator: " + accumulator[rows2-rows][cols2-cols]);
                            accumulator[rows2-rows][cols2-cols] += 1;
                        }
                    } 
                }
            }
        }
  
        // keep track of the total number of scans
        numScans++;
    }

    
    /**
     * Finds the velocity of the monster
     * 
     * @return greatest     the index of the greatest object
     * 
     */
    public void findVelocity()
    {
        int greatest = 0;
        int xVal = 0;
        int yVal = 0;
        for (int i = 0; i < accumulator.length; i++)
        {
            for (int j = 0; j < accumulator[0].length; j++)
            {
                if (accumulator[i][j] > greatest)
                {
                    greatest = accumulator[i][j];
                    System.out.print(greatest);
                }
            }
        }
        for (int i = 0; i < accumulator.length; i++)
        {
            for (int j = 0; j < accumulator[1].length; j++)
            {
                if (accumulator[i][j] == greatest)
                {
                    xVal = i-4;
                    yVal = j-4;
                    xVal = opposite(xVal);
                    yVal = opposite(yVal);
                    System.out.println("The velocity is, x: " + xVal + " y: " + yVal);
                    //slope = j/i;
                }
            }
        }
        //return slope;
    }
    
    public int opposite(int num)
    {
        if (num > 0)
        {
            num = 0-num;
        }
        else if (num < 0)
        {
            num = 0+num;
        }
        return num;
    }
    
    public void printAccumulator() throws InterruptedException
    {
        for (int i = 0; i < accumulator.length; i++)
        {
            for (int j = 0; j < accumulator[1].length; j++)
            {                
                System.out.print(accumulator[i][j]);
                Thread.sleep(10);
            }
            System.out.println("");
        }
    }
    
    /**
     * Sets the location of the monster
     * 
     * @param   row     the row in which the monster is located
     * @param   col     the column in which the monster is located
     * @pre row and col must be within the bounds of the radar grid
     */
    public void setMonsterLocation(int row, int col)
    {
        // remember the row and col of the monster's location
        monsterLocationRow = row;
        monsterLocationCol = col;
        
        // update the radar grid to show that something was detected at the specified location
        currentScan[row][col] = true;
    }
    
     /**
     * Sets the probability that a given cell will generate a false detection
     * 
     * @param   fraction    the probability that a given cell will generate a flase detection expressed
     *                      as a fraction (must be >= 0 and < 1)
     */
    public void setNoiseFraction(double fraction)
    {
        noiseFraction = fraction;
    }
    
    /**
     * Returns true if the specified location in the radar grid triggered a detection.
     * 
     * @param   row     the row of the location to query for detection
     * @param   col     the column of the location to query for detection
     * @return true if the specified location in the radar grid triggered a detection
     */
     public boolean isDetected(int row, int col)
     {
         return currentScan[row][col];
     }
    
    /**
     * Returns the number of times that the specified location in the radar grid has triggered a
     *  detection since the constructor of the radar object.
     * 
     * @param   row     the row of the location to query for accumulated detections
     * @param   col     the column of the location to query for accumulated detections
     * @return the number of times that the specified location in the radar grid has
     *          triggered a detection since the constructor of the radar object
     */
    public int getAccumulatedDetection(int row, int col)
    {
        //was return accumulator[row][col];
        return accumulator[10][10];
    }
    
    /**
     * Returns the number of rows in the radar grid
     * 
     * @return the number of rows in the radar grid
     */
      public int getNumRows()
      {
          return currentScan.length;
      }
    
    /**
     * Returns the number of columns in the radar grid
     * 
     * @return the number of columns in the radar grid
     */
    public int getNumCols()
    {
        return currentScan[0].length;
    }
    
    /**
     * Returns the number of scans that have been performed since the radar object was constructed
     * 
     * @return the number of scans that have been performed since the radar object was constructed
     */
    public int getNumScans()
    {
        return numScans;
    }
    
    /**
     * Sets cells as falsely triggering detection based on the specified probability
     * 
     */
    private void injectNoise()
    {
        for(int row = 0; row < currentScan.length; row++)
        {
            for(int col = 0; col < currentScan[0].length; col++)
            {
                // each cell has the specified probablily of being a false positive
                if(Math.random() < noiseFraction)
                {
                    currentScan[row][col] = true;
                }
            }
        }
    }
}
