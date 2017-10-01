/*
 * Copyright (c) 2017 Georg Schmidt <gs-develop@gs-sys.de>
 * All rights reserved
 * Licensing with individual contract possible, written contract necessary.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 */
 
 public class MyRunnable implements Runnable {

    private int begin, end;
    private long thread_id;
    private long starttime, stoptime;

    public MyRunnable(int b, int e)
    {
        begin = b;
        end = e;
    }

    @Override
    public void run() {
        thread_id = Thread.currentThread().getId();
        starttime = System.currentTimeMillis();

        for (int i = begin; i < end; i++) {
            System.out.println(" Thread " + thread_id + ": " + i);
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
