package com.postalweb.states;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.postalweb.config.Db;

public class FindStatesCites {
	public Map<String, Integer> getStates() {

		Db db = new Db();
		Connection con_check = db.mysqlConnect();
		ResultSet rs_check = null;
		Statement stmt_check = null;
		Map<String, Integer> map = new HashMap<>();
		String states = "";
		Integer state_id;

		try {

			String getStates_query = "SELECT state_id,state_name FROM master_states";
			stmt_check = con_check.createStatement();
			rs_check = stmt_check.executeQuery(getStates_query);
			
			while (rs_check.next()) {

				state_id = rs_check.getInt("state_id");
				states = rs_check.getString("state_name");
				
				String replaceAll = states.replaceAll("\\s+", "").toLowerCase();
				
				map.put(replaceAll, state_id);
				
				
			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			try {

				if (rs_check != null) {

					rs_check.close();

				} else if (stmt_check != null) {

					stmt_check.close();

				} else if (con_check != null) {

					con_check.close();

				}

			} catch (Exception e) {

				e.printStackTrace();
			}

		}

		return map;
	}

	public Map<Integer, Map<String, Integer>> getCites() {

		Db db = new Db();
		Connection con_check = db.mysqlConnect();
		ResultSet rs_check = null;
		Statement stmt_check = null;
		Map<String, Integer> map = new HashMap<>();
		Map<Integer, Map<String, Integer>> map1 = new HashMap<>();

		Integer city_id;
		Integer state_id = 0;
		Integer state_id1 = 1;
		String city_name = "";

		try {

			String getStates_query = "SELECT city_id,state_id,city_name FROM master_cities ORDER BY state_id ASC";
			stmt_check = con_check.createStatement();
			rs_check = stmt_check.executeQuery(getStates_query);

			while (rs_check.next()) {

				city_id = rs_check.getInt("city_id");
				state_id = rs_check.getInt("state_id");
				city_name = rs_check.getString("city_name");
				String replaceAll = city_name.replaceAll("\\s+", "").toLowerCase();

				if (state_id1 == state_id) {
					map.put(replaceAll, city_id);
				} else {
					map1.put(state_id1, map);
					map = new HashMap<>();
					map.put(replaceAll, city_id);

				}

				state_id1 = state_id;

			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			try {

				if (rs_check != null) {

					rs_check.close();

				} else if (stmt_check != null) {

					stmt_check.close();

				} else if (con_check != null) {

					con_check.close();

				}
			} catch (Exception e) {

				e.printStackTrace();
			}

		}

		return map1;
	}
}
