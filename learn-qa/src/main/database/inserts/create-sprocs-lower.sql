use `crajee`;

delimiter ;;
create definer=`root`@`localhost` procedure `answeraddsproc`(
 pquestionid int,
 pansweredbyuserid int,
 pcontent text
)
begin

declare vpostid int default 0;
insert into post (postedby, posttype, votes, content, postdate ) values (pansweredbyuserid, 0, 1, pcontent, now());
set vpostid = last_insert_id();
 insert into answer (answerid,questionid )
    values (vpostid, pquestionid);

end ;;
delimiter ;

delimiter ;;
create definer=`root`@`localhost` procedure `classroomgetbyid`(
 pclassroomid  int
)
begin

 select
    classroomid,
    minreputationtojoinid,
    classowner as postedby,
 u.firstname,
 u.middlename,
 u.lastname,
    classname,
    ispublic,
    lastupdatedate
from
    classroom c
        join
    appuser u on u.appuserid = classowner
where
    c.classroomid = pclassroomid;


end ;;
delimiter ;

delimiter ;;
create definer=`root`@`localhost` procedure `classroomjoin`(
 puserid int,
 pclassroomid int

)
begin

end ;;
delimiter ;

delimiter ;;
create definer=`root`@`localhost` procedure `joinclassroomsproc`(
 puserid int,
 pclassroomid int
)
begin

end ;;
delimiter ;

delimiter ;;
create definer=`root`@`localhost` procedure `questionaddsproc`(
ppostedby int,
ptitle varchar(50),
pcontent text,
prefsubjectid int,
pclassroomid int,
pispublic tinyint
)
begin
 declare vquestionid int default 0;

 insert into post ( postdate, postedby, content, posttype, classroomid)
 values ( now(), ppostedby, pcontent,  1, pclassroomid);

    set vquestionid = last_insert_id();

 insert into question (questionid, refsubjectid, questionlevelid, refquestionstatusid ,
     lastactivedate, title, ispublic)
 values (vquestionid,prefsubjectid,1, 1,  now(), ptitle, pispublic );

 -- select questionid into vquestionid from question where title = ptitle;

 call questionget(vquestionid);

end ;;
delimiter ;

delimiter ;;
create definer=`root`@`localhost` procedure `questionget`(
 pquestionid int
)
begin

 select
  questionid,
  refsubjectid,
  questionlevelid,
  p.postedby,
        p.posttype,
  u.firstname,
  u.middlename,
  u.lastname,
  refquestionstatusid,
  title,
  p.content,
  p.noofviews as noofviews,
  p.postdate,
  lastactivedate,
  p.votes,
    p.classroomid as classroomid,
  ispublic
 from
  question q
    join post p on p.postid = q.questionid
  join appuser u on u.appuserid = p.postedby
 where
  questionid = pquestionid;

 select
  answerid, p.postedby as answeredbyuserid, questionid, p.votes, p.content
 from
  answer a
    join post p on p.postid = a.answerid
 where
  questionid = pquestionid;

  select tagid from questiontag where questionid = pquestionid;


end ;;
delimiter ;


delimiter ;;
create definer=`root`@`localhost` procedure `questionupdatestats`(
 pquestionid int,
 pvotes int
)
begin

 declare vnoofviews, vvotes int default 0;

 select noofviews into vnoofviews from question where questionid = pquestionid;
 select votes into vvotes from question where questionid = pquestionid;


 update question
 set noofviews = vnoofviews +1,
  lastactivedate = now();

 if(pvotes != 0)
  then
   update question
   set votes = vvotes +pvotes;

 end if;

end ;;
delimiter ;


delimiter ;;
create definer=`root`@`localhost` procedure `useraddsproc`(
pemail varchar(20),
ppasword varchar(20),
pfirstname varchar(20),
pmiddlename varchar(20),
plastname varchar(20)
)
begin

 insert into appuser(email,pasword,firstname,middlename,lastname)values(pemail,ppasword,pfirstname,pmiddlename,plastname);

 select appuserid, userreputationid, email, pasword, firstname, middlename, lastname
 from appuser where email = pemail;

end ;;
delimiter ;


delimiter ;;
create definer=`root`@`localhost` procedure `userget`(
pappuserid int,
pemail varchar(20)
)
begin

 if(pappuserid is not null)
 then
  select appuserid, userreputationid, email, pasword, firstname, middlename, lastname
  from appuser where appuserid = pappuserid;
 else
  select appuserid, userreputationid, email, pasword, firstname, middlename, lastname
  from appuser where email = pemail;
 end if;


end ;;
delimiter ;


delimiter ;;
create definer=`root`@`localhost` procedure `userupdate`(
pappuserid int,
puserreputationid int,
pemail varchar(20)
)
begin

 if(puserreputationid is not null)
 then
  update appuser set userreputationid = puserreputationid where appuserid = pappuserid;
 end if;

 if(pemail is not null)
 then
  update appuser set email = pemail,pasword = ppasword where appuserid = pappuserid;
 end if;

 if(ppasword is not null)
 then
  update appuser set pasword = ppasword where appuserid = pappuserid;
 end if;

 select * from appuser where email = pemail;

end ;;
delimiter ;

