package DB;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import datatype.Info;

public class ReadOperation {
	
	private int id;
	private String content;
	
	public ReadOperation(int id) {
		// TODO Auto-generated constructor stub
		setId(id);
		readFromDatabase();
	}
	
	private void setId(int id) {
		this.id = id;
	}
	
	private void readFromDatabase() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("database.xml");
		JdbcTemplate jdbcTemplate = (JdbcTemplate) applicationContext.getBean("databaseInfo");
		String sql = "select content from article where id = ?";
		List list = jdbcTemplate.queryForList(sql, id);
		Iterator iterator = list.iterator();
		while ( iterator.hasNext() ) {
			Map userMap = (Map) iterator.next();
			content = userMap.get("content").toString();
		}
//		System.out.println(content);
	}
	
	public String getContent() {
		return content;
	}
}
