package com.example.term6project;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;

public class ActionsTableCell<S> extends TableCell<S, Void> {
    private final Button editButton = new Button("Edit");
    private final Button deleteButton = new Button("Delete");

    public ActionsTableCell() {
        // Add action handlers for Edit and Delete buttons

        editButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                S rowData = getTableView().getItems().get(getIndex());
            }
        });

        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // Handle delete action
                S rowData = getTableView().getItems().get(getIndex());
            }
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
