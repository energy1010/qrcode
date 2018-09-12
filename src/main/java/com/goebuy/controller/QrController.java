package com.goebuy.controller;

import java.util.TreeSet;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.goebuy.Question;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 */
@RestController
public class QrController {
	
	
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
	
	
	@ApiOperation(value="get question", notes="扫描二维码之后跳转到对应question")
	@ApiImplicitParam(name = "id", value = "question ID", paramType = "path", required = true, dataType = "Integer")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
    public ResponseEntity<Object> get( @PathVariable int id) {
        System.out.println("get id: "+ id);
        Question question = new Question();
        question.addChoices(1, "choice 1");
        question.addChoices(2, "choice 2");
        question.addChoices(3, "choice 3");
        question.addChoices(4, "choice 4");
        question.setQuestion("this is question");
        question.addAnsId(2);
        question.setAns_desc("ans_desc");
        question.setDesc("desc");
        JSONObject js = new JSONObject();
		js.put("data", question);
		js.put("code", 200);
		js.put("msg", "OK");
		return ResponseEntity.status(HttpStatus.OK).body(js);
    }
		
	
}
