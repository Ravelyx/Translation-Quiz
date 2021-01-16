/*
 * F�rfattare: Safin Lunest
 * Datum: 2020-06-10
 * Uppgift: Laboration 4
 * Kurs: 2IS233
*/

import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JOptionPane;

/*
 * Klassen skapar relevanta variabler och kallar en annan klass (dictionaryImport)
 * som har i uppgift att l�sa en fil och skapa arrays f�r 2 stycken spr�k och synonym.
 * VocabularyTest-klassens huvuduppgift blir sedan att h�lla ig�ng programmet med en while loop
 * samtidigt som den j�mf�r anv�ndarens input med indexar fr�n spr�k 1, spr�k 2 och synonym.
 * Vid matchande av anv�ndares input och index fr�n spr�k som efterfr�gas av programmet
 * f�r anv�ndaren po�ng. Efter 10 fr�gor st�ngs while loop av och relevant statistik visas.
 */
public class App {
	
	/*
	 * Global konstant som ska underl�tta vid byte av spr�k ifall man vill anv�nda andra
	 * spr�k ist�llet (japanska till engelska, engelska till tyska osv.)
	 */
	private static final String TRANSLATE_TO = "engelska";
	
	/*
	 * Programmet startas h�r.
	 */
	public static void main (String[] Args) throws FileNotFoundException {
		
		translationQuiz();
		
	}
	
	/*
	 * Skapar 1 scanner, 3 integervariabler, 1 stringvariabel, och 3 arrays.
	 * Instruktioner spelas upp. Klassen dictionaryImport kallas in f�r att stoppa in
	 * glosor i de olika arrayen. Whieloop spelas upp som h�ller programmet ig�ng.
	 * Ena arrayen anv�nds f�r att visa glosorna p� spr�k 1.
	 * Andra arrayen f�r spr�k 2 anv�nds f�r att j�mf�ra med anv�ndarens input.
	 * Vid r�tt svar ges po�ng och efter 10 glosor st�ngs programmet av.
	 */
	public static void translationQuiz() throws FileNotFoundException {
		
		/*
		 * languageOne �r spr�ket man blir quizzad p�, medan languageTwo
		 * �r det som man ombes �vers�tta.
		 */
		int questionCounter = 0, userScore = 0, quitApplication = 0;
		String userChoice = null;
		String[] languageOne = null, languageTwo = null, synonyms = null;
		
		instructions();
		
		/*
		 * Skapar en objekt dictionary till klassen dictionaryImport som kopplas
		 * till metoden languageImport inom den klassen.
		 * Siffrorna 1 och 2 best�mmer languageOne och languageTwo.
		 * Siffran 3 �r synonymer. Beroende p� siffran som inmatas delar
		 * languageImport metoden upp arrayerna p� olika s�tt.
		 */
		Dictionary dictionaryObject = new Dictionary();
		languageOne = dictionaryObject.fileToArray(1);
		languageTwo = dictionaryObject.fileToArray(2);
		synonyms = dictionaryObject.fileToArray(3);
		
		while (quitApplication != 1) {
			for (int i = 0; i < languageOne.length; i++) {
				userChoice = JOptionPane.showInputDialog(
						null,
							"Vad �r �vers�ttningen av " + languageTwo[i] + " p� " + TRANSLATE_TO + "?",
								"�vers�ttning",
									JOptionPane.QUESTION_MESSAGE);
				
				if (userChoice == null) {
					JOptionPane.showMessageDialog(
							null,
								"Du hade totalt " + userScore + " r�tt av " + questionCounter + " ord.",
									"Antal r�tt",
											JOptionPane.INFORMATION_MESSAGE);
					JOptionPane.showMessageDialog(
							null, 
								"Programmet avslutas! Hejd�!\n",
									"Programslut", 
										JOptionPane.INFORMATION_MESSAGE);
					System.exit(0);
				}
				
				if (userChoice.equalsIgnoreCase("Q")) {
					quitApplication = 1;
					
					JOptionPane.showMessageDialog(
							null,
								"Du hade totalt " + userScore + " r�tt av " + questionCounter + " ord.",
									"Antal r�tt",
										JOptionPane.INFORMATION_MESSAGE);
					JOptionPane.showMessageDialog(
							null, 
								"Programmet avslutas! Hejd�!\n", 
									"Programslut", 
										JOptionPane.INFORMATION_MESSAGE);
					break;
				} else if (userChoice.equalsIgnoreCase(languageOne[i]) || userChoice.equalsIgnoreCase(synonyms[i])) {
					userScore++;
					questionCounter++;
					JOptionPane.showMessageDialog(
							null, 
								"R�tt!\n" + userScore + " r�tt av " + questionCounter + " ord.\n", 
									"Svarsresultat", 
										JOptionPane.INFORMATION_MESSAGE);
				} else {
					questionCounter++;
					
					/*
					 * Kollar om majoriteten av ordet �r r�tt eller fel. Spelar upp if eller
					 * else statement beroende p� svaret.
					 */
					if (userChoice.contains(languageOne[i].subSequence(0, (languageOne[i].length()/2+1))) 
							|| userChoice.contains(synonyms[i].subSequence(0, synonyms[i].length()/2+1))) {
						
						JOptionPane.showMessageDialog(
								null,
									"N�stan korrekt. R�tt svar �r " + languageOne[i] + ".\n" + userScore + " r�tt av " + questionCounter + " ord.\n",
										"Svarsresultat", 
											JOptionPane.WARNING_MESSAGE);
					
					} else {
						JOptionPane.showMessageDialog(
								null, 
									"Fel! Korrekt svar �r " + languageOne[i] + ".\n" + userScore + " r�tt av " + questionCounter + " ord.\n",
										"Svarsresultat",
											JOptionPane.ERROR_MESSAGE);
					}
				}
				
				/*
				 * Denna rad kontrolleras efter varje j�mf�relse av anv�ndarinmatning och
				 * glosa. N�r tionde fr�gan besvarats st�nger programmet av sig sj�lv.
				 */
				if (questionCounter == 10) {
					JOptionPane.showMessageDialog(
							null,
								"Du svarade p� totalt " + questionCounter + " glosor och hade " + userScore + " r�tt.\nProgrammet avslutas!",
									"Slutresultat",
										JOptionPane.INFORMATION_MESSAGE);
					quitApplication = 1;	
					break;
				}
			}
		}
	}
	
	/*
	 * Spelar upp instruktioner med hj�lp av denna metod.
	 */
	public static void instructions() {
		JOptionPane.showMessageDialog(
				null,
				"V�lkommen till glos�vningsprogrammet!\n"
						+ "Du kommer att f� ett ord p� svenska som du sedan kommer beh�va �vers�tta till engelska.\n"
							+ "Du kan avsluta programmet n�r som helst genom att skriva in 'q'", 
								"V�lkomstmeddelande",
									JOptionPane.INFORMATION_MESSAGE);
	}
}

