/*
 * Copyright (c) 2017 Georg Schmidt <gs-develop@gs-sys.de>
 * All rights reserved
 * Licensing with individual contract possible, written contract necessary.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 */
 
 import java.security.NoSuchAlgorithmException;

public class MD5Finder implements Runnable {

    private long thread_id;
    private long starttime, stoptime = -1;
    String hash;
    String plaintext = null;
    MD5 md5;

    public MD5Finder(String hash)
    {
        this.hash = hash;
        try {
            md5 = new MD5();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        thread_id = Thread.currentThread().getId();
        starttime = System.currentTimeMillis();

        for (int i = 0; i < 1000; i++) {
            int values = NextToDo.getNext();

            if(hash.equals(md5.md5(Integer.toBinaryString(values))))
            {
                plaintext = Integer.toBinaryString(values);
                System.out.println(plaintext);
                return;
            }
        }
        stoptime = System.currentTimeMillis();
    }

    public long getThread_id() {
        return thread_id;
    }

    public long getTime() {
        return stoptime - starttime;
    }

    public String getPlaintext() {
        return plaintext;
    }

    public boolean hasFound()
    {
        return stoptime != -1 && plaintext != null;
    }
}
