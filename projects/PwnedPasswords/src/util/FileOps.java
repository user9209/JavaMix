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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileOps {

    public static boolean mkdir(String dir) {
        return new File(dir).mkdir();
    }

    public static boolean filesExists(String ... files)
    {
        for (String x : files)
        {
          //  if(!new File(x).exists())
            if(!Files.exists(Paths.get(x)))
                return false;
        }
        return true;
    }

    public static boolean fileExists(String file)
    {
       // return Files.exists(Paths.get(file));
       return new File(file).exists();
    }

    public static void extractFromJAR(String filename, String folder)
    {
        mkdir(folder);

        File file = new File(folder + "/" + filename);
        try {
            if (!file.exists()) {
                InputStream link = (FileOps.class.getClassLoader().getResourceAsStream(filename));
                Files.copy(link, file.getAbsoluteFile().toPath());
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
