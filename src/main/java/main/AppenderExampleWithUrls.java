package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import javax.sound.sampled.UnsupportedAudioFileException;

import appender.WavAppender;

public class AppenderExampleWithUrls {

	public static URL makeUrl(String sentence, String voiceRssApiKey) throws MalformedURLException {
		String urlString = "https://api.voicerss.org/?key=" + voiceRssApiKey + "&src=" + sentence + "&hl=en-in&c=WAV";
		return new URL(urlString);
	}

	public static void main(String[] args) throws IOException, UnsupportedAudioFileException {
		
		Properties properties = new Properties();
		File propertyFile = new File(AppenderExampleWithFiles.class.getResource("/properties/config.properties").getPath());
		InputStream propertyFileInputStream = new FileInputStream(propertyFile);
		properties.load(propertyFileInputStream);
		
		URL helloUrl = makeUrl("hello", properties.getProperty("apiKey"));
		URL worldUrl = makeUrl("world", properties.getProperty("apiKey"));
		
		File helloWorldFile = new File("helloWorld.wav");
		
		WavAppender appender = WavAppender.fromUrls(helloUrl, worldUrl);
		appender.appendWav(helloWorldFile);
		System.out.println("Wrote merged file to " + helloWorldFile.getAbsolutePath());

	}

}
