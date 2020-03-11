package com.trihydro.loggerkafkaconsumer.app.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.trihydro.library.helpers.SQLNullHandler;
import com.trihydro.library.model.ActiveTim;
import com.trihydro.library.tables.TimOracleTables;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActiveTimService extends BaseService {

    private TimOracleTables timOracleTables;
    private SQLNullHandler sqlNullHandler;

    @Autowired
    public void InjectDependencies(TimOracleTables _timOracleTables, SQLNullHandler _sqlNullHandler) {
        timOracleTables = _timOracleTables;
        sqlNullHandler = _sqlNullHandler;
    }

    public Long insertActiveTim(ActiveTim activeTim) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            String insertQueryStatement = timOracleTables.buildInsertQueryStatement("active_tim",
                    timOracleTables.getActiveTimTable());

            // get connection
            connection = GetConnectionPool();

            preparedStatement = connection.prepareStatement(insertQueryStatement, new String[] { "active_tim_id" });
            int fieldNum = 1;

            for (String col : timOracleTables.getActiveTimTable()) {
                if (col.equals("TIM_ID"))
                    sqlNullHandler.setLongOrNull(preparedStatement, fieldNum, activeTim.getTimId());
                else if (col.equals("MILEPOST_START"))
                    sqlNullHandler.setDoubleOrNull(preparedStatement, fieldNum, activeTim.getMilepostStart());
                else if (col.equals("MILEPOST_STOP"))
                    sqlNullHandler.setDoubleOrNull(preparedStatement, fieldNum, activeTim.getMilepostStop());
                else if (col.equals("DIRECTION"))
                    sqlNullHandler.setStringOrNull(preparedStatement, fieldNum, activeTim.getDirection());
                else if (col.equals("TIM_START"))
                    sqlNullHandler.setTimestampOrNull(preparedStatement, fieldNum, java.sql.Timestamp.valueOf(
                            LocalDateTime.parse(activeTim.getStartDateTime(), DateTimeFormatter.ISO_DATE_TIME)));
                else if (col.equals("TIM_END"))
                    if (activeTim.getEndDateTime() != null)
                        sqlNullHandler.setTimestampOrNull(preparedStatement, fieldNum, java.sql.Timestamp.valueOf(
                                LocalDateTime.parse(activeTim.getEndDateTime(), DateTimeFormatter.ISO_DATE_TIME)));
                    else
                        preparedStatement.setNull(fieldNum, java.sql.Types.TIMESTAMP);
                else if (col.equals("TIM_TYPE_ID"))
                    sqlNullHandler.setLongOrNull(preparedStatement, fieldNum, activeTim.getTimTypeId());
                else if (col.equals("ROUTE"))
                    sqlNullHandler.setStringOrNull(preparedStatement, fieldNum, activeTim.getRoute());
                else if (col.equals("CLIENT_ID"))
                    sqlNullHandler.setStringOrNull(preparedStatement, fieldNum, activeTim.getClientId());
                else if (col.equals("SAT_RECORD_ID"))
                    sqlNullHandler.setStringOrNull(preparedStatement, fieldNum, activeTim.getSatRecordId());
                else if (col.equals("PK"))
                    sqlNullHandler.setIntegerOrNull(preparedStatement, fieldNum, activeTim.getPk());

                fieldNum++;
            }

            Long activeTimId = executeAndLog(preparedStatement, "active tim");
            return activeTimId;
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

    public boolean updateActiveTim(ActiveTim activeTim) {

        boolean activeTimIdResult = false;
        String updateTableSQL = "UPDATE ACTIVE_TIM SET TIM_ID = ?, MILEPOST_START = ?, MILEPOST_STOP = ?, TIM_START = ?, TIM_END = ?, PK = ?"
                + " WHERE ACTIVE_TIM_ID = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = GetConnectionPool();
            preparedStatement = connection.prepareStatement(updateTableSQL);
            sqlNullHandler.setLongOrNull(preparedStatement, 1, activeTim.getTimId());
            sqlNullHandler.setDoubleOrNull(preparedStatement, 2, activeTim.getMilepostStart());
            sqlNullHandler.setDoubleOrNull(preparedStatement, 3, activeTim.getMilepostStop());
            sqlNullHandler.setTimestampOrNull(preparedStatement, 4, java.sql.Timestamp
                    .valueOf(LocalDateTime.parse(activeTim.getStartDateTime(), DateTimeFormatter.ISO_DATE_TIME)));

            if (activeTim.getEndDateTime() == null)
                preparedStatement.setString(5, null);
            else
                sqlNullHandler.setTimestampOrNull(preparedStatement, 5, java.sql.Timestamp
                        .valueOf(LocalDateTime.parse(activeTim.getEndDateTime(), DateTimeFormatter.ISO_DATE_TIME)));

            sqlNullHandler.setIntegerOrNull(preparedStatement, 6, activeTim.getPk());
            sqlNullHandler.setLongOrNull(preparedStatement, 7, activeTim.getActiveTimId());
            activeTimIdResult = updateOrDelete(preparedStatement);
            System.out.println("------ Updated active_tim with id: " + activeTim.getActiveTimId() + " --------------");
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

        return activeTimIdResult;
    }

    public ActiveTim getActiveSatTim(String satRecordId, String direction) {

        ActiveTim activeTim = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            connection = GetConnectionPool();
            statement = connection.createStatement();
            String query = "select * from active_tim";
            query += " where sat_record_id = '" + satRecordId + "' and active_tim.direction = '" + direction + "'";

            rs = statement.executeQuery(query);

            // convert to ActiveTim object
            while (rs.next()) {
                activeTim = new ActiveTim();
                activeTim.setActiveTimId(rs.getLong("ACTIVE_TIM_ID"));
                activeTim.setTimId(rs.getLong("TIM_ID"));
                activeTim.setSatRecordId(rs.getString("SAT_RECORD_ID"));
                activeTim.setClientId(rs.getString("CLIENT_ID"));
                activeTim.setDirection(rs.getString("DIRECTION"));
                activeTim.setEndDateTime(rs.getString("TIM_END"));
                activeTim.setStartDateTime(rs.getString("TIM_START"));
                activeTim.setMilepostStart(rs.getDouble("MILEPOST_START"));
                activeTim.setMilepostStop(rs.getDouble("MILEPOST_STOP"));
                activeTim.setRoute(rs.getString("ROUTE"));
                activeTim.setPk(rs.getInt("PK"));
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

        return activeTim;
    }

    public ActiveTim getActiveRsuTim(String clientId, String direction, String ipv4Address) {

        ActiveTim activeTim = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            connection = GetConnectionPool();
            statement = connection.createStatement();
            String query = "select * from active_tim";
            query += " inner join tim_rsu on active_tim.tim_id = tim_rsu.tim_id";
            query += " inner join rsu on tim_rsu.rsu_id = rsu.rsu_id";
            query += " inner join rsu_vw on rsu.deviceid = rsu_vw.deviceid";
            query += " where ipv4_address = '" + ipv4Address + "' and client_id = '" + clientId
                    + "' and active_tim.direction = '" + direction + "'";

            rs = statement.executeQuery(query);

            // convert to ActiveTim object
            while (rs.next()) {
                activeTim = new ActiveTim();
                activeTim.setActiveTimId(rs.getLong("ACTIVE_TIM_ID"));
                activeTim.setTimId(rs.getLong("TIM_ID"));
                activeTim.setSatRecordId(rs.getString("SAT_RECORD_ID"));
                activeTim.setClientId(rs.getString("CLIENT_ID"));
                activeTim.setDirection(rs.getString("DIRECTION"));
                activeTim.setEndDateTime(rs.getString("TIM_END"));
                activeTim.setStartDateTime(rs.getString("TIM_START"));
                activeTim.setMilepostStart(rs.getDouble("MILEPOST_START"));
                activeTim.setMilepostStop(rs.getDouble("MILEPOST_STOP"));
                activeTim.setRoute(rs.getString("ROUTE"));
                activeTim.setPk(rs.getInt("PK"));
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

        return activeTim;
    }
}