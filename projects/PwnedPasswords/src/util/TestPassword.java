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

import gui.AGB;
import gui.ControlerFX;
import gui.HelperFX.StageStore;
import gui.Info;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TestPassword {

    private static final String webside = "https://haveibeenpwned.com/Passwords";
    private static final String passwordsBasic = "https://downloads.pwnedpasswords.com/passwords/pwned-passwords-1.0.txt.7z";
    private static final String passwordsUpdate1 = "https://downloads.pwnedpasswords.com/passwords/pwned-passwords-update-1.txt.7z";
    private static final String passwordsUpdate2 = "https://downloads.pwnedpasswords.com/passwords/pwned-passwords-update-2.txt.7z";

    public static final float version = 1.0f;
    public static final String date = "2017-08-01";

    public static void main(String[] args) {

        if(!FileOps.fileExists("passwords/pwned-passwords-1.0.txt.7z")) {
            FileOps.mkdir("passwords");

            System.out.println("Download Basic ...");
            if(DownloadFile.download(passwordsBasic, "passwords/pwned-passwords-1.0.txt.7z"))
                System.out.println("Basic ok");

            System.out.println("Download Update 1 ...");
            if(DownloadFile.download(passwordsUpdate1, "passwords/pwned-passwords-update-1.txt.7z"))
                System.out.println("Update 1 ok");

            System.out.println("Download Update 2 ...");
            if(DownloadFile.download(passwordsUpdate2, "passwords/pwned-passwords-update-2.txt.7z"))
                System.out.println("Update 2 ok");

            System.out.println("Complete");
        }

        if(!FileOps.fileExists("passwords/pwned-passwords-update-2.txt")) {
            System.out.println("Extract");
            if(Wrapper7zip.extract("passwords/pwned-passwords-1.0.txt.7z","passwords"))
                System.out.println("Basic ok");

            if(Wrapper7zip.extract("passwords/pwned-passwords-update-1.txt.7z","passwords"))
                System.out.println("Update 1 ok");

            if(Wrapper7zip.extract("passwords/pwned-passwords-update-2.txt.7z", "passwords"))
                System.out.println("Update 2 ok");

            System.out.println("Complete");
        }

        int versionCheck = checkVersion(args);
        if(versionCheck != 0) {
            Info.TEXT = "You may use a old version or the check has failed\n" +
                        "(no internet?)! This may contain security bugs!\n" +
                        "Please Update and try again or set --force flag in\n" +
                        "the commandline!\n" +
                        "Error: " + versionCheck;

            Info.main(new String[0]);

            System.err.println("You may use a old version or the check has failed (no internet?)! This may contain security bugs! Please Update and try again or set --force flag in the commandline!");
            System.exit(0);
        }

        try {
            new ControlerFX().start(null);
            StageStore.getStage("agb").hide();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ControlerFX().start(null) failed!");
        }
    }

    public static int checkVersion(String ... args) {

        if(args.length == 1 && args[0].equals("--force"))
            return 0;

        String versionInfo = DownloadFile.loadToString("https://www.gs-sys.de/version");

        if(versionInfo == null)
            return 404;

        String[] versionPart = versionInfo.split("\n");

        float version = Float.parseFloat(versionPart[0].replace("Version", ""));
        Date date;
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            date = df.parse(versionPart[1]);
        } catch (ParseException e) {
            e.printStackTrace();
            return -2;
        }

        String id1 = versionPart[2];
        String id2 = versionPart[3];

        Date now = new Date();

        int days = (int) ((now.getTime() - date.getTime()) / 1000 / 60 / 60 / 24);

        System.out.println("Version is " + days + " days old: " + version + " " + date.toString() + " " + id1 + " " + id2);

        return TestPassword.version == version ? 0 : -1 ;
    }

}
