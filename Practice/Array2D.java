public class Array2D
{
    //280351
    private int[][] table =
      {
         {1, 2, 3},
         {4, 5, 6},
         {7, 8, 9},
         {10, 11, 12}
      };
    public Array2D()
    {
        
    }
    
    public String toString()
    {
        String str = "";
        //table.length is the number of rows in the table
        for (int row = 0; row < table.length; row ++)
        {
            //table[row].length is the number of columns in row
            for (int col = 0; col < table[row].length; col++)
            {
                str += table[row][col] + "\t";
            }
            str += "\n";
        }
        return str;
    }
    
    public String extractRow(int row)
    {
        String str = "";
        //for (int col = 0; col < table[row].length; col++)
        //{
        //    str += table[row][col] + "\t";
        //}
        for (int val : table[row])
        {
            str += val + "\t";
        }
        return str;
    }
    
    public String extractColumn(int col)
    {
       String str = "";
       for (int row = 0; row < table.length; row++)
       {
           str += table[row][col] + "\n";
       }
       return str;
    }
    
    public static void main(String[] args)
    {
       Array2D table = new Array2D();
       System.out.println(table.toString());
       System.out.println(table.extractRow(1));
       System.out.println(table.extractColumn(0));
    }
}