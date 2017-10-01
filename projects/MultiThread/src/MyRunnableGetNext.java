public class MyRunnableGetNext implements Runnable {

    private long thread_id;
    private long starttime, stoptime;

    @Override
    public void run() {
        thread_id = Thread.currentThread().getId();
        starttime = System.currentTimeMillis();

        for (int i = 0; i < 10000; i++) {
            int values = NextToDo.getNext();

            System.out.println(" Thread " + thread_id + ": " + values);
        }
        stoptime = System.currentTimeMillis();
    }

    public long getThread_id() {
        return thread_id;
    }

    public long getTime() {
        return stoptime - starttime;
    }
}
