package co.id.hattaldino;

import ratpack.server.RatpackServer;
import ratpack.guice.Guice;
import co.id.hattaldino.modules.AppModule;
import co.id.hattaldino.handlers.InventoryHandler;

public class InventoryManagement {
    public static void main(String[] args) throws Exception {
        RatpackServer.start(server -> server
                .registry(Guice.registry(bindings -> bindings.module(AppModule.class)))
                .handlers(chain -> chain
                        .prefix("inventory", inventory -> inventory
                                .all(InventoryHandler.class)
                        )
                )
        );
    }
}
