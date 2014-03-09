/**
* @Copyright (c) 成都大学信息科学与技术学院
* 服务外包与创意大赛
*/
package cn.cdu.edu.TQC.bems.service;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import cn.cdu.edu.TQC.bems.db.bean.EmergncyGroup;
import cn.cdu.edu.TQC.bems.db.bean.Url;
import cn.cdu.edu.TQC.bems.db.dao.EmergncyGroupDao;
import cn.cdu.edu.TQC.bems.db.dao.UrlDao;
import cn.cdu.edu.TQC.bems.db.dao.UrlRoleDao;
import cn.cdu.edu.TQC.bems.db.dao.impl.EmergncyGroupDaoImpl;
import cn.cdu.edu.TQC.bems.db.dao.impl.UrlDaoImpl;
import cn.cdu.edu.TQC.bems.db.dao.impl.UrlRoleDaoImpl;

import com.sun.jersey.api.client.ClientResponse.Status;
import com.sun.jersey.spi.resource.Singleton;

/**
 * @ClassName: UrlService
 * 说明：
 * TODO(Tell the reader such role.)
 * @Author SUN	【email:1096490965@qq.com】
 * @Version V1.0	2012-7-17 上午11:25:07
 *
 */

@Path("/urlService")
@Singleton
public class UrlService {

	private UrlDao dao=null;
	public UrlService(){
		dao=new UrlDaoImpl();
	}
	
	@GET
	@Path("geturl")
	@Produces("application/xml")
	public List<Url> getUrl(){
		List<Url> list=null;
		try{
			list=dao.getUrls();
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	@POST
	@Path("createurl")
	@Produces("application/xml")
	public Response createUrl(
			@FormParam("url") String urlstr,
			@FormParam("urlName") String urlName,
			@FormParam("parentid") Integer parentid){
		Url url=new Url();
		url.setUrl(urlstr);
		url.setUrlName(urlName);
		url.setParentId(parentid);
		
		dao.addUrl(url);
		Response res = null;
		res = Response.ok(url).type("application/xml").build();
		return res;
	}
	
	@GET
	@Path("/{id}")
	@Produces("application/xml")
	public Url getUrlById(@PathParam("id") Integer id){
		Url url=new Url();
		try{
			url=dao.getUrlById(id);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return url;
	}
	@GET
	@Path("del/{id}")
	@Produces("application/xml")
	public Response delUrlById(
			@PathParam("id") Integer id){
		String xmlString="";
		try{
			if(dao.deleteUrlById(id)){
				xmlString="删除成功";
			}else{
				xmlString="删除失败";
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		Response res; 
        ResponseBuilder builder = Response.status(Status.CREATED);
        builder.type("application/xml");
        builder.entity("<message>"+xmlString+"</message>");
        res = builder.build();
        return res;
	}
	
	
	
	/**
	 * 添加角色的权限
	 * Administrator
	 * @param urls
	 * @param roleid
	 * @return
	 */

	@POST
	@Path("addurlrole")
	@Produces("application/xml")
	public Response addUrl_role(
			@FormParam("urls")  String urls,
			@FormParam("roleid")  Integer roleid
			){
		
		String[] url=urls.split(",");
		String sql[]=new String[url.length+1];
		sql[0]="delete from url_role where role="+roleid+"";
		for(int i=0;i<url.length;i++){
			sql[i+1]=" insert into url_role(role,url) values ("+roleid+",'"+url[i]+"') ";
		}
		System.out.println(sql);
		UrlRoleDao urlroleDao=new UrlRoleDaoImpl();
		String xmlString="失败";
		if(urlroleDao.updateUrlRole(sql)){
			xmlString="成功";
		}
		Response res;
		ResponseBuilder builder =Response.status(Status.CREATED);
		builder.type("application/xml");
		builder.entity("<message>"+xmlString+"</message>");
		res=builder.build();
		return res;
	}
	
	@GET
	@Path("geturlbyroleid/{roleid}")
	@Produces("application/xml")
	public Response getUrlByRoleId(
			@PathParam("roleid")  Integer roleid
			){
		
		UrlRoleDao urlroleDao=new UrlRoleDaoImpl();
		List<String> list=urlroleDao.getUrlByRoleId(roleid);
		String re="";
		for(String string:list){
			re+=string+",";
		}
		Response res;
		ResponseBuilder builder =Response.status(Status.CREATED);
		builder.type("application/xml");
		builder.entity("<urls>"+re.substring(0,re.length()-1)+"</urls>");
		res=builder.build();
		return res;
		 
	}
	
	
	
	
	
}
