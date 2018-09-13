package com.goebuy.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	
//	
//	private String url;
//	
//	private String qrcode;
	
	
	
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

//	public String getUrl() {
//		return url;
//	}
//
//	public void setUrl(String url) {
//		this.url = url;
//	}
//
//	public String getQrcode() {
//		return qrcode;
//	}
//
//	public void setQrcode(String qrcode) {
//		this.qrcode = qrcode;
//	}
//	
//	@Override
//	public String toString() {
//		return JSON.toJSONString(this);
//	}
//	
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
