package model;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import fr.metier.Item;

public class RSSHandler extends DefaultHandler {

	private static final String ITEM = "item";
	private static final String TITLE = "title";
	private static final String LINK = "link";
	private static final String DESCRIPTION = "description";
	private static final String SUBTITLE ="itunes:subtitle";
	private static final String AUTHOR="itunes:author";
	private static final String ENCLOSURE="enclosure";
	private static final String SUMMARY="itunes:summary";
	private static final String DURATION="itunes:duration";
	private static final String KEYWORDS="itunes:keywords";
	private static final String PUBDATE = "pubDate";
	private static final String GUID = "guid";
	private static final String CATEGORY = "category";
	private boolean inITEM ;
	private boolean inTITLE ;
	private boolean inLINK;
	private boolean inDESCRIPTION ;
	private boolean inSUBTITLE ;
	private boolean inAUTHOR;
	private boolean inENCLOSURE;
	private boolean inSUMMARY;
	private boolean inDURATION;
	private boolean inKEYWORDS;
	private boolean inPUBDATE;
	private boolean inGUID;
	private boolean inCATEGORY;
	private boolean inCHANNEL;
	private List<Item> items;
	private Item curentItem;

	/**
	 * 
	 */
	private StringBuffer buffer = null; 


	@Override
	public void startElement(String uri, String localName, 
			String qName, Attributes attributes) throws SAXException{ 
		if(qName.equals("channel")){ 
			items = new ArrayList<Item>(); 
			inCHANNEL = true;
		}else if(qName.equals(ITEM)){ 
			curentItem = new Item(); 
			inITEM = true; 
		}else { 
			if(inITEM == true){
				buffer = new StringBuffer(); 
				if(qName.equals(TITLE)){
					inTITLE =true;
				}
				else if(qName.equals(LINK)){
					inLINK = true;
				}
				else if(qName.equals(DESCRIPTION)){
					inDESCRIPTION =true;
				}
				else if(qName.equals(SUBTITLE)){
					inSUBTITLE = true;
				}
				else if(qName.equals(AUTHOR)){
					inAUTHOR = true;
				}
				else if(qName.equals(ENCLOSURE)){
					String valueis=attributes.getValue("url");
					curentItem.setEnclosure(valueis);
					inENCLOSURE =true;
				}
				else if(qName.equals(SUMMARY)){
					inSUMMARY =true;
				}
				else if(qName.equals(DURATION)){
					inDURATION = true;
				}
				else if(qName.equals(KEYWORDS)){
					inKEYWORDS = true;
				}
				else if(qName.equals(PUBDATE)){
					inPUBDATE = true;
				}
				else if(qName.equals(GUID)){
					inGUID = true;
				}
				else if(qName.equals(CATEGORY)){
					inCATEGORY = true;
				}else{ 
					//throw new SAXException("Balise "+qName+" inconnue."); 
				}
			}
		} 
	}
	/**
	 * 
	 */
	@Override
	public void endElement(String uri, String localName, String qName) 
			throws SAXException{ 
		if(qName.equals("channel")){ 
			inCHANNEL = false;
		}else if(qName.equals(ITEM)){ 
			items.add(curentItem);
			curentItem = null; 
			inITEM = false; 
		}else { 
			if(inITEM == true){
				if(qName.equals(TITLE)){
					curentItem.setTitle(buffer.toString());
					inTITLE =false;
				}
				else if(qName.equals(LINK)){
					curentItem.setLink(buffer.toString());
					inLINK = false;
				}
				else if(qName.equals(DESCRIPTION)){
					curentItem.setDescription(buffer.toString());
					inDESCRIPTION =false;
				}
				else if(qName.equals(SUBTITLE)){
					curentItem.setSubtitle(buffer.toString());
					inSUBTITLE = false;
				}
				else if(qName.equals(AUTHOR)){
					curentItem.setAuthor(buffer.toString());
					inAUTHOR = false;
				}
				else if(qName.equals(ENCLOSURE)){
					//curentItem.setEnclosure(buffer.toString());
					inENCLOSURE =false;
				}
				else if(qName.equals(SUMMARY)){
					curentItem.setSummary(buffer.toString());
					inSUMMARY =false;
				}
				else if(qName.equals(DURATION)){
					curentItem.setDuration(buffer.toString());
					inDURATION = false;
				}
				else if(qName.equals(KEYWORDS)){
					curentItem.setKeyWords(buffer.toString());
					inKEYWORDS = false;
				}
				else if(qName.equals(PUBDATE)){
					curentItem.setPubDate(buffer.toString());
					inPUBDATE = false;
				}
				else if(qName.equals(GUID)){
					curentItem.setGuid(buffer.toString());
					inGUID = false;
				}
				else if(qName.equals(CATEGORY)){
					curentItem.setCategory(buffer.toString());
					inCATEGORY = false;
				}else{ 
					//throw new SAXException("Balise "+qName+" inconnue."); 
				}
				buffer = null; 
			}
		}
	} 
	/**
	 * 
	 */
	@Override
	public void characters(char[] ch,int start, int length) 
			throws SAXException{ 
		String lecture = new String(ch,start,length); 
		if(buffer != null) buffer.append(lecture);        
	} 


	public List<Item> getFeed() {
		return items;
	}


}
