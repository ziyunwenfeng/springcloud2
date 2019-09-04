CREATE DEFINER=`root`@`%` PROCEDURE `p_ops_statistic`()
BEGIN
	DECLARE tatalAlarmNum INT DEFAULT 0;

	SELECT count(*) INTO tatalAlarmNum FROM t_alarm_record;
	INSERT INTO t_alarm_statistic(alarm_num) VALUES (tatalAlarmNum);
	commit;
END