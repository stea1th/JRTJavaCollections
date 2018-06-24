package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HHStrategy implements Strategy {

    private static final String URL_FORMAT = "http://hh.ru/search/vacancy?text=java+%s&page=%s";
    //private static final String URL_FORMAT = "http://javarush.ru/testdata/big28data.html";

    @Override
    public List<Vacancy> getVacancies(String searchString) throws IOException {
        List<Vacancy> vacancies = new ArrayList<>();
        Vacancy vacancy;
        Document doc = null;
        int i = 0;
        while(true) {
            doc = getDocument(searchString, i);

            Elements elements = doc.select("[data-qa=vacancy-serp__vacancy]");      //"[data-qa=vacancy-serp__vacancy]"
            if (elements.size()==0)
                break;
            for (Element element : elements) {
                if(element!=null) {
                        vacancy = new Vacancy();
                        vacancy.setTitle(element.select("[data-qa=vacancy-serp__vacancy-title]").text());
                        vacancy.setCity(element.select("[data-qa=vacancy-serp__vacancy-address]").text());
                        vacancy.setCompanyName(element.select("[data-qa=vacancy-serp__vacancy-employer]").text());
                        vacancy.setSalary(element.select("[data-qa=vacancy-serp__vacancy-compensation]").text());
                        vacancy.setSiteName(URL_FORMAT);
                        vacancy.setUrl(element.getElementsByTag("a").attr("href"));
                        vacancies.add(vacancy);
                }
            }
            i++;
        }
        return vacancies;
    }

    protected Document getDocument(String searchString, int page) throws IOException{
        return Jsoup.connect(String.format(URL_FORMAT, searchString, page))
                .referrer("http://www.google.ru")
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")
                .get();
    }
}
