package com.trihydro.library.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.trihydro.library.helpers.DbUtility;
import com.trihydro.library.model.TimType;
import com.trihydro.library.service.CvDataServiceLibrary;

public class TimTypeService extends CvDataServiceLibrary { 

    public static List<TimType> selectAll() {
    	List<TimType> timTypes = new ArrayList<TimType>();
		Connection connection = null;
		ResultSet rs = null;
		Statement statement = null;

		try {
			connection = DbUtility.getConnectionPool();
			statement = connection.createStatement();

			// build SQL statement
				rs = statement.executeQuery("select * from TIM_TYPE");
				// convert to tim type objects   			
				while (rs.next()) {   			
					TimType timType = new TimType();
					timType.setTimTypeId(rs.getLong("TIM_TYPE_ID"));
					timType.setType(rs.getString("TYPE"));	
					timType.setDescription(rs.getString("DESCRIPTION"));			   
					timTypes.add(timType);					
				}
			} 
        catch (SQLException e) {
            e.printStackTrace();
		}
		finally {			
			try {
				// close prepared statement
				if(statement != null)
					statement.close();
				// return connection back to pool
				if(connection != null)
					connection.close();
				// close result set
				if(rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
        return timTypes;
    }
}