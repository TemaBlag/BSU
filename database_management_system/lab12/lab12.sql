CREATE OR REPLACE PROCEDURE GET_CUST(
    cust_id NUMERIC,
    OUT company CHAR(20),
    OUT fio CHAR(20),
    OUT city CHAR(10)
)
AS $$
BEGIN
    SELECT c.company, s.fio, o.city
    INTO company, fio, city
    FROM customers c
    JOIN salespers s ON c.cust_rep = s.empl_num
    JOIN offices o ON c.cust_num = o.cust_num
    WHERE c.idcust = cust_id;
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE PROCEDURE GET_CUST1(cust_id NUMERIC)
AS $$
DECLARE
    v_company CHAR(20);
    v_fio CHAR(20);
    v_city CHAR(10);
BEGIN
    CALL GET_CUST(cust_id, v_company, v_fio, v_city);
	RAISE NOTICE 'Company: %', v_company;
    RAISE NOTICE 'FIO: %', v_fio;
    RAISE NOTICE 'City: %', v_city;
END;
$$ LANGUAGE plpgsql;


CALL GET_CUST1('1');

CREATE OR REPLACE PROCEDURE CHK_TOT(
	id_cust_num CHAR(10),
	OUT sum_salaes MONEY)
AS $$
BEGIN 
  UPDATE offices
  SET status = CASE
   WHEN (SELECT SUM(amount)
		 FROM customers
		 WHERE cust_num = id_cust_num) > CAST(30000 AS MONEY)
   THEN 'большой объем заказов'
   ELSE 'малый объем заказов'
   END
   WHERE cust_num = id_cust_num;
   SELECT SUM(amount)
       INTO sum_salaes
	   FROM customers
	   WHERE cust_num = id_cust_num;
END;
$$ LANGUAGE plpgsql;

CALL CHK_TOT('213', NULL);


CREATE OR REPLACE PROCEDURE add_cust(
	idoff_cust INTEGER,
    target_cust DOUBLE PRECISION,
    city_cust CHAR(10),
	cust_num_cust CHAR(10)
)
AS $$
DECLARE 
    sum_sales_cust MONEY;
BEGIN
    IF EXISTS (SELECT 1 FROM customers WHERE cust_num = cust_num_cust) THEN
        INSERT INTO offices
        VALUES (idoff_cust, target_cust, city_cust, cust_num_cust);
	END IF;
	CALL CHK_TOT(cust_num_cust, sum_sales_cust); 
	UPDATE salespers
	SET QUOTA = CASE 
	WHEN sum_sales_cust < 20000::MONEY
	  THEN QUOTA + target_cust
	WHEN sum_sales_cust = 20000::MONEY
	  THEN QUOTA + 20000
	END
	WHERE empl_num IN 
	    (SELECT cust_rep FROM customers WHERE cust_num = cust_num_cust);
END;
$$ LANGUAGE plpgsql;


CALL add_cust(5, 1, 'Минск', '211');


CREATE OR REPLACE FUNCTION update_customers()
RETURNS TRIGGER 
AS $$
BEGIN
    UPDATE customers
    SET cust_rep = NEW.empl_num
    WHERE cust_rep = OLD.empl_num;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER update_cust_rep_trigger
AFTER UPDATE OF empl_num ON salespers
FOR EACH ROW
EXECUTE FUNCTION update_customers();


CREATE OR REPLACE FUNCTION check_order_date()
RETURNS TRIGGER AS $$
BEGIN
    IF EXTRACT(DAY FROM NEW.data_order) > 15 THEN
        RAISE EXCEPTION 'Forbidden date value!';
    END IF;
    
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER order_date_trigger
BEFORE INSERT OR UPDATE ON customers
FOR EACH ROW
EXECUTE FUNCTION check_order_date();








































