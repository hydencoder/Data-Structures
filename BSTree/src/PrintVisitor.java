
public class PrintVisitor<E> implements Visitor<E>
{
    public void visit(E item)
    {
        System.out.print(item + " ");
    }
}