

import java.awt.Choice;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.goebuy.QrcodeApplication;
import com.goebuy.entity.QuestionEntity;
import com.goebuy.repository.QuestionRepository;

import junit.framework.TestCase;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=QrcodeApplication.class)
//@TestPropertySource(locations = {"classpath:application.properties"})
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableSwagger2
public class QrcodeApplicationTests extends TestCase {

	@Autowired
	QuestionRepository service;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
//		assertNotNull(biz);
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	@Before
	public void before() {
	}
	
	@After
	public void after() {
		System.out.println("after");
	}
	
	@Test
	public void testCount() {
		System.out.println(service.count());
	}
	
	@Test
	public void testSave() {
		QuestionEntity questionEntity = new QuestionEntity();
		questionEntity.setAns_desc("asc_desc");
		questionEntity.setAns_id("[1,2]");
		questionEntity.setChoices("{\"1\":\"Choice 1\", \"2\":\"Choice 2\",\"3\":\"Choice 3\" }");
		questionEntity.setDesc("desc");
		questionEntity.setId(1);
		questionEntity.setQuestion("question");
		service.save(questionEntity);
	}
	
	
	@Test
	public void contextLoads() {
		
	}

}
