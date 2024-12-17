package com.anidra.db.consumer.repository.impl;

import com.anidra.db.consumer.dto.VitalsDto;
import com.anidra.db.consumer.entity.SensorData;
import com.anidra.db.consumer.repository.SensorDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

import static com.anidra.db.consumer.util.CommonUtils.generateUserKey;

@Slf4j
@Repository
public class SensorDataRepositoryImpl implements SensorDataRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_SENSOR_DATA_SQL =
            "INSERT INTO pm_sensor_data " +
                    "(SENSOR_DATA_KEY, SENSOR_ALLOC_ID, SENSOR_ID, SPO2, RESP_RATE, PULSE_RATE, " +
                    "TEMPERATURE, DIA_PRESSURE, SYS_PRESSURE, CREATE_TIMESTAMP) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    @Override
    public void saveSensorData(VitalsDto sensorData) {
        try {
            log.info("Sensor data received {}", sensorData.toString());
            Object[] params = prepareSensorDataParams(sensorData);
            jdbcTemplate.update(INSERT_SENSOR_DATA_SQL, params);
        } catch (Exception e) {
            log.error("Failed to save sensor data {}", e.getMessage());
        }
    }

    private Object[] prepareSensorDataParams(VitalsDto sensorData) {
        return new Object[]{
                generateUserKey(19),
                sensorData.getSensorDataKey(),
                sensorData.getSensorId(),
                sensorData.getSpO2(),
                sensorData.getRespiratoryRate(),
                sensorData.getPulseRate(),
                sensorData.getTemperature(),
                sensorData.getDiaPressure(),
                sensorData.getSysPressure(),
                sensorData.getCreateTimeStamp()
        };
    }

    @Override
    public List<VitalsDto> getAllSensorData() {
//        List<SensorData> sensorData = new ArrayList<>();
        try {
            int count = jdbcTemplate.queryForObject("select count(*) from pm_sensor_data", Integer.class);
            System.out.println("Row count is: " + count);
            List<SensorData> sensorData = jdbcTemplate.queryForList("SELECT * from pm_sensor_data", SensorData.class);
            for (SensorData data : sensorData) {
                System.out.println(data);
            }
        } catch (Exception e) {
            System.err.println("Exception occurred while fetching data " + e.getMessage());
        }
        return Collections.emptyList();
    }
}
