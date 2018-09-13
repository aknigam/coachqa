package com.coachqa;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by a.nigam on 28/01/17.
 */

@Mapper
public interface UserBookmarkMapper {


    @Insert("INSERT INTO UserBookmark(UserBookmarkId,Url,FolderId)VALUES(    <{UserBookmarkId: }>,<{Url: }>,<{FolderId: }>);")
    void insertUserBookmark(UserBookmark bookmark);

}
