package vadinUI;

import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import jpa.AccountStatus;
import jpa.AccountType;
import jpa.User;
import jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;



@SpringComponent
@UIScope
public class userEditor extends VerticalLayout{

    private final UserRepository repository;

    private User user;
    

    //Fields to edit properties
    TextField login = new TextField("Login");
    TextField Password = new TextField("Password");
    TextField Email = new TextField("Email");
    TextField Telefon = new TextField("Telephone");
    ComboBox Status = new ComboBox("Status");
    ComboBox Type = new ComboBox("Type");





    //Action buttons
    Button save = new Button("Save", VaadinIcons.CHECK);
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete",VaadinIcons.TRASH);
    CssLayout actions = new CssLayout(save,cancel,delete);

    Binder<User> userBinder = new Binder<>(User.class);

    @Autowired
    public userEditor(UserRepository repository){
        this.repository = repository;

        addComponents(login,Password,Email,Status, Telefon, Type,actions);

        //bind using naming convetions
        //userBinder.bindInstanceFields(this);


        userBinder.bind(login,"login");
        userBinder.bind(Password,"password");
        userBinder.bind(Email,"email");
        userBinder.bind(Telefon,"telefon");
        Status.setItems(AccountStatus.Disabled,AccountStatus.Enabled);
        Type.setItems(AccountType.User,AccountType.Admin);
        userBinder.bind(Status,"status");
        userBinder.bind(Type,"type");


        //Configure and style components
        setSpacing(true);
        actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        // wire action buttons to save, delete and reset
        save.addClickListener(clickEvent -> repository.save(user));
        delete.addClickListener(clickEvent -> repository.delete(user));
        cancel.addClickListener(clickEvent -> editUser(user));
        setVisible(false);


    }

    public interface ChangeHandler{

        void onChange();
    }

    public void editUser(User user) {
        if(user == null){
            setVisible(false);
            return;
        }
        final boolean persisted = user.getId() != null;
        if(persisted){
            //Find fresh entity for editing
             this.user = repository.findById(user.getId()).get();
        }else{

            this.user = user;
        }
        cancel.setVisible(persisted);

        // Bind customer properties to similarly named fields
        // Could also use annotation or "manual binding" or programmatically
        // moving values from fields to entities before saving

        userBinder.setBean(this.user);

        setVisible(true);

        //A hack to ensure the whole form is visible
        save.focus();

        //Select all text in the first field automaticaly
        login.selectAll();

    }

    public void setChangeHandler(ChangeHandler h)
    {
        //ChangeHandler is notified when either save or delete is clicked

        save.addClickListener(clickEvent -> h.onChange());
        delete.addClickListener(clickEvent -> h.onChange());
    }

}
