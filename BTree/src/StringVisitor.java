
public class StringVisitor<E> implements Visitor<E>
{
    private String str;            // data member to collect the visited items
    
    public StringVisitor()
    {
        str = "";                  // start with a blank string
    }

    public void visit(E item)      // required method from the Visitor interface
    {
        str += item + " ";         // append current item to overall string
    }

    public String getValue()       // use after the traversal to get the overall string
    {
        return "[" + str.trim() + "]";
    }
    
    public void emptyStr()      
    {
        str = "";
    }
}