package com.goebuy.entity;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.goebuy.Question;

/**
 * 
 * @author Administrator
 *
 */
/*//indexes={@Index(name="name_Index", columnList="name")},
@Getter
@Setter*/

@Entity
@Table(name = "question", schema = "qrcode", catalog = "")
public class QuestionEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5248916191547386335L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	/**
	 * 提问
	 */
	private String question;
	
	/** 选项 */
	private String choices;// = new TreeMap<>();
	
	/** 答案选项  */
	private String ans_id;// = new TreeSet<>();
	
	/** 答案解释 */
	private String ans_desc;
	/** 说明 */
	@Basic
    @Column(name = "`desc`",   nullable = true)
	private String desc;
	
	@Basic
    @Column(name = "`url`",  unique=true, nullable = true)
	private String url;
	
	@Basic
    @Column(name = "`qrcode`", unique=true,  nullable = true)
	private String qrcode;
	
	
	
	public QuestionEntity() {
		
	}
	
	public QuestionEntity(int id) {
		this.id=id;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

//	@Basic
//    @Column(name = "question", unique=true,  nullable = false)
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

//	@Basic
//    @Column(name = "choices",   nullable = false)
	public String getChoices() {
		return choices;
	}

	public void setChoices( String choices) {
		this.choices = choices;
	}

//	@Basic
//    @Column(name = "ans_id",   nullable = false)
	public String getAns_id() {
		return ans_id;
	}

	public void setAns_id(String ans_id) {
		this.ans_id = ans_id;
	}
	
//	@Basic
//    @Column(name = "ans_desc",   nullable = true)
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
	
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
	public Question convert2Question() {
		Question question = new Question(this.getId());
		question.setAns_desc( this.getAns_desc() );
		question.setUrl(this.getUrl());
		question.setQrcode(this.getQrcode());
		question.setQuestion(this.getQuestion());
		question.setDesc(this.getDesc());
		
//		String ans_id = questionEntity.getAns_id().substring(1,questionEntity.getAns_id().length()-1 );
////		String choices
////		Map<Integer, String> cmMap= (Map<Integer, String>) JSON.toJSON(questionEntity.getChoices());
//		for(String s: ans_id.split(",")) {
//			question.addAnsId(Integer.parseInt(s));
//		}
		@SuppressWarnings("unchecked")
		Set<Integer> ans_set = JSON.parseObject(this.getAns_id(), new TypeReference<TreeSet>() {} );
		@SuppressWarnings("unchecked")
		Map<Integer, String>   cmMap = JSON.parseObject(this.getChoices(),  new TypeReference<TreeMap>() {});
//		System.out.println(object);
//		System.out.println(object1);
		if(ans_set!=null) {
			question.setAns_id(ans_set);
		}
		if(cmMap!=null) {
			question.setChoices(cmMap);
		}
		System.out.println(question);
		return question;
	}
	
	
//	public static QuestionEntity toObj(String jsonStr) {
//		QuestionEntity obj = null;
//		try {
//			if(jsonStr!=null) {
//				obj = (QuestionEntity) JSON.parse(jsonStr);
//			}
//		} catch (Exception e) {
//			obj = null;
//		}
//		return obj;
//	}
	
	
	
	
}
