package com.anidra.message.repository.impl;

import com.anidra.message.dto.SensorAllocation;
import com.anidra.message.mapper.SensorAllocationDataMapper;
import com.anidra.message.repository.SensorAllocationRepository;
import com.anidra.message.util.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Slf4j
@Repository
public class SensorAllocationRepositoryImpl implements SensorAllocationRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String GET_ONLINE_SENSORS_SQL =
            "select sensor_id, sensor_alloc_id from pm_sensor_allocation WHERE effective_to is null AND effective_from  >= ? ORDER BY effective_from DESC ";

    @Override
    public List<SensorAllocation> getAllOnlineSensors() {

        java.sql.Timestamp sqlTimestamp = Timestamp.valueOf(CommonUtils.localDateTimeFromString());
        java.sql.Date sqlDate = new java.sql.Date(sqlTimestamp.getTime());

        log.info("Incomind date to SQL Query {} sqlDate {}", CommonUtils.localDateTimeFromString(), sqlDate);
        List<SensorAllocation> sensorAllocations = jdbcTemplate.query(GET_ONLINE_SENSORS_SQL,
                ps -> ps.setDate(1, new java.sql.Date(sqlTimestamp.getTime())), new SensorAllocationDataMapper());
        log.info("SensorAllocationRepositoryImpl::getAllOnlineSensors sensor allocations {}", sensorAllocations.size());
        return sensorAllocations;
    }
}
