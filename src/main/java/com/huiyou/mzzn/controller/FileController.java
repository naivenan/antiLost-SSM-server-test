package com.huiyou.mzzn.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import me.fishlord.common.result.ErrorCode;
import me.fishlord.common.result.ResultEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * 文件上传
 * 
 * @author fishlord
 * 
 */
@Controller
public class FileController {

	private static final Logger logger = LoggerFactory
			.getLogger(FileController.class);

	public static String[] arr = { "0", "1", "2", "3", "4", "5", "6", "7", "8",
			"9", "A", "B", "C", "D", "E", "F" };

	@Value("${file.basePath}")
	private String basePath;
	@Value("${file.baseUrl}")
	private String baseUrl;
	@Value("${project.domain}")
	private String domain;

	@PostConstruct
	public void init() {
		File baseFile = new File(basePath);
		if (!baseFile.exists()) {
			for (String a : arr) {
				for (String b : arr) {
					for (String c : arr) {
						for (String d : arr) {
							new File(basePath + a + b + "/" + c + d).mkdirs();
						}
					}
				}
			}
		}
	}

	/**
	 * 上传
	 * 
	 * @return
	 */
	@RequestMapping("/file/upload")
	@ResponseBody
	public ResultEntity upload(
			@RequestParam(value = "file", required = true) CommonsMultipartFile file) {

		ResultEntity resultEntity = new ResultEntity();

		try {
			String savePath = getSavePath(file);
			System.out.println("savePath: "+savePath);
			file.transferTo(new File(basePath + savePath));

			resultEntity.setCode(ErrorCode.SUCCESS);
			resultEntity.setData(domain + baseUrl + savePath);
			System.out.println("data: "+domain + baseUrl + savePath);
		} catch (IOException e) {
			logger.error("文件保存失败", e);
			
			resultEntity.setCode(ErrorCode.ERROR);
			resultEntity.setMsg("文件保存失败");
		}
		return resultEntity;
	}

	/**
	 * 批量上传
	 * 
	 * @return
	 */
	@RequestMapping("/file/multiUpload")
	@ResponseBody
	public ResultEntity mulUpload(
			@RequestParam(value = "files", required = true) CommonsMultipartFile[] files) {

		ResultEntity resultEntity = new ResultEntity();
		List<String> list = new ArrayList<>();
		try {
			for (CommonsMultipartFile file : files) {
				String savePath = getSavePath(file);
				file.transferTo(new File(basePath + savePath));
				list.add(domain + baseUrl + savePath);
			}
			resultEntity.setCode(ErrorCode.SUCCESS);
			resultEntity.setData(list);
		} catch (IOException e) {
			logger.error("文件保存失败", e);

			resultEntity.setCode(ErrorCode.ERROR);
			resultEntity.setMsg("文件保存失败");
		}
		return resultEntity;

	}

	private static String getSavePath(CommonsMultipartFile file) {

		String fileName = file.getFileItem().getName();
		String ext = fileName.substring(fileName.lastIndexOf("."),
				fileName.length());
		Random random = new Random();
		return arr[random.nextInt(arr.length)]
				+ arr[random.nextInt(arr.length)] + "/"
				+ arr[random.nextInt(arr.length)]
				+ arr[random.nextInt(arr.length)] + "/" + random.nextInt(10000)
				+ System.currentTimeMillis() + ext;
	}

}
