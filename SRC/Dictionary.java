import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * Klassens huvuduppgift är att läsa av filen dict.txt som har glosorna och synonymerna.
 * Därefter skapas en string ArrayList. Siffra bestämmer vilket innehåll från dict.txt
 * som läggs in i ArrayListen. Slutligen konverteras ArrayListen till en String Array
 * och blir returned.
 */
public class Dictionary {
	
	/*
	 * Metoden skapar 1 scanner och en ArrayList, stoppar in glosor i den, konverterar
	 * ArrayListen till en String Array och return den.
	 */
	public String[] fileToArray(int language) throws FileNotFoundException {
		
		/*
		 * För att få programmet att köra rätt i Eclipse måste path
		 * till readFile sättas till absolute filepath då Eclipse
		 * kör med en relative filepath. Utanför Eclipse (command prompt,
		 * terminal etc.) borde det gå att köra programmet utan att ändra på något.
		 */
		Scanner readFile = new Scanner(new FileReader("dict.txt"));
		
		List<String> outputList = new ArrayList<String>();
		
		while (readFile.hasNext()) {
			
			/*
			 * Siffra 1 som input i fileToArray leder till att mittersta
			 * strängen från dict.txt importeras. Om man tittar på dict.txt ser man
			 * att den är skriven på ett sådant sätt att den mittersta glosan
			 * alltid är på engelska i vårt fall.
			 * readFile.next() hoppar till nästa sträng i filen. 
			 */
			if (language == 1) {
				readFile.next();
				outputList.add(readFile.next());
				readFile.next();
			}
			/*
			 * Siffra 2 som input tar alltid in första strängen och hoppar sedan två
			 * strängar med hjälp av readFile.next().
			 */
			else if (language == 2) {
				outputList.add(readFile.next());
				readFile.next();
				readFile.next();
			}
			
			/*
			 * Siffra 3 tar in sista strängen (sträng 3) i dict.txt.
			 */
			else if (language == 3) {
				readFile.next();
				readFile.next();
				outputList.add(readFile.next());
			}
			
		}
		readFile.close();
		
		/*
		 * Ifall man väljer siffra 3 som input i fileToArray och ett ord saknar
		 * en synonym (d.v.s sätts som NONE i dict.txt) sätter programmet automatiskt
		 * ordet till gibberish.
		 */
		if (language == 3) {
			for (int i = 0; i < outputList.size(); i++) {
				if (outputList.get(i).equalsIgnoreCase("NONE")) {
					outputList.set(i, "1@£$€6{[]}6@1€129!3%)1%)6");
				}
			}
		}
		
		/*
		 * Konvertering från ArrayList till String Array.
		 */
		String[] outputArray = outputList.toArray(new String[0]);

		return outputArray;
		
	}
	
}
