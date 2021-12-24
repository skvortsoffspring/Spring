-- create database PROTECTED_COURSES;
use PROTECTED_COURSES;

CREATE TABLE `USER` (
    `ID` BIGINT unsigned NOT NULL AUTO_INCREMENT,
    `LOGIN` VARCHAR(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
    `EMAIL` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
    `PASSWORD` VARCHAR(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
    `PHONE` VARCHAR(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
    `LANGUAGE` VARCHAR(2) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
    `ROLE` INT unsigned,
     KEY `IX_USER_EMAIL` (`EMAIL`) USING BTREE,
     PRIMARY KEY (`ID`)
);

CREATE TABLE `COURSE` (
    `ID` BIGINT unsigned NOT NULL AUTO_INCREMENT,
    `NAME` VARCHAR(100)  ,
    `CATEGORY` BIGINT unsigned,
    `DESCRIPTION` VARCHAR(1000) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
    `IMAGE` MEDIUMBLOB,
    `PRICE` DECIMAL(10, 2),
    `HIDE` BOOL,
    `AUTHOR` BIGINT unsigned,
    `COMPLEXITY` INT,
     KEY `IX_COURSE_NAME` (`NAME`) USING BTREE,
     PRIMARY KEY (`ID`)
);

CREATE TABLE `CATEGORY` (
    `ID` BIGINT unsigned NOT NULL AUTO_INCREMENT,
    `NAME` VARCHAR(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
    `IMAGE` BLOB,
     KEY `IX_CATEGORY_NAME` (`NAME`) USING BTREE ,
     PRIMARY KEY (`ID`)
);

CREATE TABLE `PURCHASED` (
    `ID` BIGINT unsigned NOT NULL AUTO_INCREMENT,
    `USER` BIGINT unsigned,
    `COURSE` BIGINT unsigned,
    `KEY` VARCHAR(25) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
    `ACTIVE` BOOL,
    `DATE ACTIVATED` DATE,
    `HARDWARE TYPE` VARCHAR(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
    `HARDWARE SERIAL` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
    PRIMARY KEY (`ID`)
    );

/*
drop table  `PURCHASED`;
drop table  `COURSE`;
drop table  `CATEGORY`;
drop table  `USER`;
*/
alter table COURSE add foreign key (`AUTHOR`) references `USER` (`ID`);
alter table PURCHASED add foreign key (`USER`) references `USER` (`ID`);

alter table PURCHASED add foreign key (`COURSE`) references `COURSE` (`ID`);
alter table COURSE add foreign key (`CATEGORY`) references `CATEGORY` (`ID`);