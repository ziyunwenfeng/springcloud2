CREATE DEFINER=`root`@`%` PROCEDURE `p_customer_statistics`()
BEGIN
    DECLARE customerNumToday int(10);
    DECLARE customerNumYesterday int(10);
    DECLARE increment int(10);
    DECLARE groupNum int(10);
    DECLARE groupCustomerNum int(10);
    DECLARE groupConsumeAmount DECIMAL(18, 6);
    DECLARE resultCode int default 1;
    DECLARE continue handler for SQLEXCEPTION set resultCode = 0;
    START TRANSACTION;
    /*统计当前用户总数*/
    SELECT COUNT(*)INTO customerNumToday FROM t_customer;
    /*统计昨日用户总数*/
    SELECT COUNT(*)INTO customerNumYesterday
    FROM t_member_statistics
    WHERE DATEDIFF(CURRENT_TIMESTAMP(), batchId) >= 1;
    /*统计新增用户数*/
    set increment = customerNumToday - customerNumYesterday;
    /*统计集团，集团客户数，消费情况*/
    SELECT COUNT(*)INTO groupNum FROM t_group;
    SELECT COUNT(*)INTO groupCustomerNum FROM t_group_customer;
    SELECT SUM(wallet_amount) - SUM(wallet_balance) INTO groupConsumeAmount from t_group;

    insert into t_member_statistics (customer_num,
                                     yesterday_num,
                                     customer_increment,
                                     group_num,
                                     group_customer_num,
                                     group_consume_amount)
    values (customerNumToday, customerNumYesterday, increment, groupNum, groupCustomerNum, groupConsumeAmount);
    IF resultCode = 0
    THEN
      ROLLBACK;
    ELSE
      COMMIT;
    END IF;
  END