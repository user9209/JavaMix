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

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class DownloadFile {

    public static boolean download(String url, String filename)
    {
        if(FileOps.fileExists(filename))
            return false;

        // user agent
        System.setProperty("http.agent", "Mozilla/5.0 (Windows NT 6.3; Win64; x64; rv:54.0) Gecko/20100101 Firefox/54.0");

        // proxy
        System.setProperty("java.net.useSystemProxies", "true");
        try {
            URL downloadPage = new URL(url);
            ReadableByteChannel rbc = Channels.newChannel(downloadPage.openStream());
            FileOutputStream fos = new FileOutputStream(filename);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean download2(String url, String filename, boolean overwrite)
    {
        if(!overwrite && FileOps.fileExists(filename))
            return false;

        // user agent
        System.setProperty("http.agent", "Mozilla/5.0 (Windows NT 6.3; Win64; x64; rv:54.0) Gecko/20100101 Firefox/54.0");

        // proxy
        System.setProperty("java.net.useSystemProxies", "true");
        try {
            URL downloadPage = new URL(url);
            InputStream in = downloadPage.openStream();
            Files.copy(in, Paths.get(filename), StandardCopyOption.REPLACE_EXISTING);

            in.close();
            return true;
        }
        catch (FileNotFoundException e) {
            return false;
        }catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }



    public static String loadToString(String url)
    {
        // user agent
        System.setProperty("http.agent", "Mozilla/5.0 (Windows NT 6.3; Win64; x64; rv:54.0) Gecko/20100101 Firefox/54.0");

        // proxy
        System.setProperty("java.net.useSystemProxies", "true");
        try {
            URL downloadPage = new URL(url);
            InputStream in = downloadPage.openStream();

            Scanner s = new Scanner(in).useDelimiter("\\A");
            String result = s.hasNext() ? s.next() : "";

            in.close();
            return result;
        }catch (FileNotFoundException e) {
            return null;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        /*
        String versionInfo = loadToString("https://www.gs-sys.de/version");

        if(versionInfo == null)
            return;

        String[] versionPart = versionInfo.split("\n");
        System.out.println(versionInfo);


        float version = Float.parseFloat(versionPart[0].replace("Version", ""));
        Date date;
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            date = df.parse(versionPart[1]);
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }

        String id1 = versionPart[2];
        String id2 = versionPart[3];

        Date now = new Date();

        int days = (int) ((now.getTime() - date.getTime()) / 1000 / 60 / 60 / 24);
        System.out.println(days);

        System.out.println(version + " " + date.toString() + " " + id1 + " " + id2);
    */
    }
}
