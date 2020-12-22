package r.jworks;

import io.gomint.entity.EntityPlayer;
import io.gomint.plugin.*;
import r.jworks.listener.Listener;

import java.util.HashMap;
import java.util.Map;

@PluginName( "MintEconomy" )
@Version( major = 1, minor = 0 )
@Startup( StartupPriority.STARTUP )
public class Main extends Plugin {
    private static Main instance;
    // need help setting up a database!
    private final Map< String, Account > accounts = new HashMap<>();

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onInstall() {
        System.err.println( "Install" );
        this.registerListener( new Listener( this ) );
    }

    @Override
    public void onUninstall() {
    }

    public boolean hasAccount( EntityPlayer player ) {
        return accounts.containsKey( player.getName() );
    }

    public void createAccount( EntityPlayer player ) {
        Account acc = new Account( player, 0 );
        accounts.put( player.getName(), acc );
    }

    public Account getAccount( EntityPlayer player ) {
        return accounts.get( player.getName() );
    }

    public long getBalance( EntityPlayer player ) {
        return this.getAccount( player ).getBalance();
    }

    public void setBalance( EntityPlayer player, long balance ) {
        this.getAccount( player ).setBalance( balance );
        ;
    }

    public void addBalance( EntityPlayer player, long balance ) {
        this.getAccount( player ).addBalance( balance );
    }

    public void reduceBalance( EntityPlayer player, long balance ) {
        this.getAccount( player ).reduceBalance( balance );
    }
}