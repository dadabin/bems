package cn.cdu.edu.TQC.bems;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 版权所有:(c)2012-2013 sun</
 * 
 * @作者: Administrator
 * @时间: 2012-8-16 上午12:13:58
 * @描述: [FileDownloadUtils]请在此简要描述类的功能
 * 
 *      文件下载的几种方式
 * 
 */

public class FileDownloadUtils {

	public HttpServletResponse download(String path,
			HttpServletResponse response) {
		try {
			// path是指将要下载的文件的路径
			File file = new File(path);
			// 取得文件名
			String filename = file.getName();
			// //取得文件的后缀
			String ext = filename.substring(filename.lastIndexOf(".") + 1)
					.toUpperCase();
			// 以流的形式下载文件
			InputStream fis = new BufferedInputStream(new FileInputStream(path));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			// 设置response的Header
			response.addHeader("Content-Disposition", "attachment;file="
					+ new String(filename.getBytes()));
			response.addHeader("Content-Length", "" + file.length());
			OutputStream toClient = new BufferedOutputStream(
					response.getOutputStream());
			response.setContentType("application/octet-stream");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return response;
	}

	public void downloadLocal(HttpServletResponse response)
			throws FileNotFoundException {
		// 下载本地文件
		String fileName = "Operator.doc".toString();// 文件默认保存名
		// 读到流中
		InputStream inStream = new FileInputStream("c:/Operator.doc");// 文件的存放路径
		// 设置输出的格式
		response.reset();
		response.setContentType("bin");
		response.addHeader("Content-Disposition", "attachment;filename=\""
				+ fileName + "\"");
		// 循环取出流中的数据
		byte[] b = new byte[100];
		int len;
		try {
			while ((len = inStream.read(b)) > 0)
				response.getOutputStream().write(b, 0, len);
			inStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void downloadNet(HttpServletResponse response)
			throws MalformedURLException {
		// 下载网络文件
		int bytesum = 0;
		int byteread = 0;
		URL url = new URL("windine.blogdriver.com/logo.gif");
		try {
			URLConnection conn = url.openConnection();
			InputStream inStream = conn.getInputStream();
			FileOutputStream fs = new FileOutputStream("c:/abc.gif");
			byte[] buffer = new byte[1024];
			int length;
			while ((byteread = inStream.read(buffer)) != -1) {
				bytesum += byteread;
				System.out.println(bytesum);
				fs.write(buffer, 0, byteread);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// 支持在线打开文件的一种方式
	public void downLoad(String filePath, HttpServletResponse response,
			boolean isOnLine) throws IOException {
		File f = new File(filePath);
		if (!f.exists()) {
			response.sendError(404, "File not found");
			return;
		}
		BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
		byte[] buf = new byte[1024];
		int len = 0;
		response.reset();// 非常重要
		if (isOnLine) {// 在线打开方式
			URL u = new URL("fill:///" + filePath);
			response.setContentType(u.openConnection().getContentType());
			response.setHeader("Content-Disposition", "attachment;filename="
					+ f.getName());
			// 文件名应该编码成UTF-8
		} else {// 纯下载方式
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ f.getName());

		}
		OutputStream out = response.getOutputStream();
		while ((len = br.read(buf)) > 0) {
			out.write(buf, 0, len);
		}
		br.close();
		out.close();
	}
}
