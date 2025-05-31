package command;

import audio.AudioPlayer;
import playlist.PlaylistManager;
import model.Song;
import utils.ConsoleStyle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Lyrics implements Command {

    private final PlaylistManager playlistManager;
    private final AudioPlayer audioPlayer;

    public Lyrics(PlaylistManager playlistManager, AudioPlayer audioPlayer) {
        this.playlistManager = playlistManager;
        this.audioPlayer = audioPlayer;
    }

    /**
     * Executes the lyrics command by showing song lyrics.
     *
     * @return lyrics or error message if not found
     */
    @Override
    public String execute() {
        Song currentSong = audioPlayer.getCurrentlyPlayingSong();
        if (currentSong == null) {
            return ConsoleStyle.color("❌ No song is currently playing.", ConsoleStyle.RED);
        }

        String title = currentSong.getTitle();
        Path path = Paths.get("src/files/lyrics/" + currentSong.getTitle() + ".txt");

        try {
            return ConsoleStyle.underline("\uD83C\uDFA4 Lyrics for " + ConsoleStyle.bold(title) + ":" + "\n" + Files.readString(path));
        } catch (IOException e) {
            return ConsoleStyle.color("❌ Lyrics file not found for ", ConsoleStyle.RED) + ConsoleStyle.bold(title) + ConsoleStyle.color(".", ConsoleStyle.RED);
        }
    }

    /**
     * Indicates whether this command should cause the program to exit.
     *
     * @return false, as this commands does not terminate the program
     */
    @Override
    public boolean exit() {
        return false;
    }
}
