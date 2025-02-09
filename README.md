# Inventory Management API

This API provides CRUD (Create, Read, Update, Delete) operations for managing inventory items.

## Initiation

Create database from script in `/support/database.sql`

## Endpoints

### 1. Add Inventory Item

**Endpoint:** `POST /inventory`

**Request Body:**

```json
{
  "name": "Smart Sensor",
  "category": "Electronics",
  "quantity": 50,
  "unitPrice": 19.99,
  "supplier": "Samsung"
}
```

**Response:**

```json
{
    "data": {
        "id": 7
    },
    "message": "Inventory item added successfully"
}
```

---

### 2. Get All Inventory Items

**Endpoint:** `GET /inventory?category={category}&supplier={supplier}`

**Response:**

```json
{
    "data": [
        {
            "id": 7,
            "name": "Smart Sensor",
            "category": "Electronics",
            "quantity": 50,
            "unitPrice": 19.99,
            "supplier": "Samsung",
            "updatedAt": "2025-02-09 22:37:20.709666"
        }
    ],
    "message": "All inventory items fetched successfully"
}
```

---

### 3. Update Inventory Item

**Endpoint:** `PUT /inventory/{id}`

**Request Body:**

```json
{
  "name": "Smart Sensor",
  "category": "Electronics",
  "quantity": 120,
  "unitPrice": 19.99,
  "supplier": "Samsung"
}
```

**Response:**

```json
{
    "message": "Inventory item updated successfully"
}
```

---

### 4. Delete Inventory Item

**Endpoint:** `DELETE /inventory/{id}`

**Response:**

```json
{
    "message": "Inventory item deleted successfully"
}
```

## Testing

For testing you can use InventoryManagement.jar by execute `java -jar InventoryManagement.jar` in your server, then you can simulate the API by importing the postman collection stored in the support directory into postman.
