package Task;

import java.io.*;
import javax.sound.sampled.*;

public class SoundTrack extends Thread{
	String path;
	public SoundTrack(String MusicPath) {
		// TODO Auto-generated constructor stub
		this.path = MusicPath;
	}
	@Override
	public void run() {
		try{
            File audioFile = new File(path);

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            AudioFormat format = audioStream.getFormat();

            DataLine.Info info = new DataLine.Info(Clip.class, format);

            Clip audioClip1 = (Clip) AudioSystem.getLine(info);

            audioClip1.open(audioStream);
            audioClip1.open();
            audioClip1.start();
            System.out.println("Ting!");
        }
        catch(Exception e){
            //
        }
	}
}
