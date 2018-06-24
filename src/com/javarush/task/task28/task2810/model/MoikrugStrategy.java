package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MoikrugStrategy implements Strategy {

    //private static final String URL_FORMAT = "https://moikrug.ru/vacancies?page=%s&q=java+%s&type=all";
    //private static final String URL_FORMAT = "http://javarush.ru/testdata/big28data2.html";
    //private static final String URL_FORMAT = "https://moikrug.ru/vacancies?q=java+%s&page=%s";
    private static final String URL_FORMAT= "https://moikrug.ru/vacancies?q=java+%s&page=%d";


    @Override
    public List<Vacancy> getVacancies(String searchString) throws IOException {
        List<Vacancy> vacancies = new ArrayList<>();
        Vacancy vacancy;
        Document doc = null;
        int i = 0;

        while(true) {
            doc = getDocument(searchString, i);
            Elements elements = doc.getElementsByClass("job");
            //System.out.println(doc.html());
            if (elements.size() == 0) {
                break;
            }
            for (Element element : elements) {
                if (element != null) {
                    /*vacancy = new Vacancy();
                    vacancy.setTitle(element.select("[class=title]").attr("title"));
                    vacancy.setUrl(/*URL_FORMAT.split("/vacancies")[0]*//*"https://moikrug.ru" + element.getElementsByTag("a").attr("href"));
                    //vacancy.setCompanyName(element.getElementsByClass("company_name").tagName("a").text());
                    vacancy.setCompanyName(element.getElementsByAttributeValue("class", "company_name").text());
                    vacancy.setSiteName(URL_FORMAT);
                    vacancy.setCity(element.getElementsByClass("location").tagName("a").text());
                    vacancy.setSalary(element.getElementsByClass("count").text());*/
                    Vacancy vac = new Vacancy();
                    vac.setTitle(element.getElementsByAttributeValue("class", "title").text());
                    vac.setCompanyName(element.getElementsByAttributeValue("class", "company_name").text());
                    vac.setSiteName(URL_FORMAT);
                    vac.setUrl("https://moikrug.ru" + element.select("a[class=job_icon]").attr("href"));
                    String salary = element.getElementsByAttributeValue("class", "salary").text();
                    String city = element.getElementsByAttributeValue("class", "location").text();
                    vac.setSalary(salary.length()==0 ? "" : salary);
                    vac.setCity(city.length()==0 ? "" : city);
                    vacancies.add(vac);
                }
            }
            i++;
        }
        return vacancies;
    }

    protected Document getDocument(String searchString, int page) throws IOException{
        return Jsoup.connect(String.format(URL_FORMAT, searchString, page))
                .referrer("http://www.google.ru")
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36")
                .get();
    }
}
