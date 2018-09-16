package com.goebuy;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
* 二维码工具类
*/
public class QRCodeUtil {
  private static final int width = 300;// 默认二维码宽度
  private static final int height = 300;// 默认二维码高度
  private static final String format = "png";// 默认二维码文件格式
  private static final Map<EncodeHintType, Object> hints = new HashMap();// 二维码参数

  static {
      hints.put(EncodeHintType.CHARACTER_SET, "utf-8");// 字符编码
      hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);// 容错等级 L、M、Q、H 其中 L 为最低, H 为最高
      hints.put(EncodeHintType.MARGIN, 2);// 二维码与图片边距
  }
  /**
   * 返回一个 BufferedImage 对象
   * @param content 二维码内容
   * @param width   宽
   * @param height  高
   */
  public static BufferedImage toBufferedImage(String content, int width, int height) throws WriterException, IOException {
      BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
      return MatrixToImageWriter.toBufferedImage(bitMatrix);
//      Graphics2D gs = bufImg.createGraphics();
//      // 设置背景颜色
//      gs.setBackground(Color.WHITE);
//      // 画矩形
//      gs.clearRect(0, 0, imgSize, imgSize);
//
//      // 设定图像颜色 BLACK
//      gs.setColor(Color.BLACK);
//      // 设置偏移量，不设置可能导致解析出错
//      int pixoff = 2;
//      // 输出内容> 二维码
//      if (contentBytes.length > 0 && contentBytes.length < 800) {
//          // calQrcode()让字符串生成二维码。
//          boolean[][] codeOut = qrcode.calQrcode(contentBytes);
//          for (int i = 0; i < codeOut.length; i++) {
//              for (int j = 0; j < codeOut.length; j++) {
//                  if (codeOut[j][i]) {
//                      gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
//                  }
//              }
//          }
//      } else {
//          throw new Exception("QRCode content bytes length = " + contentBytes.length + " not in [0, 800].");
//      }
//      gs.dispose();
//      bufImg.flush();
  }
  
  /**
   * 将二维码图片输出到一个流中
   * @param content 二维码内容
   * @param stream  输出流
   * @param width   宽
   * @param height  高
   */
  public static void writeToStream(String content, OutputStream stream, int width, int height) throws WriterException, IOException {
      BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
      MatrixToImageWriter.writeToStream(bitMatrix, format, stream);
  }
  /**
   * 生成二维码图片文件
   * @param content 二维码内容
   * @param path    文件保存路径
   * @param width   宽
   * @param height  高
   */
  public static void createQRCode(String content, String path, int width, int height) throws WriterException, IOException {
      BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
      //toPath() 方法由 jdk1.7 及以上提供
      MatrixToImageWriter.writeToPath(bitMatrix, format, new File(path).toPath());
  }
  
  
  /**
   * 生成二维码(QRCode)图片
   * 
   * @param content
   *            存储内容
   * @param imgPath
   *            图片路径
   * @param imgType
   *            图片类型
   * @param output
   *            输出流
   * @param size
   *            二维码尺寸
   */
  public static void encoderQRCode(String content, String imgPath) {
      encoderQRCode(content, imgPath, "png", 200);
  }

  public static void encoderQRCode(String content, OutputStream output) {
      encoderQRCode(content, output, "png", 200);
  }

  public static void encoderQRCode(String content, String imgPath, String imgType) {
      encoderQRCode(content, imgPath, imgType, 200);
  }

  public static void encoderQRCode(String content, OutputStream output, String imgType) {
      encoderQRCode(content, output, imgType, 200);
  }

  /**
   *   生成二维码QRCode图片
   * @param content
   * @param imgPath
   * @param imgType
   * @param size
   */
  public static void encoderQRCode(String content, String imgPath, String imgType, int size) {
      try {
          BufferedImage bufImg = QRCodeUtil.toBufferedImage(content, size, size);
          if( !imgPath.endsWith(imgType.toLowerCase() )) {
        	  imgPath = imgPath+ "."+imgType.toLowerCase();
          }
          File imgFile = new File(imgPath);
          if (!imgFile.exists()) {
              imgFile.mkdirs();
          }
          // 生成二维码QRCode图片
          ImageIO.write(bufImg, imgType, imgFile);
      } catch (Exception e) {
          e.printStackTrace();
      }
  }

  public static void encoderQRCode(String content, OutputStream output, String imgType, int size) {
	  QRCodeUtil.encoderQRCode(content, output,imgType, size, size);
  }
  
  public static void encoderQRCode(String content, OutputStream output, String imgType, int width,int height ) {
      try {
          BufferedImage bufImg = QRCodeUtil.toBufferedImage(content,  height, width);
          // 生成二维码QRCode图片
          ImageIO.write(bufImg, imgType, output);
      } catch (Exception e) {
          e.printStackTrace();
      }
  }
  
  public static void main(String[] args) {
	  String host ="192.168.1.100";
//	  host="39.105.56.70";
//	  host="wwww.huiyibuer.com";
	  host="wwww.huiyibuer.cn";
	  
	  String port ="80";
//	  port = "8080";
	  
	  System.out.printf("host:%s port:%s\n", host, port);
	  
	  for(int i=1;i<=108;i++) {
		  String url="http://"+host+":"+port+"/question/"+i; //succ
		  if(port.equals("80")) {
		   url="http://"+host + "/question/"+i; //succ
	  }
	  
	  
	  System.out.println(url);
//	  String content="www.baidu.com"; //failed
	  int width=200;
	  int height =200;
	  QRCodeUtil.encoderQRCode(url, "qrcode_"+i);
	  }
	
}
  
}