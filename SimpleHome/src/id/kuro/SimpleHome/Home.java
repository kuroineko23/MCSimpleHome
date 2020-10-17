package id.kuro.SimpleHome;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class Home implements CommandExecutor{

	private final Main plugin;
	
	public Home(Main plugin)
	{
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
				player.sendMessage(ChatColor.RED + "You haven't set a home!");
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
				player.sendMessage(ChatColor.YELLOW + "Teleporting to your home in 5 seconds!");
				BukkitTask teleport = new TeleportTask(player, x1, y1, z1).runTaskTimer(this.plugin, 0, 20);
			}
		}
		return true;
	}

}
