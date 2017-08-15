
/*
 *     Copyright (C) 2017  Georg Schmidt <gs-develop@gs-sys.de>
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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HTTP {

    public static String sendStringHTTP(String url, HashMap<String,String> postData) {

        // user agent
        System.setProperty("http.agent", "Mozilla/5.0 (Windows NT 6.3; Win64; x64; rv:54.0) Gecko/20100101 Firefox/54.0");

        // proxy
        System.setProperty("java.net.useSystemProxies", "true");

        try {
            URL urlD = new URL(url);

            HttpURLConnection urlConnection = (HttpURLConnection) urlD.openConnection();

            urlConnection.setRequestMethod("POST"); // PUT is another valid option
            urlConnection.setDoOutput(true);

            StringBuilder sb = new StringBuilder();
            for(Map.Entry<String,String> entry : postData.entrySet()) {
                sb.append(URLEncoder.encode(entry.getKey(), "UTF-8")).append("=").append(URLEncoder.encode(entry.getValue(), "UTF-8")).append("&");
            }
            sb.setLength(sb.length() - 1);
            byte[] out = sb.toString().getBytes(StandardCharsets.UTF_8);

            urlConnection.setFixedLengthStreamingMode(out.length);
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            urlConnection.connect();
            try
            {
                OutputStream os = urlConnection.getOutputStream();
                os.write(out);
                os.flush();
                os.close();

                InputStream in = urlConnection.getInputStream();
                Scanner s = new Scanner(in).useDelimiter("\\A");
                String result = s.hasNext() ? s.next() : "";
                in.close();

                if(urlConnection.getResponseCode() != 200) {
                    throw new RuntimeException("Server error: " + result);
                }

                return result;
            } catch (FileNotFoundException e) {
                return null;
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return null;
            }
        } catch (IOException  e) {
            e.printStackTrace();
            return null;
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

        // demo object
        BankAccount k = new BankAccount("Paul", "4g4sd5545f5sd",5);

        // post params
        HashMap<String,String> postData = new HashMap<>();
        postData.put("uid", k.accountNumber);
        postData.put("data", k.toJson());
        postData.put("date", new Date().toString());

        // send post
        System.out.println(sendStringHTTP("http://gs-sys.de:8000/demo/index.php?id=1", postData));

        // ask stored data, send before
        String back = loadToString("http://gs-sys.de:8000/demo/get.php?id=1&uid=" + k.accountNumber);
        System.out.println(back);

        // restore demo object
        BankAccount kk = new BankAccount(back.split("\\|")[1]);

        System.out.println(kk.number);
    }

}
