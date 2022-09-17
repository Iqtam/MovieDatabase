package server;

import java.io.Serializable;

public class Movie implements Serializable{
    private String name;
    private int release_year;
    private String genre1;
    private String genre2;
    private String genre3;
    private int running_time;
    private String company;
    private long budget;
    private long revenue;
    private long profit;

    public Movie()
    {
        name="";
        release_year=0;
        genre1="";
        genre2="";
        genre3="";
        running_time=0;
        company="";
        budget=0;
        revenue=0;
        profit=0;
    }
    public Movie(String[] s)
    {
        this.name=s[0];
        this.release_year=Integer.parseInt(s[1]);
        this.genre1=s[2];
        this.genre2=s[3];
        this.genre3=s[4];
        this.running_time=Integer.parseInt(s[5]);
        this.company=s[6];
        this.budget=Long.parseLong(s[7]);
        this.revenue=Long.parseLong(s[8]);
        this.profit=this.revenue-this.budget;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRelease_year(int release_year) {
        this.release_year = release_year;
    }

    public void setGenre1(String genre1) {
        this.genre1 = genre1;
    }

    public void setGenre2(String genre2) {
        this.genre2 = genre2;
    }

    public void setGenre3(String genre3) {
        this.genre3 = genre3;
    }

    public void setRunning_time(int running_time) {
        this.running_time = running_time;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setBudget(long budget) {
        this.budget = budget;
    }

    public void setRevenue(long revenue) {
        this.revenue = revenue;
    }

    public void setProfit(long profit) {
        this.profit = profit;
    }

    public String getName() {
        return name;
    }

    public int getRelease_year() {
        return release_year;
    }

    public String getGenre1() {
        return genre1;
    }

    public String getGenre2() {
        return genre2;
    }

    public String getGenre3() {
        return genre3;
    }

    public int getRunning_time() {
        return running_time;
    }

    public String getCompany() {
        return company;
    }

    public long getBudget() {
        return budget;
    }

    public long getRevenue() {
        return revenue;
    }

    public long getProfit() {
        return profit;
    }


}
