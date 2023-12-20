DROP TABLE IF EXISTS MemberLab01; 

CREATE TABLE MemberLab01(
	id		   		INT NOT NULL AUTO_INCREMENT,
	memberId   		varchar(32) NOT NULL,
	password   		varchar(32) NOT NULL,
	name       		varchar(50) NOT NULL,
	phone 			varchar(20) NULL,
	birthday 		date NULL,
	registerdate 	datetime NOT NULL,
	picture 		longblob NULL,
	weight 			numeric NULL,
	PRIMARY KEY(id)
);

INSERT INTO MemberLab01 VALUES 
(NULL, 'kitty', 'k123', '凱蒂貓_mysql', '0919-852741', '1985-5-7', now(), NULL, 50.0), 
(NULL, 'mickey', 'm456', '米小薯_mysql', '0959-174885', '1992-12-22', now(), NULL, 46.4),  
(NULL, 'snoopy', 's789', '史努比_mysql', '0939-617501', '1997-8-14', now(), NULL, 55.6);
