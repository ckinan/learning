DELIMITER $$

USE `example_db`$$

DROP PROCEDURE IF EXISTS `sp_get_employees`$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_get_employees`(
	IN p_name VARCHAR(50)
    )
BEGIN
	SELECT * FROM employee
	WHERE NAME = p_name;
    END$$

DELIMITER ;