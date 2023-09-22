/*
return user_id where user_age is greater than 20 and less than 40
Help the companies find user_id by narrowing their age to a specific range
Useful for companies to learn about users that are between 20 and 40 if "COUNT" was used
*/
1.
Select U.user_id
From User U
Where U.user_age > 20 AND U.user_age < 40;

/*
return topic_section view by users older than 20.
Help the companies learn about topics that are popular for users older than 20
Useful for companies to create a new feature/product base on user interest
*/
2.
Select T.topic_section
From User U, viewTopics VT, topics T
Where U.user_age > 20 AND (U.user_id = VT.user_id) AND (T.topic_id = VT.topic_id);

/*
return user_id from users who looked at topic section Sports and Fashion
Help the companies find users that are interested in Sports or Fashion
Useful for creating specific ads targetting users interested in those topics
*/
3.
Select DISTINCT(U.user_id)
From User U, viewTopics VT, topics T
Where (U.user_id = VT.user_id) AND (T.topic_id = VT.topic_id) AND (T.topic_section = "Sports" OR T.topic_section = "Fashion");

/*
return amount of time users interacted with the app (aka opened the app)
Help the companies learn about the amount of times their app is open in total by all users.
Useful for keeping track of average daily uses and how it changes over the course of some period.
*/
4.
Select COUNT((I.User_id))
From User U, Interaction I
Where (U.user_id = I.user_id);

/*
Return topics that are viewed less than 2 times by users
Help the companies identify what topics are not popular with Users
Useful for stirring the company away from these topics and explore the more searched ones
Sports would not show up on this query
*/
5.
Select DISTINCT(T.topic_section)
FROM User U, topics T, viewTopics VT
WHERE (U.user_id = VT.user_id) AND (T.topic_id = VT.topic_id)
GROUP BY T.topic_section
HAVING COUNT(DISTINCT(T.topic_id)) < 2;

/*
return User_id of those who views Sports and also views Fashion
Help the companies find users who enjoy both topics
Useful for creating a specific ad like a sports athelete is selling some Fashion stuff
*/
6.
Select U.user_id
From User U, viewTopics VT, topics T
Where (U.user_id = VT.user_id) AND (T.topic_id = VT.topic_id) AND (T.topic_section = "Sports")
INTERSECT
Select U.user_id
From User U, viewTopics VT, topics T
Where (U.user_id = VT.user_id) AND (T.topic_id = VT.topic_id) AND (T.topic_section = "Fashion");
