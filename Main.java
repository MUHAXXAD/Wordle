//Programmer: Muhammad Zaid
//Last Edited Date: 2022-11-20
//This program asks users for word guesses in order to find the correct word. With the use of colour coding, users will be informed as to whether the letters in their guesses were in the word but in the incorrect position, in the word but not in that position, or not in the word. After the game is finished, the user will be  given the option to quit the program or play again.


import java.util.*;
import java.io.*;
import java.util.Scanner;
class Main {
  // These are helpers to change the text color
public static final String ANSI_RESET = "\u001B[0m";
public static final String ANSI_RED = "\u001B[31m";
public static final String ANSI_GREEN = "\u001B[32m";
public static final String ANSI_YELLOW = "\u001B[33m";
public static final String ANSI_BLUE = "\u001B[34m";
public static final String ANSI_WHITE = "\u001B[37m";
  
  public static void main(String[] args)throws InterruptedException, IOException  {
    
      Scanner keyboard = new Scanner(System.in);
      int menuInput = 0;
      int counter = 1;
      int userResp = 0;
      int userResp2 = 0;

     // Wordle ASCII artwork(Title)
    System.out.println("\n __          __" + "____"  + "  _____" + "  _____  _" + "      ______  ");
    
    System.out.println(" \\ \\        / /" + " __ \\" + "|  __ \\" + "|  __ \\| |" + "    |  ____| ");
    
    System.out.println("  \\ \\  /\\  / /"  +  " |  |" + " | |__)"+ " | |  | | |"+ "    | |__    ");
    
    System.out.println("   \\ \\/  \\/ /" + "| |  |" + " |  _  /" + "| |  | | |" + "    |  __|   ");
    
    System.out.println("    \\  /\\  / " + "| |__|" + " | | \\ \\" + "| |__| | |____" + "| |____  ");
    
    System.out.println("     \\/  \\/   " + "\\____/" + "|_|  \\_\\" + "_____/|______" + "|______| ");
     Thread.sleep(1000);
    System.out.println("\n\t\t\t\tWelcome to Wordle!");

    //Displays menu system
    Thread.sleep(2000);
    System.out.println("\033[H\033[2J");
    System.out.println("                                      _____ _ \n                                     |  _  | |___ _ _ \n                                     |   __| | .'| | |\n                                     |__|  |_|__,|_  |\n                                                 |___|");
    System.out.println("");
    System.out.println("                  _____ _____ _ _ _    _____ _____    _____ __    _____ __ __\n                 |  |  |     | | | |  |_   _|     |  |  _  |  |  |  _  |  |  |\n                 |     |  |  | | | |    | | |  |  |  |   __|  |__|     |_   _|\n                 |__|__|_____|_____|    |_| |_____|  |__|  |_____|__|__| |_|");
    System.out.println("");
    System.out.println("                                   _____ __ __ _____ _____\n                                  |   __|  |  |     |_   _|\n                                  |   __|-   -|-   -| | |\n                                  |_____|__|__|_____| |_|");
    System.out.println("");
    Thread.sleep(1000);
    System.out.print("\nPlease type the "  + "number" + " corresponding to the section you wish to proceed to(type 1 to play): ");
    menuInput = keyboard.nextInt();

    // if user enters an invalid input
      while (menuInput != 1 && menuInput != 2 && menuInput != 3) {
          
          // following two lines is code borrowed from quora.com that deletes the content of a single line
          System.out.print(String.format("\033[%dA", counter));
          System.out.print("\033[2K");
          System.out.print("Please type the number corresponding to the section you wish to proceed to(type 1 to play): ");
          menuInput = keyboard.nextInt();
      }

      //User selects to play
     if (menuInput == 1){
          System.out.print("\033[H\033[2J");
          Thread.sleep(1000);
          do{
                   //Load all possible 5 letter words into an arrayList. Use that to get random word and to check if guess is valid.
              ArrayList <String> allWords = new ArrayList <String>();
              Scanner scan = new Scanner(new File("allWords.txt"));
          
              while (scan.hasNext())
              {
                String next = scan.nextLine();
                next = next.replace("\"", "");
                next = next.replace(",", "");
                next = next.trim();
                allWords.add(next);
              }
              // Sort and remove duplicates from list.
              sort(allWords);
              allWords = removeDuplicates(allWords);
              
              // Write words to txt file. 
              try {
                File words = new File("words.txt");
                words.createNewFile();
                FileWriter myWriter = new FileWriter("words.txt");
                for (String word : allWords)
                {
                  myWriter.write(word+"\n");
                }
                // Wrties to the file
                myWriter.close();
            
              } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
              }
      
      
               // GamePlay
          // 1. Get a random word from the list
          String mystery = getRandomWord(allWords);
      
            System.out.println("\nThe word has been chosen. You may start guessing what the word is. Good Luck!");
      
             String guess = "";
          scan = new Scanner(System.in);
      
          // (!guess.equals(mystery))
          for(int count = 1; count < 7; count++)
          {
            System.out.println("Your attempts: " + count + "/6");
            guess = scan.nextLine().toLowerCase();
            if (isGuessValid(guess, mystery, allWords)) 
            {
              checkGuess(guess, mystery);
            }
            if(guess.equals(mystery))
            {
              System.out.println("Congrats you guessed it correctly!");
              counter = 7;
            }
          }
          if(!guess.equals(mystery))
            {
               System.out.println("Your tries have finished! The correct answer was:" + mystery);
          }
             
          //Asking the if they want to play
         System.out.println("Do you want to play again? Please enter 1 to play again or 2 to exit.");
         userResp= keyboard.nextInt();
      
        }while(userResp == 1);
      
          
          if (userResp == 2){
             System.out.print("\033[H\033[2J");
              Thread.sleep(1000);
      System.out.println("⣠⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⣠⣤⣶⣶\n⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⢰⣿⣿⣿⣿\n⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⣀⣀⣾⣿⣿⣿⣿\n⣿⣿⣿⣿⣿⡏⠉⠛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⣿\n⣿⣿⣿⣿⣿⣿⠀⠀⠀⠈⠛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠛⠉⠁⠀⣿\n⣿⣿⣿⣿⣿⣿⣧⡀⠀⠀⠀⠀⠙⠿⠿⠿⠻⠿⠿⠟⠿⠛⠉⠀⠀⠀⠀⠀⣸⣿\n⣿⣿⣿⣿⣿⣿⣿⣷⣄⠀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣿⣿\n⣿⣿⣿⣿⣿⣿⣿⣿⣿⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⣴⣿⣿⣿⣿\n⣿⣿⣿⣿⣿⣿⣿⣿⡟⠀⠀⢰⣹⡆⠀⠀⠀⠀⠀⠀⣭⣷⠀⠀⠀⠸⣿⣿⣿⣿\n⣿⣿⣿⣿⣿⣿⣿⣿⠃⠀⠀⠈⠉⠀⠀⠤⠄⠀⠀⠀⠉⠁⠀⠀⠀⠀⢿⣿⣿⣿\n⣿⣿⣿⣿⣿⣿⣿⣿⢾⣿⣷⠀⠀⠀⠀⡠⠤⢄⠀⠀⠀⠠⣿⣿⣷⠀⢸⣿⣿⣿\n⣿⣿⣿⣿⣿⣿⣿⣿⡀⠉⠀⠀⠀⠀⠀⢄⠀⢀⠀⠀⠀⠀⠉⠉⠁⠀⠀⣿⣿⣿\n⣿⣿⣿⣿⣿⣿⣿⣿⣧⠀⠀⠀⠀⠀⠀⠀⠈⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⣿⣿\n⣿⣿⣿⣿⣿⣿⣿⣿⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿");
              System.out.println("\nThank you for playing Wordle! Have a good day!");
          }
            
    } 
    
    

    //How to play
    if(menuInput == 2){
        System.out.print("\033[H\033[2J");
        Thread.sleep(1250);
        System.out.println("\nYour goal is to" + ANSI_BLUE + " guess the Word in 6 tries" + ANSI_RESET + "\nEach guess must be a valid 5 letter word. Hit the enter button to submit.");
      
        Thread.sleep(1250);
        System.out.println("\nAfter each guess, the color of the letters will change to show how close your guess was to the word.");
      
        Thread.sleep(1250);
        System.out.println("\nIf the letter is in the word AND in the correct position, the letter is shown in "+ANSI_GREEN+"GREEN "+ANSI_RESET);
      
        Thread.sleep(1250);
        System.out.println("\nIf the letter is in the word, but in the wrong position will be shown in "+ANSI_YELLOW+"YELLOW "+ANSI_RESET);
      
        Thread.sleep(1250);
        System.out.println("\nLetters that do not appear in the word will display in WHITE.");

    //example word
      Thread.sleep(1500);
          System.out.println(ANSI_RED + "\n\n\t\t\t\tE X A M P L E" + ANSI_RESET);
          Thread.sleep(1500);
          System.out.println("\nCorrect word: \tarray");
          
          Thread.sleep(1500);
          System.out.println("\t   Guess: \tfairy");
          
          Thread.sleep(1500);
          System.out.println("\t  Output:\t" + ANSI_WHITE + "f\t\t\t" + ANSI_RESET + ANSI_YELLOW + "a\t\t\t" + ANSI_RESET  + "i\t\t\t" + ANSI_YELLOW + "r\t\t\t" + ANSI_GREEN + "y" + ANSI_RESET);


      
    //Asking the if they want to play 
   System.out.println("\n\nNow that you know rules do you want to play?  Please enter 1 to play or 2 to exit.");
   userResp2= keyboard.nextInt();

      if(userResp2 == 1){
        System.out.print("\033[H\033[2J");
        Thread.sleep(1000);
          do{
               // Load all possible 5 letter words into an arrayList. Use that to get random word and to check if guess is valid.
          ArrayList <String> allWords = new ArrayList <String>();
          Scanner scan = new Scanner(new File("allWords.txt"));
      
          while (scan.hasNext())
          {
            String next = scan.nextLine();
            next = next.replace("\"", "");
            next = next.replace(",", "");
            next = next.trim();
            allWords.add(next);
          }
          // Sort and remove duplicates from list.
          sort(allWords);
          allWords = removeDuplicates(allWords);
          
          // Write words to txt file. 
          try {
            File words = new File("words.txt");
            words.createNewFile();
            FileWriter myWriter = new FileWriter("words.txt");
            for (String word : allWords)
            {
              myWriter.write(word+"\n");
            }
            // Wrties to the file
            myWriter.close();
        
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
  
  
           // GamePlay
      // 1. Get a random word from the list
      String mystery = getRandomWord(allWords);
  
        System.out.println("\nThe word has been chosen. You may start guessing what the word is. Good Luck!");
  
         String guess = "";
      scan = new Scanner(System.in);
  
      // (!guess.equals(mystery))
      for(int count = 1; count < 7; count++)
      {
        System.out.println("Your tries: " + count + "/6");
        guess = scan.nextLine().toLowerCase();
        if (isGuessValid(guess, mystery, allWords)) 
        {
          checkGuess(guess, mystery);
        }
        if(guess.equals(mystery))
        {
          System.out.println("Congrats you guessed it correctly!");
          counter = 7;
        }
      }
      if(!guess.equals(mystery))
        {
           System.out.println("Your tries have finished! The correct answer was:" + mystery);
      }
         
      //Asking the if they want to play
     System.out.println("\nDo you want to play again? Please enter 1 to play again or 2 to exit.");
     userResp= keyboard.nextInt();
  
    }while(userResp == 1);
  
      
      if (userResp == 2){
        System.out.println("Thank you for playing Wordle! Have a good day!");
      } 
       }
    }

      //If the user doesn't want to play
      if(userResp2 == 2){
         System.out.print("\033[H\033[2J");
        Thread.sleep(1000);
System.out.println("⣠⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⣠⣤⣶⣶\n⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⢰⣿⣿⣿⣿\n⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⣀⣀⣾⣿⣿⣿⣿\n⣿⣿⣿⣿⣿⡏⠉⠛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⣿\n⣿⣿⣿⣿⣿⣿⠀⠀⠀⠈⠛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠛⠉⠁⠀⣿\n⣿⣿⣿⣿⣿⣿⣧⡀⠀⠀⠀⠀⠙⠿⠿⠿⠻⠿⠿⠟⠿⠛⠉⠀⠀⠀⠀⠀⣸⣿\n⣿⣿⣿⣿⣿⣿⣿⣷⣄⠀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣿⣿\n⣿⣿⣿⣿⣿⣿⣿⣿⣿⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⣴⣿⣿⣿⣿\n⣿⣿⣿⣿⣿⣿⣿⣿⡟⠀⠀⢰⣹⡆⠀⠀⠀⠀⠀⠀⣭⣷⠀⠀⠀⠸⣿⣿⣿⣿\n⣿⣿⣿⣿⣿⣿⣿⣿⠃⠀⠀⠈⠉⠀⠀⠤⠄⠀⠀⠀⠉⠁⠀⠀⠀⠀⢿⣿⣿⣿\n⣿⣿⣿⣿⣿⣿⣿⣿⢾⣿⣷⠀⠀⠀⠀⡠⠤⢄⠀⠀⠀⠠⣿⣿⣷⠀⢸⣿⣿⣿\n⣿⣿⣿⣿⣿⣿⣿⣿⡀⠉⠀⠀⠀⠀⠀⢄⠀⢀⠀⠀⠀⠀⠉⠉⠁⠀⠀⣿⣿⣿\n⣿⣿⣿⣿⣿⣿⣿⣿⣧⠀⠀⠀⠀⠀⠀⠀⠈⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⣿⣿\n⣿⣿⣿⣿⣿⣿⣿⣿⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿");
        System.out.println("\nThank you for playing Wordle! Have a good day!");
      }

      //If user selects to exit from menu
      if(menuInput == 3){
        System.out.print("\033[H\033[2J");
        Thread.sleep(1000);
System.out.println("⣠⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⣠⣤⣶⣶\n⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⢰⣿⣿⣿⣿\n⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⣀⣀⣾⣿⣿⣿⣿\n⣿⣿⣿⣿⣿⡏⠉⠛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⣿\n⣿⣿⣿⣿⣿⣿⠀⠀⠀⠈⠛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠛⠉⠁⠀⣿\n⣿⣿⣿⣿⣿⣿⣧⡀⠀⠀⠀⠀⠙⠿⠿⠿⠻⠿⠿⠟⠿⠛⠉⠀⠀⠀⠀⠀⣸⣿\n⣿⣿⣿⣿⣿⣿⣿⣷⣄⠀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣿⣿\n⣿⣿⣿⣿⣿⣿⣿⣿⣿⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⣴⣿⣿⣿⣿\n⣿⣿⣿⣿⣿⣿⣿⣿⡟⠀⠀⢰⣹⡆⠀⠀⠀⠀⠀⠀⣭⣷⠀⠀⠀⠸⣿⣿⣿⣿\n⣿⣿⣿⣿⣿⣿⣿⣿⠃⠀⠀⠈⠉⠀⠀⠤⠄⠀⠀⠀⠉⠁⠀⠀⠀⠀⢿⣿⣿⣿\n⣿⣿⣿⣿⣿⣿⣿⣿⢾⣿⣷⠀⠀⠀⠀⡠⠤⢄⠀⠀⠀⠠⣿⣿⣷⠀⢸⣿⣿⣿\n⣿⣿⣿⣿⣿⣿⣿⣿⡀⠉⠀⠀⠀⠀⠀⢄⠀⢀⠀⠀⠀⠀⠉⠉⠁⠀⠀⣿⣿⣿\n⣿⣿⣿⣿⣿⣿⣿⣿⣧⠀⠀⠀⠀⠀⠀⠀⠈⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⣿⣿\n⣿⣿⣿⣿⣿⣿⣿⣿⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿");
        System.out.println("\nThank you for playing Wordle. Have a great day!");
      }

  
      
    }
  
      //To check if user guess is valid or not 
  public static boolean isGuessValid(String guess, String answer, ArrayList<String> allWords) {

    if (guess.length() != answer.length())
    {
      System.out.println(guess +" is not the correct length");
      return false;
    }
    else if (!allWords.contains(guess)) {
      System.out.println(guess + " is not a word on the list of 5-letter words.");
      return false;
    }
    else {
      return true;
    }
  }

        public static void checkGuess(String guess, String answer)
  {
        // colorCode is used to display the correct colors for the user. 2 is green, 1 is yellow, 0 is gray
    int[] colorCode = {0,0,0,0,0};
    
    // answerLetters is the arrayList of the letters in the answer. We will remove a letter when it is turned green or yellow to prevent bugs*
    ArrayList <String> answerLetters = new ArrayList<String>();
    for(int i = 0; i < answer.length();i++)
      answerLetters.add(answer.substring(i,i+1));
    
    
    // Find all correct letters: Green
    for (int i = answer.length()-1; i >= 0; i--)
    {
      String guessLetter = guess.substring(i,i+1);
      String answerLetter = answer.substring(i,i+1);
      if (answerLetter.equals(guessLetter))
      {
        colorCode[i] = 2;
        // Remove letter from answerLetters
        answerLetters.remove(i);
      }
    }
    // Find yellow
    for (int i = 0; i < guess.length(); i++)
    {
      if (colorCode[i] != 2) 
      {
        String guessLetter = guess.substring(i,i+1);
        // Check every letter in answer letters and see if it is still in it.
        for (int j = answerLetters.size()-1; j >=0; j--)
        {
          if (guessLetter.equals(answerLetters.get(j)))
          {
            // Update colorCode
            colorCode[i] = 1;
            // Remove yellow letter from list, to prevent finding it twice
            answerLetters.remove(j);
            // Break to prevent finiding it twice in one loop
            break;
          }
        }
      }
    }
     
    // Found all YELLOWS and GREENS. Display answer for user

    String result = "";
    for (int i = 0; i < guess.length();i++)
    {
      if (colorCode[i] == 2)
        result += ANSI_GREEN + guess.substring(i,i+1) + ANSI_RESET + "\t";
      else if (colorCode[i] == 1)
        result += ANSI_YELLOW + guess.substring(i,i+1) + ANSI_RESET + "\t";
      else
        result += guess.substring(i,i+1) + "\t";
    }
    System.out.println(result);
  }


  public static String getRandomWord(ArrayList<String> list)
  {
    int rand = (int)(Math.random()*list.size());
    // return "start";
    return list.get(rand);
  }


  // Implements Insertion Sort
  public static void sort(ArrayList<String> arr)
  {
    for (int i = 0; i < arr.size() - 1; i++) {
        int minIndex = i;

        for (int j = i + 1; j < arr.size(); j++) {
          if (arr.get(j).compareTo(arr.get(minIndex)) < 0 ){
            minIndex = j;
          }
        }

        String temp = arr.get(i);
        arr.set(i, arr.get(minIndex));
        arr.set(minIndex, temp);
      }
  }

  // This will remove duplicated from the list of allWords
  public static ArrayList<String> removeDuplicates(ArrayList<String> arr)
  {
    ArrayList<String> result = new ArrayList<String>();
    for (String word: arr)
    {
      if (!result.contains(word))
        result.add(word);
    }
    return result;
  
    }
  } 
  