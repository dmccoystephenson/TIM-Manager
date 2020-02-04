package com.trihydro.library.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.trihydro.library.helpers.DbUtility;
import com.trihydro.library.helpers.SQLNullHandler;
import com.trihydro.library.tables.TimOracleTables;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import us.dot.its.jpo.ode.plugin.j2735.OdePosition3D;
import us.dot.its.jpo.ode.plugin.j2735.OdeTravelerInformationMessage.DataFrame.Region;

public class RegionService extends CvDataServiceLibrary {

	public static Long insertRegion(Long dataFrameId, Long pathId, Region region) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DbUtility.getConnectionPool();
			String insertQueryStatement = TimOracleTables.buildInsertQueryStatement("region",
					TimOracleTables.getRegionTable());
			preparedStatement = connection.prepareStatement(insertQueryStatement, new String[] { "region_id" });

			OdePosition3D anchor = null;
			if (region != null)
				anchor = region.getAnchorPosition();

			int fieldNum = 1;

			Region.Geometry geometry = region.getGeometry();

			for (String col : TimOracleTables.getRegionTable()) {
				if (col.equals("DATA_FRAME_ID"))
					SQLNullHandler.setLongOrNull(preparedStatement, fieldNum, dataFrameId);
				else if (col.equals("NAME"))
					SQLNullHandler.setStringOrNull(preparedStatement, fieldNum, region.getName());
				else if (col.equals("LANE_WIDTH"))
					SQLNullHandler.setBigDecimalOrNull(preparedStatement, fieldNum, region.getLaneWidth());
				else if (col.equals("DIRECTIONALITY"))
					SQLNullHandler.setBigDecimalOrNull(preparedStatement, fieldNum, region.getDirectionality());
				else if (col.equals("DIRECTION"))
					SQLNullHandler.setStringOrNull(preparedStatement, fieldNum, region.getDirection());
				else if (col.equals("CLOSED_PATH"))
					preparedStatement.setBoolean(fieldNum, region.isClosedPath());
				else if (col.equals("ANCHOR_LAT") && anchor != null)
					SQLNullHandler.setBigDecimalOrNull(preparedStatement, fieldNum, anchor.getLatitude());
				else if (col.equals("ANCHOR_LONG") && anchor != null)
					SQLNullHandler.setBigDecimalOrNull(preparedStatement, fieldNum, anchor.getLongitude());
				else if (col.equals("PATH_ID"))
					SQLNullHandler.setLongOrNull(preparedStatement, fieldNum, pathId);
				else if (col.equals("GEOMETRY_DIRECTION")) {
					String direction = (pathId == null && geometry != null) ? geometry.getDirection() : null;
					SQLNullHandler.setStringOrNull(preparedStatement, fieldNum, direction);
				} else if (col.equals("GEOMETRY_EXTENT")) {
					Integer extent = (pathId == null && geometry != null) ? geometry.getExtent() : null;
					SQLNullHandler.setIntegerOrNull(preparedStatement, fieldNum, extent);
				} else if (col.equals("GEOMETRY_LANE_WIDTH")) {
					BigDecimal laneWidth = (pathId == null && geometry != null) ? geometry.getLaneWidth() : null;
					SQLNullHandler.setBigDecimalOrNull(preparedStatement, fieldNum, laneWidth);
				} else if (col.equals("GEOMETRY_CIRCLE_POSITION_LAT")) {
					BigDecimal lat = null;
					if (pathId == null && geometry != null && geometry.getCircle() != null
							&& geometry.getCircle().getPosition() != null) {
						lat = geometry.getCircle().getPosition().getLatitude();
					}
					SQLNullHandler.setBigDecimalOrNull(preparedStatement, fieldNum, lat);
				} else if (col.equals("GEOMETRY_CIRCLE_POSITION_LONG")) {
					BigDecimal lon = null;
					if (pathId == null && geometry != null && geometry.getCircle() != null
							&& geometry.getCircle().getPosition() != null) {
						lon = geometry.getCircle().getPosition().getLongitude();
					}
					SQLNullHandler.setBigDecimalOrNull(preparedStatement, fieldNum, lon);
				} else if (col.equals("GEOMETRY_CIRCLE_POSITION_ELEV")) {
					BigDecimal elev = null;
					if (pathId == null && geometry != null && geometry.getCircle() != null
							&& geometry.getCircle().getPosition() != null) {
						elev = geometry.getCircle().getPosition().getElevation();
					}
					SQLNullHandler.setBigDecimalOrNull(preparedStatement, fieldNum, elev);
				} else if (col.equals("GEOMETRY_CIRCLE_RADIUS")) {
					Integer rad = (pathId == null && geometry != null && geometry.getCircle() != null)
							? geometry.getCircle().getRadius()
							: null;
					SQLNullHandler.setIntegerOrNull(preparedStatement, fieldNum, rad);
				} else if (col.equals("GEOMETRY_CIRCLE_UNITS")) {
					String units = (pathId == null && geometry != null && geometry.getCircle() != null)
							? geometry.getCircle().getUnits()
							: null;
					SQLNullHandler.setStringOrNull(preparedStatement, fieldNum, units);
				}
				fieldNum++;
			}

			// execute insert statement
			Long regionId = log(preparedStatement, "regionID");
			return regionId;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// close prepared statement
				if (preparedStatement != null)
					preparedStatement.close();
				// return connection back to pool
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return new Long(0);
	}

	public static Boolean updateRegionName(Long regionId, String name) {
		String url = String.format("%s/update-region-name/%d/%s", CVRestUrl, regionId, name);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<Boolean> response = RestTemplateProvider.GetRestTemplate().exchange(url, HttpMethod.PUT, entity,
				Boolean.class);
		return response.getBody();
	}

}
