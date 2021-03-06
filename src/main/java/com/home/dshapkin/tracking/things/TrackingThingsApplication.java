package com.home.dshapkin.tracking.things;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class TrackingThingsApplication implements CommandLineRunner {

	private final MyBot myBot;

	public TrackingThingsApplication(MyBot myBot) {
		this.myBot = myBot;
	}

	public static void main(String[] args) {
		SpringApplication.run(TrackingThingsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
		botsApi.registerBot(myBot);

	}
}
