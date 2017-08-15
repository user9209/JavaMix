
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class BankAccount {
    String user;
    String accountNumber;
    int number;

    public BankAccount(String user, String accountNumber, int number) {
        this.user = user;
        this.accountNumber = accountNumber;
        this.number = number;
    }

    public BankAccount(String json) {
        Gson gson = new GsonBuilder().create();
        BankAccount k = gson.fromJson(json, BankAccount.class);

        this.user = k.user;
        this.accountNumber = k.accountNumber;
        this.number = k.number;
    }

    public String toJson()
    {
        Gson gson = new GsonBuilder().create();

        return gson.toJson(this);
    }
}
