package r.jworks.listener;

import io.gomint.entity.EntityPlayer;
import io.gomint.event.EventHandler;
import io.gomint.event.EventListener;
import io.gomint.event.player.PlayerLoginEvent;
import r.jworks.Main;
import r.jworks.utils.Messages;

public class Listener implements EventListener {


    private final Main main;

    public Listener( Main main ) {
        this.main = main;
    }

    @EventHandler
    public void onJoin( PlayerLoginEvent event ) {
        EntityPlayer player = event.getPlayer();
        if ( !this.main.hasAccount( player ) ) {
            this.main.getLogger().info( Messages.PREFIX + "Creating account for " + player.getName() + "..." );
            this.main.createAccount( player );
        }
    }
}
