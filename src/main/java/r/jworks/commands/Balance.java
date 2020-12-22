package r.jworks.commands;

import io.gomint.command.Command;
import io.gomint.command.CommandOutput;
import io.gomint.command.CommandSender;
import io.gomint.command.PlayerCommandSender;
import io.gomint.command.annotation.Description;
import io.gomint.command.annotation.Name;
import io.gomint.entity.EntityPlayer;
import io.gomint.plugin.injection.InjectPlugin;
import r.jworks.Main;
import r.jworks.utils.Messages;

import java.text.NumberFormat;
import java.util.Map;

@Name( "balance" )
@Description( "Check your account balance." )
public class Balance extends Command {

    @InjectPlugin
    private Main main;

    @Override
    public CommandOutput execute( CommandSender commandSender, String s, Map< String, Object > map ) {
        CommandOutput output = new CommandOutput();

        if ( commandSender instanceof PlayerCommandSender ) {
            EntityPlayer player = (EntityPlayer) commandSender;
            player.sendMessage( Messages.PREFIX + "Balance: $" + NumberFormat.getInstance().format( this.main.getBalance( player ) ) );
        }
        return output;
    }
}
