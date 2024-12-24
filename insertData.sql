INSERT INTO anidra.sensor (
sensor_id,
sensor_MAC,
tablet_MAC,
sensor_name,
tablet_name,
hospital_key,
active,
API_ACCESS_KEY) VALUES
(
'SK2UqpcnlUM55BrEzdeq',
'0',
'2C:FD:AB:88:A0:3B',
'Anidra Demo Kit (Jinudev)',
'Tab 7 ',
'HKfxKkFamTJhKlGPWHzg',
1,
'SAbKgOqKtEW2ji4vm7mJ');

INSERT INTO anidra.pm_sensor_allocation (SENSOR_ALLOC_ID,SENSOR_ID,ADMISSION_KEY,EFFECTIVE_FROM,EFFECTIVE_TO,CREATE_TIMESTAMP,MODIFY_TIMESTAMP,CREATE_USERID,MODIFY_USERID,CREATED_APPID,MODIFY_APPID,alerts_status) VALUES
('ABNPNXr7BqFg0tP4z246','SK2UqpcnlUM55BrEzdeq','PAzX0qyB1GjCQ2jFQGgP','2019-08-20 13:55:00','2019-09-02 12:43:55','2019-08-20 08:25:00','2019-08-20 13:55:00','UKtHh6Xfgns1vrT40E4E','UKtHh6Xfgns1vrT40E4E',NULL,NULL,'Y');


INSERT INTO anidra.pm_sensor_data (
SENSOR_DATA_KEY,
SENSOR_ALLOC_ID,
SENSOR_ID,
SPO2,
RESP_RATE,
PULSE_RATE,
TEMPERATURE,
DIA_PRESSURE,
SYS_PRESSURE,
CREATE_TIMESTAMP) VALUES
	 (
	 'Kg6FGC2lY936l4CZaCN5',
	 'ABNPNXr7BqFg0tP4z246',
	 'SK2UqpcnlUM55BrEzdeq',
	 96,
	 0,
	 90,
	 0.0,
	 0,
	 0,
	 '2019-07-15 10:45:32');