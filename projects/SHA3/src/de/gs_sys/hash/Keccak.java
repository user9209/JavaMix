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

import org.bouncycastle.jcajce.provider.digest.BCMessageDigest;
import org.bouncycastle.jcajce.provider.digest.Keccak.DigestKeccak;

import java.io.UnsupportedEncodingException;

/**
 * Created by Georg Schmidt on 16.07.2016.
 */

public class Keccak {

    public static String keccak_512(String in)
    {
        return keccak(in,512);
    }

    public static String keccak_384(String in)
    {
        return keccak(in,384);
    }

    public static String keccak_256(String in)
    {
        return keccak(in,256);
    }

    public static String keccak_224(String in)
    {
        return keccak(in,224);
    }

    private static String keccak(String in, int size)
    {
        if (size == 224 || size == 256 || size == 384 || size == 512)
        {
            BCMessageDigest hashEngine = new DigestKeccak(size);

            return getHashByEngine(hashEngine,in);
        }
        else
        {
            System.out.println("Use 224, 256, 384 or 512 !!!");
        }
        return "";
    }

    public static String getHashByEngine(BCMessageDigest hashEngine, String in) {
        try {
            return getHashByEngine(hashEngine,in.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getHashByEngine(BCMessageDigest hashEngine, byte[] in) {
        try
        {
            in = hashEngine.digest(in);
            return hashToString(in);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "";
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

    // Never used

    public static byte[] keccak(byte[] in, int size)
    {
        if (size == 224 || size == 256 || size == 384 || size == 512)
        {
            BCMessageDigest hashEngine = new DigestKeccak(size);

            return getHashByEngineAsByte(hashEngine,in);
        }
        else
        {
            System.out.println("Use 224, 256, 384 or 512 !!!");
        }
        return new byte[0];
    }

    public static byte[] getHashByEngineAsByte(BCMessageDigest hashEngine, byte[] in) {
        try
        {
            return hashEngine.digest(in);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return new byte[0];
        }
    }

    public static byte[] keccak_512(byte[] in)
    {
        return keccak(in,512);
    }

    public static byte[] keccak_384(byte[] in)
    {
        return keccak(in,384);
    }

    public static byte[] keccak_256(byte[] in)
    {
        return keccak(in,256);
    }

    public static byte[] keccak_224(byte[] in)
    {
        return keccak(in,224);
    }
}
