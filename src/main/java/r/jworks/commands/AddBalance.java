package r.jworks.commands;

import io.gomint.command.Command;
import io.gomint.command.CommandOutput;
import io.gomint.command.CommandSender;
import io.gomint.command.annotation.*;
import io.gomint.command.validator.FloatValidator;
import io.gomint.command.validator.TargetValidator;
import io.gomint.entity.EntityPlayer;
import io.gomint.plugin.injection.InjectPlugin;
import r.jworks.Main;
import r.jworks.utils.Messages;

import java.text.NumberFormat;
import java.util.Map;

@Name( "addbalance" )
@Permission( "minteconomy.addbalance" )
@Description( "Add balance to a player." )

@Overload( {
        @Parameter( name = "player", validator = TargetValidator.class, optional = false ),
        @Parameter( name = "amount", validator = FloatValidator.class, optional = false )
} )
public class AddBalance extends Command {

    @InjectPlugin
    private Main main;

    @Override
    public CommandOutput execute( CommandSender commandSender, String s, Map< String, Object > argsMap ) {
        final EntityPlayer target = (EntityPlayer) argsMap.get( "player" );
        final float amount = (float) argsMap.get( "amount" );
        if ( target == null || amount <= 0 ) {
            return CommandOutput.failure( "Usage: /addbalance <target> <amount>" );
        }

        this.main.addBalance( target, (long) amount );

        CommandOutput.successful( Messages.PREFIX + "You have received $" + NumberFormat.getInstance().format( amount ) + "." );
        return CommandOutput.successful( Messages.PREFIX + "Successfully added $" + NumberFormat.getInstance().format( amount ) + " to " + target.getName() + "." );
    }
}
