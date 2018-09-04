package pl.yamesiak.register.Commands;

import pl.yamesiak.register.Register;
import pl.yamesiak.register.Util.IPConfig;
import pl.yamesiak.register.Util.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LoginCommand implements CommandExecutor
{

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (!(sender instanceof Player))
        {
            sender.sendMessage(Message.getMessage("no-console"));
            return true;
        }

        Player p = (Player) sender;

        if (!p.hasPermission("register.login") && !p.isOp())
        {
            p.sendMessage(Message.getMessage("no-permission"));
            return true;
        }

        if (Register.getInstance().loggedInPlayers.contains(p.getUniqueId()))
        {
            p.sendMessage(Message.getMessage("already-logged"));
            return true;
        }

        switch (args.length)
        {
            case 0:
                p.sendMessage(Message.getMessage("login-help"));
            case 1:
                if (IPConfig.getPassword(p).equalsIgnoreCase(args[0]))
                {
                    Register.getInstance().loggedInPlayers.add(p.getUniqueId());
                    p.sendMessage(Message.getMessage("login-success"));
                    IPConfig.saveIP(p, p.getAddress().getAddress().getHostAddress());
                }
                else
                {
                    p.sendMessage(Message.getMessage("login-wrong"));
                }
            default:
                p.sendMessage(Message.getMessage("login-help"));
        }

        return true;
    }
}
