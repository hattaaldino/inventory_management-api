package co.id.hattaldino.services;

import co.id.hattaldino.models.Inventory;
import co.id.hattaldino.repositories.InventoryRepository;
import com.google.inject.Inject;
import java.util.List;

public class InventoryService {
    private final InventoryRepository repository;

    @Inject
    public InventoryService(InventoryRepository repository) {
        this.repository = repository;
    }

    public int addInventory(Inventory item) {
        return repository.createInventory(item);
    }

    public List<Inventory> getInventory(String category, String supplier) {
        return repository.fetchInventory(category, supplier);
    }

    public void updateInventory(int id, Inventory item) {
        repository.updateInventory(id, item);
    }

    public void deleteInventory(int id) {
        repository.deleteInventory(id);
    }
}
