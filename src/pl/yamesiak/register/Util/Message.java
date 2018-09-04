package pl.yamesiak.register.Util;

import pl.yamesiak.register.Register;
import org.bukkit.ChatColor;

public class Message
{

    private static String color(String string)
    {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static String getMessage(String path)
    {
        return color(Register.getInstance().getConfig().getString("messages.prefix")) +
                color(Register.getInstance().getConfig().getString("messages." + path));
    }
}

