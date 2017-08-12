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
import java.util.regex.Matcher;

public class Wrapper7zip {

    private static final String cmd7zipA = "\"7zip\\7za.exe\" ";
    private static final String cmd7zip = "\"7zip\\7z.exe\" ";
    private static boolean exits7zip = false;
    //private final String cmd7zip = "\"C:\\Program Files\\7-Zip\\7z.exe\" ";

    public static boolean extract(String archivFile, String outputDir) {
        return extract(archivFile,outputDir,null);
    }

    public static boolean extract(String archivFile, String outputDir, String password) {

        if(!exits7zip)
        {
            extract7zipA();
            exits7zip = true;
        }

        archivFile = archivFile.replaceAll("/", Matcher.quoteReplacement(File.separator));
        outputDir = outputDir.replaceAll("/", Matcher.quoteReplacement(File.separator));

        String cmd7zipExtract;
        if(password == null)
            cmd7zipExtract = "x " + archivFile + " -r -o" + outputDir;
        else
            cmd7zipExtract = "x " + archivFile + " -r -p" + password + " -o" + outputDir;

        return Dos.run(cmd7zipA + cmd7zipExtract) == 0;
    }

    public static boolean archiv(String archivFile, String inputDir) {
        return archiv(archivFile,inputDir,null);
    }

    public static boolean archiv(String archivFile, String inputDir, String password) {

        if(!exits7zip)
        {
            extract7zipA();
            exits7zip = true;
        }

        archivFile = archivFile.replaceAll("/",  Matcher.quoteReplacement(File.separator));
        inputDir = inputDir.replaceAll("/", Matcher.quoteReplacement(File.separator));

        String cmd7zipExtract;
        if(password == null)
            cmd7zipExtract = "a " + archivFile + " " + inputDir;
        else
            cmd7zipExtract = "a " + archivFile + " -p" + password + " " + inputDir;

        // System.out.println(cmd7zipA + cmd7zipExtract);
        return Dos.run(cmd7zipA + cmd7zipExtract) == 0;
    }

    public static void main(String[] args) {
        Wrapper7zip.archiv("7zip.7z","7zip");
        Wrapper7zip.extract("7zip.7z","7zip2");
    }

    public static void extract7zip() {

        FileOps.extractFromJAR("7z.dll", "7zip");
        FileOps.extractFromJAR("7z.exe", "7zip");
        FileOps.extractFromJAR("7z.sfx", "7zip");
        FileOps.extractFromJAR("7zCon.sfx", "7zip");
        FileOps.extractFromJAR("7-zip.dll", "7zip");
    }

    public static void extract7zipA()
    {
        FileOps.extractFromJAR("7za.dll","7zip");
        FileOps.extractFromJAR("7za.exe","7zip");
        FileOps.extractFromJAR("7zxa.dll","7zip");
    }
}
