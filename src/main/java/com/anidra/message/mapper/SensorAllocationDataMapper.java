package com.anidra.message.mapper;

import com.anidra.message.dto.SensorAllocation;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SensorAllocationDataMapper implements RowMapper<SensorAllocation> {

    @Override
    public SensorAllocation mapRow(ResultSet rs, int rowNum) throws SQLException {
        SensorAllocation sensorAllocation = new SensorAllocation();
        sensorAllocation.setSensorId(rs.getString("sensor_id"));
        sensorAllocation.setSensorAllocationId(rs.getString("sensor_alloc_id"));
        return sensorAllocation;
    }

}
