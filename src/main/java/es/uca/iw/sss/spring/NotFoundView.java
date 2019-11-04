/*
package es.uca.iw.sss.spring;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.ErrorParameter;
import com.vaadin.flow.router.HasErrorParameter;
import com.vaadin.flow.router.NotFoundException;

import javax.servlet.http.HttpServletResponse;

public class NotFoundView extends Div
        implements HasErrorParameter<NotFoundException> {

    private Paragraph error = new Paragraph();

    public NotFoundView() {
        add(error);
    }

    @Override
    public int setErrorParameter(BeforeEnterEvent event,
                                 ErrorParameter<NotFoundException> parameter) {
        error.setText("Can not find URL: " + event.getLocation().getPath());
        return HttpServletResponse.SC_NOT_FOUND;
    }
}
*/