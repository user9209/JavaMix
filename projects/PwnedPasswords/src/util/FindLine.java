/*
 *     Copyright (C) 2017 Georg Schmidt <gs-develop@gs-sys.de>
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
 
package util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.Scanner;

public class FindLine {

    public static MessageDigest hasher = null;

    public static String getHash(String plaintext)
    {
        if(hasher == null)
        {
            try {
                hasher = MessageDigest.getInstance("SHA-1");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return "";
            }
        }
        return byteToHex(hasher.digest(plaintext.getBytes(StandardCharsets.UTF_8)));
    }

    public static boolean isContained(String seachValue, String filnename) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(Paths.get(filnename));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        int lineNR = 0;
        while(scanner.hasNextLine()){
            lineNR ++;

            if(scanner.hasNext(seachValue)) {
                System.out.println("Line: " + lineNR);
                return true;
            }

            // Throw line content
            scanner.nextLine();

            if(lineNR %1000000 == 0)
               status(lineNR);
        }
        return false;
    }

    public static boolean isHashContained(String plaintext, String filnename){
        return isContained(getHash(plaintext),filnename);
    }

    public static void main(String[] args) throws Exception {

        System.out.println(getHash("password"));

        System.out.println(isHashContained("password","passwords/pwned-passwords-update-2.txt"));
        //System.out.println(isContained("0001A25CFEDAEF8B00503A5643938BF4CC48D2A9","passwords/pwned-passwords-update-2.txt"));
    }

    private static String byteToHex(final byte[] hash)
    {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result.toUpperCase();
    }

    public static void status(int count)
    {
        System.out.println(count);
    }
}
