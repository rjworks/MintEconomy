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

@Name( "setbalance" )
@Permission( "minteconomy.setbalance" )
@Description( "Add balance to a player." )

@Overload( {
        @Parameter( name = "player", validator = TargetValidator.class, optional = false ),
        @Parameter( name = "amount", validator = FloatValidator.class, optional = false )
} )
public class SetBalance extends Command {

    @InjectPlugin
    private Main main;

    @Override
    public CommandOutput execute( CommandSender commandSender, String s, Map< String, Object > argsMap ) {
        final EntityPlayer target = (EntityPlayer) argsMap.get( "player" );
        final float amount = (float) argsMap.get( "amount" );
        if ( target == null || amount < 0 ) {
            return CommandOutput.failure( "Usage: /setbalance <target> <amount>" );
        }

        this.main.setBalance( target, (long) amount );

        return CommandOutput.successful( Messages.PREFIX + "Successfully set " + target.getName() + "'s balance to $" + NumberFormat.getInstance().format( amount ) + "." );
    }
}
