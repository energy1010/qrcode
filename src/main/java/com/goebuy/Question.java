package com.goebuy;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import com.alibaba.fastjson.JSON;
import com.goebuy.entity.QuestionEntity;



public class Question implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5248916191547386335L;

	/**
	 */
	private int id;
	
	/**
	 * 提问
	 */
	private String question;
	
	/** 选项 */
	private Map<Integer, String> choices = new TreeMap<>();
	
	/** 答案选项  */
	private Set<Integer> ans_id = new TreeSet<>();
	
	/** 答案解释 */
	private String ans_desc;
	/** 说明 */
	private String desc;
	
	
	private String url;
	
	private String qrcode;
	
	
	public Question() {
		
	}
	
	public Question(int id) {
		this.id=id;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Map<Integer, String> getChoices() {
		return choices;
	}

	public void setChoices(Map<Integer, String> choices) {
		this.choices = choices;
	}

	public Set<Integer> getAns_id() {
		return ans_id;
	}

	public void setAns_id(Set<Integer> ans_id) {
		this.ans_id = ans_id;
	}

	public String getAns_desc() {
		return ans_desc;
	}

	public void setAns_desc(String ans_desc) {
		this.ans_desc = ans_desc;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getQrcode() {
		return qrcode;
	}

	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}
	
	public void addChoices(int choice_id, String choice_content) {
		if(!choices.containsKey(id)) {
			choices.put(choice_id, choice_content);
		}
	}
	
	public void addAnsId(int choice_id) {
		if(!this.ans_id.contains(choice_id)) {
			this.ans_id.add(choice_id);
		}
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
	public static Question toObj(String jsonStr) {
		Question obj = null;
		try {
			if(jsonStr!=null) {
				obj = (Question) JSON.parse(jsonStr);
			}
		} catch (Exception e) {
			obj = null;
		}
		return obj;
	}
	
//	public Question convertFromQuestionEntity(QuestionEntity questionEntity) {
//		if(questionEntity!=null) {
//			this.id=questionEntity.getId();
//			this.question=questionEntity.getQuestion();
//			Map<Integer, String> choice_map = (Map<Integer, String>) JSON.parse(questionEntity.getChoices());
//			if(choice_map!=null && !choice_map.isEmpty()) {
//				this.choices.clear();
//				for(Map.Entry<Integer, String> e: choice_map.entrySet()) {
//				this.addChoices(e.getKey(), e.getValue());
//			}
//			}
//			Set<Integer> ans_set = (Set<Integer>) JSON.parse(questionEntity.getAns_id());
//			if(ans_set!=null && !ans_set.isEmpty()) {
//				this.ans_id.clear();
//				for(Integer i: ans_set) {
//					this.ans_id.add(i);
//				}
//			}
//			this.ans_desc = questionEntity.getAns_desc();
//			this.desc = questionEntity.getDesc();
//			
//		}
//		return this;
//	}
	
	
}
