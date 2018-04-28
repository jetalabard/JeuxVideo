package fr.dataloader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import android.os.Environment;

public class LoadSQLCommand{


	public List<String> execute(String file) {

		List<String> result = null;
		result = loadCommand(file);
		return result;
	}

	private List<String> loadCommand(String nameFile)
	{
		List<String> listeCommand = new ArrayList<String>();
		BufferedReader reader = null;
		File file = new File(
				Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/"+nameFile);
		try {
			reader = new BufferedReader(
					new InputStreamReader(new FileInputStream(file), "ISO-8859-1"));

			String mLine;
			while ((mLine = reader.readLine()) != null) {
				listeCommand.add(mLine);
			}
		} catch (final IOException e) {
			e.printStackTrace();

		} finally {
			if(reader != null){
				try {
					reader.close();
				} catch (final IOException e) {
					e.printStackTrace();
				}
			}
		}

		return listeCommand;

	}


}
