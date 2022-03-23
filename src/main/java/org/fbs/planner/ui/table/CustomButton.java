package org.fbs.planner.ui.table;

@com.vaadin.flow.component.Tag("button")
public class CustomButton  extends com.vaadin.flow.component.HtmlContainer implements com.vaadin.flow.component.ClickNotifier<com.vaadin.flow.component.html.Div>, com.vaadin.flow.component.HasOrderedComponents
{

    public CustomButton()
    {

    }

    public CustomButton(final String text, final String className)
    {
        this.setText(text);
        this.setClassName(className);
    }

    public CustomButton(final String text,final String className, final String id)
    {
        this.setText(text);
        this.setClassName(className);
        this.setId(id);
    }

    public CustomButton(final String text,final String className, final String id, final String onclick)
    {
        this.setText(text);
        this.setClassName(className);
        this.setId(id);
        this.getElement().setAttribute("onclick", onclick);
    }

}
