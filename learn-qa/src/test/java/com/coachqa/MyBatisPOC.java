package com.coachqa;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

/**
 * Created by a.nigam on 30/01/17.
 */
public class MyBatisPOC {


    public BasicDataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/learn-qa");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }


    public SqlSessionFactory sqlSessionFactory(){

        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        org.apache.ibatis.mapping.Environment myBatisEnvironment =
                new org.apache.ibatis.mapping.Environment("dev", transactionFactory, dataSource());
        org.apache.ibatis.session.Configuration mybatisConfiguration = new org.apache.ibatis.session.Configuration(myBatisEnvironment);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(mybatisConfiguration);
        mybatisConfiguration.addMappers("com.smartbookmark.repository.mybatis.mapper");
        return sessionFactory;
    }

    public static void main(String[] args) {
        MyBatisPOC myBatisPOC = new MyBatisPOC();
        SqlSessionFactory sessionFactory = myBatisPOC.sqlSessionFactory();

        try(
                SqlSession session = sessionFactory.openSession();
        ) {
            UserBookmarkMapper mapper = session.getMapper(UserBookmarkMapper.class);

            UserBookmark bookmark = null;
            mapper.insertUserBookmark(bookmark);
        }


    }

}
