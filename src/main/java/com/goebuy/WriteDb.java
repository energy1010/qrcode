package com.goebuy;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.goebuy.entity.QuestionEntity;
import com.goebuy.repository.QuestionRepository;

@Component
public class WriteDb {
	
	@Autowired
	QuestionRepository service;
	
	
	
	public String ReadFile() {
		System.out.println("testFile");
		if(!new File("问茶108.txt").exists()) {
			throw new RuntimeException("file not found");
		}
		service.deleteAllInBatch();
		BufferedReader file;
		String title = "";
//		List<String> choicesList= new ArrayList<>(4);
		Map<Integer, String> choicesList = new TreeMap<>();
		int lineNo=0;
		int questNo=1;
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
					String ansString = title.substring(title.length()-1).toLowerCase();
					Set<Integer> ans_idSet= new TreeSet<>();
					
					if (ansString.matches("[a-z]") ) {
						title = title.substring(0, title.length()-1);

						 if(ansString.equalsIgnoreCase("a")) {
							ans_idSet.add(1);
						}else if(ansString.equalsIgnoreCase("b")) {
							ans_idSet.add(2);
						}else if(ansString.equalsIgnoreCase("c")) {
							ans_idSet.add(3);
						}else{
//							System.out.println(line);
							throw new RuntimeException("invald choice: " + ansString);
						}
						
					}else {
						ans_idSet.add(1);
						ans_idSet.add(2);
						ans_idSet.add(3);
					}

					question.setId(questNo++);
					question.setQuestion(title);
					question.setChoices(choicesList);
					question.setAns_id(ans_idSet);
					QuestionEntity questionEntity = new QuestionEntity();
					questionEntity.setChoices(JSON.toJSONString(question.getChoices()));
					questionEntity.setAns_id(  JSON.toJSONString(question.getAns_id() ) );;
					questionEntity.setQuestion(question.getQuestion());
					questionEntity.setId(question.getId());
					questionEntity.setDesc(question.getDesc());
					questionEntity.setAns_desc(question.getAns_desc());
					questionEntity.setQrcode(question.getQrcode());
					questionEntity.setUrl(question.getUrl());
//					System.out.println(questionEntity);
					service.save(questionEntity);
				/*	2、藏区打酥油茶的用茶是：c
					a、湖南安化黑茶
					b、云南普洱茶
					c、四川雅安藏茶*/
					
					title="";
					choicesList.clear();
				}
				
			//	System.out.println(line);
			}
			service.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "question/list";
		
		}
		
		 
	
//	public static void main(String[] args) {
//		WriteDb writeDb = new WriteDb();
//		writeDb.ReadFile();
//	}
	

}
