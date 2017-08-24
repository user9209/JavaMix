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

package de.gs_sys.kp2016.hash;

import java.io.FileInputStream;
import org.bouncycastle.jcajce.provider.digest.BCMessageDigest;
import org.bouncycastle.jcajce.provider.digest.Keccak.DigestKeccak;
import org.bouncycastle.jcajce.provider.digest.SHA3.DigestSHA3;

/**
 * Created by Georg Schmidt on 29.04.2017.
 */
public class SHA3File extends Keccak {

    public static String keccak_file_512(String filenname)
    {
        return keccak(filenname,512);
    }

    public static String keccak_file_384(String filenname)
    {
        return keccak(filenname,384);
    }

    public static String keccak_file_256(String filenname)
    {
        return keccak(filenname,256);
    }

    public static String keccak_file_224(String filenname)
    {
        return keccak(filenname,224);
    }

    private static String keccak(String filename, int size)
    {
        try {


            if (size == 224 || size == 256 || size == 384 || size == 512) {
                BCMessageDigest hashEngine = new DigestKeccak(size);

                FileInputStream fis = new FileInputStream(filename);

                byte[] dataBytes = new byte[102400];

                int readCount;
                while ((readCount = fis.read(dataBytes)) != -1) {
                    hashEngine.update(dataBytes, 0, readCount);
                }

                return hashToString(hashEngine.digest());
            } else {
                System.out.println("Use 224, 256, 384 or 512 !!!");
            }
        }catch (Exception ignore) {}
        return "";
    }



    public static String sha3_file_512(String filenname)
    {
        return sha3(filenname,512);
    }

    public static String sha3_file_384(String filenname)
    {
        return sha3(filenname,384);
    }

    public static String sha3_file_256(String filenname)
    {
        return sha3(filenname,256);
    }

    public static String sha3_file_224(String filenname)
    {
        return sha3(filenname,224);
    }

    private static String sha3(String filename, int size)
    {
        try {
            if (size == 224 || size == 256 || size == 384 || size == 512) {
                BCMessageDigest hashEngine = new DigestSHA3(size);

                FileInputStream fis = new FileInputStream(filename);

                byte[] dataBytes = new byte[102400];

                int readCount;
                while ((readCount = fis.read(dataBytes)) != -1) {
                    hashEngine.update(dataBytes, 0, readCount);
                }

                return hashToString(hashEngine.digest());
            } else {
                System.out.println("Use 224, 256, 384 or 512 !!!");
            }
        }catch (Exception ignore) {}
        return "";
    }
}
