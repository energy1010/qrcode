package com.goebuy.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.goebuy.Question;
import com.goebuy.entity.QuestionEntity;
import com.goebuy.repository.QuestionRepository;

import antlr.StringUtils;
import io.swagger.annotations.ApiOperation;

/**
 * 
 */
@Controller
@RequestMapping(value = "question")
public class QrController {
	
	@Autowired
	QuestionRepository service;
//	@ApiOperation(value="get method", notes="生成二维码")
//	@RequestMapping(value = "/", method = RequestMethod.GET)
//    public void get(@RequestParam(name = "w",defaultValue = "200",required = false) int width,
//                    @RequestParam(name = "h",defaultValue = "200",required = false) int height,
//                    @RequestParam(name = "f",defaultValue = "png",required = false) String format,
//                    @RequestParam(name = "c",defaultValue = "content") String content,
//                    HttpServletResponse response) throws Exception {
//        ServletOutputStream out = response.getOutputStream();
//        Map<EncodeHintType,Object> config = new HashMap<>();
//        config.put(EncodeHintType.CHARACTER_SET,"UTF-8");
//        config.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
//        config.put(EncodeHintType.MARGIN, 0);
//        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE,width,height,config);
//        MatrixToImageWriter.writeToStream(bitMatrix,format,out);
//        System.out.println("二维码生成完毕，已经输出到页面中。");
//    }
//	
	
	
//	@ApiOperation(value="get question", notes="扫描二维码之后跳转到对应question")
//	@ApiImplicitParam(name = "id", value = "question ID", paramType = "path", required = true, dataType = "Integer")
//	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
//	@ResponseBody
//    public ResponseEntity<Object> get( @PathVariable int id) {
//        System.out.println("get id: "+ id);
//        Question question = new Question();
//        question.addChoices(1, "choice 1");
//        question.addChoices(2, "choice 2");
//        question.addChoices(3, "choice 3");
//        question.addChoices(4, "choice 4");
//        question.setQuestion("this is question");
//        question.addAnsId(2);
//        question.setAns_desc("ans_desc");
//        question.setDesc("desc");
//        JSONObject js = new JSONObject();
//		js.put("data", question);
//		js.put("code", 200);
//		js.put("msg", "OK");
//		return ResponseEntity.status(HttpStatus.OK).body(js);
//    }
		
	/**
	 * Spring Boot+Thymeleaf
	 * @param id
	 * @param model
	 * @return
	 */
//	@ApiOperation(value="get question", notes="扫描二维码之后跳转到对应question")
//	@ApiImplicitParam(name = "id", value = "question ID", paramType = "path", required = true, dataType = "Integer")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String get( @PathVariable int id, ModelMap model) {
        System.out.println("get id: "+ id);
        Question question = null;
        QuestionEntity questionEntity = service.findOne(id);
        if(questionEntity!=null) {
        	 question = questionEntity.convert2Question();
        }
//        question.addChoices(1, "choice 1");
//        question.addChoices(2, "choice 2");
//        question.addChoices(3, "choice 3");
//        question.addChoices(4, "choice 4");
//        question.setQuestion("this is question");
//        question.addAnsId(2);
//        question.setAns_desc("ans_desc");
//        question.setDesc("desc");
        model.addAttribute("question", question);
        return "question/detail";
    }
	
	private String getAnsStr(Set<Integer> s) {
		StringBuffer sBuffer = new StringBuffer();
		for(Integer s1: s) {
			sBuffer.append(s1);
		}
		return sBuffer.toString();
	}
	
	private String getAnsStr_new(Set<Integer> s) {
		StringBuffer sBuffer = new StringBuffer();
		for(Integer s1: s) {
				if(s1==1) {
					sBuffer.append('A');
				}else if(s1==2) {
					sBuffer.append('B');
				}else if(s1==3) {
					sBuffer.append('C');
				}else if(s1==4) {
					sBuffer.append('D');
				}
		}
		return sBuffer.toString();
	}
	
	@RequestMapping(value = "/{qid}/check", method = RequestMethod.GET)
	@ResponseBody
    public ResponseEntity<Object>  check( @PathVariable int qid, @RequestParam int select) {
        System.out.println("check id: "+ qid);
        Question question = null;
        QuestionEntity questionEntity = service.findOne(qid);
        if(questionEntity!=null) {
        	 question = questionEntity.convert2Question();
        }
        boolean flag = false;
        int cnt =0;
        if(question.getAns_id().contains(select)) {
        	flag = true;
        }
        cnt = question.getAns_id().size();
        JSONObject js = new JSONObject();
        js.put("code", 200);
        js.put("msg", "OK");
        js.put("qid", qid);
		js.put("flag", flag);
		js.put("cnt", cnt);
		js.put("ans", getAnsStr( question.getAns_id() ) );
		js.put("ans_new", getAnsStr_new(question.getAns_id() ) );
        return ResponseEntity.status(HttpStatus.OK).body(js);
    }
	
//	https://blog.csdn.net/u012706811/article/details/52185345
	
//	@ApiOperation(value="list question", notes="list question")
	 @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String  list(ModelMap model) {
		List<QuestionEntity> questionEntitiesList= service.findAll();
		List<Question> questionList = new ArrayList<Question>();

		for(QuestionEntity questionEntity: questionEntitiesList) {
			Question question = new Question(questionEntity.getId());
			question.setAns_desc( questionEntity.getAns_desc() );
			question.setUrl(questionEntity.getUrl());
			question.setQrcode(questionEntity.getQrcode());
			question.setQuestion(questionEntity.getQuestion());
			question.setDesc(questionEntity.getDesc());
			
			String ans_id = questionEntity.getAns_id().substring(1,questionEntity.getAns_id().length()-1 );
			Set<Integer> ans_set = JSON.parseObject(questionEntity.getAns_id(), new TypeReference<TreeSet>() {} );
			Map<Integer, String>   cmMap = JSON.parseObject(questionEntity.getChoices(),  new TypeReference<TreeMap>() {});
			if(cmMap!=null) {
				question.setChoices(cmMap);
			}
			if(ans_set!=null) {
				question.setAns_id(ans_set);
			}
			questionList.add(question);
			
		}
//        for (int i = 0; i <10; i++) {
//        	Question question = new Question(i);
//            question.addChoices(1, "choice 1");
//            question.addChoices(2, "choice 2");
//            question.addChoices(3, "choice 3");
//            question.addChoices(4, "choice 4");
//            question.setQuestion("this is question"+i);
//            question.addAnsId(2);
//            question.setAns_desc("ans_desc");
//            question.setDesc("desc");
//        	questionList.add(question);
//        }
//        
        model.addAttribute("questions", questionList);
        return "question/list";
    }
	
	@ApiOperation(value="add question", notes="add question")
	 @RequestMapping(value="/add" , method = RequestMethod.POST)
   public String  save(ModelMap model) {
       List<Question> questionList = new ArrayList<Question>();
       for (int i = 0; i <10; i++) {
//       	Question question = new Question(i);
//           question.addChoices(1, "choice 1");
//           question.addChoices(2, "choice 2");
//           question.addChoices(3, "choice 3");
//           question.addChoices(4, "choice 4");
//           question.setQuestion("this is question"+i);
//           question.addAnsId(2);
//           question.setAns_desc("ans_desc");
//           question.setDesc("desc");
    	   QuestionEntity questionEntity = new QuestionEntity();
//    	   if(service.findOne(i)!=null) {
//    		   service.saveAndFlush(entity)
//    	   }
    	   questionEntity.setId(i);
   		questionEntity.setAns_desc("asc_desc"+i);
   		questionEntity.setAns_id("[1,2]");
   		questionEntity.setChoices("{\"1\":\"Choice 1\", \"2\":\"Choice 2\",\"3\":\"Choice 3\" }");
   		questionEntity.setDesc("desc"+i);
   		questionEntity.setQuestion("question"+i);
   		service.saveAndFlush(questionEntity);
//       	questionList.add(question);
       }
       
       model.addAttribute("questions", questionList);
       return "question/list";
   }
	
	
	
	 
	@SuppressWarnings("resource")
	 @RequestMapping(value="/add1" , method = RequestMethod.GET)
	public String ReadFile(ModelMap model) {
	System.out.println("testFile");
	if(!new File("问茶108.txt").exists()) {
		throw new RuntimeException("file not found");
	}
	BufferedReader file;
	String title = "";
//	List<String> choicesList= new ArrayList<>(4);
	Map<Integer, String> choicesList = new TreeMap<>();
	int lineNo=0;
	try {
		file = new BufferedReader(new FileReader("问茶108.txt"));
		String line =null;
		
		while( (line=file.readLine())!=null) {
			if(line.trim().isEmpty()) continue;
			if(lineNo==0) {
				title = line;
				
			}else {
				choicesList.put(lineNo, line);
			}
			
			lineNo++;
			if(lineNo%4==0) {
				lineNo= 0;
				Question question = new Question();
				if(title.isEmpty() || choicesList.isEmpty() ) {
					throw new RuntimeException("invalid line:"+line);
				}
				String ansString = title.substring(title.length()-2).toLowerCase();
				Set<Integer> ans_idSet= new TreeSet<>();
				
				if (ansString.matches("[a-z]") ) {
					title = title.substring(0, title.length()-1);
//					String ansString = title.substring( title.lastIndexOf("：")+1).trim();
					 if(ansString.equalsIgnoreCase("a")) {
						ans_idSet.add(1);
					}else if(ansString.equalsIgnoreCase("b")) {
						ans_idSet.add(2);
					}else if(ansString.equalsIgnoreCase("c")) {
						ans_idSet.add(3);
					}else{
						System.out.println(line);
						throw new RuntimeException("invald choice: " + ansString);
					}
					
				}else {
					ans_idSet.add(1);
					ans_idSet.add(2);
					ans_idSet.add(3);
				}

				question.setQuestion(title);
				question.setChoices(choicesList);
				question.setAns_id(ans_idSet);
				
				QuestionEntity questionEntity = new QuestionEntity();
				questionEntity.setChoices(JSON.toJSONString(question.getChoices()));
				questionEntity.setAns_id(  JSON.toJSONString(question.getAns_id() ) );;
				questionEntity.setQuestion(question.getQuestion());
				service.save(questionEntity);
			/*	2、藏区打酥油茶的用茶是：c
				a、湖南安化黑茶
				b、云南普洱茶
				c、四川雅安藏茶*/
				System.out.println(question);
				
				title="";
				choicesList.clear();
			}
			service.flush();
			System.out.println(line);
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return "question/list";
	
	}
	
	 
	 
	 
	 
	 
	 
}
