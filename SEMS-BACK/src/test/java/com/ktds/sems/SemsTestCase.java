package com.ktds.sems;

import org.junit.runner.RunWith;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
		"file:src/main/webapp/WEB-INF/spring/applicationContext.xml"
		, "file:src/main/webapp/WEB-INF/spring/educationContext.xml"
		, "file:src/main/webapp/WEB-INF/spring/memberContext.xml"
		, "file:src/main/webapp/WEB-INF/spring/fileContext.xml"
		, "file:src/main/webapp/WEB-INF/spring/teacherContext.xml"
		, "file:src/main/webapp/WEB-INF/spring/cooperationContext.xml"
		, "/rootContext.xml"})
@PropertySource("file:src/main/webapp/WEB-INF/spring/db.properties")
public class SemsTestCase {

}