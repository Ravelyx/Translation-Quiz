import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * Klassens huvuduppgift �r att l�sa av filen dict.txt som har glosorna och synonymerna.
 * D�refter skapas en string ArrayList. Siffra best�mmer vilket inneh�ll fr�n dict.txt
 * som l�ggs in i ArrayListen. Slutligen konverteras ArrayListen till en String Array
 * och blir returned.
 */
public class Dictionary {
	
	/*
	 * Metoden skapar 1 scanner och en ArrayList, stoppar in glosor i den, konverterar
	 * ArrayListen till en String Array och return den.
	 */
	public String[] fileToArray(int language) throws FileNotFoundException {
		
		/*
		 * F�r att f� programmet att k�ra r�tt i Eclipse m�ste path
		 * till readFile s�ttas till absolute filepath d� Eclipse
		 * k�r med en relative filepath. Utanf�r Eclipse (command prompt,
		 * terminal etc.) borde det g� att k�ra programmet utan att �ndra p� n�got.
		 */
		Scanner readFile = new Scanner(new FileReader("dict.txt"));
		
		List<String> outputList = new ArrayList<String>();
		
		while (readFile.hasNext()) {
			
			/*
			 * Siffra 1 som input i fileToArray leder till att mittersta
			 * str�ngen fr�n dict.txt importeras. Om man tittar p� dict.txt ser man
			 * att den �r skriven p� ett s�dant s�tt att den mittersta glosan
			 * alltid �r p� engelska i v�rt fall.
			 * readFile.next() hoppar till n�sta str�ng i filen. 
			 */
			if (language == 1) {
				readFile.next();
				outputList.add(readFile.next());
				readFile.next();
			}
			/*
			 * Siffra 2 som input tar alltid in f�rsta str�ngen och hoppar sedan tv�
			 * str�ngar med hj�lp av readFile.next().
			 */
			else if (language == 2) {
				outputList.add(readFile.next());
				readFile.next();
				readFile.next();
			}
			
			/*
			 * Siffra 3 tar in sista str�ngen (str�ng 3) i dict.txt.
			 */
			else if (language == 3) {
				readFile.next();
				readFile.next();
				outputList.add(readFile.next());
			}
			
		}
		readFile.close();
		
		/*
		 * Ifall man v�ljer siffra 3 som input i fileToArray och ett ord saknar
		 * en synonym (d.v.s s�tts som NONE i dict.txt) s�tter programmet automatiskt
		 * ordet till gibberish.
		 */
		if (language == 3) {
			for (int i = 0; i < outputList.size(); i++) {
				if (outputList.get(i).equalsIgnoreCase("NONE")) {
					outputList.set(i, "1@�$�6{[]}6@1�129!3%)1%)6");
				}
			}
		}
		
		/*
		 * Konvertering fr�n ArrayList till String Array.
		 */
		String[] outputArray = outputList.toArray(new String[0]);

		return outputArray;
		
	}
	
}
