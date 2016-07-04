package appender;

import java.io.File;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.net.URL;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

public class WavAppender {

	private AudioInputStream clip1;
	private AudioInputStream clip2;

	public WavAppender(AudioInputStream inputAudio1, AudioInputStream inputAudio2) {
		this.clip1 = inputAudio1;
		this.clip2 = inputAudio2;
	}

	public static WavAppender fromUrls(URL url1, URL url2) throws UnsupportedAudioFileException, IOException {
		AudioInputStream clip1 = AudioSystem.getAudioInputStream(url1);
		AudioInputStream clip2 = AudioSystem.getAudioInputStream(url2);

		return new WavAppender(clip1, clip2);
	}

	public static WavAppender fromFiles(File inputFile1, File inputFile2)
			throws UnsupportedAudioFileException, IOException {
		AudioInputStream clip1 = AudioSystem.getAudioInputStream(inputFile1);
		AudioInputStream clip2 = AudioSystem.getAudioInputStream(inputFile2);
		return new WavAppender(clip1, clip2);
	}

	public void appendWav(File outputFile) throws IOException {
		AudioInputStream appendedFiles = new AudioInputStream(new SequenceInputStream(clip1, clip2), clip1.getFormat(),
				clip1.getFrameLength() + clip2.getFrameLength());

		AudioSystem.write(appendedFiles, AudioFileFormat.Type.WAVE, outputFile);
	}
}
