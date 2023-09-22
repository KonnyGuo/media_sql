/*
drop tables
*/
drop table if exists viewTopics;
drop table if exists topics;
drop table if exists viewUsers;
drop table if exists Interaction;
drop table if exists User;

/*
The table User has 4 attributes
user_id as the primary key
user_age to identify the age of the user
user_gender to identify the gender of the user
user_username to identify the name of said user on the account
*/
create table User
  (
   user_id INTEGER NOT NULL,
   user_age INTEGER NOT NULL,
   user_gender varchar(20),
   user_userName varchar(20),
   primary key(user_id)
 );

/*
The table Interaction has 2 attributes
interact_id was added on as the primary key as a way to keep track of when a User would start an Interaction
tstamp1 is use to keep a timestamp of when the Interaction happens because
the User can initates an Interaction
*/
create table Interaction
  (
    user_id INTEGER NOT NULL,
    startTime1 TIMESTAMP,
    endTime1 TIMESTAMP,
    primary key(user_id,startTime1)
  );


/*
The relation viewUsers would go back to User
it takes the user_id and otherUser_id as primary keys
foreign key user_id in initiates references the user_id in User
foreign key otherUser_id in initates references interact_id in Interaction
*/
create table viewUsers
  (
    user_id INTEGER NOT NULL,
    otherUser_id INTEGER NOT NULL,
    primary key(user_id, otherUser_id),
    foreign key(user_id) references User(user_id),
    foreign key(otherUser_id) references User(user_id)
  );

/*
The table topics has 3 attributess
topic_id as the primary key to identify unique post
topic_section to describe the area of the topic like sports, college, etc
dateAccessed to describe when the user took a look at the topic
*/
create table topics
  (
    topic_id INTEGER NOT NULL,
    topic_section varchar(20),
    media varchar(20),
    primary key (topic_id)
  );

/*
The relation viewTopics was originally named view in the ER diagram
it takes the user_id and topic_id as primary keys
It keeps track of a timestamp for the length that the user is viewing the topic
foreign key user_id in viewTopics references the user_id in User
foreign key topic_id in viewTopics references topic_id in topics
*/

create table viewTopics
  (
    user_id INTEGER NOT NULL,
    topic_id INTEGER NOT NULL,
    startTime TIMESTAMP,
    endTime TIMESTAMP,
    primary key(user_id, topic_id),
    foreign key(user_id) references User(user_id),
    foreign key(topic_id) references topics(topic_id)
  );

/*
The table video is a child of topic
It has topic_id as the primary keys
It has video_length as an attribute to describe the length of video that User would watch
foreign key topic_id in video references topic_id in topics
*/
-- create table video
--   (
--     topic_id INTEGER NOT NULL,
--     video_length INTEGER NOT NULL,
--     primary key(topic_id),
--     foreign key(topic_id) references topics(topic_id)
--   );

/*
Insert into User: user_id,user_age,user_gender,user_name
*/
insert into User values (000000,10,'male','User000000');
insert into User values (000001,20,'female','User000001');
insert into User values (000002,30,'male','User000002');
insert into User values (000003,40,'female','User000003');
insert into User values (000004,50,'male','User000004');
insert into User values (000005,20,'female','User000005');

/*
Insert into Interaction: interact_id, timestamp
*/
insert into Interaction values (000000,"2020-01-01 01:00:00", "2020-01-01 02:00:00");
insert into Interaction values (000001,"2020-01-02 01:00:00", "2020-01-02 03:00:00");
insert into Interaction values (000002,"2020-01-03 01:00:00", "2020-01-03 04:00:00");
insert into Interaction values (000003,"2020-01-04 01:00:00", "2020-01-04 05:00:00");
insert into Interaction values (000004,"2020-01-05 01:00:00", "2020-01-05 06:00:00");
insert into Interaction values (000005,"2020-01-06 01:00:00", "2020-01-06 07:00:00");
insert into Interaction values (000005,"2020-02-06 02:00:00", "2020-02-06 07:00:00");




/*
Insert into viewUsers user_id, user_id(the second user_id would represent another user_id)
*/

insert into viewUsers values(000000, 000001);
insert into viewUsers values(000001, 000002);
insert into viewUsers values(000002, 000003);
insert into viewUsers values(000003, 000004);
insert into viewUsers values(000004, 000005);
insert into viewUsers values(000005, 000000);

/*
Insert into topics: topic_id, topic_section, date
*/

insert into topics values(222221, 'Sports', 'Video');
insert into topics values(222121, 'Sports', 'Video');
insert into topics values(222122, 'Sports', 'Photo');
insert into topics values(222123, 'Sports', 'Video');
insert into topics values(222222, 'Fashion', 'Photo');
insert into topics values(222311, 'Fashion', 'Photo');
insert into topics values(222223, 'Shows', 'Video');
insert into topics values(222224, 'Housing', 'Photo');
insert into topics values(222225, 'Environment', 'Photo');
insert into topics values(222226, 'College', 'Video');

/*
Insert into viewTopics: user_id, topic_id, timestamp
*/

insert into viewTopics values(000000, 222221, '2020-01-01 01:00:00', '2020-01-01 01:10:00');
insert into viewTopics values(000000, 222121, '2020-01-01 01:00:00','2020-01-01 01:20:00');
insert into viewTopics values(000000, 222122, '2020-01-01 01:00:00','2020-01-01 01:30:00');
insert into viewTopics values(000000, 222123, '2020-01-01 01:00:00','2020-01-01 01:40:00');
insert into viewTopics values(000000, 222222, '2020-01-01 01:00:00','2020-01-01 01:50:00');
insert into viewTopics values(000001, 222222, '2020-01-02 02:00:00','2020-01-02 02:00:00');
insert into viewTopics values(000002, 222223, '2020-01-03 03:00:00','2020-01-03 02:10:00');
insert into viewTopics values(000003, 222224, '2020-01-04 04:00:00','2020-01-04 02:20:00');
insert into viewTopics values(000004, 222225, '2020-01-05 05:00:00','2020-01-05 02:30:00');
insert into viewTopics values(000005, 222226, '2020-01-06 06:00:00','2020-01-06 02:40:00');
