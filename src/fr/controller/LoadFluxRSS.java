package fr.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import fr.jeuxvideo.Dialog;
import fr.metier.Item;
import model.RSSHandler;

public class LoadFluxRSS extends AsyncTask<String, String, List<Item>>{

	private Activity activity;
	private AlertDialog alertDialog;

	public LoadFluxRSS(Activity act) {
		this.activity  = act;
	}
	public List<Item> loadFlux(final String url)
	{
		if(new TestConnection(this.activity).isNetworkAvailable())
		{
			try {
				return loadXmlFromNetwork(url);
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			}
		}
		else{
			createDialog(url);
		}
		return null;
	}

	private void createDialog(final String url) {

		final Dialog d = new Dialog(activity, "Problème Connexion", "Pour obtenir les informations"
				+ " de JeuxVidéo.com, il faut maintenant se connecter à internet.", true);
		d.setPositiveButton("Rééssayer", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				loadFlux(url);
			}
		}).setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				activity.finish();
			}
		});
		if(alertDialog == null){
			activity.runOnUiThread(new Runnable() {
				@Override
				public void run() {
					alertDialog  = d.create();
					if(alertDialog != null && !alertDialog.isShowing()){
						alertDialog.show();
					}				
				}
			});
		}
	}
	public List<Item> loadXmlFromNetwork(String urlString) throws XmlPullParserException, IOException, ParserConfigurationException, SAXException {
		return downloadUrl(urlString);  
	}


	private List<Item> downloadUrl(String urlString) throws IOException, ParserConfigurationException, SAXException {
		URL url= new URL(urlString);
		SAXParserFactory factory =SAXParserFactory.newInstance();
		SAXParser parser=factory.newSAXParser();
		XMLReader xmlreader=parser.getXMLReader();
		RSSHandler theRSSHandler=new RSSHandler();
		xmlreader.setContentHandler(theRSSHandler);
		InputSource is=new InputSource(url.openStream());
		xmlreader.parse(is);
		return theRSSHandler.getFeed();
	}
	@Override
	protected List<Item> doInBackground(String... params) {
		return loadFlux(params[0]);
	}

}
