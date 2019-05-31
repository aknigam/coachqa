SELECT
c.classroomid as classroomId,
c.classname as className,
c.classowner,
u.firstname,
u.middlename,
u.lastname,
c.description,
subjectId,
s.subjectname,
case WHEN ( cm.membershipstartdate IS NULL and c.classowner = ${loginuserid})
THEN 2
WHEN cm.membershipstartdate IS NULL
THEN 0
ELSE cs.refclassroommemberstatusid END AS loggedInUserStatus
FROM classroom c
JOIN appuser u ON u.appuserid = c.classowner
JOIN refsubject s ON s.refsubjectid = c.subjectId
<#if myclassonly>
JOIN classroommember cm on cm.classroomid = c.classroomid and cm.appuserid = ${loginuserid}
JOIN refclassroommemberstatus cs on cs.refclassroommemberstatusid = cm.status and cs.refclassroommemberstatusid = 2
<#else>
LEFT JOIN classroommember cm on cm.classroomid = c.classroomid and cm.appuserid = ${loginuserid}
LEFT JOIN refclassroommemberstatus cs on cs.refclassroommemberstatusid = cm.status
</#if>
where c.accountId = ${accountId}
ORDER BY c.classroomid
LIMIT ${page}, 10