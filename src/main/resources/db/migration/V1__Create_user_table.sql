create table USER
(
   	ID int AUTO_INCREMENT PRIMARY KEY NOT NULL,
   	NAME VARCHAR(100),
   	ACCOUNT_ID VARCHAR(100),
   	TOKEN VARCHAR(100),
   	GMT_CREATE BIGINT,
   	GMT_MODIFIED BIGINT
);