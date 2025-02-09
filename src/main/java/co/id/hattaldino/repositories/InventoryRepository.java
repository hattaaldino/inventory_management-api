package co.id.hattaldino.repositories;

import co.id.hattaldino.models.Inventory;
import org.jdbi.v3.core.Jdbi;
import com.google.inject.Inject;
import java.util.List;

public class InventoryRepository {
    private final Jdbi jdbi;

    @Inject
    public InventoryRepository(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    public int createInventory(Inventory item) {
        return jdbi.withHandle(handle ->
                handle.createUpdate("INSERT INTO inventory (name, category, quantity, unit_price, supplier) VALUES (?, ?, ?, ?, ?);")
                        .bind(0, item.getName())
                        .bind(1, item.getCategory())
                        .bind(2, item.getQuantity())
                        .bind(3, item.getUnitPrice())
                        .bind(4, item.getSupplier())
                        .executeAndReturnGeneratedKeys("id")
                        .mapTo(Integer.class)
                        .one()
        );
    }

    public List<Inventory> fetchInventory(String category, String supplier) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM inventory WHERE (:category IS NULL OR category = :category) AND (:supplier IS NULL OR supplier = :supplier)")
                        .bind("category", category)
                        .bind("supplier", supplier)
                        .mapToBean(Inventory.class)
                        .list()
        );
    }

    public int updateInventory(int id, Inventory item) {
        return jdbi.withHandle(handle ->
                handle.createUpdate("UPDATE inventory SET name=?, category=?, quantity=?, unit_price=?, supplier=? WHERE id=?")
                        .bind(0, item.getName())
                        .bind(1, item.getCategory())
                        .bind(2, item.getQuantity())
                        .bind(3, item.getUnitPrice())
                        .bind(4, item.getSupplier())
                        .bind(5, id)
                        .execute()
        );
    }

    public int deleteInventory(int id) {
        return jdbi.withHandle(handle ->
                handle.createUpdate("DELETE FROM inventory WHERE id=?")
                        .bind(0, id)
                        .execute()
        );
    }
}
