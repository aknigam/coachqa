USE `learn-qa`;

DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `AnswerAddSproc`(
	pQuestionId int,
	pAnsweredByUserId int,
	pContent text
)
BEGIN

DECLARE vPostId INT DEFAULT 0;
insert into post (postedBy, postType, Votes, Content, postdate ) values (pAnsweredByUserId, 0, 1, pContent, now());
SET vPostId = LAST_INSERT_ID();
	insert into Answer (answerId,QuestionId )
    values (vPostId, pQuestionId);

END ;;
DELIMITER ;

DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `classroomGetById`(
	pClassroomId  int
)
BEGIN

	select
    ClassroomId,
    MinReputationToJoinId,
    ClassOwner as PostedBy,
	u.Firstname,
	u.middleName,
	u.lastName,
    ClassName,
    IsPublic,
    LastUpdateDate
from
    classroom c
        join
    AppUser u ON u.appuserId = classowner
where
    c.classroomId = pClassroomId;


END ;;
DELIMITER ;

DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `classroomJoin`(
	pUserId int,
	pClassroomId int

)
BEGIN

END ;;
DELIMITER ;

DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `JoinClassroomSproc`(
	pUserId int,
	pClassroomId int
)
BEGIN

END ;;
DELIMITER ;

DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `questionAddSproc`(
pPostedBy int,
pTitle varchar(50),
pContent text,
pRefSubjectId int,
pClassroomId int,
pIsPublic tinyint
)
BEGIN
	DECLARE vQuestionId INT DEFAULT 0;

	insert into post ( postdate, PostedBy, Content, posttype, classroomId)
	values ( now(), pPostedBy, pContent,  1, pClassroomId);

    SET vQuestionId = LAST_INSERT_ID();

	insert into question (questionId, RefSubjectId, QuestionlevelId, refQuestionStatusId ,
     lastactivedate, Title, isPublic)
	values (vQuestionId,pRefSubjectId,1, 1,  now(), pTitle, pIsPublic );

	-- Select questionId into vQuestionId from question where title = pTitle;

	call questionGet(vQuestionId);

END ;;
DELIMITER ;

DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `QuestionGet`(
	pQuestionId int
)
BEGIN

	select
		questionId,
		RefSubjectId,
		QuestionLevelId,
		p.PostedBy,
        p.postType,
		u.Firstname,
		u.middleName,
		u.lastName,
		RefQuestionStatusId,
		Title,
		p.Content,
		p.NoOfViews as NoOfViews,
		p.PostDate,
		LastActiveDate,
		p.Votes,
    p.ClassroomId as ClassroomId,
		IsPublic
	from
		Question q
    join post p on p.postid = q.questionid
		join AppUser u on u.appuserId = p.postedby
	where
		questionId = pQuestionId;

	Select
		AnswerId, p.postedby as AnsweredByUserId, QuestionId, p.Votes, p.Content
	From
		Answer a
    join post p on p.postid = a.answerid
	where
		questionId = pQuestionId;

  select TagId from questionTag where QuestionId = pQuestionId;


END ;;
DELIMITER ;


DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `questionUpdateStats`(
	pQuestionId int,
	pVotes int
)
BEGIN

	DECLARE vNoOfViews, vVotes INT DEFAULT 0;

	Select noOfViews into vNoOfViews from question where questionId = pQuestionId;
	Select votes into vVotes from question where questionId = pQuestionId;


	Update Question
	set NoOfViews = vNoOfViews +1,
		LastActiveDate = now();

	if(pVotes != 0)
		then
			Update Question
			set votes = vVotes +pVotes;

	end If;

END ;;
DELIMITER ;


DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `userAddSproc`(
pEmail varchar(20),
pPasword varchar(20),
pFirstName varchar(20),
pMiddleName varchar(20),
pLastName varchar(20)
)
BEGIN

	INSERT INTO AppUser(Email,Pasword,FirstName,MiddleName,LastName)VALUES(pEmail,pPasword,pFirstName,pMiddleName,pLastName);

	Select AppUserId, UserReputationId, Email, Pasword, FirstName, MiddleName, LastName
	from AppUser where Email = pEmail;

END ;;
DELIMITER ;


DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `userGet`(
pAppUserId int,
pEmail varchar(20)
)
BEGIN

	if(pAppUserId is not null)
	then
		Select AppUserId, UserReputationId, Email, Pasword, FirstName, MiddleName, LastName
		from AppUser where AppUserId = pAppUserId;
	else
		Select AppUserId, UserReputationId, Email, Pasword, FirstName, MiddleName, LastName
		from AppUser where Email = pEmail;
	end if;


END ;;
DELIMITER ;


DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `userUpdate`(
pAppUserId int,
pUserReputationId int,
pEmail varchar(20)
)
BEGIN

	if(pUserReputationId is not null)
	then
		UPDATE AppUser SET UserReputationId = pUserReputationId where AppUserId = pAppUserId;
	end if;

	if(pEmail is not null)
	then
		UPDATE AppUser SET Email = pEmail,Pasword = pPasword where AppUserId = pAppUserId;
	end if;

	if(pPasword is not null)
	then
		UPDATE AppUser SET Pasword = pPasword where AppUserId = pAppUserId;
	end if;

	Select * from AppUser where Email = pEmail;

END ;;
DELIMITER ;