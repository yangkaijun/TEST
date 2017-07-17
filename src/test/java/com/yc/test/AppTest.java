package com.yc.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Random;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.yc.bean.Student;


public class AppTest extends TestCase { //TestCase测试用例 ： 可以有多个测试方法
  
    public AppTest( String testName ){
        super( testName );
    }

    public static Test suite(){  //测试套件
        return new TestSuite( AppTest.class );
    }
    
    static SqlSessionFactory  sessionFactory;
    SqlSession session=null;
    static {
    	
    	try {
    		Reader reader;
        	//加载mybatis的配置文件及其映射文件 ，返回一个流
			reader=Resources.getResourceAsReader("mybatisConfiguration.xml");
			//构建SqlSession的工厂   ： SqlSession--》相当于jdbc中的Connection
	    	sessionFactory=new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }
    //测试mybatis的数据库联接
    public void testApp1() throws IOException {  
    	//创建执行映射文件中的sql中的sqlSession
    	session=sessionFactory.openSession();
    	//junit的断言  ： 我断言session不为空  ，即我的配置文件可以连上数据库
        assertNotNull( session );   
    } 
    
    //测试 查询
    public void TestApp2() throws IOException{
    	//创建执行映射文件中的sql中的sqlSession
    	session=sessionFactory.openSession();
    	Student s=session.selectOne(Student.class.getName()+".selectStudent",1);
    	assertEquals( s.getSname(),"小明" );
    	System.out.println(s);
    }

    //测试 添加
    public void TestApp3() throws IOException{	
    //创建执行映射文件中的sql中的sqlSession
	session=sessionFactory.openSession();

	 Student s=new Student("小刘","网络2班");
	 int result=0;
	try {
		result = session.insert(Student.class.getName()+".insertStudent",s);
		session.commit();
	} catch (Exception e) {
		e.printStackTrace();
		session.rollback();
	}finally{
		session.close();
	}
  } 
    //修改
    public void TestApp4(){
      	
        //创建执行映射文件中的sql中的sqlSession
    	session=sessionFactory.openSession();
    	Student s=new Student(2,"网络2班","小李");
    	int result=0;
    	try {
			result=session.update(Student.class.getName()+".updateStudent",s);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		}finally{
			session.close();
		}	
    	 System.out.println(s);
    }
    
    //删除
    public void TestApp5(){
      	
       //创建执行映射文件中的sql中的sqlSession
       session=sessionFactory.openSession();	
      int sid=6;
      Student s=new Student(sid, "小赵");
	  int result=0;
      try {
		result=session.delete(Student.class.getName()+".deleteStudent",s);
		session.commit();
	} catch (Exception e) {
		e.printStackTrace();
		session.rollback();
	}finally{
		session.close();
	}
	 
  }
    //查所有学生
    public void TestApp6(){
    	//创建执行映射文件中的sql中的sqlSession
        session=sessionFactory.openSession();
        List<Student> list=session.selectList(Student.class.getName()+".selectAll");
    	System.out.println(list);	
    	
    }
}
