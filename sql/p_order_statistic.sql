CREATE DEFINER=`root`@`%` PROCEDURE `p_order_statistic`()
BEGIN
    DECLARE totalOrderNum int(10) default 0;
    DECLARE totalChargeElectricity int(10) default 0;
    DECLARE totalFee int(10) default 0;

    DECLARE totalOrderToday int(10) default 0;
    DECLARE totalChargeElectricityToday DECIMAL(18, 6) DEFAULT 0.000000;
    DECLARE totalFeeToday DECIMAL(18, 6) DEFAULT 0.000000;
		DECLARE totalServiceFeeToday DECIMAL(18, 6) DEFAULT 0.000000;

		DECLARE totalOrderCurrentMonth int(10) default 0;
    DECLARE totalChargeElectricityCurrentMonth DECIMAL(18, 6) DEFAULT 0.000000;
    DECLARE totalFeeCurrentMonth DECIMAL(18, 6) DEFAULT 0.000000;
		DECLARE totalServiceFeeCurrentMonth DECIMAL(18, 6) DEFAULT 0.000000;

		DECLARE totalOrderCurrentYear int(10) default 0;
    DECLARE totalChargeElectricityCurrentYear DECIMAL(18, 6) DEFAULT 0.000000;
    DECLARE totalFeeCurrentYear DECIMAL(18, 6) DEFAULT 0.000000;
		DECLARE totalServiceFeeCurrentYear DECIMAL(18, 6) DEFAULT 0.000000;

    DECLARE totalFeeOfOperator DECIMAL(18, 6) DEFAULT 0.000000;
    DECLARE totalChargeElectricityOfOperator DECIMAL(18, 6) DEFAULT 0.000000;

    DECLARE totalSharpElectricity DECIMAL(18, 6) DEFAULT 0.000000;
    DECLARE totalPeakElectricity DECIMAL(18, 6) DEFAULT 0.000000;
    DECLARE totalFlatElectricity DECIMAL(18, 6) DEFAULT 0.000000;
    DECLARE totalValleyElectricity DECIMAL(18, 6) DEFAULT 0.000000;
    DECLARE totalSharpFee DECIMAL(18, 6) DEFAULT 0.000000;
    DECLARE totalPeakFee DECIMAL(18, 6) DEFAULT 0.000000;
    DECLARE totalFlatFee DECIMAL(18, 6) DEFAULT 0.000000;
    DECLARE totalValleyFee DECIMAL(18, 6) DEFAULT 0.000000;

		DECLARE monthly varchar(30) DEFAULT null;
		DECLARE monthlyFee DECIMAL(18, 6) DEFAULT 0.000000;
		DECLARE monthlyElectricity DECIMAL(18, 6) DEFAULT 0.000000;

		DECLARE payChannel varchar(30) DEFAULT null;
		DECLARE orderNumOfChannel int DEFAULT 0;

		DECLARE totalChargeTimeCurrentMonth int DEFAULT 0;

		DECLARE activeCustNum int DEFAULT 0;

		DECLARE flag int DEFAULT 0;

    DECLARE resultCode int default 1;

    DECLARE operatorId varchar(30) DEFAULT null;
    DECLARE mycusr CURSOR FOR select operator_id, sum(fee) from t_cmd202 group by operator_id order by operator_id;
		DECLARE nomthOrderCusr CURSOR FOR select month(create_time), sum(charge_total_electricity) from t_cmd202 group by month(create_time);    
		DECLARE payChannelCusr CURSOR FOR select pay_channel, count(*) from t_pay_order group by pay_channel;		
		DECLARE continue handler for SQLEXCEPTION set resultCode = 0;
		DECLARE CONTINUE HANDLER FOR NOT FOUND set flag=1;

    START TRANSACTION;
    /*统计总订单数*/
    select count(*) into totalOrderNum from t_cmd202;
    /*统计总充电量*/
    select SUM(charge_total_electricity) into totalChargeElectricity from t_cmd202;
    /*统计总金额*/
    select SUM(fee) into totalFee from t_cmd202;

    /*按照运营商统计总金额，总电量。先删除记录，重新插入*/
    DELETE FROM t_operator_order_statistic;
    COMMIT;
    OPEN mycusr;
    theLoop:LOOP
      FETCH mycusr
      into operatorId, totalFeeOfOperator;
			IF flag = 1 THEN LEAVE theLoop;
			END IF;
      SELECT SUM(charge_total_electricity) into totalChargeElectricityOfOperator FROM t_cmd202 where operator_id=operatorId;
      INSERT INTO t_operator_order_statistic(operator_id,total_charge_fee,total_charge_electricity)
      VALUES (operatorId,totalFeeOfOperator,totalChargeElectricityOfOperator);
      COMMIT;
    END LOOP;
		CLOSE mycusr;

    /*统计当日订单情况*/
    select count(*) into totalOrderToday from t_cmd202 where day(create_time) = day(current_timestamp);
    select sum(charge_total_electricity) into totalChargeElectricityToday
    from t_cmd202
    where day(create_time) = day(current_timestamp);
    select sum(fee) into totalFeeToday from t_cmtord202 where day(create_time) = day(current_timestamp);
		select sum(service_fee) into totalServiceFeeToday from t_cmd202 where day(create_time) = day(current_timestamp);

		select count(distinct phone) into activeCustNum from t_cmd202 where date_sub(current_timestamp,interval 7 day)<=date(create_time);  

		/*统计当月订单情况*/
		select count(*) into totalOrderCurrentMonth from t_cmd202 where month(create_time) = month(current_timestamp());
		select sum(charge_total_electricity) into totalChargeElectricityCurrentMonth
		from t_cmd202
		where month(create_time) = month(current_timestamp);
		select sum(fee) into totalFeeCurrentMonth from t_cmtord202 where month(create_time) = month(current_timestamp);
		select sum(service_fee) into totalServiceFeeCurrentMonth from t_cmd202 where month(create_time) = month(current_timestamp);
		select sum(charge_time_length) into totalChargeTimeCurrentMonth from t_cmd202 where month(create_time) = month(current_timestamp);

		/*统计当年订单情况*/
		select count(*) into totalOrderCurrentYear from t_cmd202 where year(create_time) = year(current_timestamp());
		select sum(charge_total_electricity) into totalChargeElectricityCurrentYear
		from t_cmd202
		where year(create_time) = year(current_timestamp);
		select sum(fee) into totalFeeCurrentYear from t_cmtord202 where year(create_time) = year(current_timestamp);
		select sum(service_fee) into totalServiceFeeCurrentYear from t_cmd202 where year(create_time) = year(current_timestamp);

    /*统计尖峰平谷总电费，服务费*/
    SELECT SUM(sharp_all_electricity),
           SUM(peak_all_electricity),
           SUM(flat_all_electricity),
           SUM(valley_all_electricity),
           SUM(sharp_all_electricity_fee),
           SUM(peak_all_electricity_fee),
           SUM(flat_all_electricity_fee),
           SUM(valley_all_electricity_fee)
        INTO
          totalSharpElectricity,
          totalPeakElectricity,
          totalFlatElectricity,
          totalValleyElectricity,
          totalSharpFee,
          totalPeakFee,
          totalFlatFee,
          totalValleyFee
    FROM t_cmd202;
		/*按月统计电量，电费*/
		set flag = 0;
		OPEN nomthOrderCusr;
		theLoop:LOOP
			FETCH nomthOrderCusr INTO monthly,monthlyElectricity;
			IF flag = 1 THEN LEAVE theLoop;
			END IF;
			SELECT SUM(fee) INTO monthlyFee FROM t_cmd202 WHERE MONTH(create_time)=monthly;
			INSERT INTO t_order_monthly(month,tatal_electricity,total_fee) VALUES (monthly,monthlyElectricity,monthlyFee);
			COMMIT;
		END LOOP;
		CLOSE nomthOrderCusr;

		/*统计不同支付渠道的订单数*/
		SET flag = 0;
		DELETE from t_pay_channel_statistic;
		COMMIT;
		OPEN payChannelCusr;
		theLoop:LOOP
			FETCH payChannelCusr INTO payChannel, orderNumOfChannel;
			IF flag = 1 THEN LEAVE theLoop;
			END IF;
			INSERT INTO t_pay_channel_statistic(pay_channel,order_num) VALUES(payChannel,orderNumOfChannel);
			COMMIT;
		END LOOP;
		CLOSE payChannelCusr;
		
		/*更新订单统计表*/
		INSERT INTO t_order_statistics (
					total_order,
					total_electricity,
					total_fee,
					total_sharp_electricity,
					total_peak_electricity,
					total_flat_electricity,
					total_valley_electricity,
					total_sharp_fee,
					total_peak_fee,
					total_flat_fee,
					total_valley_fee,
					total_order_today,
					total_electricity_today,
					total_fee_today,
					total_service_fee_today,
					total_order_currentMonth,
					total_electricity_currentMonth,
					total_fee_currentMonth,
					total_service_fee_currentMonth,
					total_order_currentYear,
					total_electricity_currentYear,
					total_fee_currentYear,
					total_service_fee_currentYear,
					tatal_charge_time_currentMonth,
					active_cust_num
					)
    VALUES (
					totalOrderNum,
					totalChargeElectricity,
					totalFee,
          totalSharpElectricity,
          totalPeakElectricity,
          totalFlatElectricity,
          totalValleyElectricity,
          totalSharpFee,
          totalPeakFee,
          totalFlatFee,
          totalValleyFee,
					totalOrderToday,
					totalChargeElectricityToday,
					totalFeeToday,
					totalServiceFeeToday,
					totalOrderCurrentMonth,
					totalChargeElectricityCurrentMonth,
					totalFeeCurrentMonth,
					totalServiceFeeCurrentMonth,
					totalOrderCurrentYear,
					totalChargeElectricityCurrentYear,
					totalFeeCurrentYear,
					totalServiceFeeCurrentYear,
					totalChargeTimeCurrentMonth,
					activeCustNum
					);
    IF resultCode = 0
    THEN
      ROLLBACK;
    ELSE
      COMMIT;
    END IF;
  END