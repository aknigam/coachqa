select
q.questionid,
s.refsubjectid,
questionlevelid,
p.postedby,
p.posttype,
u.firstname,
u.middlename,
u.lastName,
refquestionstatusid,
title,
p.content,
p.noofviews,
p.postdate,
lastactivedate,
p.votes,
p.classroomid,
p.approvalstatus,
c.ClassName,
c.classowner,
s.subjectname
 from question q
 join post p on p.postid = q.questionid
 join appuser u on u.appuserId = p.postedby
 JOIN classroom c ON  c.classroomid = p.classroomid
 JOIN refsubject s ON  q.refsubjectid = s.refsubjectid
<#if requestType == 1 >
 join favoritepost f on f.questionid = q.questionid
 where f.userid = ${appUserId}
</#if>
<#if requestType == 2>
 where p.postedby = ${appUserId}
</#if>
<#if requestType == 3>
 where questionid = ${questionId}
</#if>
 order by p.postdate desc limit ${page}, 5
