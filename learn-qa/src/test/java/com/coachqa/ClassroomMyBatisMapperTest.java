package com.coachqa;

import com.coachqa.entity.Classroom;
import com.coachqa.repository.dao.mybatis.mapper.ClassroomMyBatisMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class ClassroomMyBatisMapperTest {

    @Test
    public void testGetClassrooms() throws IOException {

        SqlSessionFactory sf = getSessionFactory();
        sf.getConfiguration().addMapper(ClassroomMyBatisMapper.class);

        ClassroomMyBatisMapper m = sf.openSession().getMapper(ClassroomMyBatisMapper.class);
        List<Classroom> c = m.searchClassrooms(0, 2, false, 100);

        System.out.println(c.size());



    }

    private SqlSessionFactory getSessionFactory() throws IOException {

        String res = "mybatis-config.xml";
        Reader reader = Resources.getResourceAsReader(res);
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(reader);
        return factory;
    }
}
