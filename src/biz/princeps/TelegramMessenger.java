package biz.princeps;

import org.bukkit.plugin.java.JavaPlugin;

public class TelegramMessenger extends JavaPlugin{
	
	private static TelegramMessenger instance;
	
	@Override
	public void onEnable(){
		instance = this;
		this.saveDefaultConfig();
	}

	protected static TelegramMessenger getInstance(){
		return instance;
	}

}

