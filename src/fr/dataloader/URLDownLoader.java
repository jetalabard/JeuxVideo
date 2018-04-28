package fr.dataloader;

import java.util.ArrayList;
import java.util.List;

public class URLDownLoader {
	
	private static String[] urls={"https://www.dropbox.com/s/x83sx22ew5xsi8w/create.txt?dl=1",
    		"https://www.dropbox.com/s/71os1wmpdgum0hb/insert.txt?dl=1"};
	
	public static String[] getUrls()
	{
		return urls;
	}
	
	public static List<String> getNameFileOfUrl()
	{
		List<String> ret = new ArrayList<String>();
		for(String url :urls)
		{
			String[] split = url.split("/");
			String name = split[split.length-1];
			ret.add(name.substring(0, 10));
		}
		return ret;
	}
	
}
