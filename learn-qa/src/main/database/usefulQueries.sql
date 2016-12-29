


-- post
select * from post p where postId = 45;

select * from question;

select * from RefQuestionStatus;

-- answer query
select p.*, a.* from answer a
join post p on p.postId = a.answerId
join question q on q.questionId = a.questionId;


-- question query
select p.*, q.*, u.*, t.*
From question q
JOIN post p ON  q.questionId = p.postId
JOIN AppUser u ON  p.postedby = u.appuserId
JOIN questionTag qt ON  q.questionId = qt.questionId
JOIN Tag t on t.tagId = qt.tagId
where postId = 45;


-- posts waiting for approval

select * from Post where ApprovalStatus ;

-- query to return event regsitration by users


SELECT UserId FROM notificationsystem.eventregistration;

select * from notificationsystem.eventregistration;

Select eventId, EventTypeId,EventSourceId,LatestEventDate,ExpirationDate from notificationsystem.`event` ;

select *  from notificationsystem.`event` e
join notificationsystem.eventType et on et.eventTypeId = e.eventTypeId;

-- add new event types in eventtype

select * from notificationsystem.eventType;

select unp.* from notificationsystem.usernotificationpreference unp
join notificationsystem.notificationType nt on nt.NotificationTypeId = unp.NotificationTypeId
right join `learn-qa`.appuser au on au.appuserid = unp.UserID;

select 
uen.UserEventNotificationStatusId,
uen.NotificationTypeId, 
uen.eventId, 
uen.userId, 
uen.*,
e.EventTypeId, 
e.EventSourceId, 
e.LatestEventDate, 
e.ExpirationDate 
from UserEventNotification uen 
join Event e on e.eventId = uen.eventId ;

select * from notificationsystem.usereventstatus;




