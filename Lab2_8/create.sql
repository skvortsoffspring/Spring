-- create database PROTECTED_COURSES;
use PROTECTED_COURSES;

CREATE TABLE `USER` (
    `ID` BIGINT unsigned NOT NULL AUTO_INCREMENT,
    `LOGIN` VARCHAR(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
    `EMAIL` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
    `PASSWORD` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
    `PHONE` VARCHAR(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
    `LANGUAGE` VARCHAR(2) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
    `STATUS` VARCHAR(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci  DEFAULT 'ACTIVE',
    `ROLE` VARCHAR(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci  DEFAULT 'USER',
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
    `KEY` VARCHAR(25) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL,
    `ACTIVE` BOOL NULL ,
    `DATE ACTIVATED` DATE NULL ,
    `HARDWARE TYPE` VARCHAR(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL ,
    `HARDWARE SERIAL` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL,
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

-- page in 8 items
create or replace
    definer = root@localhost procedure FINDEIGHTCOURSES(IN page int)
begin
    select ID, NAME, CATEGORY, DESCRIPTION, IMAGE, PRICE, HIDE, AUTHOR, COMPLEXITY from (select ID, NAME, CATEGORY, DESCRIPTION, IMAGE, PRICE, HIDE, AUTHOR, COMPLEXITY, row_number() over(order by course.ID) rn from course
                                                                                        ) as Nr  where rn  between  (page * 8 + 1) and (page * 8 + 8);
end;

-- page in 8 items by category
create or replace
    definer = root@localhost procedure FindEightCoursesByCategory(IN page bigint, IN p_category bigint)
begin
    select ID, NAME, CATEGORY, DESCRIPTION, IMAGE, PRICE, HIDE, AUTHOR, COMPLEXITY from (select ID, NAME, CATEGORY, DESCRIPTION, IMAGE, PRICE, HIDE, AUTHOR, COMPLEXITY, row_number() over(order by course.ID) rn from course
                                                                                        ) as Nr  where rn  between  (page * 8 + 1) and (page * 8 + 8) and CATEGORY = p_category;
end;
