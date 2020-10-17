package id.kuro.SimpleHome;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TeleportTask extends BukkitRunnable{
	
	private Player player;
	private Double x1;
	private Double y1;
	private Double z1;
	private Double x;
	private Double y;
	private Double z;
	private Integer count;

	public TeleportTask(Player player, Double x1, Double y1, Double z1)
	{
		this.player = player;
		this.x1 = x1;
		this.y1 = y1;
		this.z1 = z1;
		this.x = player.getLocation().getX();
		this.y = player.getLocation().getY();
		this.z = player.getLocation().getZ();
		this.count = 5;
	}
	
	@Override
	public void run() {
		if(count > 1)
		{
			Double xtemp = player.getLocation().getX();
			Double ytemp = player.getLocation().getY();
			Double ztemp = player.getLocation().getZ();
			if(!x.equals(xtemp) || !y.equals(ytemp) || !z.equals(ztemp))
			{
				player.sendMessage(ChatColor.RED + "Teleport cancelled!");
				cancel();
			}
			count--;
		}
		else
		{
			Location location = new Location(Bukkit.getWorlds().get(0), x1, y1, z1);
			player.teleport(location);
			player.sendMessage(ChatColor.GREEN + "Welcome back!");
			cancel();
		}
	}

}
