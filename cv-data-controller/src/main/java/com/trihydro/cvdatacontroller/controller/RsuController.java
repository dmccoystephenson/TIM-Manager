package com.trihydro.cvdatacontroller.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.trihydro.library.model.WydotRsu;
import com.trihydro.library.model.WydotRsuTim;
import com.trihydro.library.service.RsuService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.annotations.ApiIgnore;

@CrossOrigin
@RestController
@ApiIgnore
public class RsuController extends BaseController {

	@RequestMapping(value = "/rsus", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<WydotRsu> selectAllRsus() throws Exception {
		List<WydotRsu> rsus = RsuService.selectAll();
		return rsus;
	}

	@RequestMapping(value = "/selectActiveRSUs", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<WydotRsu> selectActiveRsus() {
		List<WydotRsu> rsus = RsuService.selectActiveRSUs();
		return rsus;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/selectRsusInBuffer/{direction}/{startingMilepost}/{endingMilepost}")
	public List<WydotRsu> selectRsusInBuffer(@PathVariable String direction, @PathVariable Double startingMilepost,
			@PathVariable Double endingMilepost) {
		List<WydotRsu> rsus = RsuService.selectRsusInBuffer(direction, startingMilepost, endingMilepost);
		return rsus;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/rsus-for-tim/{timId}")
	public List<WydotRsuTim> getFullRsusTimIsOn(@PathVariable Long timId) {
		List<WydotRsuTim> rsus = new ArrayList<WydotRsuTim>();
		Connection connection = null;
		ResultSet rs = null;
		Statement statement = null;

		try {
			connection = GetConnectionPool();
			statement = connection.createStatement();

			// select all RSUs that are labeled as 'Existing' in the WYDOT view
			rs = statement.executeQuery(
					"select rsu.*, tim_rsu.rsu_index, rsu_vw.latitude, rsu_vw.longitude, rsu_vw.ipv4_address from rsu inner join rsu_vw on rsu.deviceid = rsu_vw.deviceid inner join tim_rsu on tim_rsu.rsu_id = rsu.rsu_id where tim_rsu.tim_id = "
							+ timId);

			while (rs.next()) {
				WydotRsuTim rsu = new WydotRsuTim();
				rsu.setRsuTarget(rs.getString("ipv4_address"));
				rsu.setLatitude(rs.getDouble("latitude"));
				rsu.setLongitude(rs.getDouble("longitude"));
				rsu.setIndex(rs.getInt("rsu_index"));
				rsu.setRsuUsername(rs.getString("update_username"));
				rsu.setRsuPassword(rs.getString("update_password"));
				rsus.add(rsu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// close prepared statement
				if (statement != null)
					statement.close();
				// return connection back to pool
				if (connection != null)
					connection.close();
				// close result set
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rsus;
	}
}
