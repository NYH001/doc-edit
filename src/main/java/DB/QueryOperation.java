package DB;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import datatype.Info;

public class QueryOperation {
	
	private List<Info> data = new ArrayList<Info>();

	public QueryOperation() {
		initData(null);
	}
	
	public QueryOperation(String input) {
		initData(input);
	}
	
	private void initData(String input) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("database.xml");
		JdbcTemplate jdbcTemplate = (JdbcTemplate) applicationContext.getBean("databaseInfo");
		String sql;
		java.util.List<Map<String, Object>> list;
		if(null == input){
			sql = "select * from info";
			list = jdbcTemplate.queryForList(sql);
		}else{
			sql = "select * from info where title like ? or author like ?";
			Object[] args = new Object[]{"%" + input + "%","%" + input + "%"};
			list = jdbcTemplate.queryForList(sql,args);
		}
		Iterator iterator = list.iterator();
		while ( iterator.hasNext() ) {
			Map userMap = (Map) iterator.next();
			Info tempInfo = new Info(userMap.get("title").toString(), userMap.get("author").toString(), (Integer)userMap.get("id"));
			data.add(tempInfo);
//			System.out.println("---here---");
		}
	}
	
	public List<Info> getData() {
		return data;
	}
}
