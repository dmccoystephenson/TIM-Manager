package com.trihydro.cvdatacontroller.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.trihydro.library.helpers.SQLNullHandler;
import com.trihydro.library.tables.TimOracleTables;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.annotations.ApiIgnore;

@CrossOrigin
@RestController
@RequestMapping("path")
@ApiIgnore
public class PathController extends BaseController {

    private TimOracleTables timOracleTables;
    private SQLNullHandler sqlNullHandler;

    @Autowired
    public void InjectDependencies(TimOracleTables _timOracleTables, SQLNullHandler _sqlNullHandler) {
        timOracleTables = _timOracleTables;
        sqlNullHandler = _sqlNullHandler;
    }

    @RequestMapping(value = "/add-path", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<Long> InsertPath() {

        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
            connection = GetConnectionPool();
            String insertQueryStatement = timOracleTables.buildInsertQueryStatement("path",
                    timOracleTables.getPathTable());
            preparedStatement = connection.prepareStatement(insertQueryStatement, new String[] { "path_id" });
            int fieldNum = 1;

            for (String col : timOracleTables.getPathTable()) {
                if (col.equals("SCALE"))
                    sqlNullHandler.setIntegerOrNull(preparedStatement, fieldNum, 0);
                fieldNum++;
            }
            // execute insert statement
            Long pathId = executeAndLog(preparedStatement, "pathId");
            return ResponseEntity.ok(pathId);
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
        // if we got here, its an error
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Long(0));
    }
}