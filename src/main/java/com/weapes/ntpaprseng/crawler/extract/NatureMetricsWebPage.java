package com.weapes.ntpaprseng.crawler.extract;

import com.weapes.ntpaprseng.crawler.store.MetricsPaper;
import com.weapes.ntpaprseng.crawler.store.Storable;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;

/**
 * Created by lawrence on 16/8/17.
 */
public class NatureMetricsWebPage extends WebPage {

    //存储抽取后的字段和数值
    private Map<String, Integer> hashMap= new HashMap<>();

    //选择器路径
    private static String TotalCitations = "#am-container > div.am-cols.cleared > div > div.am-module.citations-container " +
                                           "> div > section > div > ul > li";

    private static String OnlineAttention = "#altmetric-metrics > div.cleared > div.altmetric-key > ul > li";

    private static String PageViews = "#am-container > div.am-module.pageview-metrics-container.page-views " +
                                      "> article > div > div.page-view-header > h2>span.total";

    public NatureMetricsWebPage(String html, String metricsUrl) {
        super(html, metricsUrl);
    }

    @Override
    // TODO 抽取Metrics信息
    public Storable extract() {

        Document doc = Jsoup.parse(getText());
        String number, referenceUnit;
        //抽取Total citations信息
        Elements citation= doc.select(TotalCitations);
        for (Element element : citation) {

            referenceUnit = element.select("span").text();
            number = element.select("a > div").text();
            if (number != null && !number.equals("")) {
                    hashMap.put(referenceUnit, Integer.parseInt(number));
            }
        }

        //抽取Online attention信息
        Elements onlineAttention= doc.select(OnlineAttention);
        for (Element element : onlineAttention) {

            referenceUnit = element.select("div").text();
            number = element.select("div > b").text();
            if (number != null && !number.equals("")) {
                    hashMap.put(referenceUnit, Integer.parseInt(number));
            }
        }
        final Set<Map.Entry<String,Integer>> entries= hashMap.entrySet();
        final Iterator<Map.Entry<String,Integer>> iterator=entries.iterator();
        return new MetricsPaper(
                getUrl(),
                parsePageViews(doc),
                parseWebOfScience(iterator),
                parseCrossRef(iterator),
                parseScopus(iterator),
                parseNewsOutlets(iterator),
                parseReddit(iterator),
                parseBlog(iterator),
                parseTweet(iterator),
                parseFacebook(iterator),
                parseGoogle(iterator),
                parsePinterest(iterator),
                parseWikipedia(iterator),
                parseMendeley(iterator),
                parseCiteUlink(iterator),
                parseZotero(iterator),
                parseF1000(iterator),
                parseVideo(iterator),
                parseLinkedin(iterator),
                parseQ_A(iterator)
        );
    }

    private int parsePageViews(final Document doc){
          return Integer.parseInt(doc.select(PageViews).text().replace(",",""));
    }
    private int parseWebOfScience(final Iterator<Map.Entry<String,Integer>> iterator){
        while(iterator.hasNext()){
            String key=iterator.next().getKey();
            if (key.toLowerCase().contains("Web Of Science".toLowerCase())){
               return hashMap.get(key);
            }
        }
        return  0;
    }
    private int parseCrossRef(final Iterator<Map.Entry<String,Integer>> iterator){
        while(iterator.hasNext()){
            String key=iterator.next().getKey();
            if (key.toLowerCase().contains("CrossRef".toLowerCase())){
                return hashMap.get(key);
            }
        }
        return  0;
    }
    private int parseScopus(final Iterator<Map.Entry<String,Integer>> iterator){
        while(iterator.hasNext()){
            String key=iterator.next().getKey();
            if (key.toLowerCase().contains("Scopus".toLowerCase())){
                return hashMap.get(key);
            }
        }
        return  0;
    }
    private int parseNewsOutlets(final Iterator<Map.Entry<String,Integer>> iterator){
        while(iterator.hasNext()){
            String key=iterator.next().getKey();
            if (key.contains("News Outlets")){
                return hashMap.get(key);
            }
        }
        return  0;
    }
    private int parseReddit(final Iterator<Map.Entry<String,Integer>> iterator){
        while(iterator.hasNext()){
            String key=iterator.next().getKey();
            if (key.contains("Reddit")){
                return hashMap.get(key);
            }
        }
        return  0;
    }
    private int parseBlog(final Iterator<Map.Entry<String,Integer>> iterator){
        while(iterator.hasNext()){
            String key=iterator.next().getKey();
            if (key.contains("Blogged")){
                return hashMap.get(key);
            }
        }
        return  0;
    }
    private int parseTweet(final Iterator<Map.Entry<String,Integer>> iterator){
        while(iterator.hasNext()){
            String key=iterator.next().getKey();
            if (key.contains("Tweeted")){
                return hashMap.get(key);
            }
        }
        return  0;
    }
    private int parseFacebook(final Iterator<Map.Entry<String,Integer>> iterator){
        while(iterator.hasNext()){
            String key=iterator.next().getKey();
            if (key.contains("Facebook")){
                return hashMap.get(key);
            }
        }
        return  0;
    }
    private int parseGoogle(final Iterator<Map.Entry<String,Integer>> iterator){
        while(iterator.hasNext()){
            String key=iterator.next().getKey();
            if (key.contains("Google+")){
                return hashMap.get(key);
            }
        }
        return  0;
    }
    private int parsePinterest(final Iterator<Map.Entry<String,Integer>> iterator){
        while(iterator.hasNext()){
            String key=iterator.next().getKey();
            if (key.contains("Pinterest")){
                return hashMap.get(key);
            }
        }
        return  0;
    }
    private int parseWikipedia(final Iterator<Map.Entry<String,Integer>> iterator){
        while(iterator.hasNext()){
            String key=iterator.next().getKey();
            if (key.contains("Wikipedia")){
                return hashMap.get(key);
            }
        }
        return  0;
    }
    private int parseMendeley(final Iterator<Map.Entry<String,Integer>> iterator){
        while(iterator.hasNext()){
            String key=iterator.next().getKey();
            if (key.contains("Mendeley")){
                return hashMap.get(key);
            }
        }
        return  0;
    }
    private int parseCiteUlink(final Iterator<Map.Entry<String,Integer>> iterator){
        while(iterator.hasNext()){
            String key=iterator.next().getKey();
            if (key.contains("CiteUlink")){
                return hashMap.get(key);
            }
        }
        return  0;
    }
    private int parseZotero(final Iterator<Map.Entry<String,Integer>> iterator){
        while(iterator.hasNext()){
            String key=iterator.next().getKey();
            if (key.contains("Zetero")){
                return hashMap.get(key);
            }
        }
        return  0;
    }
    private int parseF1000(final Iterator<Map.Entry<String,Integer>> iterator){
        while(iterator.hasNext()){
            String key=iterator.next().getKey();
            if (key.contains("F1000")){
                return hashMap.get(key);
            }
        }
        return  0;
    }
    private int parseVideo(final Iterator<Map.Entry<String,Integer>> iterator){
        while(iterator.hasNext()){
            String key=iterator.next().getKey();
            if (key.contains("Video")){
                return hashMap.get(key);
            }
        }
        return  0;
    }
    private int parseLinkedin(final Iterator<Map.Entry<String,Integer>> iterator){
        while(iterator.hasNext()){
            String key=iterator.next().getKey();
            if (key.contains("Linkedin")){
                return hashMap.get(key);
            }
        }
        return  0;
    }
    private int parseQ_A(final Iterator<Map.Entry<String,Integer>> iterator){
        while(iterator.hasNext()){
            String key=iterator.next().getKey();
            if (key.contains("Q&A")){
                return hashMap.get(key);
            }
        }
        return  0;
    }
    @Override
    public List<? extends ExtractedObject> extractAll() {
        return null;
    }

}