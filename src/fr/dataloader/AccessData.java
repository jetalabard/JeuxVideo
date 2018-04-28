package fr.dataloader;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import fr.jeuxvideo.about.About;
import fr.metier.Choice;
import fr.metier.Console;
import fr.metier.Flux;
import fr.metier.Maj;

public class AccessData implements Requete{

	private SQLiteDatabase dataBase;

	private static AccessData instance;

	private AccessData(Activity context) {
		dataBase = DataBase.getInstance(context).getDB();
	}

	public static AccessData getInstance(Activity context)
	{
		if(instance == null)
			instance = new AccessData(context);
		return instance;
	}
	@Override
	public About loadAbout() {
		SQLiteDatabase db = dataBase;
		String query = "SELECT * FROM About";
		Cursor cursor = db.rawQuery(query, null);
		About about = null;
		if (cursor.moveToFirst()) {
			try {
				do {
					about = new About(
							cursor.getString(cursor.getColumnIndex("IDEA")),
							cursor.getString(cursor.getColumnIndex("DATA")),
							cursor.getString(cursor.getColumnIndex("URL_DATA")),
							cursor.getString(cursor.getColumnIndex("URL_FACEBOOK")),
							cursor.getString(cursor.getColumnIndex("MESS_SITE")),
							cursor.getString(cursor.getColumnIndex("URL_SITE")),
							cursor.getString(cursor.getColumnIndex("WHERE_DATA")));
				} while (cursor.moveToNext()) ;
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				cursor.close();
			}
		}

		return about;
	}
	
	@Override
	public Maj loadMaj() {
		SQLiteDatabase db = dataBase;
		String query = "SELECT * FROM MAJ";
		Cursor cursor = db.rawQuery(query, null);
		Maj maj = null;
		if (cursor.moveToFirst()) {
			try {
				do {
					maj = new Maj(
							cursor.getString(cursor.getColumnIndex("TEXT")),
							cursor.getString(cursor.getColumnIndex("BUTTON")));
				} while (cursor.moveToNext()) ;
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				cursor.close();
			}
		}

		return maj;
	}

	@Override
	public List<Choice> loadChoices() {
		SQLiteDatabase db = dataBase;
		String query = "SELECT * FROM choice";
		Cursor cursor = db.rawQuery(query, null);
		List<Choice> list = null;
		if (cursor.moveToFirst()) {
			list= new ArrayList<Choice>();
			try {
				do {

					list.add(new Choice(
							loadFluxByIdChoice(cursor.getInt(cursor.getColumnIndex("ID"))),
							cursor.getString(cursor.getColumnIndex("TITLE")),
							cursor.getString(cursor.getColumnIndex("SUBTITLE")),
							cursor.getString(cursor.getColumnIndex("INTITULE")),
							cursor.getString(cursor.getColumnIndex("IMAGE"))));
				} while (cursor.moveToNext()) ;
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				cursor.close();
			}
		}

		return list;
	}

	private List<Flux> loadFluxByIdChoice(int idChoice) {
		SQLiteDatabase db = dataBase;
		String query = "SELECT * FROM flux where choice=?";
		Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(idChoice)});
		List<Flux> list = null;
		if (cursor.moveToFirst()) {
			list= new ArrayList<Flux>();
			try {
				do {

					list.add(new Flux(
							loadConsoleById(cursor.getInt(cursor.getColumnIndex("CONSOLE"))),
							cursor.getString(cursor.getColumnIndex("URL"))));
				} while (cursor.moveToNext()) ;
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				cursor.close();
			}
		}

		return list;
	}

	private Console loadConsoleById(int idConsole) {
		SQLiteDatabase db = dataBase;
		String query = "SELECT * FROM console where ID=?";
		Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(idConsole)});
		Console console = null;
		if (cursor.moveToFirst()) {
			try {
				do {

					console= new Console(
							cursor.getString(cursor.getColumnIndex("NAME")),
							cursor.getString(cursor.getColumnIndex("IMAGE")));
				} while (cursor.moveToNext()) ;
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				cursor.close();
			}
		}

		return console;
	}
}
