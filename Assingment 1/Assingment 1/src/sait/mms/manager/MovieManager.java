package sait.mms.manager;

import java.io.*;
import java.util.*;

import sait.mms.problemdomain.Movie;

public class MovieManager {
	Scanner reader = new Scanner(System.in);

	// Constants
	private final String FILE_NAME = "res/movies.txt";
	// Attributes
	private ArrayList<Movie> movies = new ArrayList<>();
	
	public MovieManager() {
		loadMovieList();
		displayMenu();
	}
	
	//COMPLETE
	private void loadMovieList() {
		File file = new File(FILE_NAME);
		
		try {
			Scanner in = new Scanner(file);
			while(in.hasNext()){
				String[] input = new String[3];
				input = in.nextLine().split(",");
				int duration = Integer.parseInt(input[0]);
				String title = input[1];
				int year = Integer.parseInt(input[2]);
				Movie movie = new Movie(duration, title, year);
				movies.add(movie);
			}
			in.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//Display menu method COMPLETE
	private void displayMenu(){		
		//Print for menu display
		System.out.println("\nMovie Management system "
				+ "\n1     Add New Movie and Save "
				+ "\n2     Generate List of movies Released in a Year "
				+ "\n3     Generate List of Random Movies "
				+ "\n4     Exit" 
				+ "\n\nEnter an option:");
		
		int in = reader.nextInt(); 
		
		//Reading integer input for user selection
		if(in == 1){
			addMovie();
		}else if(in == 2) {
			generateMovieListInYear();
		}else if(in == 3) {
			generateRandomMovieList();
		}else if(in == 4) {
			saveMovieListToFile();
			reader.close();
		}else {
			System.out.println("Invalid Option!\n");
			displayMenu();
		}
	}
	
	//Add movie method COMPLETE
	private void addMovie() {
		int duration;
		String title;
		int year;
		
		//User Input for a new movie		
		System.out.println("Enter the duration:");
		duration = reader.nextInt();
		
		reader.nextLine(); //Passing through the \n
		System.out.println("Enter the title:");
		title = reader.nextLine();
		
		System.out.println("Enter  the year:");
		year = reader.nextInt();
		
		//Are the values the user added valid?
		if(duration <= 0) {
			System.out.println("Please use a valid duration");
			addMovie();
		}else if(title == "") {
			System.out.println("Please enter a valid title");
			addMovie();
		}else if(year <= 0) {
			System.out.println("Please use a valid year");
			addMovie();
		}else {
			//Adding the new movie to the list of movies
			System.out.println("Saving Movies...");
			Movie movie = new Movie(duration, title, year);
			System.out.println("Added movie to the data file");
			movies.add(movie);
			displayMenu();
		}
		
	}
	
	//generateMovieListInYear method Complete
	private void generateMovieListInYear() {
		int inYear;
		int year;
		int totalDuration = 0;
		
		//input movie year
		System.out.println("Enter the year of movies you would like to display: ");
		inYear = reader.nextInt();
		System.out.println("\nMovie List");
		System.out.printf("%-12s %-6s %-20s%n", "Duration", "Year", "Title");
		
		//compares requested movie year to getYear() from Movie class
		for (Movie movie : movies) {
			year = movie.getYear();
			if (year == inYear) {
				totalDuration += movie.getDuration();
				System.out.printf("%-12s %-6s %-20s%n", movie.getDuration(), movie.getYear(), movie.getTitle());
			}
        }
		System.out.println("Total Duration: " + totalDuration + " minutes");
		displayMenu();
	}
	
	//generateRandomMovieList method complete
	private void generateRandomMovieList() {
		int numOfMovies;
		int totalDuration = 0;
		
		//input number of movies
		System.out.println("Enter number of Movies: ");
		numOfMovies = reader.nextInt();
		System.out.println("\nMovie List");
		System.out.printf("%-12s %-6s %-20s%n", "Duration", "Year", "Title");
		
		//randomizes movie list
		Collections.shuffle(movies);
		
		//checks if numOfMovies is valid, then prints request amount
		if (numOfMovies <= movies.size()) {
			for (int count = 0; count < numOfMovies; count++) {
				Movie movie = movies.get(count);
				totalDuration += movie.getDuration();
			    System.out.printf("%-12s %-6s %-20s%n", movie.getDuration(), movie.getYear(), movie.getTitle());
			}
		} else {
			System.out.println("The number of movies requested needs to be less then " + movies.size());
		}
		System.out.println("Total Duration: " + totalDuration + " minutes");
		displayMenu();
	}
	
	private void saveMovieListToFile() {
		try {
			PrintWriter out = new PrintWriter(new File(FILE_NAME));
			
			for(Movie movie : movies) {
				out.println(movie.toString());
			}
			out.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
