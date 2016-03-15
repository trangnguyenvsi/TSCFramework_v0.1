package com.vsii.tsc.guru.testcase;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.vsii.tsc.utility.DBConnection;

public class testDatabase {

		public static void main (String[] args) throws IOException, SQLException {
			ResultSet rs = DBConnection.connectMySQL("sqlCommand_02");
			/*next() returns true if next row is present otherwise it returns false. */
			while(rs.next()){
			//printing the result
			System.out.println(rs.getString("trangthai"));
			}

//			ResultSet rs = DBConnection.connectSQLServer("sqlCommand_03");
//			/*next() returns true if next row is present otherwise it returns false. */
//			while(rs.next()){
//			//printing the result
//			System.out.println(rs.getString("AccountId"));
//			}
		}
}
