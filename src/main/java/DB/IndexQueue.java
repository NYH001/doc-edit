package DB;

import java.awt.List;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import javax.sound.sampled.LineListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class IndexQueue {
	
	public static Queue<Integer> index = new LinkedList<Integer>();
	
	public IndexQueue() {
		// TODO Auto-generated constructor stub
		initQueue();
	}
	
	private void initQueue() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("database.xml");
		JdbcTemplate jdbcTemplate = (JdbcTemplate) applicationContext.getBean("databaseInfo");
		String sql = "select id from idRemain";
		java.util.List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
//		System.out.println(list.size());
		Iterator iterator = list.iterator();
		while ( iterator.hasNext()) {
			Map userMap = (Map) iterator.next();
//			System.out.println(userMap.get("id"));
			index.add((Integer)userMap.get("id"));
		}
//		System.out.print(index);
	}
	
}
