package com.brain.demo.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ExFsParser {

    public void run(){
        System.out.println("Executing run method");
        new File("parsed").mkdir();
        List<String> urls= parse();
        save(urls);

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
        for (int i = 0; i < urls.size(); i++) {
            String imgUrl = urls.get(i);
            try {
                URL url = new URL(imgUrl);
                BufferedImage image = ImageIO.read(url);
                String imgType = imgUrl.substring(imgUrl.lastIndexOf('.') + 1);
                ImageIO.write(image, imgType, new File("parsed/img_" +i+ '.' + imgType));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }
}
