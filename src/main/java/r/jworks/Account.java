package r.jworks;

import io.gomint.entity.EntityPlayer;

public class Account {

    private final EntityPlayer player;
    private long balance;

    public Account( EntityPlayer player, long balance ) {
        this.player = player;
        this.balance = balance;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance( long balance ) {
        if ( balance < 0 ) return;
        this.balance = balance;
    }

    public void addBalance( long balance ) {
        this.balance += balance;
    }

    public void reduceBalance( long balance ) {
        this.balance -= balance;
    }

    public EntityPlayer getPlayer() {
        return player;
    }
}


