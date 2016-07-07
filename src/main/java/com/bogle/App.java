package com.bogle;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.stream.Collectors;

/**
 * Hello world!
 */
public class App {

    public void parseHtml(String url) throws IOException {

        int r1 = 0, r2 = 0, r3 = 0, r4 = 0, r5 = 0, r6 = 0, b1 = 0;

        File file = new File("E:/" + System.currentTimeMillis() + ".txt");

        FileOutputStream fos = new FileOutputStream(file);
        BufferedOutputStream bos = new BufferedOutputStream(fos);

        Document doc = Jsoup.connect(url).get();
        Elements tr = doc.select("TABLE[borderColor=#ffffff] TR[align=middle]");
        Iterator<Element> iter = tr.iterator();
        int count = 0;
        while (iter.hasNext()) {
            Element element = iter.next();
            Element child = element.child(0);
            count++;
            if (child.hasAttr("bgcolor") || count == 2) continue;

            Element child1 = element.child(0);
            Element child2 = element.child(1);
            String text = child1.text() + " : " + child2.text() + "\n";
            byte[] bytes = text.getBytes();
            bos.write(bytes);

            String str = child2.text();
            String[] ball = str.split("\\+");
            String redStr = ball[0];
            String blue = ball[1];


            String[] reds = redStr.split(" ");
            r1 += Integer.parseInt(reds[0]);
            r2 += Integer.parseInt(reds[1]);
            r3 += Integer.parseInt(reds[2]);
            r4 += Integer.parseInt(reds[3]);
            r5 += Integer.parseInt(reds[4]);
            r6 += Integer.parseInt(reds[5]);
            blue = blue.trim();
            if (blue.startsWith("0")) blue = blue.substring(1);
            b1 += Integer.parseInt(blue);

            if (element.html().contains("language=\"JavaScript\"")) break;
        }
        count = count - 2;


        byte[] bytes = new String("count : " + count + " 推荐号 ： " + r1 + " " + r2 + " " + r3 + " " + r4 + " " + r5 + " " + r6 + "+" + b1+ " \n").getBytes();
        bos.write(bytes);

        r1 = r1 / count;
        r2 = r2 / count;
        r3 = r3 / count;
        r4 = r4 / count;
        r5 = r5 / count;
        r6 = r6 / count;

        b1 = b1 / count;

        bytes = new String("推荐号 ： " + r1 + " " + r2 + " " + r3 + " " + r4 + " " + r5 + " " + r6 + "+" + b1).getBytes();
        bos.write(bytes);
        bos.close();
    }


    public void parseHtml1(String url) throws IOException {
        int r1 = 0, r2 = 0, r3 = 0, r4 = 0, r5 = 0, r6 = 0, b1 = 0;

        File file = new File("E:/" + System.currentTimeMillis() + ".txt");

        FileOutputStream fos = new FileOutputStream(file);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        Document doc = Jsoup.parse(new File("E:/test.html"), "utf-8");
        Elements tr = doc.select("table[cellpadding=1].T tr");
        Iterator<Element> iter = tr.iterator();
        int count = 0;
        while (iter.hasNext()) {

            Element element = iter.next();
            Elements elements = element.select("td.wqh");
            String period = elements.html().trim();
            if ("".equals(period)) continue;
            count++;
            // 这里有中奖号码
            Elements redElements = element.select("td.h1,td.h8");
            Elements blueElements = element.select("td.h6");

            String blue = blueElements.text().trim();
            String blueText = blueElements.text().trim();

            if (blue.startsWith("0")) blue = blue.substring(1);
            b1 += Integer.parseInt(blue);


            Iterator<Element> iter1 = redElements.iterator();
            int index = 0;
            String redText = "";
            while (iter1.hasNext()) {
                index++;
                String red = iter1.next().text().trim();
                redText += red + " ";
                if (red.startsWith("0")) red = red.substring(1);
                int redball = Integer.parseInt(red);
                if (index == 1) {
                    r1 += redball;
                } else if (index == 2) {
                    r2 += redball;
                } else if (index == 3) {
                    r3 += redball;
                } else if (index == 4) {
                    r4 += redball;
                } else if (index == 5) {
                    r5 += redball;
                } else if (index == 6) {
                    r6 += redball;
                }
            }
            bos.write(new String(period + "\t\t" + redText + " + " + blueText + "\n").getBytes());
        }

        byte[] bytes = new String("count : " + count + " 推荐号 ： " + r1 + " " + r2 + " " + r3 + " " + r4 + " " + r5 + " " + r6 + "+" + b1 + " \n").getBytes();
        bos.write(bytes);

        r1 = r1 / count;
        r2 = r2 / count;
        r3 = r3 / count;
        r4 = r4 / count;
        r5 = r5 / count;
        r6 = r6 / count;

        b1 = b1 / count;

        bytes = new String("推荐号 ： " + r1 + " " + r2 + " " + r3 + " " + r4 + " " + r5 + " " + r6 + "+" + b1).getBytes();
        bos.write(bytes);
        bos.close();
    }


    private void message(String s) {
        System.out.print(s);
    }
}
