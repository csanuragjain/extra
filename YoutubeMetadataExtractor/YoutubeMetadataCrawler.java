package com.cooltrickshome;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class YoutubeMetadataCrawler {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String link="";
		Scanner s=new Scanner(System.in);
		// TODO Auto-generated method stub
		try {
			System.out.println("Enter the youtube video for which metadata need to be extracted");
			link=s.nextLine();
			Document doc = Jsoup.connect(link).ignoreContentType(true).timeout(5000).get();
			YoutubeMetadataCrawler ymc=new YoutubeMetadataCrawler();
			String title=ymc.getTitle(doc);
			String desc=ymc.getDesc(doc);
			String views=ymc.getViews(doc);
			int subscribed=ymc.getPeopleSubscribed(doc);
			int liked=ymc.getPeopleLiked(doc);
			int disliked=ymc.getPeopleDisliked(doc);
			String vid=ymc.getVideoId(link);
			List<String> comments=ymc.getCommentsDesc(link,vid);
			
			System.out.println(title);
			System.out.println(desc);
			System.out.println("Video Views: \n"+views);
			System.out.println("People subscribed: \n"+subscribed);
			System.out.println("People who liked the video: \n"+liked);
			System.out.println("People who disliked the video: \n"+disliked);
			System.out.println("Top Comments: ");
			int i=0;
			for(String comment:comments)
			{
				System.out.println(++i+") "+comment);
			}
		} catch (IOException e) {
			System.out.println("JSoup is unable to connect to the website");
		}
		finally
		{
			s.close();
		}
	}
	
	public static String removeUTFCharacters(String data){
		Pattern p = Pattern.compile("\\\\u(\\p{XDigit}{4})");
		Matcher m = p.matcher(data);
		StringBuffer buf = new StringBuffer(data.length());
		while (m.find()) {
		String ch = String.valueOf((char) Integer.parseInt(m.group(1), 16));
		m.appendReplacement(buf, Matcher.quoteReplacement(ch));
		}
		m.appendTail(buf);
		return new String(buf);
		}
	
	public String getTitle(Document doc)
	{
		return doc.select("#eow-title").text();
	}
	
	public String getViews(Document doc)
	{
		return doc.select(".watch-view-count").text();
	}
	
	public String getDesc(Document doc)
	{
		return doc.select("#watch-description-clip").text();
	}
	
	public List<String> getCommentsDesc(String link, String vid)
	{
		List<String> comments=new ArrayList<>();
		try {
			Response response = Jsoup.connect(link).ignoreContentType(true).timeout(5000).execute();
			Document doc = Jsoup.connect("https://www.youtube.com/watch_fragments_ajax?v="+vid+"&tr=time&distiller=1&ctoken="+getCommentToken(response)+"&frags=comments&spf=load")
				.ignoreContentType(true)
				.cookies(response.cookies())
				.header("X-Client-Data", "")
				.timeout(5000)
				.data("session_token",getSessionToken(response))
				.post();
			String commentSource=doc.body().text();
			while(commentSource.indexOf("comment-renderer-text-content")>-1)
			{
				int pos=commentSource.indexOf("comment-renderer-text-content")+37;
				commentSource=commentSource.substring(pos);
				//System.out.println(commentSource);
				String comment=commentSource.substring(0,commentSource.indexOf("div")-8);
				comments.add(removeUTFCharacters(comment));
			}
		Elements e= doc.select(".comment-renderer-text-content");
		for(Element e1:e)
		{
			comments.add(e1.text());
		}
		} catch (IOException e2) {
			System.out.println("Unable to retrieve comments "+e2.getMessage());
			e2.printStackTrace();
		}
		return comments;
	}
	
	public String getCommentToken(Response res)
	{
		String pageSource=res.body();
		String commentToken=pageSource=pageSource.substring(pageSource.indexOf("COMMENTS_TOKEN': \"")+18);
		commentToken=commentToken.substring(0,commentToken.indexOf("\""));
		commentToken=commentToken.replaceAll("%", "%25");
		return commentToken;
	}
	
	public String getSessionToken(Response res)
	{
		String pageSource=res.body();
		String xsrfToken=pageSource=pageSource.substring(pageSource.indexOf("XSRF_TOKEN': \"")+14);
		xsrfToken=xsrfToken.substring(0,xsrfToken.indexOf("\""));
		return xsrfToken;
	}
	
	public String getVideoId(String url)
	{
		url=url.substring(url.indexOf("v=")+2);
		if(url.contains("?"))
		{
			url=url.substring(0,url.indexOf("?"));
		}
		return url;
	}
	
	public int getPeopleSubscribed(Document doc)
	{
		return Integer.parseInt(doc.select(".yt-subscriber-count").text().replace(",", ""));
	}
	
	public int getPeopleLiked(Document doc)
	{
		return Integer.parseInt(doc.select("button.like-button-renderer-like-button-unclicked span").text().replace(",", ""));
	}
	
	public int getPeopleDisliked(Document doc)
	{
		return Integer.parseInt(doc.select("button.like-button-renderer-dislike-button-unclicked span").text().replace(",", ""));
	}

}
