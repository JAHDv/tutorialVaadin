package com.example.myproject;

import java.io.File;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Container;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.FilesystemContainer;
import com.vaadin.data.util.TextFileProperty;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;

@SuppressWarnings("serial")
@Theme("myproject")
public class MyprojectUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = MyprojectUI.class)
	public static class Servlet extends VaadinServlet {
	}

	private Container dataSource = new FilesystemContainer(new File("C:/VaadinFich"));
	
	Table tabla = new Table("Ficheros",dataSource);
	Editor label = new Editor();
	@Override
	protected void init(VaadinRequest request) {
		final VerticalSplitPanel layout = new VerticalSplitPanel();
		layout.addComponent(tabla);
		tabla.addValueChangeListener(new ValueChangeListener(){
			public void valueChange(ValueChangeEvent event){
				label.setPropertyDataSource(new TextFileProperty((File)tabla.getValue()));
			}
		});
		layout.addComponent(label);
		setContent(layout);
		
	}

}