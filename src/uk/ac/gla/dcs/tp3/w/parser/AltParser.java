package uk.ac.gla.dcs.tp3.w.parser;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import uk.ac.gla.dcs.tp3.w.league.Date;

public class AltParser {

	private boolean verbose = false;
	public AltParser() {}
	
	public boolean parse(String fileName){
		File f;
		Scanner fs;
		String line;
		String[] splitLine;
		try{
			f = new File(fileName);
			fs = new Scanner(f);
		} catch (FileNotFoundException e){
			e.printStackTrace();
			return false;
		}
		
		Date current;
		
		while(fs.hasNextLine()){
			line = fs.nextLine();
			splitLine = line.split(" ");
			if(verbose){
				System.out.print("[");
				for(String s : splitLine)
					System.out.print(s+", ");
				System.out.println("]: Length = " +splitLine.length);
			}
			if (splitLine.length == 1){break;}
			else if (splitLine.length == 3){
				int day = Integer.parseInt(splitLine[0]);
				int year = Integer.parseInt(splitLine[2]);
			}
			else{
			}
		}
		
		return true;
	}
	
	public void setVerbose(){verbose = true;}
	
	public static void main(String[] args){
		AltParser ap = new AltParser();
		ap.setVerbose();
		ap.parse(args[0]);
	}
}
