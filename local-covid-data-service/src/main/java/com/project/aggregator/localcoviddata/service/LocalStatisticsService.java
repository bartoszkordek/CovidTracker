package com.project.aggregator.localcoviddata.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.aggregator.localcoviddata.exception.FutureDateException;
import com.project.aggregator.localcoviddata.model.StatisticsModel;
import com.project.aggregator.localcoviddata.pojo.Root;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

@Service
public class LocalStatisticsService {

    private long getDaysInPast(String requestedDateFormatted) throws ParseException {
        //SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date(System.currentTimeMillis());
        Date requestedDate =new SimpleDateFormat("yyyy-MM-dd").parse(requestedDateFormatted);
        long diffInMillies = currentDate.getTime() - requestedDate.getTime();
        long datesDiff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return datesDiff;
    }

    public StatisticsModel getYesterdayStatistics(String country) throws NoSuchAlgorithmException, IOException, KeyManagementException {
        URL url = new URL("https://localcoviddata.com/covid19/v1/cases/eucdc?country="+country+"&daysInPast=1");
        SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
        sslContext.init(null, null, new SecureRandom());
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setSSLSocketFactory(sslContext.getSocketFactory());
        connection.setRequestProperty("Connection", "keep-alive");
        InputStream response = connection.getInputStream();
        String body = null;
        try (Scanner scanner = new Scanner(response, StandardCharsets.UTF_8.name())) {
            body = scanner.useDelimiter("\\A").next();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        Root root = objectMapper.readValue(body,Root.class);
        StatisticsModel statisticsModel = new StatisticsModel(root.getHistoricData().get(0).getReportedCt(),
                                                              root.getHistoricData().get(0).getDeathCt());
        return statisticsModel;
    }

    public StatisticsModel getCountryDate(String country, String date) throws IOException, KeyManagementException, NoSuchAlgorithmException, ParseException, FutureDateException {

        long daysInPast = getDaysInPast(date);
        if(daysInPast <=0){
            throw new FutureDateException("Date from the future");
        }
        URL url = new URL("https://localcoviddata.com/covid19/v1/cases/eucdc?country="+country+"&daysInPast="+daysInPast);
        SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
        sslContext.init(null, null, new SecureRandom());
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setSSLSocketFactory(sslContext.getSocketFactory());
        connection.setRequestProperty("Connection", "keep-alive");
        InputStream response = connection.getInputStream();
        String body = null;
        try (Scanner scanner = new Scanner(response, StandardCharsets.UTF_8.name())) {
            body = scanner.useDelimiter("\\A").next();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        Root root = objectMapper.readValue(body,Root.class);
        StatisticsModel statisticsModel = new StatisticsModel(root.getHistoricData().get((int) (daysInPast-1)).getReportedCt(),
                                                              root.getHistoricData().get((int) (daysInPast-1)).getDeathCt());
        return statisticsModel;
    }
}
