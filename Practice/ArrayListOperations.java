import java.util.ArrayList;

public class ArrayListOperations
{
    public static void main(String[] args)
    {
        ArrayList<String> names = new ArrayList();
        System.out.println(names);
        names.add("Alice");
        names.add("Bob");
        names.add("Connie");
        names.add("David");
        names.add("Edward");
        names.add("Fran");
        names.add("Gomez");
        names.add("Harry");
        System.out.println(names);
        System.out.println(names.get(0));
        System.out.println(names.get(7));
        System.out.println(names.size());
        System.out.println(names.get(names.size()-1));
        names.set(0, "Alice B. Toklas");
        System.out.println(names);
        names.add(4, "Doug");
        System.out.println(names);
        for (String i: names)
        {
            System.out.println(i);
        }
        ArrayList<ArrayList> names2 = new ArrayList(names);
        System.out.println(names2);
        names.remove(0);
        System.out.println(names);
        System.out.println(names2);
    }
}