package vadinUI;


import com.vaadin.annotations.Theme;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import jpa.User;
import jpa.UserRepository;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Theme("valo")
@SpringUI(path = "/vaadin")
public class vadinMain extends UI {


    private final UserRepository userRepository;

    private final userEditor editor;

    final Grid<User> grid;

    final TextField filter;

    private final Button addNewBtn;

    public vadinMain(UserRepository userRepository, userEditor editor)
    {
        this.userRepository = userRepository;
        this.editor = editor;
        this.grid = new Grid<>(User.class);
        this.filter = new TextField();
        this.addNewBtn = new Button("New customer", FontAwesome.PLUS);
    }

    @Override
    protected void init(VaadinRequest request){

        // build layout
        HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
        VerticalLayout mainLayout = new VerticalLayout(actions,grid,editor);
        setContent(mainLayout);

        grid.setHeight(300,Unit.PIXELS);
        grid.removeColumn("orders");

        filter.setPlaceholder("Filter by last name");

        //Hook logic to components

        //Replace listing with filtered content when user changes filter
        filter.setValueChangeMode(ValueChangeMode.LAZY);
        filter.addValueChangeListener(valueChangeEvent -> listCustomers(valueChangeEvent.getValue()));

        //Connect selected Customer to editor or hide if none is selected
        grid.asSingleSelect().addValueChangeListener(valueChangeEvent -> {
            editor.editUser(valueChangeEvent.getValue());
        });

        // Instantiate and edit new Customer when the new button is clicked
        addNewBtn.addClickListener(clickEvent -> editor.editUser(new User("","","","")));


        // Listen changes made by the editor, refresh data from backend
        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            listCustomers(filter.getValue());
        });

        //Initialize listing
        listCustomers(null);


    }

    // tag::listCustomers[]
    void listCustomers(String filterText){
        if(StringUtils.isEmpty(filterText)){

            grid.setItems(userRepository.findAll());

        }else {

            Optional<User> user = userRepository.findUserByLogin(filterText);
            grid.setItems(user.get());
        }


    }
    // end::listCustomers[]

}
