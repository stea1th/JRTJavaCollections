package com.javarush.task.task28.task2810.view;

import com.javarush.task.task28.task2810.Aggregator;
import com.javarush.task.task28.task2810.Controller;
import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

public class HtmlView implements View {

    private Controller controller;

    private final String filePath = "d:/JavaRushTasks/4.JavaCollections/src/"+this.getClass().getPackage().getName().replaceAll("\\.", "/")+"/vacancies.html";
    String content = "<!DOCTYPE html>\n" +
            "<html lang=\"ru\">\n" +
            "<head>\n" +
            "    <meta charset=\"utf-8\">\n" +
            "    <title>Вакансии</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "<table>\n" +
            "    <tr>\n" +
            "        <th>Title</th>\n" +
            "        <th>City</th>\n" +
            "        <th>Company Name</th>\n" +
            "        <th>Salary</th>\n" +
            "    </tr>\n" +
            "    <tr class=\"vacancy\">\n" +
            "        <td class=\"title\"><a href=\"http://javarush.ru\">Junior Java Developer</a></td>\n" +
            "        <td class=\"city\">Entire World</td>\n" +
            "        <td class=\"companyName\">JavaRush</td>\n" +
            "        <td class=\"salary\">1mmm</td>\n" +
            "    </tr>\n" +
            "    <tr class=\"vacancy\">\n" +
            "        <td class=\"title\"><a href=\"http://javarush.ru\">Junior Java Developer 2</a></td>\n" +
            "        <td class=\"city\">Entire World</td>\n" +
            "        <td class=\"companyName\">JavaRush</td>\n" +
            "        <td class=\"salary\">1к</td>\n" +
            "    </tr>\n" +
            "\n" +
            "    <tr class=\"vacancy template\" style=\"display: none\">\n" +
            "        <td class=\"title\"><a href=\"url\"></a></td>\n" +
            "        <td class=\"city\"></td>\n" +
            "        <td class=\"companyName\"></td>\n" +
            "        <td class=\"salary\"></td>\n" +
            "    </tr>\n" +
            "</table>\n" +
            "</body>\n" +
            "</html>";


    @Override
    public void update(List<Vacancy> vacancies) {
        try{
            updateFile(getUpdatedFileContent(vacancies));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private String getUpdatedFileContent(List<Vacancy> list) { //Убрать IOException и поменять public на private!!!!!!!!!!
        Document document = null;
        try {
            document = getDocument();
            Element clone = document.getElementsByClass("template").first();

            Element pattern = clone.clone();
            pattern.removeAttr("style");
            pattern.removeClass("template");
            Elements elements = document.select("tr[class='vacancy']");
            IntStream.range(0, elements.size()).forEach(i-> elements.get(i).remove());
            for(Vacancy vacancy : list){
                Element e = pattern.clone();
                e.select("[class=title]")
                        .select("a").attr("href", vacancy.getUrl()).append(vacancy.getTitle());
                e.select("[class=city]").append(vacancy.getCity());
                e.select("[class=companyName]").append(vacancy.getCompanyName());
                e.select("[class=salary]").append(vacancy.getSalary());

                document.select("tbody").first().children().last().before(e);

            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Some exception occurred";
        }
        System.out.println(document.html());
        return document.outerHtml();
    }

    protected Document getDocument() throws IOException{
        return Jsoup.parse(new File(filePath), "UTF-8");
    }

    private void updateFile(String string)  {   //Изменить content на string и поменять public на private
        try {
            try(FileWriter writer = new FileWriter(new File(filePath))){
                    writer.write(string);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;

    }

    public void userCitySelectEmulationMethod(){
        controller.onCitySelect("Минск");
    }
}
