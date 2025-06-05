package com.unp.a3.Routing;

import com.unp.a3.View.HomeView;
import com.unp.a3.View.MainView;
import com.unp.a3.View.ManagerView;
import com.unp.a3.View.SignupView;

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
        views.put(ViewKey.SIGNUP, new SignupView());
        views.put(ViewKey.MANAGER, new ManagerView());
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
