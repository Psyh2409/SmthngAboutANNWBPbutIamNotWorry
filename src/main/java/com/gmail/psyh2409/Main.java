package com.gmail.psyh2409;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.time.LocalDate;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Arrays.stream(getEURandUSDrangeNow()).forEach(System.out::println);
        int[][][] cube = new int[3][3][3];

        cube[0][0][0] = 3;
    }

    public static Double[] getEURandUSDrangeNow() {
        String surl = "https://bank.gov.ua/";
        Double[] eurUsdDoubleArr = new Double[2];
        try {
            Element element = Jsoup.parse(
                    new URL(surl), 10000)
                    .selectFirst("div[class=collection macro-indicators]");
            for (int i = 2; i < 4; i++) {
                eurUsdDoubleArr[i-2] = Double.valueOf(element
                        .getElementsByIndexEquals(i)
                        .select("div[class=value-full]")
                        .first()
                        .selectFirst("small")
                        .text()
                        .replaceAll(",", "."));
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return eurUsdDoubleArr;
    }

    public static String getUrlForRequest(String url) {
        url = "https://bank.gov.ua/";
        String usd = "/html/body/main/div/div[1]/div[3]/div/div[2]/div[1]/div[4]/div/div[2]/div/div[2]/small";
        String eur = "/html/body/main/div/div[1]/div[3]/div/div[2]/div[1]/div[3]/div/div[2]/div/div[2]/small";
        try {
            return Jsoup.parse(new URL(url), 10000)
                    .selectFirst("div[class=collection macro-indicators]")
                    .selectFirst("p[class=stan_link]")
                    .children()
                    .first()
                    .toString()
                    .split("\"")[1];
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return "";
    }

//    public static void main(String[] args) {
//        String text = "TEXT";
//        System.out.println("{ \"message\": \"" + text + "\" }");
//    }

//    public static void main(String[] args) {
//        String surl = "https://djinni.co/jobs2/?category=java&location=kyiv&experience=1";
//
//        List<String> surls = djinniParser(surl);
//        List<Vacancy> vacancies = new ArrayList<>();
//
//        surls.forEach(z -> {
//            System.out.println(z);
//        });
//
//        Vacancy vacancy = new Vacancy();
//        vacancy.setVacancyName(getVacancyName(surls.get(0)));
//        String pref = "https://djinni.co";
//        addURLofVacanciesOfCompanyToList(surls.get(0), vacancy, pref);
//        vacancy.setCompanyName(getCompanyName(surls.get(0)));
//        vacancy.setAboutCompany(getStringAboutCompany(surls.get(0)));
//        GetContactPersonAndItsName(surls.get(0), vacancy);
//        vacancy.setTextOfVacancy(surls.get(0));
////        vacancy.setPublication(new LocalDate().now.getYear().plus(4));
//
//    }

    private static void GetContactPersonAndItsName(String surls, Vacancy vacancy) {
        String cp = docFromStrURL(surls).body()
                .child(0)
                .child(1)
                .child(3)
                .child(1)
                .child(0)
                .child(1)
                .child(0)
                .text();
        vacancy.setCpName(cp);
        vacancy.setContactPerson(docFromStrURL(surls).body()
                .child(0)
                .child(1)
                .child(3)
                .child(1)
                .child(0)
                .child(1)
                .text()
                .replace(vacancy.getCpName(), "")
                .trim());
    }

    private static String getStringAboutCompany(String surls) {
        return docFromStrURL(
                surls)
                .body()
                .child(0)
                .child(1)
                .child(3)
                .child(2)
                .child(0)
                .child(1)
                .getAllElements()
                .text();
    }

    private static String getCompanyName(String surls) {
        return getChildA(surls).text();
    }

    private static void addURLofVacanciesOfCompanyToList(String surls, Vacancy vacancy, String pref) {
        try {
            vacancy.getUrls()
                    .add(new URL(pref.concat(getChildA(surls)
                            .getAllElements()
                            .attr("href"))));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private static Element getChildA(String surls) {
        return docFromStrURL(surls)
                .body()
                .child(0)
                .child(1)
                .child(1)
                .child(2)
                .child(0);
    }

    private static String getVacancyName(String surl) {
        return docFromStrURL(
                surl)
                .body()
                .child(0)
                .child(1)
                .child(1)
                .child(1)
                .text();
    }

    private static List<String> djinniParser(String surl) {
        String prefix = "https://djinni.co";
        List<String> listSURL = new ArrayList<>();
        docFromStrURL(surl)
                .body()
                .child(0)
                .child(1)
                .child(0)
                .child(1)
                .children().forEach(x -> {
            listSURL.add(
                    prefix.concat(x
                            .child(1)
                            .child(0)
                            .child(0)
                            .toString()
                            .replaceAll("<a rel=\"prefetch\" href=\"", "")
                            .split("\"")[0]
                    ));
        });
        return listSURL;
    }

    private static String textOfVacancy(String surl) {
        String textWithFinalTag =
                docFromStrURL(surl)
                        .body()
                        .child(0)
                        .child(1)
                        .child(3)
                        .child(0)
                        .child(1)
                        .toString()
                        .substring(25);
        return textWithFinalTag.substring(0, textWithFinalTag.length() - 4);
    }

    private static Document docFromStrURL(String surl) {
        try {
            return Jsoup.parse(new URL(surl), 10000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getRequestor(String url) {
        StringBuffer response = null;
        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("GET");
            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String readLine;
                response = new StringBuffer();
                while (true) {
                    if (!((readLine = br.readLine()) != null)) break;
                    response.append(readLine);
                    System.out.println(readLine);
                }
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return response.toString();
    }
}
