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

package de.gs_sys.java.GuiFX;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class PasswordEntry {
    private String serviceName;
    private String username;
    private String password;
    private String email;
    private String url;
    private String customerID;
    private String moreInformation;
    private String notice;
    private Date date;

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    public PasswordEntry(String serviceName, String username, String password, String email, String url, String customerID, String moreInformation, String notice) {
        this.serviceName = serviceName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.url = url;
        this.customerID = customerID;
        this.moreInformation = moreInformation;
        this.notice = notice;
        this.date = new Date();
    }

    public PasswordEntry(String ... values)
    {
        if(values.length < 8 || values.length > 9) {
            throw  new RuntimeException("Invalid length: " + values.length + " " + Arrays.toString(values));
        }

        this.serviceName = values[0];
        this.username = values[1];
        this.password = values[2];
        this.email = values[3];
        this.url = values[4];
        this.customerID = values[5];
        this.moreInformation = values[6];
        this.notice = values[7];
        this.date = new Date();
    }

    @Override
    public String toString()
    {
        return serviceName + " " + username + " " + password + " " + email + " " + url + " " + customerID + " " + moreInformation + " " + notice + " " + getDate();
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getMoreInformation() {
        return moreInformation;
    }

    public void setMoreInformation(String moreInformation) {
        this.moreInformation = moreInformation;
    }

    public String getDate() {
        return dateFormat.format(date);
    }

    public String[] values()
    {
        return new String[]{serviceName, username, password, email, url, customerID, moreInformation, notice, getDate()};
    }

    public void updateValues(String ... values)
    {
        if(values.length != 9) {
            System.out.println("Invalid length: " + values.length + " " + Arrays.toString(values));
            return;
        }

        this.serviceName = values[0];
        this.username = values[1];
        this.password = values[2];
        this.email = values[3];
        this.url = values[4];
        this.customerID = values[5];
        this.moreInformation = values[6];
        this.notice = values[7];
        try {
            this.date = dateFormat.parse(values[8]);
        } catch (ParseException e) {
            this.date = new Date();
        }
    }
}

