public class Main {

    public static void main(String[] args) {

        findMD5();
        //askForTask();
        //previousSplit();
    }

    public static void findMD5()
    {
        System.out.println(MD5.getMD5(Integer.toBinaryString(500)));
        MD5Finder runnable1 = new MD5Finder(MD5.getMD5(Integer.toBinaryString(500)));
        MD5Finder runnable2 = new MD5Finder(MD5.getMD5(Integer.toBinaryString(500)));
        MD5Finder runnable3 = new MD5Finder(MD5.getMD5(Integer.toBinaryString(500)));
        MD5Finder runnable4 = new MD5Finder(MD5.getMD5(Integer.toBinaryString(500)));

        Thread t1 = new Thread(runnable1);
        Thread t2 = new Thread(runnable2);
        Thread t3 = new Thread(runnable3);
        Thread t4 = new Thread(runnable4);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            int i = 0;
            while (i++ < 1000 && !runnable1.hasFound() && !runnable2.hasFound() && !runnable3.hasFound() && !runnable4.hasFound() )
                Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(runnable1.getPlaintext() + " " + runnable2.getPlaintext() + " " + runnable3.getPlaintext() + " " + runnable4.getPlaintext());
    }

    public static void askForTask()
    {
        System.out.println("Cores:" + Runtime.getRuntime().availableProcessors());

        MyRunnableGetNext runnable1 = new MyRunnableGetNext();
        MyRunnableGetNext runnable2 = new MyRunnableGetNext();
        MyRunnableGetNext runnable3 = new MyRunnableGetNext();
        MyRunnableGetNext runnable4 = new MyRunnableGetNext();

        Thread t1 = new Thread(runnable1);
        Thread t2 = new Thread(runnable2);
        Thread t3 = new Thread(runnable3);
        Thread t4 = new Thread(runnable4);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            while (t1.isAlive() || t2.isAlive() || t3.isAlive() || t4.isAlive() )
                Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(runnable1.getThread_id());
        System.out.println(runnable2.getThread_id());
        System.out.println(runnable3.getThread_id());
        System.out.println(runnable4.getThread_id());

        long stoptime1 = runnable1.getTime();
        long stoptime2 = runnable2.getTime();
        long stoptime3 = runnable3.getTime();
        long stoptime4 = runnable4.getTime();

        System.out.println(stoptime1 + " " + stoptime2 + " " + stoptime3 + " " + stoptime4);
    }


    public static void previousSplit()
    {
        System.out.println("Cores:" + Runtime.getRuntime().availableProcessors());

        MyRunnable runnable1 = new MyRunnable(0,10000);
        MyRunnable runnable2 = new MyRunnable(10000,20000);
        MyRunnable runnable3 = new MyRunnable(20000,30000);
        MyRunnable runnable4 = new MyRunnable(30000,40000);

        Thread t1 = new Thread(runnable1);
        Thread t2 = new Thread(runnable2);
        Thread t3 = new Thread(runnable3);
        Thread t4 = new Thread(runnable4);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            while (t1.isAlive() || t2.isAlive() || t3.isAlive() || t4.isAlive() )
                Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(runnable1.getThread_id());
        System.out.println(runnable2.getThread_id());
        System.out.println(runnable3.getThread_id());
        System.out.println(runnable4.getThread_id());

        long stoptime1 = runnable1.getTime();
        long stoptime2 = runnable2.getTime();
        long stoptime3 = runnable3.getTime();
        long stoptime4 = runnable4.getTime();

        System.out.println(stoptime1 + " " + stoptime2 + " " + stoptime3 + " " + stoptime4);
    }
}
