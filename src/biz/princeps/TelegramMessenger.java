package biz.princeps;

import java.io.IOException;
import java.net.URL;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;


public class TelegramMessenger extends JavaPlugin{

	private static TelegramMessenger instance;
	private String auth, chatid;
	
	@Override
	public void onEnable(){
		instance = this;
		auth = this.getConfig().getString("Telegram.Auth-Code");
		chatid = this.getConfig().getString("Telegram.Chat-ID");
		getLogger().info("TelegramMessenger enabled!");
	}
	
	@Override
	public void onDisable(){
		instance = null;
		getLogger().info("TelegramMessenger disabled!");
	}

	public void sendMsg(String msg){
		//String url = "https://api.telegram.org/bot%AUTH%/sendMessage?chat_id=%CHATID%&text=%TEXT%".replace("%AUTH%", auth).replace("%TEXT%", msg).replace("%CHATID%", chatid).replace("#", ""); 
		
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
		r.runTaskAsynchronously(instance);
	}
	
	public static TelegramMessenger getInstance(){
		return instance;
	}
}
