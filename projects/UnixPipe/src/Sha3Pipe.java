/*
    Copyright (C) 2017  Georg Schmidt <gs-develop@gs-sys.de>
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.
    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/


import org.bouncycastle.jcajce.provider.digest.BCMessageDigest;
import org.bouncycastle.jcajce.provider.digest.Keccak;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Sha3Pipe {

    private static boolean check;
    /**
      Test  generate hash:
        echo "test" | java -jar Sha3Pipe.jar file.sha3-512 | more
      Test check hash:
        echo "test" | java -jar Sha3Pipe.jar -c file.sha3-512 | more
     */

    public static void main(String ... args){
        if(args.length == 1) {
            // for example  "file.sha3-512"
            check = false;
            sha3InputStreamToOut(args[0], System.in, System.out);
        }
        else if(args.length == 2) {
            // for example  "-c file.sha3-512"
            check = true;
            sha3InputStreamToOut(args[1], System.in, System.out);
        }
        else
            copyInputStreamToOutputStream(System.in,System.out);
    }

    private static void sha3InputStreamToOut(String filename, InputStream in, OutputStream out) {

        try {
            if(in.available() == 0)
                return;

            BCMessageDigest hashEngine = new Keccak.DigestKeccak(512);
            byte[] buf = new byte[1024];
            int len;


            while((len=in.read(buf))>0){
                hashEngine.engineUpdate(buf,0,len);
                out.write(buf,0,len);
            }
            if(!check)
                Files.write(Paths.get(filename),
                            hashToString(hashEngine.digest()).getBytes(StandardCharsets.UTF_8),
                            StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            else
            {
                String compareString;
                try {
                    compareString = new String(Files.readAllBytes(Paths.get(filename)), StandardCharsets.UTF_8);
                }catch (Exception ignore)
                {
                    // Will always fails
                    compareString = null;
                }

                if(compareString == null || !compareString.equals(hashToString(hashEngine.digest())))
                {
                    // TODO: how to handle

                    // throw Error
                    out.write("\nFAILED!".getBytes());

                    out.flush();
                    out.close();
                    in.close();
                    System.exit(1);
                    return;
                }
            }

            out.flush();
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void copyInputStreamToOutputStream(InputStream in, OutputStream out) {
        try {
            if(in.available() == 0)
                return;

            byte[] buf = new byte[1024];
            int len;
            while((len=in.read(buf))>0){
                out.write(buf,0,len);
            }
            out.flush();
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String hashToString(byte[] hash)
    {
        StringBuilder buff = new StringBuilder();

        for (byte b : hash) {
            buff.append(String.format("%02x", b & 0xFF));
        }

        return buff.toString();
    }
}