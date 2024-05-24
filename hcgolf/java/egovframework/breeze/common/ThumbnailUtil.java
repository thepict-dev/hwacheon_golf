package egovframework.breeze.common;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.service.FileVO;

@Component
public class ThumbnailUtil {

	private static EgovFileMngService fileMngService;
	
	@Autowired
    public void setFileMngService(EgovFileMngService fileMngService) {
    	ThumbnailUtil.fileMngService = fileMngService;
    }
	
	/**
	 * 해당 sourceFileUrl의 파일에 대하여 globals.properties 선언된 크기의 썸네일을 생성한다.
	 * @param fileVO atchFileId정보를 가지고 있는 fileVO
	 * @throws Exception
	 */
    public static void generateThumbnail(String atchFileId) throws Exception{
		int dw = Integer.parseInt(EgovProperties.getProperty("Globals.thumbnailWidthSize"));
		int dh = Integer.parseInt(EgovProperties.getProperty("Globals.thumbnailHeightSize"));
		generateThumbnail(atchFileId, dw, dh);
    }
    
    /**
     * 해당 atchFileId의 파일에 대하여 주어진 크기의 썸네일을 생성한다.
     * @param fileVO atchFileId정보를 가지고 있는 fileVO
     * @param dw 썸네일 폭
     * @param dh 썸네일 높이
     * @throws Exception
     */
	public static void generateThumbnail(String atchFileId, int dw, int dh) throws Exception {
		FileVO fileVO = new FileVO();
		fileVO.setAtchFileId(atchFileId);
		List<FileVO> files = fileMngService.selectFileInfs(fileVO);

		if (files == null){
			throw new Exception("주어진 atchFileId로 조회된 파일이 없습니다.");
		}
		
		for(FileVO fvo : files) {
			if(fvo.getFileExtsn().equalsIgnoreCase("jpg") || fvo.getFileExtsn().equalsIgnoreCase("png") || fvo.getFileExtsn().equalsIgnoreCase("gif") || fvo.getFileExtsn().equalsIgnoreCase("jpeg")){
				generateThumbnail(fvo.getFileStreCours()+fvo.getStreFileNm(), fvo.getStreFileNm(), dw, dh, fvo.getFileExtsn());
			}
		}

//		throw new Exception("주어진 athchFileId로 조회한 파일 중 썸네일을 생성할 수 있는 파일이 없습니다. 허용된 확장자 목록 : [jpg, png, gif, jpeg]");
	}
	
	/**
	 * 해당 sourceFileUrl의 파일에 대하여 globals.properties 선언된 크기의 썸네일을 생성한다.
	 * @param sourceFileUrl	썸네일을 생성할 기본 파일 Url
	 * @param thumbnailFileName 생성될 썸네일 파일 이름
	 * @throws Exception
	 */
	public static void generateThumbnail(String sourceFileUrl, String thumbnailFileName, String fileExtsn) throws Exception{
		int dw = Integer.parseInt(EgovProperties.getProperty("Globals.thumbnailWidthSize"));
		int dh = Integer.parseInt(EgovProperties.getProperty("Globals.thumbnailHeightSize"));
		generateThumbnail(sourceFileUrl, thumbnailFileName, dw, dh, fileExtsn);
	}
	
	/**
	 * 해당 sourceFileUrl의 파일에 대하여 globals.properties 선언된 크기의 썸네일을 생성한다.
	 * @param sourceFileUrl	썸네일을 생성할 기본 파일 Url
	 * @param thumbnailFileName 생성될 썸네일 파일 이름
	 * @param dw 썸네일 폭
	 * @param dh 썸네일 높이
	 * @throws Exception
	 */
	public static void generateThumbnail(String sourceFileUrl, String thumbnailFileName, int dw, int dh, String fileExtsn) throws Exception{
		
		BufferedImage sourceImage= ImageIO.read(new File(sourceFileUrl));
		
	    // 원본이미지 크기
	    int ow = sourceImage.getWidth();
	    int oh = sourceImage.getHeight();
	    
	    // 썸네일 저장 위치 및 이름 지정
		String name = EgovProperties.getProperty("Globals.thumbnailFileStorePath") + thumbnailFileName+"."+fileExtsn;
	    
	    // 원본이미지 사이즈가 썸네일 기본 사이즈보다 클 때만 계산
	    if(ow > dw && oh > dh) {
			
		    // 늘어날 길이를 계산하여 패딩
		    int pd = 0;	
		    if(ow > oh) {
		    	pd = (int)(Math.abs((dh * ow / (double)dw) - oh) / 2d);
		    } else {
		    	pd = (int)(Math.abs((dw * oh / (double)dh) - ow) / 2d);
		    }
		    sourceImage = Scalr.pad(sourceImage, pd, Color.WHITE, Scalr.OP_ANTIALIAS);
			
		    // 변경된 이미지 크기
		    ow = sourceImage.getWidth();
		    oh = sourceImage.getHeight();
			
		    // 썸네일 비율로 크롭
		    int nw = ow;
		    int nh = (ow * dh) / dw;
		    if(nh > oh) {
		    	nw = (oh * dw) / dh;
		    	nh = oh;
		    }
		    BufferedImage cropImg = Scalr.crop(sourceImage, (ow-nw)/2, (oh-nh)/2, nw, nh);
			
		    // 썸네일 크기로 리사이즈
		    BufferedImage destImg = Scalr.resize(cropImg, dw, dh);
			
		    int param_w = destImg.getWidth();
		    int param_h = destImg.getHeight();
			Image scaledImage = destImg.getScaledInstance(param_w,param_h, Image.SCALE_SMOOTH);

			// byte[] buffer = null;
			BufferedImage img = new BufferedImage(param_w, param_h, BufferedImage.TYPE_INT_RGB);
			img.createGraphics().drawImage(scaledImage, 0, 0, null);
			BufferedImage img2 = new BufferedImage(param_w, param_h ,BufferedImage.TYPE_INT_RGB);
			img2 = img.getSubimage(0, 0, param_w, param_h);
			
			OutputStream os = new FileOutputStream(name);
			ImageIO.write(img2, fileExtsn, os);
			os.close();
			
	    }else {
		    // 원본이미지 사이즈가 썸네일 기본 사이즈보다 작다면 그냥 저장
			OutputStream os = new FileOutputStream(name);
			ImageIO.write(sourceImage, fileExtsn, os);
			os.close();
	    }
		
	}
}
