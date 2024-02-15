import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
public class MovieCollection {
    private ArrayList<Movie> movies;
    private Scanner scanner;

    public MovieCollection() {
        movies = new ArrayList<>();
        scanner = new Scanner(System.in);
        readData();
        menu();
    }

    private void menu() {
        System.out.println("Welcome to the movie collection!");
        String menuOption = "";

        while (!menuOption.equals("q")) {
            System.out.println("------------ Main Menu ----------");
            System.out.println("- search (t)itles");
            System.out.println("- search (c)ast");
            System.out.println("- (q)uit");
            System.out.print("Enter choice: ");
            menuOption = scanner.nextLine();

            if (menuOption.equals("t")) {
                searchTitles();
            } else if (menuOption.equals("c")) {
                searchCast();
            } else if (menuOption.equals("q")) {
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }

    public void searchTitles() {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();

        ArrayList<Movie> searchedMovies = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.getTitle().toLowerCase().contains(title.toLowerCase())) {
                searchedMovies.add(movie);
            }
        }
        if (searchedMovies.size() == 0) {
            System.out.println("No movie titles match that search term!");
        } else {
            insertionSort(searchedMovies);
            for (int i = 0; i < searchedMovies.size(); i++) {
                System.out.println((i + 1) + ". " + searchedMovies.get(i).getTitle());
            }
            System.out.println("\nWhich movie would you like to learn more about?");
            System.out.print("Enter number: ");
            int movieNum = scanner.nextInt();
            scanner.nextLine();
            System.out.println("\n" + searchedMovies.get(movieNum - 1).getInfo());
        }
        System.out.println();
    }

    public void searchCast() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();


    }

    private void readData() {
        try {
            File myFile = new File("src//movies_data.csv");
            Scanner fileScanner = new Scanner(myFile);
            while (fileScanner.hasNext()) {
                String data = fileScanner.nextLine();
                String[] splitData = data.split(",");
                String title = splitData[0];
                String cast = splitData[1];
                String director = splitData[2];
                String overview = splitData[3];
                String runtime = splitData[4];
                String userRating = splitData[5];
                Movie movie = new Movie(title, cast, director, overview, runtime, userRating);
                movies.add(movie);
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public static void insertionSort(ArrayList<Movie> movies) {
        for (int i = 1; i < movies.size(); i++) {
            Movie movie = movies.get(i);
            int shift = i;
            while (shift > 0 && movie.getTitle().compareTo(movies.get(shift - 1).getTitle()) < 0) {
                movies.set(shift, movies.get(shift - 1));
                shift--;
            }
            movies.set(shift, movie);
        }
    }

}