SELECT
classroommemberid as membershipId,
c.classname,
u.appuserid as memberId,
u.email,
u.firstname,
u.lastname,
u.middlename,
cm.classroomid as classroomId,
status  as membershipStatus,
membershipstartdate  as startDate,
membershipexpirartiondate as expirationDate,
membershiprequestdate  as membershipRequestDate,
comments as requestComments
FROM crajee.classroommember cm
JOIN classroom c on c.classroomid = cm.classroomid
JOIN appuser u on u.appuserid =  cm.appuserid
and c.ClassOwner = ${ownerid}
<#if classroomid??>
where cm.classroomid = ${classroomid}
and status = ${status}
<#else >
where status = ${status}
</#if>
order by membershipRequestDate, memberId desc limit ${page}, 5