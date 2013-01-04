package uk.ac.gla.dcs.tp3.w.parser;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

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
		
		while(fs.hasNextLine()){
			line = fs.nextLine();
			splitLine = line.split(" ");
			if(verbose){
				System.out.print("[");
				for(String s : splitLine)
					System.out.print(s+", ");
				System.out.println("]");
			}
			/**
			if (splitLine.length == 0){break;}
			else if (splitLine.length == 3){}*/
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
