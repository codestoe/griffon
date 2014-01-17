package sample;

import griffon.core.GriffonApplication;
import griffon.core.artifact.GriffonView;
import griffon.core.controller.Action;
import griffon.pivot.support.PivotAction;
import griffon.pivot.support.adapters.TextInputContentAdapter;
import org.apache.pivot.serialization.SerializationException;
import org.apache.pivot.wtk.*;
import griffon.metadata.ArtifactProviderFor;
import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonView;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collections;

@ArtifactProviderFor(GriffonView.class)
public class SampleView extends AbstractGriffonView {
    private SampleController controller;                                         //<1>
    private SampleModel model;                                                   //<1>

    @Inject
    public SampleView(@Nonnull GriffonApplication application) {
        super(application);
    }

    public void setController(SampleController controller) {
        this.controller = controller;
    }

    public void setModel(SampleModel model) {
        this.model = model;
    }

    @Override
    public void initUI() {
        Window window = (Window) getApplication()
            .createApplicationContainer(Collections.<String, Object>emptyMap());
        window.setTitle(getApplication().getApplicationConfiguration().getAsString("application.title"));
        window.setMaximized(true);
        getApplication().getWindowManager().attach("mainWindow", window);        //<2>

        BoxPane vbox = new BoxPane(Orientation.VERTICAL);
        try {
            vbox.setStyles("{horizontalAlignment:'center', verticalAlignment:'center'}");
        } catch (SerializationException e) {
            // ignore
        }

        vbox.add(new Label(getApplication().getMessageSource().getMessage("name.label")));

        TextInput input = new TextInput();
        input.getTextInputContentListeners().add(new TextInputContentAdapter() {  //<3>
            @Override
            public void textChanged(TextInput arg0) {
                model.setInput(arg0.getText());
            }
        });
        vbox.add(input);

        Action sayHelloAction = getApplication().getActionManager()
            .actionFor(controller, "sayHello");
        final Button button = new PushButton(sayHelloAction.getName());
        button.setAction((PivotAction) sayHelloAction.getToolkitAction());       //<4>
        vbox.add(button);

        final TextInput output = new TextInput();
        output.setEditable(false);
        model.addPropertyChangeListener("output", new PropertyChangeListener() { //<3>
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                output.setText(String.valueOf(evt.getNewValue()));
            }
        });
        vbox.add(output);

        window.setContent(vbox);
    }
}
