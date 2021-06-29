package com.local.date.util;

import org.springframework.util.FileCopyUtils;

import java.io.File;

public class UploadFileUtils {
	
	public static String uploadFile(String uploadPath, String originalName, byte[] fileData)throws Exception{
		
		String savedName =  originalName;
		
		String makePath = File.separator + "Date";
		
		//업로드할 디렉터리(날짜별 폴더) 생성
		String savedPath = uploadPath + makePath;
		makeDir(uploadPath, makePath);
		System.out.println("Saved Path : " + savedPath);

		//파일 경로(기존의 업로드 경로+ 날짜별 경로), 파일명을 받아 파일 객체 생성
		File target = new File(savedPath + File.separator + savedName);
		System.out.println("target : " + target);
		
		//임시 디렉터리에 업로드된 파일을 지정된 디렉터리로 복사
		FileCopyUtils.copy(fileData, target);
		
		//썸네일을 생성하기 위한 파일의 확장자 검사
		//파일명이 aaa.bbb.ccc.jpg일 경우 마지막 마침표를 찾기 위해
//		String formatName = originalName.substring(originalName.lastIndexOf(".")+1);
//		String uploadedFileName = null;
		
		//이미지 파일은 썸네일 사용
//		if(MediaUtils.getMediaType(formatName) != null){
//			//썸네일 생성
//			uploadedFileName = makeThumbnail(uploadPath, savedPath, savedName, fileData);
//		}
		
		return savedName;
		
	}

	//썸네일 생성
//	private static String makeThumbnail(String uploadPath, String path, String fileName, byte[] fileData)throws Exception {
//
//		File target = new File(uploadPath + path, fileName);
//		FileCopyUtils.copy(fileData, target);
//
//		//썸네일 이름 생성 원본파일명에 "_s"를 붙임
//		String thumbnailName = uploadPath + path + File.separator + "s_" + fileName;
//		File newFile = new File(thumbnailName);
//		Thumbnails.of(target).size(300, 300).toFile(newFile);
//
//		//썸네일 이름을 리턴
//		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
//	}

	
	//날짜별 디렉터리 추출
//	private static String calcPath(String uploadPath) {
//		
//		Calendar cal = Calendar.getInstance();
//		
//		//연도, ex) \2020
//		String yearPath = File.separator + cal.get(Calendar.YEAR);
//		
//		//월, ex) \2020\04
//		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);
//		
//		//일, ex \2020\04\08
//		String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));
//		System.out.println(datePath);
//		
//		makeDir(uploadPath, yearPath, monthPath, datePath);
//		
//		return datePath;
//	}

	//디렉터리 생성
	private static void makeDir(String uploadPath, String path) {
		//디렉터리가 존재하면
		if(new File(path).exists()){
			return;
		}else{
			File dirPath = new File(uploadPath + path);
			dirPath.mkdir();
		}
	}
	
	//아이콘 생성
//	private static String makeIcon(String uploadPath, String path, String fileName)throws Exception{
//		//아이콘의 이름
//		String iconName = uploadPath + path + File.separator + fileName;
//		//아이콘 이름을 리턴
//		//File.separatorChar : 디렉터리 구분자
//		// 윈도우 \ , 유닉스(리눅스) /
//		
//		return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
//	}

}
