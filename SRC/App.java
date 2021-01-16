/*
 * Författare: Safin Lunest
 * Datum: 2020-06-10
 * Uppgift: Laboration 4
 * Kurs: 2IS233
*/

import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JOptionPane;

/*
 * Klassen skapar relevanta variabler och kallar en annan klass (dictionaryImport)
 * som har i uppgift att läsa en fil och skapa arrays för 2 stycken språk och synonym.
 * VocabularyTest-klassens huvuduppgift blir sedan att hålla igång programmet med en while loop
 * samtidigt som den jämför användarens input med indexar från språk 1, språk 2 och synonym.
 * Vid matchande av användares input och index från språk som efterfrågas av programmet
 * får användaren poäng. Efter 10 frågor stängs while loop av och relevant statistik visas.
 */
public class App {
	
	/*
	 * Global konstant som ska underlätta vid byte av språk ifall man vill använda andra
	 * språk istället (japanska till engelska, engelska till tyska osv.)
	 */
	private static final String TRANSLATE_TO = "engelska";
	
	/*
	 * Programmet startas här.
	 */
	public static void main (String[] Args) throws FileNotFoundException {
		
		translationQuiz();
		
	}
	
	/*
	 * Skapar 1 scanner, 3 integervariabler, 1 stringvariabel, och 3 arrays.
	 * Instruktioner spelas upp. Klassen dictionaryImport kallas in för att stoppa in
	 * glosor i de olika arrayen. Whieloop spelas upp som håller programmet igång.
	 * Ena arrayen används för att visa glosorna på språk 1.
	 * Andra arrayen för språk 2 används för att jämföra med användarens input.
	 * Vid rätt svar ges poäng och efter 10 glosor stängs programmet av.
	 */
	public static void translationQuiz() throws FileNotFoundException {
		
		/*
		 * languageOne är språket man blir quizzad på, medan languageTwo
		 * är det som man ombes översätta.
		 */
		int questionCounter = 0, userScore = 0, quitApplication = 0;
		String userChoice = null;
		String[] languageOne = null, languageTwo = null, synonyms = null;
		
		instructions();
		
		/*
		 * Skapar en objekt dictionary till klassen dictionaryImport som kopplas
		 * till metoden languageImport inom den klassen.
		 * Siffrorna 1 och 2 bestämmer languageOne och languageTwo.
		 * Siffran 3 är synonymer. Beroende på siffran som inmatas delar
		 * languageImport metoden upp arrayerna på olika sätt.
		 */
		Dictionary dictionaryObject = new Dictionary();
		languageOne = dictionaryObject.fileToArray(1);
		languageTwo = dictionaryObject.fileToArray(2);
		synonyms = dictionaryObject.fileToArray(3);
		
		while (quitApplication != 1) {
			for (int i = 0; i < languageOne.length; i++) {
				userChoice = JOptionPane.showInputDialog(
						null,
							"Vad är översättningen av " + languageTwo[i] + " på " + TRANSLATE_TO + "?",
								"Översättning",
									JOptionPane.QUESTION_MESSAGE);
				
				if (userChoice == null) {
					JOptionPane.showMessageDialog(
							null,
								"Du hade totalt " + userScore + " rätt av " + questionCounter + " ord.",
									"Antal rätt",
											JOptionPane.INFORMATION_MESSAGE);
					JOptionPane.showMessageDialog(
							null, 
								"Programmet avslutas! Hejdå!\n",
									"Programslut", 
										JOptionPane.INFORMATION_MESSAGE);
					System.exit(0);
				}
				
				if (userChoice.equalsIgnoreCase("Q")) {
					quitApplication = 1;
					
					JOptionPane.showMessageDialog(
							null,
								"Du hade totalt " + userScore + " rätt av " + questionCounter + " ord.",
									"Antal rätt",
										JOptionPane.INFORMATION_MESSAGE);
					JOptionPane.showMessageDialog(
							null, 
								"Programmet avslutas! Hejdå!\n", 
									"Programslut", 
										JOptionPane.INFORMATION_MESSAGE);
					break;
				} else if (userChoice.equalsIgnoreCase(languageOne[i]) || userChoice.equalsIgnoreCase(synonyms[i])) {
					userScore++;
					questionCounter++;
					JOptionPane.showMessageDialog(
							null, 
								"Rätt!\n" + userScore + " rätt av " + questionCounter + " ord.\n", 
									"Svarsresultat", 
										JOptionPane.INFORMATION_MESSAGE);
				} else {
					questionCounter++;
					
					/*
					 * Kollar om majoriteten av ordet är rätt eller fel. Spelar upp if eller
					 * else statement beroende på svaret.
					 */
					if (userChoice.contains(languageOne[i].subSequence(0, (languageOne[i].length()/2+1))) 
							|| userChoice.contains(synonyms[i].subSequence(0, synonyms[i].length()/2+1))) {
						
						JOptionPane.showMessageDialog(
								null,
									"Nästan korrekt. Rätt svar är " + languageOne[i] + ".\n" + userScore + " rätt av " + questionCounter + " ord.\n",
										"Svarsresultat", 
											JOptionPane.WARNING_MESSAGE);
					
					} else {
						JOptionPane.showMessageDialog(
								null, 
									"Fel! Korrekt svar är " + languageOne[i] + ".\n" + userScore + " rätt av " + questionCounter + " ord.\n",
										"Svarsresultat",
											JOptionPane.ERROR_MESSAGE);
					}
				}
				
				/*
				 * Denna rad kontrolleras efter varje jämförelse av användarinmatning och
				 * glosa. När tionde frågan besvarats stänger programmet av sig själv.
				 */
				if (questionCounter == 10) {
					JOptionPane.showMessageDialog(
							null,
								"Du svarade på totalt " + questionCounter + " glosor och hade " + userScore + " rätt.\nProgrammet avslutas!",
									"Slutresultat",
										JOptionPane.INFORMATION_MESSAGE);
					quitApplication = 1;	
					break;
				}
			}
		}
	}
	
	/*
	 * Spelar upp instruktioner med hjälp av denna metod.
	 */
	public static void instructions() {
		JOptionPane.showMessageDialog(
				null,
				"Välkommen till glosövningsprogrammet!\n"
						+ "Du kommer att få ett ord på svenska som du sedan kommer behöva översätta till engelska.\n"
							+ "Du kan avsluta programmet när som helst genom att skriva in 'q'", 
								"Välkomstmeddelande",
									JOptionPane.INFORMATION_MESSAGE);
	}
}

