public class NextToDo  {

    private static int i = 0;

    public static synchronized int getNext()
    {
        return i++;
    }
}
