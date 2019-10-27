package com.company;

import com.company.Controller.Controller;
import com.company.Repository.IRepository;
import com.company.Repository.Repository;
import com.company.View.View;

public class Main {

    public static void main(String[] args) {
        IRepository repository = new Repository();
        Controller controller = new Controller(repository);
        View view = new View(controller);
        view.run();

    }
}
