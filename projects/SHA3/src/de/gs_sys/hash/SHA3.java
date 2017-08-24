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

import org.bouncycastle.jcajce.provider.digest.SHA3.DigestSHA3;

/**
 * Created by Georg Schmidt on 05.07.2016.
 */
public class SHA3 extends Keccak
{
    private static String sha3(String in, int size)
    {
        if (size == 224 || size == 256 || size == 384 || size == 512)
        {
            DigestSHA3 sha3 = new DigestSHA3(size);

            return getHashByEngine(sha3,in);
        }
        else
        {
            System.out.println("Use 224, 256, 384 or 512 !!!");
        }
        return "";
    }

    public static String sha3_512(String in)
    {
        return sha3(in,512);
    }

    public static String sha3_384(String in)
    {
        return sha3(in,384);
    }

    public static String sha3_256(String in)
    {
        return sha3(in,256);
    }

    public static String sha3_224(String in)
    {
        return sha3(in,224);
    }
}
