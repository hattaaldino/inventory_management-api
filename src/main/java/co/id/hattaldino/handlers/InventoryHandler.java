package co.id.hattaldino.handlers;

import co.id.hattaldino.services.InventoryService;
import co.id.hattaldino.models.Inventory;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.http.Request;
import ratpack.jackson.Jackson;

import com.google.inject.Inject;
import ratpack.path.PathTokens;

import java.util.List;
import java.util.Map;

public class InventoryHandler implements Handler {
    private final InventoryService service;

    @Inject
    public InventoryHandler(InventoryService service) {
        this.service = service;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        ctx.byMethod(method -> method
                .post(() -> ctx.parse(Inventory.class).then(item -> {
                    int id = service.addInventory(item);

                    ctx.render(Jackson.json(Map.of(
                            "message", "Inventory item added successfully",
                            "data", Map.of("id", id)
                    )));
                }))

                .get(() -> {
                    String category = ctx.getRequest().getQueryParams().get("category");
                    String supplier = ctx.getRequest().getQueryParams().get("supplier");
                    List<Inventory> items = service.getInventory(category, supplier);

                    ctx.render(Jackson.json(Map.of(
                            "message", "All inventory item fetch succesfully",
                            "data", items
                    )));
                })

                .put(() -> {
                    int id = Integer.parseInt(ctx.getRequest().getUri().split("/")[2]);
                    ctx.parse(Inventory.class).then(item -> {
                        service.updateInventory(id, item);

                        ctx.render(Jackson.json(Map.of(
                                "message", "Inventory item updated successfully"
                        )));
                    });
                })

                .delete(() -> {
                    int id = Integer.parseInt(ctx.getRequest().getUri().split("/")[2]);
                    service.deleteInventory(id);

                    ctx.render(Jackson.json(Map.of(
                            "message", "Inventory item deleted successfully"
                    )));
                })
        );
    }
}
