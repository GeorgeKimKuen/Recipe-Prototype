package com.vaadin.tutorial.crm.ui;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import com.vaadin.tutorial.crm.backend.entity.ingredient;
import com.vaadin.tutorial.crm.backend.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and
 * use @Route annotation to announce it in a URL as a Spring managed
 * bean.
 * Use the @PWA annotation make the application installable on phones,
 * tablets and some desktop browsers.
 * <p>
 * A new instance of this class is created for every new user and every
 * browser tab/window.
 */
@Route("")
@PWA(name = "Vaadin Application",
        shortName = "Vaadin App",
        description = "This is an example Vaadin application.",
        enableInstallPrompt = false)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends VerticalLayout {

    /**
     * Construct a new Vaadin view.
     * <p>
     * Build the initial UI state for the user accessing the application.
     *
     */
    private Grid<ingredient> grid = new Grid<>(ingredient.class);
    private IngredientService ingredientService;

    public MainView(IngredientService ingredientService) {

        // Use TextField for standard text input
        ComboBox<ingredient> searchbox = new ComboBox<>("Add Ingredients");
       // TextField textField = new TextField("Add Ingredient");
       // textField.addThemeName("bordered");

        addClassName("list_view");

        // Button click listeners can be defined as lambda expressions
        Button addButton = new Button("Add");
        Button finButton = new Button("Reset");

        addButton.addClickListener(click -> addIngredient(searchbox.getValue()));
        finButton.addClickListener(click -> resetGrid() );

        HorizontalLayout layout = new HorizontalLayout(searchbox,addButton,finButton);
        layout.setDefaultVerticalComponentAlignment(Alignment.END);


        // Theme variants give you predefined extra styles for components.
        // Example: Primary button has a more prominent look.
        addButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        finButton.addThemeVariants(ButtonVariant.LUMO_CONTRAST);

        // You can specify keyboard shortcuts for buttons.
        // Example: Pressing enter in this view clicks the Button.
        addButton.addClickShortcut(Key.ENTER);




        add(layout);
        setSizeFull();
        configureGrid();

        add(grid);
        updateGrid();

    }

    private void resetGrid() {
    }

    private void addIngredient(ingredient ingredient) {
    }


    private void configureGrid() {
        grid.setColumns("recipe","ingredient");

    }

    private void updateGrid() {
    }

}
