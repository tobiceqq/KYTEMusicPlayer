package command;

import exceptions.InvalidCommandException;
import audio.AudioPlayer;
import model.Playlist;
import playlist.PlaylistManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Executes commands based on user input.
 */
public class CommandExecutor {

    private final Map<String, Command> commandMap;
    private final PlaylistManager playlistManager;
    private final AudioPlayer audioPlayer;
    private final Scanner scanner;

    public CommandExecutor(PlaylistManager playlistManager, AudioPlayer audioPlayer) {
        this.commandMap = new HashMap<>();
        this.playlistManager = playlistManager;
        this.audioPlayer = audioPlayer;
        this.scanner = new Scanner(System.in);
        registerCommands();
    }

    /**
     * Registers all available commands to the command map.
     */
    private void registerCommands() {

        commandMap.put("select", new SelectPlaylist(playlistManager, scanner));
        commandMap.put("1", new SelectPlaylist(playlistManager, scanner));

        commandMap.put("play", new Play(playlistManager, audioPlayer));
        commandMap.put("2", new Play(playlistManager, audioPlayer));

        commandMap.put("pause", new Pause(playlistManager, audioPlayer));
        commandMap.put("3", new Pause(playlistManager, audioPlayer));

        commandMap.put("stop", new Stop(playlistManager, audioPlayer));
        commandMap.put("4", new Stop(playlistManager, audioPlayer));

        commandMap.put("next", new Next(playlistManager, audioPlayer));
        commandMap.put("5", new Next(playlistManager, audioPlayer));

        commandMap.put("previous", new Previous(playlistManager, audioPlayer));
        commandMap.put("6", new Previous(playlistManager, audioPlayer));

        commandMap.put("shuffle", new Shuffle(playlistManager, audioPlayer));
        commandMap.put("7", new Shuffle(playlistManager, audioPlayer));

        commandMap.put("loop", new Loop(playlistManager, scanner));
        commandMap.put("8", new Loop(playlistManager, scanner));

        commandMap.put("seekforward", new SeekForward(playlistManager, audioPlayer, 15l));
        commandMap.put("9", new SeekForward(playlistManager, audioPlayer, 15l));

        commandMap.put("seekbackward", new SeekBackward(playlistManager, audioPlayer, 15l));
        commandMap.put("10", new SeekBackward(playlistManager, audioPlayer, 15l));

        commandMap.put("addsong", new AddSong(playlistManager, scanner));
        commandMap.put("11", new AddSong(playlistManager, scanner));

        commandMap.put("removesong", new RemoveSong(playlistManager, scanner));
        commandMap.put("12", new RemoveSong(playlistManager, scanner));

        commandMap.put("currentsong", new ShowCurrentSong(playlistManager));
        commandMap.put("13", new ShowCurrentSong(playlistManager));

        commandMap.put("playlist", new ShowPlaylist(playlistManager));
        commandMap.put("14", new ShowPlaylist(playlistManager));

        commandMap.put("createplaylist", new CreatePlaylist(playlistManager, scanner));
        commandMap.put("15", new CreatePlaylist(playlistManager, scanner));

        commandMap.put("favorite", new Favorite(playlistManager, scanner));
        commandMap.put("16", new Favorite(playlistManager, scanner));

        commandMap.put("search", new Search(playlistManager, scanner));
        commandMap.put("17", new Search(playlistManager, scanner));

        commandMap.put("normalmode", new NormalMode(playlistManager));
        commandMap.put("18", new NormalMode(playlistManager));

        commandMap.put("stats", new Stats(playlistManager));
        commandMap.put("19", new Stats(playlistManager));

        commandMap.put("help", new Help());
        commandMap.put("20", new Help());

        commandMap.put("exit", new Exit());
        commandMap.put("21", new Exit());

    }

    /**
     * Executes the command matching the given input.
     *
     * @param input the user input
     * @return the result message of the executed command
     * @throws InvalidCommandException if the input command is invalid
     */
    public String executeCommand(String input) throws InvalidCommandException {
        Command command = commandMap.get(input.toLowerCase());
        if (command == null) {
            throw new InvalidCommandException("❓ Unknown command: " + input);
        }
        return command.execute();
    }

    /**
     * Determines if the given command  should terminate the app.
     *
     * @param input the user input
     * @return true if the command should exit the app, false otherwise
     */
    public boolean shouldExit(String input) {
        Command command = commandMap.get(input.toLowerCase());
        return command != null && command.exit();
    }
}
