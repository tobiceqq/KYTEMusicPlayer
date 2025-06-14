package playlist;

import model.Playlist;
import model.Song;

/**
 * Creates playlists (albums) with songs.
 */
public class PlaylistLoader {

    public static void loadPlaylists(PlaylistManager playlistManager) {
        playlistManager.createPlaylist("LONG.LIVE.A$AP" , "A$AP Rocky");
        Playlist longLiveAsap = playlistManager.getPlaylist("LONG.LIVE.A$AP");

        if (longLiveAsap != null) {
            longLiveAsap.addSong(new Song("LVL" , "A$AP Rocky" , 220 , "src/songs/LVL.wav"));
        }


        playlistManager.createPlaylist("LYFESTYLE" , "Yeat");
        Playlist lyfestyle = playlistManager.getPlaylist("LYFESTYLE");

        if (lyfestyle != null) {
            lyfestyle.addSong(new Song("FATË (BONUS)" , "Yeat" , 199 , "src/songs/FATË (BONUS).wav"));
        }
    }





}
