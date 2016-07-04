package main;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.UnsupportedAudioFileException;

import appender.WavAppender;

public class AppenderExampleWithFiles {

	public static void main(String[] args) throws IOException, UnsupportedAudioFileException {
		File helloFile = new File(AppenderExampleWithFiles.class.getResource("/hello.wav").getPath());
		File worldFile = new File(AppenderExampleWithFiles.class.getResource("/world.wav").getPath());
		
		File helloWorldFile = new File("helloWorld.wav");
		
		WavAppender appender = WavAppender.fromFiles(helloFile, worldFile);
		appender.appendWav(helloWorldFile);
		System.out.println("Wrote merged file to " + helloWorldFile.getAbsolutePath());
	}

}
