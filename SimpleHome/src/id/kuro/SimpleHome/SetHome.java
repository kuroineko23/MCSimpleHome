package id.kuro.SimpleHome;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class SetHome implements CommandExecutor {

	private final Main plugin;
	
	public SetHome(Main plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player)
		{
			Player player = (Player) sender;
			String name = player.getName();
			Double x = player.getLocation().getX();
			Double y = player.getLocation().getY();
			Double z = player.getLocation().getZ();
			File file = new File(Main.getPlugin().getDataFolder() + File.separator + "data.yml");
			FileConfiguration data = YamlConfiguration.loadConfiguration(file);
			if(data.getString(player.getName()) == null)
			{
				try {
					data.set(name, x + " " + y + " " + z);
					data.save(file);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else
			{
				Double x1, y1, z1;
				Object get = data.get(name);
				String oldPos = get.toString();
				String[] temp = oldPos.split(" ");
				x1 = Double.parseDouble(temp[0]);
				y1 = Double.parseDouble(temp[1]);
				z1 = Double.parseDouble(temp[2]);
				player.sendMessage(ChatColor.RED + "Replacing your home coordinates!");
				player.sendMessage("Old coordinates : " + x1 + " " + y1 + " " + z1);
				try {
					data.set(name, x + " " + y + " " + z);
					data.save(file);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			player.sendMessage("Home coordinates saved!");
		}
		return true;
	}
	
}
