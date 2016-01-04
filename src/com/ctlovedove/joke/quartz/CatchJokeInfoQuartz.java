package com.ctlovedove.joke.quartz;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.ctlovedove.joke.bean.JokeInfo;
import com.ctlovedove.joke.service.JokeInfoService;
import com.ctlovedove.util.HttpUtil;
import com.ctlovedove.util.StringUtil;
/**
 * 抓去来源与<a href='http://lengxiaohua.com/'>http://lengxiaohua.com/</a>的内容
 * @author chenting
 *
 */
@Component("catchJokeInfoQuartz")
public class CatchJokeInfoQuartz {

	private final int BATCH_NUM = 100;//每读取100条数据，插入一次数据库
	@Resource
	private JokeInfoService jokeInfoService;
	
	public JokeInfoService getJokeInfoService() {
		return jokeInfoService;
	}

	public void setJokeInfoService(JokeInfoService jokeInfoService) {
		this.jokeInfoService = jokeInfoService;
	}

	public void run(){
		String url = "http://lengxiaohua.com/";
		this.execute(url);
	}
	
	public void execute(String url){
		if(!StringUtil.isURL(url)){
			return;
		}
		String html = HttpUtil.sendGet(url, "");
		Document document = null;
		List<JokeInfo> jokeInfoList = null;
		if(StringUtil.isNull(html)){
			return;
		}
		try {
			document = Jsoup.parse(html);
			if(document == null){
				return;
			}
			Elements liElements = document.getElementsByClass("joke_li");
			jokeInfoList = new ArrayList<JokeInfo>();
			for (Element liElement : liElements) {
				JokeInfo jokeInfo = new JokeInfo();
				boolean isTrue = this.setJokeInfo(liElement, jokeInfo);
				if(isTrue){
					jokeInfoList.add(jokeInfo);
				}
				System.out.println(jokeInfo);
				if(jokeInfoList.size() == BATCH_NUM){
					jokeInfoService.addBatch(jokeInfoList);
					jokeInfoList.clear();
				}
			}
			if(jokeInfoList.size() != 0){
				jokeInfoService.addBatch(jokeInfoList);
				jokeInfoList.clear();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean setJokeInfo(Element liElement, JokeInfo jokeInfo){
		String image = "";
		String author = null;
		String jokeContent = null;
		//笑话内容
		try {
			Elements paraCanElems = liElement.getElementsByClass("para_can");
			Element elem = paraCanElems.get(0);
			Elements jsElements = elem.getElementsByAttributeValue("js", "joke_summary");
			Element jsElement = jsElements.get(0);
			jokeContent = jsElement.html().replaceAll("<span class=\"first_char\">", "").replaceAll("</span>", "");
			System.out.println("jokeContent----"+jokeContent);
			//图片
			Elements imageElems = paraCanElems.select("img");
			if(imageElems != null && imageElems.size() > 0){
				image = getImageStr(imageElems);
			}
			//笑话作者
			Elements userInfoElems = liElement.getElementsByClass("user_info");
			Element userInfoElem = userInfoElems.get(0);
			Elements authorElems = userInfoElem.getElementsByTag("a");
			Element authorElem = authorElems.get(0);
			author = authorElem.text();
			System.out.println("author------"+author);
			
			jokeInfo.setContent(jokeContent);
			jokeInfo.setSource(author);
			jokeInfo.setTitle("我们都爱冷笑话");
			jokeInfo.setPubDate(new Date());
			jokeInfo.setImage(image);
			jokeInfo.setTypeId(1);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public String getImageStr(Elements imageElems){
		String image = "";
		try {
			for (Element element : imageElems) {
				String src = element.attr("data-original");
				if(StringUtil.isNull(src)){
					continue;
				}
				if(src.indexOf("http") == -1){
					src = "http://lengxiaohua.com" + src;
				}
				src = src.replaceAll("!water", "");
				image += (StringUtil.isNull(src) ? "" : ((StringUtil.isNull(image) ? "" + src : "|" + src)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("image------"+image);
		return image;
	}
	
	public static void main(String[] args) {
		String url = "http://lengxiaohua.com/";
		//ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml","springmvc-servlet.xml","mybatis-config.xml");
		ApplicationContext ctx = new FileSystemXmlApplicationContext("" +
				"config/applicationContext.xml",
				"config/springmvc-servlet.xml");
		CatchJokeInfoQuartz catchJokeInfoQuartz = ctx.getBean("catchJokeInfoQuartz", CatchJokeInfoQuartz.class);
		catchJokeInfoQuartz.execute(url);
	}
}
