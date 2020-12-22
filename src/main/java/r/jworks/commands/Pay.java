package r.jworks.commands;

import io.gomint.command.*;
import io.gomint.command.annotation.Description;
import io.gomint.command.annotation.Name;
import io.gomint.command.annotation.Overload;
import io.gomint.command.annotation.Parameter;
import io.gomint.command.validator.FloatValidator;
import io.gomint.command.validator.TargetValidator;
import io.gomint.entity.EntityPlayer;
import io.gomint.plugin.injection.InjectPlugin;
import r.jworks.Main;
import r.jworks.utils.Messages;

import java.awt.*;
import java.text.NumberFormat;
import java.util.Map;

@Name( "pay" )
@Description( "Pay someone money." )

// Our velocity command should be able to accept a parameter for a player name and the specified velocity they should receive.
@Overload( {
        @Parameter( name = "player", validator = TargetValidator.class, optional = false ),
        @Parameter( name = "amount", validator = FloatValidator.class, optional = false )
} )
public class Pay extends Command {

    @InjectPlugin
    private Main main;

    @Override
    public CommandOutput execute( CommandSender commandSender, String alias, Map< String, Object > arguments ) {
        CommandOutput output = new CommandOutput();
        if ( commandSender instanceof PlayerCommandSender ) {
            EntityPlayer player = (EntityPlayer) commandSender;
            EntityPlayer target = (EntityPlayer) arguments.get( "player" );
            float amount = (float) arguments.get( "amount" );

            if ( target == null ) {
                return output.fail( Color.RED + "That player could not be found." );
            }

            if ( this.main.getBalance( player ) > amount ) {
                this.main.addBalance( target, (long) amount );
                this.main.reduceBalance( player, (long) amount );
                target.sendMessage( Messages.PREFIX + "You have received $" + NumberFormat.getInstance().format( amount ) + " from " + player.getName() );
                player.sendMessage( Messages.PREFIX + "You have sent $" + NumberFormat.getInstance().format( amount ) + " to " + target.getName() );
            } else {
                player.sendMessage( Messages.PREFIX + "You don't have enough money." );
            }

        } else if ( commandSender instanceof ConsoleCommandSender ) {
            return output.fail( Messages.PREFIX + "This command must be used in game!" );
        }
        return output;
    }
}
