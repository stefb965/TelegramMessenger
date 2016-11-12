package biz.princeps;

import java.io.IOException;
import java.net.URL;

import org.bukkit.scheduler.BukkitRunnable;

public class Telegram {

	private String auth, chatid;
	private static Telegram instance;
	private TelegramMessenger maininstance;
	
	Telegram(){
		maininstance = TelegramMessenger.getInstance();
		auth = maininstance.getConfig().getString("Telegram.Auth-Code");
		chatid = maininstance.getConfig().getString("Telegram.Chat-ID");
	}
	
	/*
	 * Send a message via telegram to a defined chat.
	 * Define your chat in the config.yml
	 * 
	 * @param msg this is your message you want to send.
	 */
	public void sendMsg(String msg){		
		StringBuilder url = new StringBuilder();
		url.append("https://api.telegram.org/bot")
			.append(auth)
			.append("/sendMessage?chat_id=")
			.append(chatid)
			.append("&text=")
			.append(msg);
		
		BukkitRunnable r = new BukkitRunnable(){
			@Override
			public void run() {
				try {
					new URL(url.toString()).openStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				};
				cancel();
			}
			
		};
		r.runTaskAsynchronously(maininstance);
	}
	
	/*
	 * Use this method to get the telegram instance.
	 * This way you are avoiding creating multiple instances.
	 * 
	 * @return returns a telegram object
	 */
	public static Telegram getInstance(){
		if(instance == null)
			instance = new Telegram();
		return instance;
	}
}
