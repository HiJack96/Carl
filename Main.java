import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
//import java.awt.*;

public class Main {
	private static ArrayList<Converse> catalogue = new ArrayList<Converse>();

	private static void loadFile() {
		ArrayList dummyList = new ArrayList();
		File data = new File("Memory.sav");
		try {
			if(!data.exists()) {
				if(data.createNewFile()) 
					System.out.println("Hello! My name is Carl. I am an AI created by Jack Bachman. \nIf you are reading this, then that means that I must have \nforgotten how to talk with people. Which means, \nyou are going to have to teach me the basics. So...");
				catalogue.add(new Converse("Hello!","Hello!",1) );
			} else {
				try {
					FileInputStream savedFile = new FileInputStream("Memory.sav");
					ObjectInputStream restore = new ObjectInputStream(savedFile);
					dummyList = (ArrayList) restore.readObject();
					restore.close();
				} catch(Exception exc) { exc.printStackTrace(); }
	
				Converse dummy = new Converse();
				for(java.lang.Object arg : dummyList) {
					dummy = (Converse) arg;
					catalogue.add(dummy);
				}
			}
			data.delete();
		} catch(Exception exc) { exc.printStackTrace(); }
	}

	private static void saveFile() {
		try {
			FileOutputStream savedFile = new FileOutputStream("Memory.sav");
			ObjectOutputStream save = new ObjectOutputStream(savedFile);
			save.writeObject(catalogue);
			save.close();
		} catch(Exception exc) { exc.printStackTrace();}
	}
	
	public static void main(String[] args) {
		loadFile();
		Scanner scan = new Scanner(System.in);
		boolean continueGame = true, responseExists = false;
		Converse properResponse = new Converse();
		Random ran = new Random();
		Converse nullCon = new Converse("","", 0);


		String output = catalogue.get( ran.nextInt( catalogue.size() ) ).getResponse();
		System.out.println(output);
		String input = scan.nextLine();

		do {



			for( Converse arg : catalogue ) {
				if( arg.getStatement().equals(output) && arg.getResponse().equals(input) ) {
					arg.increaseFreq();
					responseExists = true;
				}
			}
			if (!responseExists) {
				catalogue.add( new Converse(output,input,1));
			}
			responseExists=false;



			properResponse = nullCon;
			for( Converse arg : catalogue ) {
				if( arg.getStatement().equals(input) ) {
					responseExists = true;
					if( arg.getFreq() > properResponse.getFreq() )
							properResponse =  arg;
				}
			}
			if (!responseExists ) {
				properResponse = catalogue.get( ran.nextInt( catalogue.size() ) );
			}
			responseExists=false;

			output = properResponse.getResponse();



			System.out.println(output);
			input = scan.nextLine();

			if (input.equals("goodbye") )
				continueGame = false;
		} while (continueGame);
		saveFile();
	}
}