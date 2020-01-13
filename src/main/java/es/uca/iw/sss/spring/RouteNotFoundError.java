package es.uca.iw.sss.spring;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.router.*;
import es.uca.iw.sss.spring.ui.common.MainLayout;

import javax.servlet.http.HttpServletResponse;

@Tag(Tag.DIV)
@Route(value = "NotFound", layout = MainLayout.class)
public class RouteNotFoundError extends Component
        implements HasErrorParameter<NotFoundException> {


    @Override
    public int setErrorParameter(BeforeEnterEvent event,
                                 ErrorParameter<NotFoundException> parameter) {
        getElement().setText("Could not navigate to '"
                + event.getLocation().getPath()
                + "'");
        return HttpServletResponse.SC_NOT_FOUND;
    }
}

