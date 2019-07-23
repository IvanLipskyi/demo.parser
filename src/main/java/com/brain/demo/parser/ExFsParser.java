package com.brain.demo.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExFsParser {

    public void run(){
        System.out.println("Executing run method");
        parse();
        save();

    }

    private void save(){
        System.out.println("Try to save");
    }

    private List<String> parse(){
        System.out.println("Parsing page");
        String url = "http://ex-fs.com/page/1";
        List<String> result = new ArrayList<>();
        try {
            Document document = Jsoup.connect(url).get();
            Elements links = document.select(".custom-poster img[src]");
            for (Element link: links){
        String imgUrl = "http://ex-fs.com/" + link.attr("src");
        result.add(imgUrl);
                System.out.println(imgUrl);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private void save (List<String> urls){

    }
}
