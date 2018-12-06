SELECT
classroommemberid as membershipId,
appuserid as memberId,
cm.classroomid as classroomId,
status  as membershipStatus,
membershipstartdate  as startDate,
membershipexpirartiondate as expirationDate,
membershiprequestdate  as membershipRequestDate,
comments as requestComments
FROM crajee.classroommember cm
JOIN classroom c on c.classroomid = cm.classroomid
and c.ClassOwner = ${ownerid}
<#if classroomid??>
where cm.classroomid = ${classroomid}
and status = ${status}
<#else >
where status = ${status}
</#if>