CREATE DEFINER=`root`@`%` PROCEDURE `p_res_statistic`()
BEGIN
    DECLARE stationNum int DEFAULT 0;
    DECLARE pileNum int DEFAULT 0;
    DECLARE gunNum int DEFAULT 0;
    DECLARE chargingGunNum int DEFAULT 0;
    DECLARE freeGunNum int DEFAULT 0;
    DECLARE badGunNum int DEFAULT 0;
    DECLARE offGunNum int DEFAULT 0;
    DECLARE totalPower int DEFAULT 0;
    DECLARE stationNumOfOperator int DEFAULT 0;
    DECLARE pileNumOfOperator int DEFAULT 0;
    DECLARE operatorId varchar(30) DEFAULT null;

		DECLARE selfStationNum INT DEFAULT 0;
		DECLARE independentStationNum INT DEFAULT 0;
		DECLARE cecStationNum INT DEFAULT 0;
		DECLARE selfStationFastNum INT DEFAULT 0;
		DECLARE selfStationSlowNum INT DEFAULT 0;
		DECLARE independentStationFastNum INT DEFAULT 0;
		DECLARE independentStationSlowNum INT DEFAULT 0;
		DECLARE cecStationFastNum INT DEFAULT 0;
		DECLARE cecStationSlowNum INT DEFAULT 0;

		DECLARE selfPileNum INT DEFAULT 0;
		DECLARE independentPileNum INT DEFAULT 0;
		DECLARE cecPileNum INT DEFAULT 0;
		DECLARE selfPileFastNum INT DEFAULT 0;
		DECLARE selfPileSlowNum INT DEFAULT 0;
		DECLARE independentPileFastNum INT DEFAULT 0;
		DECLARE independentPileSlowNum INT DEFAULT 0;
		DECLARE cecPileFastNum INT DEFAULT 0;
		DECLARE cecPileSlowNum INT DEFAULT 0;

    DECLARE resultCode int default 1;
		DECLARE flag int default 0;
    DECLARE t_cusrselect CURSOR for select operator_id, count(*) from t_station group by operator_id;
    DECLARE continue handler for SQLEXCEPTION set resultCode = 0;
		DECLARE CONTINUE HANDLER FOR NOT FOUND set flag=1;
    start transaction;
    select count(*) into stationNum from t_station;
    select count(*) into pileNum from t_pile;
    select count(*) into gunNum from t_gun;
    select count(*) into offGunNum from t_gun where state = '0';
    select count(*) into chargingGunNum from t_gun where state = '1';
    select count(*) into badGunNum from t_gun where state = '4';
    set freeGunNUm = gunNum - chargingGunNum - badGunNum - offGunNum;
    /*
      按照运营商统计场站和桩数量。首先清空表中数据，重新插入。
SELECT count(*) into pileNumOfOperator from t_pile where operator_id = operatorId;
  theLoop:LOOP
      FETCH t_cusrselect
      into operatorId, stationNumOfOperator;
			IF resultCode = 0 THEN LEAVE theLoop;
			END IF;

    END LOOP;
    */
    delete from t_operator_station_statistic;
    commit;
    OPEN t_cusrselect;
		theLoop:LOOP
			FETCH t_cusrselect INTO operatorId,stationNumOfOperator;
			IF flag = 1 THEN LEAVE theLoop;
			END IF;
			SELECT count(*) into pileNumOfOperator from t_pile where operator_id = operatorId;
			INSERT INTO t_operator_station_statistic(operatorId,stationNum,pileNum) VALUES(operatorId,stationNumOfOperator,pileNumOfOperator);
		END LOOP;
		COMMIT;
		CLOSE t_cusrselect;

    /*统计快充慢充互联互通场站数量，桩数量，快充慢充数量。首先清空表中数据，重新插入*/

		SELECT COUNT(*) into selfStationNum from t_station WHERE business_type = 'own';
		SELECT COUNT(*) into independentStationNum from t_station WHERE business_type = 'OTHER';
		SELECT COUNT(*) into cecStationNum from t_station WHERE is_cec = 'Y';

		SELECT COUNT(*) into selfPileNum from t_station s, t_pile p where s.id = p.station_id and s.business_type='own';
		SELECT COUNT(*) into independentPileNum from t_station s, t_pile p where s.id = p.station_id and s.business_type='OTHER';
		SELECT COUNT(*) into cecPileNum from t_station s, t_pile p where s.id = p.station_id and s.is_cec='Y';
		SELECT COUNT(*) into selfPileFastNum from t_station s, t_pile p where s.id = p.station_id and s.business_type='own' and p.fast_or_slow='F';
		SELECT COUNT(*) into selfPileSlowNum from t_station s, t_pile p where s.id = p.station_id and s.business_type='own' and p.fast_or_slow='S';
		SELECT COUNT(*) into independentPileFastNum from t_station s, t_pile p where s.id = p.station_id and s.business_type='OTHER' and p.fast_or_slow='F';
		SELECT COUNT(*) into independentPileSlowNum from t_station s, t_pile p where s.id = p.station_id and s.business_type='OTHER' and p.fast_or_slow='S';
		SELECT COUNT(*) into cecPileFastNum from t_station s, t_pile p where s.id = p.station_id and s.is_cec='Y' and p.fast_or_slow='F';
		SELECT COUNT(*) into cecPileSlowNum from t_station s, t_pile p where s.id = p.station_id and s.is_cec='Y' and p.fast_or_slow='S';


		/*更新统计表*/
    insert into t_res_statistics (
				station_num,
				pile_num,
				gun_num,
				charging_gun_num,
				free_gun_num,
				off_gun_num,
				bad_gun_num,
				self_station_num,
				independent_station_num,
				cec_station_num,
				self_pile_num,
				independent_pile_num,
				cec_pile_num,
				self_pile_fast_num,
				self_pile_slow_num,
				independent_pile_fast_num,
				independent_pile_slow_num,
				cec_pile_fast_num,
				cec_pile_slow_num
		)
    values (
				stationNum,
				pileNum,
				gunNum,
				chargingGunNum,
				freeGunNUm,
				offGunNum,
				badGunNum,
				selfStationNum,
				independentStationNum,
				cecStationNum,
				selfPileNum,
				independentPileNum,
				cecPileNum,
				selfPileFastNum,
				selfPileSlowNum,
				independentPileFastNum,
				independentPileSlowNum,
				cecPileFastNum,
				cecPileSlowNum
		);
    IF resultCode = 0
    THEN
      ROLLBACK;
    ELSE
      COMMIT;
    END IF;
  END