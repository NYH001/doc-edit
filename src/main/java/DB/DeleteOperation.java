package DB;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class DeleteOperation {
	
	public DeleteOperation(int id) {
		deleteFromDatabase(id);
	}
	
	private void deleteFromDatabase(int id) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("database.xml");
		JdbcTemplate jdbcTemplate = (JdbcTemplate) applicationContext.getBean("databaseInfo");
		String sqlDeleteInfo = "delete from info where id = ?";
		String sqlDeleteArticle = "delete from article where id = ?";
		IndexQueue.index.add(id);
		String sqlInsertIdRemain = "insert into idRemain (id) values (?)";
		jdbcTemplate.update(sqlDeleteInfo, id);
		jdbcTemplate.update(sqlDeleteArticle, id);
		jdbcTemplate.update(sqlInsertIdRemain, new Object[]{id});
		System.out.println("---deleted---");
	}
}
