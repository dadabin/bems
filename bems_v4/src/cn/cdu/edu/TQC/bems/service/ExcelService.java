/**
 * @Copyright (c) 成都大学信息科学与技术学院
 * 服务外包与创意大赛
 */
package cn.cdu.edu.TQC.bems.service;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import cn.cdu.edu.TQC.bems.DownLoadUtil;
import cn.cdu.edu.TQC.bems.ReadExcel;
import cn.cdu.edu.TQC.bems.db.bean.Phone;
import cn.cdu.edu.TQC.bems.db.bean.PhoneFeeData;
import cn.cdu.edu.TQC.bems.db.dao.PhoneFeeDataDao;
import cn.cdu.edu.TQC.bems.db.dao.impl.PhoneDaoImpl;
import cn.cdu.edu.TQC.bems.db.dao.impl.PhoneFeeDataDaoImpl;

import com.sun.jersey.api.client.ClientResponse.Status;
import com.sun.jersey.spi.resource.Singleton;

/**
 * @ClassName: ExcelService 说明： TODO(Tell the reader such role.)
 * @Author LPM 【email:shouli1990@gmail.com 】
 * @Version V1.0 2012-7-15 上午11:30:42
 * 
 */

@Path("/exelTools")
@Singleton
public class ExcelService {
    
    private PhoneFeeDataDao dao;
    public ExcelService(){
	dao = new PhoneFeeDataDaoImpl();
    }
    
   /**
    * 
    * @param type---末班下载类型
    * 1、phonedata--电话费用模版下载
    * 2、phone----电话基本信息末班下载
    * 
    * 
    * @param servletResponse
    * @param servletRequest
    * @return
    * @throws Exception
    */
    @GET
    @Path("/downLoadExcelTemplate")
    @Produces("application/json")
    public Response downLoad(
	    @QueryParam("type") String type,
	    @Context HttpServletResponse servletResponse,
	    @Context HttpServletRequest servletRequest) throws Exception {
	String fileName = type+".xls";
	String path = servletRequest.getSession().getServletContext()
		.getRealPath("/download");
	String filePath = path + "\\" + fileName;
	DownLoadUtil.downLoadFile(type,filePath, servletResponse, fileName, "xls");
	return Response.ok().build();
    }

    /***
     * @author LPM
     * 
     * 
     * @param type----------------操作类型
     * 1、phonedata解析电话费用excel
     * 2、phone解析电话基本信息
     * @param servletResponse
     * @param servletRequest
     * @return
     * @throws Exception
     */
    @POST
    @Path("/upLoadExcel")
    @Consumes("multipart/form-data")
    @Produces("application/xml")
    public Response upload(
	    @QueryParam("type") String type,
	    @Context HttpServletResponse servletResponse,
	    @Context HttpServletRequest servletRequest) throws Exception {
	
	String fullfilepath = servletRequest.getSession().getServletContext()
		.getRealPath("/")
		+ "upload\\";
	String fileName = "";
	FileItemFactory factory = new DiskFileItemFactory();
	ServletFileUpload upload = new ServletFileUpload(factory);
	List items = null;
	try {
	    items = upload.parseRequest(servletRequest);
	    System.out.println("items: " + items);
	} catch (Throwable e) {
	    e.printStackTrace();
	}

	Iterator itr = items.iterator();
	while (itr.hasNext()) {
	    FileItem item = (FileItem) itr.next();

	    if (item.isFormField()) {
		String name = item.getFieldName();
		System.out.println("name: " + name);
		String value = item.getString();
		System.out.println("value: " + value);
//
//		if (name.equalsIgnoreCase("buildingid")) {
//		    buildingid = value;
//		}

	    } else {
		// processUploadedFile
		String fieldName = item.getFieldName();
		fileName = item.getName();
		int i2 = fileName.lastIndexOf("\\");
		if (i2 > -1)
		    fileName = fileName.substring(i2 + 1);

		System.out.println("-------------" + fileName);
		File dirs = new File(fullfilepath);

		File uploadedFile = new File(dirs, fileName);
		item.write(uploadedFile);

	    }
	}
	
	// 在此解析excel
	System.out.println("parse--------->"+fullfilepath+fileName);
	if("phonedata".equals(type)){
	    List<PhoneFeeData> all = new ReadExcel().readExcel(fullfilepath+fileName);
	        PhoneFeeDataDaoImpl dao = new PhoneFeeDataDaoImpl();
	        //插入数据到数据库中
	        for(PhoneFeeData data : all){
	            dao.addPhoneFeeData(data);
	        }
	}
	if("phone".equals(type)){
	    //电话基本信息excel解析
	    List<Phone> datas = new ReadExcel().readPhoneExcel(fullfilepath+fileName);
	    PhoneDaoImpl dao = new PhoneDaoImpl();
	    //遍历exel，将数据插入到数据库中
		for(Phone phone: datas){
		    dao.addPhone(phone);
		}
	}
        // 返回信息
	String xmlString = "<result>";

	    xmlString += "<entity>";
	    xmlString += "<filename>" + "upload/" + fileName + "</filename>";
	    xmlString += "</entity>";
	    xmlString += "</result>";
	Response res;
	ResponseBuilder builder = Response.status(Status.CREATED);
	builder.type("application/XML");
	builder.entity(xmlString);
	res = builder.build();
	return res;

    }
}
