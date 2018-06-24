package com.javarush.task.task28.task2810;

import com.javarush.task.task28.task2810.model.*;
import com.javarush.task.task28.task2810.view.HtmlView;
import java.io.IOException;


public class Aggregator {
    public static void main(String[] args) throws IOException {
        HtmlView view = new HtmlView();
        Provider provider = new Provider(new MoikrugStrategy());
        Provider[] providers = {new Provider(new HHStrategy()), provider};

        Model model = new Model(view, providers);
        Controller controller = new Controller(model);
        view.setController(controller);
        view.userCitySelectEmulationMethod();
        //view.update(provider.getJavaVacancies("Dnepropetrovsk"));
        //controller.onCitySelect("Dnepropetrovsk");

        //Strategy strategy = new MoikrugStrategy();
        //strategy.getVacancies("Dnepropetrovsk");




        //view.userCitySelectEmulationMethod();
        //view.updateFile("");
        /*List<Vacancy> list = new ArrayList<>();
        Vacancy vac = new Vacancy();
        vac.setTitle("Java Developer");
        vac.setUrl("http://tts.lt");
        vac.setSiteName("TTS.LT");
        vac.setCompanyName("Stea1th Corp.");
        vac.setCity("Visaginas");
        vac.setSalary("5000");
        list.add(vac);
        view.getUpdatedFileContent(list);*/
        //new Aggregator().test();

        //System.out.println(Paths.get(new HtmlView().test()).relativize(Paths.get(new Aggregator().test())));
        //System.out.println(Paths.get(new Aggregator().test()).relativize(Paths.get(new HtmlView().test())));
        //System.out.println(Paths.get(Aggregator.class.getName().replace(".", "/")).relativize(Paths.get(this.getClass().getPackage().getName().replace(".", "/")+"/vacancies.html")));




    }

    /*public String test(){
        //String filePath = this.getClass().getPackage().getName().replace(".", "/");
        //String filePath = Aggregator.class.getPackage()..getName().replace(".", "/");
        String filePath = Aggregator.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        System.out.println(filePath);
        return  filePath;

    }*/

}
