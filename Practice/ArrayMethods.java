public class ArrayMethods
{
    //private final int uselessvalue = 691859;
    private int[] values;
    private int storage;
    public ArrayMethods(int[] initialValues)
    {
        values = initialValues;
    }
    
    public void swapFirstAndLast()
    {
        storage = values[0];
        values[0] = values[values.length-1];
        values[values.length-1] = values[storage];
        for (int i = 0; i < values.length-1; i++)
        {
            System.out.println(values[i]);
        }
    }
    
    public void shiftRight()
    {
        storage = values[values.length-1];
        for (int i = 1; i < values.length-1; i++)
        {
            values[i + 1] = values[i];
            System.out.println(values);
        }
    }
    
    public void Evens()
    {
        for (int i = 0; i < values.length-1; i++)
        {
            if (i % 2 == 0)
            {
                values[i] = 0;
            }
        }
    }
    
    public void ReplaceFL()
    {
        for (int i = 1; i < values.length-2; i++)
        {
            int f = values[i-1];
            int l = values[i+1];
            if (f > l)
            {
                values[i] = f;
            }
            else
            {
                values[i] = l;
            }
        }
    }
    
    public void RemoveMid()
    {
        if (values.length % 2 == 0)
        {
            storage = values.length / 2;
            values[storage].remove;
            values[storage-1].remove;
        }
        else
        {
            storage = values.length / 2;
            storage = values.length - storage;
            values[storage-1].remove;
        }
    }
}