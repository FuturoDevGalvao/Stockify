package com.unp.a3.Routing;

import com.unp.a3.Routing.enums.ViewKey;

import com.unp.a3.View.HomeView;
import com.unp.a3.View.MainView;
import com.unp.a3.View.ManagerEmployeeView;
import com.unp.a3.View.ManagerProductView;
import com.unp.a3.View.RegisterInputView;
import com.unp.a3.View.RegisterOutputView;
import com.unp.a3.View.ReportView;
import com.unp.a3.View.SignupEmployeeView;
import com.unp.a3.View.SignupProductView;

import java.awt.BorderLayout;
import javax.swing.JPanel;

import java.util.HashMap;
import java.util.Map;

public class ViewManager {
    private final MainView mainView;
    private final Map<ViewKey, JPanel> views = new HashMap<>();
    
    private static ViewManager instance;

    private ViewManager(MainView mainView) {
        this.mainView = mainView;

        views.put(ViewKey.MAIN, mainView.getMainPainel()); // painel já criado no designer
        views.put(ViewKey.HOME, new HomeView()); // painel já criado no designer
        views.put(ViewKey.SIGNUP_PRODUCT, new SignupProductView());
        views.put(ViewKey.MANAGER_PRODUCTS, new ManagerProductView());  
        views.put(ViewKey.REGISTER_INPUT_PRODUCT, new RegisterInputView());  
        views.put(ViewKey.REGISTER_OUTPUT_PRODUCT, new RegisterOutputView());  
        views.put(ViewKey.SIGNUP_EMPLOYEE, new SignupEmployeeView());
        views.put(ViewKey.MANAGER_EMPLOYEE, new ManagerEmployeeView());
        views.put(ViewKey.RELATORY, new ReportView());
    }
    
    public static ViewManager getInstance(MainView mainView) {
        if (instance == null) {
            return instance = new ViewManager(mainView);
        }
        
        return instance;
    }

    public void transition(ViewKey to) {
        JPanel mainPanel = views.get(ViewKey.MAIN);
        mainPanel.removeAll();
        mainPanel.add(views.get(to), BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }
}
