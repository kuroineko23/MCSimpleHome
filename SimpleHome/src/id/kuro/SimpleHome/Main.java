package id.kuro.SimpleHome;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{

	private static Plugin plugin; 
	@Override
	public void onEnable()
	{
		plugin = this;
		File file = new File(getDataFolder() + File.separator + "data.yml");
		FileConfiguration data = YamlConfiguration.loadConfiguration(file);
		if(!file.exists())
		{
			try {
				Double x = Bukkit.getWorlds().get(0).getSpawnLocation().getX();
				Double y = Bukkit.getWorlds().get(0).getSpawnLocation().getY();
				Double z = Bukkit.getWorlds().get(0).getSpawnLocation().getZ();
				data.set("spawn", x + " " + y + " " + z);
				data.save(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		this.getCommand("sethome").setExecutor(new SetHome(this));
		this.getCommand("home").setExecutor(new Home(this));
		this.getCommand("spawn").setExecutor(new Spawn(this));
	}
	
	@Override
	public void onDisable()
	{
		
	}
	
	public static Plugin getPlugin()
	{
		return plugin;
	}
}