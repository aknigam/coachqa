SELECT
c.classroomid as classroomId,
c.classname as className,
c.classowner,
u.firstname,
u.middlename,
u.lastname,
description,
subjectId,
s.subjectname,
case when cm.membershipstartdate is null then false else TRUE end as isLoggedInUserEnroled
FROM classroom c
JOIN appuser u ON u.appuserid = c.classowner
JOIN refsubject s ON s.refsubjectid = c.subjectId
<#if myclassonly>
JOIN classroommember cm on cm.classroomid = c.classroomid and cm.appuserid = ${loginuserid}
<#else>
LEFT JOIN classroommember cm on cm.classroomid = c.classroomid and cm.appuserid = ${loginuserid}
</#if>
ORDER BY c.classroomid
LIMIT ${page}, 10