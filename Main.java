package mymusicapp;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Main {

    private static ArrayList<Album> albums = new ArrayList<>();

    public static void main(String[] args) {
        Album album = new Album("Album", "AC/DC");
        album.addSong("TNT", 4.5);
        album.addSong("Highway to hell", 3.5);
        album.addSong("Thunderstruck", 5.5);
        albums.add(album);

        album = new Album("Album2", "Arjit Singh");
        album.addSong("chahu me aaja", 4.0);
        album.addSong("munni badnam", 4.5);
        album.addSong("dil sambal ja jara", 2.5);
        album.addSong("Khamosiya", 4.5);
        albums.add(album);

        LinkedList<Song> playList_1 = new LinkedList<>();
        albums.get(0).addtoPlayList("TNT", playList_1);
        albums.get(1).addtoPlayList("chahu me aaja", playList_1);
        albums.get(1).addtoPlayList("Khamosiya", playList_1); // corrected index

        play(playList_1);
    }

    private static void play(LinkedList<Song> playList) {
        Scanner sc = new Scanner(System.in);
        boolean quit = false;
        boolean forward = true;
        ListIterator<Song> listIterator = playList.listIterator();

        if (playList.size() == 0) {
            System.out.println("This playlist has no song");
            return;
        } else {
            System.out.println("Now playing: " + listIterator.next().toString());
            printMenu();
        }

        while (!quit) {
            int action = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (action) {
                case 0:
                    System.out.println("Playlist complete");
                    quit = true;
                    break;

                case 1: // play next
                    if (!forward) {
                        if (listIterator.hasNext()) listIterator.next();
                        forward = true;
                    }
                    if (listIterator.hasNext()) {
                        System.out.println("Now playing: " + listIterator.next().toString());
                    } else {
                        System.out.println("No next song, reached the end of the playlist");
                        forward = false;
                    }
                    break;

                case 2: // play previous
                    if (forward) {
                        if (listIterator.hasPrevious()) listIterator.previous();
                        forward = false;
                    }
                    if (listIterator.hasPrevious()) {
                        System.out.println("Now playing: " + listIterator.previous().toString());
                    } else {
                        System.out.println("You are at the start of the playlist");
                        forward = true;
                    }
                    break;

                case 3: // replay current
                    if (forward) {
                        if (listIterator.hasPrevious()) {
                            System.out.println("Replaying: " + listIterator.previous().toString());
                            forward = false;
                        } else {
                            System.out.println("We are at the start of the list");
                        }
                    } else {
                        if (listIterator.hasNext()) {
                            System.out.println("Replaying: " + listIterator.next().toString());
                            forward = true;
                        } else {
                            System.out.println("We have reached the end of the list");
                        }
                    }
                    break;

                case 4:
                    printList(playList);
                    break;

                case 5:
                    printMenu();
                    break;

                case 6: // delete current song
                    if (playList.size() > 0) {
                        listIterator.remove();
                        System.out.println("Song removed from playlist");

                        if (listIterator.hasNext()) {
                            System.out.println("Now playing: " + listIterator.next().toString());
                        } else if (listIterator.hasPrevious()) {
                            System.out.println("Now playing: " + listIterator.previous().toString());
                        }
                    }
                    break;

                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }

    private static void printMenu() {
        System.out.println("\nAvailable options:\nPress");
        System.out.println("0 - to quit");
        System.out.println("1 - to play next song");
        System.out.println("2 - to play previous song");
        System.out.println("3 - to replay current song");
        System.out.println("4 - list all songs in the playlist");
        System.out.println("5 - print available options");
        System.out.println("6 - delete current song from playlist");
    }

    private static void printList(LinkedList<Song> playList) {
        Iterator<Song> iterator = playList.iterator();
        System.out.println("------------ Playlist ------------");
        while (iterator.hasNext()) {
            System.out.println(iterator.next().toString());
        }
        System.out.println("----------------------------------");
    }
}

