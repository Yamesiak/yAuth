package pl.yamesiak.register;

import pl.yamesiak.register.Commands.LoginCommand;
import pl.yamesiak.register.Commands.RegisterCommand;
import pl.yamesiak.register.Listeners.PlayerListener;
import pl.yamesiak.register.Util.IPConfig;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Register extends JavaPlugin
{

    private static Register register;
    public List<UUID> loggedInPlayers = new ArrayList<>();

    public void onEnable()
    {
        register = this;
        initConfig();
        registerAll();
        IPConfig.newIPFile();
    }

    public void onDisable()
    {

    }

    private void initConfig()
    {
        FileConfiguration config = getConfig();
        config.options().copyDefaults(true);
        saveConfig();
    }

    private void registerAll()
    {
        getCommand("register").setExecutor(new RegisterCommand());
        getCommand("login").setExecutor(new LoginCommand());
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
    }

    public static Register getInstance()
    {
        return register;
    }
}
