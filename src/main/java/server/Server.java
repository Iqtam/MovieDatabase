package server;

import util.NetworkUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Server {
    private static final String INPUT_FILE_NAME="movies.txt";
    private static final String OUTPUT_FILE_NAME="movies.txt";
    private  List<Movie> movieList;
    private ServerSocket serverSocket;
    public HashMap<String,NetworkUtil> companyNetworkMap;
    public HashMap<String, String> userMap;
    public void readFile(String INPUT_FILE_NAME) throws Exception
    {
        BufferedReader br=new BufferedReader(new FileReader(INPUT_FILE_NAME));
        while(true)
        {
            String line=br.readLine();
            if(line==null)break;
            movieList.add(lineToMovie(line));

        }
        br.close();
    }
    public  Movie lineToMovie(String line)
    {
        String[] s=line.split(",");
        Movie mv=new Movie(s);
        return mv;
    }

    public  List<Movie> getMovieList() {
        return movieList;
    }
//    public static void writeFile(String OUTPUT_FILE_NAME)throws Exception
//    {
//        BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));
//        for(Movie m:movieList)
//        {
//            bw.write(m.getName()+",");
//            bw.write(m.getRelease_year()+",");
//            bw.write(m.getGenre1()+",");
//            bw.write(m.getGenre2()+",");
//            bw.write(m.getGenre3()+",");
//            bw.write(m.getRunning_time()+",");
//            bw.write(m.getCompany()+",");
//            bw.write(m.getBudget()+",");
//            bw.write(Long.toString(m.getRevenue()));
//            bw.write(System.lineSeparator());
//        }
//        bw.close();
//    }
    Server() throws Exception {
        new CloseThreadServer(this);
        movieList=new ArrayList<>();
        companyNetworkMap=new HashMap<>();
        readFile(INPUT_FILE_NAME);
        userMap = new HashMap<>();
        for(Movie m:movieList)
        {
            userMap.put(m.getCompany(),m.getCompany());
        }

        try {
            serverSocket = new ServerSocket(33333);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                serve(clientSocket);
            }
        } catch (Exception e) {
            System.out.println("Server starts:" + e);
        }

    }

    public void serve(Socket clientSocket) throws IOException, ClassNotFoundException {
        NetworkUtil networkUtil = new NetworkUtil(clientSocket);
        //String companyName=(String)networkUtil.read();
        //companyNetworkMap.put(companyName,networkUtil);

        new ReadThreadServer(companyNetworkMap,userMap, networkUtil,this);
        //new CloseThreadServer();
    }

    public static void main(String[] args) throws Exception {
        //movieList=new ArrayList<>();
        new Server();
//        Thread th=new Thread(()->{
//            System.out.println("Write exit to stop server :");
//            Scanner scn=new Scanner(System.in);
//            while(scn.hasNextLine())
//            {
//
//                if(scn.nextLine().equalsIgnoreCase("exit"))
//                {
//                    System.exit(1);
//                    break;
//                }
//            }
//            try {
//                //writeFile(OUTPUT_FILE_NAME);
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        });
//        th.start();
//        th.join();
        //new CloseThreadServer();
    }
}