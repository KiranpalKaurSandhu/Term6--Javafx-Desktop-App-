package com.example.term6project;

import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;

public class ActionsTableCell<S> extends TableCell<S, Void> {
    private final Button editButton = new Button("Edit");
    private final Button deleteButton = new Button("Delete");

    public ActionsTableCell() {
        // Add action handlers for Edit and Delete buttons
        editButton.setOnAction(event -> {
            // Handle edit action (e.g., open an edit dialog for the selected row)
            S rowData = getTableView().getItems().get(getIndex());
            // Implement edit logic here
        });

        deleteButton.setOnAction(event -> {
            // Handle delete action (e.g., prompt for confirmation and delete the selected row)
            S rowData = getTableView().getItems().get(getIndex());
            // Implement delete logic here
        });
    }

    @Override
    protected void updateItem(Void item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setGraphic(null);
        } else {
            // Set the Edit and Delete buttons in the cell
            setGraphic(new HBox(editButton, deleteButton));
        }
    }
}
