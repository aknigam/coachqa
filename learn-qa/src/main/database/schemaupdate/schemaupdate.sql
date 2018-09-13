use crajee;

ALTER TABLE appuser 
DROP COLUMN lastname,
DROP COLUMN middlename,
DROP COLUMN firstname,
ADD COLUMN name VARCHAR(45) NOT NULL AFTER usertypeid;


ALTER TABLE appuser 
DROP COLUMN name,
ADD COLUMN firstname VARCHAR(20) NOT NULL AFTER pasword,
ADD COLUMN middlename VARCHAR(20) NULL AFTER firstname,
ADD COLUMN lastname VARCHAR(20) NOT NULL AFTER middlename;
