import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.goebuy.QrcodeApplication;
import com.goebuy.Question;
import com.goebuy.WriteDb;
import com.goebuy.entity.QuestionEntity;
import com.goebuy.repository.QuestionRepository;

import junit.framework.TestCase;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@ConfigurationProperties(prefix = "", value = "classpath:conf.properties")
@RunWith(SpringRunner.class)
@SpringBootTest(classes=QrcodeApplication.class)
//@TestPropertySource(locations = {"classpath:application.properties"})
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableSwagger2
public class QrcodeApplicationTests extends TestCase {

	@Autowired
	QuestionRepository service;
	
	@Autowired
	WriteDb writeDb;
	
	@Autowired  
    private Environment env;  
	
	List<Question> questionList = new ArrayList<>();
	
	@Override
	protected void setUp() throws Exception {
		System.out.println("setup");
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
	public void TestFile() {
		System.out.println("testFile");
		if(!new File("问茶108.txt").exists()) {
			throw new RuntimeException("file not found");
		}
		BufferedReader file;
		try {
			file = new BufferedReader(new FileReader("问茶108.txt"));
			String line =null;
			while( (line=file.readLine())!=null) {
				System.out.println(line);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Ignore
	@Test
	public void testCount() {
//		WriteDb writeDb = new WriteDb();
		writeDb.ReadFile();
		System.out.println(service.count());
	}
	
	@Ignore
	@Test
	public void TestList() {
		List<QuestionEntity> questionEntitiesList= service.findAll();
		List<Question> questionList = new ArrayList<Question>();

		for(QuestionEntity questionEntity: questionEntitiesList) {
			Question question = new Question(questionEntity.getId());
			question.setAns_desc( questionEntity.getAns_desc() );
			question.setUrl(questionEntity.getUrl());
			question.setQrcode(question.getQrcode());
			question.setQuestion(question.getQuestion());
			question.setDesc(question.getDesc());
			
//			String ans_id = questionEntity.getAns_id().substring(1,questionEntity.getAns_id().length()-1 );
////			String choices
////			Map<Integer, String> cmMap= (Map<Integer, String>) JSON.toJSON(questionEntity.getChoices());
//			for(String s: ans_id.split(",")) {
//				question.addAnsId(Integer.parseInt(s));
//			}
			@SuppressWarnings("unchecked")
			Set<Integer> ans_set = JSON.parseObject(questionEntity.getAns_id(), new TypeReference<TreeSet>() {} );
			@SuppressWarnings("unchecked")
			Map<Integer, String>   cmMap = JSON.parseObject(questionEntity.getChoices(),  new TypeReference<TreeMap>() {});
//			System.out.println(object);
//			System.out.println(object1);
			if(ans_set!=null) {
				question.setAns_id(ans_set);
			}
			if(cmMap!=null) {
				question.setChoices(cmMap);
			}
//			System.out.println(question);
			questionList.add(question);
		}
			
	}
	
	@Ignore
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
	public void testFind() {
		QuestionEntity questionEntity = service.findOne(1);
		Question question = questionEntity.convert2Question();
		System.out.println(question);
	}
	
	@Test
	public void testConf() {
		String host= env.getProperty("server.host");
		String port = env.getProperty("server.port"); //, "8080"
		System.out.println(host);
		System.out.println(port);
	}
	
	
	@Test
	public void contextLoads() {
		
	}

}
